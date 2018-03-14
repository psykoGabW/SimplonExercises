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

	public String getUpdateQuery() {
		return(
				String.format(Locale.US, "UPDATE city SET name = '%s', countryCode = '%s', latitude = %f, longitude= %f WHERE id=%d",
						name, 
						countryCode,
						latitude,
						longitude,
						id)
				);		
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean hasBeenSaved() {
		return (id!=null);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
