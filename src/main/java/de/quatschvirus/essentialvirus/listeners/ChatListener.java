package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final YamlConfiguration config = Config.getConfig();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (!config.contains("chatcolor." + player.getUniqueId())) {
            if(player.isOp()) {
                config.set("chatcolor." + player.getUniqueId(), ChatColor.GREEN.toString());
            } else {
                config.set("chatcolor." + player.getUniqueId(), ChatColor.RESET.toString());
            }
        }


        if(player.isOp()) {
            event.setFormat(ChatColor.DARK_GRAY + "<" + ChatColor.GREEN + "ADMIN - %1$s" + ChatColor.DARK_GRAY + ">" + ChatColor.RESET + " %2$s");
        } else {
            event.setFormat(ChatColor.DARK_GRAY + "<" + config.get("chatcolor." + player.getUniqueId()) + "%1$s" + ChatColor.DARK_GRAY + ">" + ChatColor.RESET + " %2$s");
        }

        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));


    }

}
