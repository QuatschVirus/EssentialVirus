package de.quatschvirus.essentialvirus.listeners;

import com.google.common.base.Enums;
import de.quatschvirus.essentialvirus.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public class ProjectileHitListener implements Listener {
    private final Map<EntityType, Double> chances = Map.of(

    );

    private final Random random = new Random();

    @EventHandler
    public void OnProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() != null) {
            if (event.getHitEntity() instanceof LivingEntity) {
                LivingEntity hit = (LivingEntity) event.getHitEntity();
                if (event.getEntity().getType() == EntityType.EGG) {
                    AttributeInstance maxHealthAttribute = hit.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    if (maxHealthAttribute != null) {
                        double maxHealth = maxHealthAttribute.getValue();
                        double baseChance = Math.max(10d / maxHealth, 1d);
                        double healthPercentage = hit.getHealth() / maxHealth;
                        double chance = chances.getOrDefault(hit.getType(), baseChance + ((1d - baseChance) / 2d) * (1 - healthPercentage));

                        if (random.nextDouble() <= chance) {
                            String eggName = hit.getType().name() + "_SPAWN_EGG";
                            if (Enums.getIfPresent(Material.class, eggName).isPresent()) {
                                Location spawn = event.getEntity().getLocation();
                                if (spawn.getWorld() == null) return;
                                hit.remove();
                                spawn.getWorld().dropItemNaturally(spawn, new ItemStack(Material.valueOf(eggName)));
                            }
                        }
                    }
                }
            }
        }
    }
}
