//Main class to test the Team and Player classes.
//Demonstrates all required methods and calculates average age.
public class Main {

    public static void main(String[] args) {

        //Create two teams
        Team teamA = new Team("FC Bayern");
        Team teamB = new Team("Borussia Dortmund");

        //Add players to Team A
        teamA.addPlayer("Manuel Neuer",    "Goalkeeper", 38);
        teamA.addPlayer("Joshua Kimmich",  "Midfielder", 29);
        teamA.addPlayer("Leroy Sané",      "Winger",     28);
        teamA.addPlayer("Harry Kane",      "Striker",    30);
        teamA.addPlayer("Alphonso Davies", "Defender",   23);

        //Add players to Team B
        teamB.addPlayer("Gregor Kobel",   "Goalkeeper", 26);
        teamB.addPlayer("Marco Reus",     "Midfielder", 35);
        teamB.addPlayer("Donyell Malen",  "Winger",     25);
        teamB.addPlayer("Sébastien Haller","Striker",   30);

        //Print rosters
        System.out.println("=== " + teamA.getName() + " ===");
        for (Player p : teamA.getPlayers()) {
            System.out.println("  " + p);
        }

        System.out.println("\n=== " + teamB.getName() + " ===");
        for (Player p : teamB.getPlayers()) {
            System.out.println("  " + p);
        }

        //Average age
        System.out.println("\n-- Average Ages --");
        System.out.printf("%s: %.1f years%n",
                teamA.getName(), averageAge(teamA));
        System.out.printf("%s: %.1f years%n",
                teamB.getName(), averageAge(teamB));

        //Search for a player
        System.out.println("\n-- Player Search --");
        String searchName = "Harry Kane";
        Player found = teamA.findPlayer(searchName);
        if (found != null) {
            System.out.println("Found in " + teamA.getName() + ": " + found);
        } else {
            System.out.println(searchName + " not found.");
        }

        //Change a player's age
        System.out.println("\n-- Update Age --");
        Player kimmich = teamA.findPlayer("Joshua Kimmich");
        if (kimmich != null) {
            System.out.println("Before: " + kimmich);
            kimmich.setAge(kimmich.getAge() + 1);
            System.out.println("After:  " + kimmich);
        }

        //Return individual player details
        System.out.println("\n-- Player Details via Methods --");
        Player sane = teamA.findPlayer("Leroy Sané");
        if (sane != null) {
            System.out.println("Name:     " + sane.getName());
            System.out.println("Position: " + sane.getPosition());
            System.out.println("Age:      " + sane.getAge());
        }
    }

    // Calculates the average age of all players in a team.
    private static double averageAge(Team team) {
        if (team.getPlayers().isEmpty()) return 0;
        int total = 0;
        for (Player p : team.getPlayers()) {
            total += p.getAge();
        }
        return (double) total / team.getPlayers().size();
    }
}
