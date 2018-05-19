package org.rspeer.malikdz.hunter.userinterface.framework;

import java.awt.AWTEvent;

/**
 * 
 * @author MalikDz
 *
 */

@SuppressWarnings("rawtypes")
public abstract class AbstractView<T extends AbstractPresenter> extends javax.swing.JFrame {

	private T presenter;
	private static final long serialVersionUID = 1L;

	public AbstractView(String title) {
		super(title);
	}

	public T presenter() {
		return presenter;
	}

	public void setPresenter(T presenter) {
		this.presenter = presenter;
	}

	public abstract void updateModelFromView(AWTEvent event);

	public abstract void updateViewFromModel();

	public abstract void releaseRessources();

	public abstract void coverDisplay();

	public abstract boolean isHiden();

	public abstract void display();

}
