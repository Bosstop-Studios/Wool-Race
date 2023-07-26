package tech.bosstop.woolrace.commands.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import tech.bosstop.common.structures.gui.GamesGUI;
import tech.bosstop.woolrace.commands.WRCommand;

public class cmd_selector extends WRCommand {

    public cmd_selector() {
        super("selector");
    }

    @Override
    public String getDescription() {
        return "Opens the map selector.";
    }

    @Override
    public String getUsage() {
        return "/wr selector";
    }

    @Override
    public String getPermission() {
        return "woolrace.command.selector";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!this.isPlayer(sender)) return true;

        Player player = (Player) sender;
        Inventory inv = new GamesGUI(this.instance.getGameManager().getGames()).getInventory();

        player.openInventory(inv);

        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
    
}
