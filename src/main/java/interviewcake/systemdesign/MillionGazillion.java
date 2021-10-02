package interviewcake.systemdesign;

import java.util.HashMap;

/**
 * I'm making a search engine called MillionGazillion TM.
 * <p>
 * I wrote a crawler that visits web pages, stores a few keywords in a database, and follows links
 * to other web pages. I noticed that my crawler was wasting a lot of time visiting the same pages
 * over and over, so I made a hash set, visited, where I'm storing URLs I've already visited. Now
 * the crawler only visits a URL if it hasn't already been visited.
 * <p>
 * Thing is, the crawler is running on my old desktop computer in my parents' basement (where I
 * totally don't live anymore), and it keeps running out of memory because visited is getting so
 * huge.
 * <p>
 * How can I trim down the amount of space taken up by visited?
 */
public class MillionGazillion {

    public static void main(String[] args) {
        Trie visitedWebsites = new Trie();
        visitedWebsites.addWord("donut.net");
        visitedWebsites.addWord("dogood.org");
        visitedWebsites.addWord("dog.com");
        visitedWebsites.addWord("dog.com/about");
        visitedWebsites.addWord("dog.com/pug");
        visitedWebsites.addWord("dog.org");

        System.out.println(visitedWebsites.addWord("cat.com")); // new website
        System.out.println(visitedWebsites.addWord("dog.com/about")); // visited website
        System.out.println(visitedWebsites.addWord("dog.com/husky")); // new website
    }

    static class TrieNode {

        private HashMap<Character, TrieNode> nodeChildren;

        public TrieNode() {
            this.nodeChildren = new HashMap<>();
        }

        public boolean hasChildNode(char character) {
            return this.nodeChildren.containsKey(character);
        }

        public void makeChildNode(char character) {
            this.nodeChildren.put(character, new TrieNode());
        }

        public TrieNode getChildNode(char character) {
            return this.nodeChildren.get(character);
        }
    }

    static class Trie {

        private static final char END_OF_WORD_MARKER = '\0';
        private TrieNode rootNode;

        public Trie() {
            this.rootNode = new TrieNode();
        }

        public boolean addWord(String word) {

            TrieNode currentNode = this.rootNode;
            boolean isNewWord = false;

            // Work downwards through the trie, adding nodes
            // as needed, and keeping track of whether we add
            // any nodes.
            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);

                if (!currentNode.hasChildNode(character)) {
                    isNewWord = true;
                    currentNode.makeChildNode(character);
                }

                currentNode = currentNode.getChildNode(character);
            }

            // Explicitly mark the end of a word.
            // Otherwise, we might say a word is
            // present if it is a prefix of a different,
            // longer word that was added earlier.
            if (!currentNode.hasChildNode(END_OF_WORD_MARKER)) {
                isNewWord = true;
                currentNode.makeChildNode(END_OF_WORD_MARKER);
            }

            return isNewWord;
        }
    }
}

//Complexity
//    How much space does this approach save?
//
//    How many characters were we storing in our original approach (i.e set)? Suppose visited
//    includes all possible URLs of length 5 or fewer characters.Let's ignore non-alphabetical
//    characters to simplify, sticking to the standard 26 English letters in lowercase. There are
//    26^5 different possible 5-character URLs (26 options for the first character, times 26 options
//    for the 2nd character,etc),and of course 26^4 different possible 4-character URLs,etc. If we
//    store each 5-character URL as a normal string in memory, we are storing 5 characters per
//    string, for a total of 5*26^5 characters for all possible 5-character strings (and 4*26^4
//    total characters for all 4-character strings, etc).So for all 1,2,3,4,or 5 character URLs,
//    our total number of characters stored is:
//
//    5*26^5 + 4*26^4 + 3*26^3 + 2*26^2 + 1*26^1
//
//    So for all possible URLs of length n or fewer, our total storage space is:
//
//    n26^n + (n-1)26^(n-1) + ... + 1*26^1
//
//    This is O(n26^n).
//
//    How many characters are stored in our trie? The first layer has 26 nodes (and thus
//    26 characters),one for each possible starting character. On the second layer, each of those
//    26 nodes has 26 children, for a total of 26^2 nodes. The fifth layer has 26^5
//    nodes. To store all 1,2,3,4,or 5 character URLs our trie will have 5 layers. So the total
//    number of nodes is:
//
//    26^5 + 26^4 + 26^3 + 26^2 + 26^1
//
//    So for all URLs of length n or fewer, we have:
//
//    26^n + 26^(n-1) + ... + 26^1
//
//    This is O(26^n). We've shaved off a factor of n.