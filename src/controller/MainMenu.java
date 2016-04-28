package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the game lobby(main menu)
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
	private JButton start, rules, authorInfo;
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
		ImageIcon image = new ImageIcon("pic/rogue.png");
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
		start = new JButton("Start");
		setButton(start);
		bottom.add(start);
		rules = new JButton("Rules");
		setButton(rules);
		bottom.add(rules);
		authorInfo = new JButton("About Us");
		setButton(authorInfo);
		bottom.add(authorInfo);

	}

	@Override
	public void setButton(JButton button) {
		button.setForeground(Color.white);
		button.setBackground(MEDIUM_ORCHID);
		button.setOpaque(true);
		button.setBorderPainted(true);
		button.setBorder(WHITE_BORDER);
		button.setFont(new Font("Book Antiqua", Font.BOLD, 20));
	}

	@Override
	public void attachListenersToComponents() {

		// add listeners to buttons
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showDifficultyMenu();

			}

		});

		rules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		authorInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

	}

}
