public class TeamClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int    port = 7896;

        TeamProxy team1 = new TeamProxy(1, host, port);
        TeamProxy team2 = new TeamProxy(2, host, port);

        // Add players — name, position, age
        System.out.println("Adding Players");
        System.out.println(team1.addPlayer("An",   "Goalkeeper", 37));
        System.out.println(team1.addPlayer("Binh",  "Midfielder", 34));
        System.out.println(team1.addPlayer("Cuong",    "Striker",    30));
        System.out.println(team2.addPlayer("Duong",    "Midfielder", 35));
        System.out.println(team2.addPlayer("Giang", "Striker",    23));

        // Search player
        System.out.println("\nSearch Player");
        System.out.println(team1.searchPlayer("An"));
        System.out.println(team1.searchPlayer("Giang")); // NOT_FOUND

        // Get all players
        System.out.println("\nAll Players");
        System.out.println(team1.getPlayers());
        System.out.println(team2.getPlayers());

        // Calculate average age of team1
        System.out.println("\nAverage Age of " + team1.getName());
        String playersStr = team1.getPlayers();
        if (!playersStr.equals("empty")) {
            String[] playerList = playersStr.split(";");
            int totalAge = 0;
            for (String playerData : playerList) {
                String[] fields = playerData.split(",");
                totalAge += Integer.parseInt(fields[2]); // age is fields[2]
            }
            double avgAge = (double) totalAge / playerList.length;
            System.out.println("Average age: " + avgAge);
        }
    }
}