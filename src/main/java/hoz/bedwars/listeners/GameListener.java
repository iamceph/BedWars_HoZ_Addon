package hoz.bedwars.listeners;

import hoz.bedwars.Main;
import hoz.bedwars.utils.MiscUtils;
import misat11.bw.api.Game;
import misat11.bw.api.GameStatus;
import misat11.bw.api.ItemSpawnerType;
import misat11.bw.api.events.BedwarsPlayerKilledEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;

public class GameListener implements Listener {

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Game game = Main.getBedWars().getGameOfPlayer(victim);

        if (game == null) {
            return;
        }

        if (game.getStatus() != GameStatus.RUNNING) {
            return;
        }

        if (!Main.getConfigurator().config.getBoolean("dropArmorOnDeath", false)) {
            for (Iterator<ItemStack> iterator = event.getDrops().iterator(); iterator.hasNext(); ) {
                ItemStack drop = MiscUtils.isMaterialArmor(iterator.next());
                if (drop != null) {
                    event.getItemsToKeep().add(drop);
                    iterator.remove();
                }
            }
        }
    }

    @EventHandler
    public void onBwPlayerDie(BedwarsPlayerKilledEvent event) {
        if (Main.getConfigurator().config.getBoolean("addResourcesToKillerOnDeath", true)) {
            Player killer = event.getKiller();
            if (killer != null) {
                for (ItemStack itemstack : event.getDrops()) {
                    List<ItemSpawnerType> itemSpawnerTypes = Main.getBedWars().getItemSpawnerTypes();
                    for (ItemSpawnerType itemSpawnerType : itemSpawnerTypes) {
                        ItemStack itemSpawnerStack = itemSpawnerType.getStack();
                        if (itemSpawnerStack.isSimilar(itemstack)) {
                            killer.getInventory().addItem(itemstack);
                        }
                    }
                }
            }
        }
    }
}
