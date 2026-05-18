// TeamClient.java
public class TeamClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int    port = 7896;

        // Tạo proxy — không cần biết Team thực sự nằm ở đâu
        TeamProxy team1 = new TeamProxy(1, host, port);
        TeamProxy team2 = new TeamProxy(2, host, port);

        // --- Test các method ---

        System.out.println("=== Team Names ===");
        System.out.println(team1.getName());
        System.out.println(team2.getName());

        System.out.println("\n=== Adding Players ===");
        System.out.println(team1.addPlayer("Neuer"));
        System.out.println(team1.addPlayer("Müller"));
        System.out.println(team2.addPlayer("Reus"));
        System.out.println(team2.addPlayer("Haaland"));

        System.out.println("\n=== Player Lists ===");
        System.out.println(team1.getPlayers());
        System.out.println(team2.getPlayers());

        System.out.println("\n=== Remove Player ===");
        System.out.println(team1.removePlayer("Müller"));
        System.out.println(team1.removePlayer("Robben")); // không tồn tại

        System.out.println("\n=== Final Lists ===");
        System.out.println(team1.getPlayers());
        System.out.println(team2.getPlayers());
    }
}