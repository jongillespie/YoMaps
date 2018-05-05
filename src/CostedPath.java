import java.util.ArrayList;

/**
 * Cost Path Class keeps track of DA's path cost and node routes.
 */
public class CostedPath {

    private double pathCost = 0;
    private ArrayList<Node> pathList = new ArrayList<>();

    /**
     * Returns the Path Cost
     * @return pathCost
     */
    public double getPathCost() {
        return pathCost;
    }

    /**
     * Sets the Path Cost
     */
    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    /**
     *  Returns the Path list
     * @return pathList
     */
    public ArrayList<Node> getPathList() {
        return pathList;
    }

    /**
     * Sets the path list
     * @param pathList
     */
    public void setPathList(ArrayList<Node> pathList) {
        this.pathList = pathList;
    }
}
