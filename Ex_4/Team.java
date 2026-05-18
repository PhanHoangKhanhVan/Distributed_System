// Team.java
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<String> players = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    // Method 1: Thêm cầu thủ
    public String addPlayer(String playerName) {
        players.add(playerName);
        return "OK: " + playerName + " added to " + name;
    }

    // Method 2: Xóa cầu thủ
    public String removePlayer(String playerName) {
        if (players.remove(playerName)) {
            return "OK: " + playerName + " removed from " + name;
        }
        return "ERROR: " + playerName + " not found in " + name;
    }

    // Method 3: Lấy danh sách cầu thủ
    public String getPlayers() {
        if (players.isEmpty()) return name + ": (no players)";
        return name + ": " + String.join(", ", players);
    }

    // Method 4: Lấy tên đội
    public String getName() {
        return name;
    }
}