import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This class represents the easy game mode.
 * 
 * @author zhiyuanli
 *
 */
@SuppressWarnings("serial")
public class MapMenu extends JFrame {

	/**
	 * Game controller
	 */
	private WindowController controller;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton map1, map2, map3, back;
	private MapView mapView;

	/**
	 * GUI constants
	 */
	private static final Border WHITE_BORDER = new LineBorder(Color.WHITE, 2);
	private static final Color BLACK = new Color(0, 0, 0);
	private static final Color TEAL = new Color(0, 128, 128);

	/**
	 * difficulty level
	 */
	private String level;

	/**
	 * constructor
	 */
	public MapMenu(WindowController controller) {
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
		addImage("src/rogue.png");
	}

	/**
	 * helper method to construct the view
	 */
	private void display() {
		setTitle("Map");
		setPreferredSize(new Dimension(600, 400));
		setLocation();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layOutComponents();
		attachListenersToComponents();
	}

	/**
	 * set the location of map window
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
	private void addImage(String imagePath) {

		// adjust image size and add to view
		mapView = new MapView();
		mapView.setPreferredSize(new Dimension(600, 360));
		mapView.setImage(imagePath);
		mapView.repaint();
		center.add(mapView);
	}

	/**
	 * helper method to set the layout
	 */
	private void layOutComponents() {
		setLayout(new BorderLayout());
		addPanels();
		addSubPanels();
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

	/**
	 * set button attributes
	 * 
	 * @param button
	 *            the button to be set
	 */
	private void setButton(JButton button) {
		button.setForeground(Color.white);
		button.setBackground(TEAL);
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
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showGameFrame();
			}

		});

	}
}
