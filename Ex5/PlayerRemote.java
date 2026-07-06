import java.rmi.Remote;
import java.rmi.RemoteException;

//Remote interface for a Player object.
//All methods declared here can be invoked from the client.
public interface PlayerRemote extends Remote {

    String getName()     throws RemoteException;
    String getPosition() throws RemoteException;
    int    getAge()      throws RemoteException;
    void   setAge(int age) throws RemoteException;
}
