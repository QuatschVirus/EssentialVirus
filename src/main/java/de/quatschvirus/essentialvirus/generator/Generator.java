package de.quatschvirus.essentialvirus.generator;

import de.quatschvirus.essentialvirus.Main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Generator {
    private final Material type;
    private final Block block;
    private final int genTime;

    private final BukkitRunnable set = new BukkitRunnable() {
        @Override
        public void run() {
            block.setType(type, true);
        }
    };
    private final BukkitRunnable place = new BukkitRunnable() {
        @Override
        public void run() {
            if (block.getType() != type && block.getType() != Material.BEDROCK) {
                block.setType(Material.BEDROCK);
            }
        }
    };

    public Generator(Material type, Block block, int genTime) {
        this.type = type;
        this.block = block;
        this.genTime = genTime;
    }

    public Generator(Map<String, Object> serial) {
        this.genTime = Integer.parseInt((String) serial.get("genTime"));
        this.type = Material.getMaterial((String) serial.get("type"));
        Location blockLoc = Location.deserialize(serial);
        this.block = blockLoc.getWorld().getBlockAt(blockLoc);
    }

    public void run() {
        set.runTaskTimer(Main.getInstance(), 60, genTime * 20L);
        place.runTaskTimer(Main.getInstance(), 20, 20);
    }

    public void destroy() {
        set.cancel();
        place.cancel();
        block.setType(Material.AIR);
        ItemStack item = new ItemStack(type);
        item.setItemMeta(Main.getInstance().getGeneratorManager().getGeneratorMaterial(type));
        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), item);
    }

    public Map<String, Object> exportData() {
        Map<String, Object> data = new HashMap<>(block.getLocation().serialize());
        data.put("type", type.name());
        data.put("genTime", String.valueOf(genTime));
        return data;
    }

    public Location getLoc() {
        return block.getLocation();
    }
}
