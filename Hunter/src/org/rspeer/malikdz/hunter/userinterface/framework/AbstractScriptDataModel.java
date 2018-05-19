package org.rspeer.malikdz.hunter.userinterface.framework;

import org.rspeer.malikdz.hunter.data.Hunter;

/**
 * 
 * @author MalikDz
 *
 */

public abstract class AbstractScriptDataModel {

	public abstract int getTypeIndex();

	public abstract int getLoopDelay();

	public abstract String getTrainingMethod();

	public abstract Hunter getHunterData();

	public abstract void setTypeIndex(int Index);

	public abstract void setLoopDelay(int delay);

	public abstract void setTrainingMethod(String method);

	public abstract void setHunterData(Hunter data);
}
