package me.nivyox.duels;

import me.nivyox.duels.commands.CommandGame;
import me.nivyox.duels.commands.CommandSaveInventory;
import me.nivyox.duels.commands.CommandSpectate;
import me.nivyox.duels.game.ArenaManager;
import me.nivyox.duels.listeners.PlayerListener;
import me.nivyox.duels.utils.DefaultValues;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
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

        getCommand("spectate").setExecutor(new CommandSpectate());
        getCommand("spectate").setTabCompleter(new CommandSpectate());

        getCommand("game").setExecutor(new CommandGame());
        getCommand("game").setTabCompleter(new CommandGame());

        getCommand("saveinventory").setExecutor(new CommandSaveInventory());

    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(DefaultValues.lobbyworld.getSpawnLocation());
            player.setGameMode(GameMode.ADVENTURE);
        }
    }
}