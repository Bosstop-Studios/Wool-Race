package tech.bosstop.woolrace;

import org.bukkit.plugin.java.JavaPlugin;

import tech.bosstop.woolrace.commands.WRCommandHandler;
import tech.bosstop.woolrace.core.WRGameManager;
import tech.bosstop.woolrace.core.WRKitManager;
import tech.bosstop.woolrace.core.WRPlayerManager;
import tech.bosstop.woolrace.events.WREventHandler;
import tech.bosstop.woolrace.utilities.Chat;

public class WoolRace extends JavaPlugin {

    private static WoolRace instance;

    private Chat chat;

    private WREventHandler eventHandler;

    private WRCommandHandler commandHandler;

    private WRGameManager gameManager;

    private WRKitManager kitManager;

    private WRPlayerManager playerManager;

    private String[] startup = {
        "                                                                        ",
        "&b██     ██  ██████   ██████  ██      ██████   █████   ██████ ███████ &r", 
        "&b██     ██ ██    ██ ██    ██ ██      ██   ██ ██   ██ ██      ██      &r",
        "&b██  █  ██ ██    ██ ██    ██ ██      ██████  ███████ ██      █████   &r",
        "&b██ ███ ██ ██    ██ ██    ██ ██      ██   ██ ██   ██ ██      ██      &r",
        "&b ███ ███   ██████   ██████  ███████ ██   ██ ██   ██  ██████ ███████ &r",
        "                                                                        ",
        "&aVersion: %version%                                                  &r",
        "&aMaps: %maps%                                                        &r",
    };

    @Override
    public void onEnable() {
        instance = this;
        this.chat = new Chat();
        this.eventHandler = new WREventHandler();
        this.commandHandler = new WRCommandHandler();
        this.gameManager = new WRGameManager();
        this.kitManager = new WRKitManager();
        this.playerManager = new WRPlayerManager();

        this.eventHandler.register();
        this.commandHandler.register();

        for(String line : startup) {
            line.replace("%version%", getDescription().getVersion());
            line.replace("%maps%", "0");

            this.chat.console(line);
        }

        this.chat.console("&aPlugin enabled.");
    }

    @Override
    public void onDisable() {
        this.chat.console("&cPlugin disabled.");
    }

    public Chat getChat() {
        return chat;
    }

    public WREventHandler getEventHandler() {
        return eventHandler;
    }

    public WRCommandHandler getCommandHandler() {
        return commandHandler;
    }

    public WRGameManager getGameManager() {
        return gameManager;
    }

    public WRKitManager getKitManager() {
        return kitManager;
    }

    public WRPlayerManager getPlayerManager() {
        return playerManager;
    }

    public static WoolRace getInstance() {
        return instance;
    }
}
