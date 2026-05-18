import java.util.HashSet;
import java.util.Set;

public class Team {
    private String name;
        private Set<Player> players = new HashSet<>();

            public Team(String name) {
                    this.name = name;
                        }

                            // Search player by name — returns null if not found
                                public Player searchPlayer(String playerName) {
                                        for (Player p : players) {
                                                    if (p.getName().equals(playerName)) return p;
                                                            }
                                                                    return null;
                                                                        }

                                                                            // Add new player with 3 params — creates Player object internally
                                                                                public String addPlayer(String playerName, String position, int age) {
                                                                                        players.add(new Player(playerName, position, age));
                                                                                                return "OK: " + playerName + " added to " + name;
                                                                                                    }

                                                                                                        // Returns set of Player objects
                                                                                                            public Set<Player> getPlayers() {
                                                                                                                    return players;
                                                                                                                        }

                                                                                                                            public String getName() {
                                                                                                                                    return name;
                                                                                                                                        }
                                                                                                                                        }