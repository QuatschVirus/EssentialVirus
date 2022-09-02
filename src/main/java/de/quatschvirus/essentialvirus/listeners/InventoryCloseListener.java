package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.blockInventory.BlockInventory;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inv = event.getInventory();
        BlockInventory bInv = Main.getInstance().getBlockInventoryManager().getInventory(inv);
        if (bInv != null) {
            Block gravestone = bInv.getBlock();
            if (gravestone.getType() == Material.SPRUCE_SIGN) {
                Sign state = (Sign) gravestone.getState();
                if (state.getLine(0).startsWith(ChatColor.WHITE.toString()) && state.getLine(1).startsWith(ChatColor.RED.toString())) {
                    if (bInv.getInventory().isEmpty()) {
                        String posString = state.getWorld().getName() + " " + state.getX() + " " + state.getY() + " " + state.getZ();
                        gravestone.setType(Material.valueOf(Config.getConfig().getString("deaths." + posString + ".replace")));
                        Config.getConfig().set("deaths." + posString, null);
                        Main.getInstance().getBlockInventoryManager().removeInventory(bInv);
                    }
                }
            }
        }
    }
}
