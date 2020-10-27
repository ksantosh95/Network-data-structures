package PkgCountMin;
import java.util.Arrays;

import javafx.util.Pair;

public class CounterSketch {

	public static void computeCounterSketch(Pair[] inputArray,int w, int[][] m, int[] s)
	{
		for(Pair p: inputArray) {
			
			String strFlow = (String)p.getKey();
			String strFlowSize = (String)p.getValue();
			int flow = strFlow.hashCode();
			
			int size = Integer.parseInt(strFlowSize);
			int sign = 0;
			int index = 0;
			
			for(int i=0;i < s.length; i++)
			{
				sign = (flow >> 31) & 1;
				
				index = (int)Math.abs((flow ^ s[i]) % w);
			
				
				m[i][index] += ((sign == 1)? size : -(size));
				//System.out.println(flow + " " + sign + " " + size);
			
			}
		}
	}
	
	public static <K, V> int lookupCounterSketch(Pair<K, V> p, int w, int[][] m, int[] s) {
		String strFlow = (String)p.getKey();
		String strFlowSize = (String)p.getValue();
		int flow = strFlow.hashCode();
		int size = Integer.parseInt(strFlowSize);
		int index = 0;
		int sign = 0;
		long a = Long.parseLong("6723115157");
		int[] median = new int[s.length];
		for(int i=0;i < s.length; i++)
		{
			sign = (flow >> 31) & 1;
			index = (int)Math.abs((flow ^ s[i]) % w);
			median[i] = ((sign == 1)? m[i][index] : -(m[i][index]));
			if(flow == a)
			{
				//System.out.println(flow + " " + sign + " " + median[i]);
			}
		}
		
		Arrays.sort(median);
		return median[1];
	}
	
}
