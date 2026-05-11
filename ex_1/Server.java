package ex_1;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws Exception {
        Map<String, Team> teams = new HashMap<>();
        teams.put("T1", new Team("VGU Warriors"));
        teams.put("T2", new Team("Dortmund Stars"));

        ServerSocket ss = new ServerSocket(1234);
        while (true) {
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            String line = in.readLine();
            String[] parts = line.split("#");
            Team target = teams.get(parts[0]);
            String method = parts[1];

            if (method.equals("getName")) {
                out.println(target.getName());
            } else if (method.equals("addPlayer")) {
                String[] p = parts[2].split(",");
                target.addPlayer(p[0], p[1], Integer.parseInt(p[2]));
                out.println("OK");
            } else if (method.equals("getPlayers")) {
                // Biến Set<Player> thành chuỗi để gửi đi
                StringBuilder sb = new StringBuilder();
                for (Player p : target.getPlayers()) {
                    sb.append(p.toString()).append(";");
                }
                out.println(sb.toString());
            }
            s.close();
        }
    }
}