package lucas.hazardous.warriors_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerClient {
    private Socket mainSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public PlayerClient(Socket socket) {
        try {
            mainSocket = socket;

            socketOut =
                    new PrintWriter(mainSocket.getOutputStream(), true);

            socketIn =
                    new BufferedReader(
                            new InputStreamReader(mainSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerClient(String hostName, int portNumber) {
        try {
            mainSocket = new Socket(hostName, portNumber);

            socketOut =
                    new PrintWriter(mainSocket.getOutputStream(), true);

            socketIn =
                    new BufferedReader(
                            new InputStreamReader(mainSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readData() throws IOException {
        return socketIn.readLine();
    }

    public void close() throws IOException {
        socketIn.close();
        socketOut.close();
        mainSocket.close();
    }

    public void sendData(String data) {
        socketOut.println(data);
    }
}
