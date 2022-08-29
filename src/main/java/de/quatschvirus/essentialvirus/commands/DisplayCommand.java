package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DisplayCommand extends PlayerCommand implements TabCompleter {
    @Override
    public void function(Player player, Command command, String label, String[] args) {
        if (args.length == 1) {
            if ("toggle".equalsIgnoreCase(args[0])) {
                Main.getInstance().getActionBarManager().getActionBar(player).setWholeVisible(!Main.getInstance().getActionBarManager().getActionBar(player).isWholeVisible());
            } else if(Main.getInstance().getActionBarManager().getActionBar(player).getNames().contains(args[0])) {
                Main.getInstance().getActionBarManager().getActionBar(player).setVisible(args[0], !Main.getInstance().getActionBarManager().getActionBar(player).isVisible(args[0]));
            } else {
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                        "\"/display\" zum Umschalten\n" +
                        "\"/display toggle\" zum Umschalten aller Displays\n" +
                        "\"/display [Name]\" zum Umschalten des genannten Displays (siehe Vorschläge)");
            }
        } else {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                    "\"/display\" zum Umschalten\n" +
                    "\"/display on\" zum Anschalten\n" +
                    "\"/display off\" zum Ausschalten");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                ArrayList<String> out = new ArrayList<>(List.of("toggle"));
                out.addAll(Main.getInstance().getActionBarManager().getActionBar(player).getNames());
                return out;
            } else {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}
