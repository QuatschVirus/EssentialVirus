package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.ChatColor;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        InventoryHolder holder = event.getSource().getHolder();
        if ((holder instanceof BlockState && Main.lockable.contains(((BlockState) holder).getType()) && ((Chest) holder).getCustomName() != null && ((Chest) holder).getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) || (holder instanceof DoubleChest && ((Chest)((DoubleChest) holder).getLeftSide()).getCustomName() != null && ((Chest)((DoubleChest) holder).getLeftSide()).getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt"))) {
            event.setCancelled(true);
        }
    }
}
