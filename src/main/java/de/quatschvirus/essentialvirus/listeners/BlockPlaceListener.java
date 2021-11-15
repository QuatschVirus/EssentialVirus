package de.quatschvirus.essentialvirus.listeners;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @SuppressWarnings({"SwitchStatementWithTooFewBranches", "ConstantConditions"})
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        switch (event.getBlock().getType()) {
            case SPAWNER: {
                switch (event.getItemInHand().getItemMeta().getDisplayName()) {
                    case "Zombie Spawner": {
                        CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
                        spawner.setSpawnedType(EntityType.ZOMBIE);
                        spawner.update();
                        break;
                    }
                }
            }
        }
    }
}
