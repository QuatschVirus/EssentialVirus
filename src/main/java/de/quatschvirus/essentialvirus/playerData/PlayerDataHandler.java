package de.quatschvirus.essentialvirus.playerData;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataHandler {
    private final Map<Player, PlayerData> dataMap = new HashMap<>();

    public PlayerData get(Player p) {
        if (dataMap.containsKey(p)) {
            return dataMap.get(p);
        } else {
            return new PlayerData(p);
        }
    }

    public void set(Player p, PlayerData pD) {
        dataMap.put(p, pD);
    }
}
