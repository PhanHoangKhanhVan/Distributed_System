// TeamServer.java
import java.io.*;
import java.net.*;

public class TeamServer {
    public static void main(String[] args) {
        int port = 7896;

        // Hai Team objects — chỉ tồn tại ở server
        Team[] teams = {
            new Team("FC Bayern"),   // teamId = 1
            new Team("Borussia")     // teamId = 2
        };

        System.out.println("TeamServer started on port " + port);

        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                // Mỗi request xử lý trong một thread riêng
                new RequestHandler(clientSocket, teams).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

// Thread xử lý từng request
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

    // Decode string và gọi method tương ứng
    private String dispatch(String request) {
        // Tách theo dấu "|"
        String[] parts = request.split("\\|");

        int    teamId     = Integer.parseInt(parts[0]) - 1; // 0-indexed
        String methodName = parts[1];

        if (teamId < 0 || teamId >= teams.length) {
            return "ERROR: Invalid team ID";
        }

        Team team = teams[teamId];

        switch (methodName) {
            case "addPlayer":
                return team.addPlayer(parts[2]);
            case "removePlayer":
                return team.removePlayer(parts[2]);
            case "getPlayers":
                return team.getPlayers();
            case "getName":
                return team.getName();
            default:
                return "ERROR: Unknown method: " + methodName;
        }
    }
}