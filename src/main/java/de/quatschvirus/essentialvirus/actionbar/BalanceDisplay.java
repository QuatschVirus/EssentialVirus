package de.quatschvirus.essentialvirus.actionbar;

import de.quatschvirus.essentialvirus.economy.Money;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BalanceDisplay extends Display {
    @Override
    public String display(Player player) {
        return ChatColor.GREEN + "Kontostand: " + Money.get(player) + "€";
    }
}
