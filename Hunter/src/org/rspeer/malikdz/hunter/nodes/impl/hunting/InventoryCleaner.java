package org.rspeer.malikdz.hunter.nodes.impl.hunting;

import java.util.Arrays;
import org.rspeer.malikdz.hunter.nodes.CustomNode;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;

/**
 * 
 * @author MalikDz
 *
 */

public class InventoryCleaner extends CustomNode {

	private static final String[] UNEEDED_ITEMS = { "Bones", "Raw bird meat" };

	public InventoryCleaner() {
		Arrays.sort(UNEEDED_ITEMS);
	}

	@Override
	public void run() {
		for (Item item : Inventory.getItems())
			if (Arrays.binarySearch(UNEEDED_ITEMS, item.getName()) >= 0)
				dropItem(item);

	}

	private void dropItem(Item item) {
		final int firstCount = Inventory.getCount();
		if (item.interact("Drop")) Time.sleepUntil(() -> firstCount != Inventory.getCount(), 1500);
	}

	@Override
	public boolean canRun() {
		return Inventory.contains(UNEEDED_ITEMS);
	}
}
