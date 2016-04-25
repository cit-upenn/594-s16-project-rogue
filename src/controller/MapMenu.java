package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.MapView;

/**
 * This class represents the easy/medium/hard mode map selection menu.
 * 
 * @author zhiyuanli
 *
 */
@SuppressWarnings("serial")
public class MapMenu extends Controller {

	/**
	 * Game controller
	 */
	private WindowSwitcher switcher;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton map1, map2, map3, back;
	private MapView mapView;
	private String[] mapFiles;
	private String imageFile;

	/**
	 * difficulty level
	 */
	private String level;

	/**
	 * constructor
	 */
	public MapMenu(WindowSwitcher controller) {
		this.switcher = controller;

	}

	@Override
	public void enable() {
		display("Map Menu", 600, 400);
		setVisible(true);
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
		// TODO: change each level background image
		switch (level) {
		case "easy":
			imageFile = "rogue.png";
			mapFiles = new String[] { "dungeonA.txt", "dungeonB.txt", "dungeonC.txt" };
			break;

		case "medium":
			imageFile = "rogue.png";
			mapFiles = new String[] { "dungeonF.txt", "dungeonG.txt", "dungeonH.txt" };
			break;

		case "hard":
			imageFile = "rogue.png";
			mapFiles = new String[] { "dungeonI.txt", "dungeonJ.txt", "dungeonM.txt" };
			break;

		default:
			break;
		}

	}

	@Override
	public void layOutComponents() {
		setLayout(new BorderLayout());
		addPanels();
		addSubPanels();
		addImage(imageFile);
	}

	/**
	 * helper method to add image to the the center of the map menu layout
	 */
	private void addImage(String imagePath) {

		// adjust image size and add to view
		mapView = new MapView();
		mapView.setPreferredSize(new Dimension(600, 360));
		mapView.setImage(imagePath);
		mapView.repaint();
		center.add(mapView);
	}

	/**
	 * helper method to add 5 panels
	 */
	private void addPanels() {
		top = new JPanel();
		left = new JPanel();
		center = new JPanel();
		right = new JPanel();
		bottom = new JPanel();

		// add them to the frame
		add(top, BorderLayout.NORTH);
		add(left, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		add(right, BorderLayout.EAST);
		add(bottom, BorderLayout.SOUTH);

	}

	/**
	 * helper method to add sub-panels within the bottom panel
	 */
	private void addSubPanels() {

		// set buttom panel layout
		bottom.setLayout(new GridLayout(1, 4));
		bottom.setBackground(BLACK);

		// add 3 option buttons
		map1 = new JButton("Map 1");
		setButton(map1);
		bottom.add(map1);
		map2 = new JButton("Map 2");
		map2.setForeground(Color.white);
		setButton(map2);
		bottom.add(map2);
		map3 = new JButton("Map 3");
		setButton(map3);
		bottom.add(map3);
		back = new JButton("Back");
		setButton(back);
		bottom.add(back);

	}
	
	@Override
	public void setButton(JButton button) {
		button.setForeground(Color.white);
//		button.setBackground(MEDIUM_ORCHID);
		button.setOpaque(true);
		button.setBorderPainted(true);
		button.setBorder(WHITE_BORDER);
		button.setFont(new Font("Arial", Font.PLAIN, 25));
		
		switch(level) {
		case "easy":
			button.setBackground(CRIMSON);	
			break;
		case "medium":
			button.setBackground(SIENNA);
			break;
		case "hard":
			button.setBackground(TEAL);
			break;
		}
	}

	@Override
	public void attachListenersToComponents() {

		// add listeners to buttons
		map1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showGame(mapFiles[0]);
			}
		});

		map2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showGame(mapFiles[1]);
			}
		});

		map3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showGame(mapFiles[2]);
			}
		});

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showMainMenu();
			}

		});

	}
}
