import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class StaXMLParserTest {

    StaXMLParser read = new StaXMLParser();
    ArrayList<Node> nodes = read.readXMLforNODES("TinyWaterfordForTESTS.xml");
    ArrayList<Way> ways = read.readXMLforWAYS("TinyWaterfordForTESTS.xml");
//    ArrayList<Node> nodes = read.readXMLforNODES("IrelandFilteredMapData.xml");
//    ArrayList<Way> ways = read.readXMLforWAYS("IrelandFilteredMapData.xml");

    @Test
    void readXMLforNODES() {
        for (Node node : nodes) {
            System.out.println(node);
            System.out.println("NodeID: " + node.id
                    + " Lat= " + node.lat
                    + " Lon= " + node.lon
                    + " Name: " + node.name);
        }
        System.out.println("NODE COUNT: " + nodes.size());
    }

    @Test
    void readXMLforWAYS() {
        for (Way way : ways) {
            System.out.println("----------------------------------------------------------------");
            System.out.println(way);
            System.out.println("WayID: " + way.getId() + " Type: " + way.getHighwayType()
                    + " Speed: " + way.getMaxSpeed()
                    + " Name: " + way.getName());
            if (way.nodes != null){
                for (Node node : way.nodes) {
                    System.out.println("NodeID " + node.getId());
                }
            }
           // if (way.maxSpeed > 50) System.out.println(way.getMaxSpeed());
        }
        System.out.println("COUNT: " + ways.size());
    }

}