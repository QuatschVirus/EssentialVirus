package de.quatschvirus.essentialvirus.custom.guis;

import de.quatschvirus.essentialvirus.custom.materials.CustomItemStack;
import de.quatschvirus.essentialvirus.types.VoidReturnFunction;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class CustomGUI {
    private final String name;
    private final int size;

    private final CustomItemStack defaultIS;

    private final ArrayList<CustomGUIButton> buttons;

    public CustomGUI(String name, int size, CustomItemStack defaultIS) {
        this.name = name;
        this.size = size;
        this.defaultIS = defaultIS;
        buttons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buttons.add(null);
        }
    }

    public CustomGUI setButton(int index, CustomItemStack iS, VoidReturnFunction<CustomGUIClickEvent> function) {
        buttons.set(index, new CustomGUIButton(iS, function, this));
        return this;
    }

    public CustomGUI resetButton(int index) {
        buttons.set(index, null);
        return this;
    }

    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, this.size, this.name);
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i) == null) {
                inv.setItem(i, defaultIS);
            } else {
                inv.setItem(i, buttons.get(i).getiS());
            }
        }
        return inv;
    }

    public void click(InventoryClickEvent e) {
        if (buttons.get(e.getSlot()) != null) {
            buttons.get(e.getSlot()).click(e);
        }
    }

    // TODO: Implement interacting with the players inventory
}
