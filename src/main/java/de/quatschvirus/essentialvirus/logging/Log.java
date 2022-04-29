package de.quatschvirus.essentialvirus.logging;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Log {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void write(String message){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        File fl = new File(Main.getInstance().getDataFolder(), "Watchlog-" + dateFormat.format(date) + ".log");
        if(!fl.exists()) {
            try {
                fl.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fl, true));
            bw.append("[").append(timeFormat.format(date)).append("]").append(message);
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean playercheck(Player player) {
        if (Config.getConfig().contains("log.players")) {
            return (Config.getConfig().getStringList("log.players").contains(player.getUniqueId().toString()));
        }
        Config.getConfig().set("log.players", new ArrayList<String>());
        return false;
    }

    public Log() {
        Bukkit.getPluginManager().registerEvents(new LogListeners(), Main.getInstance());
    }
}
