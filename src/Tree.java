import java.util.ArrayList;

public class Tree {
    Node root;
    Node goal;
    ArrayList<Node> nodeArrayList;

    public Tree(int x, int y, int goal_x, int goal_y) {
        root = new Node(x, y, 0, 5, null);
        goal = new Node(goal_x,goal_y);
        nodeArrayList = new ArrayList<>();
        nodeArrayList.add(root);
    }

    public void add(Node parent, Node child) {
        parent.addChild(child);
        nodeArrayList.add(child);
        child.setParent(parent);
    }

    public void remove(Node node) {
        node.getParent().removeChild(node);
        nodeArrayList.remove(node);
    }
    
   
    public boolean contains(Node nodeReq) {
        return nodeArrayList.contains(nodeReq);
    }

    public Node nearestNode(int x, int y) {
        double minDistance = distance(root.getX(), root.getY(), x, y);
        Node nearestNode = root;
        for (Node node : nodeArrayList) {
            double currentDistance = distance(node.getX(), node.getY(), x, y);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                nearestNode = node;
            }
        }
        return nearestNode;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }


	public boolean goalfound(Node randomNode) 
	{
		double dists = distance(randomNode.getX(),randomNode.getY(),goal.getX(),goal.getY()); 
		if(randomNode.getX()==goal.getX() &&randomNode.getY()==goal.getY())
		{
			return true;
		}
		if(dists<5)
		{
			return true;
		}
		else if(dists==5)
		{
			return true;
		}
		else {
		return false;
		}
	}
    
}
