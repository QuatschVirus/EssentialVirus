package de.quatschvirus.essentialvirus.types;

import de.quatschvirus.essentialvirus.Main;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;

public class AetherLootTable implements LootTable {
    @NonNull
    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        ItemStack item = null;
        double roll = random.nextDouble();
        double loot = context.getLuck() + 1;
        int dA = random.nextInt(16);
        if (roll <= 0.0000001 * loot) {
            item = new ItemStack(Material.DRAGON_EGG);
        } else if (roll <= 0.01 * loot) {
            item = new ItemStack(Material.NETHERITE_INGOT, Math.max(1, Math.min(dA, 4)));
        } else if (roll <= 0.05 * loot) {
            item = new ItemStack(Material.DIAMOND, Math.max(1, Math.min(dA, 4)));
        } else if (roll <= 0.1 * loot) {
            item = new ItemStack(Material.EXPERIENCE_BOTTLE, Math.max(1, Math.min(dA, 3)));
        } else if (roll <= 0.15 * loot) {
            item = new ItemStack(Material.GOLD_INGOT, Math.max(1, Math.min(dA, 4)));
        } else if (roll <= 0.25 * loot) {
            item = new ItemStack(Material.IRON_INGOT, Math.max(1, Math.min(dA, 4)));
        } else if (roll <= 0.5 * loot) {
            item = new ItemStack(Material.FIREWORK_ROCKET, Math.max(1, dA));
        }
        return new ArrayList<>(Collections.singletonList(item));
    }

    @Override
    public void fillInventory(@NonNull Inventory inventory, Random random, @NonNull LootContext context) {}

    @Override
    @NonNull
    public NamespacedKey getKey() {
        return new NamespacedKey(Main.getProvidingPlugin(Main.class), "aether_loot");
    }
}
