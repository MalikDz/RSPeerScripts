package org.rspeer.malikdz.hunter.nodes.impl.hunting;

import org.rspeer.malikdz.hunter.nodes.CustomNode;
import org.rspeer.runetek.adapter.scene.SceneObject;

/**
 * 
 * @author MalikDz
 *
 */

public class TrapRemover extends CustomNode {

	private SceneObject trap;
	private String[] actions;
	private String itemTrapName;
	private String failTrapName;

	public TrapRemover( String itemTrapName, String failTrapName, String[] actions) {
		this.actions = actions;
		this.itemTrapName = itemTrapName;
		this.failTrapName = failTrapName;
	}

	@Override
	public boolean canRun() {
		return (trap = nextObject(failTrapName, actions)) != null;
	}

	@Override
	public void run() {
		takeTrap(trap, itemTrapName, failTrapName, actions);
	}
}
