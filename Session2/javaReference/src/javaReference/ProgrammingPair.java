package javaReference;

class ProgrammingPair {
	Person driver;
	Person navigator;

	public Person getDriver() {
		return driver;
	}

	public void setDriver(Person driver) {
		this.driver = driver;
	}

	public Person getNavigator() {
		return navigator;
	}

	public void setNavigator(Person navigator) {
		this.navigator = navigator;
	}
	
	public ProgrammingPair() {
		this.driver= null;
		this.navigator= null;
	}
	
	public ProgrammingPair(Person driver, Person navigator) {
		this.driver = new Person(driver.firstname, driver.lastname, driver.age);
		this.navigator = new Person(navigator.firstname, navigator.lastname, navigator.age);
	}
	
	public ProgrammingPair clone(ProgrammingPair other) {
		return (new ProgrammingPair(other.driver, other.navigator));
	}
	
	public static void main(String[] args) {
		ProgrammingPair pair = new ProgrammingPair();
		pair.setNavigator(new Person("Lex", "Luthor", 42));
		pair.setDriver(new Person("Clark", "Kent", 45));
		
		ProgrammingPair originalPair = pair;
		ProgrammingPair other = new ProgrammingPair();
		other.setDriver(pair.getDriver());
		other.setNavigator(pair.getNavigator());
		
	}
}
