package de.quatschvirus.essentialvirus.playerData;

import com.google.common.base.Preconditions;

import de.quatschvirus.essentialvirus.Main;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    private final Map<PlayerDataKey, Object> data = new HashMap<>();
    private final Player player;

    public PlayerData(Player player) {
        for (PlayerDataKey key : PlayerDataKey.values()) {
            data.put(key, key.getDefaultValue());
        }
        this.player = player;
        Main.getInstance().getPlayerDataHandler().set(player, this);
    }

    public Object get(PlayerDataKey key) {
        return data.get(key);
    }

    public void set(PlayerDataKey key, Object value) {
        Preconditions.checkArgument(key.getDefaultValue().getClass().isInstance(value), "value type needs to match the default objects type");
        data.put(key, key.change(value));
        Main.getInstance().getPlayerDataHandler().set(this.player, this);
    }
}
