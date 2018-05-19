package org.rspeer.malikdz.hunter.data;

/**
 * 
 * @author MalikDz
 *
 */

public enum Hunter {

	BIRDS(new int[] { 34, 95 }, "Bird snare", "Bird snare", "Bird snare", "Bird snare", new String[] { "Dismantle", "" }, new String[] { "Check", "" }), 
	CHINS(new int[] { 198, 265, 315 }, "Box trap", "Box trap", "Shaking box", "Box trap", new String[] { "Dismantle", "" }, new String[] { "Check", "" });

	private int[] experiences;
	private String[] failTrapActions, checkTrapActions;
	private String itemName, failTrapName, checkTrapName, idleName;

	Hunter(int[] experiences, String itemName, String failTrapName, String checkTrapName, String idleName, String[] failTrapActions, String[] checkTrapActions) {

		this.checkTrapActions = checkTrapActions;
		this.failTrapActions = failTrapActions;
		this.checkTrapName = checkTrapName;
		this.failTrapName = failTrapName;
		this.experiences = experiences;
		this.idleName = idleName;
		this.itemName = itemName;
	}

	public String[] getFailTrapActions() {
		return failTrapActions;
	}

	public String[] getCheckTrapActions() {
		return checkTrapActions;
	}

	public String getCheckTrapName() {
		return checkTrapName;
	}

	public int[] getExperiences() {
		return experiences;
	}

	public String getFailTrapName() {
		return failTrapName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getIdleName() {
		return idleName;
	}

	public String toString() {
		final String originalString = super.toString();
		return originalString.charAt(0) + originalString.substring(1, originalString.length()).toLowerCase();
	}
}
