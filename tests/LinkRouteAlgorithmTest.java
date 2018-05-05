import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkRouteAlgorithmTest {

    TradXMLParse read = new TradXMLParse();
    HashMap<Double, Node> nodesMap = read.readXMLNodes("AllWaterford.xml");
    HashMap<String, Way> waysMap = read.readXMLWays("AllWaterford.xml");
//    HashMap<Double, Node> nodesMap = read.readXMLNodes("IrelandFilteredMapData.xml");
//    HashMap<String, Way> waysMap = read.readXMLWays("IrelandFilteredMapData.xml");
    HashMap<String, Link> linksMap = read.createLinks(read.waysList);
    LinkRouteAlgorithm linkRouteAlgorithm = new LinkRouteAlgorithm();
    ArrayList<ArrayList<Link>> route = new ArrayList<>();
    String street = "Beau Street";

    @Test
    void findWayByName() {
        System.out.println(linkRouteAlgorithm.findWayByName(street, waysMap).getName());
        assertEquals(street, linkRouteAlgorithm.findWayByName(street, waysMap).getName());
    }

    @Test
    void getANodeOfWay(){
        Way way = linkRouteAlgorithm.findWayByName(street, waysMap);
        Node node = linkRouteAlgorithm.getANodeOfWay(way, nodesMap);
        System.out.println(node.getId());
    }

    @Test
    void multipleRouteBFS() {
        Link lookingForLink = linkRouteAlgorithm.findLinkByName("Water Street", linksMap);
        System.out.println(lookingForLink);
        Link originLink = linkRouteAlgorithm.findLinkByName("Beau Street", linksMap);
        System.out.println(originLink);
        ArrayList<Link> initial = new ArrayList<>();
        initial.add(originLink);
        route.add(initial);
        System.out.println(route.size());
        ArrayList<Link> results = linkRouteAlgorithm.multipleRouteBFS(route, null, lookingForLink);
//        for (Link link : results){
////            System.out.println(link.name);
////        }
        System.out.println("RESULTS SIZE: " + results.size());
        ArrayList<Link> dupeRemoved = new ArrayList<>();
        // Removes the duplication of Ways effect from Node Jumping.
        Link temp = results.get(0);
        for (Link link : results){
            if (!link.getName().equals(temp.getName()) && !link.getName().equals(" ")){
                dupeRemoved.add(link);
                temp = link;
            }
        }
        System.out.println("--- THE ROUTE CHECK >>>> ");
        for (Link link : dupeRemoved){
            System.out.println(link.getName());
        }
    }
}