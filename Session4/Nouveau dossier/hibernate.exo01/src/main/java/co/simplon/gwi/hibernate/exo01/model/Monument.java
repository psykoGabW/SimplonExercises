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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "monument", schema = "hibernate_exo01")
@NamedQueries({
		@NamedQuery(name = "Monument.findAll", query = "Select m from Monument m order by m.name"),
		@NamedQuery(name = "Monument.deleteById", query = "Delete from Monument m where m.id=:id")
})
public class Monument {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_monument")
	@SequenceGenerator(name = "gen_monument", sequenceName = "seq_monument", initialValue = 1)
	@Column
	private Long id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city")
	private City city;

	@ManyToMany(mappedBy = "monuments", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<User>();

	public Monument(String name) {
		super();
		this.name = name;
	}

	public Monument() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public boolean addUser(User visitor) {
		if (visitor == null) {
			return false;
		}
		boolean result = visitor.getMonuments().add(this);
		if (result) {
			result = this.users.add(visitor);
		}
		return result;
	}

	@Override
	public String toString() {
		return "Monument [id=" + id + ", name=" + name
				+ ", city=" + city + "]";
	}
}