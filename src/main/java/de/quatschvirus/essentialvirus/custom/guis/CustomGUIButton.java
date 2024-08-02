package de.quatschvirus.essentialvirus.custom.guis;

import de.quatschvirus.essentialvirus.custom.materials.CustomItemStack;
import de.quatschvirus.essentialvirus.types.VoidReturnFunction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomGUIButton {
    private CustomItemStack iS;

    private final CustomGUI parent;

    private VoidReturnFunction<CustomGUIClickEvent> function;

    public CustomGUIButton(CustomItemStack iS, VoidReturnFunction<CustomGUIClickEvent> function, CustomGUI parent) {
        this.iS = iS;
        this.function = function;
        this.parent = parent;
    }

    public CustomItemStack getiS() {
        return iS;
    }

    public void setiS(CustomItemStack iS) {
        this.iS = iS;
    }

    public VoidReturnFunction<CustomGUIClickEvent> getFunction() {
        return function;
    }

    public void setFunction(VoidReturnFunction<CustomGUIClickEvent> function) {
        this.function = function;
    }

    public CustomGUI getParent() {
        return parent;
    }

    public void click(InventoryClickEvent e) {
        function.run(new CustomGUIClickEvent(e, this.parent));
    }
}
