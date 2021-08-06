package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class SleepListener implements Listener {

    private int sleeping = 0;

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        switch (event.getBedEnterResult()) {
            case OK: {
                sleeping++;
                Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + event.getPlayer().getDisplayName() + " liegt im Bett! (" + sleeping + "/" + Bukkit.getOnlinePlayers().size() + ")");
                break;
            }
            case NOT_POSSIBLE_NOW: {
                event.getPlayer().sendMessage(Main.getPrefix() + ChatColor.RED + "Du kannst dur nachts oder wärend eines Gewitters schlafen!");
                break;
            }
            case NOT_SAFE: {
                event.getPlayer().sendMessage(Main.getPrefix() + ChatColor.RED + "Es ist ein Monster in der Nähe!");
                break;
            }
            case TOO_FAR_AWAY: {
                event.getPlayer().sendMessage(Main.getPrefix() + ChatColor.RED + "Das Bett ist zu weit weg!");
                break;
            }
        }
    }

    @EventHandler
    public void onWake(PlayerBedLeaveEvent event) {
        if (event.getPlayer().getWorld().getTime() >= 0 && event.getPlayer().getWorld().getTime() <= 12500 && !event.getPlayer().getWorld().isThundering()) {
            sleeping = 0;
            Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + "Guten Morgen!");
        } else {
            sleeping--;
            Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + event.getPlayer().getDisplayName() + " hat das Bett verlassen! (" + sleeping + "/" + Bukkit.getOnlinePlayers().size() + ")");
        }
    }
}
