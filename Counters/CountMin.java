package PkgCountMin;

import javafx.util.Pair;

public class CountMin {

	
	public static <K, V> void computeCountMin(Pair<K, V> p, int w, int[][] m, int[] s)
	{	
		String strFlow = (String)p.getKey();
		String strFlowSize = (String)p.getValue();
		strFlow = strFlow.replace(".", "");
		long flow = Long.parseLong(strFlow);
		int size = Integer.parseInt(strFlowSize);
		
		int index = 0;
		
		for(int i=0;i < s.length; i++)
		{
			index = (int)((flow ^ s[i]) % w);
			m[i][index] += size;
		
		
		}
		
		
	}
	
	public static <K, V> int lookupCountMin(Pair<K, V> p, int w, int[][] m, int[] s) {
		String strFlow = (String)p.getKey();
		String strFlowSize = (String)p.getValue();
		strFlow = strFlow.replace(".", "");
		long flow = Long.parseLong(strFlow);
		int size = Integer.parseInt(strFlowSize);
		int index = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0;i < s.length; i++)
		{
			index = (int)((flow ^ s[i]) % w);
			if(min > m[i][index] )
				min = m[i][index] ;	
		}
		
		return min;
	}
	

}
