package de.quatschvirus.essentialvirus.blockInventory;

import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class BlockInventoryManager {

    private final ArrayList<BlockInventory> inventories = new ArrayList<>();

    public BlockInventoryManager() {
        ArrayList<String> data;
        if (Config.getConfig().contains("blockInventories")) {
            data = (ArrayList<String>) Config.getConfig().getStringList("blockInventories");
            for (String info : data) {
                inventories.add(new BlockInventory(info));
            }
        }
    }

    public ArrayList<BlockInventory> getInventories() {
        return inventories;
    }

    public BlockInventory getInventory(Block block) {
        for (BlockInventory inventory : inventories) {
            if (inventory.getBlock().equals(block)) {
                return inventory;
            }
        }
        return null;
    }

    public BlockInventory getInventory(Inventory inv) {
        for (BlockInventory inventory : inventories) {
            if (inventory.getInventory().equals(inv)) {
                return inventory;
            }
        }
        return null;
    }

    public void setInventory(BlockInventory bInv) {
        inventories.add(bInv);
    }

    public void removeInventory(Block block) {
        inventories.remove(getInventory(block));
    }

    public void removeInventory(Inventory inventory) {
        inventories.remove(getInventory(inventory));
    }

    public void removeInventory(BlockInventory bInv) {
        inventories.remove(bInv);
    }

    public void save() {
        ArrayList<String> data = new ArrayList<>();
        for (BlockInventory inventory : inventories) {
            data.add(inventory.getSerial());
        }
        Config.getConfig().set("blockInventories", data);
    }

}
