package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PaidCommand;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class LockCommand extends PaidCommand {
    public LockCommand() {
        super(1);
    }

    @Override
    public void function(Player player, Command command, String label, String[] args) {
        Block block = player.getTargetBlock(null, 5);
        Chest chest = (Chest) block.getState();
        if (chest.getInventory().getHolder() instanceof DoubleChest) {
            chest = (Chest) ((DoubleChest) chest.getInventory().getHolder()).getLeftSide();
        }
        StringBuilder containerName = new StringBuilder();
        for (String word : block.getType().name().toLowerCase().split("_")) {
            containerName.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        chest.setCustomName(ChatColor.DARK_GREEN + "Gesperrte " + containerName + "von " + player.getName());
        chest.update();
    }

    @Override
    public boolean check(Player player, Command command, String label, String[] args) {
        return Main.lockable.contains(player.getTargetBlock(null, 5).getType());
    }
}
