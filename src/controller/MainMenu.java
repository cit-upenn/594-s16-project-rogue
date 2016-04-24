package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the game lobby(main menu), which has 3 difficulty modes: Easy,
 * Medium, Hard.
 *
 * @author SHANG
 * 
 */
@SuppressWarnings("serial")
public class MainMenu extends Controller {

	/**
	 * Game controller
	 */
	private WindowSwitcher switcher;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton easy, medium, hard;
	private JLabel lobbyView;

	/**
	 * constructor
	 */
	public MainMenu(WindowSwitcher controller) {
		this.switcher = controller;
	}
	
	@Override
	public void enable() {
		display("Lobby", 600, 400);
		setVisible(true);
	}

	@Override
	public void layOutComponents() {
		setLayout(new BorderLayout());
		addPanels();
		addSubPanels();
		addImage();
	}

	/**
	 * helper method to add image to the center of main menu layout
	 */
	private void addImage() {

		// adjust image size and add to view
		ImageIcon image = new ImageIcon("rogue.png");
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(600, 360, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(newImg);
		lobbyView = new JLabel(newImage);
		center.add(lobbyView);
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
		bottom.setLayout(new GridLayout(1, 3));
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

	}

	@Override
	public void attachListenersToComponents() {

		// add listeners to buttons
		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showMapMenu("easy");

			}

		});

		medium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showMapMenu("medium");

			}

		});

		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showMapMenu("hard");

			}

		});

	}

}
