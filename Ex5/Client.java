import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

//Client entry point.
//The client connects to the RMI registry, looks up both teams,and exercises all remote methods — including calculating the, average age of each team's players.
//Run AFTER starting the rmiregistry and the Server.
public class Client {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            //Look up both teams
            TeamRemote teamA = (TeamRemote) registry.lookup("FC_Bayern");
            TeamRemote teamB = (TeamRemote) registry.lookup("Borussia_Dortmund");

            //Print rosters 
            printRoster(teamA);
            printRoster(teamB);

            //Average age 
            System.out.println(" Average Ages");
            System.out.printf("%s: %.1f years%n", teamA.getName(), averageAge(teamA));
            System.out.printf("%s: %.1f years%n", teamB.getName(), averageAge(teamB));

            //Search for a specific player (remote call)
            System.out.println("\nPlayer Search");
            PlayerRemote kane = teamA.findPlayer("Harry Kane");
            if (kane != null) {
                System.out.println("Found: " + kane.getName() + kane.getPosition() + " age " + kane.getAge());
            }

            //Change a player's age via remote method
            System.out.println("\nUpdate Age");
            PlayerRemote kimmich = teamA.findPlayer("Joshua Kimmich");
            if (kimmich != null) {
                System.out.println("Before: " + kimmich.getName() + ", age " + kimmich.getAge());
                kimmich.setAge(kimmich.getAge() + 1);   // remote call
                System.out.println("After:  " + kimmich.getName() + ", age " + kimmich.getAge());
            }

            //Verify the change is persisted on the server
            System.out.println("\nRe-fetch Kimmich from server to confirm persistence");
            PlayerRemote kimmichCheck = teamA.findPlayer("Joshua Kimmich");
            if (kimmichCheck != null) {
                System.out.println("Server age: " + kimmichCheck.getAge());
            }

        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Prints all players of a team by calling remote methods.
    private static void printRoster(TeamRemote team) throws Exception {
        System.out.println("\n" + team.getName());
        Set<PlayerRemote> players = team.getPlayers();
        for (PlayerRemote p : players) {
            System.out.printf("  %-20s | %-12s | age %d%n", p.getName(), p.getPosition(), p.getAge());
        }
    }

    //Calculates the average age by invoking getAge() on each remote player stub.
    private static double averageAge(TeamRemote team) throws Exception {
        Set<PlayerRemote> players = team.getPlayers();
        if (players.isEmpty()) return 0;
        int total = 0;
        for (PlayerRemote p : players) {
            total += p.getAge();
        }
        return (double) total / players.size();
    }
}
