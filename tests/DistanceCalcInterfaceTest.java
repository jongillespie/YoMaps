import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DistanceCalcInterfaceTest implements DistanceCalcInterface {

    double lat1 = 52.2589817;
    double lon1 = -7.1081033;
    double lat2 = 52.2569611;
    double lon2 = -7.1068123;

    @Test
    void distance() {
       assertEquals(241.2517010513314, distance(lat1, lat2, lon1, lon2));
    }

}