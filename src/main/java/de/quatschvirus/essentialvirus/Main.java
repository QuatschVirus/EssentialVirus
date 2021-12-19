package de.quatschvirus.essentialvirus;

import de.quatschvirus.essentialvirus.actionbar.ActionBarManager;
import de.quatschvirus.essentialvirus.actionbar.BalanceDisplay;
import de.quatschvirus.essentialvirus.actionbar.LagDisplay;
import de.quatschvirus.essentialvirus.actionbar.TimeDisplay;
import de.quatschvirus.essentialvirus.backpack.BackpackManager;
import de.quatschvirus.essentialvirus.commands.*;
import de.quatschvirus.essentialvirus.commands.economy.BalanceCommand;
import de.quatschvirus.essentialvirus.commands.economy.DepositCommand;
import de.quatschvirus.essentialvirus.commands.economy.PayCommand;
import de.quatschvirus.essentialvirus.listeners.*;
import de.quatschvirus.essentialvirus.logging.Log;
import de.quatschvirus.essentialvirus.timer.Timer;
import de.quatschvirus.essentialvirus.utils.Config;
import de.quatschvirus.essentialvirus.utils.Lag;
import de.quatschvirus.essentialvirus.utils.NoTabComplete;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Timer timer;
    private BackpackManager backpackManager;
    private ActionBarManager actionBarManager;

    private boolean indev = false;

    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "EssentialVirus" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    @Override
    public void onLoad() {
        instance = this;
        new Config();
    }



    @SuppressWarnings("InstantiationOfUtilityClass")
    @Override
    public void onEnable() {
        timer = new Timer();
        backpackManager = new BackpackManager();
        actionBarManager = new ActionBarManager();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);

        if (Config.contains("indev.isindev")) {
            indev = (boolean) Config.get("indev.isindev");
        }

        new BalanceDisplay();
        new TimeDisplay();
        new LagDisplay();
        new Log();

        listenerRegistration();
        commandRegistration();

        Bukkit.getLogger().fine("Plugin enabled");
    }

    @Override
    public void onDisable() {
        Config.set("indev.isindev", indev);
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
        pluginManager.registerEvents(new SleepListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
    }

    @SuppressWarnings("ConstantConditions")
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
            getCommand("api").setExecutor(new APICommand());
            getCommand("api").setTabCompleter(new NoTabComplete());
            getCommand("display").setExecutor(new DisplayCommand());
            getCommand("indev").setExecutor(new IndevCommand());
            getCommand("indev").setTabCompleter(new NoTabComplete());
            getCommand("blocktnt").setExecutor(new BlockTNTCommand());
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

    public boolean isIndev() {
        return indev;
    }

    public void setIndev(boolean indev) {
        this.indev = indev;
        if (!indev) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                ArrayList<String> data = new ArrayList<>(Config.getStringList("indev.last_pos." + p.getUniqueId()));
                p.teleport(new Location(Bukkit.getWorld(data.get(0)), Double.parseDouble(data.get(1)), Double.parseDouble(data.get(2)), Double.parseDouble(data.get(3))));
                Config.set("indev.teleported." + p.getUniqueId(), true);
            }
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer(ChatColor.RED + "Der Indev-Modus wurde aktiviert.");
            }
        }
    }
}
