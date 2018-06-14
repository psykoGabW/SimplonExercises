// Test Expression régulère : https://regex101.com/
// Tuto RegExp : https://www.lucaswillems.com/fr/articles/25/tutoriel-pour-maitriser-les-expressions-regulieres

//*******************************************************************
// Welcome to CompileJava!
// www.compilejava.net
// If you experience any issues, please contact us ('More Info')  -->
// Also, sorry that the "Paste" feature no longer works! GitHub broke
// this (so we'll switch to a new provider): https://blog.github.com\
// /2018-02-18-deprecation-notice-removing-anonymous-gist-creation/
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class
import java.util.regex.Pattern;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

// one class needs to have a main() method
public class HelloWorld
{
  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {
    /*
    OtherClass myObject = new OtherClass("Hello World!");
    System.out.println(myObject);
    */

    List<String> lst = new ArrayList<String>();
 lst.add(":^p");
 lst.add(":m");
 lst.add(":-");
 lst.add(";-)");
 lst.add(":-D");

    System.out.println("Nb smiley: " + countSmiley(lst));

  }

  public static int countSmiley(List<String> arr) {
   Pattern pattern = Pattern.compile("[:;][-]{0,1}[)D]");

	List<String> matching = arr.stream()
        .filter(pattern.asPredicate())
        .collect(Collectors.toList());

    return matching.size();
  }


}

// you can add other public classes to this editor in any order
public class OtherClass
{
  private String message;
  private boolean answer = false;
  public OtherClass(String input)
  {
    message = "Why, " + input + " Isn't this something?";
  }
  public String toString()
  {
    return message;
  }
}
