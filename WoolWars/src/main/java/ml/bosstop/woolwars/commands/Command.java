package ml.bosstop.woolwars.commands;

import ml.bosstop.woolwars.Main;
import ml.bosstop.woolwars.events.playerJoin;

public class Command {
    public static void enable(Main plugin) {
        new playerJoin(plugin);
    }
}
