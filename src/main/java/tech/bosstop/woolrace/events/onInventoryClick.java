package tech.bosstop.woolrace.events;

import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import tech.bosstop.common.structures.core.WRGameState;
import tech.bosstop.common.structures.core.WRPlayer;
import tech.bosstop.common.structures.gui.GamesGUI;
import tech.bosstop.common.structures.gui.KitsGUI;

public class onInventoryClick extends WREvent {
    
    public onInventoryClick() {
        super();
    }

    private void clickGamesGUI(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
    
        String gameName = Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName();

        this.instance.getGameManager().getGames().forEach(game -> {
            if(game.isPlayerIn(player.getUniqueId())) {
                this.instance.getChat().send(player, "&cYou are already in a Game!");
                player.closeInventory();
                return;
            }
        });

        if(this.instance.getGameManager().gameExists(gameName)) {
            WRPlayer wrPlayer = new WRPlayer(player.getUniqueId());
            wrPlayer.setPreviousLocation(player.getLocation());
            this.instance.getGameManager().getGame(gameName).addPlayer(wrPlayer);
            player.closeInventory();
            this.instance.getChat().send(player, "&aYou entered a Game!");
        } else {
            player.closeInventory();
            this.instance.getChat().send(player, "&cGame does not exist!");
        }

    }

    private void clickKitsGUI(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        String kitName = Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName();

        this.instance.getGameManager().getGames().forEach(game -> {
            if(game.isPlayerIn(player.getUniqueId())) {
                if(game.getState() != WRGameState.TRANSITION) {
                    player.closeInventory();
                    this.instance.getChat().send(player, "&cYou cannot change your kit right now!"); 
                    return;
                }

                if(this.instance.getKitManager().kitExists(kitName)) {
                    game.getPlayer(player.getUniqueId()).setKit(this.instance.getKitManager().getKit(kitName));
                    player.closeInventory();
                    this.instance.getChat().send(player, "&aYou changed your kit! &7(" + kitName + ")");
                    return;
                } else {
                    player.closeInventory();
                    this.instance.getChat().send(player, "&cKit does not exist!");
                    return;
                }

            } else {
                player.closeInventory();
                this.instance.getChat().send(player, "&cYou are not in a Game!");
                return;
            }
        });
        
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }

        if(event.getClickedInventory().getHolder() instanceof GamesGUI) {
            event.setCancelled(true);
            this.clickGamesGUI(event);
        }

        if(event.getClickedInventory().getHolder() instanceof KitsGUI) {
            event.setCancelled(true);
            this.clickKitsGUI(event);
        }
        
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if(event.getInventory() instanceof GamesGUI) {
            event.setCancelled(true);
        }

        if(event.getInventory() instanceof KitsGUI) {
            event.setCancelled(true);
        }
    }
}
