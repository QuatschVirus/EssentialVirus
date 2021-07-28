package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage(Main.getPrefix() + ChatColor.RED + player.getName() + ChatColor.YELLOW + " ist aufgetaucht.");

        if (!Config.contains("balance." + player.getUniqueId())) {
            try {
                Config.set("balance." + player.getUniqueId(), 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Main.getInstance().getActionBarManager().addActionBar(player);
        //new TestScoreboard(player);
    }

}
