import java.rmi.Remote;
import java.rmi.RemoteException;

//danh ba lien lac tu xa
public interface PlayerInterface extends Remote {
    public String getName() throws RemoteException;
    public String getPosition() throws RemoteException;
    public String getAge() throws RemoteException;
}