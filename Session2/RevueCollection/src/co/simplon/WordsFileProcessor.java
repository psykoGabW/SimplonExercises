package co.simplon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordsFileProcessor {

	private List<String> firstFileContent;
	private Set<String> secondFileContent;

	private Collection<String> putFileLinesInCollection(String fileName) throws IOException, FileNotFoundException {
		ArrayList<String> bufferedCollection = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				bufferedCollection.add(line);
			}
			br.close();
		} catch (FileNotFoundException f) {
			throw new FileNotFoundException("File " + fileName + " not found ! ");

		} catch (IOException io) {
			throw new IOException("Error during " + fileName + " reading!");
		}
		return bufferedCollection;
	}

	public WordsFileProcessor(String firstFile, String secondFile) throws IllegalArgumentException, IOException {
		if (firstFile.isEmpty() || secondFile.isEmpty()) {
			throw new IllegalArgumentException("Both file names are required");
		} else {
			firstFileContent = new ArrayList<String>(putFileLinesInCollection(firstFile));
			secondFileContent = new TreeSet<String>(putFileLinesInCollection(secondFile));
		}
	}

	public WordsFileProcessor(String fileName) throws IllegalArgumentException, IOException, FileNotFoundException {
		if (fileName.isEmpty()) {
			throw new IllegalArgumentException("File name is mandatory");
		} else {
			firstFileContent = new ArrayList<String>(putFileLinesInCollection(fileName));
		}
	}

	public Collection<String> getCommonWords() throws IllegalArgumentException {
		Set<String> result = new TreeSet<String>();

		if (firstFileContent != null && secondFileContent != null) {
			for (String word : firstFileContent) {
				if (secondFileContent.contains(word)) {
					result.add(word);
				}
			}
		} else {
			throw new IllegalArgumentException("getCommonWords needs 2 files to process");
		}

		return result;
	}

	public Map<String, Integer> getWordsCounting() throws IllegalArgumentException {
		Map<String, Integer> result = new HashMap<String, Integer>();
		if (firstFileContent == null) {
			throw new IllegalArgumentException("getWordsCounting needs 1 file to process");
		} else {
			for (String word : firstFileContent) {
				Integer wordCount = 0;
				if (result.containsKey(word)) {
					wordCount = result.get(word);
					result.remove(word);
				}
				wordCount++;
				result.put(word, wordCount);
			}
		}
		return result;
	}

	public void displayMapOrderByKey(Map<String, Integer> mapToDisplay) {
		// Sort of Map keys
		TreeSet<String> orderedKey = new TreeSet<String>(mapToDisplay.keySet());
		for (String word : orderedKey) {
			System.out.println(word + " : " + mapToDisplay.get(word));
		}

	}

	public void displayMapOrderByValue(Map<String, Integer> mapToDisplay) {
		// Sort of keys by values
		List<Entry<String, Integer>> list = new ArrayList<>(mapToDisplay.entrySet());

		// Lambda comparator writing Java 8+
		list.sort(
				(o1, o2) -> -1 * (o1.getValue().compareTo(o2.getValue())));

		// Classical comparator writing
		list.sort(
				(new Comparator<Entry<String, Integer>>() {
					@Override
					public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
						if (o1.getValue().compareTo(o2.getValue())==0) {
							return o1.getKey().compareTo(o2.getKey());
						}
						return o1.getValue().compareTo(o2.getValue());
					}
				}).reversed()
				);

		for (Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	public static void main(String arg[]) {

		String fileName1 = "ethics.txt";
		String fileName2 = "wealth.txt";

		if (arg.length > 0) {
			fileName1 = arg[0];
			if (arg.length == 2) {
				fileName2 = arg[1];
			}
		}

		Scanner scanner = new Scanner(System.in);

		String userChoice = "";
		while (!userChoice.equals("0")) {

			System.out.println();
			System.out.println("*** Words file processor *** ");
			System.out.println("1 -> Find common words of " + fileName1 + " and " + fileName2);
			System.out.println("2 -> Count words in file " + fileName1);
			System.out.println("0 -> Exit");
			System.out.print("Your choice : ");

			userChoice = scanner.nextLine();

			switch (userChoice) {
			case "1":
				// First exercise ( Search of common words)
				// Search of common words contained in fileName1 and fileName2 files
				try {
					WordsFileProcessor wordsProcessor = new WordsFileProcessor(fileName1, fileName2);
					Collection<String> setResult = wordsProcessor.getCommonWords();
					System.out.println("Count of CommonWords : " + setResult.size());
					if (setResult.size() > 0) {
						for (String word : setResult) {
							System.out.println(word);
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case "2":

				// Second exercise ( Count of words in a file )
				// Count occurrences of each words in fileName1
				try {
					WordsFileProcessor wP = new WordsFileProcessor(fileName1);
					Map<String, Integer> mapResult = wP.getWordsCounting();
					System.out.println("Words counted in " + fileName1 + " : " + mapResult.size());

					String subMenuChoice = "";
					while (!(subMenuChoice.equals("1") || subMenuChoice.equals("2"))) {
						System.out.println();
						System.out.println("Result Display : ");
						System.out.println("1 -> Order by Words");
						System.out.println("2 -> Order by descending words count");
						System.out.print("Your choice: ");
						subMenuChoice = scanner.nextLine();
						switch (subMenuChoice) {
						case "1":
							wP.displayMapOrderByKey(mapResult);
							break;
						case "2":
							wP.displayMapOrderByValue(mapResult);
							break;
						default:
							System.out.println("Please select a value in [1-2]");
						}
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "0":
				// Exit case
				break;
			default:
				System.out.println("Please select a choice between [0-2]");
			}
		} // while
		scanner.close();
	}
}
