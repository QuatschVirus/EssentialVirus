package de.quatschvirus.essentialvirus.backpack;

import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.*;

public class BackpackManager {

    private final Map<UUID, Backpack> map;
    private final YamlConfiguration config = Config.getConfig();

    public BackpackManager() {
        map = new HashMap<>();

        load();
    }

    public Backpack getBackpack(UUID uuid) {

        if(map.containsKey(uuid)) {
            return map.get(uuid);
        }

        Backpack backpack = new Backpack();

        map.put(uuid, backpack);

        return backpack;
    }

    private void load() {

        List<String> uuids = config.getStringList("backpack.uuids");

        uuids.forEach(s -> {

            UUID uuid = UUID.fromString(s);

            String base64 = config.getString("backpack.inventory." + s);

            try {
                map.put(uuid, new Backpack(base64));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public void save() {

        List<String> uuids = new ArrayList<>();

        for (UUID uuid : map.keySet()) {
            uuids.add(uuid.toString());
        }

        config.set("backpack.uuids", uuids);

        map.forEach((uuid, backpack) -> config.set("backpack.inventory." + uuid.toString(), backpack.toBase64()));
    }
}
