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
            try {
                Config.set("chatcolor.valid", validColors);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0]) {
            case "dark_red": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_RED);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_RED + "dunkelrot (dark_red)" + ChatColor.RESET + "geändert");
                break;
            }

            case "red": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.RED);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.RED + "rot (red)" + ChatColor.RESET + "geändert");
                break;
            }

            case "gold": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.GOLD);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.GOLD + "gold (gold)" + ChatColor.RESET + "geändert");
                break;
            }

            case "yellow": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.YELLOW);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.YELLOW + "gelb (yellow)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_green": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_GREEN);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_GREEN + "dunkelgrün (dark_green)" + ChatColor.RESET + "geändert");
                break;
            }

            case "green": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.GREEN);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.GREEN + "grün (green)" + ChatColor.RESET + "geändert");
                break;
            }

            case "aqua": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.AQUA);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.AQUA + "cyan (aqua)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_aqua": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_AQUA);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_AQUA + "türkis (dark_aqua)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_blue": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_BLUE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_BLUE + "dunkel_blau (dark_blue)" + ChatColor.RESET + "geändert");
                break;
            }

            case "blue": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.BLUE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.BLUE + "Violet (blue)" + ChatColor.RESET + "geändert");
                break;
            }

            case "light_purple": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.LIGHT_PURPLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.LIGHT_PURPLE + "pink (light_purple)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_purple": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_PURPLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_PURPLE + "lila (dark_purple)" + ChatColor.RESET + "geändert");
                break;
            }

            case "white": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.WHITE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.WHITE + "weiß (white)" + ChatColor.RESET + "geändert");
                break;
            }

            case "gray": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.GRAY);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.GRAY + "hellgrau (gray)" + ChatColor.RESET + "geändert");
                break;
            }

            case "dark_gray": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.DARK_GRAY);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.DARK_GRAY + "dunkelgrau (dark_gray)" + ChatColor.RESET + "geändert");
                break;
            }

            case "black": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.BLACK);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(Main.getPrefix() + "Deine Chatfarbe wurde zu " + ChatColor.BLACK + "schwarz (black)" + ChatColor.RESET + "geändert");
                break;
            }

            case "default": {
                try {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.RESET);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
