package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (!Config.contains("chatcolor." + player.getUniqueId())) {
            try {
                if(player.isOp()) {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.GREEN.toString());
                } else {
                    Config.set("chatcolor." + player.getUniqueId(), ChatColor.RESET.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(player.isOp()) {
            event.setFormat(ChatColor.DARK_GRAY + "<" + ChatColor.GREEN + "ADMIN - %1$s" + ChatColor.DARK_GRAY + ">" + ChatColor.RESET + " %2$s");
        } else {
            event.setFormat(ChatColor.DARK_GRAY + "<" + Config.get("chatcolor." + player.getUniqueId()) + "%1$s" + ChatColor.DARK_GRAY + ">" + ChatColor.RESET + " %2$s");
        }

        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));


    }

}
