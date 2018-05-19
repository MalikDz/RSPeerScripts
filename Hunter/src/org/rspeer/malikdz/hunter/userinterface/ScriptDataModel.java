package org.rspeer.malikdz.hunter.userinterface;

import org.rspeer.malikdz.hunter.data.Hunter;
import org.rspeer.malikdz.hunter.userinterface.framework.AbstractScriptDataModel;

/**
 * 
 * @author MalikDz
 *
 */
public class ScriptDataModel extends AbstractScriptDataModel {

	private int loopDelay, index;
	private String trainingMethod;
	private Hunter hunterData;

	@Override
	public int getLoopDelay() {
		return loopDelay;
	}

	@Override
	public int getTypeIndex() {
		return index;
	}

	@Override
	public String getTrainingMethod() {
		return trainingMethod;
	}

	@Override
	public Hunter getHunterData() {
		return hunterData;
	}

	@Override
	public void setLoopDelay(int delay) {
		this.loopDelay = delay;
	}

	@Override
	public void setTypeIndex(int index) {
		this.index = index;
	}

	@Override
	public void setTrainingMethod(String method) {
		this.trainingMethod = method;
	}

	@Override
	public void setHunterData(Hunter data) {
		this.hunterData = data;
	}
}
