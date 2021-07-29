package de.quatschvirus.essentialvirus;

import de.quatschvirus.essentialvirus.actionbar.ActionBarManager;
import de.quatschvirus.essentialvirus.actionbar.BalanceDisplay;
import de.quatschvirus.essentialvirus.actionbar.LagDisplay;
import de.quatschvirus.essentialvirus.actionbar.TimeDisplay;
import de.quatschvirus.essentialvirus.backpack.BackpackManager;
import de.quatschvirus.essentialvirus.commands.*;
import de.quatschvirus.essentialvirus.commands.economy.*;
import de.quatschvirus.essentialvirus.listeners.*;
import de.quatschvirus.essentialvirus.timer.Timer;
import de.quatschvirus.essentialvirus.utils.Config;
import de.quatschvirus.essentialvirus.utils.Lag;
import de.quatschvirus.essentialvirus.utils.NoTabComplete;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Timer timer;
    private BackpackManager backpackManager;
    private ActionBarManager actionBarManager;

    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "EssentialVirus" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    @Override
    public void onLoad() {
        instance = this;
        new Config();
    }



    @Override
    public void onEnable() {
        // Plugin startup logic

        timer = new Timer();
        backpackManager = new BackpackManager();
        actionBarManager = new ActionBarManager();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);

        new BalanceDisplay();
        new TimeDisplay();
        new LagDisplay();

        Bukkit.getLogger().fine("Plugin enabled");

        listenerRegistration();
        commandRegistration();
    }

    @Override
    public void onDisable() {
        timer.saveTime();
        backpackManager.save();
        Config.save();
        // Plugin shutdown logic
    }



    private void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
    }

    private void commandRegistration() {
        try {
            //getCommand("date").setExecutor(new DateCommand());
            getCommand("backpack").setExecutor(new BackpackCommand());
            getCommand("announceshutdown").setTabCompleter(new NoTabComplete());
            getCommand("timer").setExecutor(new TimerCommand());
            getCommand("chatcolor").setExecutor(new ChatcolorCommand());
            getCommand("nick").setExecutor(new NickCommand());
            getCommand("announceshutdown").setExecutor(new AnnounceShutdownCommand());
            getCommand("playerteleport").setExecutor(new TeleportCommand());
            getCommand("transfer").setExecutor(new TransferCommand());
            getCommand("balance").setExecutor(new BalanceCommand());
            getCommand("balance").setTabCompleter(new NoTabComplete());
            getCommand("deposit").setExecutor(new DepositCommand());
            getCommand("deposit").setTabCompleter(new NoTabComplete());
            getCommand("slime").setExecutor(new SlimeCommand());
            getCommand("slime").setTabCompleter(new NoTabComplete());
            getCommand("pay").setExecutor(new PayCommand());
            //getCommand("pos").setExecutor(new PosCommand());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public Timer getTimer() {
        return timer;
    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }

    public ActionBarManager getActionBarManager() {
        return actionBarManager;
    }
}
