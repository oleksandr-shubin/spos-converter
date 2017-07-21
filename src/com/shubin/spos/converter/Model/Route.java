package com.shubin.spos.converter.Model;

import java.util.List;

public class Route {
    private String name;
    private List<Waypoint> waypoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public Route(String name, List<Waypoint> waypoints) {
        this.name = name;
        this.waypoints = waypoints;
    }
}
