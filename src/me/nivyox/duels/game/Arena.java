package me.nivyox.duels.game;

import me.nivyox.duels.Main;
import me.nivyox.duels.utils.ChatMessages;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Niek on 26-1-2017.
 */
public class Arena {
    private final World world;
    private Game game;
    private WorldState state;
    private ArrayList<Location> spawnLocations;
    private HashMap<Player, Location> playerSpawnLocations = new HashMap<>();

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

            Location location;
            try {
                location = spawnLocations.get(i);
            } catch (NullPointerException e) {
                for (Player player : players) {
                    player.sendMessage(ChatMessages.no_coords_set.replace("%%INSTANCE%%", this.getWorld().getName()));
                }
                return;
            }
            location.setWorld(getWorld());
            players.get(i).teleport(location);
            playerSpawnLocations.put(players.get(i), location);
        }
    }

    public ArrayList<Location> getSpawnLocations() {
        return this.spawnLocations;
    }

    public HashMap<Player, Location> getPlayerSpawnLocations() {
        return this.playerSpawnLocations;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return this.world;
    }
}
