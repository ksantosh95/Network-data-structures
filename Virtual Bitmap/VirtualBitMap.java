import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.FileWriter;


import javafx.util.Pair;




public class VirtualBitMap {
	
	static int []bitmap = new int[500000];
	
	public static int getRandomNumber() 
	{
		Random r = new Random(); 
		return r.nextInt();
	}
	
	private static BufferedReader br;
	
	public static void main(String[] args) throws IOException
	{
		File file = new File("project4input.txt");
		br = new BufferedReader(new FileReader(file)); 
		
		//COUNT MIN DEMO Parameters
		int num_of_flows = Integer.parseInt(br.readLine());
		int m = 500000;
		int l = 500;
		
		//Input Array read from the file
		String[][] inputArray = new String[num_of_flows][2];
		
		int i=0;
		String st; 
		while ((st = br.readLine()) != null) 
		{
			String columns[] = st.split("\\s+");
			inputArray[i][0] = columns[0];
			inputArray[i][1] = columns[1];
			i++;
		}
		
		
		int[] r = computeRandomArray(l); 
		insertFlows(inputArray, r, m, l);
		double[][] result = computeEstimates(inputArray, r ,m ,l);
		
		
		try {
		      FileWriter myWriter = new FileWriter("output.txt");
		      PrintWriter pWriter = new PrintWriter(myWriter);
		      for(i = 0; i < result.length; i++)
		      {
		    	  pWriter.printf("\n %f \t %f" , result[i][0] , result[i][1]);
		      }
		     
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
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
	
	public static void insertFlows(String[][] inputArray, int[] r , int m, int l) 
	{
		for (int i = 0 ; i < inputArray.length ; i++)
		{
			for ( int j = 0 ; j < Integer.parseInt(inputArray[i][1]) ; j++ )
			{
				int e = Math.abs(getRandomNumber());
				bitmap[Math.abs(((inputArray[i][0].hashCode()) ^ (r[Math.abs( (Integer.toString(e)).hashCode() % l)])) % m)] = 1;
			}
		}
	}
	
	public static double[][] computeEstimates(String[][] inputArray, int[] r , int m, int l) 
	{
		
		int bitmapZero = computeZero(bitmap);
		int flowZero = 0;
		double[][] result= new double[inputArray.length][2];
		for (int i = 0 ; i < inputArray.length ; i++)
		{
			flowZero = 0;
			for (int j = 0; j < r.length ; j++)
			{
				
				result[i][0] = Integer.parseInt( inputArray[i][1]);
				if(bitmap[Math.abs((inputArray[i][0].hashCode() ^ (r[j])) % m)] == 0)
					flowZero++;
			}
		
			double Vb = (double)bitmapZero / m;
			double Vf = (double)flowZero / l;
			result[i][1] =  Math.abs(((l * Math.log(Vb)) - (l * Math.log(Vf)))); 
			System.out.println("Actual Spread\t" +result[i][0] + "\tEstimated spread\t" + result[i][1] + "\tVb="+ Vb + "\tVf = " + Vf);
		}
		
		return result;
	}
	
	public static int computeZero(int[] arr)
	{
		int numZeroes = 0;
		for(int i = 0; i < arr.length; i++)
			if(arr[i] == 0)
				numZeroes++;
		
		return numZeroes;
	}
	
}
