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
    static final String NODE = "node";
    static final String NODEID = "id";
    static final String LAT = "lat";
    static final String LON = "lon";

    // WAY RELATED ATTRIBUTES
    static final String WAY = "way";
    static final String WAYID = "id";
    static final String NAME = "name";
    static final String HIGHWAYTYPE = "highway";
    static final String MAXSPEED = "maxSpeed";
    static final String WAYNODE = "nd ref";

    @SuppressWarnings({"unchecked", "null"})
    public ArrayList<Node> readXMLforNODES(String SmallWaterfordMapData) {
        ArrayList<Node> nodes = new ArrayList<>();
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
//                    if (event.isStartElement()) {
//                        if (event.asStartElement().getName().getLocalPart()
//                                .equals(MODE)) {
//                            event = eventReader.nextEvent();
//                            item.setMode(event.asCharacters().getData());
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
        ArrayList<Way> ways = new ArrayList<>();
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(SmallWaterfordMapData);
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
                            way.s(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(UNIT)) {
                        event = eventReader.nextEvent();
                        item.setUnit(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(CURRENT)) {
                        event = eventReader.nextEvent();
                        item.setCurrent(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(INTERACTIVE)) {
                        event = eventReader.nextEvent();
                        item.setInteractive(event.asCharacters().getData());
                        continue;
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

}

