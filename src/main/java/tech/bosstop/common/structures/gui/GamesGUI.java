package tech.bosstop.common.structures.gui;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import tech.bosstop.common.structures.core.WRGame;

public class GamesGUI implements InventoryHolder {

    private final Inventory inv;

    private final List<WRGame> games;

    public GamesGUI(List<WRGame> games) {
        this.inv = Bukkit.createInventory(this, 9, "Games"); //54 max size\
        this.games = games;
        init();
    }

    private void init() {
        this.games.forEach((game) -> {
            ItemStack item = createItem(game.getName(), Material.PAPER, Collections.singletonList("§c" + game.getState().name()));
            inv.addItem(item);
        });
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inv;
    }
    
}
