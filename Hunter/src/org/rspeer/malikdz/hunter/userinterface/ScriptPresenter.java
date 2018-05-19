package org.rspeer.malikdz.hunter.userinterface;

import org.rspeer.malikdz.hunter.data.Hunter;
import org.rspeer.malikdz.hunter.userinterface.framework.AbstractPresenter;

/**
 * 
 * @author MalikDz
 *	
 */

public class ScriptPresenter extends AbstractPresenter<ScriptDataModel, ScriptView> {

	public ScriptPresenter(ScriptView view, ScriptDataModel model) {
		super(view, model);
	}

	public void updateModelData(String trainingMethod) {
		switch (trainingMethod) {
		case "Crimson swift":
			model().setHunterData(Hunter.BIRDS);
			model().setTypeIndex(0);
			break;
		case "Tropical wagtail":
			model().setHunterData(Hunter.BIRDS);
			model().setTypeIndex(1);
			break;
		case "Grey chins":
			model().setHunterData(Hunter.CHINS);
			model().setTypeIndex(0);
			break;
		case "Red chins":
			model().setHunterData(Hunter.CHINS);
			model().setTypeIndex(1);
			break;
		case "Black chins":
			model().setHunterData(Hunter.CHINS);
			model().setTypeIndex(2);
			break;
		}
		model().setTrainingMethod(trainingMethod);
	}
}
