public class AccountProxy {
    private String objectId;
    private String host;
    private int port;

    public AccountProxy(String id, String host, int port) {
        this.objectId = id;
        this.host = host;
        this.port = port;
    }

    public double getBalance() throws Exception {
        return Double.parseDouble(sendRequest("getBalance;0"));
    }

    public void deposit(double amount) throws Exception {
        sendRequest("deposit;" + amount);
    }

    private String sendRequest(String command) throws Exception {
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        // Gửi theo format: ID_Doi_Tuong;Ten_Ham;Tham_So
        out.println(this.objectId + ";" + command);
        String response = in.readLine();
        
        socket.close();
        return response;
    }
}