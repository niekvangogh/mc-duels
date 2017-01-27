package me.nivyox.duels.game;

import me.nivyox.duels.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Niek on 26-1-2017.
 */
public class Arena {
    private final World world;
    private Game game;
    private WorldState state;
    private ArrayList<Location> spawnLocations;

    public Arena(World world) {
        this.state = WorldState.AVAILABLE;
        this.world = world;
        this.spawnLocations = (ArrayList<Location>) Main.getInstance().getConfig().getList("game.arenas.worlds." + world.getName() + ".spawnlocations");
    }

    public WorldState getState() {
        return this.state;
    }

    public void setState(WorldState state) {
        this.state = state;
    }

    public void teleportPlayers() {
        ArrayList<Player> players = game.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            try {

                Location location = spawnLocations.get(i);
                location.setWorld(getWorld());
                players.get(i).teleport(location);
                System.out.println(players.get(i).getWorld().getName());
            } catch (NullPointerException e) {
                game.endGame(EndReason.NO_ARENA_SPAWNPOINTS);
            }
        }
    }

    public ArrayList<Location> getSpawnLocations() {
        return this.spawnLocations;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return this.world;
    }
}
