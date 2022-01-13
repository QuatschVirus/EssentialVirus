package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class VoidSaverExcludeCommand extends PlayerCommand {
    @Override
    public void function(Player player, Command command, String label, String[] args) {
        if (Main.getInstance().getVoidSaver().excludePlayerToggle(player)) {
            player.sendMessage(Main.getPrefix() + "Der VoidSaver wurde für dich deaktiviert. Dies hält nur bis zum nächsten Reload des Plugins (Serverneustart etc.).");
        } else {
            player.sendMessage(Main.getPrefix() + "Der VoidSaver wurde für dich reaktiviert. Du wirst nun jedes mal, wenn du durch das Void Schaden kriegst, gegen 1€ auf deine letzte sichere Position teleportiert.");
        }
    }
}
