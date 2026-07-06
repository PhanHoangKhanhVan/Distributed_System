//defining remote interface
import java.rmi.Remote;
import java.rmi.RemoteExeption;
import java.rmi.*

public interface Server extends Remotes {
    //declaring method
    public String query(String search) throws RemoteExeption;
}