import java.util.Scanner;

import javax.swing.JFrame;

public class start {
	public static int startpointx;
	public static int startpointy;
	public static int endpointx;
	public static int endpointy;
	public static int nodecount;
	public static int enviroment;
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int loop;
		System.out.print("chose the enverimont from 1,2 ,3 and to exit 0: ");
		loop=input.nextInt();
		JFrame frame = new JFrame( "RRT Visualization" );
		
		while(loop!=0)
		{
			enviroment=loop;
		if(loop==1)
		{
		System.out.println("Enter start point from 1 to 99 x:");
		startpointx = input.nextInt();
		System.out.println("Enter start point 1 to 99 Y:");
		startpointy=input.nextInt();
		System.out.println("Enter end point 1 to 99 X:");
		endpointx=input.nextInt();
		System.out.println("Enter end point 1 to 99 Y:");
		endpointy=input.nextInt();
		System.out.println("Enter the times you would like to generate nodes and if you keep the amount 10,000 it will mostly find the goal: ");
		nodecount=input.nextInt();
		
		frame.dispose();
		frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		PolygonsJPanel polygonsJPanel = new PolygonsJPanel();
		frame.add( polygonsJPanel ); 
		frame.setSize(550, 550); 
		frame.setVisible( true );
		
		}
		if(loop==2)
		{
		System.out.println("Enter start point from 1 to 199 x:");
		startpointx = input.nextInt();
		System.out.println("Enter start point 1 to 199 Y:");
		startpointy=input.nextInt();
		System.out.println("Enter end point 1 to 199 X:");
		endpointx=input.nextInt();
		System.out.println("Enter end point 1 to 199 Y:");
		endpointy=input.nextInt();
		System.out.println("Enter the times you would like to generate nodes and if you keep the amount 10,000 it will mostly find the goal: ");
		nodecount=input.nextInt();
		frame.dispose();
		frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		PolygonsJPanel polygonsJPanel = new PolygonsJPanel();	
		frame.add( polygonsJPanel ); 
		frame.setSize(550, 550); 
		frame.setVisible( true );
		
		
		}
		if(loop==3)
		{
		System.out.println("Enter start point from 1 to 299 x:");
		startpointx = input.nextInt();
		System.out.println("Enter start point 1 to 299 Y:");
		startpointy=input.nextInt();
		System.out.println("Enter end point 1 to 299 X:");
		endpointx=input.nextInt();
		System.out.println("Enter end point 1 to 299 Y:");
		endpointy=input.nextInt();
		System.out.println("Enter the times you would like to generate nodes and if you keep the amount 10,000 it will mostly find the goal: ");
		nodecount=input.nextInt();
		frame.dispose();
		frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		PolygonsJPanel polygonsJPanel = new PolygonsJPanel();	
		frame.add( polygonsJPanel ); 
		frame.setSize(550, 550); 
		frame.setVisible( true );
		}
		
		System.out.println("chose the enverimont from 1,2 ,3 and to exit 0: ");
		loop=input.nextInt();
		
		if(loop==0) 
		{
			System.exit(0);
		}
		}
		input.close();
		
	}
}
