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

import tech.bosstop.common.structures.core.WRKit;

public class KitsGUI implements InventoryHolder {

    private final Inventory inv;

    private final List<WRKit> kits;

    public KitsGUI(List<WRKit> kits) {
        this.inv = Bukkit.createInventory(this, 9, "Kits"); //54 max size\
        this.kits = kits;
        init();
    }

    private void init() {
        this.kits.forEach((kit) -> {
            ItemStack item = createItem(kit.getName(), Material.PAPER, Collections.singletonList("§c" + kit.getInventory().toString()));
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
