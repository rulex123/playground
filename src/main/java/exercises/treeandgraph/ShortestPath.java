package exercises.treeandgraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/** Given a directed graph, find the shortest path between two nodes if it exists **/
public class ShortestPath {

  public static void main(String[] args) {

    Graph<Integer> graph = new Graph<>();
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.addNode(4);
    graph.addNode(5);

    graph.addEdge(1,2);
    graph.addEdge(1,3);
    graph.addEdge(2,5);
    graph.addEdge(4,1);
    graph.addEdge(4,3);
    graph.addEdge(5,4);

    Stack<Graph.Node> shortestPath = shortestPath(graph.getNode(2), graph.getNode(3));
    while (!shortestPath.isEmpty()) {
      System.out.println(shortestPath.pop().getValue());
    }
  }

  private static Stack<Graph.Node> shortestPath(Graph.Node node1, Graph.Node node2) {
    if (node1 == null || node2 == null) {
      return null;
    }

    Map<Graph.Node, Graph.Node> parentNodeMap = new HashMap<>(); // map from node to parent node

    Queue<Graph.Node> nodesToVisit = new LinkedList<>();
    nodesToVisit.add(node1);
    parentNodeMap.put(node1, null);

    while (!nodesToVisit.isEmpty()) {
      Graph.Node currNode = nodesToVisit.poll();
      if (currNode == node2) {
        break; // found destination node
      }

      // enqueue all children nodes
      for (int i = 0; i < currNode.getChildren().size(); i++) {
        Graph.Node childNode = (Graph.Node) currNode.getChildren().get(i);
        if (!parentNodeMap.containsKey(childNode)) { // we only want the parent node from the
          // shortest path
          nodesToVisit.add(childNode);
          parentNodeMap.put(childNode, currNode);
        }
      }
    }

    if (parentNodeMap.get(node2) == null) {
      return null; // there is no path from node1 to node2
    }

    Stack<Graph.Node> path = new Stack<>();
    path.push(node2);

    Graph.Node parentNode = parentNodeMap.get(node2);
    while (parentNode != null) {
      path.push(parentNode);
      parentNode = parentNodeMap.get(parentNode);
    }
    return path;
  }

}
