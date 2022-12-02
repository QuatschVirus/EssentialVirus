package de.quatschvirus.essentialvirus.pos;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PositionManager {

    private final YamlConfiguration config = Config.getConfig();

    private final ArrayList<Position> positions = new ArrayList<>();

    public PositionManager() {
        List<?> pos_raw = config.getList("pos");
        if (pos_raw != null) {
            for (Object pos : pos_raw) {
                if (pos instanceof HashMap) {
                    if (((HashMap<?, ?>) pos).keySet().toArray()[0] instanceof String) {
                        try {
                            //noinspection unchecked
                            positions.add(new Position((HashMap<String, Object>) pos));
                        } catch (IllegalArgumentException e) {
                            Bukkit.getLogger().warning(Main.getPrefix() + "Postion ignored (index: " + pos_raw.indexOf(pos) + ")");
                        }
                    }
                }
            }
        } else {
            Bukkit.getLogger().severe(Main.getPrefix() + "Positions are not stored as List");
            throw new IllegalArgumentException();
        }
    }

    public ArrayList<String> getIds() {
        ArrayList<String> ids = new ArrayList<>();
        for (Position pos : positions) {
            ids.add(pos.getId());
        }
        return ids;
    }

    public Position getPosition(String id) {
        for (Position pos : positions) {
            if (pos.getId().equals(id)) {
                return pos;
            }
        }
        return null;
    }

    public void move(String id, Location new_loc) {
        Position pos = getPosition(id);
        if (pos != null) {
            pos.setLoc(new_loc);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void add(String id, Location loc, Player owner) {
        positions.add(new Position(loc, id, owner.getUniqueId().toString()));
    }


    public void remove(String id) {
        Position pos = getPosition(id);
        if (pos != null) {
            positions.remove(pos);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void save() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        for (Position pos : positions) {
            data.add(pos.save());
        }
        config.set("pos", data);
    }
}
