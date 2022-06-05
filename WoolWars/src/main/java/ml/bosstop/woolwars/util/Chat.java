package ml.bosstop.woolwars.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

import static ml.bosstop.woolwars.Main.config;

public class Chat {
    static Logger log = Bukkit.getServer().getLogger();
    private static String prefix = config.getString("prefix");
    private String Color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public void send(Player p, String input) {
        p.sendMessage(this.Color(prefix + input));
    }
    public void console(String input) {
        log.info(this.Color(prefix + " " + input));
    }
}
