package dev._2lstudios.inventoryapi.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryOpenEvent;

import dev._2lstudios.inventoryapi.InventoryPlayer;
import dev._2lstudios.inventoryapi.InventoryWrapper;

public class InventoryAPIOpenEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final InventoryOpenEvent event;
    private final InventoryPlayer player;
    private final InventoryWrapper inventoryWrapper;
    private boolean cancel;

    public InventoryAPIOpenEvent(final InventoryOpenEvent event, final InventoryPlayer inventoryPlayer,
            final InventoryWrapper inventoryWrapper) {
        this.event = event;
        this.player = inventoryPlayer;
        this.inventoryWrapper = inventoryWrapper;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public InventoryOpenEvent getEvent() {
        return event;
    }

    public InventoryPlayer getPlayer() {
        return player;
    }

    public InventoryWrapper getInventoryWrapper() {
        return inventoryWrapper;
    }
}