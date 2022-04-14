package lucas.hazardous.warriors_game.characters;

public interface CharacterBase {
    void attack(CharacterCharacter p);
    void loseHealth(int amount);
    void info();
}
