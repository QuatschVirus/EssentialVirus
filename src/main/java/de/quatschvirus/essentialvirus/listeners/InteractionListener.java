package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.Objects;

public class InteractionListener implements Listener {
    @EventHandler
    public void onInteraction (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.hasBlock()) {
            if (Arrays.asList(
                Material.WOODEN_SHOVEL,
                Material.STONE_SHOVEL,
                Material.IRON_SHOVEL,
                Material.GOLDEN_SHOVEL,
                Material.DIAMOND_SHOVEL,
                Material.NETHERITE_SHOVEL,
                Material.WOODEN_PICKAXE,
                Material.STONE_PICKAXE,
                Material.IRON_PICKAXE,
                Material.GOLDEN_PICKAXE,
                Material.DIAMOND_PICKAXE,
                Material.NETHERITE_PICKAXE
            ).contains(player.getInventory().getItem(event.getHand()).getType())) {
                if (Main.getInstance().getGeneratorManager().check(event.getClickedBlock())) {
                    Main.getInstance().getGeneratorManager().destroy(event.getClickedBlock());
                }
            }
            if (event.hasBlock()) {
                Block block = event.getClickedBlock();
                //noinspection ConstantConditions
                if (Main.lockable.contains(block.getType())) {
                    if (Main.shulkerboxes.contains(block.getType())) {
                        ShulkerBox state = (ShulkerBox) block.getState();
                        if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                            if (!player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) {
                                event.setCancelled(true);
                            }
                        }
                    } else if (block.getType().equals(Material.BARREL)) {
                        Barrel state = (Barrel) block.getState();
                        if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                            if (!player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) {
                                event.setCancelled(true);
                            }
                        }
                    } else {
                        Chest state = (Chest) block.getState();
                        if (state.getInventory().getHolder() instanceof DoubleChest) {
                            state = (Chest) ((DoubleChest) state.getInventory().getHolder()).getLeftSide();
                        }
                        //noinspection ConstantConditions
                        if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                            if (!player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) {
                                event.setCancelled(true);
                            }
                        }
                    }
                }
                if (Main.getInstance().getBlockInventoryManager().getInventory(block) != null) {
                    if (Main.getInstance().getBlockInventoryManager().getInventory(block).getOwner() != null) {
                        if (Objects.equals(Main.getInstance().getBlockInventoryManager().getInventory(block).getOwner(), player.getUniqueId().toString())) {
                            player.openInventory(Main.getInstance().getBlockInventoryManager().getInventory(block).getInventory());
                        }
                    } else {
                        player.openInventory(Main.getInstance().getBlockInventoryManager().getInventory(block).getInventory());
                    }
                }
                if (block.getType() == Material.SPRUCE_SIGN) {
                    Sign state = (Sign) block.getState();
                    if (state.getLine(0).startsWith(ChatColor.WHITE.toString()) && state.getLine(1).startsWith(ChatColor.RED.toString())) {
                        String posString = state.getWorld().getName() + " " + state.getX() + " " + state.getY() + " " + state.getZ();
                        player.giveExp(Config.getConfig().getInt("deaths." + posString + ".xp"));
                        Config.getConfig().set("deaths." + posString + ".xp", 0);
                    }
                }
            }
        }
    }
}
