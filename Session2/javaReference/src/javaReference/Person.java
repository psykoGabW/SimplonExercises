package javaReference;

public class Person{
    String firstname;
    String lastname;
    int age;
    public Person(String firstname, String lastname, int age){
        this.firstname= firstname;
        this.lastname= lastname;
        this.age= age;
    }
    public boolean equals(Object other){
        if(other != null && (other instanceof Person)){
        	Person otherPerson = (Person) other;
            if(firstname.equals(otherPerson.firstname) && lastname.equals(otherPerson.lastname)
               && (age == otherPerson.age)){
                return true;
            }
        }
        return false;
    }
    
    public boolean equals(Person other) {
    	boolean result = false;
    	
    	if (other!=null)
    	{
    		result = ( firstname.equals(other.firstname) &&
    					lastname.equals(other.lastname) &&
    					(age == other.age)
    				);
    	
    	}
    	
    	return result;
    }
    
}