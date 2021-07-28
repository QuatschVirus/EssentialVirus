package de.quatschvirus.essentialvirus.backpack;

import de.quatschvirus.essentialvirus.utils.Config;

import java.io.IOException;
import java.util.*;

public class BackpackManager {

    private final Map<UUID, Backpack> map;

    public BackpackManager() {
        map = new HashMap<>();

        load();
    }

    public Backpack getBackpack(UUID uuid) {

        if(map.containsKey(uuid)) {
            return map.get(uuid);
        }

        Backpack backpack = new Backpack(uuid);

        map.put(uuid, backpack);

        return backpack;
    }

    public void setBackpack(UUID uuid, Backpack backpack) {
        map.put(uuid, backpack);
    }

    private void load() {

        List<String> uuids = Config.getStringList("backpack.uuids");

        uuids.forEach(s -> {

            UUID uuid = UUID.fromString(s);

            String base64 = (String) Config.get("backpack.inventory." + s);

            try {
                map.put(uuid, new Backpack(uuid, base64));
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

        try {
            Config.set("backpack.uuids", uuids);
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.forEach((uuid, backpack) -> {
            try {
                Config.set("backpack.inventory." + uuid.toString(), backpack.toBase64());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
