import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

//Server-side implementation of the TeamRemote interface.
//Each Team exists only on the server as a UnicastRemoteObject.
//The players set holds remote references to PlayerImpl objects,
//also living solely on the server.

public class TeamImpl extends UnicastRemoteObject implements TeamRemote {

    private String name;
    private Set<PlayerRemote> players;   // set of remote player objects

    public TeamImpl(String name) throws RemoteException {
        super();
        this.name    = name;
        this.players = new HashSet<>();
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    // Creates a new PlayerImpl on the server and stores it in the set.

    @Override
    public void addPlayer(String name, String position, int age) throws RemoteException {
        PlayerImpl player = new PlayerImpl(name, position, age);
        players.add(player);
    }

    //Searches for a player by name; returns the remote reference or null.
    @Override
    public PlayerRemote findPlayer(String name) throws RemoteException {
        for (PlayerRemote p : players) {
            if (p.getName().equals(name)) {
                return p;   // client receives a remote stub
            }
        }
        return null;
    }

    //Returns the full set of remote player references.
    //The client can call methods on each stub without data leaving the server.

    @Override
    public Set<PlayerRemote> getPlayers() throws RemoteException {
        return players;
    }
}
