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
}