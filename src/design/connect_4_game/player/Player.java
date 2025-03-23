package design.connect_4_game.player;

public class Player {
    private String name;
    private char disc;

    public Player(String name, char disc) {
        this.name = name;
        this.disc = disc;
    }

    public String getName() {
        return name;
    }

    public char getDisc() {
        return disc;
    }
}

