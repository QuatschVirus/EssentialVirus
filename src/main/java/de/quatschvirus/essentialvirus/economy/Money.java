package de.quatschvirus.essentialvirus.economy;

import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Money {

    private static final YamlConfiguration config = Config.getConfig();

    public static int get(Player player) {
        return Integer.parseInt(config.get("balance." + player.getUniqueId()).toString());
    }

    public static void add(Player player, int amount) {
        config.set("balance." + player.getUniqueId(), Integer.parseInt(config.get("balance." + player.getUniqueId()).toString()) + amount);
    }

    public static void remove(Player player, int amount) {
        config.set("balance." + player.getUniqueId(), Integer.parseInt(config.get("balance." + player.getUniqueId()).toString()) - amount);
    }
}
