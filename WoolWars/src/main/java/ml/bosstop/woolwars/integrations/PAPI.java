package ml.bosstop.woolwars.integrations;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import ml.bosstop.woolwars.Main;
import org.bukkit.entity.Player;

public class PAPI extends PlaceholderExpansion {
    private Main plugin;

    public PAPI(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "core";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        if (p == null) {
            return "";
        }

        if (identifier.equals("blocks")) {
            return "Hoi";
        }


        return null;
    }

}