package com.bptn.course;
import java.util.Scanner;

import java.util.ArrayList; // import ArrayList class
import java.util.HashSet; // import HashSet class
import java.util.List; // import List interface
import java.util.Set; // import Set interface

public class Test {
	

	    public static void main(String[] args) {
	        
	        // Create a list of phone numbers
	        List<String> phoneNumbers = new ArrayList<>();
	        phoneNumbers.add("555-555-1234");
	        phoneNumbers.add("555-555-2345");
	        phoneNumbers.add("555-555-3456");
	        phoneNumbers.add("444-444-1234");
	        phoneNumbers.add("333-333-1234");

	        // Create a set to hold the unique prefixes
	        Set<String> prefixes = new HashSet<>();
	       

	        // Add the prefix of each phone number to the set using a loop
	            
	        for(String phoneNumber: phoneNumbers){
	          String prefix = phoneNumber.substring(0,7);
	          prefixes.add(prefix);
	        }
	        // Print the unique prefixes
	        System.out.println("Unique prefixes: " + prefixes);
	    }
	

	//I created an Arraylist of phone numbers and looped through it to extract
	//thee prefix and add to a prefix hash set
	//NB: one test does not pass and i'm not sure why because the logic is correct.

}
