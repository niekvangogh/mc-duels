package me.nivyox.duels.listeners;

import me.nivyox.duels.game.*;
import me.nivyox.duels.utils.DefaultValues;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Niek on 27-1-2017.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityByEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                if (GameManager.getGame(player) == null) {
                    if (damager.getGameMode() != GameMode.CREATIVE) {
                        event.setCancelled(true);
                    }
                } else {
                    Game game = GameManager.getGame(player);
                    if (game.getState() != GameState.GAME) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(DefaultValues.lobbyworld.getSpawnLocation());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (GameManager.isIngame(player)) {
            Game game = GameManager.getGame(player);
            game.endGame(EndReason.OPPONENT_LEFT);
            game.removePlayer(player);
        }
    }

    @EventHandler
    public void onHealthRegen(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (GameManager.isIngame(player)) {
                Game game = GameManager.getGame(player);
                if (game.getType() == GameType.UHC) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (GameManager.getGame(player) != null) {
            player.setHealth(20L);
            player.setGameMode(GameMode.SPECTATOR);
            Game game = GameManager.getGame(player);
            event.setDeathMessage(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "%%KILLER%% has won the game!".replace("%%KILLER%%", event.getEntity().getKiller().getDisplayName()));
            event.getDrops().clear();
            game.setState(GameState.END);
        } else {
            event.setDeathMessage(null);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);

    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (event.getTo().getWorld().getName() != event.getFrom().getWorld().getName()) {
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().clear();
        }
    }
}