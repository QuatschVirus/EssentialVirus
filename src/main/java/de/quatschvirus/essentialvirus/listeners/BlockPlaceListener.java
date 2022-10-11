package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class BlockPlaceListener implements Listener {

    @SuppressWarnings({"ConstantConditions"})
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        switch (event.getBlock().getType()) {
            case SPAWNER: {
                String name = event.getItemInHand().getItemMeta().getLore().get(0).substring(8);
                CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
                spawner.setSpawnedType(EntityType.valueOf(name));
                spawner.update();
                break;
            }

            case TNT: {
                if (Config.getConfig().contains("TNTBlock")) {
                    ArrayList<String> blockedPlayers = (ArrayList<String>) Config.getConfig().getStringList("TNTBlock");
                    if (blockedPlayers.contains(event.getPlayer().getUniqueId().toString())) {
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(Main.getPrefix() + ChatColor.RED + "Es ist dir nicht gestattet, TNT zu platzieren!");
                        Bukkit.getLogger().fine(event.getPlayer().getUniqueId() + " hat versucht, TNT zu platzieren!");
                    }
                }
            }

            case SAND, RED_SAND, GRAVEL: {
                if (event.getItemInHand().getEnchantments().containsKey(Enchantment.KNOCKBACK)) {
                    Main.getInstance().getGeneratorManager().add(event.getBlockPlaced());
                }
            }
        }
    }
}
