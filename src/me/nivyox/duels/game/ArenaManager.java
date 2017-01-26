package me.nivyox.duels.game;

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
        for(Arena arena : arenas) {
            if(arena.getState() == WorldState.AVAILABLE) {
                Arena nextArena = new Arena(game);
                nextArena.setState(WorldState.NOT_AVAILABLE);
                return nextArena;
            }
        }
        return null;
    }

    public static void loadArenas() {

    }

}
