package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        InventoryHolder holder = event.getSource().getHolder();
        String customName;
        if ((holder instanceof BlockState && Main.lockable.contains(((BlockState) holder).getType()))) {
            if (Main.shulkerboxes.contains(((BlockState) holder).getType())) {
                customName = ((ShulkerBox) holder).getCustomName();
            } else if (((BlockState) holder).getType().equals(Material.BARREL)) {
                customName = ((Barrel) holder).getCustomName();
            } else if (holder instanceof DoubleChest) {
                customName = ((Chest) ((DoubleChest) holder).getLeftSide()).getCustomName();
            } else {
                customName = ((Chest) holder).getCustomName();
            }
            if (customName != null && customName.contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                event.setCancelled(true);
            }
        }
    }
}
