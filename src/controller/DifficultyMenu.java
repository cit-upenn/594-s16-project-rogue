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

import view.DifficultyView;

/**
 * This class represents the easy/medium/hard mode map selection menu.
 * 
 * @author zhiyuanli
 *
 */
@SuppressWarnings("serial")
public class DifficultyMenu extends Controller {

	/**
	 * Game controller
	 */
	private WindowSwitcher switcher;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton easy, medium, hard, back;
	private DifficultyView difficultyView;
	private String[] startMapFiles;
	private String imageFile;

	/**
	 * difficulty level
	 */
	private String level;

	/**
	 * constructor
	 */
	public DifficultyMenu(WindowSwitcher controller) {
		this.switcher = controller;
		imageFile = "pic/rogue.png";
		startMapFiles = new String[] { "dungeon/1.txt", "dungeon/2.txt", "dungeon/3.txt" };
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
		difficultyView = new DifficultyView();
		difficultyView.setPreferredSize(new Dimension(600, 360));
		difficultyView.setImage(imagePath);
		difficultyView.repaint();
		center.add(difficultyView);
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
		easy = new JButton("Easy");
		setButton(easy);
		bottom.add(easy);
		medium = new JButton("Medium");
		medium.setForeground(Color.white);
		setButton(medium);
		bottom.add(medium);
		hard = new JButton("Hard");
		setButton(hard);
		bottom.add(hard);
		back = new JButton("Back");
		setButton(back);
		bottom.add(back);

	}

	@Override
	public void setButton(JButton button) {
		button.setForeground(Color.white);
		// button.setBackground(MEDIUM_ORCHID);
		button.setOpaque(true);
		button.setBorderPainted(true);
		button.setBorder(WHITE_BORDER);
		button.setFont(new Font("Arial", Font.PLAIN, 25));
		button.setBackground(CRIMSON);
	}

	@Override
	public void attachListenersToComponents() {

		// add listeners to buttons
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showGame(startMapFiles[0]);
			}
		});

		medium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showGame(startMapFiles[1]);
			}
		});

		hard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showGame(startMapFiles[2]);
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
