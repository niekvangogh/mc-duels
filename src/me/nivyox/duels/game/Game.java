package me.nivyox.duels.game;

import me.nivyox.duels.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Niek on 24-1-2017.
 */
public class Game {

    private final Arena arena;
    private GameType type;
    private ArrayList<Player> players;
    private GameState state;
    private GameTimer gameTimer;
    private GameScoreboardManager scoreboardManager;

    public Game(GameType type, ArrayList<Player> players) {
        this.type = type;
        this.players = players;
        this.state = GameState.COUNTDOWN;
        this.arena = ArenaManager.findArena(this);
        this.gameTimer = new GameTimer(this);
        this.scoreboardManager = new GameScoreboardManager(this);

        this.startGame();
    }

    public GameType getType() {
        return this.type;
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

    public GameTimer getGameTimer() {
        return this.gameTimer;
    }

    public void startGame() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), gameTimer, 20, 20);
        arena.teleportPlayers();
        for (Player player : players) {
            player.getInventory().clear();
            type.giveGameInventory(player);
        }
    }

    public void broadcast(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    public GameScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    public void endGame(EndReason opponentLeft) {
        GameManager.removeGame(this);
        for (Player player : players) {
            player.sendMessage(ChatColor.RED + "Game ended! Reason: " + opponentLeft.name());
        }
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }
}
