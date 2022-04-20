import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class mymyServer {
    private final ServerSocket serverSocket;

    public mymyServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            // Listen for connections (clients to connect) on port 1234.
            while (!serverSocket.isClosed()) {
                // Will be closed in the Client Handler.
                Socket socket = serverSocket.accept();
                System.out.println("Welcome" + "A  new client has connected!");
                mymyClientHandler clientHandler = new mymyClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                // The start method begins the execution of a thread.
                // When you call start() the run method is called.
                // The operating system schedules the threads.
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    // Close the server socket gracefully.
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run the program.
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        mymyServer server = new mymyServer(serverSocket);
        server.startServer();
    }
}   


