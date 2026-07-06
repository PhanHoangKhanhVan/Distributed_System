import java.util.HashSet;
import java.util.Set;

// Team have PlayerS
public class Team{
    private String teamName;
    private Set<Player> Players;

    //Constructor team
    public Team(String name){
        this.teamName = name;
        //tao ra 1 Team trong ban dau
        this.Players = new HashSet<>();
    }

    // lay ten doi bong
    public String getTeamName(){
        return this.teamName;
    }

    //them cau thu moi
    //user chi can dua info, Team su tu dong tao ra Player roi bo vao danh sach
    public void addPlayer(String name, String position, double age){
        Player newPlayer = new Player(name, position, age); //tu tao vat pham
        this.Players.add(newPlayer);    //bo vao tap hop
    }

    // tim Player theo ten
    public Player searchPlayer(String targetName){
        //xet qua tung Player trong Team
        for (Player currentPlayer : this.Players){
            if (currentPlayer.getName().equals(targetName)){
                return currentPlayer; // thay roi thi tra ve nguyen Player do
            }
        }
        return null; //neu khong thay ket qua
    }

    // tra ve toan bo danh sach Team
    public Set<Player> getAllItems(){
        return this.Players;
    }
}