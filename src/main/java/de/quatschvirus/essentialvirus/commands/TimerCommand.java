package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.timer.Timer;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            sendUsage(sender);
            return true;
        }

        switch(args[0].toLowerCase()) {
            case "resume": {
                Timer timer = Main.getInstance().getTimer();

                if (timer.isRunning()) {
                    sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Timer läuft bereits!");
                    break;
                }

                timer.setRunning(true);
                sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Der Timer wurde gestartet!");

                break;
            }
            case "pause": {
                Timer timer = Main.getInstance().getTimer();

                if (!timer.isRunning()) {
                    sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Timer läuft nicht!");
                    break;
                }

                timer.setRunning(false);
                sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Der Timer wurde gestoppt!");

                break;
            }
            case "time": {
                Timer timer = Main.getInstance().getTimer();

                if(args.length != 2) {
                    sendUsage(sender);
                    return true;
                }

                try {
                    timer.setTime(Integer.parseInt(args[1]));
                } catch (NumberFormatException e) {
                    sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Bitte gebe eine Zahl als Zeit an!");
                    return true;
                }
                sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Die Zeit wurde auf " + args[1] + "gesetzt!");

                break;
            }
            case "reset": {
                Timer timer = Main.getInstance().getTimer();

                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Der Timer wurde zurückgesetzt!");

                break;
            }
            case "save": {
                Timer timer = Main.getInstance().getTimer();

                timer.setRunning(false);
                try {
                    Config.set("timer.time", timer.getTime());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                timer.setRunning(true);
                sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Die Zeit wurde gespeichert!");

                break;
            }
            default:
                sendUsage(sender);
                break;
        }


        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(Main.getPrefix() + ChatColor.GRAY + "Verwendung:\n" + ChatColor.GREEN + "/timer resume\n/timer pause\n/timer time <Zeit>\n/timer reset");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return new ArrayList<String>(Arrays.asList("resume", "pause", "time", "reset", "save"));
        } else {
            return new ArrayList<String>();
        }
    }
}
