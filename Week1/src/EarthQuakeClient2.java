import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {

    private ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    }

    void testMatchAllFilter(){
        ArrayList<QuakeEntry> list = readFile();
        System.out.println("# of quakes read: " + list.size());

        MatchAllFilter m = new MatchAllFilter(new ArrayList <>());
        m.addFilter(new DepthFilter(-12000, -10000));

        ArrayList<QuakeEntry> ans = filter(list, m);

        System.out.println("# of quakes fit: " + ans.size());

        for(QuakeEntry q: ans){
            System.out.println(q);
        }
    }

    void quakesWithFilter() {

        ArrayList<QuakeEntry> list  = readFile();
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> first;
        ArrayList<QuakeEntry> ans;

        Filter f = new MagnitudeFilter(3.5,4.5);
        Filter f2 = new DepthFilter(-55000, -20000);
        first = filter(list, f);
        ans = filter(first, f2);

        System.out.println("# of quakes that match criteria: " + ans.size());

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
        String source = "/Users/minkhati/Desktop/Repos/Coursera/PrinciplesOfSoftwareDesign/Week1/src/nov20quakedata.atom";
        //String source = "/Users/minkhati/Desktop/Repos/Coursera/PrinciplesOfSoftwareDesign/Week1/src/nov20quakedatasmall.atom";
        return parser.read(source);
    }

    void findLargestQuakes(){
        ArrayList<QuakeEntry> data = readFile();
         int num = 50;
        ArrayList<QuakeEntry> ans = new ArrayList <>();

        for(int i = 0; i < num; i++){
            int max = 0;
            for(int j = 1; j < data.size(); j++){
                if(data.get(j).getMagnitude() > data.get(max).getMagnitude()){
                    max = j;
                }
            }
            ans.add(data.get(max));
            data.remove(max);
        }

        for(QuakeEntry q: ans){
            System.out.println(q);
        }

    }
}
