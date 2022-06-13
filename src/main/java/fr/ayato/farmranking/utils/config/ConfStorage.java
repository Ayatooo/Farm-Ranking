package fr.ayato.farmranking.utils.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.ayato.farmranking.Main;
import java.io.*;
import java.util.Objects;

public class ConfStorage {
    public static File storageFile;

    public ConfStorage() {
        this.gson = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
        storageFile = Main.getInstance().getDataFolder();
        if (!storageFile.exists())
            storageFile.mkdir();
    }
    private Gson gson;

    public boolean configExist() {
        for (File f : (File[]) Objects.<File[]>requireNonNull(storageFile.listFiles())) {
            if (f.getName().equalsIgnoreCase("config.json")) {
                return true;
            }
        }
        return false;
    }

    public void saveDefaultConfig() {
        ConfigJSON defaultConfig = new ConfigJSON();
        File file = new File(storageFile, "config.json");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(serialize(defaultConfig));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        for (File f : Objects.requireNonNull(storageFile.listFiles())) {
            if (f.getName().equalsIgnoreCase("config.json")) {
                (Main.getInstance()).loadConfig = deserialize(f);
                if ((Main.getInstance()).loadConfig == null) {
                    System.out.println("Error, Configuration was corrupted, loaded default config!");
                    (Main.getInstance()).loadConfig = new ConfigJSON();
                }
                return;
            }
        }
    }

    public String read(File f) {
        if (f.exists()) {
            try {
                BufferedReader r = new BufferedReader(new FileReader(f));
                StringBuilder t = new StringBuilder();
                String l;
                while ((l = r.readLine()) != null) {
                    t.append(l);
                }
                r.close();
                return t.toString();
            }
            catch (IOException ignored) {}
        }
        return null;
    }

    public void saveConfig() {
        ConfigJSON defaultConfig = (Main.getInstance()).loadConfig;
        File file = new File(ConfStorage.storageFile, "config.json");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(serialize(defaultConfig));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigJSON deserialize(File file) {
        return this.gson.fromJson(read(file), ConfigJSON.class);
    }

    public String serialize(ConfigJSON config) {
        return this.gson.toJson(config);
    }
}
