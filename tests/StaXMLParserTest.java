import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StaXMLParserTest {

    @Test
    void readXMLforNODES() {
        StaXMLParser read = new StaXMLParser();
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
    void readXMLforWAYS(){
        StaXMLParser read = new StaXMLParser();
        ArrayList<Way> ways = read.readXMLforWAYS("SmallWaterfordMapData.xml");
        for (Way way : ways) {
            System.out.println("WayID: " + way.id + "Type: " + way.getHighwayType() );
        }
        System.out.println("COUNT: " + ways.size());
    }

}