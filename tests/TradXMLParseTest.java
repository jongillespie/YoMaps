import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;


class TradXMLParseTest {

    TradXMLParse read = new TradXMLParse();

    HashMap<Integer, Node> nodes = read.readXMLNodes("AllWaterford.xml");
    HashMap<String, Way> ways = read.readXMLWays("AllWaterford.xml");

//    HashMap<Integer, Node> nodes = read.readXMLNodes("IrelandFilteredMapData.xml");
//    ArrayList<Way> ways = read.readXMLWays("IrelandFilteredMapData.xml");

    ArrayList<Way> waysList = read.waysList;
    HashMap<String, Link> links = read.createLinks(waysList);

    @Test
    void readXMLNodes() {
    //    assertThat(nodes, IsMapContaining.hasEntry());
//        assertEquals("42311089", nodes.get(42311089).id);
//        forEach(Node,) {
//            System.out.println(node);
//            System.out.println("NodeID: " + node.id
//                    + " Lat= " + node.lat
//                    + " Lon= " + node.lon
//                    + " Name: " + node.name);
//        }
        System.out.println("NODE COUNT: " + nodes.size());
    }

    @Test
    void readXMLWays() {
//        for (Way way : ways) {
//            System.out.println("----------------------------------------------------------------");
//            System.out.println(way);
//            System.out.println("WayID: " + way.getId() + " Type: " + way.getHighwayType()
//                    + " Speed: " + way.getMaxSpeed()
//                    + " Name: " + way.getName());
//            if (way.nodes != null){
//                for (Node node : way.nodes) {
//                    System.out.println("NodeID " + node.getId());
//                }
//            }
//            // if (way.maxSpeed > 50) System.out.println(way.getMaxSpeed());
//        }
        System.out.println("WAYS COUNT: " + ways.size());
    }

    @Test
    void createLinks() {
//        for (Link link : links){
//            System.out.println(link.getName());
//        }
        System.out.println(waysList.get(0).getName());
        System.out.println(links.get("Exchange Street"));
        System.out.println("LINK COUNT: " + links.size());
    }

}