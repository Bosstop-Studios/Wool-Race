package ml.bosstop.woolwars;

import ml.bosstop.woolwars.commands.Command;
import ml.bosstop.woolwars.events.Event;
import ml.bosstop.woolwars.util.Chat;
import ml.bosstop.woolwars.util.Storage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public Chat chat;
    public Storage storage;

    public static YamlConfiguration config;
    public Main() {}

    @Override
    public void onEnable() {

        saveDefaultConfig();

        config = YamlConfiguration.loadConfiguration(new File("plugins/" + this.getDataFolder().getName() + "/config.yml"));

        storage = new Storage(this);

        this.chat = new Chat();

        try {
            storage.enable();
            Command.enable(this);
            Event.enable(this);
        } finally {
            chat.console("&fStarting Wool Wars");
            chat.console("&fWool Wars Active");
        }

        this.getConfig().options().copyDefaults();

    }


    @Override
    public void onDisable() {

        try {
            storage.disable();
        } finally {
            chat.console("&fWool Wars Disabled");
        }

    }

}
