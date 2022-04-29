package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.types.Change;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class JoinListener implements Listener {

    private final ArrayList<Player> seenChanges = new ArrayList<>();
    private final YamlConfiguration config = Config.getConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Main.getPrefix() + ChatColor.RED + player.getName() + ChatColor.YELLOW + " ist aufgetaucht.");

        if (!config.contains("balance." + player.getUniqueId())) {
            config.set("balance." + player.getUniqueId(), 10);
        }
        if (!config.contains("indev.teleported." + player.getUniqueId())) {
            config.set("indev.teleported." + player.getUniqueId(), true);
        }

        if (Main.getInstance().isIndev()) {
            if (player.hasPermission("essentialvirus.indev.join")) {
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Server ist im Indev-Modus");
                config.set("indev.last_pos." + player.getUniqueId(), new ArrayList<>(Arrays.asList(player.getWorld().getName(), String.valueOf(player.getLocation().getX()), String.valueOf(player.getLocation().getY()), String.valueOf(player.getLocation().getZ()))));
                config.set("indev.teleported." + player.getUniqueId(), false);
                player.setGameMode(GameMode.CREATIVE);
            } else if (player.hasPermission("essentialvirus.indev.visit")) {
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Server ist im Indev-Modus. Du bist als Zuschauer erlaubt");
                config.set("indev.last_pos." + player.getUniqueId(), new ArrayList<>(Arrays.asList(player.getWorld().getName(), String.valueOf(player.getLocation().getX()), String.valueOf(player.getLocation().getY()), String.valueOf(player.getLocation().getZ()))));
                config.set("indev.teleported." + player.getUniqueId(), false);
                player.setGameMode(GameMode.SPECTATOR);
            } else {
                player.kickPlayer(ChatColor.RED + "Der Server ist gerade im Indev-Modus und wird bearbeitet.");
                return;
            }
        } else {
            if(!config.getBoolean("indev.teleported." + player.getUniqueId())) {
                ArrayList<String> data = new ArrayList<>(config.getStringList("indev.last_pos." + player.getUniqueId()));
                player.teleport(new Location(Bukkit.getWorld(data.get(0)), Double.parseDouble(data.get(1)), Double.parseDouble(data.get(2)), Double.parseDouble(data.get(3))));
                config.set("indev.teleported." + player.getUniqueId(), true);
            }
        }

        Main.getInstance().getActionBarManager().addActionBar(player);
        if (!seenChanges.contains(player)) {
            StringBuilder out  = new StringBuilder();
            for (Change change : Main.getChanges()) {
                out.append("\n").append(change.render());
            }
            player.sendMessage(Main.getPrefix() + "Neuste Änderungen:" + out);
            seenChanges.add(player);
        }
    }

}
