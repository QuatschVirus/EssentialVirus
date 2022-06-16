package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class BlockBreakListener implements Listener {

    @SuppressWarnings({"ConstantConditions"})
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.SPAWNER)) {
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
        } else if (Main.lockable.contains(event.getBlock().getType())) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            if (Main.lockable.contains(block.getType())) {
                if (Main.shulkerboxes.contains(block.getType())) {
                    ShulkerBox state = (ShulkerBox) block.getState();
                    if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                        if (!player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) {
                            event.setCancelled(true);
                        } else {
                            state.setCustomName(null);
                            state.update();
                        }
                    }
                } else if (block.getType().equals(Material.BARREL)) {
                    Barrel state = (Barrel) block.getState();
                    if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                        if (!player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) {
                            event.setCancelled(true);
                        } else {
                            state.setCustomName(null);
                            state.update();
                        }
                    }
                } else {
                    Chest state = (Chest) block.getState();
                    if (state.getInventory().getHolder() instanceof DoubleChest) {
                        state = (Chest) ((DoubleChest) state.getInventory().getHolder()).getLeftSide();
                    }
                    if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                        if (!player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) {
                            event.setCancelled(true);
                        } else {
                            state.setCustomName(null);
                            state.update();
                        }
                    }
                }
            }
        }
    }
}
