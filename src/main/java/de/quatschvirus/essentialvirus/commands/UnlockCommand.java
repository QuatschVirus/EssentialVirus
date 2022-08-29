package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import org.bukkit.ChatColor;
import org.bukkit.block.*;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class UnlockCommand extends PlayerCommand {
    @Override
    public void function(Player player, Command command, String label, String[] args) {
        Block block = player.getTargetBlock(null, 5);
        if (Main.lockable.contains(block.getType())) {
            Container state = (Container) block.getState();
             if (state.getCustomName() != null && state.getCustomName().contains(ChatColor.DARK_GREEN + "Gesperrt")) {
                 if ((player.getName().equals(state.getCustomName().split(" ")[state.getCustomName().split(" ").length - 1])) || player.isOp()) {
                     state.setCustomName(null);
                     state.update();
                 } else {
                     player.sendMessage(Main.getPrefix() + ChatColor.RED + "Das ist nicht deins!");
                 }
             }
        }
    }
}
