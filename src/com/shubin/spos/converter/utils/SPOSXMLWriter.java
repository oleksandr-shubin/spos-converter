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

    public SPOSXMLWriter() {
    }

    public void write(Route route, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            // root
            Element sposroutetemplate = doc.createElement("sposroutetemplate");
            sposroutetemplate.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            sposroutetemplate.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
            sposroutetemplate.setAttribute("xmlVersion", "1");
            sposroutetemplate.setAttribute("xmlns", "http://www.meteogroup-maritime.com/spos/routetemplate");
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

            // write into file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
//            StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

        }   catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private String getWriteDate() {
        return Instant.now().toString();
    }
}
