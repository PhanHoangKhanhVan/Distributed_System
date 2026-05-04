import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        Team team = new Team("Red Lions");
        team.addPlayer("Alice", "Midfielder", 23);
        team.addPlayer("Bob", "Striker", 27);
        team.addPlayer("Carla", "Defender", 21);

        System.out.println("Team name: " + team.getName());
        System.out.println("Players in the team:");
        for (Player player : team.getPlayers()) {
            System.out.println("- " + player.getName()
                    + " (" + player.getPosition() + ", age " + player.getAge() + ")");
        }

        String searchName = "Bob";
        Player found = team.findPlayerByName(searchName);
        if (found != null) {
            System.out.println("\nFound player: " + found.getName()
                    + " - " + found.getPosition() + " - age " + found.getAge());
        } else {
            System.out.println("\nPlayer " + searchName + " not found.");
        }

        double averageAge = calculateAverageAge(team);
        System.out.printf("\nAverage age of all players: %.2f\n", averageAge);

        System.out.println("\nUpdating age of Alice to 24.");
        Player alice = team.findPlayerByName("Alice");
        if (alice != null) {
            alice.setAge(24);
        }
        System.out.printf("New average age: %.2f\n", calculateAverageAge(team));
    }

    private static double calculateAverageAge(Team team) {
        Set<Player> players = team.getPlayers();
        if (players.isEmpty()) {
            return 0.0;
        }

        int sumAge = 0;
        for (Player player : players) {
            sumAge += player.getAge();
        }
        return (double) sumAge / players.size();
    }
}

class Team {
    private final String name;
    private final Set<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new HashSet<>();
    }

    public Player findPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(String name, String position, int age) {
        players.add(new Player(name, position, age));
    }

    public Set<Player> getPlayers() {
        return new HashSet<>(players);
    }

    public String getName() {
        return name;
    }
}

class Player {
    private final String name;
    private final String position;
    private int age;

    public Player(String name, String position, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
