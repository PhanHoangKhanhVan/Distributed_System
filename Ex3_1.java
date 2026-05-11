import java.util.HashSet;
import java.util.Set;

class Team {  //class for Team
    private String name;    //team name
    private Set<Player> players;    //set of players

    public Team(String name) {
        this.name = name;
        this.players = new HashSet<>();
    }
    public Player findPlayer(String name) { //search Player
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
    public void addPlayer(String name, String position, int age) { // add Player
        players.add(new Player(name, position, age));
    }
    public Set<Player> getPlayers() {   //return set of Players
        return players;
    }
    public String getName() {   //return team name
        return name;
    }
}

class Player {
    private String name;
    private String position;
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
}


public class Ex3_1 {
    public static void main(String[] args) {
        Team team = new Team("Team A");
        team.addPlayer("Anh", "Midfielder", 24);
        team.addPlayer("Bao", "Striker", 27);
        System.out.println(team.getName());
        for (Player player : team.getPlayers()) {
            System.out.println(player.getName() + ", " + player.getPosition() + ", " + player.getAge());
        }
        Player found = team.findPlayer("Binh");
        if (found != null) {
            System.out.println("Found player: " + found.getName());
        } else {
            System.out.println("Player not found");
        }
        int totalAge = 0;
        for (Player player : team.getPlayers()) {
            totalAge = player.getAge() + totalAge;
        }
        double averageAge = (double) totalAge / team.getPlayers().size();
        System.out.printf("Average age: %.2f", averageAge);
        
    }
}
