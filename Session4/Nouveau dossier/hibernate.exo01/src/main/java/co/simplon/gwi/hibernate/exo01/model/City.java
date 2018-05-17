package co.simplon.gwi.hibernate.exo01.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "city", schema = "hibernate_exo01")
@NamedQueries({
		@NamedQuery(name = "City.findAll", query = "Select c from City c order by c.name"),
		@NamedQuery(name = "City.deleteById", query = "Delete from City c where c.id=:id")
})
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_city")
	@SequenceGenerator(name = "gen_city", sequenceName = "seq_city", initialValue = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "latitude", columnDefinition = "Decimal(10,8) default 0", nullable = false)
	private Double latitude;

	@Column(name = "longitude", columnDefinition = "Decimal(11,8) default 0", nullable = false)
	private Double longitude;

	/*
	 * @OneToMany(mappedBy= "city") private List<Monument> monuments = new
	 * ArrayList<Monument>();
	 */

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Monument> monuments = new HashSet<Monument>();

	public City() {
	}

	public City(String name, double latitude, double longitude) {
		this(null, name, latitude, longitude);
	}

	public City(Long id, String name, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public boolean addMonument(Monument monument) {
		monument.setCity(this);
		return this.monuments.add(monument);
	}

	public boolean removeMonument(Monument monument) {
		if (this.monuments.contains(monument)) {
			return this.monuments.remove(monument);
		} else {
			return false;
		}
	}

	public Set<Monument> getMonuments() {
		return this.monuments;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
