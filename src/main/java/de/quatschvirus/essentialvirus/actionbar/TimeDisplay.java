package de.quatschvirus.essentialvirus.actionbar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDisplay extends Display {
    @Override
    public String display(Player player) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
        return ChatColor.DARK_AQUA + format.format(now) + " Uhr";
    }
}
