package me.nivyox.duels.game;

import me.nivyox.duels.Main;
import me.nivyox.duels.utils.DefaultValues;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

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
    private ArrayList<Player> spectators = new ArrayList<>();
    private BukkitTask bukkitTask;

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
        this.bukkitTask = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), gameTimer, 20, 20);
        arena.teleportPlayers();
        for (Player player : players) {
            player.setGameMode(GameMode.ADVENTURE);
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

    public void endGame(EndReason endReason) {
        getArena().setState(WorldState.AVAILABLE);
        bukkitTask.cancel();
        GameManager.removeGame(this);
        for (Player player : spectators) {
            player.teleport(DefaultValues.lobbyworld.getSpawnLocation());
            player.setGameMode(GameMode.ADVENTURE);
        }
        for (Player player : players) {
            player.teleport(DefaultValues.lobbyworld.getSpawnLocation());
            player.setGameMode(GameMode.ADVENTURE);
        }
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public Arena getArena() {
        return arena;
    }

    public void addSpectator(Player spectator, Player player) {
        if (player == null) {
            spectator.teleport(getPlayers().get(0).getLocation());
        } else {
            spectator.teleport(player);
        }
        this.spectators.add(spectator);
        spectator.setGameMode(GameMode.SPECTATOR);
    }

    public ArrayList<Player> getSpectator() {
        return spectators;
    }
}
