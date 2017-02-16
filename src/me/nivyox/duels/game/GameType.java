package me.nivyox.duels.game;

import me.nivyox.duels.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Niek on 24-1-2017.
 */
public enum GameType {
    VANILLA("Vanilla"), ENCHANTS("Enchants"), POTPVP("PotPVP"), UHC("UHC");

    private final String type;

    GameType(String type) {
        this.type = type;
    }

    public void giveGameInventory(Player player) {
        List<ItemStack> items = (List<ItemStack>) Main.getInstance().getConfig().getList("modes." + type.toLowerCase() + ".inventory.items");
        ItemStack[] itemstacks = new ItemStack[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemstacks[i] = items.get(i);
        }
        items = (List<ItemStack>) Main.getInstance().getConfig().getList("modes." + type.toLowerCase() + ".inventory.armor");
        ItemStack[] armorstacks = new ItemStack[items.size()];
        for (int i = 0; i < armorstacks.length; i++) {
            armorstacks[i] = items.get(i);
        }
        player.getInventory().setContents(itemstacks);
        player.getInventory().setArmorContents(armorstacks);
        player.updateInventory();
    }

    public String getCoolName() {
        return type;
    }
}