import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team. The primary system holds the master copy, each
 * secondary system holds a replica. Only the primary copy may be modified
 * by a user directly; secondary copies are only updated via replicated
 * Player-objects sent over JMS.
 */
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String teamName;
    private final List<Player> players = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public synchronized void addPlayer(Player p) {
        players.add(p);
    }

    public synchronized List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public String getTeamName() {
        return teamName;
    }

    public synchronized void display() {
        System.out.println("---- Team: " + teamName + " ----");
        if (players.isEmpty()) {
            System.out.println("(no players yet)");
        } else {
            for (Player p : players) {
                System.out.println("  " + p);
            }
        }
        System.out.println("--------------------------------");
    }
}
