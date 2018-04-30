import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultipleRoutesTest {

//    String street = "Beau Street";
    StaXMLParser read = new StaXMLParser();
    ArrayList<Node> nodes = read.readXMLforNODES("TinyWaterfordForTESTS.xml");
    ArrayList<Way> ways = read.readXMLforWAYS("TinyWaterfordForTESTS.xml");
    MultipleRoutes multipleRoutes = new MultipleRoutes();

    ArrayList<ArrayList<Way>> route = new ArrayList<>();

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
        Way lookingFor = multipleRoutes.findWayByName("Johnstown", ways);
        Way origin = multipleRoutes.findWayByName("Water Street", ways);
        ArrayList<Way> initial = new ArrayList<>();
        initial.add(origin);
        route.add(initial);
        ArrayList<Way> results = multipleRoutes.multipleRouteBFS(ways, route, null, lookingFor);
        ArrayList<Way> dupeRemoved = new ArrayList<>();
        // Removes the duplication of Ways effect from Node Jumping.
        Way temp = results.get(0);
        for (Way way : results){
            if (!way.getName().equals(temp.getName())){
                dupeRemoved.add(way);
                temp = way;
            }
        }
        for (Way way : dupeRemoved){
            if (!way.getName().equals(" ")){
                System.out.println("----------------");
                System.out.println(way.getName());
            }
        }
    }
}