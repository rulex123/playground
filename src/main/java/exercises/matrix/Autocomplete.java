
package exercises.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write an autocomplete class that returns all dictionary words with a given prefix.
 *
 * @author emanno
 * @version 1.0 Aug 18, 2017
 */
public class Autocomplete {

	private final Node trie = new Node( "" );


	public Autocomplete ( List<String> dictionary ) {
		// build trie data structure for the given dictionary
		for ( String word : dictionary ) {
			Node curr = this.trie;
			for ( int i = 0; i < word.length(); i++ ) {

				// insert new node if necessary
				if ( !curr.children.containsKey( word.charAt( i ) ) ) {
					curr.children.put( word.charAt( i ), new Node( word.substring( 0, i + 1 ) ) );
				}

				// move to next node in trie
				curr = curr.children.get( word.charAt( i ) );

				// if it's the end of word, mark node appropriately
				if ( i == word.length() - 1 ) {
					curr.isWord = true;
				}
			}
		}
	}


	public List<String> findWordsStartingWithPrefix ( String prefix ) {
		List<String> words = new ArrayList<>();
		Node curr = this.trie;
		for ( char c : prefix.toCharArray() ) {
			if ( curr.children.containsKey( c ) ) {
				curr = curr.children.get( c );
			}
			else {
				// trie doesn't contain the whole prefix
				return words;
			}
		}

		// now curr points to the node representing the last character in the prefix.
		// get children words
		this.findChildrenWords( curr, words );
		return words;
	}


	private void findChildrenWords ( Node node, List<String> words ) {
		if ( node.isWord )
			words.add( node.prefix );

		for ( char c : node.children.keySet() ) {
			this.findChildrenWords( node.children.get( c ), words );
		}
	}

	private static class Node {
		public final String prefix;
		public final Map<Character, Node> children;
		public boolean isWord;


		public Node ( String prefix ) {
			this.prefix = prefix;
			this.children = new HashMap<>();
		}
	}


	public static void main ( String[] args ) {
		List<String> dict = Arrays.asList( "abe", "abba", "abarth", "bubble", "bumblebee", "cat", "cats" );
		Autocomplete autocomplete = new Autocomplete( dict );
		System.out.println( autocomplete.findWordsStartingWithPrefix( "ab" ) );
		System.out.println( autocomplete.findWordsStartingWithPrefix( "ca" ) );
		System.out.println( autocomplete.findWordsStartingWithPrefix( "ac" ) );
	}

}
