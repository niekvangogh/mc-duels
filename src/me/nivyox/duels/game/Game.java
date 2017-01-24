package me.nivyox.duels.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Niek on 24-1-2017.
 */
public class Game {

    private GameType type;
    private ArrayList<Player> players;
    private GameState state;

    public Game(GameType type, ArrayList<Player> players) {
        this.type = type;
        this.players = players;
        this.state = GameState.COUNTDOWN;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void startGame() {

    }

    public void endGame() {

        GameManager.removeGame(this);
    }

}
