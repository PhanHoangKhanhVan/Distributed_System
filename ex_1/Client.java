package ex_1;
import java.util.Set;

public class Client {
    public static void main(String[] args) {
        TeamProxy proxy = new TeamProxy("T1");

        // Thêm cầu thủ qua mạng
        proxy.addPlayer("An", "Striker", 20);
        proxy.addPlayer("Binh", "Midfielder", 22);

        // Lấy danh sách về và tính trung bình tuổi
        Set<Player> list = proxy.getPlayers();
        double sum = 0;
        for (Player p : list) {
            sum += p.getAge();
        }
        
        if (!list.isEmpty()) {
            System.out.println("Average Age of " + proxy.getName() + ": " + (sum / list.size()));
        }
    }
}