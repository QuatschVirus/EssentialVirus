package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.blockInventory.BlockInventory;
import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String posString = player.getLocation().getWorld().getName() + " " +  player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ();
        player.sendMessage("You died at: " + posString.substring(posString.indexOf(" ") + 1));
        Config.getConfig().set("deaths." + posString + ".player", player.getUniqueId().toString());
        Block gravestone = player.getLocation().getBlock();
        createBInv(gravestone, event, player);
        event.getDrops().clear();
        Config.getConfig().set("deaths." + posString + ".replace", gravestone.getType().name());
        Config.getConfig().set("deaths." + posString + ".xp", event.getDroppedExp());
        event.setDroppedExp(0);
        gravestone.setType(Material.SPRUCE_SIGN);
        Sign data = (Sign) gravestone.getState();
        data.setLine(0, ChatColor.WHITE + "Hier ruht:");
        data.setLine(1, ChatColor.RED + player.getDisplayName());
        data.setLine(2, ChatColor.WHITE + "†");
        data.setGlowingText(true);
        data.setEditable(false);
        data.update();
    }

    private void createBInv(Block block, PlayerDeathEvent event, Player player) {
        if (Main.getInstance().getBlockInventoryManager().getInventory(block) == null) {
            Main.getInstance().getBlockInventoryManager().setInventory(new BlockInventory(block, 45, ChatColor.DARK_GRAY + "Grabstein", event.getDrops().toArray(new ItemStack[]{}), player.getUniqueId().toString(), 1));
        } else {
            createBInv(block.getRelative(BlockFace.UP), event, player);
        }
    }
}
