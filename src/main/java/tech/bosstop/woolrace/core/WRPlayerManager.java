package tech.bosstop.woolrace.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tech.bosstop.common.structures.core.WRPlayer;

public class WRPlayerManager {
    
    List<WRPlayer> players = new ArrayList<>();

    public WRPlayer getPlayer(UUID uuid) {
        for(WRPlayer player : players) {
            if(player.getUuid().equals(uuid)) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(WRPlayer player) {
        this.players.add(player);
    }

    public void removePlayer(WRPlayer player) {
        this.players.remove(player);
    }

    public List<WRPlayer> getPlayers() {
        return players;
    }
}
