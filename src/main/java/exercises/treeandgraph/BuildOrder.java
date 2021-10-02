package exercises.treeandgraph;

import exercises.treeandgraph.Graph.Node;
import exercises.treeandgraph.Graph.Node.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the second
 * project is dependent on the first project). All of a project's dependencies must be built before the project is. Find
 * a build order that will allow the projects to be built. If there is no valid build order, return an error.
 *
 * @author emanno
 * @version 1.0 Aug 21, 2017
 */
public class BuildOrder {

    public static void main(String[] args) {
        System.out.println(buildOrder(new String[]{
                "a", "b", "c", "d", "e"
        }, new String[][]{
                {
                        "a", "d"
                }, {
                "f", "b"
        }, {
                "b", "d"
        }, {
                "f", "a"
        }, {
                "d", "c"
        }, {
                "a", "d"
        }
        }));
    }


    public static List<String> buildOrder(String[] projects, String[][] dependencies) {
        Graph<String> dependenciesGraph = buildGraph(projects, dependencies);
        List<String> buildOrder = new ArrayList<>();

        for (Node<String> node : dependenciesGraph.getNodes()) {
            if (node.getStatus() == Status.VISITED)
                continue;
            visitNode(node, buildOrder);
        }

        return buildOrder;
    }


    private static void visitNode(Node<String> node, List<String> buildOrder) {
        if (node.getStatus() == Status.VISITING)
            throw new RuntimeException("cyclic dependency detected"); // cycle detected

        if (node.getStatus() != Status.VISITED) {
            node.setStatus(Status.VISITING);

            for (Node<String> child : node.getChildren()) {
                visitNode(child, buildOrder);
            }

            node.setStatus(Status.VISITED);
            buildOrder.add(node.getValue());
        }
    }


    private static Graph<String> buildGraph(String[] projects, String[][] dependencies) {
        Graph<String> graph = new Graph<>();

        // add a node to graph for each project
        for (int i = 0; i < projects.length; i++) {
            graph.addNode(projects[i]);
        }

        // add edges between nodes in graph to represent dependencies
        for (int i = 0; i < dependencies.length; i++) {
            graph.addEdge(dependencies[i][1], dependencies[i][0]);
        }

        return graph;
    }

}
