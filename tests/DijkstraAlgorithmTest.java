import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {

    TradXMLParse read = new TradXMLParse();
    HashMap<Double, Node> nodesMap = read.readXMLNodes("AllWaterford.xml");
    HashMap<String, Way> waysMap = read.readXMLWays("AllWaterford.xml");
//    HashMap<Double, Node> nodesMap = read.readXMLNodes("IrelandFilteredMapData.xml");
//    HashMap<String, Way> waysMap = read.readXMLWays("IrelandFilteredMapData.xml");
    HashMap<String, Link> linksMap = read.createLinks(read.waysList);
    DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
    ArrayList<ArrayList<Link>> route = new ArrayList<>();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void findCheapestPathDijkstra() {
        String start = "High Street";
        String end = "Water Street";
        String avoid = "Catherine Street";
//        Node toAvoid = nodesMap.get(waysMap.get(avoid));
//        ArrayList<Node> avoidList = new ArrayList<>();
        ArrayList<String> avoidList = new ArrayList<>();
        avoidList.add(avoid);
        CostedPath result = dijkstraAlgorithm.findCheapestPathDijkstra(
                dijkstraAlgorithm.findNodeByWay(start, waysMap, nodesMap),
                dijkstraAlgorithm.findNodeByWay(end, waysMap, nodesMap),
                avoidList);

        ArrayList<Link> translateToLink = new ArrayList();
        for (int i = 0; i < result.getPathList().size() - 1; i ++){
            Node currentNode = result.getPathList().get(i);
            Node nextNode = result.getPathList().get(i +1);
            for (Link link : currentNode.getAdjTwinLinks())
                if (link.getDestinationNode() == nextNode){
                translateToLink.add(link);
            }
        }
        for (Link link : translateToLink){
            System.out.println(link.getName());
        }
        ArrayList<Link> dupeRemovedRoute = new ArrayList<>();
        // Removes the duplication of Ways effect from Node Jumping.
        Link temp = translateToLink.get(0);
        dupeRemovedRoute.add(temp);
        for (Link link : translateToLink){
            if (!link.getName().equals(temp.getName()) && !link.getName().equals(" ")){
                dupeRemovedRoute.add(link);
                temp = link;
            }
        }
        System.out.println("--- THE ROUTE CHECK >>>> ");
        for (Link link : dupeRemovedRoute){
            System.out.println(link.getName());
        }
    }

    @Test
    void stringTest(){
        String test = "This, Then This, And This";
        String[] waypointsRequired = test.split(", ");
        System.out.println(test);
        System.out.println(waypointsRequired);
        for (String string : waypointsRequired){
            System.out.println("Answer: " + string);
        }
    }
}