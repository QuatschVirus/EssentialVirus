package de.quatschvirus.essentialvirus.custom_materials;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum CustomMaterial {
    MISTERY_DUST(Material.GLOWSTONE_DUST, 10001, "Merkwürdiger Staub");

    private final Material base;
    private final int CustomModelData;
    private final String name;

    CustomMaterial(Material base, int customModelData, String name) {
        this.base = base;
        CustomModelData = customModelData;
        this.name = name;
    }

    public Material getBase() {
        return base;
    }

    public int getCustomModelData() {
        return CustomModelData;
    }

    public String getName() {
        return ChatColor.RESET + name;
    }
}
