package org.rspeer.malikdz.hunter.nodes.impl.hunting;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.malikdz.hunter.nodes.CustomNode;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;

/**
 * 
 * @author MalikDz
 *
 */

public class TrapsSetting extends CustomNode {

	private Position target;
	private String trapName;

	public TrapsSetting(String trapName) {
		this.trapName = trapName;
	}

	@Override
	public boolean canRun() {
		return trapPositions.size() < maxTrap() && (target = nextNonusedTile()) != null && Inventory.contains(trapName);
	}

	@Override
	public void run() {
		if (Players.getLocal().getPosition().equals(target) || handleWalk())
			setTrap();
	}

	private void setTrap() {
		final long trapCount = Inventory.getCount(trapName);
		if (Inventory.contains(trapName) && Inventory.getFirst(trapName).interact("Lay"))sleepTillAnimation();
		if (trapCount != Inventory.getCount(trapName) && !trapPositions.contains(target)) {
			synchronized (CustomNode.trapPositions) {
				trapPositions.add(target);
			}
		}
	}

	private boolean handleWalk() {
		Movement.walk(target);
		Time.sleepUntil(() -> Players.getLocal().getPosition().equals(target.getPosition()), 1500);
		return Players.getLocal().getPosition().equals(target);
	}
}
