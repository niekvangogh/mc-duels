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
        for(Game game : games) {
            if(game.getPlayers().contains(player)) {
                return game;
            }
        }
        return null;
    }

    public static Game getGame(String name) {
        Player player = Bukkit.getPlayer(name);
        for(Game game : games) {
            if(game.getPlayers().contains(player)) {
                return game;
            }
        }
        return null;
    }
}
