package de.quatschvirus.essentialvirus.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class BlockBreakListener implements Listener {

    @SuppressWarnings({"SwitchStatementWithTooFewBranches", "ConstantConditions"})
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        switch (event.getBlock().getType()) {
            case SPAWNER: {
                if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_PICKAXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
                    if (event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        event.setExpToDrop(0);
                        ItemStack spawner = new ItemStack(Material.SPAWNER);
                        ItemMeta spawnerMeta = spawner.getItemMeta();
                        String name = ((CreatureSpawner) event.getBlock().getState()).getSpawnedType().name();
                        spawnerMeta.setLore(Collections.singletonList(ChatColor.RESET + "Spawnt: " + name));
                        spawner.setItemMeta(spawnerMeta);
                        event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), spawner);
                    }
                }
                break;
            }
        }
    }
}
