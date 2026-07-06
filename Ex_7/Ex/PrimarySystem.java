import jakarta.jms.*;
import javax.naming.*;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Primary system: hosts the master copy of the Team.
 * - User can add a new player or display the current set of players.
 * - When a player is added, it is propagated to both secondary systems
 *   using a separate Point-to-Point queue for each link
 *   (queue.secondary1 and queue.secondary2), transported as a
 *   JMS ObjectMessage.
 *
 * Run with:
 *   java -Dorg.apache.activemq.SERIALIZABLE_PACKAGES=* -cp "out:$CLASSPATH" PrimarySystem
 */
public class PrimarySystem {

    private static final String QUEUE_SECONDARY_1 = "queue.secondary1";
    private static final String QUEUE_SECONDARY_2 = "queue.secondary2";

    public static void main(String[] args) throws Exception {

        Team team = new Team("MyTeam");

        Hashtable<String, String> properties = new Hashtable<>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);

        QueueConnectionFactory connFactory =
                (QueueConnectionFactory) context.lookup("ConnectionFactory");

        QueueConnection conn = connFactory.createQueueConnection();
        QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue1 = (Queue) context.lookup("dynamicQueues/" + QUEUE_SECONDARY_1);
        Queue queue2 = (Queue) context.lookup("dynamicQueues/" + QUEUE_SECONDARY_2);

        QueueSender sender1 = session.createSender(queue1);
        QueueSender sender2 = session.createSender(queue2);

        conn.start();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Primary System ===");
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Player name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Player number: ");
                    int number = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Player position: ");
                    String position = scanner.nextLine().trim();

                    Player player = new Player(name, number, position);
                    team.addPlayer(player);

                    ObjectMessage msg1 = session.createObjectMessage(player);
                    sender1.send(msg1);

                    ObjectMessage msg2 = session.createObjectMessage(player);
                    sender2.send(msg2);

                    System.out.println("Added player and propagated to both secondaries: " + player);
                    break;

                case "2":
                    team.display();
                    break;

                case "3":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        session.close();
        conn.close();
        scanner.close();
        System.out.println("Primary system shut down.");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1) Add new player");
        System.out.println("2) Display current set of players");
        System.out.println("3) Exit");
        System.out.print("Choice: ");
    }
}
