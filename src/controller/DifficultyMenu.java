package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.DifficultyView;

/**
 * This class represents the easy/medium/hard mode map selection menu.
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
@SuppressWarnings("serial")
public class DifficultyMenu extends Controller {

	/**
	 * Game window switcher
	 */
	private WindowSwitcher switcher;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton easy, medium, hard, back;
	private DifficultyView difficultyView;
	private String[] startMapFiles;
	private String backgroundImage;

	/**
	 * difficulty level
	 */
	private String level;

	/**
	 * constructor
	 */
	public DifficultyMenu(WindowSwitcher controller) {
		this.switcher = controller;
		// initialize the background image
		backgroundImage = "pic/rogue.png";
		// initialize the entrance map for 3 difficulty levels
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
		addImage(backgroundImage);
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

		// add 3 option buttons
		easy = createButton("Easy");
		setButton(easy);
		bottom.add(easy);
		medium = createButton("Medium");
		setButton(medium);
		bottom.add(medium);
		hard = createButton("Hard");
		setButton(hard);
		bottom.add(hard);
		back = createButton("Back");
		setButton(back);
		bottom.add(back);

	}
	
	private JButton createButton(String name) {
		ImageIcon image = new ImageIcon("pic/mapbutton.jpg");
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(220, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(newImg);
		JButton b = new JButton(name, newImage);
		b.setVerticalTextPosition(SwingConstants.CENTER);
		b.setHorizontalTextPosition(SwingConstants.CENTER);
		return b;
	}

	@Override
	public void setButton(JButton button) {
		button.setForeground(Color.white);
		button.setOpaque(true);
		button.setBorderPainted(true);
		button.setBorder(WHITE_BORDER);
		button.setFont(new Font("Book Antiqua", Font.BOLD, 20));
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
