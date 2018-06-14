public class SmileFaces {
public static int countSmileys(List<String> arr){
  int smilingFaces = 0;
  for (String face: arr) {
    if (matchesSmiley(face)) {
      smilingFaces++;
    }
  }
  return smilingFaces;
}

private static boolean matchesSmiley(String input) {
  return input.matches("[:;][-.]?[)pD]");
}
}
