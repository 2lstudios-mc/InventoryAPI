package dev._2lstudios.inventoryapi.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev._2lstudios.inventoryapi.InventoryManager;
import dev._2lstudios.inventoryapi.InventoryPlayer;
import dev._2lstudios.inventoryapi.InventoryPlayerManager;
import dev._2lstudios.inventoryapi.InventoryWrapper;
import dev._2lstudios.inventoryapi.events.InventoryAPICloseEvent;

public class InventoryCloseListener implements Listener {
    private final InventoryPlayerManager inventoryPlayerManager;
    private final InventoryManager inventoryManager;

    public InventoryCloseListener(final InventoryPlayerManager inventoryPlayerManager,
            final InventoryManager inventoryManager) {
        this.inventoryPlayerManager = inventoryPlayerManager;
        this.inventoryManager = inventoryManager;
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onInventoryClose(final InventoryCloseEvent event) {
        final InventoryWrapper inventoryWrapper = inventoryManager.get(event.getInventory());

        if (inventoryWrapper != null) {
            final HumanEntity whoClicked = event.getPlayer();

            if (whoClicked instanceof Player) {
                final Player player = (Player) whoClicked;
                final InventoryPlayer inventoryPlayer = inventoryPlayerManager.get(player);

                if (inventoryPlayer != null) {
                    final InventoryAPICloseEvent inventoryAPIClickEvent = new InventoryAPICloseEvent(event, inventoryPlayer, inventoryWrapper);

                    Bukkit.getPluginManager().callEvent(inventoryAPIClickEvent);
                }
            }
        }
    }
}