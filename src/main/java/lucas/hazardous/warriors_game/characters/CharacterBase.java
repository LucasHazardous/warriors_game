package lucas.hazardous.warriors_game.characters;

public interface CharacterBase {
    void attack(CharacterCharacter p);
    void restoreHealth(int amount);
    void loseHealth(int amount);
    void restoreMana(int amount);
    void loseMana(int amount);
    void levelUp();
    void info();
}
