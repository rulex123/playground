package interviewcake.treeandgraph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * You wrote a trendy new messaging app, MeshMessage, to get around flaky cell phone coverage.
 *
 * Instead of routing texts through cell towers, your app sends messages via the phones of nearby
 * users, passing each message along from one phone to the next until it reaches the intended
 * recipient. (Don't worry - the messages are encrypted while they're in transit.)
 *
 * Some friends have been using your service, and they're complaining that it takes a long time for
 * messages to get delivered. After some preliminary debugging, you suspect messages might not be
 * taking the most direct route from the sender to the recipient.
 *
 * Given information about active users on the network, find the shortest route for a message from
 * one user (the sender) to another (the recipient). Return an array of users that make up this
 * route.
 *
 * There might be a few shortest delivery routes, all with the same length. For now, let's just
 * return any shortest route.
 *
 * Your network information takes the form of a hash map mapping username strings to an array of
 * other users nearby.
 */
public class MeshMessage {

  public static void main(String[] args) {
    Map<String, String[]> network = createNetwork();
    String[] path = meshMessage(network, "d", "c");
    System.out.println(Arrays.toString(path)); // expected [d, a, c]
  }

  static String[] meshMessage(Map<String, String[]> network, String sender, String receiver) {
    if (network == null || network.size() == 0) {
      throw new IllegalArgumentException("invalid network");
    }
    if (!network.containsKey(sender) || !network.containsKey(receiver)) {
      throw new IllegalArgumentException("sender/receiver not in network");
    }

    Queue<String> toVisit = new LinkedList<>();
    toVisit.offer(sender);

    // keep track of how we got to each node
    // we'll use this to reconstruct the shortest path at the end
    // we'll ALSO use this to keep track of which nodes we've
    // already visited
    Map<String, String> howWeReachedNodes = new HashMap<>();
    howWeReachedNodes.put(sender, null);

    while (!toVisit.isEmpty()) {
      String currentNode = toVisit.poll();

      // have we reached the receiver?
      if (currentNode.equals(receiver)) {
        return reconstructPath(howWeReachedNodes, sender, receiver);
      }

      for (String neighbor : network.get(currentNode)) {
        if (!howWeReachedNodes.containsKey(neighbor)) { // check if already visited
          toVisit.add(neighbor);
          howWeReachedNodes.put(neighbor, currentNode);
        }
      }
    }

    // if we get here, then we never found the end node
    // so there's NO path from sender to receiver
    return null;
  }

  private static String[] reconstructPath(Map<String, String> howWeReachedNodes,
                                          String sender, String receiver) {
    Stack<String> pathStacked = new Stack<>();
    pathStacked.push(receiver);

    String tmp = receiver;
    while (tmp != sender) {
      pathStacked.push(howWeReachedNodes.get(tmp));
      tmp = howWeReachedNodes.get(tmp);
    }

    String[] path = new String[pathStacked.size()];
    for (int i = 0; i < path.length; i++) {
      path[i] = pathStacked.pop();
    }
    return path;
  }

  static Map<String, String[]> createNetwork() {
    Map<String, String[]> network = new HashMap<>();
    network.put("a", new String[]{ "b", "c", "d" });
    network.put("b", new String[]{ "a", "d" });
    network.put("c", new String[]{ "a", "e" });
    network.put("d", new String[]{ "a", "b" });
    network.put("e", new String[]{ "c" });
    network.put("f", new String[]{ "g" });
    network.put("g", new String[]{ "f" });
    return network;
  }
}