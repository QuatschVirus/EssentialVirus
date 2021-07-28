package de.quatschvirus.essentialvirus.actionbar;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BalanceDisplay {
    private void display() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Main.getInstance().getActionBarManager().getActionBar(player).set(1, ChatColor.GREEN + "Kontostand: " + Config.get("balance." + player.getUniqueId()) + "€");
        }
    }

    private void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                display();
            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }

    public BalanceDisplay() {
        start();
    }
}
