package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import de.quatschvirus.essentialvirus.economy.Money;
import de.quatschvirus.essentialvirus.pos.PositionManager;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PosCommand extends PlayerCommand {

    private final PositionManager pM = Main.getInstance().getPositionManager();

    @Override
    public void function(Player player, Command command, String label, String[] args) {
        switch (args.length) {
            case 1 -> {
                if (!Objects.equals(args[0], "list")) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                            "\"/pos list\" zum Auflisten der Positionen\n" +
                            "\"/pos tp <Position>\" zum Teleportieren zu der Position\n" +
                            "\"/pos set <Position>\" zum Erstellen oder Bearbeiten einer Position\n" +
                            "\"/pos rm <Position>\" zum Löschen einer Position");
                    return;
                }
                StringBuilder out = new StringBuilder(Main.getPrefix() + "Gespeicherte Positionen:\n");
                for (String pos : pM.getIds()) {
                    out.append(pos);
                }
                player.sendMessage(out.toString());
            }
            case 2 -> {
                switch (args[0]) {
                    case "set" -> {
                        if (pM.getPosition(args[1]) != null) {
                            if (!pM.getPosition(args[1]).getOwner().equals(player.getUniqueId().toString())) {
                                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Diese Position gehört nicht dir!");
                                return;
                            }
                            Money.remove(player, 1);
                            pM.move(args[1], player.getLocation());
                        } else {
                            Money.remove(player, 2);
                            pM.add(args[1], player.getLocation(), player);
                        }
                    }
                    case "rm" -> {
                        if (pM.getPosition(args[1]) == null) {
                            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Diese Position existiert nicht!");
                            return;
                        }
                        if (!pM.getPosition(args[1]).getOwner().equals(player.getUniqueId().toString())) {
                            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Diese Position gehört nicht dir!");
                            return;
                        }
                        pM.remove(args[1]);
                        player.sendMessage(Main.getPrefix() + ChatColor.DARK_GREEN + "Die Postion \"" + args[1] + "\" wurde erflogreich gelöscht!");
                    }
                    case "tp" -> {
                        if (Money.get(player) < 2) {
                            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht genug Geld auf dem Konto!");
                            return;
                        }
                        if (pM.getPosition(args[1]) == null) {
                            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Diese Position existiert nicht!");
                            return;
                        }
                        Money.remove(player, 2);
                        Location destination = pM.getPosition(args[1]).getLoc();
                        player.teleport(destination);
                        player.playSound(player, Sound.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.AMBIENT, 0.95f, 1f);
                    }
                    default -> player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                            "\"/pos list\" zum Auflisten der Positionen\n" +
                            "\"/pos tp <Position>\" zum Teleportieren zu der Position\n" +
                            "\"/pos set <Position>\" zum Erstellen oder Bearbeiten einer Position\n" +
                            "\"/pos rm <Position>\" zum löschen einer Position");
                }
            }
            default -> player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                    "\"/pos list\" zum Auflisten der Positionen\n" +
                    "\"/pos tp <Position>\" zum Teleportieren zu der Position\n" +
                    "\"/pos set <Position>\" zum Erstellen oder Bearbeiten einer Position\n" +
                    "\"/pos rm <Position>\" zum löschen einer Position");
        }
    }
}
