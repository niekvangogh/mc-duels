package me.nivyox.duels.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Niek on 26-1-2017.
 */
public class GameScoreboardManager {
    private final Game game;


    public GameScoreboardManager(Game game) {
        this.game = game;
    }

    public void update() {
        for (Player player : game.getAllPlayers()) {
            player.setScoreboard(getScoreboard());
        }
    }

    public Scoreboard getScoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("mainScoreboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Dueller");

        ArrayList<String> scores = new ArrayList<>();
        switch (game.getState()) {
            case COUNTDOWN:
                scores.add(" ");
                scores.add(ChatColor.GREEN + "Time: " + ChatColor.WHITE + 0);
                scores.add("  ");
                scores.add(ChatColor.GREEN + "Instance: " + ChatColor.WHITE + game.getArena().getWorld().getName());
                scores.add("   ");
                scores.add(ChatColor.GREEN + "Game type: " + ChatColor.WHITE + game.getType().getCoolName());
                scores.add("    ");
                break;
            case GAME:
                scores.add(" ");
                scores.add(ChatColor.GREEN + "Time: " + ChatColor.WHITE + game.getGameTimer().getTime());
                scores.add("  ");
                scores.add(ChatColor.GREEN + "Instance: " + ChatColor.WHITE + game.getArena().getWorld().getName());
                scores.add("   ");
                scores.add(ChatColor.GREEN + "Game type: " + ChatColor.WHITE + game.getType().getCoolName());
                break;
            case END:
                break;
        }

        Collections.reverse(scores);
        for (int i = 0; i < scores.size(); i++) {
            obj.getScore(scores.get(i)).setScore(i);
        }
        return scoreboard;
    }
}