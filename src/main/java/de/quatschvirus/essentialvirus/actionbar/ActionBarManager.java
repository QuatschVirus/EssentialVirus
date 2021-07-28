package de.quatschvirus.essentialvirus.actionbar;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ActionBarManager {
    private final List<ActionBar> actionbars = new ArrayList<>();

    public void addActionBar(Player player) {
        actionbars.add(new ActionBar(player, 20, 60));
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
