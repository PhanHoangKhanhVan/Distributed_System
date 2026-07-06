import java.io.*;
import java.net.*;

public class TeamProxy {
    private int teamId;
    private String serverHost;
    private int serverPort;

    public TeamProxy(int teamId, String serverHost, int serverPort) {
        this.teamId     = teamId;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    private Player sendPlayerRequest(String request) throws IOException, ClassNotFoundException {
    Socket s = new Socket(serverHost, serverPort);
    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
    ObjectInputStream  in  = new ObjectInputStream(s.getInputStream());
    out.writeUTF(request);
    Player p = (Player) in.readObject(); // nhận Player object trực tiếp
    s.close();
    return p;
}

     // Returns string
    public String addPlayer(String name, String position, int age) throws IOException {
    return sendRequest(teamId + "|addPlayer|" + name + "|" + position + "|" + age);
    }

// Returns player info as string, or "NOT_FOUND"
    // public String searchPlayer(String name) throws IOException {
    // return sendRequest(teamId + "|searchPlayer|" + name);
    // }

    public Player searchPlayer(String name) throws IOException, ClassNotFoundException {
    return sendPlayerRequest(teamId + "|searchPlayer|" + name);
}

// Returns all players as string: "name,position,age;name,position,age;..."
    public String getPlayers() throws IOException {
        return sendRequest(teamId + "|getPlayers");
    }

    public Player getPlayer(String name) throws IOExeption{
        return sendPlayerRequest(teamId + "|getPlayers");
    }
    
    public String getName() throws IOException {
        return sendRequest(teamId + "|getName");
    }
}

// method riêng gửi request và nhận về Player object
private Player sendPlayerRequest(String request) throws IOException, ClassNotFoundException {
    Socket s = new Socket(serverHost, serverPort);
    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
    ObjectInputStream  in  = new ObjectInputStream(s.getInputStream());
    out.writeUTF(request);
    Player p = (Player) in.readObject(); // nhận Player object trực tiếp
    s.close();
    return p;
}

public Player searchPlayer(String name) throws IOException, ClassNotFoundException {
    return sendPlayerRequest(teamId + "|searchPlayer|" + name);
}