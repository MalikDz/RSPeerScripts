package org.rspeer.malikdz.hunter;

import java.util.List;
import java.util.Timer;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.SwingUtilities;

import org.rspeer.malikdz.hunter.data.Hunter;
import org.rspeer.malikdz.hunter.nodes.CustomNode;
import org.rspeer.malikdz.hunter.nodes.impl.hunting.TrapRemover;
import org.rspeer.malikdz.hunter.nodes.impl.hunting.TrapsChecker;
import org.rspeer.malikdz.hunter.nodes.impl.hunting.TrapsLooting;
import org.rspeer.malikdz.hunter.nodes.impl.hunting.TrapsSetting;
import org.rspeer.malikdz.hunter.nodes.impl.hunting.TrapResetting;
import org.rspeer.malikdz.hunter.nodes.impl.hunting.InventoryCleaner;

import org.rspeer.malikdz.hunter.renderers.impl.TilesRenderer;
import org.rspeer.malikdz.hunter.renderers.impl.ScriptInformationRenderer;

import org.rspeer.malikdz.hunter.userinterface.ScriptView;
import org.rspeer.malikdz.hunter.userinterface.ScriptDataModel;
import org.rspeer.malikdz.hunter.userinterface.ScriptPresenter;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.EventDispatcher;
import org.rspeer.runetek.event.listeners.RenderListener;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

import org.rspeer.ui.Log;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;

/**
 * 
 * @author MalikDz
 *
 */
@ScriptMeta(name = "Hunter", developer = "MalikDz", desc = "Hunt some cool creatures")
public class Boot extends Script {

	private Hunter data;
	private int delay = 150;
	private ScriptView scriptView;
	private ScriptDataModel dataModel;
	private Timer timer = new Timer();
	private boolean scriptInitiated = false;
	private List<CustomNode> nodes = new ArrayList<CustomNode>();
	private RenderListener scriptInformationRenderer, tilesRenderer;

	private void initiateScript() {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					scriptView = new ScriptView();
					dataModel = new ScriptDataModel();
					ScriptPresenter presenter = new ScriptPresenter(scriptView, dataModel);
					scriptView.setPresenter(presenter);
				}
			});
			sleep(3000);
			while (!scriptView.isHiden())
				sleep(1000);
			data = dataModel.getHunterData();
			delay = dataModel.getLoopDelay();
			Log.info(dataModel.getTrainingMethod() == null ? "null" : "nonnull");
			CustomNode.getOurZonesTiles(Players.getLocal().getPosition());
			CustomNode.offset = dataModel.getTrainingMethod().equalsIgnoreCase("Black chins") ? 1 : 0;
			Log.info(String.format("Started script at %s with %d hunter", dataModel.getTrainingMethod(), Skills.getLevel(Skill.HUNTER)));
			timer.schedule(new TrapsChecker(data.getIdleName(), data.getFailTrapName(), data.getCheckTrapName(),data.getItemName()), 0, 1500);
			scriptInitiated = true;
			registerRenderers();
			registerTasks();
		} catch (Exception exception) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			Log.info(sw.toString());
		}
	}

	private void proccessNodes() {
		for (CustomNode t : nodes) {
			if (t.canRun()) {
				t.run();
				break;
			}
		}
	}

	private void registerTasks() {
		nodes.add(new InventoryCleaner());
		nodes.add(new TrapResetting(data.getItemName()));
		nodes.add(new TrapsSetting(data.getItemName()));
		nodes.add(new TrapsLooting(data.getItemName(), data.getCheckTrapName(), data.getCheckTrapActions()));
		nodes.add(new TrapRemover(data.getItemName(), data.getFailTrapName(), data.getFailTrapActions()));
	}

	private void registerRenderers() {
		EventDispatcher dispatcher = Game.getEventDispatcher();
		dispatcher.register(scriptInformationRenderer = new ScriptInformationRenderer(dataModel.getTrainingMethod(), data.getExperiences()[dataModel.getTypeIndex()]));
		dispatcher.register(tilesRenderer = new TilesRenderer());
	}

	@Override
	public void onStop() {
		EventDispatcher dispatcher = Game.getEventDispatcher();
		dispatcher.deregister(scriptInformationRenderer);
		dispatcher.deregister(tilesRenderer);
		timer.cancel();
		timer.purge();
	}

	@Override
	public int loop() {
		if (!scriptInitiated) initiateScript();
		else proccessNodes();
		return delay;
	}
}
