package ml.bosstop.woolwars.util;

import ml.bosstop.woolwars.Main;

import java.io.File;

public class Storage {
    private  Main plugin;
    public Storage(Main plugin) { this.plugin = plugin; }

    public void createDir(String folderName) {
        File dir = new File("plugins/" + plugin.getDataFolder().getName() + "/"+ folderName);
        if (!dir.exists()) { dir.mkdirs(); }
    }

    public void enable() {

        createDir("Worlds");

    }


}
