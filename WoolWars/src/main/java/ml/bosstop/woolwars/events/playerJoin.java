package ml.bosstop.woolwars.events;

import ml.bosstop.woolwars.Main;
import ml.bosstop.woolwars.util.Storage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class playerJoin implements Listener {

    private Main plugin;

    public playerJoin(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        if(Storage.findNoteExist(p.getUniqueId())) {

            // DO SOMETHING

        } else {
            Storage.createNote(p.getUniqueId());
        }

        plugin.chat.console(p.getUniqueId().toString() + " " + p.getName());
    }
}
