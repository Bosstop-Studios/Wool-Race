package ml.bosstop.woolwars.util;

import com.google.gson.Gson;
import ml.bosstop.woolwars.Main;
import ml.bosstop.woolwars.util.storage.Note;

import java.io.*;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Storage {
    private static Main plugin;

    private static ArrayList<Note> players = new ArrayList<Note>();

    public Storage(Main plugin) { Storage.plugin = plugin; }

    public void createDir(String folderName) {
        File dir = new File("plugins/" + plugin.getDataFolder().getName() + "/"+ folderName);
        if (!dir.exists()) { dir.mkdirs(); }
    }

    public static Note createNote(UUID id){
        Note note = new Note(id);
        players.add(note);

        return note;
    }

    public static void deleteNote(UUID id) {
        for (Note note : players) {
            if (note.getId().equalsIgnoreCase(String.valueOf(id))) {
                players.remove(note);
                break;
            }
        }
    }
    public static Note findNote(UUID id) {
        for (Note note : players) {
            if (note.getId().equalsIgnoreCase(String.valueOf(id))) {
                return note;
            }
        }
        return null;
    }
    public static boolean findNoteExist(UUID id) {
        for (Note note : players) {
            if (note.getId().equalsIgnoreCase(String.valueOf(id))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static Note updateNote(UUID id, int amount){
        for (Note note : players) {
            if (note.getId().equalsIgnoreCase(String.valueOf(id))) {
                note.setCoins(amount);
            }
        }
        return null;
    }

    public static void loadNotes() throws IOException {

        Gson gson = new Gson();
        File file = new File("plugins/" + plugin.getDataFolder().getName() + "/players.json");
        if (file.exists()){
            Reader reader = new FileReader(file);
            Note[] n = gson.fromJson(reader, Note[].class);
            players = new ArrayList<>(Arrays.asList(n));
            System.out.println(players);
        }

    }

    public static void saveNotes() throws IOException {

        Gson gson = new Gson();
        File file = new File("plugins/" + plugin.getDataFolder().getName() + "/players.json");
        Writer writer = new FileWriter(file, false);
        gson.toJson(players, writer);
        writer.flush();
        writer.close();
        System.out.println("Notes saved.");

    }

    public void enable() {
        try {
            createDir("Worlds");
            loadNotes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disable() {
        try {
            saveNotes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
