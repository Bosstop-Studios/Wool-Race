package tech.bosstop.woolrace.commands.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tech.bosstop.common.structures.core.WRGame;
import tech.bosstop.woolrace.commands.WRCommand;

public class cmd_game extends WRCommand {

    public cmd_game() {
        super("game");
    }

    @Override
    public String getDescription() {
        return "Game management";
    }

    @Override
    public String getUsage() {
        return "/wr game <create|set|update|finalize> (...args)";
    }

    @Override
    public String getPermission() {
        return "woolrace.command.game";
    }

    private void onCreate(Player player, String[] args) {
        if(this.instance.getGameManager().createGame(args[1], player.getLocation())) {
            this.instance.getChat().send(player, "&aSuccessfully created game &6" + args[1] + "&a.");
        } else {
            this.instance.getChat().send(player, "&4Game with that name already exists &6" + args[1] + "&4.");
        }
    }

    private void onSet(Player player, String[] args) {

        if(args[1].equalsIgnoreCase("lobby")) {
            this.instance.getGameManager().setLobby(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set lobby.");
            return;
        }

        if(args[1].equalsIgnoreCase("teama")) {
            this.instance.getGameManager().setTeamA(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set spawn.");
            return;
        }

        if(args[1].equalsIgnoreCase("teamb")) {
            this.instance.getGameManager().setTeamB(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set spawn.");
            return;
        }

        if(args[1].equalsIgnoreCase("woolgrid")) {
            this.instance.getGameManager().setWoolGrid(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set spectator spawn.");
            return;
        }

        if(args[1].equalsIgnoreCase("minplayers")) {
            this.instance.getGameManager().setMinPlayers(Integer.parseInt(args[2]));
            this.instance.getChat().send(player, "&aSuccessfully set min players to &6" + args[2] + "&a.");
            return;
        }

        if(args[1].equalsIgnoreCase("maxplayers")) {
            this.instance.getGameManager().setMaxPlayers(Integer.parseInt(args[2]));
            this.instance.getChat().send(player, "&aSuccessfully set max players to &6" + args[2] + "&a.");
            return;
        }

        if(args[1].equalsIgnoreCase("rounds")) {
            this.instance.getGameManager().setRounds(Integer.parseInt(args[2]));
            this.instance.getChat().send(player, "&aSuccessfully set rounds to &6" + args[2] + "&a.");
            return;
        }

        this.instance.getChat().send(player, "&cInvalid argument.");
    }

    private void onUpdate(Player player, String[] args) {

        String gameName = args[1];

        if(!this.instance.getGameManager().gameExists(gameName)) {
            this.instance.getChat().send(player, "&cGame with that name doesn't exist.");
            return;
        }

        WRGame game = this.instance.getGameManager().getGame(gameName);

        if(game == null) {
            this.instance.getChat().send(player, "&cGame with that name doesn't exist.");
            return;
        }

        if(args[2].equalsIgnoreCase("lobby")) {
            game.setLobby(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set lobby.");
            return;
        }

        if(args[2].equalsIgnoreCase("teama")) {
            game.setTeamASpawn(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set spawn.");
            return;
        }

        if(args[2].equalsIgnoreCase("teamb")) {
            game.setTeamBSpawn(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set spawn.");
            return;
        }

        if(args[2].equalsIgnoreCase("woolgrid")) {
            game.setWoolGrid(player.getLocation());
            this.instance.getChat().send(player, "&aSuccessfully set spectator spawn.");
            return;
        }

        if(args[2].equalsIgnoreCase("minplayers")) {
            game.setMinPlayers(Integer.parseInt(args[3]));
            this.instance.getChat().send(player, "&aSuccessfully set min players to &6" + args[3] + "&a.");
            return;
        }

        if(args[2].equalsIgnoreCase("maxplayers")) {
            game.setMaxPlayers(Integer.parseInt(args[3]));
            this.instance.getChat().send(player, "&aSuccessfully set max players to &6" + args[3] + "&a.");
            return;
        }

        if(args[2].equalsIgnoreCase("rounds")) {
            game.setRounds(Integer.parseInt(args[3]));
            this.instance.getChat().send(player, "&aSuccessfully set rounds to &6" + args[3] + "&a.");
            return;
        }

        this.instance.getChat().send(player, "&cInvalid argument.");
    }

    private void onFinalized(Player player, String[] args) {
        WRGame game = this.instance.getGameManager().getNewGame();

        if(game == null) {
            this.instance.getChat().send(player, "&cNo game is being created.");
            return;
        }

        if(game.getLobby() == null) {
            this.instance.getChat().send(player, "&cLobby is not set.");
            return;
        }

        if(game.getTeamASpawn() == null) {
            this.instance.getChat().send(player, "&cTeam A spawn is not set.");
            return;
        }

        if(game.getTeamBSpawn() == null) {
            this.instance.getChat().send(player, "&cTeam B spawn is not set.");
            return;
        }

        if(game.getWoolGrid() == null) {
            this.instance.getChat().send(player, "&cWool grid is not set.");
            return;
        }

        if(game.getMinPlayers() == 0) {
            this.instance.getChat().send(player, "&cMin players is not set.");
            return;
        }

        if(game.getMaxPlayers() == 0) {
            this.instance.getChat().send(player, "&cMax players is not set.");
            return;
        }

        if(game.getRounds() == 0) {
            this.instance.getChat().send(player, "&cRounds is not set.");
            return;
        }

        this.instance.getGameManager().finalizeGame();
        this.instance.getChat().send(player, "&aSuccessfully finalized game &6" + game.getName() + "&a.");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!this.isPlayer(sender)) return true;
        if(!this.argsNeeded(sender, args, this.getUsage(), 1)) return true;

        Player player = (Player) sender;

        if(args[0].equalsIgnoreCase("create")) this.onCreate(player, args);
        if(args[0].equalsIgnoreCase("set")) this.onSet(player, args);
        if(args[0].equalsIgnoreCase("update")) this.onUpdate(player, args);
        if(args[0].equalsIgnoreCase("finalize")) this.onFinalized(player, args);


        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabList = new ArrayList<>();

        if(args.length == 1) {
            tabList.add("create");
            tabList.add("set");
            tabList.add("update");
            tabList.add("finalize");
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("set")) {
            tabList.add("lobby");
            tabList.add("teama");
            tabList.add("teamb");
            tabList.add("woolgrid");
            tabList.add("minplayers");
            tabList.add("maxplayers");
            tabList.add("rounds");
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("update")) {
            for(WRGame game : this.instance.getGameManager().getGames()) {
                tabList.add(game.getName());
            }
        }

        if(args.length == 3 && args[0].equalsIgnoreCase("update")) {
            tabList.add("lobby");
            tabList.add("teama");
            tabList.add("teamb");
            tabList.add("woolgrid");
            tabList.add("minplayers");
            tabList.add("maxplayers");
            tabList.add("rounds");
        }

        return tabList;
    }
    
}
