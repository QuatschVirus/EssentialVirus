package de.quatschvirus.essentialvirus.blockInventory;

import com.google.gson.Gson;

import de.quatschvirus.essentialvirus.utils.Base64;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Map;

public class BlockInventory {

    private Block block;
    private Inventory inventory;
    private String owner = null;
    private String title;
    private int breakable = 0; //2: everyone, 1: only owner (if owner == null -> everyone), 0: none

    public BlockInventory(Block block, Inventory inventory) {
        this.block = block;
        this.inventory = inventory;
    }

    public BlockInventory(Block block, int size, String title, ItemStack[] contents) {
        this.block = block;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, size, title);
        this.inventory.setContents(contents);
    }

    public BlockInventory(Block block, Inventory inventory, String owner) {
        this.block = block;
        this.inventory = inventory;
        this.owner = owner;
    }

    public BlockInventory(Block block, int size, String title, ItemStack[] contents, String owner) {
        this.block = block;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, size, title);
        this.inventory.setContents(contents);
        this.owner = owner;
    }

    public BlockInventory(Block block, Inventory inventory, int breakable) {
        this.block = block;
        this.inventory = inventory;
        this.breakable = breakable;
    }

    public BlockInventory(Block block, int size, String title, ItemStack[] contents, int breakable) {
        this.block = block;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, size, title);
        this.inventory.setContents(contents);
        this.breakable = breakable;
    }

    public BlockInventory(Block block, Inventory inventory, String owner, int breakable) {
        this.block = block;
        this.inventory = inventory;
        this.owner = owner;
        this.breakable = breakable;
    }

    public BlockInventory(Block block, int size, String title, ItemStack[] contents, String owner, int breakable) {
        this.block = block;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, size, title);
        this.inventory.setContents(contents);
        this.owner = owner;
        this.breakable = breakable;
    }

    @SuppressWarnings("unchecked")
    public BlockInventory(String serial) {
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(serial, Map.class);
        this.owner = (String) data.remove("owner");
        this.title = (String) data.get("title");
        if (this.title != null) {
            this.inventory = Bukkit.createInventory(null, ((Double) data.remove("size")).intValue(), (String) data.remove("title"));
        } else {
            this.inventory = Bukkit.createInventory(null, ((Double) data.remove("size")).intValue());
            data.remove("title");
        }
        try {
            this.inventory.setContents(Base64.itemStackArrayFromBase64((String) data.remove("inventory")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Location pos = Location.deserialize(data);
        this.block = pos.getWorld().getBlockAt(pos);
        this.breakable = ((Double) data.remove("breakable")).intValue();
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBreakable() {
        return breakable;
    }

    public void setBreakable(int breakable) {
        this.breakable = breakable;
    }

    public void dropContents() {
        for (ItemStack stack : inventory.getContents()) {
            if (stack != null) {
                block.getWorld().dropItemNaturally(block.getLocation(), stack);
            }
        }
        inventory.clear();
    }

    public String getSerial() {
        Gson gson = new Gson();
        Map<String, Object> data = block.getLocation().serialize();
        data.put("inventory", Base64.itemStackArrayToBase64(inventory.getContents()));
        data.put("size", inventory.getSize());
        data.put("title", title);
        data.put("owner", owner);
        data.put("breakable", breakable);
        return gson.toJson(data);
    }
}
