package org.rspeer.malikdz.hunter.nodes.impl.hunting;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.stream.Stream;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.malikdz.hunter.nodes.CustomNode;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.movement.position.Position;

/**
 * 
 * @author MalikDz
 *
 */

public class TrapsChecker extends TimerTask {

	private String trapName;
	private String[] objectsName;

	public TrapsChecker(String idleTrap, String failTrap, String lootTrap, String trapName) {
		this.trapName = trapName;
		this.objectsName = new String[] { idleTrap, failTrap, lootTrap };
		Arrays.sort(objectsName);
	}

	@Override
	public void run() {
		if (Game.getClient().getGameState() != 30) return;
		List<Position> firstTiles, secondTiles;
		checkTilesUsage(firstTiles = new ArrayList<Position>());
		Time.sleep(1000);
		if (Game.getClient().getGameState() != 30)	return;
		checkTilesUsage(secondTiles = new ArrayList<Position>());
		boolean sameSize = firstTiles.size() == secondTiles.size();
		if (sameSize) {
			firstTiles.retainAll(secondTiles);
			synchronized (CustomNode.trapPositions) {
				CustomNode.trapPositions.removeAll(firstTiles);
			}
		}
	}

	public void checkTilesUsage(List<Position> tiles) {
		synchronized (CustomNode.trapPositions) {
			for (Position t : CustomNode.trapPositions) {
				Stream<Pickable> gItemStream = Stream.of(Pickables.getAt(t.getX(), t.getY(), t.getFloorLevel()));
				boolean itemCheck = gItemStream.anyMatch(p -> p.getName().equals(trapName));
				Stream<SceneObject> objStream = Stream.of(SceneObjects.getAt(t.getX(), t.getY(), t.getFloorLevel()));
				boolean objsCheck = objStream.anyMatch(s -> Arrays.binarySearch(objectsName, s.getName()) >= 0);
				if (itemCheck && objsCheck)
					tiles.add(t);
			}
		}
	}
}
