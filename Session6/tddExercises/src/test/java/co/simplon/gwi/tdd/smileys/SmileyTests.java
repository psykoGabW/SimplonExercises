package co.simplon.gwi.tdd.smileys;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SmileyTests {
	@Test
	public void shouldReturn0IfInputIsNull() {
		assertEquals(0, Smiley.countSmileys(null));
	}

	@Test
	public void shouldReturn0IfInputIsEmptyList() {
		assertEquals(0, Smiley.countSmileys(new ArrayList<String>()));
	}
	
	@Test
	public void shouldReturn1IfInputIsACorrectSmiley() {
		List<String> arr = new ArrayList<String>();
		arr.add(";-)");
		assertEquals(1, Smiley.countSmileys(arr));
	}
	
	@Test
	public void shouldReturn0IfInputIsAWrongSmiley() {
		List<String> arr = new ArrayList<String>();
		arr.add(";-(");
		assertEquals(0, Smiley.countSmileys(arr));
	}
	
}
