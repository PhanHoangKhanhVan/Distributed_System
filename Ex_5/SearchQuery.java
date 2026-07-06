//implement the remote interface
import java.rmi.*;
import java.rmi.server.*;

public class SearchQuery extends UnicastRemoteObject implements Search{
    //default constructor to throws RemoteExeption from its parent contructor
    SearchQuery() throws RemoteExeption{
        super();
    }

    //implementation of the query interface
    public String query(String search) throws RemoteExeption{
        String result;
        if (search.equals("Reflection in Java"))
            result = "Found";
        else
            result = "Not Found";
        return result;
    }
}