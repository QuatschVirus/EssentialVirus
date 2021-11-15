package de.quatschvirus.essentialvirus.utils;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PosSerial {

    public static ArrayList<Object> toSerial(Location location) {
        ArrayList<Object> serial = new ArrayList<>();
        if(location.getWorld() != null) {
            serial.add(location.getWorld().getName());
        } else {
            serial.add("$$NONE$$");
        }

        serial.add(String.valueOf(location.getX()));
        serial.add(String.valueOf(location.getY()));
        serial.add(String.valueOf(location.getZ()));
        serial.add(String.valueOf(location.getYaw()));
        serial.add(String.valueOf(location.getPitch()));
        return serial;
    }

    public static Location fromSerial(ArrayList<String> serial) {
        Map<String, Object> intern = new HashMap<>();
        intern.put("world", serial.get(0));
        intern.put("x", serial.get(1));
        intern.put("y", serial.get(2));
        intern.put("z", serial.get(3));
        intern.put("yaw", serial.get(4));
        intern.put("pitch", serial.get(5));
        return Location.deserialize(intern);
    }

}
