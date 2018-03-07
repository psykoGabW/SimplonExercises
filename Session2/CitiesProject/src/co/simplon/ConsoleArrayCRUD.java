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
import java.util.Locale;
import java.util.Scanner;

public class ConsoleArrayCRUD {
	private static NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
	public static final String TEMP_DIR= "tempfiles";

	private static City[] cities;
	
	private static String cityToString(City city) {
		return city.getName()+";"+numberFormat.format(city.getLatitude())
				+";"+numberFormat.format(city.getLongitude());
	}
	private static void printCities(City[] cities) {
		for(City city : cities) {
			System.out.println(cityToString(city));
		}
	}
	
	private static int findIdxByName(String name) throws IOException {
		int res= -1;
		for(int i=0; i != cities.length && (res == -1); ++i) {
			if(cities[i].getName().equalsIgnoreCase(name)) {
				res= i;
			}
		}
		return res;
	}
	private static City findByName(String name) throws IOException {
		int idx= findIdxByName(name);
		return idx == -1 ? null : cities[idx];
	}
	
	private static void createCity(City city) throws IOException {
		cities= add(cities, city);
	}
	private static void chrono(long start) {
		System.out.println((System.nanoTime()-start)/1.e9);		
	}
	private static void bench(long nb) throws IOException {
		long start= System.nanoTime();
		for(int i=0; i != nb; ++i) {
			findByName("Rouen");
		}
		chrono(start);
		start= System.nanoTime();
		for(int i=0; i != nb; ++i) {
			createCity(new City("Atlantis", 0.,0.));
		}
		chrono(start);
		start= System.nanoTime();
		for(int i=0; i != nb; ++i) {
			closestCity(47., 3.);
		}
		chrono(start);
		start= System.nanoTime();
		for(int i=0; i != nb; ++i) {
			nearCities("Rouen", 10000., "Rennes", 10000.);
		}
		chrono(start);
	}

	private static City closestCity(double lat, double lon) throws IOException {
		City res=null;
		for(City city : cities) {
			if ((res == null) || city.distanceTo(lat, lon)< res.distanceTo(lat, lon)) {
				res= city;
			}
		}
		return res;
	}

	public static void main(String[] args) throws NumberFormatException, IOException{
	      String fileName = args.length > 0 ? args[0] : "NameLatLong.txt";
	      Path filePath = Paths.get(fileName);
	      cities= readCities(filePath);
	      if(args.length >1){
		  bench(Long.parseLong(args[1]));
		  return;
	      }
	      String quitOption= "Quitter";
	      String[] menuOptions={"Choisissez l'opération à effectuer:",
				    "Rechercher une ville par son nom",
				    "Ajouter une ville",
				    "Rechercher la ville la plus proche d'un point",
				    "Rechercher les villes proches d'une ville donnée",
				    "Rechercher les villes proches de deux villes",
				    "Supprimer une ville de la base",
				    "Modifier une ville de la base",
				    quitOption};
	      Scanner input= new Scanner(System.in);
	      ConsoleMenu menu= new ConsoleMenu(menuOptions, input);
	      for(int choice= menu.askSelection(); ! menuOptions[choice].equals(quitOption)
	    		  ; choice= menu.askSelection()){
		  switch (choice){
		  case 1:{
		      String name= input.nextLine();
		      City city = findByName(name);
		      if(city != null){
			  System.out.println(city);
		      }else{
			  System.out.println("Pas de ville avec le nom :"+name);
		      }
		      break;
		  }
		  case 2:{
		      String name= input.nextLine();
		      double lat= input.nextDouble();
		      double lon= input.nextDouble();
		      createCity(new City(name, lat, lon));
		      input.nextLine();  //flush line break
		      break;
		  }
		  case 3:{
		      double lat= input.nextDouble();
		      double lon= input.nextDouble();
		      City city= closestCity(lat, lon);
		      if(city != null){
			  System.out.println(city);
		      }else{
			  System.out.println("Pas de ville trouvée proche de :"+lat+","+lon);
		      }
		      input.nextLine();  //flush line break
		      break;
		  }
		  case 4:{
		      String name= input.nextLine();
		      double d= input.nextDouble();
		      City[] res= nearCity(name, d);
		      printCities(res);
		      input.nextLine();  //flush line break
		      break;
		  }
		  case 5:{
		      String name1= input.nextLine();
		      double d1= input.nextDouble();
		      input.nextLine();  //flush line break
		      String name2= input.nextLine();
		      double d2= input.nextDouble();
		      City[] res= nearCities(name1, d1, name2, d2);
		      printCities(res);
		      input.nextLine();  //flush line break
		      break;
		  }
		  case 6:{
		      String name= input.nextLine();
		      if(deleteCity(name)){
			  System.out.println(name +" effacée.");
		      }else{
			  System.out.println(name +" n'a pas été trouvée.");
		      }
		      break;
		  }
		  case 7:{
		      String name= input.nextLine();
		      double lat= input.nextDouble();
		      double lon= input.nextDouble();
		      if(!updateCity(name, lat, lon)) {
		    	  System.out.println("Aucune ville nommée "+name+" n'a pu être trouvée pour modification");
		      }
		      input.nextLine();  //flush line break
		      break;
		  }
		  }
	      }
	      writeCities(cities, filePath);
	  }
	private static City[] readCities(Path filePath) throws IOException {
		City[] res= new City[0];
		try(BufferedReader br = Files.newBufferedReader(filePath)) {
			br.readLine(); // skip header
			for(String line = br.readLine(); line != null ; line= br.readLine()){
				City tmp= readCity(line);
				if(tmp != null) {
					res= add(res, tmp);
				}
			}
      }
		return res;
	}
	private static void writeCities(City[] cities, Path filePath) throws IOException {
		Path tempDir = Files.createTempDirectory(TEMP_DIR);
	    Path tempFile = Files.createTempFile(tempDir, TEMP_DIR, ".tmp");
		try(BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8
				     , StandardOpenOption.WRITE)) {
				for(City city : cities) {
					writer.write(cityToString(city)+"\n");
				}
			}
			Files.move(tempFile, filePath,  StandardCopyOption.ATOMIC_MOVE);
	}
	private static boolean updateCity(String name, double lat, double lon) throws IOException {
		int idx = findIdxByName(name);
		if (idx == -1) { return false;}
		cities[idx]= new City(name, lat, lon);
		return true;
	}
	private static boolean deleteCity(String name) throws IOException {
		int idx= findIdxByName(name);
		if(idx == -1) { return false; }
		cities= remove(cities, idx);
		return true;
	}

	private static City[] nearCities(String name1, double d1, String name2, double d2) throws IOException {
		City[] res= new City[0];
		for(City nearCity1 : nearCity(name1, d1)) {
			for(City nearCity2 : nearCity(name2, d2)) {
				if(nearCity1.equals(nearCity2)) {
					res= add(res, nearCity1);
				}
			}
		}
		return res;
	}

	private static City readCity(String line) {
		City res=null;
		String[] data = line.split(";");
		if(data.length == 3){
			try{
				res=new City(data[0], numberFormat.parse(data[1]).doubleValue(), numberFormat.parse(data[2]).doubleValue());
				
			}catch(ParseException e){
				System.err.println(e);
			}
		}
		return res;
	}
	private static City[] nearCity(String name, double distance) throws IOException {
		City[] res= new City[0];
		City ref= findByName(name);
		if (ref == null) { return res; }
		for(City city : cities) {
			if(ref.distanceTo(city)< distance) {
				res=add(res, city);
			}
		}
		return res;
	}

	private static City[] add(City[] cities, City city) {
		City[] res= new City[cities.length+1];
		for(int i=0; i != cities.length; ++i) {
			res[i]= cities[i];
		}
		res[cities.length]= city;
		return res;
	}	    
	private static City[] remove(City[] cities, int idx) {
		City[] res= new City[cities.length-(( idx >= 0 && idx < cities.length) ? 1 :0)];
		for(int src=0, dst=0; src != cities.length; ++src) {
			if(src != idx) {
				res[dst]= cities[src];
				++dst;
			}
		}
		return res;
	}

}
