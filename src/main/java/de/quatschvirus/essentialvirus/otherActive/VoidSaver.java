package de.quatschvirus.essentialvirus.otherActive;

import de.quatschvirus.essentialvirus.economy.Money;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class VoidSaver implements Listener {
    private final HashMap<Player, Location> lastSafe = new HashMap<>();
    private final ArrayList<Player> excluded = new ArrayList<>();

    @EventHandler
    public void onVoidDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID && event.getEntityType() == EntityType.PLAYER && !excluded.contains((Player) event.getEntity()) && Money.get((Player) event.getEntity()) > 0) {
            Player p = (Player) event.getEntity();
            Money.remove(p, 1);
            p.teleport(lastSafe.get(p).add(0, 1, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 5, 1, true, true));
            if (p.getHealth() + event.getDamage() > 20) {
                p.setHealth(20);
            } else {
                p.setHealth(p.getHealth() + event.getDamage());
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location newLocation = event.getFrom();
        if(newLocation.getWorld() != null && !newLocation.getWorld().getBlockAt(newLocation.subtract(0, 1, 0)).getType().isAir()) {
            lastSafe.put(event.getPlayer(), newLocation);
        }
    }

    public boolean excludePlayerToggle(Player p) {
        if (excluded.contains(p)) {
            excluded.remove(p);
        } else {
            excluded.add(p);
        }
        return excluded.contains(p);
    }
}
