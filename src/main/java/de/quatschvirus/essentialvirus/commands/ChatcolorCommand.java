package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatcolorCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(!Config.contains("chatcolor.valid")) {
            List<String> validColors = new ArrayList<>(Arrays.asList("dark_red", "red", "gold", "yellow", "dark_green", "green", "aqua", "dark_aqua", "dark_blue", "blue", "light_purple", "dark_purple", "white", "gray", "dark_gray", "black", "default"));
            Config.set("chatcolor.valid", validColors);
        }

        if (args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0]) {
            case "dark_red": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_RED.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_RED + "dunkelrot (dark_red)" + ChatColor.RESET + "geändert");
                break;
            }

            case "red": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.RED.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.RED + "rot (red)" + ChatColor.RESET + "geändert");
                break;
            }

            case "gold": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.GOLD.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.GOLD + "gold (gold)" + ChatColor.RESET + "geändert");
                break;
            }

            case "yellow": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.YELLOW.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.YELLOW + "gelb (yellow)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_green": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_GREEN.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_GREEN + "dunkelgrün (dark_green)" + ChatColor.RESET + "geändert");
                break;
            }

            case "green": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.GREEN.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.GREEN + "grün (green)" + ChatColor.RESET + "geändert");
                break;
            }

            case "aqua": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.AQUA.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.AQUA + "cyan (aqua)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_aqua": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_AQUA.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_AQUA + "türkis (dark_aqua)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_blue": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_BLUE.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_BLUE + "dunkel_blau (dark_blue)" + ChatColor.RESET + "geändert");
                break;
            }

            case "blue": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.BLUE.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.BLUE + "Violet (blue)" + ChatColor.RESET + "geändert");
                break;
            }

            case "light_purple": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.LIGHT_PURPLE.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.LIGHT_PURPLE + "pink (light_purple)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_purple": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_PURPLE.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_PURPLE + "lila (dark_purple)" + ChatColor.RESET + "geändert");
                break;
            }

            case "white": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.WHITE.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.WHITE + "weiß (white)" + ChatColor.RESET + "geändert");
                break;
            }

            case "gray": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.GRAY.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.GRAY + "hellgrau (gray)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_gray": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_GRAY.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_GRAY + "dunkelgrau (dark_gray)" + ChatColor.RESET + "geändert");
                break;
            }

            case "black": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.BLACK.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.BLACK + "schwarz (black)" + ChatColor.RESET + "geändert");
                break;
            }

            case "default": {
                Config.set("chatcolor." + player.getUniqueId(), ChatColor.RESET.toString());
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zurückgesetzt");
                break;
            }

            default:
                sendUsage(sender);
                break;
        }

        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n"
                + ChatColor.DARK_RED + "dark_red\n"
                + ChatColor.RED + "red\n"
                + ChatColor.GOLD + "gold\n"
                + ChatColor.YELLOW + "yellow\n"
                + ChatColor.DARK_GREEN + "dark_green\n"
                + ChatColor.GREEN + "green\n"
                + ChatColor.AQUA + "aqua\n"
                + ChatColor.DARK_AQUA + "dark_aqua\n"
                + ChatColor.DARK_BLUE + "dark_blue\n"
                + ChatColor.BLUE + "blue\n"
                + ChatColor.LIGHT_PURPLE + "light_purple\n"
                + ChatColor.DARK_PURPLE + "dark_purple\n"
                + ChatColor.WHITE + "white\n"
                + ChatColor.GRAY + "gray\n"
                + ChatColor.DARK_GRAY + "dark_gray\n"
                + ChatColor.BLACK + "black\n"
                + ChatColor.RESET + "default");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Config.getStringList("chatcolor.valid");
    }
}
