import java.util.HashSet;
import java.util.Set;

//Represents a sports club team with a name and a set of player objects.
public class Team {

    private String name;
    private Set<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new HashSet<>();
    }

    //Searches for a player by name. Returns null if not found.
    public Player findPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    //Creates a new player and adds them to the team.
    public void addPlayer(String name, String position, int age) {
        players.add(new Player(name, position, age));
    }

    //Returns the full set of player objects.
    public Set<Player> getPlayers() {
        return players;
    }

    //Returns the team name.
    public String getName() {
        return name;
    }
}
