package exercises.treeandgraph;

import java.util.Stack;

/**
 * Given a sorted dictionary (array of words) of an alien language, find order of characters in the
 * language. <br>
 * </>Examples:
 *
 * <p>Input: words[] = {"baa", "abcd", "abca", "cab", "cad"} Output: Order of characters is 'b',
 * 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd",
 * therefore 'b' is before 'a' in output. Similarly we can find other orders.
 *
 * <p>Input: words[] = {"caa", "aaa", "aab"} Output: Order of characters is 'c', 'a', 'b'
 */
public class AlienDictionary {

    public static void main(String[] args) {
        String[] words_1 = {"caa", "aaa", "aab"};
        alienDictionary(words_1);

        System.out.println("==========");

        String[] words_2 = {"baa", "abcd", "abca", "cab", "cad"};
        alienDictionary(words_2);
    }

    private static void alienDictionary(String[] words) {
        // first, create graph containing letters and relationships between them
        Graph<String> graphOfLetters = new Graph<>();

        for (int i = 0; i < words.length - 1; i++) {
            String word_1 = words[i];
            String word_2 = words[i + 1];

            for (int j = 0; j < Math.min(word_1.length(), word_2.length()); j++) {
                char char_1 = word_1.charAt(j);
                char char_2 = word_2.charAt(j);
                if (char_1 != char_2) {
                    // add an edge to the graph
                    graphOfLetters.addNode("" + char_1);
                    graphOfLetters.addNode("" + char_2);
                    graphOfLetters.addEdge("" + char_1, "" + char_2);
                    // continue with next pair of words
                    break;
                }
            }
        }

        // now figure out the alphabet
        Stack<String> alphabet = new Stack<>();
        for (Graph.Node<String> node : graphOfLetters.getNodes()) {
            if (node.getStatus() == Graph.Node.Status.VISITED) continue;
            visitNode(node, alphabet);
        }

        // print alphabet
        while (!alphabet.isEmpty()) {
            String letter = alphabet.pop();
            System.out.print(letter + " ");
        }
        System.out.println("");
    }

    private static void visitNode(Graph.Node<String> node, Stack<String> alphabet) {
        if (node.getStatus() == Graph.Node.Status.VISITING)
            throw new RuntimeException("invalid alphabet!"); // cycle detected, so given words do not
        // produce a valid alphabet

        if (node.getStatus() != Graph.Node.Status.VISITED) {
            node.setStatus(Graph.Node.Status.VISITING);

            for (Graph.Node<String> child : node.getChildren()) {
                visitNode(child, alphabet);
            }

            node.setStatus(Graph.Node.Status.VISITED);
            alphabet.push(node.getValue());
        }
    }
}
