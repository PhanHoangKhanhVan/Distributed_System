import jakarta.jms.*;
import javax.naming.*;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Secondary system: hosts a read-only replica of the Team.
 * - Receives new Player-objects from the primary system via a dedicated
 *   Point-to-Point queue, using a MessageListener so receiving does NOT
 *   block (task 5, non-blocking receiver).
 * - Every time the replica changes, the current set of players is
 *   displayed automatically.
 * - The user can also trigger a display on demand at any time via a
 *   simple text menu running on the main thread.
 *
 * Run with (one instance per secondary, pass the matching queue name):
 *   java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp "out:$CLASSPATH" SecondarySystem queue.secondary1
 *   java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp "out:$CLASSPATH" SecondarySystem queue.secondary2
 */
public class SecondarySystem {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: java SecondarySystem <queueName>");
            System.out.println("Example: java SecondarySystem queue.secondary1");
            return;
        }

        String queueName = args[0];
        Team replica = new Team("MyTeam (replica via " + queueName + ")");

        Hashtable<String, String> properties = new Hashtable<>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);

        QueueConnectionFactory connFactory =
                (QueueConnectionFactory) context.lookup("ConnectionFactory");

        QueueConnection conn = connFactory.createQueueConnection();
        QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = (Queue) context.lookup("dynamicQueues/" + queueName);
        QueueReceiver receiver = session.createReceiver(queue);

        // Non-blocking receive: register a MessageListener instead of
        // calling receiver.receive() in a loop.
        receiver.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof ObjectMessage) {
                        ObjectMessage objMsg = (ObjectMessage) message;
                        Object obj = objMsg.getObject();
                        if (obj instanceof Player) {
                            Player player = (Player) obj;
                            replica.addPlayer(player);
                            System.out.println();
                            System.out.println("[" + queueName + "] New player received: " + player);
                            replica.display();
                            System.out.print("\nChoice: "); // reprint prompt for the UI thread
                        }
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        conn.start();

        System.out.println("=== Secondary System (" + queueName + ") ===");
        System.out.println("Listening for updates from the primary system (non-blocking)...");

        // Main thread stays free for the on-demand UI because receiving
        // happens asynchronously in the MessageListener above.
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    replica.display();
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        session.close();
        conn.close();
        scanner.close();
        System.out.println("Secondary system (" + queueName + ") shut down.");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1) Display current set of players");
        System.out.println("2) Exit");
        System.out.print("Choice: ");
    }
}
