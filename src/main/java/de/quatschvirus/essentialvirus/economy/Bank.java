package de.quatschvirus.essentialvirus.economy;

import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Bank {
    private static final YamlConfiguration config = Config.getConfig();

    public static double get(String uuid) {
        return config.getDouble("balance." + uuid);
    }

    public static void add(String uuid, double amount) {
        config.set("bank." + uuid, config.getDouble("bank." + uuid) + amount);
    }

    public static void withdraw(Player player, int amount) {
        Money.add(player, amount);
        remove(player.getUniqueId().toString(), amount);
    }

    public static void remove(String uuid, double amount) {
        config.set("bank." + uuid, config.getDouble("bank." + uuid) - amount);
    }
}
