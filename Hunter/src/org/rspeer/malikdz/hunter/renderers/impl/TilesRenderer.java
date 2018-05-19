package org.rspeer.malikdz.hunter.renderers.impl;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics2D;

import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.malikdz.hunter.nodes.CustomNode;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.event.listeners.RenderListener;


/**
 * 
 * @author MalikDz
 *
 */

public class TilesRenderer implements RenderListener {

	private static final Color TILE_FILING_COLOR = new Color(130, 30, 30, 80);

	@Override
	public void notify(RenderEvent event) {
		Graphics2D g = (Graphics2D) event.getSource();
		for (Position p : CustomNode.trapPositions) {
			Polygon onScreenPosition =Projection.getTileShape(p);
			g.setColor(TILE_FILING_COLOR);
			g.fill(onScreenPosition);
			g.setColor(Color.BLACK);
			g.draw(onScreenPosition);
		}
	}
}
