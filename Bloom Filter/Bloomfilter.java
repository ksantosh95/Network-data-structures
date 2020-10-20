package BloomFilter;

import java.util.Random;

public class Bloomfilter {

	public static int []bloom_filter_array = new int[10000];
	public static int []counting_bloom_filter_array = new int[10000];
	public static int []coded_bloom_filter_1 = new int[30000];
	public static int []coded_bloom_filter_2 = new int[30000];
	public static int []coded_bloom_filter_3 = new int[30000];
	
public static void main(String[] args) {
		
		//Bloom Filter Demo Input parameters
		int bloom_elements = 1000;
		int bloom_bits = 10000;
		int bloom_hashes = 7;
		
		//Counting Bloom Filter Demo Input Parameters
		int counting_elements = 1000;
		int counting_elements_removed = 500;
		int counting_elements_added = 500;
		int counting_counters = 10000;
		int counting_hashes = 7;
		
		//Coded Bloom Filter Demo Input Parameters
		int coded_num_of_sets = 7;
		int coded_num_of_elements = 1000;
		int coded_num_of_filters = 3;
		int coded_num_of_bits = 30000;
		int coded_num_of_hashes = 7;
		
		bloomFilter(bloom_elements, bloom_bits, bloom_hashes);
		countingFilter(counting_elements,counting_elements_removed,counting_elements_added,counting_counters,counting_hashes );
		codedBloomFilter(coded_num_of_sets,coded_num_of_elements,coded_num_of_filters,coded_num_of_bits,coded_num_of_hashes);
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

//-------------------------BLOOM FILTER-------------------------------------------

public static void bloomFilter(int num_of_elements, int num_of_bits, int num_of_hashes)
{
	int []A = computeRandomArray(num_of_elements);
	
	int []s = computeRandomArray(num_of_hashes);

	int []B = computeRandomArray(num_of_elements);

	computeBloomFilter(A, s, num_of_bits);
	

	int results_of_arrayA = lookupBloomFilter(A, s, num_of_bits);
	int results_of_arrayB = lookupBloomFilter(B, s, num_of_bits);
	System.out.println("*******Bloom Filter********");
	System.out.println(results_of_arrayA);
	System.out.println(results_of_arrayB);
	
}


public static void computeBloomFilter(int[] element_array, int[] s,int num_of_bits) 
{
	for(int element: element_array)
	{
		for(int i=0;i<s.length;i++)
		{
			int refIndex = ((element ^ s[i]) % num_of_bits);
			
			
				bloom_filter_array[refIndex] = 1;
			
		}
	}
}

public static int lookupBloomFilter(int []lookupArray, int []s, int num_of_bits) 
{
	int count = 0;
	boolean element_present = true;
	for(int element: lookupArray)
	{
		element_present = true;
		for(int i=0;i<s.length;i++)
		{
			int refIndex = ((element ^ s[i]) % num_of_bits);
			
			if(bloom_filter_array[refIndex] ==0)
			{
				element_present = false;
				break;
				
			}
		}
		if (element_present) 
			count++;
	}
	return count;
}

//-------------------------COUNTING BLOOM FILTER-------------------------------------------

public static void countingFilter(int num_of_elements, int remove_element_count, int add_element_count, int num_of_bits, int num_of_hashes)
{
	int []A =computeRandomArray(num_of_elements);
	int []s =computeRandomArray(num_of_hashes);
	int []randomArray = computeRandomArray(add_element_count);

	insertCountingBloomFilter(A, s, num_of_bits);
	removeCountingBloomFilter(A,s,remove_element_count, num_of_bits);
	insertCountingBloomFilter(randomArray, s, num_of_bits);
	System.out.println("*******Counting Bloom Filter********");
	System.out.println(lookupCountingBloomFilter(A,s,num_of_bits));
}


public static void insertCountingBloomFilter(int[] elementArray, int[] s,int num_of_bits) 
{
	for(int element: elementArray)
	{
		for(int i=0;i<s.length;i++)
		{
			int refIndex = ((element ^ s[i]) % num_of_bits);
			
			
			counting_bloom_filter_array[refIndex] ++;
			
		}
	}
}

public static void removeCountingBloomFilter(int[] elementArray, int[] s,int remove_element_count, int num_of_bits) 
{
	int element = 0;
	for(int i = 0; i < remove_element_count;i++)
	{
		element = elementArray[i];
		
		for(int j=0;j< s.length ;j++)
		{
			int refIndex = ((element ^ s[j]) % num_of_bits);
			
	
			counting_bloom_filter_array[refIndex]--;
			
		}
	}
}

public static int lookupCountingBloomFilter(int []lookupArray, int []s, int num_of_bits) 
{
	int count = 0;
	boolean element_present = true;
	for(int element: lookupArray)
	{
		element_present = true;
		for(int i=0;i<s.length;i++)
		{
			int refIndex = ((element ^ s[i]) % num_of_bits);
			
			if(counting_bloom_filter_array[refIndex] ==0)
			{
				element_present = false;
				break;
				
			}
		}
		if (element_present) 
			count++;
	}
	return count;
}


//-------------------------CODED BLOOM FILTER-------------------------------------------

public static void codedBloomFilter(int num_sets, int num_elements, int num_filters, int num_bits, int num_hashes)
{
	//Set array [0:999] is set 1, [1000:1999] is set 2, [2000:2999] is set 3 and so on
	int []setArray = computeRandomArray(num_elements * num_sets);
	int []s = computeRandomArray(num_hashes);
	insertCodedBloomFilter(setArray,s,num_bits);
	System.out.println("*******Coded Bloom Filter********");
	System.out.println(lookupCodedBloomFilter(setArray, s, num_bits));
	
	}


public static void insertCodedBloomFilter(int[] setArray, int[] s, int num_of_bits) 
{
	int setNo = 0;
	String binaryFilterCode = "";
	for(int i=0; i < setArray.length;i++)
	{
		setNo = ((int)i/1000) + 1;
		binaryFilterCode = Integer.toBinaryString(setNo );
		if(binaryFilterCode.length() == 1 )
			binaryFilterCode = "00" +  binaryFilterCode ;
		else if(binaryFilterCode.length() == 2 )
			binaryFilterCode = "0" +  binaryFilterCode ;
		//System.out.println(binaryFilterCode);
		
		if(binaryFilterCode.charAt(0)=='1')
		{
				for(int k=0;k<s.length;k++)
				{
					int refIndex = ((setArray[i] ^ s[k]) % num_of_bits);
	
					coded_bloom_filter_1[refIndex] =1;				
				}
			
		}
		if(binaryFilterCode.charAt(1)=='1')
		{
				for(int k=0;k<s.length;k++)
				{
					int refIndex = ((setArray[i] ^ s[k]) % num_of_bits);
					coded_bloom_filter_2[refIndex] =1;				
				}
			
		}
		
		if(binaryFilterCode.charAt(2)=='1')
		{
				for(int k=0;k<s.length;k++)
				{
					int refIndex = ((setArray[i] ^ s[k]) % num_of_bits);
					coded_bloom_filter_3[refIndex] =1;				
				}
			
		}
		
		
	}
	
//
}




public static int lookupCodedBloomFilter(int[] setArray, int[] s, int num_of_bits) 
{
	int count=0;
	int actual_setnum = 0;
	int derived_setnum=0;
	String binaryFilterCode = "";
	boolean present_in_filter1 = false,present_in_filter2 = false, present_in_filter3= false;
	for(int i=0; i < setArray.length;i++)
	{
		binaryFilterCode="";
		actual_setnum = ((int)i/1000) + 1;
		present_in_filter1 = lookupElement(coded_bloom_filter_1, s, setArray[i]);
		present_in_filter2 = lookupElement(coded_bloom_filter_2, s, setArray[i]);
		present_in_filter3 = lookupElement(coded_bloom_filter_3, s, setArray[i]);
		
		if(present_in_filter1)
			binaryFilterCode= "1";
		else
			binaryFilterCode= "0";
		if(present_in_filter2)
			binaryFilterCode=   binaryFilterCode + "1" ;
		else
			binaryFilterCode=   binaryFilterCode + "0" ;
		if(present_in_filter3)
			binaryFilterCode=  binaryFilterCode + "1";
		else
			binaryFilterCode=   binaryFilterCode + "0" ;
		
		derived_setnum = Integer.parseInt(binaryFilterCode, 2);
		if(derived_setnum == actual_setnum)
			count++;
		
	}
	
	return count;
	

	
}


public static boolean lookupElement(int[] filter,int[] s, int element)
{
	boolean is_present = true;
	
		for(int i=0;i<s.length;i++)
		{
			int refIndex = ((element ^ s[i]) % filter.length);
			
			if(filter[refIndex] ==0)
			{
				is_present = false;
				break;
				
			}
		}
		
	
	
	return is_present;
	
	
	}

}
