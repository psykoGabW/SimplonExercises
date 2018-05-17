package co.simplon.gwi.hibernate.exo01.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USERS", schema = "hibernate_exo01")
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "Select u from User u order by u.name"),
	@NamedQuery(name = "User.deleteById", query = "Delete from User u where u.id=:id")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@ManyToMany
	@JoinTable(name = "USER_MONUMENT", schema = "hibernate_exo01", joinColumns = {
			@JoinColumn(name = "FK_USER", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "FK_MONUMENT", referencedColumnName = "ID") })
	private Set<Monument> monuments = new HashSet<Monument>();

	public User() {
	}

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean addMonument(Monument m) {
		if (m == null) {
			return false;
		}
		boolean result = this.monuments.add(m);
		if (result) {
			result = m.getUsers().add(this);
		}
		return result;
	}

	public Set<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(Set<Monument> monuments) {
		this.monuments = monuments;
	}

	public String toString() {
		return "User :{ id= " + id + "\n name= " + name + "\n nb momunents" + monuments.size() + "\n}";
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}