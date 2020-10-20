package Hashtables;

import java.util.Arrays;
import java.util.Random;

public class Hashtable {
	
	//Table entries =1000
	public static int[] multiHashTable = new int[1000];
	public static int multiHashTableCount=0;
	public static int[] cuckooHashTable = new int[1000];
	public static int cuckooHashTableCount=0;
	public static int[] dLeftHashTable = new int[1000];
	public static int dLeftHashTableCount=0;

	public static void main(String[] args) {
		
		//Demo Input parameters
		int numFlows = 1000;
		int numHashes = 3;
		int cuckooSteps = 2;
		int numSegments = 4;
		
		computeMultiHashTable(numFlows,numHashes);
		//computeCuckooHashTable(numFlows,numHashes, cuckooSteps );
		//computeDLeftHashTable(numFlows,numSegments);
		
		
	}
	
	
	public static void computeMultiHashTable(int numFlows, int numHashes)
	{
		int[] flows = new int[numFlows];
		int k=numHashes;
		int []s = new int[numHashes];
		
		Random rand = new Random();
		for(int i=0; i<flows.length;i++)
		{
			if(i<numHashes)
			{
				s[i] = Math.abs(rand.nextInt());
			}
			
			flows[i] = Math.abs(rand.nextInt());
		}
		
		
		for(int flow : flows)
		{
			MultiHashTable(flow, k, s);
			
		}
		//System.out.println("----------------------------------MultiHash-----------------------------");
		System.out.println(multiHashTableCount);
		{
		for(int entry:multiHashTable) { System.out.println(entry); }

		}
	}
	
	
	

	public static void MultiHashTable(int flow, int k, int[] s) 
	{
		for(int i=0;i<k;i++)
		{
			int refIndex = ((flow ^ s[i]) % 1000);
			
			if(multiHashTable[refIndex] ==0)
			{
				multiHashTable[refIndex] = flow;
				multiHashTableCount++;
				break;
			}
		}
	}
	
	
	
	
	
	
	public static void computeCuckooHashTable(int numFlows, int numHashes, int cuckooSteps)
	{
		int[] flows = new int[numFlows];
		int k=numHashes;
		int []s = new int[numHashes];
		
		Random rand = new Random();
		for(int i=0; i<flows.length;i++)
		{
			if(i<numHashes)
			{
				s[i] = Math.abs(rand.nextInt());
			}
			
			flows[i] = Math.abs(rand.nextInt());
		}
		
		
		for(int flow : flows)
		{
			CuckooHashTable(flow, k, s, cuckooSteps);
			
		}
		//System.out.println("----------------------------------Cuckoo----------------------------");
		System.out.println(cuckooHashTableCount);
		{
		for(int entry:cuckooHashTable) { System.out.println(entry); }

		}
	}
	
	
	
	
	
	
	
	
	public static boolean CuckooHashTable(int flow,int k,int[] s, int cuckooSteps)
	{
		

		for(int i=0;i<k;i++)
		{
			int Index = ((flow ^ s[i]) % 1000);
			
			if(cuckooHashTable[Index]==0)
			{
				cuckooHashTable[Index] = flow;
				cuckooHashTableCount++;
				return true;
			}
		}
		
		
			for(int i=0;i<k;i++)
			{
				int index = ((flow ^ s[i]) % 1000);
				if(cuckooMove(index,k,s,cuckooSteps))
				{
					cuckooHashTable[index] = flow;
					cuckooHashTableCount++;
					return true;
				}
			}
		
			
		
		
		return false;
	}
	
	public static boolean cuckooMove(int index,int k,int[]s, int cuckooSteps)
	{
		if(cuckooSteps <= 0) return false;
		
		int flow = cuckooHashTable[index];
		for(int i=0;i<k;i++)
		{
			int newIndex = ((flow ^ s[i]) % 1000);
			if(newIndex != index && cuckooHashTable[index]==0)
			{
				cuckooHashTable[newIndex] = flow;
				cuckooHashTable[index] = 0;
				
				return true;
			}
		}
		
		for(int i=0; i < k;i++)
		{
			int newIndex = ((flow ^ s[i]) % 1000);
			if(newIndex != index && cuckooMove(newIndex,k,s,cuckooSteps-1))
			{
				cuckooHashTable[newIndex] = flow;
				cuckooHashTable[index] = 0;
				
				return true;
			}
		}
		
		
		
		return false;
	}
	
	
	
	
	
	
	
	
	

	public static void computeDLeftHashTable(int numFlows, int numSegments)
	{
		int[] flows = new int[numFlows];

		int []s = new int[numSegments];
		
		Random rand = new Random();
		for(int i=0; i<flows.length;i++)
		{
			if(i<numSegments)
			{
				s[i] = Math.abs(rand.nextInt());
			}
			
			flows[i] = Math.abs(rand.nextInt());
		}
		
		
		for(int flow : flows)
		{
			DLeftHashTable(flow, s, numSegments);
			
		}
		//System.out.println("----------------------------------DLeft------------------------------");
		System.out.println(dLeftHashTableCount);
		
		for(int entry:dLeftHashTable) { System.out.println(entry); }

		
	}
	
	
	
	
	
	
	
	public static void DLeftHashTable(int flow, int[]s, int numSegments)
	{
		
		int[] indexArray = new int[numSegments];

		for(int i=0;i<numSegments;i++)
		{
			int Index = ((flow ^ s[i]) % 1000);
			indexArray[i]=Index;
			

		}
		
		Arrays.sort(indexArray);
		
		for(int j:indexArray)
		{
			
			if(dLeftHashTable[j]==0)
			{
				dLeftHashTable[j]=flow;
				dLeftHashTableCount++;
				break;
			}
		}
			
			
		
		
		
		
		
		
	}
	
	
}
