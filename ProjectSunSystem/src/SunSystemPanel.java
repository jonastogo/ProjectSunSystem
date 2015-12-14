import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SunSystemPanel extends JPanel {
	Point	sunPoint	= new Point();
	Point	ovalPoint	= new Point();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		sunPoint.x = 200;
		sunPoint.y = 200;
		ovalPoint.x = 700;
		ovalPoint.y = 550;

		g.setColor(Color.yellow);
		g.fillOval((this.getWidth() / 2) - (sunPoint.x / 2), (this.getHeight() / 2) - (sunPoint.y / 2), sunPoint.x, sunPoint.y);
		g.setColor(Color.white);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawOval((this.getWidth() / 2) - (ovalPoint.x / 2), (this.getHeight() / 2) - (ovalPoint.y / 2), ovalPoint.x, ovalPoint.y);
		((Graphics2D) g).setStroke(new BasicStroke(1));

	}
}
