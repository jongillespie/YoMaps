import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultipleRoutesTest {

    String street = "Beau Street";

    @BeforeEach
    void setUp() {
        StaXMLParser read = new StaXMLParser();
        Main.nodes = read.readXMLforNODES("TinyWaterfordForTESTS.xml");
        Main.ways = read.readXMLforWAYS("TinyWaterfordForTESTS.xml");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findWayByName(street) {
        System.out.println(street);
        System.out.println();
        assertEquals(, findWayByName(street));
        assertEquals(Way objext, findWayByName(street));
        assert

//        assertEquals(street, findWayByName(street));
//        Way way = findWayByName(street);
        //assertEquals(, findWayByName("Beau Street"));
    }

    @Test
    void multipleRouteBFS() {
    }
}