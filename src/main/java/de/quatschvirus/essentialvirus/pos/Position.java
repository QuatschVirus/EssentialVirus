package de.quatschvirus.essentialvirus.pos;

import org.bukkit.Location;

import java.util.HashMap;

public class Position {

    private Location loc;
    private final String id;
    private String owner;

    public Position(HashMap<String, Object> data) throws IllegalArgumentException {
        if (data.get("id") != null && data.get("id") instanceof String) {
            this.id = data.get("id").toString();
        } else {
            throw new IllegalArgumentException();
        }
        if (data.get("owner") != null && data.get("owner") instanceof String) {
            this.owner = data.get("owner").toString();
        } else {
            throw new IllegalArgumentException();
        }
        this.loc = Location.deserialize(data);
    }

    public Position(Location loc, String id, String owner) {
        this.loc  = loc;
        this.id = id;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public HashMap<String, Object> save() {
        HashMap<String, Object> data;
        data = (HashMap<String, Object>) loc.serialize();
        data.put("id", id);
        return data;
    }
}
