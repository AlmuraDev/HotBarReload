package com.almuradev.HotBarReload.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.almuradev.HotBarReload.Util;

public class PlayerEvents implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if ((!event.isCancelled()) && (event.getPlayer().getGameMode() != GameMode.CREATIVE) && (event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getPlayer().hasPermission("hotbarreload.use")) {
			int currentDurability = event.getPlayer().getItemInHand().getDurability();
			int maxDurability = event.getPlayer().getItemInHand().getType().getMaxDurability();

			if (maxDurability > 0) {
				if (currentDurability >= maxDurability)
					Util.ReloadItemBar(event.getPlayer(), new SpoutItemStack(event.getPlayer().getItemInHand()));
			} else {
				Player player = event.getPlayer();
				if (player.getItemInHand().getAmount() == 0)
					Util.ReloadItemBar(player, new SpoutItemStack(player.getItemInHand()));
			}
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if ((!event.isCancelled()) && (event.getPlayer().getGameMode() != GameMode.CREATIVE)) {
			Player player = event.getPlayer();
			if (event.getPlayer().hasPermission("hotbarreload.use") && (player.getItemInHand().getAmount() == 0))
				Util.ReloadItemBar(player, new SpoutItemStack(event.getItemDrop().getItemStack()));
		}
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if ((!event.isCancelled()) && (event.getPlayer().getGameMode() != GameMode.CREATIVE) && (event.getRightClicked() != null) && event.getPlayer().hasPermission("hotbarreload.use")) {
			int currentDurability = event.getPlayer().getItemInHand().getDurability();
			int maxDurability = event.getPlayer().getItemInHand().getType().getMaxDurability();

			if (maxDurability > 0) {
				if (currentDurability >= maxDurability) {
					Util.ReloadItemBar(event.getPlayer(), new SpoutItemStack(event.getPlayer().getItemInHand()));
				}
			} else {
				Player player = event.getPlayer();
				if (player.getItemInHand().getAmount() == 0) {
					Util.ReloadItemBar(player, new SpoutItemStack(player.getItemInHand()));
				}
			}
		}
	}

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if ((!event.isCancelled()) && (event.getPlayer().getGameMode() != GameMode.CREATIVE) && event.getPlayer().hasPermission("hotbarreload.use")) {
			int currentDurability = event.getPlayer().getItemInHand().getDurability();
			int maxDurability = event.getPlayer().getItemInHand().getType().getMaxDurability();

			if (currentDurability >= maxDurability) {
				Util.ReloadItemBar(event.getPlayer(), new SpoutItemStack(event.getPlayer().getItemInHand()));
			}
		}
	}

	@EventHandler
	public void onPlayerEggThrow(PlayerEggThrowEvent event) {
		if ((event.getPlayer().getGameMode() != GameMode.CREATIVE) && event.getPlayer().hasPermission("hotbarreload.use")) {
			Player player = event.getPlayer();
			if (player.getItemInHand().getAmount() == 0) {
				Util.ReloadItemBar(player, new SpoutItemStack(player.getItemInHand()));
			}
		}
	}
}