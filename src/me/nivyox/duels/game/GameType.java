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
        List<ItemStack> items = (List<ItemStack>) Main.getInstance().getConfig().getList("modes." + this.type.toLowerCase() + ".items");

        for (ItemStack item : items) {
            player.getInventory().addItem(item);
        }
    }


}