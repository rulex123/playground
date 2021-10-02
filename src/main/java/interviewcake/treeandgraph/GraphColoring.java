package interviewcake.treeandgraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Given an undirected graph with maximum degree D, find a graph coloring using at most D+1 colors.
 * Terminology explained: graph coloring is when you assign colors to the nodes in a graph; a
 * legal coloring means no adjacent nodes have the same color. The degree of a node is the number of
 * edges connected to the node; the maximum degree of a graph is the highest degree of all the
 * nodes. In a directed graph, nodes have an indegree and an outdegree.
 * <p>
 * Graphs are represented by an array of N node objects, each with a label, a hash set of
 * neighbors, and a color (see <code>GraphNode</code> class).
 */
public class GraphColoring {

    static void graphColoring(GraphNode[] nodes, String[] colors) {
        if ((nodes == null || nodes.length == 0) || (colors == null || colors.length == 0)) {
            return;
        }

        for (GraphNode node : nodes) {
            // check if there is a loop in the graph, meaning a node with
            // and edge to itself
            if (node.neighbors.contains(node)) {
                throw new RuntimeException("legal coloring impossible for node with loop " + node.label);
            }

            // check out the colors of the neighbors
            Set<String> colorsOfNeighbors = new HashSet<>();
            for (GraphNode neighbor : node.neighbors) {
                if (neighbor.hasColor()) {
                    colorsOfNeighbors.add(neighbor.getColor());
                }
            }

            // assign legal color to the current node
            // (where legal color is the first one in available colors that does not appear among
            // the colors assigned to the neighbors)
            for (String color : colors) {
                if (!colorsOfNeighbors.contains(color)) {
                    node.setColor(color);
                    break;
                }
            }
        }
    }

    static class GraphNode {

        private String label;
        private Set<GraphNode> neighbors;
        private Optional<String> color;

        public GraphNode(String label) {
            this.label = label;
            neighbors = new HashSet<GraphNode>();
            color = Optional.empty();
        }

        public String getLabel() {
            return label;
        }

        public Set<GraphNode> getNeighbors() {
            return Collections.unmodifiableSet(neighbors);
        }

        public void addNeighbor(GraphNode neighbor) {
            neighbors.add(neighbor);
        }

        public boolean hasColor() {
            return color.isPresent();
        }

        public String getColor() {
            return color.get();
        }

        public void setColor(String color) {
            this.color = Optional.ofNullable(color);
        }
    }
}
