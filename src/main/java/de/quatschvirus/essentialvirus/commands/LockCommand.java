package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PaidCommand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class LockCommand extends PaidCommand {
    public LockCommand() {
        super(1);
    }

    @Override
    public void function(Player player, Command command, String label, String[] args) {
        Block block = player.getTargetBlock(null, 5);
        if (Main.lockable.contains(block.getType())) {
            if (Main.shulkerboxes.contains(block.getType())) {
                ShulkerBox state = (ShulkerBox) block.getState();
                state.setCustomName(ChatColor.DARK_GREEN + "Gesperrte Shulkerkiste von " + player.getName());
                state.update();
            } else if (block.getType().equals(Material.BARREL)) {
                Barrel state = (Barrel) block.getState();
                state.setCustomName(ChatColor.DARK_GREEN + "Gesperrtes Fass von " + player.getName());
                state.update();
            } else {
                Chest state = (Chest) block.getState();
                if (state.getInventory().getHolder() instanceof DoubleChest) {
                    state = (Chest) ((DoubleChest) state.getInventory().getHolder()).getLeftSide();
                }
                state.setCustomName(ChatColor.DARK_GREEN + "Gesperrte Kiste von " + player.getName());
                state.update();
            }
        }
    }

    @Override
    public boolean check(Player player, Command command, String label, String[] args) {
        return Main.lockable.contains(player.getTargetBlock(null, 5).getType());
    }
}
