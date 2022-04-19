package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static lucas.hazardous.warriors_game.Constants.TIMER_DELAY;

public class MenuPanel extends JPanel implements ActionListener {
    private MainWindow mainWindow;
    private Timer timer;
    private JTextField nicknameField;

    public MenuPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.timer = new Timer(TIMER_DELAY, this);
        setFocusable(true);

        addPlayButton();

        addNicknameField();
    }

    private void addPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            timer.start();

            String playerNickname = nicknameField.getText();
            nicknameField.setEditable(false);

            mainWindow.setLocalPlayerNickname(playerNickname);
            mainWindow.sendInitializationData(playerNickname);

            mainWindow.receiveOnlineData();
        });
        add(playButton);
    }

    private void addNicknameField() {
        nicknameField = new JTextField("", 20);
        nicknameField.setEditable(true);
        add(nicknameField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startGameIfOpponentReady();
    }

    private void startGameIfOpponentReady() {
        if(OnlineDataTransfer.nickname != "") {
            timer.stop();
            mainWindow.startOnlineGame();
        }
    }
}
