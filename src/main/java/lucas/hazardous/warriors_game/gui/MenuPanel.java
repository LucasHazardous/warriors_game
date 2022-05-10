package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

import static lucas.hazardous.warriors_game.Constants.*;

public class MenuPanel extends JPanel implements ActionListener {
    private final MainWindow mainWindow;
    private final Timer timer;
    private JTextField nicknameField;

    private JList<String> arenaListField;
    private final String[] arenaOptions = ARENA_LIST.keySet().toArray(new String[0]);
    private String selectedArena = arenaOptions[0];

    private JList<String> heroListField;
    private String selectedHero = HERO_LIST[0];

    public MenuPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.timer = new Timer(TIMER_DELAY, this);
        OnlineDataTransfer.loadNewPlayerClient();

        setFocusable(true);

        addPlayButton();

        addNicknameField();

        addMapSelectionFieldAndSetDefaultArena();

        addHeroSelectionFieldAndSetDefaultHero();
    }

    private void addPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            timer.start();

            mainWindow.setArenaChoice(selectedArena);
            mainWindow.setHeroChoice(selectedHero);

            mainWindow.generateLocalPlayer();

            String playerNickname = nicknameField.getText();
            nicknameField.setEditable(false);

            if(playerNickname.equals(""))
                playerNickname = String.valueOf(new Random().nextFloat());

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

    private void addMapSelectionFieldAndSetDefaultArena() {
        arenaListField = new JList<>(arenaOptions);
        arenaListField.addListSelectionListener(e -> selectedArena = arenaOptions[arenaListField.getSelectedIndex()]);
        add(arenaListField);
        arenaListField.setSelectedIndex(0);
    }

    private void addHeroSelectionFieldAndSetDefaultHero() {
        heroListField = new JList<>(HERO_LIST);
        heroListField.addListSelectionListener(e -> selectedHero = HERO_LIST[heroListField.getSelectedIndex()]);
        add(heroListField);
        heroListField.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startGameIfOpponentReady();
    }

    private void startGameIfOpponentReady() {
        if(!Objects.equals(OnlineDataTransfer.nickname, "")) {
            timer.stop();
            mainWindow.startOnlineGame();
        }
    }
}
