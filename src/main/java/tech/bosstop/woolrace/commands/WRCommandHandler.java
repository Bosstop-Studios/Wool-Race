package tech.bosstop.woolrace.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import tech.bosstop.woolrace.WoolRace;
import tech.bosstop.woolrace.commands.core.cmd_game;
import tech.bosstop.woolrace.commands.core.cmd_help;
import tech.bosstop.woolrace.commands.core.cmd_kit;
import tech.bosstop.woolrace.commands.player.cmd_selector;

public class WRCommandHandler implements CommandExecutor, TabCompleter {

    private final WoolRace instance = WoolRace.getInstance();

    private HashMap<String, WRCommand> wrCommands = new HashMap<>();

    public WRCommandHandler() {
        instance.getCommand("woolrace").setExecutor(this::onCommand);
        instance.getCommand("woolrace").setTabCompleter(this::onTabComplete);
    }

    private String[] removeFirst(String[] args) {
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        return newArgs;
    }

    public void registerCommand(String command, WRCommand wrCommand) {
        this.wrCommands.put(command, wrCommand);
    }

    public void register() {
        
        // Core commands
        new cmd_game();
        new cmd_kit();
        new cmd_help();
        
        // Player commands
        new cmd_selector();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) return true;
        if(this.wrCommands.containsKey(args[0])) {
            if (this.wrCommands.get(args[0]).getPermission() != null) {
                if (!commandSender.hasPermission(this.wrCommands.get(args[0]).getPermission())) {
                    instance.getChat().send(commandSender, "&4You don't have permission to use this command.");
                    return true;
                }
            }
            return this.wrCommands.get(args[0]).onCommand(commandSender, command, s, this.removeFirst(args));
        } else {
            instance.getChat().send(commandSender, "&4Unknown command. Type &6/wr help &4for help.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tabList = new ArrayList<>();
        if(args.length == 1) {
            tabList.addAll(this.wrCommands.keySet());
        } else {
            if(this.wrCommands.containsKey(args[0])) {
                tabList = this.wrCommands.get(args[0]).onTab(commandSender, command, s, this.removeFirst(args));
            }
        }
        return tabList;
    }

    public HashMap<String, WRCommand> getCommands() {
        return this.wrCommands;
    }
}
