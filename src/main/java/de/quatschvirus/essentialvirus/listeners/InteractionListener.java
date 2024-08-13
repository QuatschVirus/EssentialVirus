package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;

import de.quatschvirus.essentialvirus.utils.Directions;
import de.quatschvirus.essentialvirus.utils.ScoreboardTags;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class InteractionListener implements Listener {
    @EventHandler
    public void onInteraction (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.hasBlock()) {
            Block block = Objects.requireNonNull(event.getClickedBlock());
            ItemStack item = player.getInventory().getItem(event.getHand());
            if (item != null) {
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
                ).contains(item.getType())) {
                    if (Main.getInstance().getGeneratorManager().check(block)) {
                        Main.getInstance().getGeneratorManager().destroy(block);
                    }
                }
            }
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
                SignSide side = state.getSide(Side.FRONT);
                if (side.getLine(0).startsWith(ChatColor.WHITE.toString()) && side.getLine(1).startsWith(ChatColor.RED.toString())) { // TODO: Rework all of this bullshit and similar to PersistentDataContainer shizz
                    String posString = state.getWorld().getName() + " " + state.getX() + " " + state.getY() + " " + state.getZ();
                    player.giveExp(Config.getConfig().getInt("deaths." + posString + ".xp"));
                    Config.getConfig().set("deaths." + posString + ".xp", 0);
                }
            }
            if (Tag.STAIRS.isTagged(block.getType()) && !player.isSneaking()) {
                Stairs s = (Stairs) block.getBlockData();
                if (s.getHalf() == Bisected.Half.TOP) return;
                Location l = block.getLocation().add(0.5, 0.5, 0.5);
                if (l.getWorld() == null) return;
                ArmorStand stand = l.getWorld().spawn(l, ArmorStand.class);
                stand.addScoreboardTag(ScoreboardTags.Seat);
                stand.setRotation(Directions.ConvertStairsToFaceRotation(s), 0);
                stand.setMarker(true);
                stand.setSmall(true);
                stand.setSilent(true);
                stand.setVisible(false);
                stand.setPersistent(true);
                stand.setCollidable(false);
                stand.setGravity(false);
                stand.setArms(false);
                stand.setBasePlate(false);
                stand.setInvulnerable(true);
                stand.setAI(false);
                stand.eject();

                player.leaveVehicle();
                stand.addPassenger(player);
            }
            if (Tag.SLABS.isTagged(block.getType()) && !player.isSneaking()) {
                Slab s = (Slab) block.getBlockData();
                if (s.getType() != Slab.Type.BOTTOM) return;
                Location l = block.getLocation().add(0.5, 0.5, 0.5);
                if (l.getWorld() == null) return;
                ArmorStand stand = l.getWorld().spawn(l, ArmorStand.class);
                stand.addScoreboardTag(ScoreboardTags.Seat);
                stand.setRotation(Directions.SnapToCardinal(player.getLocation().getYaw() + 180F), 0);
                stand.setMarker(true);
                stand.setSmall(true);
                stand.setSilent(true);
                stand.setVisible(false);
                stand.setPersistent(true);
                stand.setCollidable(false);
                stand.setGravity(false);
                stand.setArms(false);
                stand.setBasePlate(false);
                stand.setInvulnerable(true);
                stand.setAI(false);
                stand.eject();

                player.leaveVehicle();
                stand.addPassenger(player);
            }
            if (player.isSneaking() && !player.getPassengers().isEmpty()) {
                if (player.getPassengers().get(0) instanceof LivingEntity) {
                    Block b = block.getRelative(event.getBlockFace());
                    LivingEntity entity = (LivingEntity) player.getPassengers().get(0);
                    if (b.isPassable()) {
                        player.removePassenger(entity);
                        entity.teleport(b.getLocation().add(0.5, 0.2, 0.5));
                    }
                }
            }
        }

        // TODO: Move to PlayerItemConsumeEvent
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand().getType() == Material.GLOW_BERRIES) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 1, false, false));
            }
        }
    }

    @EventHandler
    public void OnEntityInteraction(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (player.isSneaking()) {
            if (event.getRightClicked() instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) event.getRightClicked();
                if (player.getPassengers().isEmpty()) {
                    if (entity.getBoundingBox().getVolume() <= 2.3) {
                        player.addPassenger(entity);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
