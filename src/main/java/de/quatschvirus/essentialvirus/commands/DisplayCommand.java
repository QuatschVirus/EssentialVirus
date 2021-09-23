package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayCommand extends PlayerCommand implements TabCompleter {
    @Override
    public void function(Player player, Command command, String label, String[] args) {
        switch (args.length) {
            case 0: {
                Main.getInstance().getActionBarManager().getActionBar(player).setVisible(!Main.getInstance().getActionBarManager().getActionBar(player).isVisible());
                if (Main.getInstance().getActionBarManager().getActionBar(player).isVisible()) {
                    player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hust das Display wieder sichtbar gemacht!");
                } else {
                    player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hust das Display unsichtbar gemacht!");
                }
                break;
            }

            case 1: {
                switch (args[0].toLowerCase()) {
                    case "on": {
                        Main.getInstance().getActionBarManager().getActionBar(player).setVisible(true);
                        player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hust das Display wieder sichtbar gemacht!");
                        break;
                    }

                    case "off": {
                        Main.getInstance().getActionBarManager().getActionBar(player).setVisible(false);
                        player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hust das Display unsichtbar gemacht!");
                        break;
                    }

                    default: {
                        player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                                "\"/display\" zum Umschalten\n" +
                                "\"/display on\" zum Anschalten\n" +
                                "\"/display off\" zum Ausschalten");
                        return;
                    }
                }
                break;
            }

            default: {
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                        "\"/display\" zum Umschalten\n" +
                        "\"/display on\" zum Anschalten\n" +
                        "\"/display off\" zum Ausschalten");
            }

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(Arrays.asList("on", "off"));
        } else {
            return new ArrayList<>();
        }
    }
}
