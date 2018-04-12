import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XML {

    private int nodeID;
    private double lat;
    private double lon;

    // Import the XML Document
    File file = new File("filepath here");
    DocumentBuilderFactory docbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = docbf.newDocumentBuilder();
    Document document = db.parse(file);

    public XML() throws ParserConfigurationException, IOException, SAXException {
    }

    //Individual elements from XML files

    // NodeID
    public int getNodeID()
    {
        return nodeID;
    }

    public void setNodeID(int nodeID)
    {
        this.nodeID = nodeID;
    }

    // Lat and Lon
    public double getLat()
    {
        return lat;
    }

    public void setLat(String value)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(String value)
    {
        this.lon = lon;
    }

    // ...other elements as they are needed
    // ...need to correspond nodeID's with location names!

    @Override
    public String toString() {
        return "\nNodeID: " + nodeID + "\nLat: " + lat + "\nlon: " + lon;
    }
}
