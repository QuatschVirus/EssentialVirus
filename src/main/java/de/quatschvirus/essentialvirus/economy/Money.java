package de.quatschvirus.essentialvirus.economy;

import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.entity.Player;

public class Money {

    public static int get(Player player) {
        return Integer.parseInt(Config.get("balance." + player.getUniqueId()).toString());
    }

    public static void add(Player player, int amount) {
        Config.set("balance." + player.getUniqueId(), Integer.parseInt(Config.get("balance." + player.getUniqueId()).toString()) + amount);
    }

    public static void remove(Player player, int amount) {
        Config.set("balance." + player.getUniqueId(), Integer.parseInt(Config.get("balance." + player.getUniqueId()).toString()) - amount);
    }
}
