package de.quatschvirus.essentialvirus.utils;

public class Lag implements Runnable {
    /*
    This code was written entirely by LazyLemons on Bukkit Forums
    Thread URL: https://bukkit.org/threads/get-server-tps.143410/
    I just stripped it down to what I need
     */

    public static int TICK_COUNT= 0;
    public static long[] TICKS= new long[600];

    public static double getTPS()
    {
        return getTPS(100);
    }

    public static double getTPS(int ticks)
    {
        if (TICK_COUNT <= ticks) {
            return 20.0D;
        }
        int target = (TICK_COUNT- 1 - ticks) % TICKS.length;
        long elapsed = System.currentTimeMillis() - TICKS[target];

        return ticks / (elapsed / 1000.0D);
    }

    public void run()
    {
        TICKS[(TICK_COUNT% TICKS.length)] = System.currentTimeMillis();

        TICK_COUNT+= 1;
    }
}
