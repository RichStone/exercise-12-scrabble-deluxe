package my;

import java.util.LinkedList;

public class WordBucket {
	
	private LinkedList<String> wordList;
	
	public WordBucket() {
		
		wordList = new LinkedList<String>();
		
	}
	
	public void insert(String word) {
		wordList.add(word);
	}
	
	public void printWords() {
		
		for(String word : wordList) {
			System.out.println(word);
		}
	}
	
	public int getSize() {
		return wordList.size();
	}
	
	public LinkedList<String> getList() {
		return wordList;
	}
}
