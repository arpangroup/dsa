package design.connect_4_game.player;

import design.connect_4_game.game.GameObserver;

public class PlayerObserver implements GameObserver {
    private Player player;

    public PlayerObserver(Player player) {
        this.player = player;
    }

    @Override
    public void update(String message) {
        System.out.println(player.getName() + "--> " + player.getDisc());
    }
}
