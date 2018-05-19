package org.rspeer.malikdz.hunter.renderers;

import org.rspeer.runetek.event.listeners.RenderListener;

/**
 * 
 * @author MalikDz
 * 
 */

//Note: NOT NEEDED FOR RSPEER BECAUSE OF THE STATIC API
public abstract class AbstractRenderer<T> implements RenderListener {

	protected T ctx;

	protected AbstractRenderer(T ctx) {
		this.ctx = ctx;
	}
}
