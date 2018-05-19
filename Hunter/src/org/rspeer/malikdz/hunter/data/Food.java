package org.rspeer.malikdz.hunter.data;

/**
 * 
 * @author MalikDz
 *
 */

public enum Food {

	TROUT, LOBSTER, SARDINE, SALMON, SWORDFISH, SHARK, MONKFISH;

	public String toString() {
		final String originalString = super.toString();
		return originalString.charAt(0) + originalString.substring(1, originalString.length()).toLowerCase();
	}
}
