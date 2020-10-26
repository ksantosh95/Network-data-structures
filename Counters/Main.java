package PkgCountMin;

import static PkgCountMin.CountMin.computeCountMin;
import static PkgCountMin.CountMin.lookupCountMin;
import static PkgCountMin.CounterSketch.computeCounterSketch;
import static PkgCountMin.CounterSketch.lookupCounterSketch;
import static PkgCountMin.ActiveCounter.binaryAdd;
import static PkgCountMin.ActiveCounter.binaryToDecimal;
import static PkgCountMin.ActiveCounter.expntToDecimal;

import java.io.*;
import java.util.*; 
import javafx.util.Pair;


class FlowCounter 
{ 
    int estimate, actual; 
    String flowid; 
  
    // Constructor 
    public FlowCounter(int estimate, String flowid, 
                               int actual) 
    { 
        this.estimate = estimate; 
        this.flowid = flowid; 
        this.actual = actual; 
    } 

    public String toString() 
    { 
        return this.flowid + "\t\t " + this.estimate + 
                           "\t\t " + this.actual; 
    } 
} 

class SortbyEstimate implements Comparator<FlowCounter> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(FlowCounter a, FlowCounter b) 
    { 
        return  b.estimate - a.estimate; 
    } 
} 


public class Main {

	private static BufferedReader br;

	public static void main(String[] args) throws IOException {
	
		File file = new File("project3input.txt");
		br = new BufferedReader(new FileReader(file)); 
		
		//COUNT MIN DEMO Parameters
		int num_of_flows = Integer.parseInt(br.readLine());
		int k = 3;
		int w = 3000;
		
		//Input Array read from the file
		Pair[] inputArray = new Pair[num_of_flows];
		
		int i=0;
		String st; 
		while ((st = br.readLine()) != null) 
		{
			String columns[] = st.split("\\s+");
			inputArray[i] = new Pair(columns[0], columns[1]);
			i++;
		}
		
		//runCountMin(k,w,inputArray,num_of_flows);
		//runCounterSketch(k,w,inputArray,num_of_flows);
		runActiveCounter(1000000);
		
		
}
	
	public static void runCountMin(int k, int w, Pair[] inputArray, int num_of_flows)
	{
		int[][] m = new int[k][w]; 
		int min_counter = 0;
		int error = 0;
		int[] s = computeRandomArray(k);
		ArrayList<FlowCounter> ar = new ArrayList<FlowCounter>(); 
		for(Pair p : inputArray)
		{
			computeCountMin(p , w, m, s);
		}
		
		for(Pair p : inputArray)
		{
			min_counter = lookupCountMin(p , w, m, s);
			error += (min_counter - Integer.parseInt((String)p.getValue())  );	
			ar.add(new FlowCounter(min_counter, (String)p.getKey(), Integer.parseInt((String)p.getValue())));		
		}
		System.out.println("*****Count Min Result*****");
		System.out.println(error / num_of_flows);
        Collections.sort(ar, new SortbyEstimate()); 
        for (int j=0; j<100; j++) 
            System.out.println(ar.get(j)); 
	}
	
	
	public static void runCounterSketch(int k, int w, Pair[] inputArray, int num_of_flows)
	{
		int[][] m = new int[k][w]; 
		int count = 0;
		int error = 0;
		int[] s = computeRandomArray(k);
		ArrayList<FlowCounter> ar = new ArrayList<FlowCounter>(); 
		
		computeCounterSketch(inputArray , w, m, s);
		
		
		for(Pair p : inputArray)
		{
			count = lookupCounterSketch(p , w, m, s);
			error += Math.abs(count - Integer.parseInt((String)p.getValue())  );	
			ar.add(new FlowCounter(count, (String)p.getKey(), Integer.parseInt((String)p.getValue())));		
		}
		System.out.println("*****Counter Sketch Result*****");
		System.out.println(error / num_of_flows);
       Collections.sort(ar, new SortbyEstimate()); 
        for (int j=0; j<100; j++) 
            System.out.println(ar.get(j)); 
	}
	
	public static void runActiveCounter(int num)
	{
		Random r = new Random(); 
		
		
		int []counterArray = new int[32];
		int lastcounterindex = counterArray.length - 1;
	
		int currentbit = 16;
		int expnt_bits = 16;
		int expnt_result = 0;
		int prob = 1;
		for(int i=0;i < num;i++)
		{
			int chance = r.nextInt(prob) ;
			if(chance==0)
				counterArray = binaryAdd(counterArray,currentbit, lastcounterindex, expnt_bits);
				expnt_result = (int) expntToDecimal(counterArray,expnt_bits);
				prob =  (int) Math.ceil(Math.pow(2, expnt_result));
		}
		
		
		
		long result = binaryToDecimal(counterArray,expnt_bits);
		System.out.println("*****Active Counter Result*****");
		System.out.println(result);

	}
	
	
	
	

	public static int[] computeRandomArray(int num_of_elements)
	{
		int []array = new int[num_of_elements];
		Random rand = new Random();
		for(int i=0; i<num_of_elements;i++)
		{
			array[i] = Math.abs(rand.nextInt());
		
		}
		
		return array;
	}
	
	
	
}
