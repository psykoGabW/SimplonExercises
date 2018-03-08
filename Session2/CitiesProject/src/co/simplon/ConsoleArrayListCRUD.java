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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleArrayListCRUD {
	private static NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
	public static final String TEMP_DIR = "tempfiles";

	// private static City[] cities;
	private static ArrayList<City> cities;

	private static String cityToString(City city) {
		return city.getName() + ";" + numberFormat.format(city.getLatitude())
				+ ";" + numberFormat.format(city.getLongitude());
	}

	private static void printCities(ArrayList<City> cities) {
		for (City city : cities) {
			System.out.println(cityToString(city));
		}
	}

	private static int findIdxByName(String name) throws IOException {
		int res = -1;

		for (int i = 0; i != cities.size() && (res == -1); ++i) {
			if (cities.get(i).getName().equalsIgnoreCase(name)) {
				res = i;
			}
		}
		return res;
	}

	private static City findByName(String name) throws IOException {
		int idx = findIdxByName(name);
		return idx == -1 ? null : cities.get(idx);
	}

	private static void createCity(City city) throws IOException {
		// cities= add(cities, city);
		cities.add(city);

	}

	private static void chrono(long start) {
		System.out.println((System.nanoTime() - start) / 1.e9);
	}

	private static void bench(long nb) throws IOException {
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
		for (City city : cities) {
			if ((res == null) || city.distanceTo(lat, lon) < res.distanceTo(lat, lon)) {
				res = city;
			}
		}
		return res;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		String fileName = args.length > 0 ? args[0] : "NameLatLong.txt";
		Path filePath = Paths.get(fileName);
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
				ArrayList<City> res = nearCity(name, d);
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
				ArrayList<City> res = nearCities(name1, d1, name2, d2);
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

	private static ArrayList<City> readCities(Path filePath) throws IOException {
		ArrayList<City> res = new ArrayList<City>();
		try (BufferedReader br = Files.newBufferedReader(filePath)) {
			br.readLine(); // skip header
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				City tmp = readCity(line);
				if (tmp != null) {

					add(res, tmp);
				}
			}
		}
		Collections.sort(res);
		return res;
	}

	private static void writeCities(ArrayList<City> cities, Path filePath) throws IOException {
		Path tempDir = Files.createTempDirectory(TEMP_DIR);
		Path tempFile = Files.createTempFile(tempDir, TEMP_DIR, ".tmp");
		try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8,
				StandardOpenOption.WRITE)) {
			for (City city : cities) {
				writer.write(cityToString(city) + "\n");
			}
		}
		try {
		Files.move(tempFile, filePath, StandardCopyOption.ATOMIC_MOVE);
		} catch (Exception e) {
		// ATOMIC_MOVE is only efficient if source & destination files are on the same disk
		Files.move(tempFile, filePath, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	private static boolean updateCity(String name, double lat, double lon) throws IOException {
		int idx = findIdxByName(name);
		if (idx == -1) {
			return false;
		}
		// cities.get[idx]= new City(name, lat, lon);
		cities.remove(idx);
		cities.add(idx, new City(name, lat, lon));
		return true;
	}

	private static boolean deleteCity(String name) throws IOException {
		int idx = findIdxByName(name);
		if (idx == -1) {
			return false;
		}
		remove(cities, idx);
		return true;
	}

	private static ArrayList<City> nearCities(String name1, double d1, String name2, double d2) throws IOException {
		ArrayList<City> res = new ArrayList<City>();
		for (City nearCity1 : nearCity(name1, d1)) {
			for (City nearCity2 : nearCity(name2, d2)) {
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

	private static ArrayList<City> nearCity(String name, double distance) throws IOException {
		ArrayList<City> res = new ArrayList<City>();
		City ref = findByName(name);
		if (ref == null) {
			return res;
		}
		for (City city : cities) {
			if (ref.distanceTo(city) < distance) {
				add(res, city);
			}
		}
		return res;
	}

	private static void add(ArrayList<City> cities, City city) {
		cities.add(city);
	}

	private static void remove(ArrayList<City> cities, int idx) {
		if (idx < cities.size()) {
			cities.remove(idx);
		}

	}

}
