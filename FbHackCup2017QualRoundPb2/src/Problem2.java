import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem2 {
	
	public static final int MIN_WEIGHT = 50;

	public static void main(String[] args) {
		List<List<Integer>> weightsPerDaysList = readFile("input.txt");
		for (int i = 0, max = weightsPerDaysList.size(); i < max; i++) {	//For each day
			List<Integer> currentDay = weightsPerDaysList.get(i);
			Collections.sort(currentDay);
			int nbTrips = 0;	//Will holds the number of trips made by Wilson that day
			while (!currentDay.isEmpty() && isWorthAnotherTrip(currentDay)) {
				int weight = currentDay.remove(currentDay.size() - 1);
				int ref = weight; //The reference weight
				while (weight < MIN_WEIGHT) { 
					currentDay.remove(0);
					weight += ref;
				}
				nbTrips++;
			}
			System.out.println("Case #" + Integer.toString(i + 1) + ": " + Integer.toString(nbTrips));
		}
	}

	public static boolean isWorthAnotherTrip(List<Integer> dailyList) {
		return ((dailyList.get(dailyList.size() - 1) * dailyList.size()) >= MIN_WEIGHT);
	}
	
	public static List<List<Integer>> readFile (String fileName) {
		List<List<Integer>> weightsPerDaysList = new ArrayList<List<Integer>>();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    for (int i = 0, nbDays = Integer.parseInt(br.readLine()); i < nbDays; i++) {	//For each days
		    	weightsPerDaysList.add(new ArrayList<Integer>());
		    	for (int j = 0, nbItems = Integer.parseInt(br.readLine()); j < nbItems; j++) {	//For each items that day
		    		weightsPerDaysList.get(i).add(Integer.parseInt(br.readLine()));	//Insert item's weight
		    	}
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weightsPerDaysList;
	}
}
