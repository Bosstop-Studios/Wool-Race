package ml.bosstop.woolwars.currency;

import ml.bosstop.woolwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class currencyManager implements Listener {
    private static Main plugin;
    private static HashMap<UUID, Integer> coins = new HashMap<UUID, Integer>();

    public currencyManager(Main plugin) {
        currencyManager.plugin = plugin;
        Bukkit.getPluginManager().registerEvents( this, plugin);
    }

    public static void enable() {

    }

    public void addCurrencyToPlayer(Player p, int amount) {

        coins.put(p.getUniqueId(), coins.get(p.getUniqueId()) + amount);

        plugin.chat.console("HOi");

        return;

    }

    public void removeCurrencyFromPlayer(Player p, int amount) {

        coins.put(p.getUniqueId(), coins.get(p.getUniqueId()) - amount);
        return;

    }

    public void setPlayerCurrency(Player p, int amount) {

        coins.put(p.getUniqueId(), amount);
        return;

    }

    public int getPlayerCurrency(Player p){

        return coins.get(p.getUniqueId());
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        // GET VALUES FROM DATABASE AND SET

        coins.put(p.getUniqueId(), 0);

    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        // GET VALUE FROM HASHMAP AND SAVE TO DATABASE

        int silvers = coins.get(p.getUniqueId());

    }

    public static void disable() {

        for (Map.Entry s : coins.entrySet()) {

        }


    }

}
