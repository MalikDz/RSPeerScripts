package org.rspeer.malikdz.hunter.userinterface.framework;

/**
 * 
 * @author MalikDz
 *
 */

@SuppressWarnings("rawtypes")
public abstract class AbstractPresenter<T extends AbstractScriptDataModel, E extends AbstractView> {

	private E view;
	private T model;

	public AbstractPresenter(E view, T model) {
		this.view = view;
		this.model = model;
	}

	public E view() {
		return view;
	}

	public T model() {
		return model;
	}
}
