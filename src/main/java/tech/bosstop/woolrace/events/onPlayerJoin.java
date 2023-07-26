package tech.bosstop.woolrace.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import tech.bosstop.common.structures.core.WRPlayer;

public class onPlayerJoin extends WREvent {
    
    public onPlayerJoin() {
        super();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.instance.getPlayerManager().addPlayer(new WRPlayer(player.getUniqueId()));
    }
}
