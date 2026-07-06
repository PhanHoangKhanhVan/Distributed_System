import java.rmi.Remote;
import java.rmi.RemoteExeption;

//Danh ba cua Team
public interface TeamInterface extends Remote {
    //nguoi tu xa chi goi duoc ham trong danh ba nay
    public String getTeamName() throws RemoteException;
    public void addPlayer() throws RemoteException;
    public Player searchPlayer() throws RemmoteException;
    public Set<Player> getAllItems() throws RemoteException;
}