import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapView extends JPanel {

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

	/**
	 * Displays what is going on in the Model. Note: This method should NEVER be
	 * called directly; call repaint() instead.
	 * 
	 * @param g
	 *            The Graphics on which to paint things.
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		// set the background of the view
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(bImage, 0, 0, 600, 360, null);

	}
}
