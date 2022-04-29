package de.quatschvirus.essentialvirus.logging;

import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class LogListeners implements Listener {

    private final YamlConfiguration config = Config.getConfig();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Log.playercheck(event.getPlayer())) {
            ArrayList<String> listen;
            if (config.contains("log.listenPlace")) {
                listen = (ArrayList<String>) config.getStringList("log.listenPlace");
            } else {
                config.set("log.listenPlace", new ArrayList<>(Arrays.asList("TNT", "BEACON")));
                listen = new ArrayList<>(Arrays.asList("TNT", "BEACON"));
            }
            if (listen.contains(event.getBlock().getType().name())) {
                Log.write(event.getPlayer().getName() + " (" + event.getPlayer().getUniqueId() + ") at " + event.getPlayer().getLocation() + " placed block " + event.getBlock().getType().name() + " at " + event.getBlock().getLocation());
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (Log.playercheck(event.getPlayer())) {
            ArrayList<String> listen;
            if (config.contains("log.listenBreak")) {
                listen = (ArrayList<String>) config.getStringList("log.listenBreak");
            } else {
                config.set("log.listenBreak", new ArrayList<>(Arrays.asList("TNT", "BEACON")));
                listen = new ArrayList<>(Arrays.asList("TNT", "BEACON", "DIAMOND_BLOCK", "NETHERITE_BLOCK", "OBSIDIAN"));
            }
            if (listen.contains(event.getBlock().getType().name())) {
                Log.write(event.getPlayer().getName() + " (" + event.getPlayer().getUniqueId() + ") at " + event.getPlayer().getLocation() + " broke block " + event.getBlock().getType().name() + " at " + event.getBlock().getLocation());
            }
        }
    }

    @EventHandler
    public void onEntityDamaged(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (Log.playercheck(player)) {
                Log.write(player.getName() + " (" + player.getUniqueId() + ") at " + player.getLocation() + " dealt " + event.getDamage() + " damage (" + event.getCause() + ") to " + (event.getEntity().getCustomName() == null ? "Entity" : event.getEntity().getCustomName()) + " (" + event.getEntity().getType() + ", " + event.getEntity().getLocation() + ")");
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            if (Log.playercheck(player)) {
                Log.write(player.getName() + " (" + player.getUniqueId() + ") at " + player.getLocation() + " killed " + (event.getEntity().getCustomName() == null ? "Entity" : event.getEntity().getCustomName()) + " (" + event.getEntity().getType() + ", " + event.getEntity().getLocation() + ") with " + Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause());
            }
        }
    }
}
