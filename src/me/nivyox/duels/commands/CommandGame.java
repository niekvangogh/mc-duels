package me.nivyox.duels.commands;

import me.nivyox.duels.game.Game;
import me.nivyox.duels.game.GameManager;
import me.nivyox.duels.game.GameType;
import me.nivyox.duels.utils.ChatMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 24-1-2017.
 */
public class CommandGame implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.isOp()) {
                if (args.length <= 1) {
                    sender.sendMessage(ChatMessages.game_no_args);
                } else {
                    if (!GameManager.isIngame(Bukkit.getPlayer(args[1]))) {
                        GameType gameType;
                        try {
                            gameType = GameType.valueOf(args[0].toUpperCase());
                        } catch(IllegalArgumentException e) {
                            sender.sendMessage(ChatMessages.no_gametype_like_that);
                            return false;
                        }
                        sender.sendMessage(ChatMessages.game_created_game);
                        ArrayList<Player> players = new ArrayList<>();
                        players.add((Player) sender);
                        players.add(Bukkit.getPlayer(args[1]));
                        Game game = new Game(gameType, players);
                        GameManager.addGame(game);
                    } else {
                        sender.sendMessage(ChatMessages.player_is_ingame);
                    }
                }
            } else {
                sender.sendMessage(ChatMessages.no_permissions);
            }
        } else {
            sender.sendMessage(ChatMessages.no_player);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String lable, String[] args) {
        List<String> returning = new ArrayList<>();
        if (sender instanceof Player) {
            returning.clear();
            if (args.length == 1) {
                for (GameType type : GameType.values()) {
                    returning.add(type.getCoolName());
                }
            } else if (args.length == 2) {
                ArrayList<Player> players = GameManager.getAvailablePlayers();
                for (Player availableplayer : players) {
                    returning.add(availableplayer.getName());
                }
                returning.remove(sender.getName());
            }
        }
        return returning;
    }
}
