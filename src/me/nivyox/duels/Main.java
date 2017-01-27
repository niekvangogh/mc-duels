package me.nivyox.duels;

import me.nivyox.duels.commands.CommandGame;
import me.nivyox.duels.game.ArenaManager;
import me.nivyox.duels.listeners.PlayerListener;
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
        ArenaManager.loadArenas();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("game").setExecutor(new CommandGame());
        getCommand("game").setTabCompleter(new CommandGame());
    }

    @Override
    public void onDisable() {

    }
}
