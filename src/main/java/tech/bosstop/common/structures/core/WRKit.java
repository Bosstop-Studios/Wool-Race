package tech.bosstop.common.structures.core;

import org.bukkit.inventory.Inventory;

public class WRKit {
    
    private String name;

    private Inventory inventory;

    public WRKit(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

}
