package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * 
 * @author reyanadil
 * The SolutionB class contains the answer code for Question B of Algorithms assignment
 * To search a key in an array of unknown length
 * This class is dependent on classes Utilities and Data
 * Following methods are included in this class apart from main(String[]):
 * int findBound(int[], int)
 * int search(int[], int, int, int)
 * Data input()
 * Data random()
 */
public class SolutionB {
	public static void main(String[] args) throws IOException{
		/**manual input of array and key*/
		Data data = input();
		
		/* This random() command below if uncommented creates a sorted array of random 'length'between 100 - 900 for quick testing
		 * The elements of this random array are also random but Ascending and Distinct
		 * It prints the created array for reference before asking for a key to search
		 * All you have to do is put the value of key to search for testing
		 * Make sure you comment the command for 'manual input of array and key'(above) if using random();
		 */
		//Data data = random();
		
		//localising variables so code can be understood easily/its redundant and can be bypassed if needed
		int[] arr = data.getArr();
		int key = data.getKey();
				
		//initiating upper bound search and then binary search inside findBound consecutively -
		//returns the index if found or 0 if not 
		int keyIndex = findBound(arr, key); 
		
		if(keyIndex == 0) {
			System.out.println("NOT_FOUND");
		}else System.out.println(keyIndex);	
	}
	
	/**FINDBOUND
	 * 
	 * Worst case Time Complexity O(logn)
	 * Finds the upperBound and lowerBound range where 'key' lies in an array of unknown length
	 * sends found range to search() to initiate a binary search
	 * @param arr : array to search
	 * @param key : key to find in the array
	 * @return : returns 0 if no match found and Index(as returned by Search()) if match found
	 */
	static int findBound(int[] arr, int key) {
		int lowPointer = 0; //set lowPointer in array to point to 0
		int highPointer = 1; //setHighPointer in array to point to 1
		boolean foundBound = false; //if upperbound found toggle to true, used to exit the while loop if upper bound was found
		while(!foundBound) {
			try {
				while(lowPointer != highPointer) {
					if(key > arr[highPointer]) {
						//if youre here means key is bigger and upperbound yet not found, saving old upper bound and doubling it to recheck
						lowPointer = highPointer;
						highPointer *= 2;
					}else if(key < arr[highPointer]) {
						//if you are here means upperbound found, time to 'break' from the inner while loop and also toggle the switch foundbound
						foundBound = true;
						break;
					}else {
						//if by sheer luck you are here means key is directly equal to highPointer, so just return the index
						return highPointer;
					}
				}
			}catch(Exception e) {
				//if you are here means upper bound exceeded array size, we will try again from the middle of upper and lower bound
				highPointer = lowPointer + (highPointer - lowPointer)/2;
			}
			//if you are here means that we have scanned the whole array and key was no where to be found, return 0
			if(lowPointer == highPointer)
				return 0;
		}
		
		//Congratulations!! if you are here means upperbound search was a success and we also know the range where key lies
		//we can now initiate a search and return whatever index search() finds for key
		return search(arr, lowPointer , highPointer, key);
	}
	
	/** SEARCH()
	 * 
	 * Time Complexity O(logn)
	 * Standard recursive binary search algorithm  with Worst case Time complexity O(logn)
	 * Returns 0 if the key is not found in the array
	 * @param arr : Array you want to search key in
	 * @param start : Start index of the array
	 * @param end : End index of the array
	 * @param key : Key to find in the array
	 * @return : returns 0 if no match found, and index(stored in mid) if match found
	 */
	static int search(int[] arr, int start, int end, int key) {
		if(start > end)
			return 0;
		int mid = start + (end - start)/2;
		if(key > arr[mid])
			return search(arr, mid + 1, end, key);
		else if(key < arr[mid])
			return search(arr, start, mid - 1, key);
		else return mid;
	}
		
	/**INPUT()
	 * 
	 * TimeComplexity O(n)
	 * Input function to take input from user and return an array to arr[] of main() 
	 * Declares and initialises an array of length input by user
	 * Declares and initialises key to be searched
	 * Packages both the arr[] and key in to an object of class Data to be returned together
	 * @return : returns the object of Data which contains both arr[] and key
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	static Data input() throws NumberFormatException, IOException{
		
		int length;
		//Exception handling - NumberFormatException for variable 'length'
		int count = 0;
		while(true) {
			try {
				length = 1 + Integer.parseInt(Utilities.input()); //input length of array and automatically increase by 1 because array starts from index 1
				//Exception handling - NegativeArraySizeException for variable 'arr[]'
				if(length < 2) {
					System.out.println("NOT_FOUND");
					System.exit(0);
				}
				break;
			}catch(Exception e) {
				count++;
				if(count == 3) {
					System.out.println("NOT_FOUND");
					System.exit(0);
				}
			}
		}
		
		int[] arr = new int[length];
		int key;
		
		//Exception handling - NumberFormatException for variable 'key'
		count = 0;
		while(true) {
			try {
				key = Integer.parseInt(Utilities.input()); //input key value to search
				break;
			}catch(Exception e){
				count++;
				if(count == 3) {
					System.out.println("NOT_FOUND");
					System.exit(0);
				}
			}
		}
		
		//manually initialising arr[]
		for(int i = 1; i < length; i++) {
			//Exception handling - NumberFormatException for arr[i]
			count = 0;
			while(true) {
				try {
					arr[i] = Integer.parseInt(Utilities.input());
					break;
				}catch(Exception e) {
					count++;
					if(count == 3) {
						System.out.println("NOT_FOUND");
						System.exit(0);
					}
				}
			}
		}	
		
		Data data = new Data(arr, key); //packaging both arr[] and key into a object of Data, ready to be returned together
		return data;
	}	
	
	/**RANDOM()
	 * 
	 * TimeComplexity O(n)
	 * function to create a random data set with random length so don't have to manually input values every time 
	 * length is randomised between 100 to 900
	 * elements are randomised between -20 to 20*length
	 * elements generated are distinct
	 * elements generated are in ascending order
	 * @return : returns the object of Data which contains both arr[] and key
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	static Data random() throws NumberFormatException, IOException{
		Random rand = new Random();
		int length = rand.nextInt(900) + 100; //random length 100 - 900
		int[] arr = new int[length];
		int key;
		
		//initialising arr[] with random sorted increasing elements
		for(int i = 1; i < length; i++) {
			if(i == 1)
				arr[i] = rand.nextInt(10) - 30;
			else
				arr[i] = arr[i - 1] + rand.nextInt(10) + 2;
		}
		
		//print random array
		for(int i = 1; i < arr.length; i++)
			System.out.print(arr[i] + "(" + i + "), ");
		System.out.println();
		
		//Exception handling - NumberFormatException for variable 'key'
		int count = 0;
		while(true) {
			try {
				key = Integer.parseInt(Utilities.input()); //input key value to search
				break;
			}catch(Exception e){
				count++;
				if(count == 3) {
					System.out.println("NOT_FOUND");
					System.exit(0);
				}
			}
		}
		
		Data data = new Data(arr, key); //packaging both arr[] and key, ready to be returned together
		
		return data;
	}
}

/**
 * 
 * @author reyanadil
 * This class packages two variable arr[] and key
 * The object of this class and created and returned wherever a necessity of returning two variables together is felt
 * Constructor of this class initialises the two variables
 */
class Data{
	private int key;
	private int[] arr;
	
	public Data(int[] arr, int key) {
		this.arr = arr;
		this.key = key;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public int[] getArr(){
		return this.arr;
	}
}

/**
 * 
 * @author reyanadil
 * This class contains general methods which can be used directly throughout the program
 * Static method input() can be used to input a String from console and return that String
 */
class Utilities{
	
	public static String input() throws NumberFormatException, IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader scan = new BufferedReader(isr);
		String input = scan.readLine();
		return input;
	}
}


