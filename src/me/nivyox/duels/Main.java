package me.nivyox.duels;

import me.nivyox.duels.commands.CommandGame;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Niek on 15-1-2017.
 */
public class Main extends JavaPlugin {

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

    @Override
    public void onEnable() {
        getCommand("game").setExecutor(new CommandGame());
    }

    @Override
    public void onDisable() {

    }
}
