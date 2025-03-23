package design.connect_4_game.player;

public class PlayerFactory {
    public static Player createPlayer(String name, char disc) {
        return new Player(name, disc);
    }
}
