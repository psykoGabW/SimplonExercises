package co.simplon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleHashMapCRUD {
	private static NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
	public static final String TEMP_DIR = "tempfiles";

	// private static City[] cities;
	private static HashMap<String,City> cities;

	private static String cityToString(City city) {
		return city.getName() + ";" + numberFormat.format(city.getLatitude())
				+ ";" + numberFormat.format(city.getLongitude());
	}

	private static void printCities(HashMap<String,City> cities) {
		for (City city : cities.values()) {
			System.out.println(cityToString(city));
		}
	}


	private static City findByName(String name) throws IOException {
		return ( cities.get(name));
	}

	private static void createCity(City city) throws IOException {
		// cities= add(cities, city);
		cities.put(city.getName(), city);

	}

	private static void chrono(long start) {
		System.out.println((System.nanoTime() - start) / 1.e9);
	}

	private static void bench(long nb) throws IOException {
		System.out.println("Bench mark times");
		
		long start = System.nanoTime();
		for (int i = 0; i != nb; ++i) {
			findByName("Rouen");
		}
		chrono(start);
		start = System.nanoTime();
		for (int i = 0; i != nb; ++i) {
			createCity(new City("Atlantis", 0., 0.));
		}
		chrono(start);
		start = System.nanoTime();
		for (int i = 0; i != nb; ++i) {
			closestCity(47., 3.);
		}
		chrono(start);
		start = System.nanoTime();
		for (int i = 0; i != nb; ++i) {
			nearCities("Rouen", 10000., "Rennes", 10000.);
		}
		chrono(start);
	}

	private static City closestCity(double lat, double lon) throws IOException {
		City res = null;
		for (City city : cities.values()) {
			if ((res == null) || city.distanceTo(lat, lon) < res.distanceTo(lat, lon)) {
				res = city;
			}
		}
		return res;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		String fileName = args.length > 0 ? args[0] : "NameLatLong.txt";
		Path filePath = Paths.get(fileName);
		System.out.println("Read cities Time");
		long startTime = System.nanoTime();
		chrono(startTime);
		cities = readCities(filePath);
		
		if (args.length > 1) {
			bench(Long.parseLong(args[1]));
			return;
		}
		String quitOption = "Quitter";
		String[] menuOptions = { "Choisissez l'opération à effectuer:",
				"Rechercher une ville par son nom",
				"Ajouter une ville",
				"Rechercher la ville la plus proche d'un point",
				"Rechercher les villes proches d'une ville donnée",
				"Rechercher les villes proches de deux villes",
				"Supprimer une ville de la base",
				"Modifier une ville de la base",
				quitOption };
		Scanner input = new Scanner(System.in);
		ConsoleMenu menu = new ConsoleMenu(menuOptions, input);
		for (int choice = menu.askSelection(); !menuOptions[choice].equals(quitOption); choice = menu.askSelection()) {
			switch (choice) {
			case 1: {
				String name = input.nextLine();
				City city = findByName(name);
				if (city != null) {
					System.out.println(city);
				} else {
					System.out.println("Pas de ville avec le nom :" + name);
				}
				break;
			}
			case 2: {
				String name = input.nextLine();
				double lat = input.nextDouble();
				double lon = input.nextDouble();
				createCity(new City(name, lat, lon));
				input.nextLine(); // flush line break
				break;
			}
			case 3: {
				double lat = input.nextDouble();
				double lon = input.nextDouble();
				City city = closestCity(lat, lon);
				if (city != null) {
					System.out.println(city);
				} else {
					System.out.println("Pas de ville trouvée proche de :" + lat + "," + lon);
				}
				input.nextLine(); // flush line break
				break;
			}
			case 4: {
				String name = input.nextLine();
				double d = input.nextDouble();
				HashMap<String,City> res = nearCity(name, d);
				printCities(res);
				input.nextLine(); // flush line break
				break;
			}
			case 5: {
				String name1 = input.nextLine();
				double d1 = input.nextDouble();
				input.nextLine(); // flush line break
				String name2 = input.nextLine();
				double d2 = input.nextDouble();
				HashMap<String,City> res = nearCities(name1, d1, name2, d2);
				printCities(res);
				input.nextLine(); // flush line break
				break;
			}
			case 6: {
				String name = input.nextLine();
				if (deleteCity(name)) {
					System.out.println(name + " effacée.");
				} else {
					System.out.println(name + " n'a pas été trouvée.");
				}
				break;
			}
			case 7: {
				String name = input.nextLine();
				double lat = input.nextDouble();
				double lon = input.nextDouble();
				if (!updateCity(name, lat, lon)) {
					System.out.println("Aucune ville nommée " + name + " n'a pu être trouvée pour modification");
				}
				input.nextLine(); // flush line break
				break;
			}
			}
		}
		writeCities(cities, filePath);
	}

	private static HashMap<String,City> readCities(Path filePath) throws IOException {
		HashMap<String,City> res = new HashMap<String,City>();
		try (BufferedReader br = Files.newBufferedReader(filePath)) {
			br.readLine(); // skip header
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				City tmp = readCity(line);
				if (tmp != null) {

					add(res, tmp);
				}
			}
		}
		
		return res;
	}

	private static void writeCities(HashMap<String,City> cities, Path filePath) throws IOException {
		Path tempDir = Files.createTempDirectory(TEMP_DIR);
		Path tempFile = Files.createTempFile(tempDir, TEMP_DIR, ".tmp");
		try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8,
				StandardOpenOption.WRITE)) {
			for (City city : cities.values()) {
				writer.write(cityToString(city) + "\n");
			}
		}
		Files.move(tempFile, filePath, StandardCopyOption.ATOMIC_MOVE);
	}

	private static boolean updateCity(String name, double lat, double lon) throws IOException {
		
		cities.replace(name, new City(name, lat, lon));
		
		return true;
	}

	private static boolean deleteCity(String name) throws IOException {
		
		cities.remove(name);
		
		return true;
	}

	private static HashMap<String,City> nearCities(String name1, double d1, String name2, double d2) throws IOException {
		HashMap<String,City> res = new HashMap<String,City>();
		for (City nearCity1 : nearCity(name1, d1).values()) {
			for (City nearCity2 : nearCity(name2, d2).values()) {
				if (nearCity1.equals(nearCity2)) {
					add(res, nearCity1);
				}
			}
		}
		return res;
	}

	private static City readCity(String line) {
		City res = null;
		String[] data = line.split(";");
		if (data.length == 3) {
			try {
				res = new City(data[0], numberFormat.parse(data[1]).doubleValue(),
						numberFormat.parse(data[2]).doubleValue());

			} catch (ParseException e) {
				System.err.println(e);
			}
		}
		return res;
	}

	private static HashMap<String,City> nearCity(String name, double distance) throws IOException {
		HashMap<String,City> res = new HashMap<String,City>();
		City ref = findByName(name);
		if (ref == null) {
			return res;
		}
		for (City city : cities.values()) {
			if (ref.distanceTo(city) < distance) {
				add(res, city);
			}
		}
		return res;
	}

	private static void add(HashMap<String,City> cities, City city) {
		cities.put(city.getName(),city);
	}

}
