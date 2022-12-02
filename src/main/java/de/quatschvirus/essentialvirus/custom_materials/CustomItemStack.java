package de.quatschvirus.essentialvirus.custom_materials;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class CustomItemStack extends ItemStack {

    public static final List<String> LORE = Collections.singletonList(ChatColor.DARK_GREEN + "Custom Item by EssentialVirus");

    public CustomItemStack(CustomMaterial cMat) {
        super(cMat.getBase());
        ItemMeta iM = this.getItemMeta();
        iM.setCustomModelData(cMat.getCustomModelData());
        iM.setDisplayName(cMat.getName());
        iM.setLore(LORE);
        this.setItemMeta(iM);
    }

    public CustomItemStack(CustomMaterial cMat, int amount) {
        super(cMat.getBase(), amount);
        ItemMeta iM = this.getItemMeta();
        iM.setCustomModelData(cMat.getCustomModelData());
        iM.setDisplayName(cMat.getName());
        iM.setLore(LORE);
        this.setItemMeta(iM);
    }
}
