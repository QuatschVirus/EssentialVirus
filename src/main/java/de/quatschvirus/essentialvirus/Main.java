package de.quatschvirus.essentialvirus;

import de.quatschvirus.essentialvirus.actionbar.ActionBarManager;
import de.quatschvirus.essentialvirus.backpack.BackpackManager;
import de.quatschvirus.essentialvirus.blockInventory.BlockInventoryManager;
import de.quatschvirus.essentialvirus.commands.*;
import de.quatschvirus.essentialvirus.commands.economy.*;
import de.quatschvirus.essentialvirus.commands.modCommands.*;
import de.quatschvirus.essentialvirus.generator.GeneratorManager;
import de.quatschvirus.essentialvirus.listeners.*;
import de.quatschvirus.essentialvirus.logging.Log;
import de.quatschvirus.essentialvirus.logging.LogListeners;
import de.quatschvirus.essentialvirus.otherActive.VoidSaver;
import de.quatschvirus.essentialvirus.playerData.PlayerDataHandler;
import de.quatschvirus.essentialvirus.pos.PositionManager;
import de.quatschvirus.essentialvirus.timer.Timer;
import de.quatschvirus.essentialvirus.types.Change;
import de.quatschvirus.essentialvirus.utils.*;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Main extends JavaPlugin {

    private static final Change[] changes = new Change[]{
            new Change("Rework der wandernden Händler", new String[]{
                    "Sieh es die selbst an",
                    "25% Chance auf einen Mystischen Händler",
                    "Gerne mehr Trade-Ideen!"
            }),
            new Change("Erleichterter Entity-Transport", new String[]{
                    "Wirf ein Ei auf ein Lebewesen",
                    "Es gibt eine Chance dass es zu einem Spawn-Ei wird",
                    "Trage Lebewesen, indem du sie beim Schleichen rechts-klickst"
            }),
            new Change("Setz dich!", new String[]{
                    "Man kann sich jetzt auf Stufen und Treppen setzen",
                    "Probier's einfach aus!"
            })
    };

    private static Main instance;

    private Timer timer;
    private BackpackManager bpM;
    private ActionBarManager abM;
    private VoidSaver voidSaver;
    private GeneratorManager gM;
    private BlockInventoryManager biM;
    private PositionManager pM;
    private PlayerDataHandler pdH;

    private YamlConfiguration config;

    public static List<Material> lockable = Arrays.asList(
            Material.CHEST,
            Material.BARREL,
            Material.SHULKER_BOX,
            Material.BLACK_SHULKER_BOX,
            Material.BLUE_SHULKER_BOX,
            Material.BROWN_SHULKER_BOX,
            Material.CYAN_SHULKER_BOX,
            Material.GRAY_SHULKER_BOX,
            Material.GREEN_SHULKER_BOX,
            Material.LIGHT_BLUE_SHULKER_BOX,
            Material.LIGHT_GRAY_SHULKER_BOX,
            Material.LIME_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX,
            Material.ORANGE_SHULKER_BOX,
            Material.PINK_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX,
            Material.RED_SHULKER_BOX,
            Material.WHITE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX,
            Material.TRAPPED_CHEST
    );
    public static List<Material> shulkerboxes = Arrays.asList(
            Material.SHULKER_BOX,
            Material.BLACK_SHULKER_BOX,
            Material.BLUE_SHULKER_BOX,
            Material.BROWN_SHULKER_BOX,
            Material.CYAN_SHULKER_BOX,
            Material.GRAY_SHULKER_BOX,
            Material.GREEN_SHULKER_BOX,
            Material.LIGHT_BLUE_SHULKER_BOX,
            Material.LIGHT_GRAY_SHULKER_BOX,
            Material.LIME_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX,
            Material.ORANGE_SHULKER_BOX,
            Material.PINK_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX,
            Material.RED_SHULKER_BOX,
            Material.WHITE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX
    );

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
        config = Config.getConfig();
        timer = new Timer();
        bpM = new BackpackManager();
        abM = new ActionBarManager();
        voidSaver = new VoidSaver();
        gM = new GeneratorManager();
        biM = new BlockInventoryManager();
        pM = new PositionManager();
        pdH = new PlayerDataHandler();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, Config::save, 18000L, 18000L);

        if (config.contains("indev.isindev")) {
            indev = config.getBoolean("indev.isindev");
        }

        new Log();

        listenerRegistration();
        commandRegistration();

        IntConverter.init();

        gM.run();

        for (World w : getServer().getWorlds()) {
            for (ArmorStand a : w.getEntitiesByClass(ArmorStand.class)) {
                if (a.getScoreboardTags().contains(ScoreboardTags.Seat)) {
                    a.remove();
                }
            }
        }
    }

    @Override
    public void onDisable() {
        config.set("indev.isindev", indev);
        timer.saveTime();
        bpM.save();
        gM.save();
        biM.save();
        pM.save();
        Config.save();
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
        pluginManager.registerEvents(new InteractionListener(), this);
        pluginManager.registerEvents(new InventoryCloseListener(), this);
        pluginManager.registerEvents(new CreatureSpawnListener(), this);
        pluginManager.registerEvents(new ProjectileHitListener(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new DismountListener(), this);

        pluginManager.registerEvents(new LogListeners(), this);

        pluginManager.registerEvents(voidSaver, this);
    }

    @SuppressWarnings("ConstantConditions")
    private void commandRegistration() {
        try {
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
            getCommand("pos").setExecutor(new PosCommand());
            getCommand("api").setExecutor(new APICommand());
            getCommand("api").setTabCompleter(new NoTabComplete());
            getCommand("display").setExecutor(new DisplayCommand());
            getCommand("indev").setExecutor(new IndevCommand());
            getCommand("indev").setTabCompleter(new NoTabComplete());
            getCommand("blocktnt").setExecutor(new BlockTNTCommand());
            getCommand("generator").setExecutor(new GeneratorCommand());
            getCommand("lock").setExecutor(new LockCommand());
            getCommand("lock").setTabCompleter(new NoTabComplete());
            getCommand("voidsaverexclude").setExecutor(new VoidSaverExcludeCommand());
            getCommand("voidsaverexclude").setTabCompleter(new NoTabComplete());
            getCommand("unlock").setExecutor(new UnlockCommand());
            getCommand("unlock").setTabCompleter(new NoTabComplete());
            getCommand("blocktp").setExecutor(new BlockTpCommand());
            getCommand("log").setExecutor(new LogCommand());
            getCommand("debugstick").setExecutor(new DebugStickCommand());
            getCommand("debugstick").setTabCompleter(new NoTabComplete());
            getCommand("money").setExecutor(new MoneyCommand());
            getCommand("repair").setExecutor(new RepairCommand());
            getCommand("repair").setTabCompleter(new NoTabComplete());
            getCommand("scavenge").setExecutor(new ScavengeCommand());
            getCommand("scavenge").setTabCompleter(new NoTabComplete());
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
        return bpM;
    }

    public ActionBarManager getActionBarManager() {
        return abM;
    }

    public VoidSaver getVoidSaver() {
        return voidSaver;
    }

    public GeneratorManager getGeneratorManager() {
        return gM;
    }

    public BlockInventoryManager getBlockInventoryManager() {
        return biM;
    }

    public PositionManager getPositionManager() {
        return pM;
    }

    public PlayerDataHandler getPlayerDataHandler() {
        return pdH;
    }

    public boolean isIndev() {
        return indev;
    }

    public void setIndev(boolean indev) {
        this.indev = indev;
        if (!indev) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                ArrayList<String> data = new ArrayList<>(config.getStringList("indev.last_pos." + p.getUniqueId()));
                p.teleport(new Location(Bukkit.getWorld(data.get(0)), Double.parseDouble(data.get(1)), Double.parseDouble(data.get(2)), Double.parseDouble(data.get(3))));
                config.set("indev.teleported." + p.getUniqueId(), true);
            }
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer(ChatColor.RED + "Der Indev-Modus wurde aktiviert.");
            }
        }
    }

    public static Change[] getChanges() {
        return changes;
    }
}
