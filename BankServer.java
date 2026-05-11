public class BankServer {
    private Map<String, Account> accounts = new HashMap<>();

    public void start() throws Exception {
        // Khởi tạo dữ liệu mẫu
        accounts.put("ACC01", new Account("ACC01", 1000));
        
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            try (Socket s = serverSocket.accept()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                String request = in.readLine(); // "ACC01;deposit;500"
                String[] parts = request.split(";");
                
                String id = parts[0];
                String method = parts[1];
                Account realObj = accounts.get(id);

                if (method.equals("getBalance")) {
                    out.println(realObj.getBalance());
                } else if (method.equals("deposit")) {
                    realObj.deposit(Double.parseDouble(parts[2]));
                    out.println("Success");
                }
            }
        }
    }
}