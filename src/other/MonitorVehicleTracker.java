package other;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MonitorVehicleTracker {

    private final Map<String,LocationPoint> locations;

    public MonitorVehicleTracker(Map<String,LocationPoint> locations){
        this.locations = locations;
    }
    public synchronized Map<String,LocationPoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized LocationPoint getLocation(String car){
        LocationPoint locationPoint = locations.get(car);
        return locationPoint==null?null : new LocationPoint(locationPoint);
    }
    public synchronized void setLocations(String car,int x,int y){
        LocationPoint locationPoint = locations.get(car);
        if(locationPoint==null)
            throw new IllegalArgumentException("no such id");
        locationPoint.x=x;
        locationPoint.y=y;
    }

    private static Map<String,LocationPoint> deepCopy(Map<String,LocationPoint> m){
        Map<String,LocationPoint> res = new HashMap<>();
        for(String car : m.keySet()){
            res.put(car,m.get(car));
        }
        return Collections.unmodifiableMap(res);
    }

    /**
     * 非线程安全
     */
    static class LocationPoint{
        public int x,y;
        public LocationPoint(){
            x=y=0;
        }
        public LocationPoint(LocationPoint point){
            this.x = point.x;
            this.y = point.y;
        }
    }
}
