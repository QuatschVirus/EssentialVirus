package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import de.quatschvirus.essentialvirus.utils.Config;
import de.quatschvirus.essentialvirus.economy.Money;
import de.quatschvirus.essentialvirus.utils.PosSerial;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;

public class PosCommand extends PlayerCommand {

    private final YamlConfiguration config = Config.getConfig();

    @Override
    public void function(Player player, Command command, String label, String[] args) {
        if (!config.contains("pos.positions")) {
            config.set("pos.positions", new ArrayList<String>());
        }
        ArrayList<String> positions = (ArrayList<String>) config.getStringList("pos.positions");
        switch (args.length) {
            case 0: {
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                        "\"/pos list\" zum Auflisten der Positionen\n" +
                        "\"/pos tp <Position>\" zum Teleportieren zu der Position\n" +
                        "\"/pos set <Position>\" zum Erstellen oder Bearbeiten einer Position\n" +
                        "\"/pos del <Position>\" zum Löschen einer Position");
                return;
            }

            case 1: {
                if (!Objects.equals(args[0], "list")) {
                    player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                            "\"/pos list\" zum Auflisten der Positionen\n" +
                            "\"/pos tp <Position>\" zum Teleportieren zu der Position (Kostet dich 10€)\n" +
                            "\"/pos set <Position>\" zum Erstellen oder Bearbeiten einer Position\n" +
                            "\"/pos del <Position>\" zum Löschen einer Position");
                    return;
                }
                StringBuilder out = new StringBuilder(Main.getPrefix() + "Gespeicherte Positionen:\n");
                for (String pos : positions) {
                    out.append(pos);
                }
                player.sendMessage(out.toString());
                return;
            }

            case 2: {
                //noinspection SwitchStatementWithTooFewBranches
                switch (args[0]) {
                    case "tp":
                        if (Money.get(player) < 10) {
                            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht genug Geld auf dem Konto!");
                            return;
                        }
                        if (!positions.contains(args[1])) {
                            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Diese Position existiert nicht!");
                            return;
                        }
                        Money.remove(player, 10);
                        Location destination = PosSerial.fromSerial((ArrayList<String>) config.getStringList("pos." + args[1]));


                }
            }
        }

    }
}
