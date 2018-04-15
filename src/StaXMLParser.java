import com.sun.tools.javac.util.List;

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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class StaXMLParser {

    //  NODE RELATED ATTRIBUTES
    static final String NODE = "node";
    static final String NODEID = "id";
    static final String LAT = "lat";
    static final String LON = "lon";

    // WAY RELATED ATTRIBUTES
    static final String WAY = "way";
    static final String WAYID = "id";
    static final String NAME = "name";
    static final String HIGHWAYTYPE = "highway";
    static final String MAXSPEED = "maxspeed";
    static final String WAYNODE = "nd";
    static final String WAYNODEID = "ref";
    static final String TAG = "tag";
    static final String KEY = "k";
    static final String VALUE = "v";

    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Way> ways = new ArrayList<>();
    private List<String> highways;

    private void createHighways() {
        String motorway = "motorway";
        String motorway_link = "motorway_link";
        String primary = "primary";
        String primary_link = "primary_link";
        String residential = "residential";
        String secondary = "secondary";
        String secondary_link = "secondary_link";
        String service = "service";
        String tertiary = "tertiary";
        String tertiary_link = "tertiary_link";
        String trunk = "trunk";
        String trunk_link = "trunk_link";
        String unclassified = "unclassified";
        highways.add(motorway);
        highways.add(motorway_link);
        highways.add(primary);
        highways.add(primary_link);
        highways.add(residential);
        highways.add(secondary);
        highways.add(secondary_link);
        highways.add(service);
        highways.add(tertiary);
        highways.add(tertiary_link);
        highways.add(trunk);
        highways.add(trunk_link);
        highways.add(unclassified);
    }

    @SuppressWarnings({"unchecked", "null"})
    public ArrayList<Node> readXMLforNODES(String SmallWaterfordMapData) {

        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(SmallWaterfordMapData);
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
    public ArrayList<Way> readXMLforWAYS(String SmallWaterfordMapData) {

        createHighways();

        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(SmallWaterfordMapData);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Way way = null;

            ArrayList<Node> wayNodes = null;

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
                                        if (node.id == attribute.getValue()) {
                                            wayNodes.add(node);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TAG)) {
                            event = eventReader.nextEvent();

                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {

                                Boolean isHighway = false;
                                Boolean hasMaxSpeed = false;
                                Boolean hasName = false;

                                Attribute attribute = attributes.next();

                                if (attribute.getName().toString().equals(KEY)) {
                                    // Checks if the Key is a Highway we want
                                    for (int i = 0; i < highways.size(); i++) {
                                        if (highways.get(i) == attribute.getValue()) {
                                            isHighway = true;
                                        }
                                    }
                                    // Checks if Key has a speed we may need to capture
                                    if (attribute.getValue() == MAXSPEED){
                                        hasMaxSpeed = true;
                                    }
                                    // Checks if the key is a name.
                                    if (attribute.getValue() == NAME) {
                                        hasName = true;
                                    }

                                }
                                if (isHighway && attribute.getName().toString().equals(VALUE)) {
                                    way.setHighwayType(attribute.getValue());
                                }
                                if (hasMaxSpeed && isHighway && attribute.getName().toString().equals(VALUE)) {
                                    way.setMaxSpeed(Integer.parseInt(attribute.getValue()));
                                }
                                if ( hasName && hasMaxSpeed && isHighway && attribute.getName().toString().equals(VALUE)) {
                                    way.setName(attribute.getValue());
                                }
                            }
                        }
                    }
                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(WAY)) {
                        ways.add(way);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return ways;
    }




}




//                            way.set(event.asCharacters().getData());
//                            continue;
//                        }
//                    }
//                    if (event.asStartElement().getName().getLocalPart()
//                            .equals(UNIT)) {
//                        event = eventReader.nextEvent();
//                        item.setUnit(event.asCharacters().getData());
//                        continue;
//                    }
//
//                    if (event.asStartElement().getName().getLocalPart()
//                            .equals(CURRENT)) {
//                        event = eventReader.nextEvent();
//                        item.setCurrent(event.asCharacters().getData());
//                        continue;
//                    }
//
//                    if (event.asStartElement().getName().getLocalPart()
//                            .equals(INTERACTIVE)) {
//                        event = eventReader.nextEvent();
//                        item.setInteractive(event.asCharacters().getData());
//                        continue;
//                    }