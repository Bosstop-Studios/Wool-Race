package tech.bosstop.common.structures.core;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import tech.bosstop.woolrace.WoolRace;

public class WRGame {

    private String name;

    private WRGameState state = WRGameState.LOBBY;

    private World world;

    private Location lobby;

    private List<WRPlayer> teamA;

    private Location teamASpawn;

    private List<WRPlayer> teamB;

    private Location teamBSpawn;

    private Location woolGrid;

    private int minPlayers = 4;

    private int maxPlayers = 8;

    private int rounds = 3;

    private int round = 0;

    private List<WRPlayer> players;

    public WRGame(String name, World world) {
        this.name = name;
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public WRGameState getState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public Location getLobby() {
        return lobby;
    }

    public List<WRPlayer> getTeamA() {
        return teamA;
    }

    public Location getTeamASpawn() {
        return teamASpawn;
    }

    public List<WRPlayer> getTeamB() {
        return teamB;
    }

    public Location getTeamBSpawn() {
        return teamBSpawn;
    }

    public Location getWoolGrid() {
        return woolGrid;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getRounds() {
        return rounds;
    }

    public int getRound() {
        return round;
    }

    public void setState(WRGameState state) {
        this.state = state;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public void setTeamASpawn(Location teamA) {
        this.teamASpawn = teamA;
    }

    public void setTeamBSpawn(Location teamB) {
        this.teamBSpawn = teamB;
    }

    public void setWoolGrid(Location woolGrid) {
        this.woolGrid = woolGrid;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setPlayers(List<WRPlayer> players) {
        this.players = players;
    }

    public List<WRPlayer> getPlayers() {
        if(this.getState() == WRGameState.LOBBY) return this.players;

        List<WRPlayer> players = teamA;
        players.addAll(teamB);
        return players;
    }

    public boolean isPlayerIn(UUID uuid) {
        for(WRPlayer player : players) {
            if(player.getUuid().equals(uuid)) return true;
        }
        return false;
    }

    public boolean addPlayer(WRPlayer player) {
        if(this.isFull()) return false;
        players.add(player);
        Bukkit.getPlayer(player.getUuid()).teleport(this.getLobby());
        if(this.isReady()) this.start();
        return true;
    }

    public void removePlayer(WRPlayer player) {
        players.remove(player);
    }

    public WRPlayer getPlayer(UUID uuid) {
        for(WRPlayer player : players) {
            if(player.getUuid().equals(uuid)) return player;
        }
        return null;
    }

    public boolean isFull() {
        return players.size() == maxPlayers;
    }

    public boolean isReady() {
        return players.size() >= minPlayers;
    }

    public boolean isInGrid(Location loc) {
        if(loc.getBlockX() >= woolGrid.getBlockX() - 1 && loc.getBlockX() <= woolGrid.getBlockX() + 1) {
            if(loc.getBlockZ() >= woolGrid.getBlockZ() - 1 && loc.getBlockZ() <= woolGrid.getBlockZ() + 1) {
                return true;
            }
        }
        return false;
    }

    public boolean checkGrid() {
        for(int x = -1; x < 2; x++) {
            for(int z = -1; z < 2; z++) {
                Location loc = new Location(woolGrid.getWorld(), woolGrid.getX() + x, woolGrid.getY(), woolGrid.getZ() + z);
                System.out.println(loc.getBlock().getType().toString());
                if(loc.getBlock().getType().toString().contains("WOOL")) {
                    return false;
                }
            }
        }

        return true;
    }

    public void resetGrid() {
        for(int x = -1; x < 2; x++) {
            for(int z = -1; z < 2; z++) {
                Location loc = new Location(woolGrid.getWorld(), woolGrid.getX() + x, woolGrid.getY(), woolGrid.getZ() + z);
                loc.getBlock().setType(Material.WHITE_WOOL);
            }
        }
    }

    public void CountDown() {
        new BukkitRunnable() {
            WoolRace instance = WoolRace.getInstance();
            int timeLeft = 30;

            @Override
            public void run() {
                
                if(timeLeft == 30) {
                    getPlayers().forEach((player) -> {
                        instance.getChat().send(Bukkit.getPlayer(player.getUuid()), "&aGame starting in 30 seconds!");
                    });
                }

                if(timeLeft == 20) {
                    getPlayers().forEach((player) -> {
                        instance.getChat().send(Bukkit.getPlayer(player.getUuid()), "&aGame starting in 20 seconds!");
                    });
                }

                if(timeLeft <= 10) {
                    getPlayers().forEach((player) -> {
                        instance.getChat().send(Bukkit.getPlayer(player.getUuid()), "&aGame starting in " + timeLeft + " seconds!");
                    });
                }

                if(timeLeft == 0) {
                    this.cancel();
                    startRound();
                    return;
                }
            }
        }.runTaskTimer(WoolRace.getInstance(),20L, 20L);
    }

    public void start() {
        this.setState(WRGameState.TRANSITION);
        this.setRound(1);
        this.resetGrid();

        this.getPlayers().forEach((player) -> {
            if(this.getPlayers().indexOf(player) % 2 == 0) {
                this.teamA.add(player);
                Bukkit.getPlayer(player.getUuid()).teleport(this.getTeamASpawn());
            } else {
                this.teamB.add(player);
                Bukkit.getPlayer(player.getUuid()).teleport(this.getTeamBSpawn());
            }
        });

        this.CountDown();
    }

    public void nextRound() {
        this.setState(WRGameState.TRANSITION);
        this.setRound(this.getRound() + 1);
        this.resetGrid();
        this.getPlayers().forEach((player) -> {
            if(this.getPlayers().indexOf(player) % 2 == 0) {
                Bukkit.getPlayer(player.getUuid()).teleport(this.getTeamASpawn());
            } else {
                Bukkit.getPlayer(player.getUuid()).teleport(this.getTeamBSpawn());
            }
        });

        this.CountDown();
    }

    public void startRound() {
        this.setState(WRGameState.INGAME);

        this.getTeamA().forEach((player) -> {
            Bukkit.getPlayer(player.getUuid()).getInventory().clear();
            Bukkit.getPlayer(player.getUuid()).getInventory().setStorageContents(player.getKit().getInventory().getStorageContents());
            ItemStack woolStack = new ItemStack(Material.RED_WOOL, 64);
            woolStack.addEnchantment(Enchantment.BINDING_CURSE, 1);
            Bukkit.getPlayer(player.getUuid()).getInventory().addItem(woolStack);
            Bukkit.getPlayer(player.getUuid()).getInventory().addItem(new ItemStack(Material.SHEARS, 1));
        });

        this.getTeamB().forEach((player) -> {
            Bukkit.getPlayer(player.getUuid()).getInventory().clear();
            Bukkit.getPlayer(player.getUuid()).getInventory().setStorageContents(player.getKit().getInventory().getStorageContents());
            ItemStack woolStack = new ItemStack(Material.BLUE_WOOL, 64);
            woolStack.addEnchantment(Enchantment.BINDING_CURSE, 1);
            Bukkit.getPlayer(player.getUuid()).getInventory().addItem(woolStack);
            Bukkit.getPlayer(player.getUuid()).getInventory().addItem(new ItemStack(Material.SHEARS, 1));
        });
    }

    public void end() {
        this.setState(WRGameState.LOBBY);
        this.setRound(0);
        this.resetGrid();
        this.getPlayers().forEach((player) -> {
            Bukkit.getPlayer(player.getUuid()).teleport(player.getPreviousLocation());
        });
    }

}
