package de.quatschvirus.essentialvirus.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {

    private static File file;
    private static YamlConfiguration config;

    public Config() {

        File dir = new File("./plugins/EssentialVirus");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file = new File(dir, "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public static boolean contains(String path) {
        return config.contains(path);
    }

    public static void set(String path, Object value) throws IOException {
        config.set(path, value);
    }

    public static Object get(String path) {
        if(!contains(path)) {
            return null;
        }else {
            return config.get(path);
        }
    }

    public static List<String> getStringList(String path) {
        if(!contains(path)) {
            return new ArrayList<>();
        }else {
            return config.getStringList(path);
        }
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
