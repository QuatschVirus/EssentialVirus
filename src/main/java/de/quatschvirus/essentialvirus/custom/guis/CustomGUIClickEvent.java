package de.quatschvirus.essentialvirus.custom.guis;

import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomGUIClickEvent extends InventoryClickEvent {

    private final CustomGUI customGUI;

    public CustomGUIClickEvent(InventoryClickEvent event, CustomGUI customGUI) {
        super(event.getView(), event.getSlotType(), event.getSlot(), event.getClick(), event.getAction());
        this.customGUI = customGUI;
    }

    public CustomGUI getCustomGUI() {
        return customGUI;
    }
}
