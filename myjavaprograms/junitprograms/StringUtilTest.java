package junitprograms;


//StringUtilTest.java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

	 @Test
	 public void testRemoveDuplicates_withDuplicates() {
	     String input = "banana";
	     String expected = "ban";
	     assertEquals(expected, StringUtil.removeDuplicates(input));
	 }
	
	 @Test
	 public void testRemoveDuplicates_noDuplicates() {
	     String input = "abcd";
	     String expected = "abcd";
	     assertEquals(expected, StringUtil.removeDuplicates(input));
	 }
	
	 @Test
	 public void testRemoveDuplicates_allSameChar() {
	     String input = "aaaaaa";
	     String expected = "a";
	     assertEquals(expected, StringUtil.removeDuplicates(input));
	 }
	
	 @Test
	 public void testRemoveDuplicates_emptyString() {
	     String input = "";
	     String expected = "";
	     assertEquals(expected, StringUtil.removeDuplicates(input));
	 }
	
	 @Test
	 public void testRemoveDuplicates_nullInput() {
	     assertNull(StringUtil.removeDuplicates(null));
	 }
	
	 @Test
	 public void testRemoveDuplicates_specialChars() {
	     String input = "a@b@c@a";
	     String expected = "a@bc";
	     assertEquals(expected, StringUtil.removeDuplicates(input));
	 }
}
