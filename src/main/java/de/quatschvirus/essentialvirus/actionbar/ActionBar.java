package de.quatschvirus.essentialvirus.actionbar;

import de.quatschvirus.essentialvirus.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionBar {

    private final HashMap<String, Display> elements;
    private final long timing;
    private final long delay;
    private final ArrayList<String> visible;
    private boolean wholeVisible = true;
    private final Player player;


    public ActionBar(Player player, long timing, long delay, HashMap<String, Display> elements) {
        this.timing = timing;
        this.delay = delay;
        this.player = player;
        this.elements = elements;
        this.visible = new ArrayList<>(elements.keySet());
        System.out.println(player.getName());
        start();
    }

    private void display() {
        if (wholeVisible) {
            StringBuilder out = new StringBuilder();
            ArrayList<String> render = new ArrayList<>();
            for (String key : this.elements.keySet()) {
                if (visible.contains(key)) {
                    render.add(this.elements.get(key).display(player));
                }
            }
            for (int i = 0; i < render.size(); i++) {
                out.append(render.get(i));
                if (i < (render.size() - 1)) {
                    out.append(ChatColor.GOLD).append(" --- ");
                }
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(out.toString()));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<String> getNames() {
        return new ArrayList<>(elements.keySet());
    }

    public boolean isVisible(String name) {
        return visible.contains(name);
    }

    public ArrayList<String> getVisible() {
        return visible;
    }

    public void setVisible(String name, boolean visibility) {
        if (visibility) {
            visible.add(name);
        } else {
            visible.remove(name);
        }
    }

    public boolean isWholeVisible() {
        return wholeVisible;
    }

    public void setWholeVisible(boolean wholeVisible) {
        this.wholeVisible = wholeVisible;
    }

    private void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                display();
            }
        }.runTaskTimer(Main.getInstance(), this.delay, this.timing);
    }
}
