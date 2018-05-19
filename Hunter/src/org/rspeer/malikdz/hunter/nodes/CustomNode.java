package org.rspeer.malikdz.hunter.nodes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.malikdz.hunter.utilities.Timer;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.SceneObject;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;

import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.SceneObjects;

/**
 * 
 * @author MalikDz
 *
 */

public abstract class CustomNode extends Node {

	public static int offset = 0;
	public static int maxTrap = 0;
	public static List<Position> trapPositions = new ArrayList<Position>();
	public final static List<Position> POSIBLE_TILES_OPTIONS = new ArrayList<Position>();
	private static final String[] OBSTACLES_NAMES = { "Bird snare", "Shaking box", "Box trap", "Flowers", "Fire" };
	private static final int OFFSETS[][] = { { 1, -1, 1, -1, 0, 2, 0, 0, 1, -1 }, { 1, 1, -1, -1, 0, 0, 1, -1, 0, 0 } };

	static {
		Arrays.sort(OBSTACLES_NAMES);
	}

	private boolean actionMatch(String[] actions, String... result) {
		return result.length == 1 && actions[0].equals(result[0]);
	}

	public int maxTrap() {
		return (int) (Skills.getLevel(Skill.HUNTER) / 20) + 1 + offset;
	}

	public boolean isCleanTile(final Position position) {
		for (SceneObject obj : SceneObjects.getAt(position))
			if (Arrays.binarySearch(OBSTACLES_NAMES, obj.getName()) >= 0)
				return false;
		return true;
	}

	public Position nextNonusedTile() {
		for (Position tile : POSIBLE_TILES_OPTIONS)
			if (isCleanTile(tile))
				return tile;
		return null;
	}

	public SceneObject nextObject(String name, String[] actions) {
		for (Position position : trapPositions)
			for (SceneObject o : SceneObjects.getAt(position))
				if (o.getName().equals(name) && actionMatch(actions, o.getActions()))
					return o;
		return null;
	}

	public Pickable nextItem(String name) {
		for (Position tile : trapPositions)
			for (Pickable item : Pickables.getAt(tile))
				if (item.getName().equals(name))
					return item;
		return null;
	}

	public Point randomPoint(final Rectangle r) {
		final Point point = new Point(-1, -1);
		point.x = r != null ? (int) (r.x + (r.width * Math.random())) : -1;
		point.y = r != null ? (int) (r.y + (r.height * Math.random())) : -1;
		return point;
	}

	public static void getOurZonesTiles(final Position baseTile) {
		for (int i = 0; i < OFFSETS[0].length; i++) {
			final int x = baseTile.getX() + OFFSETS[0][i];
			final int y = baseTile.getY() + OFFSETS[1][i];
			POSIBLE_TILES_OPTIONS.add(new Position(x, y, 0));
		}
	}

	public void takeTrap(SceneObject trap, String itemTrapName, String trapName, String[] actions) {
		final Position tile = trap.getPosition();
		final long trapCount = Inventory.getCount(trapName);
		if (trap != null && trap.interact(actions[0]))
			sleepTillAnimation();
		if (Inventory.getCount(itemTrapName) != trapCount && trapPositions.contains(tile) && isCleanTile(tile)) {
			synchronized (CustomNode.trapPositions) {
				trapPositions.remove(tile);
			}
		}
	}

	public void sleepTillAnimation() {
		int count = 0;
		Timer time = new Timer(4000);
		do
			Time.sleep(count++ == 0 ? 2500 : 200);
		while (time.isRunning() && Players.getLocal().getAnimation() != -1);
	}
}
