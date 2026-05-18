public class TeamClient {
        public static void main(String[] args) throws Exception {
                String host = "localhost";
                        int    port = 7896;

                                TeamProxy team1 = new TeamProxy(1, host, port);
                                        TeamProxy team2 = new TeamProxy(2, host, port);

                                                // Add players — name, position, age
                                                        System.out.println("=== Adding Players ===");
                                                                System.out.println(team1.addPlayer("Neuer",   "Goalkeeper", 37));
                                                                        System.out.println(team1.addPlayer("Müller",  "Midfielder", 34));
                                                                                System.out.println(team1.addPlayer("Kane",    "Striker",    30));
                                                                                        System.out.println(team2.addPlayer("Reus",    "Midfielder", 35));
                                                                                                System.out.println(team2.addPlayer("Haaland", "Striker",    23));

                                                                                                        // Search player
                                                                                                                System.out.println("\n=== Search Player ===");
                                                                                                                        System.out.println(team1.searchPlayer("Kane"));
                                                                                                                                System.out.println(team1.searchPlayer("Robben")); // NOT_FOUND

                                                                                                                                        // Get all players
                                                                                                                                                System.out.println("\n=== All Players ===");
                                                                                                                                                        System.out.println(team1.getPlayers());
                                                                                                                                                                System.out.println(team2.getPlayers());

                                                                                                                                                                        // Calculate average age of team1
                                                                                                                                                                                System.out.println("\n=== Average Age of " + team1.getName() + " ===");
                                                                                                                                                                                        String playersStr = team1.getPlayers();
                                                                                                                                                                                                if (!playersStr.equals("EMPTY")) {
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
}