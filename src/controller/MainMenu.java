package controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This class represents the game lobby, which has 3 difficulty modes: Easy,
 * Medium, Hard.
 *
 * @author SHANG
 * 
 */
@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	/**
	 * Game controller
	 */
	private WindowController controller;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton easy, medium, hard;
	private JLabel gameView;

	/**
	 * GUI constants
	 */
	private static final Border WHITE_BORDER = new LineBorder(Color.WHITE, 2);
	private static final Color BLACK = new Color(0, 0, 0);
	private static final Color MEDIUM_ORCHID = new Color(186, 85, 211);

	/**
	 * constructor
	 */
	public MainMenu(WindowController controller) {
		this.controller = controller;
		display();
	}

	/**
	 * set visible of this frame
	 * 
	 * @param visible
	 */
	public void setFrameVisible(boolean visible) {
		setVisible(visible);
	}

	/**
	 * helper method to construct the lobby
	 */
	private void display() {
		setTitle("Lobby");
		layOutComponents();
		attachListenersToComponents();
		setPreferredSize(new Dimension(600, 400));
		setLocation();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * set the location of lobby window
	 */
	private void setLocation() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() / 2 - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() / 2 - this.getHeight()) / 2);
		setLocation(x, y);
	}

	/**
	 * helper method to add image to the view
	 */
	private void addImage() {

		// adjust image size and add to view
		ImageIcon image = new ImageIcon(getClass().getResource("rogue.png"));
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(600, 360, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(newImg);
		gameView = new JLabel(newImage);
		center.add(gameView);
	}

	/**
	 * helper method to set the layout
	 */
	private void layOutComponents() {
		setLayout(new BorderLayout());
		addPanels();
		addSubPanels();
		addImage();
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

	/**
	 * set button attributes
	 * 
	 * @param button
	 *            the button to be set
	 */
	private void setButton(JButton button) {
		button.setForeground(Color.white);
		button.setBackground(MEDIUM_ORCHID);
		button.setOpaque(true);
		button.setBorderPainted(true);
		button.setBorder(WHITE_BORDER);
		button.setFont(new Font("Arial", Font.PLAIN, 25));

	}

	/**
	 * attach action listeners to buttons
	 */
	private void attachListenersToComponents() {

		// add listeners to buttons
		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showMapFrame("easy");

			}

		});

		medium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showMapFrame("medium");

			}

		});

		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showMapFrame("hard");

			}

		});

	}

}
