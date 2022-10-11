package de.quatschvirus.essentialvirus.backpack;

import de.quatschvirus.essentialvirus.utils.Base64;
import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class Backpack {

    private final Inventory inventory;
    private final YamlConfiguration config = Config.getConfig();

    public Backpack() {
        if(!config.contains("backpack.default.slots")) {
            config.set("backpack.default.slots", 27);
        }
        this.inventory = Bukkit.createInventory(null, config.getInt("backpack.default.slots"), ChatColor.DARK_GREEN + "Rucksack");
    }

    public Backpack(String data) throws IOException {
        if(!config.contains("backpack.default.slots")) {
            config.set("backpack.default.slots", 27);
        }
        this.inventory = Bukkit.createInventory(null, config.getInt("backpack.default.slots"), ChatColor.DARK_GREEN + "Rucksack");
        this.inventory.setContents(Base64.itemStackArrayFromBase64(data));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String toBase64() {
        return Base64.itemStackArrayToBase64(inventory.getContents());
    }
}
