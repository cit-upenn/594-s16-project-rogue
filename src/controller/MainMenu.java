package controller;

import java.awt.BorderLayout;
import java.awt.Button;
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
import javax.swing.SwingConstants;

/**
 * This class represents the game lobby(main menu), inherits from Controller
 * class
 *
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 * 
 */
@SuppressWarnings("serial")
public class MainMenu extends Controller {

	/**
	 * Game window switcher
	 */
	private WindowSwitcher switcher;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton start, rules, authorInfo;
	private JLabel lobbyView;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            window switcher
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

		// add 3 option buttons
		start = createButton("Start");
		setButton(start);
		bottom.add(start);
		rules = createButton("Rules");
		setButton(rules);
		bottom.add(rules);
		authorInfo = createButton("About Us");
		setButton(authorInfo);
		bottom.add(authorInfo);

	}

	private JButton createButton(String name) {
		ImageIcon image = new ImageIcon("pic/lobbybutton.jpg");
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
