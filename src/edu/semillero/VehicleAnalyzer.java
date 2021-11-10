package edu.semillero;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.util.Pair;

public class VehicleAnalyzer {

	private List<String> listaFuncional = Arrays.asList(
			"Funci�n para retornar una lista de Carros o  de motos",
			"Funci�n para retornar un mapa con dos claves (Carro, Moto) seg�n el tipo de veh�culo.",
			"Funci�n para calcular impuesto del veh�culo (46.5%) y guardarlo en el mismo objeto",
			"Funci�n para establecer el estado del veh�culo ('Ok', 'Averiado'). Usar aleatorios.",
			"Funci�n para obtener los veh�culos clasificados seg�n el a�o de fabricaci�n.",
			"Funci�n para obtener los veh�culos clasificados por su valor actual en mercado (al precio restarle el 10% por a�o).",
			"Funci�n para clasificar los carros si son autom�ticos o no.",
			"Funci�n para obtener datos sobre el precio de los carros (m�s costoso, menos costoso, promedio, suma).",
			"Funci�n para obtener datos sobre el precio de las motos (m�s costosa, menos costosa, promedio, suma).",
			"Funci�n para obtener, dada una marca, el promedio de los precios de sus veh�culos, el mas costoso y menos costoso.",
			"Funci�n para clasificar los colores disponibles por modelo, de una marca en espec�fico."		
																); 
	
	private final int CAR = 7;
	private final int MOTO = 6;
	
	
	public static void main(String[] args) {
		new VehicleAnalyzer()
			.analizeVehicles();

	}
	
	public void analizeVehicles() {
		
//		int opcion = 0;
//		
//		do {
//			listaFuncional.stream()
//						.forEach(System.out::println);
//			Scanner scanner = new Scanner(System.in);  // Create a Scanner object
//		    System.out.println("Enter opt");
//
//		    opcion = scanner.nextInt();  // Read user input
//		    
//		} while(opcion > 0);
		
		// ReadFile
		
		Stream<String> records = Stream.empty();
		
		try {
			
			//* Leer archivo Vehiculos.txt
			
			JFileChooser jf = new JFileChooser();
						
			int seleccion = jf.showOpenDialog(null);
			
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
				File fichero = jf.getSelectedFile();
				records = Files.lines(Paths.get(fichero.getPath()));
			}
							
							
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		
		
		//* Generar objetos a partir de lo le�do en el archivo, aplicar concepto de herencia y retornar lista de veh�culos
		
		List<Vehicle> vehicles = records.filter(x -> x.split(",").length > 2)
										.map(x -> BuildVehicle(x))
										.collect(Collectors.toList());
		
				
		//* Funci�n para retornar un mapa con dos claves (Carro, Moto) seg�n el tipo de veh�culo.
		System.out.println(
		vehicles.stream().collect(Collectors.groupingBy(x -> x.getClass()))
				);
				
		
		
		//* Funci�n para retornar una lista de Carros o  de motos
		List<Car> carsRecords = vehicles.stream()
												.filter(x -> x instanceof Car)
												.map(x -> (Car)x)
												.collect(Collectors.toList());
		List<Moto> motosRecords = vehicles.stream()
												.filter(x -> x instanceof Moto)
												.map(x -> (Moto)x)
												.collect(Collectors.toList());
	
		
		carsRecords.forEach(System.out::println);
		
		motosRecords.forEach(System.out::println);
				
		
		
		
		//* Funci�n para calcular impuesto del veh�culo (46.5%) y guardarlo en el mismo objeto
		
		carsRecords.stream().map(x -> new Pair<Vehicle, Double>(x, Double.valueOf(x.get_price()*0.465))).forEach(System.out::println);
						
		//* Funci�n para establecer el estado del veh�culo ('Ok', 'Averiado'). Usar aleatorios.
		
		System.out.println(
				vehicles.stream().collect(Collectors.partitioningBy(x -> Math.random() > 0.5))
				);
		
		//* Funci�n para obtener los veh�culos clasificados seg�n el a�o de fabricaci�n.
		System.out.println(
				carsRecords.stream().collect(Collectors.groupingBy(x -> ((Car)x).get_model()))
		);
		
		//* Funci�n para obtener los veh�culos clasificados por su valor actual en mercado (al precio restarle el 10% por a�o).
		System.out.println(
				carsRecords.stream().collect(Collectors.groupingBy(x -> ((Car)x).get_price() - (((Car)x).get_price()*0.10) ))
		);
		
		//* Funci�n para clasificar los carros si son autom�ticos o no.
		System.out.println(
				carsRecords.stream().collect(Collectors.groupingBy(x -> ((Car)x).get_transmisionType()))
		);
		
		//* Funci�n para obtener datos sobre el precio de los carros (m�s costoso, menos costoso, promedio, suma).
		DoubleSummaryStatistics carPriceStatictics = carsRecords.stream().collect(Collectors.summarizingDouble(Car::get_price));
		
		System.out.println(carPriceStatictics.getMax());
		System.out.println(carPriceStatictics.getMin());
		System.out.println(carPriceStatictics.getAverage());
		System.out.println(carPriceStatictics.getSum());
		
		//* Funci�n para obtener datos sobre el precio de las motos (m�s costosa, menos costosa, promedio, suma).
			
		DoubleSummaryStatistics motoPriceStatictics = motosRecords.stream().collect(Collectors.summarizingDouble(Moto::get_price));
		
		System.out.println(motoPriceStatictics.getMax());
		System.out.println(motoPriceStatictics.getMin());
		System.out.println(motoPriceStatictics.getAverage());
		System.out.println(motoPriceStatictics.getSum());
		
		//* Funci�n para obtener, dada una marca, el promedio de los precios de sus veh�culos, el mas costoso y menos costoso.
		
		DoubleSummaryStatistics carBrandPriceStatictics = carsRecords.stream()
				.filter(x -> x.get_brand().equals("Mercedes Benz"))
				.collect(Collectors.summarizingDouble(Car::get_price));
		
		System.out.println(carBrandPriceStatictics.getMax());
		System.out.println(carBrandPriceStatictics.getMin());
		System.out.println(carBrandPriceStatictics.getAverage());
		
		DoubleSummaryStatistics motoBrandPriceStatictics = motosRecords.stream()
				.filter(x -> x.get_brand().equals("Yamaha"))
				.collect(Collectors.summarizingDouble(Moto::get_price));
		
		System.out.println(motoBrandPriceStatictics.getMax());
		System.out.println(motoBrandPriceStatictics.getMin());
		System.out.println(motoBrandPriceStatictics.getAverage());
		
		//* Funci�n para clasificar los colores disponibles por modelo, de una marca en espec�fico.
		System.out.println(		
					vehicles.stream().filter(x -> x.get_brand().equals("Mercedes Benz")).collect(Collectors.groupingBy(x -> Arrays.asList(((Vehicle)x).get_model(),((Vehicle)x).get_color()), Collectors.counting()))
				);
	}
	
	
	private Vehicle BuildVehicle(String record) {
		String[] data = record.split(",");
		return data.length == CAR? buildCar(data):buildMoto(data);
	}

	private List<Vehicle> getVehicleTypeList(int vehicleType, Map<Object, List<String>> groupOfRecords)
	{
		return (List<Vehicle>) groupOfRecords
				.get(vehicleType)
				.stream()
				.map(x -> vehicleType == CAR? buildCar(x.split(x)) : buildMoto(x.split(x)) );
				
	}
	

	private Car buildCar(String[] data) {
		
		return new Car(data[0]
					,data[1]
					,Integer.parseInt(data[2].trim())
					,Double.parseDouble(data[3].trim())
					,data[4]
					,data[5]
					,data[6]);
	}
	
	private Moto buildMoto(String[] data) {
		
		return new Moto(data[0]
					,data[1]
					,Integer.parseInt(data[2].trim())
					,data[3]
					,Double.parseDouble(data[4].trim())
					,data[5]);
	}

	
	

}
