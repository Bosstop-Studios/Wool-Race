package tech.bosstop.woolrace.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import tech.bosstop.woolrace.WoolRace;

public abstract class WREvent implements Listener {
    
    protected WoolRace instance;

    public WREvent() {
        super();
        this.instance = WoolRace.getInstance();
        Bukkit.getPluginManager().registerEvents(this, instance);
    }
}
