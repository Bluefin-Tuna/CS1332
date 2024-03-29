import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Tanush Chopra
 * @userid tchopra32
 * @GTID 903785867
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Argument(s) can't be null."); 
        }
        Queue<Vertex<T>> q = new LinkedList<Vertex<T>>();
        Set<Vertex<T>> v = new HashSet<Vertex<T>>();
        List<Vertex<T>> r = new ArrayList<Vertex<T>>();
        q.add(start);
        v.add(start);
        while (!q.isEmpty()) {
            Vertex<T> t = q.remove();
            r.add(t);
            for (VertexDistance<T> d : graph.getAdjList().get(t)) {
                if (!v.contains(d.getVertex())) {
                    q.add(d.getVertex());
                    v.add(d.getVertex());
                }
            }
        }
        return r;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Argument(s) can't be null.");
        }
        Set<Vertex<T>> v = new HashSet<Vertex<T>>();
        List<Vertex<T>> r = new ArrayList<Vertex<T>>();
        GraphAlgorithms.dfs(start, graph, v, r);
        return r;
    }

    /**
     * Private helper function for dfs.
     *
     * @param <T> the generic typing of the data
     * @param v the vertex to perform recursion on
     * @param g the graph to use
     * @param vs the set of visited vertices
     * @param r the set holding all vertices found and in which order
     */
    private static <T> void dfs(Vertex<T> v, Graph<T> g, Set<Vertex<T>> vs, List<Vertex<T>> r) {
        r.add(v);
        vs.add(v);
        for (VertexDistance<T> d : g.getAdjList().get(v)) {
            if (!vs.contains(d.getVertex())) {
                dfs(d.getVertex(), g, vs, r);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Argument(s) can't be null.");
        }
        Queue<VertexDistance<T>> q = new PriorityQueue<VertexDistance<T>>();
        Map<Vertex<T>, Integer> r = new HashMap<Vertex<T>, Integer>();
        for (Vertex<T> v : graph.getAdjList().keySet()) {
            if (v.equals(start)) {
                r.put(v, 0);
            } else {
                r.put(v, Integer.MAX_VALUE);
            }
        }
        q.add(new VertexDistance<T>(start, 0));
        while (!q.isEmpty()) {
            VertexDistance<T> t = q.remove();
            for (VertexDistance<T> d : graph.getAdjList().get(t.getVertex())) {
                int dist = t.getDistance() + d.getDistance();
                if (r.get(d.getVertex()) > dist) {
                    r.put(d.getVertex(), dist);
                    q.add(new VertexDistance<T>(d.getVertex(), dist));
                }
            }
        }
        return r;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use PriorityQueue, java.util.Set, and any class that 
     * implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Argument(s) can't be null.");
        }
        PriorityQueue<Edge<T>> q = new PriorityQueue<Edge<T>>();
        Set<Vertex<T>> v = new HashSet<Vertex<T>>();
        Set<Edge<T>> r = new HashSet<Edge<T>>();
        for (Edge<T> e : graph.getEdges()) {
            if (e.getU().equals(start)) {
                q.add(e);
            }
        }
        v.add(start);
        while (!q.isEmpty()) {
            Edge<T> t = q.remove();
            if (!v.contains(t.getV()) || !v.contains(t.getU())) {
                r.add(t);
                r.add(new Edge<T>(t.getV(), t.getU(), t.getWeight()));
                v.add(t.getV());
                for (Edge<T> e : graph.getEdges()) {
                    if (t.getV().equals(e.getU())) {
                        q.add(e);
                    }
                }
            }
        }
        if (r.size() < graph.getVertices().size() - 1) {
            return null;
        }
        return r;
    }
}