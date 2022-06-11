package ml.bosstop.woolwars.util.storage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Note {
    private String id;
    private int coins ;
    public Note(UUID uuid) {
        this.id = uuid.toString();
        this.coins = 0;
    }

    public String getId() {
        return id;
    }

    public String getPlayer() {
        Player p = Bukkit.getPlayer(UUID.fromString(id));
        return p.getName();
    }

    public void setCoins(int amount) {
        this.coins = amount;
    }

    public int getCoins() {
        return coins;
    }

}
