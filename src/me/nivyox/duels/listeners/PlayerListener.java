package me.nivyox.duels.listeners;

import me.nivyox.duels.game.EndReason;
import me.nivyox.duels.game.Game;
import me.nivyox.duels.game.GameManager;
import me.nivyox.duels.game.GameType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Niek on 27-1-2017.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(Bukkit.getWorld("lobby").getSpawnLocation());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(GameManager.isIngame(player)) {
            Game game = GameManager.getGame(player);
            game.endGame(EndReason.OPPONENT_LEFT);
            game.removePlayer(player);
        }
    }

    @EventHandler
    public void onHealthRegen(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(GameManager.isIngame(player)) {
                Game game = GameManager.getGame(player);
                if(game.getType() == GameType.UHC) {
                    event.setCancelled(true);
                }
            }
        }
    }

}