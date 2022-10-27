package part01;

public class vendingLocation {

	private String locationName;
    private double distance1;
    private double distance2;   
     


    public vendingLocation(String locationName, double distance1, double distance2) {
        this.locationName = locationName;
        this.distance1  = distance1;
        this.distance2 = distance2;
    }

    
    // returning the distance between two locations
    public double locationDistance(vendingLocation distanceBetween) {
        double Miles = 1.15077945;
        double distance2 = Math.toRadians(this.distance1);
        double distance3 = Math.toRadians(this.distance2);
        double distance4 = Math.toRadians(distanceBetween.distance1);
        double distance5 = Math.toRadians(distanceBetween.distance2);

        
        double angleR = Math.acos(Math.sin(distance2) * Math.sin(distance4)
                               + Math.cos(distance2) * Math.cos(distance4) * Math.cos(distance3 - distance5));

        // Distance in Miles
        double nMiles = 60 * Math.toDegrees(angleR);
        double Miles1 = Miles * nMiles;
        return Miles1;
    }

    // return toString method
    public String toString() {
        return locationName+""+"("+""+distance1+""+","+""+distance2+")";
    }


    // Testing vendingLocation Class 
    public static void main(String[] args) {
        vendingLocation firstLocation = new vendingLocation("Queen's University Belfast", 54.5844832888948, -5.933995657519206);
        vendingLocation secondLocation = new vendingLocation("Queen's Elms Village", 54.57635590512267, -5.942333815592251);  
        double TrueDistance = firstLocation.locationDistance(secondLocation);
        System.out.printf("%6.3f miles from\n", TrueDistance);
        System.out.println(firstLocation + " to " + secondLocation);
    }
}

