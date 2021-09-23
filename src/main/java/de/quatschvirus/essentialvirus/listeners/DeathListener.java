package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.utils.Base64;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Config.set("latest_death_inventory." + player.getUniqueId(), Base64.itemStackArrayToBase64(player.getInventory().getContents()));
    }
}
