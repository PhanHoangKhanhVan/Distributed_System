package test;

import java.io IOExeption;
import java.net.ServerSocket;
import java.net.Socket;

public class main {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(port: 6666);
            Socket socket = serverSocket.accept();
            try(DataOutputStream ouputstream = new DataOutputStream(socket.getOutputStream())){
                outputStream.flush();
                try(DataInputStream inputStream = new DataInputStream(socket.getInputStream())){

                }
            }

        }
    }
}
