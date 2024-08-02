package de.quatschvirus.essentialvirus.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void OnEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
            if (event.getEntity().isInsideVehicle()) {
                if (event.getEntity().getVehicle().getType() == EntityType.PLAYER) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
