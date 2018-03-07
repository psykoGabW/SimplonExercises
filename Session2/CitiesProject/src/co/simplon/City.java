package co.simplon;

public class City implements Comparable<City> {
    private final String name;
    private final double latitude;
    private final double longitude;

    public City(String name, double latitude, double longitude){
        this.name= name;
        this.latitude= latitude;
        this.longitude= longitude;
    } 

    public double distanceTo(double latitude, double longitude){
        double R= 6371e3;
        double phi1=Math.toRadians(this.latitude);
        double phi2=Math.toRadians(latitude);
        double deltaPhi=Math.toRadians(latitude- this.latitude);
        double deltaLambda= Math.toRadians(longitude- this.longitude);
        double a= Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) +
            Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
        double c= 2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
    public double distanceTo(City other){
        return distanceTo(other.latitude, other.longitude);
    }
    public String toString(){
        return name+"; "+ latitude+"; "+ longitude;
    }
    public String getName() {
    	return name;
    }
    public double getLongitude() {
    	return longitude;
    }
    public double getLatitude() {
    	return latitude;
    }
	@Override
	public int compareTo(City other) {
	    int res=  name.compareToIgnoreCase(other.name);
	    if (res != 0) { return res; }
	    res= Double.compare(latitude, other.latitude);
	    if (res != 0) { return res; }
	    return Double.compare(longitude, longitude);
	}
	  public boolean equals(Object o){
		  if(! (o instanceof City)) { return false;}
		  City c= (City) o;
		  return name.equals(c.name) && (latitude == c.latitude) && (longitude == c.longitude);
		}

}
