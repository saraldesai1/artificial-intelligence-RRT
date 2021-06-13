import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PolygonsJPanel extends JPanel {
	public static boolean answer;

	 public void paintComponent( Graphics g )
	 {
		 int sx=start.startpointx;
		 int sy=start.startpointy;
		 int ex=start.endpointx;
		 int ey=start.endpointy;
		 int co=start.nodecount;
		 int en =start.enviroment;
		 
		super.paintComponent( g );
		if(en == 1)
		{
			g.setColor(Color.CYAN);
			g.fillRect(40, 40, 50, 40);
			g.setColor(Color.CYAN);
			
			g.setColor(Color.red);
			g.fillOval(sx, sy, 5, 5);
			g.fillOval(ex, ey, 5, 5);
			g.setColor(Color.BLACK);
			RRT rrt = new RRT(sx, sy, ex, ey,co,en,g);
			rrt.run();
		}
		if(en == 2)
		{
			g.setColor(Color.CYAN);
			g.fillRect(20, 20, 90, 40);
			
			g.setColor(Color.CYAN);
			g.fillRect(100, 100, 70, 70);
			
			g.setColor(Color.red);
			g.fillOval(sx, sy, 5, 5);
			g.setColor(Color.GREEN);
			g.fillOval(ex, ey, 5, 5);
			g.setColor(Color.BLACK);
			RRT rrt = new RRT(sx, sy, ex, ey,co,en,g);
			rrt.run1();
		}
		
		if(en == 3)
		{
			g.setColor(Color.CYAN);
			g.fillRect(20, 20, 90, 40);
			
			g.setColor(Color.CYAN);
			g.fillRect(100, 100, 70, 70);
			
			g.setColor(Color.CYAN);
			g.fillRect(200, 200, 90, 40);
			
			
			g.setColor(Color.red);
			g.fillOval(sx, sy, 5, 5);
			g.setColor(Color.GREEN);
			g.fillOval(ex, ey, 5, 5);
			g.setColor(Color.BLACK);
			RRT rrt = new RRT(sx, sy, ex, ey,co,en,g);
			rrt.run2();
		}
		
	 }
}
