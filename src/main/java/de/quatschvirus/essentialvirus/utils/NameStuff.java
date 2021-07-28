package de.quatschvirus.essentialvirus.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NameStuff {

    public static boolean isPlayerName(String name) {

        Player player = Bukkit.getPlayer(name);
        return player != null;
    }

    public static boolean isNickname(String name) {

        List<String> nicknames;

        if (!Config.contains("nick.nicknames")) {
            try {
                Config.set("nick.nicknames", new ArrayList<String>());
            } catch (IOException e) {
                e.printStackTrace();
            }
            nicknames = new ArrayList<>();
        } else {
            nicknames = Config.getStringList("nick.nicknames");
        }

        return nicknames.contains(name);
    }

    public static Player getPlayerFromNickname(String nickname) {

        List<String> uuids;

        if (!Config.contains("nick.uuids")) {
            try {
                Config.set("nick.uuids", new ArrayList<String>());
            } catch (IOException e) {
                e.printStackTrace();
            }
            uuids = new ArrayList<>();
        } else {
            uuids = Config.getStringList("nick.uuids");
        }

        for (String uuid: uuids) {
            if (Config.get("nick." + uuid) == nickname) {
                return Bukkit.getPlayer(uuid);
            }
        }
        return null;
    }

    public static String getNicknameFromPlayer(Player player) {
        if(Config.contains("nick." + player.getUniqueId())) {
            return (String) Config.get("nick." + player.getUniqueId());
        }
        return null;
    }

    public static String getNicknameFromPlayer(UUID uuid) {
        if(Config.contains("nick." + uuid)) {
            return (String) Config.get("nick." + uuid);
        }
        return null;
    }

    public static String getNicknameFromPlayer(String name) {
        Player player = Bukkit.getPlayer(name);
        if (player == null) {
            return null;
        }
        if(Config.contains("nick." + player.getUniqueId())) {
            return (String) Config.get("nick." + player.getUniqueId());
        }
        return null;
    }

}
