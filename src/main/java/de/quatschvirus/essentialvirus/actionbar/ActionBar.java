package de.quatschvirus.essentialvirus.actionbar;

import de.quatschvirus.essentialvirus.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ActionBar {

    private final ArrayList<String> elements = new ArrayList<>();
    private final long timing;
    private final long delay;
    private final Player player;


    public void set(int index, String iteration) {
        if (this.elements.size() == index) {
            this.elements.add(iteration);
        } else {
            this.elements.set(index, iteration);
        }
    }

    public ActionBar(Player player, long timing, long delay) {
        this.timing = timing;
        this.delay = delay;
        this.player = player;
        start();
    }

    private void display() {
        StringBuilder out  = new StringBuilder();
        for (int i = 0; i < this.elements.size(); i++) {
            out.append(this.elements.get(i));
            if (!(i == this.elements.size() - 1)) {
                out.append(ChatColor.GOLD).append(" --- ");
            }
        }
        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(out.toString()));

    }

    public int latestIndex() {
        return this.elements.size();
    }

    public Player getPlayer() {
        return player;
    }

    private void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                display();
            }
        }.runTaskTimer(Main.getInstance(), delay, this.timing);
    }
}
