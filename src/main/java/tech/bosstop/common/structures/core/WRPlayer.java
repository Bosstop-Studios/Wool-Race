package tech.bosstop.common.structures.core;

import java.util.UUID;

import org.bukkit.Location;

public class WRPlayer {

    private UUID uuid;

    private WRPlayerState state = WRPlayerState.LOBBY;

    private WRKit kit = null;

    private int kills = 0;

    private int score = 0;

    private Location previousLocation;

    public WRPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public WRPlayerState getState() {
        return state;
    }
    
    public WRKit getKit() {
        return kit;
    }

    public int getKills() {
        return kills;
    }

    public int getScore() {
        return score;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public void setState(WRPlayerState state) {
        this.state = state;
    }

    public void setKit(WRKit kit) {
        this.kit = kit;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void addKill() {
        this.kills++;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }

    public void deletePreviousLocation() {
        this.previousLocation = null;
    }
    
}
