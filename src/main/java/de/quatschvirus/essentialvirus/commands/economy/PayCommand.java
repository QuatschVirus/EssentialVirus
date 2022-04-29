package de.quatschvirus.essentialvirus.commands.economy;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import de.quatschvirus.essentialvirus.economy.Money;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PayCommand implements CommandExecutor, TabCompleter {

    private final YamlConfiguration config = Config.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern verwendet werden!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 2) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /pay <player to pay> <amount of money>");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + args[0] + " ist kein Spieler oder ist nicht online!");
            return true;
        }
        if (config.getInt("balance." + player.getUniqueId()) < 0 ) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dein Kontostand ist unter Null (" + config.get("balance." + player.getUniqueId()) + "€)!");
            return true;
        }
        if (config.getInt("balance." + player.getUniqueId()) - Integer.parseInt(args[1]) < 0 ) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dein Kontostand wäre nach der Zahlng unter Null (" + (config.getInt("balance." + player.getUniqueId()) - Integer.parseInt(args[1])) + "€)!");
            return true;
        }
        Money.remove(player, Integer.parseInt(args[1]));
        Money.add(target, Integer.parseInt(args[1]));
        player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hast " + args[1] + "€ an " + args[0] + " gezahlt");
        target.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hast " + args[1] + "€ von " + player.getDisplayName() + " bekommen");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) {
            return new ArrayList<>();
        } else {
            return null;
        }
    }
}
