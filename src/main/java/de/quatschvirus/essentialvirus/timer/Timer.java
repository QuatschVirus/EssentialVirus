package de.quatschvirus.essentialvirus.timer;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {

    private boolean running = false;
    private int time;

    public Timer() {
        if (Config.getConfig().contains("timer.time")) {
            this.time = Config.getConfig().getInt("timer.time");
        } else {
            this.time = 0;
        }
        run();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void saveTime() {
        Config.getConfig().set("timer.time", getTime());
    }

    public String getTimeString() {
        time = getTime();
        int mins = time / 60;
        int hours = mins / 60;
        int days = hours / 24;
        int secs = time;
        String secString = "" + secs % 60;
        String minString = "" + mins % 60;
        String hourString = "" + hours % 24;
        if (secs % 60 < 10) {
            secString = "0" + secs % 60;
        }
        if (mins % 60 < 10) {
            minString = "0" + mins % 60;
        }
        if (hours % 24 < 10) {
            hourString = "0" + hours % 24;
        }
        if (days > 0) {
            return days + " Tage, " + hourString + ":" + minString;
        } else if (hours > 0) {
            return hourString + ":" + minString;
        } else {
            return minString + ":" + secString;
        }
    }

    public String getTimerOutput() {
        if (isRunning()) {
            return ChatColor.GOLD + "Timer: " + ChatColor.BOLD + getTimeString();
        }
        return ChatColor.GOLD + "Der Timer ist pausiert!";
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!isRunning()) {
                    return;
                }

                setTime(getTime() + 1);
            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }
}
