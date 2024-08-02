package de.quatschvirus.essentialvirus.custom.materials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CustomItemHandler implements Listener {
    @EventHandler
    public void onCraftPrepare(CraftItemEvent e) {
        System.out.println(e.getInventory().getClass());
    }
}
