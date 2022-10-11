package de.quatschvirus.essentialvirus.commands.modCommands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import de.quatschvirus.essentialvirus.economy.Money;
import de.quatschvirus.essentialvirus.utils.ExperienceManager;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class RepairCommand extends PlayerCommand {
    @Override
    public void function(Player player, Command command, String label, String[] args) {
        ItemStack i = player.getInventory().getItemInMainHand();
        if (i.getItemMeta() instanceof Damageable) {
            Damageable d = (Damageable) i.getItemMeta();
            if (d.hasDamage()) {
                if (!i.getEnchantments().containsKey(Enchantment.MENDING)) {
                    if (Money.get(player) < 5) {
                        player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht genug Geld auf dem Konto!");
                        return;
                    }
                    Money.remove(player, 5);
                }
                int cost = (int) Math.floor(d.getDamage() / 2.0);
                int tXp = ExperienceManager.getTotalExperience(player);
                if (tXp - cost < 0) {
                    d.setDamage(d.getDamage() - (tXp * 2));
                    ExperienceManager.setTotalExperience(player, 0);
                } else {
                    d.setDamage(0);
                    ExperienceManager.setTotalExperience(player,tXp - cost);
                }
                i.setItemMeta(d);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.5f, 0.95f);

            }
        }
    }
}
