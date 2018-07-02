package co.simplon.gwi.tdd.fizzbuzz;

import org.junit.Test;

public class TestFizzBuzz {
	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnAnIllegalArgumentExceptionIfMinIsSuperiorToMax() {
	    FizzBuzz.fizzBuzz(3, 2);
	}

}
