package de.quatschvirus.essentialvirus.commands.modCommands;

import de.quatschvirus.essentialvirus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IndevCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("essentialvirus.indev.set")) {
                sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dazu bist du nicht berechtigt!");
                return true;
            }
        }

        switch (args.length) {
            case 0 -> Main.getInstance().setIndev(!Main.getInstance().isIndev());
            case 1 -> {
                switch (args[0].toLowerCase()) {
                    case "on" -> Main.getInstance().setIndev(true);
                    case "off" -> Main.getInstance().setIndev(false);
                    default -> {
                        sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendnung:\n" +
                                "\"/indev\" zum Umschalten\n" +
                                "\"/indev on\" zum Anschalten\n" +
                                "\"/indef off\" zum Ausschalten");
                        return true;
                    }
                }
            }
            default -> {
                sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendnung:\n" +
                        "\"/indev\" zum Umschalten\n" +
                        "\"/indev on\" zum Anschalten\n" +
                        "\"/indef off\" zum Ausschalten");
                return true;
            }
        }
        return false;
    }
}
