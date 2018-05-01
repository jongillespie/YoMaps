//package archiveClasses;
//
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.stream.XMLEventReader;
//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.events.Attribute;
//import javax.xml.stream.events.EndElement;
//import javax.xml.stream.events.StartElement;
//import javax.xml.stream.events.XMLEvent;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class XMLParse {
//
//    static final String NODEID = "id";
//    static final String LAT = "latitude";
//    static final String LON = "longitude";
//
//    public List<XML> readConfig(String SmallWaterfordMapData)
//    {
//        List<XML> elements = new ArrayList<XML>();
//        try {
//            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//            InputStream in = new FileInputStream(SmallWaterfordMapData);
//            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
//            XML xml = null;
//
//            while (eventReader.hasNext())
//            {
//                XMLEvent event = eventReader.nextEvent();
//
//                if (event.isStartElement())
//                {
//                    StartElement startElement = event.asStartElement();
//
//                    // if it matches a nodeID create a new item
//                    if (startElement.getName().getLocalPart().equals(NODEID))
//                    {
//                        xml = new XML();
//                        // Read attributes from the tag
//                        Iterator<Attribute> attributes = startElement.getAttributes();
//                        while (attributes.hasNext())
//                        {
//                            Attribute attribute = attributes.next();
//                            if (attribute.getValue().toString().equals(LAT))
//                            {
//                                xml.setLat(attribute.getValue());
//                            }
//                            if (attribute.getValue().toString().equals(LON))
//                            {
//                                xml.setLon(attribute.getValue());
//                            }
//                            /*
//                            if (event.isStartElement())
//                            {
//                                if (event.asStartElement().getValue.getLocalPart.equals(THING WE WANT TO EQUAL))
//                                {
//                                    event = eventReader.nextEvent();
//                                    xml.SET-METHOD-NAME-FOR-ELEMENT(event.asCharacters().getData());
//                                    continue; //method can be used for multiples items in future
//                                }
//                            }
//                            */
//                            if (event.isEndElement())
//                            {
//                                EndElement endElement = event.asEndElement();
//                                if (endElement.getName().getLocalPart().equals(NODEID))
//                                {
//                                    elements.add(xml);
//                                }
//                            }
//                        }
//                    }
//                }
//            } // end while
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//
//        return elements;
//    }
//}
