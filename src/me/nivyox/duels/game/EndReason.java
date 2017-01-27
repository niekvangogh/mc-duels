package me.nivyox.duels.game;

import org.bukkit.ChatColor;

/**
 * Created by Niek on 27-1-2017.
 */
public enum EndReason {
    OPPONENT_LEFT("Game ended because your opponent left the game!"), NO_ARENA_SPAWNPOINTS("Game ended because the arena you got assigned doesn't have any spawnpoints, please contact a staff member"), OPPONENT_KILLED("Game ended because you won the game!");

    private String description;


    EndReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return ChatColor.RED + description;
    }
}
