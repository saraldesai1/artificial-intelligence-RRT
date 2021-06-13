import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class RRT {
	private int start_x;
	private int start_y;
	private int goal_x;
	private int goal_y;
	private int node_count;
	private int enviroment_selected;
	private Graphics g;
	Random rand = new Random();
	Node goalNode; 
	Node startNode;
	Tree tree;

	public RRT(int i, int j, int k, int l, int m, int envi, Graphics g2) {
		start_x=i;
		start_y=j;
		goal_x=k;
		goal_y=l;
		node_count=m;
		enviroment_selected=envi;
		g=g2;
		tree = new Tree(start_x,start_y,goal_x,goal_y);
		goalNode = new Node(goal_x,goal_y);
		startNode= new Node(start_x,start_y);
		
	}
	public void run()
	{
		int i=0;
		Node intermediateNode;
		
		
		while(i<node_count)
		{
			Node randomNode = selectRandomNode();
			Node nearestNode = tree.nearestNode(randomNode.getX(), randomNode.getY());
			
			
			if(isValidNode(randomNode,nearestNode))
			{
				
				while (distanceBetween(nearestNode, randomNode)>0) {
                    intermediateNode = moveTowardRandNode(nearestNode, randomNode,20);	
                    drawLineSegment(nearestNode, intermediateNode);
                    tree.add(nearestNode, intermediateNode);
                    nearestNode = intermediateNode;
                    
				}
				
                if (tree.goalfound(randomNode)) 
                {
                	tree.add(nearestNode, randomNode);
                	tree.add(nearestNode, goalNode);
                	ArrayList<Node>path = getPath(startNode,goalNode,tree);
                	draw(path);
                	System.out.println("Path found");
                	break;
                }
			}
			
			i++;
			if(i==node_count)
			{
				System.out.println("Path not found");
			}
		}
	}
	

	private void draw(ArrayList<Node> path)
	{
		g.setColor(Color.red);
		for(int j=1;j<path.size();j++)
		{
			g.drawLine(path.get(j-1).getX(), path.get(j-1).getY(), path.get(j).getX(), path.get(j).getY());
		}
		
	}


	private ArrayList<Node> getPath(Node startNode2, Node nearestNode, Tree tree2)
	{
		
		ArrayList<Node> path = new ArrayList<>();
		path.add(nearestNode);
		Node n = nearestNode;
			
		while(true)
		{
			n = n.getParent();
			path.add(n);
			if(n==startNode2)
			{
				return path;
			}
			if(n.getParent()==null)
			{
				return path;
			}
		}
		
	}

	private void drawLineSegment(Node nearestNode, Node intermediateNode) {

		g.drawLine(nearestNode.getX(),nearestNode.getY(),intermediateNode.getX(),intermediateNode.getY());
		
	}

	private Node moveTowardRandNode(Node nearestNode, Node randomNode, int edgeLength) {
        int x1 = nearestNode.getX();
        int y1 = nearestNode.getY();
        int x2 = randomNode.getX();
        int y2 = randomNode.getY();

        Node newNode = new Node(x2, y2);

        double d = distanceBetween(nearestNode, randomNode);

        if (d <= edgeLength) {
            newNode.setDistanceFromRoot(nearestNode.getDistanceFromRoot() + d);
            return newNode;
        }

        newNode.setDistanceFromRoot(nearestNode.getDistanceFromRoot() + edgeLength);

        double distFromLine;
        double distFromNearestNode;
        int num;
        double den;

        if (x1 > x2 && y1 > y2) {
            for (int x = x2; x <= x1; x++) {
                for (int y = y2; y <= y1; y++) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 > x2 && y1 < y2) {
            for (int x = x2; x <= x1; x++) {
                for (int y = y2; y >= y1; y--) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 < x2 && y1 > y2) {
            for (int x = x2; x >= x1; x--) {
                for (int y = y2; y <= y1; y++) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 < x2 && y1 < y2) {
            for (int x = x2; x >= x1; x--) {
                for (int y = y2; y >= y1; y--) {

                    num = Math.abs((x - x1) * (y1 - y2) - (y - y1) * (x1 - x2));
                    den = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

                    distFromLine = num / den;
                    distFromNearestNode = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

                    if (distFromLine <= 2 && distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                        newNode.setCoordinate(x, y);
                        return newNode;
                    }
                }
            }
        } else if (x1 == x2 && y1 > y2) {
            for (int y = y2; y <= y1; y++) {
                distFromNearestNode = Math.sqrt((y - y1) * (y - y1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x1, y);
                    return newNode;
                }
            }
        } else if (x1 == x2 && y1 < y2) {
            for (int y = y2; y >= y1; y--) {
                distFromNearestNode = Math.sqrt((y - y1) * (y - y1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x1, y);
                    return newNode;
                }
            }
        } else if (x1 > x2 && y1 == y2) {
            for (int x = x2; x <= x1; x++) {
                distFromNearestNode = Math.sqrt((x - x1) * (x - x1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x, y1);
                    return newNode;
                }
            }
        } else if (x1 < x2 && y1 == y2) {
            for (int x = x2; x >= x1; x--) {
                distFromNearestNode = Math.sqrt((x - x1) * (x - x1));

                if (distFromNearestNode <= edgeLength && distFromNearestNode > edgeLength - 2) {
                    newNode.setCoordinate(x, y1);
                    return newNode;
                }
            }
        }
        return null;
    }

	private boolean isValidNode(Node randomNode, Node nearestNode) {
		int x1=nearestNode.getX();
		int y1=nearestNode.getY();
		int x2=randomNode.getX();
		int y2=randomNode.getY();
		
		if(x1==x2 && y1==y2)
		{
			return false;
		}
		Line2D line = new Line2D.Double(x1,y1,x2,y2);
		boolean intere = line.intersects(goal_x, goal_y, 10, 10);
		if(intere)
		{
			return false;
		}
		Rectangle r1 = new Rectangle(40, 40, 50, 40);
		
		if(line.intersects(r1))
		{
			return false;
		}
		else
		return true;
		
	}

	private Node selectRandomNode() {
		int x = 0;
		int y=0;
		if(enviroment_selected==1)
		{
		x = rand.nextInt(0+99)+1;
		y = rand.nextInt(0+99)+1;
		}
		if(enviroment_selected ==2)
		{
			x = rand.nextInt(0+199)+1;
			y = rand.nextInt(0+199)+1;
		}
		if(enviroment_selected ==3)
		{
			x = rand.nextInt(0+399)+1;
			y = rand.nextInt(0+399)+1;
		}
		
		return new Node(x,y);
	}
	public double distanceBetween(Node n1, Node n2) {
        int x1 = n1.getX();
        int y1 = n1.getY();
        int x2 = n2.getX();
        int y2 = n2.getY();
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
	public void run1() {
		int i=0;
		Node intermediateNode;
		
		while(i<node_count)
		{
			Node randomNode = selectRandomNode();
			Node nearestNode = tree.nearestNode(randomNode.getX(), randomNode.getY());
			
			if(isValidNode1(randomNode,nearestNode))
			{
				
				while (distanceBetween(nearestNode, randomNode) > 0) {
                    intermediateNode = moveTowardRandNode(nearestNode, randomNode,20);	
                    drawLineSegment(nearestNode, intermediateNode);
                    tree.add(nearestNode, intermediateNode);
                    nearestNode = intermediateNode;
                    
				}
				
                if (tree.goalfound(randomNode)) 
                {
                	tree.add(nearestNode, randomNode);
                	tree.add(nearestNode, goalNode);
                	ArrayList<Node>path = getPath(startNode,goalNode,tree);
                    System.out.println("Path found");
                	draw(path);
                    break;
                }
			}
			
			i++;
			if(i==node_count)
			{
				System.out.println("Path not found");
			}
		}
	}
		private boolean isValidNode1(Node randomNode, Node nearestNode) {
		int x1=nearestNode.getX();
		int y1=nearestNode.getY();
		int x2=randomNode.getX();
		int y2=randomNode.getY();
		
		if(x1==x2 && y1==y2)
		{
			return false;
		}
		Line2D line = new Line2D.Double(x1,y1,x2,y2);
		boolean intere = line.intersects(goal_x, goal_y, 10, 10);
		if(intere)
		{
			return false;
		}
		Rectangle r1 = new Rectangle(20, 20, 90, 40);
		Rectangle r2 = new Rectangle(100, 100, 70, 70);
		
		
		if(line.intersects(r1))
		{
			return false;
		}
		if(line.intersects(r2))
		{
			return false;
		}
		else
		return true;
		
	}
		public void run2() {
			int i=0;
			Node intermediateNode;
			
			while(i<node_count)
			{
				Node randomNode = selectRandomNode();
				Node nearestNode = tree.nearestNode(randomNode.getX(), randomNode.getY());
				
				if(isValidNode2(randomNode,nearestNode))
				{
					
					while (distanceBetween(nearestNode, randomNode) > 0) {
	                    intermediateNode = moveTowardRandNode(nearestNode, randomNode,20);	
	                    drawLineSegment(nearestNode, intermediateNode);
	                    tree.add(nearestNode, intermediateNode);
	                    nearestNode = intermediateNode;
	                    
					}
					
	                if (tree.goalfound(randomNode)) 
	                {
	                	tree.add(nearestNode, randomNode);
	                	tree.add(nearestNode, goalNode);
	                	ArrayList<Node>path = getPath(startNode,goalNode,tree);
	                    System.out.println("Path found");
	                	draw(path);
	                    break;
	                }
				}
				
				i++;
				if(i==node_count)
				{
					System.out.println("Path not found");
				}
			}
		}
		private boolean isValidNode2(Node randomNode, Node nearestNode) {
			int x1=nearestNode.getX();
			int y1=nearestNode.getY();
			int x2=randomNode.getX();
			int y2=randomNode.getY();
			
			if(x1==x2 && y1==y2)
			{
				return false;
			}
			Line2D line = new Line2D.Double(x1,y1,x2,y2);
			boolean intere = line.intersects(goal_x, goal_y, 10, 10);
			if(intere)
			{
				return false;
			}
			Rectangle r1 = new Rectangle(20, 20, 90, 40);
			Rectangle r2 = new Rectangle(100, 100, 70, 70);
			Rectangle r3 = new Rectangle(200, 200, 90, 40);
			
			
			if(line.intersects(r1))
			{
				return false;
			}
			if(line.intersects(r2))
			{
				return false;
			}
			if(line.intersects(r3))
			{
				return false;
			}
			else
			return true;
		}
}
