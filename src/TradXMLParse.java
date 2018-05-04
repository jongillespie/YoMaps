import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("ALL")
public class TradXMLParse implements DistanceCalcInterface {

    //  NODE RELATED ATTRIBUTES
    private static final String NODE = "node";
    private static final String NODEID = "id";
    private static final String LAT = "lat";
    private static final String LON = "lon";

    // WAY RELATED ATTRIBUTES
    private static final String WAY = "way";
    private static final String WAYID = "id";
    private static final String NAME = "name";
    private static final String MAXSPEED = "maxspeed";
    private static final String WAYNODE = "nd";
    private static final String WAYNODEID = "ref";
    private static final String TAG = "tag";
    private static final String VALUE = "v";

    public HashMap<Double, Node> nodes = new HashMap();
   // public HashMap edges = new HashMap(5000);

    public HashMap<String, Way> ways = new HashMap<>();
    public ArrayList<Way> waysList = new ArrayList<>();

//    public ArrayList<Node> nodes = new ArrayList<>();
//    public  ArrayList<Way> ways = new ArrayList<>();

    @SuppressWarnings({"unchecked", "null"})
    public HashMap readXMLNodes(String mapData) {

        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(mapData);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Node node = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    if (startElement.getName().getLocalPart().equals(NODE)) {
                        node = new Node();
                        // We read the attributes from this tag and add the date
                        // attribute to our object
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(NODEID)) {
                                node.setId(attribute.getValue());
                            }
                            if (attribute.getName().toString().equals(LAT)) {
                                node.setLat(Float.valueOf(attribute.getValue()));
                            }
                            if (attribute.getName().toString().equals(LON)) {
                                node.setLon(Float.valueOf(attribute.getValue()));
                            }
                        }
                    }
                    // Finds and Sets the name of the Node
                    if (node != null && event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TAG)) {
                            event = eventReader.nextEvent();
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String tempValue = null;
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                // SETS VALUE >> ITERATOR IS BACKWARDS!!!!!!
                                if (attribute.getName().toString().equals(VALUE)) {
                                    tempValue = attribute.getValue();
                                }
                                // SETS THE NAME
                                if (attribute.getValue().equals(NAME)){
                                    node.setName(tempValue);
                                }
                            }
                        }
                    }
                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(NODE)) {
                        nodes.put(Double.parseDouble(node.getId()), node); // Used to put into the hashmap. ID as Key
                      //  System.out.println(" ------> NODE ID PUT in HASH: " + node.getId());
                        // nodes.add(node); was used for the array list.
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    @SuppressWarnings({"unchecked", "null"})
    public HashMap<String, Way> readXMLWays(String MapData) {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(MapData);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Way way = null;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    if (startElement.getName().getLocalPart().equals(WAY)) {
                        way = new Way();
                        // We read the attributes from this tag and add the date
                        // attribute to our object
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(WAYID)) {
                                way.setId(attribute.getValue());
                            }
                        }
                    }
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(WAYNODE)) {
                            event = eventReader.nextEvent();
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(WAYNODEID)) {
                               //     for (Node node : nodes) {
                                        if (way != null ) { // && node.id.equals(attribute.getValue())) {
                                            //way.nodes.add(node);
                                            way.nodes.add(Double.parseDouble(attribute.getValue()));
                                           // System.out.println(">>> WAY NODE >>> : " + attribute.getValue());
                                        }
                               //     }
                                }
                            }
                        }
                    }
                    if (way != null && event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TAG)) {
                            event = eventReader.nextEvent();
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String tempValue = null;
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                // CHECKS FOR HIGHWAY TYPE AND SETS HIGHWAY VALUE
                                String h = attribute.getValue();
                                if (h.equals("motorway") || h.equals("motorway_link") || h.equals("primary")
                                        || h.equals("primary_link") || h.equals("residential")
                                        || h.equals("secondary") || h.equals("secondary_link")
                                        || h.equals("service") || h.equals("tertiary")
                                        || h.equals("tertiary_link") || h.equals("trunk") || h.equals("trunk_link")
                                        || h.equals("unclassified")) {
                                    way.setHighwayType(h);
                                }
                                // SETS VALUE >> ITERATOR IS BACKWARDS!!!!!!
                                if (attribute.getName().toString().equals(VALUE)) {
                                    tempValue = attribute.getValue();
                                }
                                // SETS MAX SPEED
                                if (attribute.getValue().equals(MAXSPEED)){
                                    int speed = 50;
                                    try {
                                        speed = Integer.parseInt(tempValue);
                                        way.setMaxSpeed(speed);
                                    }
                                    catch (NumberFormatException e) {
                                        way.setMaxSpeed(speed);
                                    }
                                }
                                // SETS THE NAME
                                if (attribute.getValue().equals(NAME)){
                                    way.setName(tempValue);
                                }
                            }
                        }
                    }
                }

                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (way != null && endElement.getName().getLocalPart().equals(WAY)
                            && way.highwayType != null) {
                        ways.put(way.getName(), way);
                        waysList.add(way);
                    }
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return ways;
    }

    /**
     * Splits the Ways into seperate links between nodes.
     * @param waysList
     * @return
     */
    public HashMap<String, Link> createLinks(ArrayList<Way> waysList){
        // create a new hashmap that contains links with key of name of street.
        HashMap<String, Link> links = new HashMap<>();
        // for each way in the waysList, associate one node to the next with a link etc.
        for (Way way : waysList){
            for (int i = 0; i < way.nodes.size() - 1; i++) {
                // Find the Nodes in memory
                Node firstNode = nodes.get(way.nodes.get(i));
                Node secondNode = nodes.get(way.nodes.get(i + 1));
                // create an adjacent node list for link
                ArrayList<Node> adjListNodes = new ArrayList<>();
                adjListNodes.add(firstNode);
                adjListNodes.add(secondNode);
                // create a link + add the nodes to the link adj list
                Link newLink = new Link(way.getName(), adjListNodes, way.getMaxSpeed());
                // add the links to the node's adj list
                firstNode.getAdjLinks().add(newLink);
                secondNode.getAdjLinks().add(newLink);
                // add the link to the hashmap
                links.put(newLink.getName(), newLink);

                // --------------- BELOW ADDED FOR DA'S - CREATES TWIN LINKS, SINGLE DIRECTION, SAME DETAILS
                // calculates the distance
                int distance = (int) distance(firstNode.getLat(), secondNode.getLat(), firstNode.getLon(), secondNode.getLon());
               // System.out.println("Link Name: " + way.getName() + " DISTANCE: " + distance);
                // creates and adds the twin links
                firstNode.getAdjTwinLinks().add(new Link(way.getName(), secondNode, distance, way.getMaxSpeed()));
                secondNode.getAdjTwinLinks().add(new Link(way.getName(), firstNode, distance, way.getMaxSpeed()));
            }
        }
        return links;
    }
}
