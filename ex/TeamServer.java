import java.io.*;
import java.net.*;

public class TeamServer {
    public static void main(String[] args) {
        int port = 7896;

        Team[] teams = {
            new Team("Bayern"),
            new Team("Hessen")
        };

        System.out.println("TeamServer started on port " + port);

        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                new RequestHandler(clientSocket, teams).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

class RequestHandler extends Thread {
    private Socket socket;
    private Team[] teams;

    public RequestHandler(Socket socket, Team[] teams) {
        this.socket = socket;
        this.teams  = teams;
    }

    public void run() {
        try {
            DataInputStream  in  = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String request = in.readUTF();
            System.out.println("Received: " + request);

            String result = dispatch(request);
            out.writeUTF(result);
            socket.close();
        } catch (IOException e) {
            System.out.println("Handler error: " + e.getMessage());
        }
    }

    private String dispatch(String request) {
        String[] parts    = request.split("\\|");
        int      teamIndex = Integer.parseInt(parts[0]) - 1;
        String   method   = parts[1];

        if (teamIndex < 0 || teamIndex >= teams.length)
            return "Invalid team ID";

        Team team = teams[teamIndex];

        switch (method) {
            case "addPlayer":
                // parts: [teamId, addPlayer, name, position, age]
                return team.addPlayer(parts[2], parts[3], Integer.parseInt(parts[4]));

            case "searchPlayer": {
                Player p = team.searchPlayer(parts[2]);
                if (p == null) return "NOT_FOUND";
                // encode player as "name,position,age"
                return p.getName() + "," + p.getPosition() + "," + p.getAge();
            }

            case "getPlayers": {
                // encode each player as "name,position,age" separated by ";"
                StringBuilder sb = new StringBuilder();
                for (Player p : team.getPlayers()) {
                    if (sb.length() > 0) sb.append(";");
                    sb.append(p.getName()).append(",")
                      .append(p.getPosition()).append(",")
                      .append(p.getAge());
                }
                return sb.length() == 0 ? "EMPTY" : sb.toString();
            }

            case "getName":
                return team.getName();

            default:
                return "ERROR: Unknown method: " + method;
        }
    }
}