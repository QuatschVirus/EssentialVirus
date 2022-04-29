package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OldPosCommand implements CommandExecutor, TabCompleter {

    private final YamlConfiguration config = Config.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern benutzt werden!");
            return true;
        }
        Player player = (Player) sender;

        switch (args[0]) {
            case "list": {
                StringBuilder out = new StringBuilder(Main.getPrefix() + "Deine Marker:\n");
                for (String position : config.getStringList("pos.positions." + player.getUniqueId())) {
                    out.append(position).append("\n");
                }
                player.sendMessage(out.toString());
                break;
            }

            case "add": {
                if (!(args.length == 2)) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /pos add <name>");
                    return true;
                }
                config.set("pos." + player.getUniqueId() + args[1], new ArrayList<>(Arrays.asList(String.valueOf((int) player.getLocation().getX()), String.valueOf((int) player.getLocation().getY()), String.valueOf((int) player.getLocation().getZ()))));
                List<String> positions = config.getStringList("pos.positions." + player.getUniqueId());
                positions.add(args[1]);
                config.set("pos.positons." + player.getUniqueId(), positions);
                player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Die Position " + args[0] + " wurde auf " + player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ() + " gesetzt.");
                break;
            }

            case "get": {
                if (!(args.length == 2)) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /pos get <name>");
                    return true;
                }
                if (config.contains("pos." + player.getUniqueId() + args[1])) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Marker " + args[1] + " existiert nicht.");
                    return true;
                }
                if (config.get("pos." + player.getUniqueId() + args[1]) == null) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Marker " + args[1] + " existiert nicht.");
                    return true;
                }
                ArrayList<String> position = (ArrayList<String>) config.getStringList("pos." + player.getUniqueId() + args[1]);
                player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Der Marker " + args[1] + " befindet sich auf " + position.get(0) + position.get(1) + position.get(2) + ".");
                break;
            }

            case "remove": {
                if (!(args.length == 2)) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /pos remove <name>");
                    return true;
                }
                if (config.contains("pos." + player.getUniqueId() + args[1])) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Marker " + args[1] + " existiert nicht.");
                    return true;
                }
                config.set("pos." + player.getUniqueId() + args[1], null);
                List<String> positions = config.getStringList("pos.positions." + player.getUniqueId());
                positions.remove(args[1]);
                config.set("pos.positons." + player.getUniqueId(), positions);
                break;
            }

            default:
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: \n" +
                        "/pos list -> Listet alle deine Marker auf\n" +
                        "/pos add <name> -> Fügt einen Marker mit dem gegebenen Namen an der aktuellen Position hinzu\n" +
                        "/pos get <name> -> Zeigt die Koordinaten eines Markers an\n" +
                        "/pos remove <name> -> Entfernt den Marker");
                break;
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            return new ArrayList<>(Arrays.asList("list", "add", "remove"));
        } else if (args.length == 1 && args[0].equals("list")) {
            return new ArrayList<>();
        } else if (args.length == 1 && (args[0].equals("add") || args[0].equals("remove") || args[0].equals("get"))) {
            return config.getStringList("pos.positions" + player.getUniqueId());
        } else {
            return new ArrayList<>();
        }
    }
}
