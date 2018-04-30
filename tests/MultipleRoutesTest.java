import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultipleRoutesTest {

//    String street = "Beau Street";
    StaXMLParser read = new StaXMLParser();
    ArrayList<Node> nodes = read.readXMLforNODES("TinyWaterfordForTESTS.xml");
    ArrayList<Way> ways = read.readXMLforWAYS("TinyWaterfordForTESTS.xml");
    MultipleRoutes multipleRoutes = new MultipleRoutes();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findWayByName() {
        String street = "Beau Street";
        assertEquals(street, multipleRoutes.findWayByName(street, ways).getName());
    }

    @Test
    void multipleRouteBFS() {
    }
}