Folder Structure
----------------
Submitted Zip file contains 9 files
	1.	Main.java 
		This file contains the main function for the program which routes the program flow. It initializes the parameters and calls the member functions.
		a.	runCountMin() - This function sends the input parameters received from the main program to the CountMin.java file and displays the output to the screen.
		
		b.	runCounterSketch() - This function takes the inputs from the main function for counter sketch operations and sends the parameters to CountSketch.java and prints the average error to the screen.
		
		c.	runActiveCounter() - This function takes the input number for active counter from the main function and initiates ActiveCounter.java and prints the final value in the counter to the screen. 
								
	2.	CountMin.java	-	Once this file receives the input parameters from the main file, it computes the hashes for the flows and increments the size. Once all the flows are entered, the lookup function returns the minimum sizes of the flow ids from the counter array.

	3.	CounterSketch.java	-	Once this file receives the input parameters from the main file, it computes the hashes for the flows and increments or decrements the size at the index depending upon the most significant bit. Once all the flows are entered, the lookup function returns median size of the flow ids from the counter array.	
	
	4.	ActiveCounter.java	-	This file computes the 32 bit active counter and returns the value of the counter after the computation.
	
	5.	Output_Count_Min.txt	-	Output file containing the Demo results of Count Min implementation. The first line in this file represents the average error among all flows. The next 100 lines represent the flowid, estimated size and true size for the flows in the decreasing order of their estimated values.
	
	6.	Output_Counter_Sketch.txt	-	Output file containing the Demo results of Counter Sketch implementation. The first line in this file represents the average error among all flows. The next 100 lines represent the flowid, estimated size and true size for the flows in the decreasing order of their estimated values.
	
	7.	Output_Active_Counter.txt	-	Output file containing the Demo results of Active Counter implementation for 1,000,000. This file contains one line representing the counter value at the end of the computation.
	
	8.	project3input.txt	-	Input file for demo implementation of countmin and countsketch algorithms.
	
	9. 	ReadMe.txt