package org.rspeer.malikdz.hunter.renderers.impl;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;

import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.listeners.RenderListener;

/**
 * 
 * @author MalikDz
 *
 */

public class ScriptInformationRenderer implements RenderListener {

	private String trainingMethod;
	private long experienceGained;
	private int startXp, totalCatch, xpCatch;
	private long startTime, millis, hours, minutes, seconds;

	private static final Font TITLE_FONT = new Font("Lao UI", 1, 11);
	private static final Font INFORMATION_FONT = new Font("Lao UI", 1, 10);
	private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 116);
	private static final Color SOME_COLOR = new Color(255, 255, 255);

	public ScriptInformationRenderer( String trainingMethod, int xpCatch) {
		this.xpCatch = xpCatch;
		this.trainingMethod = trainingMethod;
		this.startTime = System.currentTimeMillis();
		this.startXp = Skills.getExperience(Skill.HUNTER);
	}

	@Override
	public void notify(RenderEvent event) {
		Graphics2D g = (Graphics2D) event.getSource();
		updateTimer();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(11, 34, 140, 78);
		g.setColor(Color.WHITE);
		g.drawRect(11, 34, 140, 78);
		g.setFont(TITLE_FONT);
		g.setColor(SOME_COLOR);
		g.drawString("nullHunter v0.00", 15, 50);
		g.setFont(INFORMATION_FONT);
		g.drawString("Hunting : " + trainingMethod, 15, 75);
		g.drawString("Caughts : " + totalCatch + " (" + catchHour() + ")", 15, 88);
		g.drawString("Experience : " + experienceGained() + " (" + experienceHour() + ")", 15, 101);
		g.drawString("Time : " + hours + " : " + minutes + " : " + seconds, 15, 63);
	}

	private void updateTimer() {
		millis = System.currentTimeMillis() - startTime;
		hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		seconds = millis / 1000;
	}

	private int experienceGained() {
		return (int) (experienceGained = Skills.getExperience(Skill.HUNTER) - startXp);
	}

	private int experienceHour() {
		return (int) ((experienceGained) * 3600000D / (System.currentTimeMillis() - startTime));
	}

	private int catchHour() {
		return (int) ((totalCatch = (int) (experienceGained / xpCatch)) * 3600000D/ (System.currentTimeMillis() - startTime));
	}
}
