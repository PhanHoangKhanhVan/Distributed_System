package Ex_4;
import java.util.*;

public class Team {
    private String name;
    private Set<Player> players = new HashSet<>();

    public Team(String name) { this.name = name; }

    public String getName() { return name; }

    public void addPlayer(String name, String pos, int age) {
        players.add(new Player(name, pos, age));
    }

    public Player searchPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public Set<Player> getPlayers() { return players; }
}