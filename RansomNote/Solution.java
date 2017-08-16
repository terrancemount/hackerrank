/***************************************************************************************
Author: Terrance Mount
Date: 8/15/2017
Purpose: solve the HackerRank.com problem for Ransom Note.
	URL https://www.hackerrank.com/challenges/ctci-ransom-note

Notes:
	uses a hash map to store the strings as keys and a custom class called MutableInt
	to keep track of the occurances of the keys.  MutableInt was used to effectively
	increment the count when finding the same key.  Hashing depends of the Java 8 HashMap
	function that uses chaining for identically hashed keys.
***************************************************************************************/
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//private class that keeps track of how many times a key was inputted into the hashmap
class MutableInt
{
    int value = 1; // note that we start at 1 since we're counting

    //simply increment the map
    public void increment ()
    {
        value++;
    }

    //get the count of the map
    public int  get ()
    {
        return value;
    }
}


//main class to run the solution (named solution becasue of hackerrank.com)
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        //create maps for the magazine and the ransom note
        Map<String, MutableInt> magazineMap = new HashMap<>();
        Map<String, MutableInt> ransomMap = new HashMap<>();

        String inputTemp;  //used to store input from user (keeps from allocating memory in two loops)
        MutableInt countTemp; //used to store return from get out of hashmap.


        //input magazine and store into hashmap
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            inputTemp = in.next(); //get input from user

            //get possible count from the hashmap
            countTemp = magazineMap.get(inputTemp);
            if(countTemp == null) //if not found then
            {
                //create a new map
                magazineMap.put(inputTemp, new MutableInt());
            }
            else
            {
                //increment map
                countTemp.increment();
            }
        }

        //input ransom not and store int hashmap
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            inputTemp = in.next();//get input from user

             //get possible count from the hashmap
            countTemp = ransomMap.get(inputTemp);
            if(countTemp == null)//if not found then
            {
                //create a new map
                ransomMap.put(inputTemp, new MutableInt());
            }
            else
            {
                //increment map
                countTemp.increment();
            }
        }

		//assume the comparsion will be true (a false will break out of loop)
        boolean isValid = true;

        //loop through each member of the hash map
        for(Map.Entry<String, MutableInt> entry : ransomMap.entrySet())
        {
            //get the possible count from the magazine for the given key == word
            countTemp = magazineMap.get(entry.getKey());

            //first check if countTemp is null, then if countTemp of magazine is greater then ransom note
            if(countTemp == null || countTemp.get() < entry.getValue().get())
            {
                //ether case the check fails
                isValid = false;
                //stop checking
                break;
            }
        }

        //simply print out wheather it was successful or not
        if(isValid)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}


