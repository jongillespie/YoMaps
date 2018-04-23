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
import java.util.Iterator;

public class StaXMLParser {

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

    private  ArrayList<Node> nodes = new ArrayList<>();
    private  ArrayList<Way> ways = new ArrayList<>();

    @SuppressWarnings({"unchecked", "null"})
    public ArrayList<Node> readXMLforNODES(String MapData) {

        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(MapData);
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
                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(NODE)) {
                        nodes.add(node);
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
    public ArrayList<Way> readXMLforWAYS(String MapData) {
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
                                    for (Node node : nodes) {
                                        if (way != null && node.id.equals(attribute.getValue())) {
                                            way.nodes.add(node);
                                        }
                                    }
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
                                    int speed = parseMPHCheck(tempValue);
                                    way.setMaxSpeed(speed);
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
                        ways.add(way);
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
     * Receives the speed String and checks if in MPH, if it is, will parse for the int only.
     * @param mpgTest
     * @return Int Value of Speed String Input.
     */
    private int parseMPHCheck(String mpgTest){
        int length = mpgTest.length();
        for (int i = 0; i < length; i ++){
            Character letter = mpgTest.charAt(i);
            if (letter.equals('m')){
                Double kphSpeedConvert = 1.60934 * (Integer.parseInt(mpgTest.substring(0, (i - 1))));
                return kphSpeedConvert.intValue();
            }
        } return Integer.parseInt(mpgTest);
    }

}