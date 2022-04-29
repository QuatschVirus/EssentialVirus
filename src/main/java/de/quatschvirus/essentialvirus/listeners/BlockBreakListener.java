package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
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
            Chest state = (Chest) event.getBlock().getState();
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
