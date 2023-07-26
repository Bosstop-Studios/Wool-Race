package tech.bosstop.woolrace.core;

import java.util.List;

import org.bukkit.Location;

import tech.bosstop.common.structures.core.WRGame;

public class WRGameManager {
    
    private List<WRGame> games;

    private WRGame newgame;

    public List<WRGame> getGames() {
        return games;
    }

    public void setGames(List<WRGame> games) {
        this.games = games;
    }

    public boolean gameExists(String name) {
        for (WRGame game : this.games) {
            if (game.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public WRGame getGame(String name) {
        for (WRGame game : this.games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }

    public WRGame getNewGame() {
        return this.newgame;
    }

    public void addGame(WRGame game) {
        this.games.add(game);
    }

    public void removeGame(WRGame game) {
        this.games.remove(game);
    }

    public boolean createGame(String name, Location loc) {
        if(this.gameExists(name)) return false;
        this.newgame = new WRGame(name, loc.getWorld());
        return true;
    }

    public void setLobby(Location lobby) {
        this.newgame.setLobby(lobby);
    }

    public void setTeamA(Location teamA) {
        this.newgame.setTeamASpawn(teamA);
    }

    public void setTeamB(Location teamB) {
        this.newgame.setTeamBSpawn(teamB);
    }

    public void setWoolGrid(Location woolGrid) {
        this.newgame.setWoolGrid(woolGrid);
    }

    public void setMinPlayers(int minPlayers) {
        this.newgame.setMinPlayers(minPlayers);
    }

    public void setMaxPlayers(int maxPlayers) {
        this.newgame.setMaxPlayers(maxPlayers);
    }

    public void setRounds(int rounds) {
        this.newgame.setRounds(rounds);
    }

    public void setRound(int round) {
        this.newgame.setRound(round);
    }

    public void finalizeGame() {
        this.games.add(this.newgame);
    }

}
