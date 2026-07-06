import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Server entry point.
//Start order:
//  1.  rmiregistry &          (in the directory containing the compiled classes)
//  2.  java Server
//The server creates two TeamImpl objects and binds them in the RMI registry under the names "FC_Bayern" and "Borussia_Dortmund".
//All team and player objects live exclusively on the server.
public class Server {

    public static void main(String[] args) {
        try {
            // Locate the registry started by rmiregistry (default port 1099)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            //Team 1: FC Bayern
            TeamImpl teamA = new TeamImpl("FC Bayern");
            teamA.addPlayer("Manuel Neuer", "Goalkeeper", 38);
            teamA.addPlayer("Joshua Kimmich", "Midfielder", 29);

            //Team 2: Borussia Dortmund
            TeamImpl teamB = new TeamImpl("Borussia Dortmund");
            teamB.addPlayer("Gregor Kobel", "Goalkeeper", 26);
            teamB.addPlayer("Marco Reus", "Midfielder", 35);

            // Bind both teams in the registry
            registry.rebind("FC_Bayern", teamA);
            registry.rebind("Borussia_Dortmund", teamB);

            System.out.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
