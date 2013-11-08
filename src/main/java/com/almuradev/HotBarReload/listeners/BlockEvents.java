package com.almuradev.HotBarReload.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.almuradev.HotBarReload.Util;

public class BlockEvents implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if ((!event.isCancelled()) && (event.getPlayer().getGameMode() != GameMode.CREATIVE) && event.getPlayer().hasPermission("hotbarreload.use")) {

			int currentDurability = event.getPlayer().getItemInHand().getDurability();
			int maxDurability = event.getPlayer().getItemInHand().getType().getMaxDurability();
			if (maxDurability > 0) {
				if (currentDurability >= maxDurability) {
					Util.ReloadItemBar(event.getPlayer(), new SpoutItemStack(event.getPlayer().getItemInHand()));
				}
			} else {
				Player player = event.getPlayer();
				if (player.getItemInHand().getAmount() == 1) {
					Util.ReloadItemBar(player, new SpoutItemStack(player.getItemInHand()));
				}
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)	{
		if ((!event.isCancelled()) && (event.getPlayer().getGameMode() != GameMode.CREATIVE) && event.getPlayer().hasPermission("hotbarreload.use")) {
			int currentDurability = event.getPlayer().getItemInHand().getDurability();
			int maxDurability = event.getPlayer().getItemInHand().getType().getMaxDurability();
			if ((maxDurability > 0) && 	(currentDurability >= maxDurability)) {
				Util.ReloadItemBar(event.getPlayer(), new SpoutItemStack(event.getPlayer().getItemInHand()));
			}
		}
	}
}