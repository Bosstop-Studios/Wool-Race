package tech.bosstop.woolrace.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import tech.bosstop.common.structures.core.WRGameState;

public class onPlayerMove extends WREvent {
    
    public onPlayerMove() {
        super();
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        this.instance.getGameManager().getGames().forEach(game -> {
            if(game.isPlayerIn(player.getUniqueId())) {
                if(game.getState() == WRGameState.TRANSITION) {
                    event.setCancelled(true);
                }
            }
        });

    }
}
