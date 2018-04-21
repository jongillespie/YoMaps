import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StaXMLParserTest {

    StaXMLParser read = new StaXMLParser();
    ArrayList<Node> nodes = read.readXMLforNODES("TinyWaterfordForTESTS.xml");
    ArrayList<Way> ways = read.readXMLforWAYS("TinyWaterfordForTESTS.xml");

    @Test
    void readXMLforNODES() {
        for (Node node : nodes) {
            System.out.println(node);
            System.out.println("NodeID: " + node.id
                    + " Lat= " + node.lat
                    + " Lon= " + node.lon);
        }
        System.out.println("NODE COUNT: " + nodes.size());
    }

    @Test
    void readXMLforWAYS() {
        for (Way way : ways) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("WayID: " + way.getId() + " Type: " + way.getHighwayType()
                    + " Speed: " + way.getMaxSpeed()
                    + " Name: " + way.getName());
            if (way.nodes != null){
                for (Node node : way.nodes) {
                    System.out.println("NodeID " + node.getId());
                }
            }
        }
        System.out.println("COUNT: " + ways.size());
    }

}