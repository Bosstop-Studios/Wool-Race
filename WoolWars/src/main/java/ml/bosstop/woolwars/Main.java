package ml.bosstop.woolwars;

import ml.bosstop.woolwars.util.Chat;
import ml.bosstop.woolwars.util.Storage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public Chat chat;

    public static YamlConfiguration config;
    public Main() {}

    @Override
    public void onEnable() {

        saveDefaultConfig();

        config = YamlConfiguration.loadConfiguration(new File("plugins/" + this.getDataFolder().getName() + "/config.yml"));

        new Storage(this).enable();

        this.chat = new Chat();

        try {

        } finally {
            chat.console("&fStarting Wool Wars");
            chat.console("&fCore Active");
        }

        this.getConfig().options().copyDefaults();

    }


    @Override
    public void onDisable() {

        try {

        } finally {
            chat.console("&fCore Disabled");
        }

    }

}
