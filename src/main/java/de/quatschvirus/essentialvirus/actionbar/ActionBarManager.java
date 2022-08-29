package de.quatschvirus.essentialvirus.actionbar;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActionBarManager {
    private final List<ActionBar> actionbars = new ArrayList<>();
    private final HashMap<String, Display> standardElements = new HashMap<>();

    public ActionBarManager() {
        standardElements.put("Timer", new TimerDisplay());
        standardElements.put("Balance", new BalanceDisplay());
        standardElements.put("Lag", new LagDisplay());
        standardElements.put("Time", new TimeDisplay());
    }

    public void addActionBar(Player player) {
        actionbars.add(new ActionBar(player, 20, 60, standardElements));
    }

    public ActionBar getActionBar(Player player) {
        for (ActionBar actionbar : actionbars) {
            if (actionbar.getPlayer().equals(player)) {
                return actionbar;
            }
        }
        return null;
    }
}
