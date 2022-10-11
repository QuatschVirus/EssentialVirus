package de.quatschvirus.essentialvirus.economy;

import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Money {

    private static final YamlConfiguration config = Config.getConfig();

    public static int get(Player player) {
        return config.getInt("balance." + player.getUniqueId());
    }

    public static void add(Player player, int amount) {
        config.set("balance." + player.getUniqueId(), config.getInt("balance." + player.getUniqueId()) + amount);
    }

    public static void remove(Player player, int amount) {
        config.set("balance." + player.getUniqueId(), config.getInt("balance." + player.getUniqueId()) - amount);
    }
}
