package de.quatschvirus.essentialvirus.actionbar;

import org.bukkit.ChatColor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDisplay extends Display {
    public TimeDisplay() {
        super(2);
    }

    @Override
    public String display() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
        return ChatColor.DARK_AQUA + format.format(now) + " Uhr";
    }
}
