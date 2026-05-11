package ex_1;
import java.io.*;
import java.net.*;
import java.util.*;

public class TeamProxy {
    private String teamId;
    private String host = "localhost";
    private int port = 1234;

    public TeamProxy(String id) { this.teamId = id; }

    public String getName() {
        return sendRequest("getName", "");
    }

    public void addPlayer(String n, String p, int a) {
        sendRequest("addPlayer", n + "," + p + "," + a);
    }

    public Set<Player> getPlayers() {
        String response = sendRequest("getPlayers", "");
        Set<Player> set = new HashSet<>();
        if (response.isEmpty()) return set;
        
        // Giả sử server trả về: "Name1,Pos1,Age1;Name2,Pos2,Age2"
        String[] playersData = response.split(";");
        for (String data : playersData) {
            String[] p = data.split(",");
            set.add(new Player(p[0], p[1], Integer.parseInt(p[2])));
        }
        return set;
    }

    private String sendRequest(String method, String params) {
        try (Socket s = new Socket(host, port);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            
            out.println(teamId + "#" + method + "#" + params);
            return in.readLine();
        } catch (Exception e) { return ""; }
    }
}