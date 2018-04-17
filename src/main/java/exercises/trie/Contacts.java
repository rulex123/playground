
package exercises.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * We're going to make our own Contacts application! The application must perform two types of operations:
 * <code>add name</code>, where name is a string denoting a contact name. This must store name as a new contact in the
 * application. <code>find partial</code>, where partial is a string denoting a partial name to search the application
 * for. It must count the number of contacts starting with partial and print the count on a new line. Given sequential
 * add and find operations, perform each operation in order. <br>
 * Input Format: the first line contains a single integer, n , denoting the number of operations to perform. Each line i
 * of the subsequent n lines contains an operation in one of the two forms defined above.
 *
 * @author emanno
 * @version 1.0 Jan 6, 2018
 */
public class Contacts {

	public static void main ( String[] args ) {
		Trie contactsBook = new Trie();
		List<Integer> results = new ArrayList<>();
		Scanner in = new Scanner( System.in );
		int n = in.nextInt();
		for ( int i = 0; i < n; i++ ) {
			String op = in.next();
			String contact = in.next();
			if ( op.equals( "add" ) )
				contactsBook.add( contact );
			else
				results.add( contactsBook.find( contact ) );
		}
		in.close();
		results.forEach( r -> System.out.println( "" + r ) );
	}

	private static class Trie {
		private final Node root = new Node();


		public void add ( String contact ) {
			Node curr = this.root;
			for ( int i = 0; i < contact.length(); i++ ) {
				// insert new node if necessary
				curr.children.putIfAbsent( contact.charAt( i ), new Node() );
				// move to next node in trie
				curr = curr.children.get( contact.charAt( i ) );
				// increment number of visits at node
				curr.noOfVisits++;
			}
		}


		public int find ( String prefix ) {
			Node curr = this.root;
			for ( char c : prefix.toCharArray() ) {
				if ( curr.children.containsKey( c ) ) {
					curr = curr.children.get( c );
				}
				else {
					// trie doesn't contain the whole prefix
					return 0;
				}
			}

			// now curr points to the node representing the last character in the prefix.
			// get children contacts
			return curr.noOfVisits;
		}
	}

	private static class Node {
		public final Map<Character, Node> children;
		int noOfVisits;


		public Node () {
			this.children = new HashMap<>();
		}
	}

}
