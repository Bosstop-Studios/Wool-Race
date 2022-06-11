package ml.bosstop.woolwars.events;

import ml.bosstop.woolwars.Main;

import ml.bosstop.woolwars.managers.WorldManager;
import ml.bosstop.woolwars.util.Storage;

import java.io.File;
import java.util.UUID;

public class Event {
    public static void enable(Main plugin) {
        new playerJoin(plugin);

        Storage.createNote(UUID.randomUUID());

        WorldManager.copyFileStructure(new File("world/"), new File("plugins/" + plugin.getDataFolder().getName() + "/Worlds"));

        WorldManager.createWorld(plugin,"Worlds");
    }
}
