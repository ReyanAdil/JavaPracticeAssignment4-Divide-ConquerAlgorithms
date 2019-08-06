package com.company;

import java.util.Random;
import java.util.*;
import java.io.*;

/**
 * 
 * @author reyanadil
 * The SolutionA class contains the answer code to QuestionA of Algorithms Assignment
 * To find the first number equal to it's index in an array 
 * Following methods are included in this class apart from main(String[]):
 * int findFirst(int[], int, int, int)
 * int[] input(BufferedReader)
 * int[] random()
 */
public class SolutionA {
	public static void main(String[] args) throws Exception{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader scan = new BufferedReader(isr);
		
		//comment the below line if using random() for AUTOMATIC testing. Uncomment for manual input
		int arr[] = input(scan);
				
		/* This random() command below if uncommented creates a distinct sorted array of random 'length' between 5 - 30 for quick testing
		 * The elements of this random array are also random but Ascending and Distinct
		 * The method also prints the generated elements for reference, it's self sufficient
		 * All you have to do is run the code to test its functionality
		 * Make sure you comment the code for manual input of array if using random();
		 */
		
		//int arr[] = random();
		
		/*calling findFirst() to find the first number in array equal to its index
		 * it returns 0 if no elements found and the index of the number if found
		 */
		int number = findFirst(arr, 1, arr.length - 1, 0);

		if(number != 0) {
			System.out.println(number);
		}else System.out.println("NOT_FOUND");
		
		scan.close();
	}
	
	/**FINDFIRST() 
	 * 
	 * Time Complexity O(logn)
	 * Custom algorithm to search the first number same as index
	 * It is derived from Binary Search Algorithm but runs through the entire left or right hand side
	 * hence maintains the time complexity of O(logn) for all best, average and worst case scenarios
	 * @param arr 	: distinct and ascending array to process
	 * @param start : start index of the array
	 * @param end 	: end index of the array
	 * @param num 	: is passed as 0 by default, num represents the number same as its index. The algorithm keeps updating the
	 * 		  		  value of num for each recursion if num == its own index and keeps passing the new num to its successive recursions
	 * 		  		  value of 0 represents that no matching elements found
	 * @return when start becomes greater than end it returns updated num
	 */
	static int findFirst(int[] arr, int start, int end, int num) {
		if(start > end)
			return num; //returns final num at end of all recursions
		int mid = (start + end) >>> 1; //find mid
		if(mid <= arr[mid]) {
			if(mid == arr[mid])
				num = mid; //update num if condition holds, to be passed to next recursive call as an argument
			return findFirst(arr, start, mid - 1, num);
		}else return findFirst(arr, mid + 1, end, num);
	}
		
	/**
	 * INPUT()
	 * 
	 * TimeComplexity O(n)
	 * Input function to take input from user and return an
	 * array to arr[] of main() Declares, initialises and returns an array of length
	 * input from console
	 * 
	 * @return : returns the manually created array
	 */
	static int[] input(BufferedReader scan){
		
		int length;
		//Exception handling - NumberFormatException for variable 'length'
		int count = 0;
		while(true) {
			try {				
				length = 1 + Integer.parseInt(scan.readLine()); //input length of array and automatically increase by 1 because array starts from index 1
				//Exception handling - NegativeArraySizeException for variable 'arr[]'
				if(length < 2) {
					System.out.println("NOT_FOUND");
					System.exit(0);
				}
				break;
			}catch(Exception e) {	
			}	
		}
		int[] arr = new int[length];
		//initialising arr[]
		for(int i = 1; i < length; i++) {
			//Exception handling - NumberFormatException for variable 'arr[i]'
			while(true) {
				try {
					arr[i] = Integer.parseInt(scan.readLine());
					break;
				}catch(Exception e) {	
				}
			}
		}	
		return arr;
	}
	
	/**RANDOM()
	 * 
	 * TimeComplexity O(n)
	 * Function to create a random data set with random length so don't have to manually input values every time 
	 * Length is randomised between 5 to 30
	 * Elements are randomised between -8 to 3*length
	 * Elements generated are distinct
	 * Elements generated are in ascending order
	 * @return : Returns created random array
	 */
	static int[] random() {
		Random rand = new Random();
		int length = rand.nextInt(31) + 5; //random length between 5 and 30
		
		int[] arr = new int[length];
		
		//initialising arr[] with random ascending distinct elements
		for(int i = 1; i < length; i++) {
			if(i == 1)
				arr[i] = rand.nextInt(10) - 8;
			else
				arr[i] = arr[i - 1] + rand.nextInt(3) + 1;
		}
		//print random array
		for(int i = 1; i < arr.length; i++)
			System.out.print(arr[i] + "(" + i + "), ");
		System.out.println();
		return arr;
	}	
}
