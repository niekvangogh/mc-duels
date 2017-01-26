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
        for (Player player : game.getPlayers()) {
            player.setScoreboard(getScoreboard());
        }
    }

    public Scoreboard getScoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("mainScoreboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Dueller");

        ArrayList<String> scores = new ArrayList<>();
        switch (game.getState()) {
            case COUNTDOWN:
                scores.add(ChatColor.GREEN + "Time: " + ChatColor.WHITE + 0);
                break;
            case GAME:
                scores.add(ChatColor.GREEN + "Time: " + ChatColor.WHITE + game.getGameTimer().getTime());
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