package de.quatschvirus.essentialvirus.actionbar;

import de.quatschvirus.essentialvirus.Main;

import org.bukkit.entity.Player;

public class TimerDisplay extends Display {
    @Override
    public String display(Player player) {
        return Main.getInstance().getTimer().getTimerOutput();
    }
}
