package de.quatschvirus.essentialvirus.actionbar;

import de.quatschvirus.essentialvirus.utils.Lag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LagDisplay extends Display{
    @Override
    public String display(Player player) {
        double tps = Lag.getTPS();
        double percentage = (tps / 20) * 100;
        if (percentage > 75) {
            return ChatColor.GREEN + "TPS: " + Math.round(tps) + " | Leistung: " + String.valueOf(percentage).substring(0, 4) + "%";
        } else if (percentage > 50) {
            return ChatColor.GOLD + "TPS: " + Math.round(tps) + " | Leistung: " + String.valueOf(percentage).substring(0, 4) + "%";
        } else {
            return ChatColor.RED + "TPS: " + Math.round(tps) + " | Leistung: " + String.valueOf(percentage).substring(0, 4) + "%";
        }
    }
}
