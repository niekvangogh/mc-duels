package me.nivyox.duels.game;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Niek on 24-1-2017.
 */
public enum GameType {
    VANILLA, ENCHANTS, POTPVP, UHC;

    public ArrayList<ItemStack> getInventory() {
        ArrayList<ItemStack> items = new ArrayList<>();

        return items;
    }

}