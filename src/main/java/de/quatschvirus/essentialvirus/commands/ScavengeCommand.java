package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PaidCommand;
import de.quatschvirus.essentialvirus.types.AetherLootTable;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ScavengeCommand extends PaidCommand {
    public ScavengeCommand() {
        super(5);
    }

    @Override
    public void function(Player player, Command command, String label, String[] args) {
        player.sendMessage(Main.getPrefix() + "Das Dimensionsfischen hat begonnen, es wird ca. eine Minute dauern");
        player.playSound(player, Sound.BLOCK_PORTAL_TRIGGER, SoundCategory.AMBIENT, 0.5f, 1.5f);
        Random random = new Random();
        long delay = 1200 + (random.nextBoolean() ? random.nextInt(600) : -random.nextInt(600));
        new BukkitRunnable () {
            public void run() {
                ItemStack item = (ItemStack) new AetherLootTable().populateLoot(random, new LootContext.Builder(player.getLocation()).lootingModifier(0).build()).toArray()[0];
                if (item == null) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Das Dimensionsfischen war nicht erfolgreich!");
                    return;
                }
                player.getInventory().addItem(item);
                String itemTypeName = item.getType().name().toLowerCase().replace('_', ' ');
                StringBuilder itemName = new StringBuilder();
                for (String word : itemTypeName.split(" ")) {
                    itemName.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
                }
                player.sendMessage(Main.getPrefix() + "Das Dimensionsfischen war erfolgreich! Du hast " + item.getAmount() + " " + itemName + "erhalten!");
                player.playSound(player, Sound.BLOCK_PORTAL_TRAVEL, SoundCategory.AMBIENT, 0.5f, 1.5f);
            }
        }.runTaskLater(Main.getInstance(), delay);
    }

    @Override
    public boolean check(Player player, Command command, String label, String[] args) {
        return player.getInventory().firstEmpty() >= 0;
    }
}
