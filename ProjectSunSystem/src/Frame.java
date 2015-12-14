import java.awt.EventQueue;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new EllipticalOrbitExample();
			}
		});
	}
}
