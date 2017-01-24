package me.nivyox.duels.commands;

import me.nivyox.duels.game.Game;
import me.nivyox.duels.game.GameManager;
import me.nivyox.duels.game.GameType;
import me.nivyox.duels.utils.ChatMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Niek on 24-1-2017.
 */
public class CommandGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.isOp()) {
                if (args.length <= 1) {
                    sender.sendMessage(ChatMessages.game_no_args);
                } else {
                    GameType gameType = GameType.valueOf(args[0].toUpperCase());
                    sender.sendMessage(ChatMessages.game_created_game);
                    ArrayList<Player> players = new ArrayList<>();
                    players.add((Player) sender);
                    Game game = new Game(gameType, players);
                    GameManager.addGame(game);
                }
            }
        }
        return true;
    }
}
