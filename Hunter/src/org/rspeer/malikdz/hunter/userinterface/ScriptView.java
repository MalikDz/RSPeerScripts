package org.rspeer.malikdz.hunter.userinterface;

import java.awt.Point;
import java.awt.Insets;
import java.awt.AWTEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.rspeer.malikdz.hunter.userinterface.framework.AbstractView;

/**
 * 
 * @author MalikDz
 * @author JFormDesigner
 *
 */

@SuppressWarnings("serial")
public class ScriptView extends AbstractView<ScriptPresenter> {

	private JButton startScript;
	private JTextField delayText;
	private JComboBox<String> hunterChoices;
	private JLabel titleLabel, delayLabel, methodLabel;
	public static final String[] HUNTER_CHOICES = { "Crimson swift", "Tropical wagtail", "Grey chins", "Red chins", "Black chins" };

	public ScriptView() {
		super("nullHunter v0.00");
		titleLabel = new JLabel("nullHunter");
		delayLabel = new JLabel("Sleep delay :");
		methodLabel = new JLabel("Hunter method :");
		startScript = new JButton("Start script !");
		delayText = new JTextField("150");
		hunterChoices = new JComboBox<String>(HUNTER_CHOICES);
		hunterChoices.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				updateModelFromView(event);
			}
		});
		Container ViewportContentPane = getContentPane();
		ViewportContentPane.setLayout(null);

		ViewportContentPane.add(methodLabel);
		methodLabel.setBounds(new Rectangle(new Point(50, 60), methodLabel.getPreferredSize()));
		ViewportContentPane.add(hunterChoices);
		hunterChoices.setBounds(143, 60, 100, 20);

		titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getSize() + 12f));
		ViewportContentPane.add(titleLabel);
		titleLabel.setBounds(85, 25, 130, 25);

		ViewportContentPane.add(delayLabel);
		delayLabel.setBounds(new Rectangle(new Point(65, 85), delayLabel.getPreferredSize()));
		ViewportContentPane.add(delayText);
		delayText.setBounds(143, 85, 100, 20);

		ViewportContentPane.add(startScript);
		startScript.setBounds(new Rectangle(new Point(95, 110), startScript.getPreferredSize()));
		startScript.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateModelFromView(arg0);
				coverDisplay();
			}
		});

		Dimension preferredSize = new Dimension();
		for (int i = 0; i < ViewportContentPane.getComponentCount(); i++) {
			Rectangle bounds = ViewportContentPane.getComponent(i).getBounds();
			preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
			preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
		}

		setSize(310, 195);
		Insets insets = ViewportContentPane.getInsets();
		preferredSize.width += insets.right;
		preferredSize.height += insets.bottom;
		ViewportContentPane.setMinimumSize(preferredSize);
		ViewportContentPane.setPreferredSize(preferredSize);
		setLocationRelativeTo(null);
		display();
	}

	@Override
	public void updateModelFromView(AWTEvent event) {
		presenter().updateModelData(hunterChoices.getSelectedItem().toString());
	}

	@Override
	public void updateViewFromModel() {
	}

	@Override
	public void releaseRessources() {
		dispose();
	}

	@Override
	public void coverDisplay() {
		setVisible(false);
	}

	@Override
	public boolean isHiden() {
		return !isVisible();
	}

	@Override
	public void display() {
		setVisible(true);
	}
}
