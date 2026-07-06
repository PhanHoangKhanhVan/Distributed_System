import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Server-side implementation of the PlayerRemote interface.
//Each Player exists only on the server as a UnicastRemoteObject.
public class PlayerImpl extends UnicastRemoteObject implements PlayerRemote {

    private String name;
    private String position;
    private int age;

    public PlayerImpl(String name, String position, int age) throws RemoteException {
        super();
        this.name     = name;
        this.position = position;
        this.age      = age;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getPosition() throws RemoteException {
        return position;
    }

    @Override
    public int getAge() throws RemoteException {
        return age;
    }

    @Override
    public void setAge(int age) throws RemoteException {
        this.age = age;
    }
}
