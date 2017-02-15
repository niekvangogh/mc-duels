package me.nivyox.duels.game;

import me.nivyox.duels.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Niek on 24-1-2017.
 */
public class GameManager {
    private static ArrayList<Game> games = new ArrayList<>();
    private static ArrayList<Player> availablePlayers;

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static void addGame(Game game) {
        games.add(game);
        Main.getInstance().getLogger().info("STARTED GAME");
    }

    public static void removeGame(Game game) {
        games.remove(game);
    }

    public static Game getGame(Player player) {
        for (Game game : games) {
            if (game.getPlayers().contains(player)) {
                return game;
            }
        }
        return null;
    }

    public static Game getGame(String name) {
        Player player = Bukkit.getPlayer(name);
        for (Game game : games) {
            if (game.getPlayers().contains(player)) {
                return game;
            }
        }
        return null;
    }

    public static boolean isIngame(Player player) {
        return getGame(player) == null ? false : true;
    }

    public static ArrayList<Player> getAvailablePlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (getGame(player) == null) {
                players.add(player);
            }
        }
        return players;
    }

    public static ArrayList<Player> getIngamePlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Game game : games) {
            for (Player player : game.getPlayers()) {
                players.add(player);
            }
        }
        return players;
    }
}
