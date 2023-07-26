package tech.bosstop.woolrace.commands.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tech.bosstop.woolrace.commands.WRCommand;

public class cmd_help extends WRCommand {

    public cmd_help() {
        super("help");
    }

    @Override
    public String getDescription() {
        return "Shows the help page.";
    }

    @Override
    public String getUsage() {
        return "/wr help";
    }

    @Override
    public String getPermission() {
        return "woolrace.command.help";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        HashMap<String, WRCommand> commands = this.instance.getCommandHandler().getCommands();

        instance.getChat().send(sender, "&3WoolRace &r- &aHelp Page");
        commands.forEach((name, cmd) -> {
            instance.getChat().send(sender, "&3" + cmd.getUsage() + " &r- " + cmd.getDescription());
        });

        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<String>();
    }
}
