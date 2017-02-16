package me.nivyox.duels.commands;

import me.nivyox.duels.Main;
import me.nivyox.duels.utils.ChatMessages;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Niek on 16-2-2017.
 */
public class CommandGameSpawnpoint implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(ChatMessages.saveposition_usage);
                } else {
                    if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("1")) {
                        Location location = player.getLocation();
                        List<Location> locations = (List<Location>) Main.getInstance().getConfig().getList("game.arenas.worlds." + location.getWorld().getName() + ".spawnlocations");
                        try {
                            locations.set(Integer.parseInt(args[0] + 1), location);
                            Main.getInstance().getConfig().set("game.arenas.worlds." + location.getWorld().getName() + ".spawnlocations", locations);
                            Main.getInstance().saveConfig();
                        } catch (NumberFormatException e) {
                            sender.sendMessage(ChatMessages.saveposition_dont_use_letters);
                        }
                    } else {
                        sender.sendMessage(ChatMessages.saveposition_use_1_or_2);
                    }
                }
            }
        }
        return false;
    }
}
