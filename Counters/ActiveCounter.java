package PkgCountMin;

public class ActiveCounter {

	
	public static int[] binaryAdd(int[] counterArray, int currentbit, int lastcounterindex, int expnt_bits) {

		if(currentbit <= lastcounterindex)
		{
			if(counterArray[currentbit] == 1)
			{
				counterArray[currentbit] = 0;
				binaryAdd(counterArray, currentbit+1, lastcounterindex, expnt_bits);
			}
			else if(counterArray[currentbit] == 0)
			{
				counterArray[currentbit] = 1;
			}
			
		}	
		else
		{
			binaryAdd(counterArray, 0, expnt_bits ,expnt_bits);
			for(int k=lastcounterindex;k> expnt_bits - 1;k--)
			{
				if(k==lastcounterindex)
					counterArray[k] = 1;
				else
					counterArray[k]=0;
			}
			
		}
			
		return counterArray;
	}
	
	
	public static long binaryToDecimal(int[] counterArray, int expbits)
	{
		char[] exp = new char[expbits];
		char[] number = new char[expbits];
		for(int i=expbits-1;i>=0;i--)
		{
			exp[expbits - i - 1] = counterArray[i]==1? '1' : '0';
		}
		//System.out.println(new String(exp));
		
		
		for(int i=counterArray.length - 1;i>=expbits;i--)
		{
			number[counterArray.length - i - 1] = counterArray[i]==1? '1' : '0';
		}
		
		//System.out.println(Integer.parseInt(new String(number),2));
		
		return (long) ((Integer.parseInt(new String(number),2)) * (Math.pow( 2, Integer.parseInt(new String(exp),2))));
		//for(int i = expbits; i )
	}
	
	public static long expntToDecimal(int[] counterArray, int expbits)
	{
		char[] exp = new char[expbits];
		char[] number = new char[expbits];
		for(int i=expbits-1;i>=0;i--)
		{
			exp[expbits - i - 1] = counterArray[i]==1? '1' : '0';
		}
//		System.out.println(new String(exp));
		
		
		return (long) (( Integer.parseInt(new String(exp),2)));
		//for(int i = expbits; i )
	}
	
	
}
