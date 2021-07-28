package de.quatschvirus.essentialvirus.actionbar;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Display {
    private int index = 0;

    public abstract String display();

    private void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Main.getInstance().getActionBarManager().getActionBar(player).set(index ,display());
                }
            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }

    public Display(int index) {
        this.index = index;
        start();
    }
}
