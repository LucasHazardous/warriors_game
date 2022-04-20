package lucas.hazardous.warriors_game.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerClient {
    private Socket mainSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

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

    public void sendGameData(int x, int y, int damage, int health) {
        socketOut.println(x + " " + y + " " + damage + " " + health);
    }

    public void sendEndGameData() {
        socketOut.println("\n");
    }

    public void sendInitializationData(String nickname, int health) {
        socketOut.println(nickname + " " + health);
    }

    public boolean isClosed() {
        return mainSocket.isClosed();
    }
}
