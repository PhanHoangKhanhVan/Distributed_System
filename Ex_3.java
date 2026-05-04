import java.util.Hashset;
import java.util.Set;

class teams{
    private String name;
    private Set<Player> players;

    public teams(String name){
        this.name = name;
        this.players = new HashSet<>();
    }

    public Player findPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(String name, String position, String age){
        players.add(new Player(name, position, age));
    }
}

class player{
    private String name;
    private String position;
    private String age;
    
    public player(String name, String position, String age){
        this.name = name;
        this.position = position;
        this.age = age;
    }
    public void changeAge(int age){
        this.age = age;
    }
}

public class Ex_3 {
    public static void main(String[] args) {
        teams team1 = new teams("Team A");
        team1.addPlayer("John Doe", "Forward", "25");
        team1.addPlayer("Jane Smith", "Midfielder", "22");

    //     Player player = team1.findPlayer("John Doe");
    //     if (player != null) {
    //         System.out.println("Player found: " + player.getName() + ", Position: " + player.getPosition() + ", Age: " + player.getAge());
    //         player.changeAge(26);
    //         System.out.println("Player's new age: " + player.getAge());
    //     } else {
    //         System.out.println("Player not found.");
    //     }
    }
}