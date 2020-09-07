
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    private int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    private void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
           if(!checkInSortedOrder(in)){
               int minIdx = getSmallestMagnitude(in,i);
               QuakeEntry qi = in.get(i);
               QuakeEntry qmin = in.get(minIdx);
               in.set(i,qmin);
               in.set(minIdx,qi);
           }
           else {
               System.out.println("# of passes: " + i);
               break;
           }
       }
        
    }

    public void testSort() {
        ArrayList<QuakeEntry> list  = readFile();
        System.out.println("read data for "+list.size()+" quakes");

        //sortByMagnitudeWithCheck(list);
        sortByDepth(list);
        //sortByMagBubbleSort(list);
        //sortByMagBSWithCheck(list);

        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }

    public ArrayList<QuakeEntry> readFile(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "/Users/minkhati/Desktop/Repos/Coursera/PrinciplesOfSoftwareDesign/Week2/src/nov20quakedatasmall.atom";
        String source = "/Users/minkhati/Desktop/Repos/Coursera/PrinciplesOfSoftwareDesign/Week2/src/earthQuakeDataDec6sample1.atom";
        return parser.read(source);
    }

    public void createCSV() {
        ArrayList<QuakeEntry> list  = readFile();
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    private void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

	private int getLargestDepth(ArrayList<QuakeEntry> data, int from){
        int maxIdx = from;

        for(int i = from+1; i < data.size(); i++){
            if(data.get(i).getDepth() > data.get(maxIdx).getDepth()){
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    private void sortByDepth(ArrayList<QuakeEntry> in){
        for(int i = 0; i < in.size(); i++){
            int max = getLargestDepth(in, i);
            Collections.swap(in, i, max);
        }
    }

    private void onePassBubbleSort(ArrayList<QuakeEntry> data, int numSorted){
        for(int i = 0; i < data.size()-numSorted; i++){
            int j = i+1;
            if( j < data.size() && data.get(i).getMagnitude() > data.get(j).getMagnitude()){
                Collections.swap(data, i, j);
            }
        }
    }

    private void sortByMagBubbleSort(ArrayList<QuakeEntry> data){
            for(int i = 0; i < data.size()-1; i++){
                onePassBubbleSort(data,i);
            }
    }

    private boolean checkInSortedOrder(ArrayList<QuakeEntry> data){
        for(int i = 0; i < data.size()-1; i++){
            int j = i+1;
            if(data.get(i).getMagnitude() > data.get(j).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    /**
     * BS stands for bubble sort.
     */
    private void sortByMagBSWithCheck(ArrayList<QuakeEntry> data){
        for(int i = 0; i < data.size()-1; i++){
            if(checkInSortedOrder(data)){
                System.out.println("# of passes needed: " + i);
                break;
            }
            else {
                onePassBubbleSort(data, i);
            }
        }
    }
}
