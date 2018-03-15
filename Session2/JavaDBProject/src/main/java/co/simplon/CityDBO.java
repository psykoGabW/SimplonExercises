package co.simplon;

import java.util.Locale;

class CityDBO {
	
	private Long id;
	private String name;
	private String countryCode;
	private double latitude;
	private double longitude;
	
	public CityDBO(String name, String countryCode, double latitude, double longitude) {
		this.name = name;
		this.countryCode = countryCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getInsertQuery() {
		return(
				String.format(Locale.US, "INSERT INTO city( name, countrycode, latitude, longitude) VALUES ('%s', '%s', %f, %f);",
						name, 
						countryCode,
						latitude,
						longitude)
				);		
	}

	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean hasBeenSaved() {
		return (id!=null);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
