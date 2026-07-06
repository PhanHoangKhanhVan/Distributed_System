import java.rmi.*;
import java.rmi.registry.*;
public class SearchServer{
    public static void main(String args[]){
        try{
            //create object of interface
            //implementation class
            Search obj = new SearchQuery();

            //rmiregistry within the server JVM with port # 1990
            LocateRegistry.createRegistry(1990);

            //binds the remote object by the name .....
            Naming.rebind("rmi://localhost:1990"+"/meee", obj);
        }
        catch(Exeption ae)
    }
}