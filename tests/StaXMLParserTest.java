import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StaXMLParserTest {

    StaXMLParser read = new StaXMLParser();


    @Test
    void readXMLforNODES() {
        ArrayList<Node> nodes = read.readXMLforNODES("SmallWaterfordMapData.xml");
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
        ArrayList<Way> ways = read.readXMLforWAYS("SmallWaterfordMapData.xml");
        for (Way way : ways) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("WayID: " + way.id + " Type: " + way.getHighwayType()
                    + " Speed: " + way.getMaxSpeed()
                    + " Name: " + way.getMaxSpeed());
            if (way.nodes != null){
                for (Node node : way.nodes) {
                    System.out.println("Node" + node.getId());
                }
            }
        }
        System.out.println("COUNT: " + ways.size());
    }

}