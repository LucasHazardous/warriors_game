package lucas.hazardous.warriors_game.gui;

import javax.swing.*;

public class MenuPanel extends JPanel {
    private MainWindow mainWindow;

    public MenuPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setFocusable(true);

        addPlayButton();
    }

    private void addPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> mainWindow.startOnlineGame());
        add(playButton);
    }
}
