package me.nivyox.duels.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Niek on 1/27/2017.
 */
public class DefaultValues {

    public static Location lobbySpawnLocation = Bukkit.getWorlds().get(0).getHighestBlockAt(0,0).getLocation();
}
