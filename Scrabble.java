package my;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Scrabble {
	
	private HashSet<String> normalizedPermutations = new HashSet<String>();
	
	private Dictionary dict_2_letter = new Dictionary("/Users/Rich/Google Drive/Studium/INFO 2/week-27/dict-2-letter-words.txt");
	private Dictionary dict_3_letter = new Dictionary("/Users/Rich/Google Drive/Studium/INFO 2/week-27/dict-3-letter-words.txt");
	private Dictionary dict_4_letter = new Dictionary("/Users/Rich/Google Drive/Studium/INFO 2/week-27/dict-4-letter-words.txt");
	private Dictionary dict_5_letter = new Dictionary("/Users/Rich/Google Drive/Studium/INFO 2/week-27/dict-5-letter-words.txt");
	private Dictionary dict_6_letter = new Dictionary("/Users/Rich/Google Drive/Studium/INFO 2/week-27/dict-6-letter-words.txt");
	private Dictionary dict_7_letter = new Dictionary("/Users/Rich/Google Drive/Studium/INFO 2/week-27/dict-7-letter-words.txt");
	
	public static void main(String[] args) throws IOException {
		Scrabble scrabble = new Scrabble();
	}
	
	public Scrabble() throws IOException {
		//generate a Random String to Scrabble
		String stringToScrabble = generateRandomString(7);
		System.out.println("Your random String is: " + stringToScrabble);
		scrabbleIt(stringToScrabble);
	}
	
	/**
	 * compare all your possible permutations with all your dictionaries
	 * @param stringToLookFor String which you want some useful words for
	 */
	public void scrabbleIt(String stringToLookFor) {
		//create all possible permutations of your String first
		createNormalizedPermutations("", stringToLookFor);
		System.out.println("Those are all the possible normalized permutations of your random String: ");
		System.out.println(normalizedPermutations.toString());
		
		//for every word in the set create a HashKey and look up in the right dictionary
		for(String string : normalizedPermutations) {
			int dictToCheck = string.length();
			switch(dictToCheck) {
			
			case 2: dict_2_letter.printPermutations(string);
					break;
			case 3: dict_3_letter.printPermutations(string);
					break;
			case 4: dict_4_letter.printPermutations(string);
					break;
			case 5: dict_5_letter.printPermutations(string);
					break;
			case 6: dict_6_letter.printPermutations(string);
					break;
			case 7: dict_7_letter.printPermutations(string);
					break;
			}
		}
	}
	
	public void createNormalizedPermutations(String prefix, String rest){
		
		   if(prefix.length() > 1){
			  //normalize prefix before adding it
			  String prefixToAdd = normalize(prefix);
			  
			  normalizedPermutations.add(prefixToAdd);
		   }
		  
		   // n needed to determine the duration of our recursion
		   int n = rest.length();
		  
		   //recursive structure to go through all the possibilities of a given word
		   for(int i=0; i < n; i++){
			   createNormalizedPermutations(prefix+rest.charAt(i), rest.substring(0,i) + rest.substring(i+1, n));
		   } 
	 }
	
	public HashSet<String> getNormalizedPermutationsSet() {
		return normalizedPermutations;
	}
	
	public static String generateRandomString(int numberOfLetters) {
		
		Random rnd = new Random();
		
		String randomString = "";

		for(int i = 0; i < numberOfLetters; i++) {
			randomString += (char)(rnd.nextInt(26) + 97);
		}
		
		return randomString;
	}
	
	public String normalize(String wordToNormalize) {
		wordToNormalize.toLowerCase();
		char[] chars = wordToNormalize.toCharArray();
		Arrays.sort(chars);
		
		String normalizedWord = new String (chars);
		
		return normalizedWord;
	}

}
