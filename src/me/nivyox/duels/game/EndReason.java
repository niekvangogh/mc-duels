package me.nivyox.duels.game;

import org.bukkit.ChatColor;

/**
 * Created by Niek on 27-1-2017.
 */
public enum EndReason {
    OPPONENT_LEFT("Game ended because your opponent left the game!"),
    OPPONENT_KILLED("Game ended because you won the game!"),
    PLAYER_DIED("Game ended because player randomly died!");

    private String description;


    EndReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return ChatColor.RED + description;
    }
}
