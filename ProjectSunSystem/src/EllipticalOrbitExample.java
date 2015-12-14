import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class EllipticalOrbitExample extends JFrame {
	Point					size		= new Point();
	volatile BufferStrategy	bufferStrategy;

	Runnable				renderLoop	= new Runnable() {
											Point	sunPoint	= new Point();
											Point	center		= new Point();
											Point	ovalPoint	= new Point();
											Point	earthPoint	= new Point();

											public void run() {
												center.x = (size.x / 2);
												center.y = (size.y / 2);
												sunPoint.x = 200;
												sunPoint.y = 200;
												ovalPoint.x = 700;
												ovalPoint.y = 550;
												earthPoint.x = 100;
												earthPoint.y = 100;
												Image sun = null;
												try {
													sun = ImageIO.read(new URL("http://i.imgur.com/85mYqQy.png")).getScaledInstance(sunPoint.x, sunPoint.y, BufferedImage.SCALE_SMOOTH);
												} catch (Exception e) {
													e.printStackTrace();
												}
												Image earth = null;
												try {
													earth = ImageIO.read(new URL("http://i.imgur.com/hvUE093.jpg")).getScaledInstance(earthPoint.x, earthPoint.y, BufferedImage.SCALE_SMOOTH);
												} catch (Exception e) {
													e.printStackTrace();
												}
												double angle = 0;
												double step = 0.02;
												int radiusA = ovalPoint.x / 2;
												int radiusB = ovalPoint.y / 2;
												while (true) {
													Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
													RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
													rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
													g.setRenderingHints(rh);

													g.setBackground(Color.black);

													int x = (int) (radiusA * Math.sin(angle)) + center.x;
													int y = (int) (radiusB * Math.cos(angle)) + center.y;

													g.clearRect(0, 0, getWidth(), getHeight());

													g.setColor(Color.yellow);
													g.drawImage(sun, (center.x) - (sunPoint.x / 2), (center.y) - (sunPoint.y / 2), null);

													g.setColor(Color.white);
													((Graphics2D) g).setStroke(new BasicStroke(3));
													g.drawOval((size.x / 2) - (ovalPoint.x / 2), (size.y / 2) - (ovalPoint.y / 2), ovalPoint.x, ovalPoint.y);

													((Graphics2D) g).setStroke(new BasicStroke(1));

													g.setColor(Color.blue);
													g.drawImage(earth, x - earthPoint.x / 2, y - earthPoint.y / 2, null);

													bufferStrategy.show();

													g.dispose();

													try {
														TimeUnit.MILLISECONDS.sleep(40);
													} catch (InterruptedException e) {
														e.printStackTrace();
													}

													angle += step;
													angle %= 360;
												}

											}
										};

	public EllipticalOrbitExample() {
		super("EllipticalOrbitExample");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		size.x = 1000;
		size.y = 1000;
		setSize(size.x, size.y);
		setVisible(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);

		createBufferStrategy(3);

		this.bufferStrategy = getBufferStrategy();

		Executors.newSingleThreadExecutor().execute(renderLoop);
	}
}
