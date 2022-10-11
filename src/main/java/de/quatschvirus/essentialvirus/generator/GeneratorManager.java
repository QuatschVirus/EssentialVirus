package de.quatschvirus.essentialvirus.generator;

import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class GeneratorManager {
    private final ArrayList<Generator> generators = new ArrayList<>();

    private final HashMap<Material, Integer> scores = new HashMap<>();

    private final YamlConfiguration config = Config.getConfig();

    public GeneratorManager() {
        scores.put(Material.SAND, 5);
        scores.put(Material.RED_SAND, 5);
        scores.put(Material.GRAVEL, 10);
        for (Map<?, ?> data : config.getMapList("generators")){
            generators.add(new Generator((Map<String, Object>) data));
        }
    }

    public void add(Block block) {
        Generator g = new Generator(block.getType(), block, scores.get(block.getType()));
        generators.add(g);
        g.run();
    }

    public boolean check(Block block) {
        return findGenIndex(block) >= 0;
    }

    public void destroy(Block block) {
        if (check(block)) {
            generators.remove(findGenIndex(block)).destroy();
        }
    }

    public void run() {
        for (Generator g : generators) {
            g.run();
        }
    }

    public void save() {
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (Generator gen : generators) {
            data.add(gen.exportData());
        }
        config.set("generators", data);
    }

    private int findGenIndex(Block block) {
        for (int i = 0; i < generators.size(); i++) {
            if (generators.get(i).getLoc().equals(block.getLocation())) {
                return i;
            }
        }
        return -1;
    }

    public ItemMeta getGeneratorMaterial(Material type) {
        ItemMeta meta = new ItemStack(type).getItemMeta();
        meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.DARK_PURPLE + "Generator");
        meta.setLore(List.of(ChatColor.RESET + "Generates: " + type.name()));
        meta.addEnchant(Enchantment.KNOCKBACK, -1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return meta;
    }
}
