package com.shubin.spos.converter.utils;

import com.shubin.spos.converter.Model.Route;
import com.shubin.spos.converter.Model.Waypoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.Instant;

public class SPOSXMLWriter {
    private final String XMLNS_XSI = "http://www.w3.org/2001/XMLSchema-instance";
    private final String XMLNS_XSD = "http://www.w3.org/2001/XMLSchema";
    private final String XML_VERSION = "1";
    private final String XMLNS = "http://www.meteogroup-maritime.com/spos/routetemplate";

    public void write(Route route, File file) {
        try {
            Document doc = createRouteXMLDoc(route);

            // write into file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

        }   catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private String getWriteDate() {
        return Instant.now().toString();
    }

    private Document createRouteXMLDoc(Route route) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        // root
        Element sposroutetemplate = doc.createElement("sposroutetemplate");
        sposroutetemplate.setAttribute("xmlns:xsi", XMLNS_XSI);
        sposroutetemplate.setAttribute("xmlns:xsd", XMLNS_XSD);
        sposroutetemplate.setAttribute("xmlVersion", XML_VERSION);
        sposroutetemplate.setAttribute("xmlns", XMLNS);
        doc.appendChild(sposroutetemplate);

        // template
        Element template = doc.createElement("template");
        template.setAttribute("name", route.getName());
        template.setAttribute("created", getWriteDate());
        sposroutetemplate.appendChild(template);

        // waypoints container
        Element waypointsElement = doc.createElement("waypoints");
        template.appendChild(waypointsElement);

        for (Waypoint waypoint : route.getWaypoints()) {
            Element waypointElement = doc.createElement("waypoint");
            waypointElement.setAttribute("name", waypoint.getName());
            waypointElement.setAttribute("lat", Double.toString(waypoint.getLatitude()));
            waypointElement.setAttribute("lon", Double.toString(waypoint.getLongitude()));
            waypointElement.setAttribute("delay", waypoint.getDelay());
            waypointElement.setAttribute("speed", Integer.toString(waypoint.getSpeed()));
            waypointElement.setAttribute("useSpeed", Boolean.toString(waypoint.isUseSpeed()));
            waypointElement.setAttribute("tracktype", waypoint.getTracktype());
            waypointElement.setAttribute("routeTemplatePointType", waypoint.getRouteTemplatePointType());
            waypointElement.setAttribute("ignoreLand", Boolean.toString(waypoint.isIgnoreLand()));
            waypointsElement.appendChild(waypointElement);
        }

        return doc;
    }
}
