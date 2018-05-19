package org.rspeer.malikdz.hunter.renderers;

import org.rspeer.runetek.event.listeners.RenderListener;

/**
 * 
 * @author MalikDz
 * 
 */

public abstract class AbstractRenderer<T> implements RenderListener {

	protected T ctx;

	protected AbstractRenderer(T ctx) {
		this.ctx = ctx;
	}
}
