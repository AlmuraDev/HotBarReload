package com.almuradev.HotBarReload;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.inventory.SpoutItemStack;

public class Util
{
	@SuppressWarnings("deprecation")
	public static void ReloadItemBar(Player player, SpoutItemStack depleted) {
		int destSlot = player.getInventory().getHeldItemSlot();
		int srcSlot = getSlot(destSlot, getItems(player, depleted));

		if (srcSlot >= 0) {
			player.getInventory().setItem(destSlot, player.getInventory().getItem(srcSlot));
			player.getInventory().clear(srcSlot);
			player.updateInventory();
		}
	}

	private static int getSlot(int destSlot, Map<Integer, ?> items) {
		int count = 0;
		int srcSlot = -1;

		if ((items != null) && 
				(items.size() > 0)) {
			do {
				if (items.size() > count) {
					srcSlot = ((Integer)items.keySet().toArray()[count]).intValue();
				}
				count++;
			}while ((srcSlot == destSlot) && (
					items.size() > count));
		}

		return srcSlot;
	}

	@SuppressWarnings("unchecked")
	private static Map<Integer, ?> getItems(Player player, SpoutItemStack depleted) {
		SpoutItemStack held = new SpoutItemStack(depleted);

		if ((!held.isCustomItem()) && (!held.isCustomBlock())) {
			return player.getInventory().all(Material.getMaterial(depleted.getType().name()));
		}

		@SuppressWarnings("rawtypes")
		Map found = new HashMap();
		for (int i = 0; i < player.getInventory().getSize(); i++)
		{
			if (player.getInventory().getContents()[i] == null)
			{
				continue;
			}
			SpoutItemStack stack = new SpoutItemStack(player.getInventory().getContents()[i]);

			if (held.getMaterial() != stack.getMaterial())
			{
				continue;
			}
			found.put(new Integer(i), new SpoutItemStack(player.getInventory().getContents()[i]));
		}
		return found;
	}
}