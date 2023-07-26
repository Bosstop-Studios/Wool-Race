package tech.bosstop.woolrace.commands.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tech.bosstop.common.structures.core.WRKit;
import tech.bosstop.woolrace.commands.WRCommand;

public class cmd_kit extends WRCommand {

    public cmd_kit() {
        super("kit");
    }

    @Override
    public String getDescription() {
        return "Kit management";
    }

    @Override
    public String getUsage() {
        return "/wr kit <create|delete|update> (...args)";
    }

    @Override
    public String getPermission() {
        return "woolrace.command.kit";
    }

    private void onCreate(Player player, String[] args) {
        if(this.instance.getKitManager().createKit(args[1], new WRKit(args[0], player.getInventory()))) {
            this.instance.getChat().send(player, "&aSuccessfully created kit &6" + args[1] + "&a.");
        } else {
            this.instance.getChat().send(player, "&4Kit with that name already exists &6" + args[1] + "&4.");
        }
    }

    private void onDelete(Player player, String[] args) {
        if(this.instance.getKitManager().kitExists(args[1])) {
            this.instance.getKitManager().deleteKit(args[1]);
            this.instance.getChat().send(player, "&aSuccessfully deleted kit &6" + args[1] + "&a.");
        } else {
            this.instance.getChat().send(player, "&4Kit with that name does not exist &6" + args[1] + "&4.");
        }
    }

    private void onUpdate(Player player, String[] args) {
        if(this.instance.getKitManager().kitExists(args[1])) {
            this.instance.getKitManager().updateKit(args[1], new WRKit(args[0], player.getInventory()));
            this.instance.getChat().send(player, "&aSuccessfully updated kit &6" + args[1] + "&a.");
        } else {
            this.instance.getChat().send(player, "&4Kit with that name does not exist &6" + args[1] + "&4.");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!this.isPlayer(sender)) return true;
        Player player = (Player) sender;

        if(args[0].equalsIgnoreCase("create")) {
            this.onCreate(player, args);
            System.out.println("createWORKS");
        }

        if(args[0].equalsIgnoreCase("delete")) this.onDelete(player, args);
        if(args[0].equalsIgnoreCase("update")) this.onUpdate(player, args);

        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabList = new ArrayList<>();

        if(args.length == 1) {
            tabList.add("create");
            tabList.add("delete");
            tabList.add("update");
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("update")) {
                for(String kit : this.instance.getKitManager().getKits().keySet()) {
                    tabList.add(kit);
                }
            }
        }

        return tabList;
    }
    
}
