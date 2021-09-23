package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PaidCommand;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CorpseCommand extends PaidCommand {
    public CorpseCommand() {
        super(1);
    }

    @Override
    public void function(Player player, Command command, String label, String[] args) {

    }

    @Override
    public boolean check(Player player, Command command, String label, String[] args) {
        if (Config.contains("latest_death_inventory." + player.getUniqueId())) {
            if (Config.get("latest_death_inventory." + player.getUniqueId()) == null) {
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast das nach deinem letzten Tot bereits getan!");
                return false;
            }
            return true;
        } else {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du bist noch nicht gestorben! (Mach weiter so!)");
            return false;
        }
    }
}
