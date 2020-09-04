import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {

    private ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    void quakesWithFilter() {

        ArrayList<QuakeEntry> list  = readFile();
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> first;
        ArrayList<QuakeEntry> ans;

//        Filter f = new MagnitudeFilter(4, 5);
//        Filter f2 = new DepthFilter(-35000, -12000);
//        first = filter(list, f);
//        ans = filter(first, f2);

        Filter f = new DistanceFilter(new Location(35.42, 139.43), 10000000);
        Filter f2 = new PhraseFilter("end", "Japan");
        first = filter(list, f);
        ans = filter(first, f2);

        for (QuakeEntry qe: ans) {
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    private ArrayList<QuakeEntry> readFile(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "/Users/minkhati/Desktop/Repos/Coursera/PrinciplesOfSoftwareDesign/Week1/src/nov20quakedata.atom";
        String source = "/Users/minkhati/Desktop/Repos/Coursera/PrinciplesOfSoftwareDesign/Week1/src/nov20quakedatasmall.atom";
        return parser.read(source);
    }
}
