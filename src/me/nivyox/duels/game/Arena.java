package me.nivyox.duels.game;

import org.bukkit.World;

/**
 * Created by Niek on 26-1-2017.
 */
public class Arena {
    private final World world;
    private final Game game;
    private WorldState state;

    public Arena(World world, Game game) {
        this.world = world;
        this.game = game;
    }

    public WorldState getState() {
        return this.state;
    }

    public void setState(WorldState state) {
        this.state = state;
    }

    public void teleportPlayers() {

    }

}
