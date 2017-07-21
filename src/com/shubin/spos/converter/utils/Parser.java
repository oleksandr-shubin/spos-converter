package com.shubin.spos.converter.utils;

import com.shubin.spos.converter.Model.Waypoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parser {
    public static List<Waypoint> parseWaypoints(File file) {
        List<Waypoint> waypoints = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = "";

            while((line =  in.readLine()) != null) {
                waypoints.add(parseWaypoint(line));
            }
            // remove nulls
            waypoints.removeAll(Collections.singleton(null));
            return waypoints;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return waypoints;
    }

    private static Waypoint parseWaypoint(String line) {
        if (isComment(line)) {
            return null;
        } else {
            String[] data = line.split(",");
            return convertDataToWaypoint(data);
        }
    }

    private static Waypoint convertDataToWaypoint(String[] data) {
        String name = "wp-" + data[0];

        int latDeg = Integer.parseInt(data[1]);
        double latMin = Double.parseDouble(data[2]);
        String latDirection = data[3];
        double latitude = latDirection.equals("N") ? latDeg + latMin/60 : -(latDeg + latMin/60) ;

        int longDeg = Integer.parseInt(data[4]);
        double longMin = Double.parseDouble(data[5]);
        String longDirection = data[6];
        double longitude = longDirection.equals("E") ? longDeg + longMin/60: -(longDeg + longMin/60);

        // return Waypoint
        return new Waypoint(
                name,
                latitude,
                longitude
        );
    }

    private static boolean isComment(String line) {
        return line.startsWith("//");
    }

}
