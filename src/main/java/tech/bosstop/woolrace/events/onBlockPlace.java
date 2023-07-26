package tech.bosstop.woolrace.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class onBlockPlace extends WREvent {
    
    public onBlockPlace(){
        super();
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        this.instance.getGameManager().getGames().forEach(game -> {
            if(game.isPlayerIn(player.getUniqueId())) {
                if(!game.isInGrid(event.getBlock().getLocation())) {
                    event.setCancelled(true);
                    return;
                }
            }
        });
    }
}
