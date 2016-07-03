package my;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Dictionary {
	
	private ArrayList<String> startWordList = new ArrayList<String>();
	
	private WordBucket[] hashTable;
	
	public Dictionary() throws IOException {
		readWords();
		
		//now as we know the number of words we can initialize our WordBucket
		hashTable = new WordBucket[startWordList.size()];
		
		listToHashTable(startWordList);
		
		printDictionary();
	}
	
	/**
	 * read words from file into hashlist
	 * @throws IOException
	 */
	public void readWords() throws IOException {
		
		File fileWithWords = new File("/Users/Rich/Google Drive/Studium/INFO 2/week-26/dict.txt");
		Scanner input = new Scanner(fileWithWords);
			
		while(input.hasNext()) {
			String word = input.next();
			startWordList.add(word);
		}
	}
	
	public int stringHashFunction(String wordToHash) {
		
		int hashKeyValue = 7;
		
		//toLowerCase and sort letters in alphabetical order
		wordToHash = normalize(wordToHash);
		
		for(int i = 0; i < wordToHash.length(); i++) {
			
			int charCode = wordToHash.charAt(i) - 96;
			
			//calculate the hash key using the 26 letters
			hashKeyValue = (hashKeyValue * 26 + charCode) % hashTable.length;
		}
		
		return hashKeyValue;
	}
	
	public void listToHashTable(ArrayList<String> listOfWords) {
		
		for(String word : listOfWords) {
			
			WordBucket tempWordBucket = new WordBucket();
			
			int hashKey = stringHashFunction(word);
			
			if(hashTable[hashKey] == null) {
				hashTable[hashKey] = tempWordBucket;
			}

			hashTable[hashKey].insert(word);
		}
	}
	
	public void printDictionary() {
		for(int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] != null) {
				System.out.println("Bucket Number: " + i);
				hashTable[i].printWords();
				System.out.println("\n");
			}
		}
	}
	
	public String[] find(String wordToFind) {
		
		System.out.println("\nYou look for permutations of: " + wordToFind);
		
		int wordHashKey = stringHashFunction(wordToFind);
		
		int bucketSize = 0;
		String [] arrayOfPermutations = null;
		
		if(hashTable[wordHashKey] != null) {
			bucketSize = hashTable[wordHashKey].getSize();
			arrayOfPermutations = new String[bucketSize];
		}
		else {
			System.out.println("There are no words for your permutation!");
			return arrayOfPermutations;
		}
		
		
		
		
		System.out.println("The cheater found the following words: ");
		hashTable[wordHashKey].printWords();

		
		Iterator <String> it = hashTable[wordHashKey].getList().iterator();
		int index = 0;
		while(it.hasNext()) {
			String wordForArray = it.next();
			arrayOfPermutations[index] = wordForArray;
			index++;
		}
		
		return arrayOfPermutations;
	}
	
	public void printStatistics() {

		//number of buckets
		System.out.println("\nNumber of buckets: " + hashTable.length);
		
		//the biggest bucket
		//and empty buckets
		int biggest = 0;
		int emptyBuckets = 0;
		int allItems = hashTable.length;
		for(int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] == null) {
				emptyBuckets++;
				continue;
			}
			
			if(hashTable[i].getSize() > biggest) {
				biggest = hashTable[i].getSize();
			}
		}
		System.out.println("All items: " + allItems);
		double average = (double)((double)(allItems) / (hashTable.length - emptyBuckets));
		System.out.println("The biggest bucket counts: " + biggest + " items.");
		System.out.println("There are: " + emptyBuckets + " empty buckets");
		System.out.println("On average there are " + average + " per bucket.");
	}
	
	public String normalize(String wordToNormalize) {
		wordToNormalize.toLowerCase();
		char[] chars = wordToNormalize.toCharArray();
		Arrays.sort(chars);
		
		String normalizedWord = new String (chars);
		
		return normalizedWord;
	}
	
	
}