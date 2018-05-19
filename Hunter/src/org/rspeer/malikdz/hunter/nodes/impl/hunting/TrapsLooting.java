package org.rspeer.malikdz.hunter.nodes.impl.hunting;

import org.rspeer.malikdz.hunter.nodes.CustomNode;
import org.rspeer.runetek.adapter.scene.SceneObject;

/**
 * 
 * @author MalikDz
 *
 */

public class TrapsLooting extends CustomNode {

	private SceneObject trap;
	private String[] actions;
	private String itemTrapName;
	private String checkTrapName;

	public TrapsLooting(  String itemTrapName, String checkTrapName, String[] actions) {
		this.actions = actions;
		this.itemTrapName = itemTrapName;
		this.checkTrapName = checkTrapName;
	}

	@Override
	public boolean canRun() {
		return (trap = nextObject(checkTrapName, actions)) != null;
	}

	@Override
	public void run() {
		takeTrap(trap, itemTrapName, checkTrapName, actions);
	}
}