package me.nivyox.duels.game;

import me.nivyox.duels.Main;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.ArrayList;

/**
 * Created by Niek on 26-1-2017.
 */
public class ArenaManager {

    private static ArrayList<Arena> arenas = new ArrayList<Arena>();

    public static void addArena(Arena arena) {
        arenas.add(arena);
    }

    public static void removeArena(Arena arena) {
        arenas.remove(arena);
    }

    public static Arena findArena(Game game) {
        for (Arena arena : arenas) {
            if (arena.getState() == WorldState.AVAILABLE) {
                System.out.println("FOUND ARENA > " + arena.getWorld().getName());
                arena.setGame(game);
                arena.setState(WorldState.NOT_AVAILABLE);
                return arena;
            }
        }
        return null;
    }

    public static void loadArenas() {
        try {
            for (int i = 0; i < 5; i++) {
                if (Bukkit.getServer().createWorld(new WorldCreator("game_arena_" + i).environment(World.Environment.NORMAL)) != null) {
                    Main.getInstance().getLogger().info("Loaded world > " + i);
                }
            }

        } catch (NullPointerException e) {
            Main.getInstance().getLogger().info("Loaded all worlds!");
            e.printStackTrace();
        }

        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.PEACEFUL);
            if (world.getName().startsWith("game_arena_")) {
                Main.getInstance().getLogger().info("Found world > " + world.getName());
                addArena(new Arena(world));
            }
        }
    }
}
