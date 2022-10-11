package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PaidCommand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneratorCommand extends PaidCommand implements TabCompleter {

    public GeneratorCommand() {
        super(50);
    }

    @Override
    public void function(Player player, Command command, String label, String[] args) {
        Material mat = Material.getMaterial(args[0]);
        ItemStack item = new ItemStack(mat);
        item.setItemMeta(Main.getInstance().getGeneratorManager().getGeneratorMaterial(mat));
        player.getInventory().addItem(item);
        player.sendMessage(Main.getPrefix() + ChatColor.DARK_GREEN + "Du hast einen Generator erhalten!");
    }

    @Override
    public boolean check(Player player, Command command, String label, String[] args) {
        if (player.getInventory().firstEmpty() < 0) {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast keinen Platz im Inventar!");
            return false;
        }
        if (args.length != 1) {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du musst den Typen angeben!");
            return false;
        } else if(!Arrays.asList("SAND", "RED_SAND", "GRAVEL").contains(args[0])) {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du musst einen unterstützen Typen angeben! (Sand, Roter Sand oder Kies)");
            return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length < 2) {
            return new ArrayList<>(Arrays.asList("SAND", "RED_SAND", "GRAVEL"));
        }
        return new ArrayList<>();
    }
}
