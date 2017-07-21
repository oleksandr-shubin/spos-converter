package com.shubin.spos.converter.Model;

public class Waypoint {
    private String name;
    private double latitude;
    private double longitude;
    private String delay = "PT0S";
    private int speed = 0;
    private boolean useSpeed = false;
    private String tracktype = "RhumbLine";
    private String routeTemplatePointType = "Fixed";
    private boolean ignoreLand = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isUseSpeed() {
        return useSpeed;
    }

    public void setUseSpeed(boolean useSpeed) {
        this.useSpeed = useSpeed;
    }

    public String getTracktype() {
        return tracktype;
    }

    public void setTracktype(String tracktype) {
        this.tracktype = tracktype;
    }

    public String getRouteTemplatePointType() {
        return routeTemplatePointType;
    }

    public void setRouteTemplatePointType(String routeTemplatePointType) {
        this.routeTemplatePointType = routeTemplatePointType;
    }

    public boolean isIgnoreLand() {
        return ignoreLand;
    }

    public void setIgnoreLand(boolean ignoreLand) {
        this.ignoreLand = ignoreLand;
    }

    public Waypoint(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", delay='" + delay + '\'' +
                ", speed=" + speed +
                ", useSpeed=" + useSpeed +
                ", tracktype='" + tracktype + '\'' +
                ", routeTemplatePointType='" + routeTemplatePointType + '\'' +
                ", ignoreLand=" + ignoreLand +
                '}';
    }
}
