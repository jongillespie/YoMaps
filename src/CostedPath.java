import java.util.ArrayList;

public class CostedPath {

    private double pathCost = 0;
    private ArrayList<Node> pathList = new ArrayList<>();

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    public ArrayList<Node> getPathList() {
        return pathList;
    }

    public void setPathList(ArrayList<Node> pathList) {
        this.pathList = pathList;
    }
}
