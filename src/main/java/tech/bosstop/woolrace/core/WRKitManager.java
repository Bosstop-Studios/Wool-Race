package tech.bosstop.woolrace.core;

import java.util.HashMap;

import tech.bosstop.common.structures.core.WRKit;

public class WRKitManager {
    
    private HashMap <String, WRKit> kits = new HashMap<String, WRKit>();

    public boolean kitExists(String name) {
        return this.kits.containsKey(name);
    }

    public boolean createKit(String name, WRKit kit) {
        if(kitExists(name)) return false;
        this.kits.put(name, kit);
        return true;
    }

    public void updateKit(String name, WRKit kit) {
        if(!kitExists(name)) return;
        this.kits.replace(name, kit);
    }

    public void deleteKit(String name) {
        if(!kitExists(name)) return;
        this.kits.remove(name);
    }

    public WRKit getKit(String name) {
        if(!kitExists(name)) return null;
        return this.kits.get(name);
    }

    public HashMap<String, WRKit> getKits() {
        return this.kits;
    }
}
