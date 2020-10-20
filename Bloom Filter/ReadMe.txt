Folder Structure
----------------
Submitted Zip file contains 5 files
	1.	Bloomfilter.java 
		Source code for all three bloom filter types: Regular Bloom filter, Counting Bloom filter and Coded bloom filter.
		This file contains 4 major functions.
		a.	Main() - Main program function which sets the input parameters and calls the other member functions
		
		b.	bloomFilter() - This function takes the inputs from the main function, initializes the arrays A,B and hash function array S. Next, it calls the member functions to insert elements of A into the bloom filter and display the elements of A and B in the bloom filter.
		
		c.	countingFilter() - This function takes the inputs from the main function, initializes the array A and hash function array S. It calls the series of functions as mentioned in the project requirements to insert elements of A into the bloom filter, remove 500 elements from the bloom filter, add another random 500 elements elements from the bloom filter and lookup the original elements of array A in the bloom filter. 
		
		d.	codedBloomFilter() - After receiving the input parameters from the main function, this function redirects the inputs as follows:
			1.	It initializes input set array and hashed value array for the bloom filter
			2.	It then calls the insert function to insert elements of the set array into the bloom filter
			3.	Finally, it calls the lookup function to lookup each element in the sets in the bloom filter to reconstruct the original set number. 	This set number is evaluated against the actual set number of the element to obtain the output.
		

								
	2.	Output_Bloom_Filter.txt	-	Output file containing the Demo results of regular bloom filter implementation. First line of the output contains the elements of A found in the bloom filter. Second line of the output file indicates the count of elements of B found in the bloom filter.
	
	3.	Output_Counting_Bloom_Filter.txt-	Output file containing the Demo results of Counting bloom filter implementation. The output signifies the number of elements of array A found in the bloom filter.
	
	4.	Output_Coded_Bloom_Filter.txt	-	Output file containing the Demo results of Coded bloom filter implementation. The result signifies the number of elements whose lookup results were correct.
									
		Note : First line in output file contains the number of flows in the hash table and is followed by the flow Ids in the subsequent lines. Entries in hash table where flow Ids are not present is represented by 0.
	
	5. ReadMe.txt