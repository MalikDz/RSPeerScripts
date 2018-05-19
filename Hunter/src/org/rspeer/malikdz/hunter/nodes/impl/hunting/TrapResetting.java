package org.rspeer.malikdz.hunter.nodes.impl.hunting;

import org.rspeer.malikdz.hunter.nodes.CustomNode;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;

/**
 * 
 * @author MalikDz
 *
 */

public class TrapResetting extends CustomNode {

	private Pickable item;
	private String itemTrapName;

	public TrapResetting( String itemTrapName) {
		this.itemTrapName = itemTrapName;
	}

	@Override
	public boolean canRun() {
		return (item = nextItem(itemTrapName)) != null;
	}

	@Override
	public void run() {
		final Position itemTile = item.getPosition();
		final int trapCount = Inventory.getCount() + CustomNode.trapPositions.size();
		if (!isCleanTile(itemTile) && trapCount < 28) item.interact("Take");
		if (isCleanTile(itemTile)) resetTrap();
	}

	public void resetTrap() {
		final Position tile = item.getPosition();
		final long trapCount = Inventory.getCount(itemTrapName);
		if (Inventory.contains(itemTrapName) && item.interact("Lay")) sleepTillAnimation();
		if (Inventory.getCount(itemTrapName) != trapCount && !trapPositions.contains(tile)) synchronized (trapPositions) {
			trapPositions.add(tile);
		}
	}
}
