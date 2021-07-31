package de.quatschvirus.essentialvirus.backpack;

import de.quatschvirus.essentialvirus.utils.Base64;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.UUID;

public class Backpack {

    private final Inventory inventory;

    public Backpack() {
        if(!Config.contains("backpack.default.slots")) {
            try {
                Config.set("backpack.default.slots", 27);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.inventory = Bukkit.createInventory(null, (int) Config.get("backpack.default.slots"), ChatColor.DARK_GREEN + "Rucksack");
    }

    public Backpack(String data) throws IOException {
        if(!Config.contains("backpack.default.slots")) {
            try {
                Config.set("backpack.default.slots", 27);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.inventory = Bukkit.createInventory(null, (int) Config.get("backpack.default.slots"), ChatColor.DARK_GREEN + "Rucksack");
        this.inventory.setContents(Base64.itemStackArrayFromBase64(data));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String toBase64() {
        return Base64.itemStackArrayToBase64(inventory.getContents());
    }
}
