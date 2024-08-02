package de.quatschvirus.essentialvirus.listeners;

import de.quatschvirus.essentialvirus.custom.materials.CustomItemStack;
import de.quatschvirus.essentialvirus.custom.materials.CustomMaterial;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.*;

public class CreatureSpawnListener implements Listener {

    private final Map<MerchantRecipe, List<ItemStack>> NORMAL_TRADES = Map.of(
            new MerchantRecipe(new CustomItemStack(CustomMaterial.MISTERY_DUST, 2), 100), Arrays.asList(new ItemStack(Material.ENDER_PEARL, 8))
    );
    private final Map<MerchantRecipe, List<ItemStack>> MYSTIC_TRADES = Map.of(
            new MerchantRecipe(new ItemStack(Material.EMERALD, 4), 100), Arrays.asList(new CustomItemStack(CustomMaterial.MISTERY_DUST, 32))
    );

    private final Random random = new Random();

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getEntity().getType() == EntityType.WANDERING_TRADER) {
            WanderingTrader wT = (WanderingTrader) e.getEntity();
            List<MerchantRecipe> recipeList = new ArrayList<>();
            double nD = random.nextDouble();
            for (Map.Entry<MerchantRecipe, List<ItemStack>> entry : nD >= 0.75 ? MYSTIC_TRADES.entrySet() : NORMAL_TRADES.entrySet()) {
                MerchantRecipe mR = entry.getKey();
                mR.setIngredients(entry.getValue());
                recipeList.add(mR);
            }
            wT.setRecipes(recipeList);
            if (nD >=0.75) {
                wT.setCustomName(ChatColor.DARK_GREEN + "Mystischer Händler");
                wT.setCustomNameVisible(false);
            }
        }
    }
}
