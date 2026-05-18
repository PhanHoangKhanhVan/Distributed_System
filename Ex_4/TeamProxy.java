// TeamProxy.java
import java.io.*;
import java.net.*;

public class TeamProxy {
    private int teamId;        // 1 hoặc 2 — để server biết dùng object nào
    private String serverHost;
    private int serverPort;

    public TeamProxy(int teamId, String serverHost, int serverPort) {
        this.teamId = teamId;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    // Hàm nội bộ: gửi request string, nhận response string
    private String sendRequest(String request) throws IOException {
        Socket s = new Socket(serverHost, serverPort);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in   = new DataInputStream(s.getInputStream());

        out.writeUTF(request);           // Gửi encoded string
        String response = in.readUTF();  // Nhận kết quả
        s.close();
        return response;
    }

    // Các method có cùng signature với Team:

    public String addPlayer(String playerName) throws IOException {
        // Format: "teamId|addPlayer|playerName"
        return sendRequest(teamId + "|addPlayer|" + playerName);
    }

    public String removePlayer(String playerName) throws IOException {
        return sendRequest(teamId + "|removePlayer|" + playerName);
    }

    public String getPlayers() throws IOException {
        return sendRequest(teamId + "|getPlayers");
    }

    public String getName() throws IOException {
        return sendRequest(teamId + "|getName");
    }
}