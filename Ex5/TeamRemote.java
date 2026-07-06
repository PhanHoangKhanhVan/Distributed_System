import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

//Remote interface for a Team object.
//All methods declared here can be invoked from the client.
public interface TeamRemote extends Remote {

    //Returns the team name.
    String getName() throws RemoteException;

    //Adds a new player to the team (creates the player object on the server).
    void addPlayer(String name, String position, int age) throws RemoteException;

    //Searches for a player by name and returns a remote reference.
    //Returns null if no player with that name exists.
    PlayerRemote findPlayer(String name) throws RemoteException;

    //Returns the set of all player remote references belonging to this team.
    Set<PlayerRemote> getPlayers() throws RemoteException;
}
