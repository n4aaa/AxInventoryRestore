package com.artillexstudios.axinventoryrestore.commands.subcommands.player;

import com.artillexstudios.axapi.scheduler.Scheduler;
import com.artillexstudios.axinventoryrestore.AxInventoryRestore;
import com.artillexstudios.axinventoryrestore.queue.Priority;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.artillexstudios.axinventoryrestore.AxInventoryRestore.MESSAGEUTILS;

public enum Claim {
    INSTANCE;

    public void execute(final Player sender) {
        AxInventoryRestore.getThreadedQueue().submit(() -> {
            if (AxInventoryRestore.getBackups().isEmpty()) {
                MESSAGEUTILS.sendLang(sender, "errors.backup-unavailable");
                return;
            }

            final ItemStack[] items = AxInventoryRestore.getBackups().remove(sender.getUniqueId());
            if (items == null) {
                MESSAGEUTILS.sendLang(sender, "errors.backup-unavailable");
                return;
            }

            Scheduler.get().runAt(sender.getLocation(), t -> {
                int n2 = 0;
                for (ItemStack it : items) {
                    if (it == null) it = new ItemStack(Material.AIR);

                    sender.getInventory().setItem(n2, it);
                    n2++;
                }
            });

            MESSAGEUTILS.sendLang(sender, "player-backup-claimed");
        }, Priority.HIGH);
    }

}
