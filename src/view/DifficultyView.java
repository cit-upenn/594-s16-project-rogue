package view;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This is class to draw the DifficultyView, used for chose difficulty
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
@SuppressWarnings("serial")
public class DifficultyView extends JPanel {

	/**
	 * Instance variable for DifficultyView
	 */
	private BufferedImage bImage;

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String imagePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bImage = img;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bImage, 0, 0, 600, 360, null);
	}
}
