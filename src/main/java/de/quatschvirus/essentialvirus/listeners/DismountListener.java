package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.utils.ScoreboardTags;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;

public class DismountListener implements Listener {
    @EventHandler
    public void OnDismount(EntityDismountEvent event) {
        Entity dismounted = event.getDismounted();
        if (event.getEntity().getType() == EntityType.PLAYER) {
            if (dismounted.getType() == EntityType.ARMOR_STAND && dismounted.getScoreboardTags().contains(ScoreboardTags.Seat)) {
                dismounted.remove();
            }
        }
    }
}
