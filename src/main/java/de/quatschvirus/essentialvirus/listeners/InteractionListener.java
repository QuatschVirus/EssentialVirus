package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

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
            if (Main.lockable.contains(event.getClickedBlock().getType())) {
                Chest state = (Chest) event.getClickedBlock().getState();
                if (state.getInventory().getHolder() instanceof DoubleChest) {
                    state = (Chest) ((DoubleChest) state.getInventory().getHolder()).getLeftSide();
                }
                if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt") && !player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
