package co.simplon.gwi.tdd.camel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.regex.Pattern;

import org.junit.Test;

public class TestCamelCase {

	@Test
	public void shouldReturnNullIfInputIsNull() {
		assertNull(CamelCase.camelCase(null));
	}

	@Test
	public void shouldReturnEmptyStringIfInputIsEmptyString() {
		assertEquals("", CamelCase.camelCase(""));
	}

	@Test
	public void shouldReturnStringWithFirstCharacterInLowerCase() {
		assertEquals("first", CamelCase.camelCase("First"));
	}
	 
	@Test
	public void shouldContainedOnlyAlphaNumericCharacters() {
		assertFalse(Pattern.compile( "[^a-zA-Z0-9]" ).matcher( CamelCase.camelCase("toto_Max") ).find());		
	}
	
	@Test
	public void shouldIgnoreFirstSpacesBeforeProcessingWords() {
		assertEquals("noSpace", CamelCase.camelCase("  No Space"));
	}
	
	@Test
	public void eachCharactersPlacedAfterNonAlphaNumericCharactersShouldBeUpperCased() {
		assertEquals("aGoodEwokIsADeadOne", CamelCase.camelCase(" A$good ewok*is.a/dead 	one"));
	}
	
	@Test
	public void eachCharactersOfAWordExceptFirstCharacterHasToBeLowerCased() {
		assertEquals("doOrDoNotThereIsNoTry", CamelCase.camelCase("DO OR DO NOT THERE IS NO TRY !"));
	}
}
