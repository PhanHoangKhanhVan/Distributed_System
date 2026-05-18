import java.io.*;
import java.net.*;

public class TeamProxy {
    private int teamId;
        private String serverHost;
            private int serverPort;

                public TeamProxy(int teamId, String serverHost, int serverPort) {
                        this.teamId     = teamId;
                                this.serverHost = serverHost;
                                        this.serverPort = serverPort;
                                            }

                                                private String sendRequest(String request) throws IOException {
                                                        Socket s         = new Socket(serverHost, serverPort);
                                                                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                                                                        DataInputStream  in  = new DataInputStream(s.getInputStream());
                                                                                out.writeUTF(request);
                                                                                        String response = in.readUTF();
                                                                                                s.close();
                                                                                                        return response;
                                                                                                            }

                                                                                                                // Returns "OK: ..." string
                                                                                                                    public String addPlayer(String name, String position, int age) throws IOException {
                                                                                                                            return sendRequest(teamId + "|addPlayer|" + name + "|" + position + "|" + age);
                                                                                                                                }

                                                                                                                                    // Returns player info as string, or "NOT_FOUND"
                                                                                                                                        public String searchPlayer(String name) throws IOException {
                                                                                                                                                return sendRequest(teamId + "|searchPlayer|" + name);
                                                                                                                                                    }

                                                                                                                                                        // Returns all players as string: "name,position,age;name,position,age;..."
                                                                                                                                                            public String getPlayers() throws IOException {
                                                                                                                                                                    return sendRequest(teamId + "|getPlayers");
                                                                                                                                                                        }

                                                                                                                                                                            public String getName() throws IOException {
                                                                                                                                                                                    return sendRequest(teamId + "|getName");
                                                                                                                                                                                        }
                                                                                                                                                                                        }