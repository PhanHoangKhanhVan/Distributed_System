import java.io.*;
import java.net.*;

public class LibraryServer{
    public static void main(String[] args){
        int port 1234;

        //2 Library objects, chi ton tai o server
        Book[] books = {
            new Book("DS"),
            new Book("RTS")
        };

        System.out.println("LibraryServer started on port " = port);

        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while true {
                Socket clientSocket = listenSocket.accept();
                //Each request handle in different thread
                new RequestHandler(clientSocket, teams).start();
            }
        } catch (IOExption e) {
            System.out.println("Server error" e.getMessage());
        }
    }
}

//Thread su li tung request
class RequestHandler extends Thread {
    private Socket socket;
    private Book[] books;

    public RequestHandler(Socket socket, Team[] teams) {
        this.socket = socket;
        this.books = books;
    }

    public void run(){
        try{
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String request = in.readUTF();
            System.out.println("Received: " + request);

            String result = dispatch(request);
            out.writeUTF(result);

            socket.close();
        } catch (IOExeption e) {
            System.out.println("Handler error: " + e.getMessage());
        }
    }

    //Decode string va goi method tuong ung
    
}