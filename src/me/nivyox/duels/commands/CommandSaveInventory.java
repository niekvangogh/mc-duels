package me.nivyox.duels.commands;

import me.nivyox.duels.Main;
import me.nivyox.duels.game.GameType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 15-2-2017.
 */
public class CommandSaveInventory implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<ItemStack> items = new ArrayList<>();
            List<ItemStack> armor = new ArrayList<>();
            for (ItemStack item : player.getInventory().getContents()) {
                items.add(item);
            }
            for (ItemStack armorpiece : player.getInventory().getArmorContents()) {
                armor.add(armorpiece);
            }
            Main.getInstance().getConfig().set("modes." + args[0].toLowerCase() + ".inventory.items", items);
            Main.getInstance().getConfig().set("modes." + args[0].toLowerCase() + ".inventory.armor", armor);
            Main.getInstance().saveConfig();
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        List<String> returning = new ArrayList<>();
        if (sender instanceof Player) {
            returning.clear();
            if (args.length == 1) {
                for (GameType type : GameType.values()) {
                    returning.add(type.getCoolName());
                }
            }
        }
        return returning;
    }
}
