import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Akshat Shenoi
 * @version 1.0
 */
public class GraphAlgorithmsStudentTests1 {

    private Graph<Integer> directedGraph;
    private Graph<Character> undirectedGraph;

    private Graph<Character> undirectedOneVertexGraph;

    private Graph<Character> disconnectedGraph;
    private Graph<Integer> directedOneVertexGraph;
    public static final int TIMEOUT = 200;

    @Before
    public void init() {
        directedGraph = createDirectedGraph();
        undirectedGraph = createUndirectedGraph();
        disconnectedGraph = createDisconnectedGraph();
        directedOneVertexGraph = createDirectedOneVertexGraph();
        undirectedOneVertexGraph = createUndirectedOneVertexGraph();

    }

    /**
     * Creates a directed graph of one vertex.
     *
     * @return the completed graph
     */
    private Graph<Integer> createDirectedOneVertexGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<Vertex<Integer>>();
        vertices.add(new Vertex<Integer>(1));

        Set<Edge<Integer>> edges = new LinkedHashSet<Edge<Integer>>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(1), 0));
        return new Graph<Integer>(vertices, edges);
    }

    /**
     * Creates a undirected graph of one vertex.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedOneVertexGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        vertices.add(new Vertex<Character>((char) 65));

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('A'), 7));

        return new Graph<Character>(vertices, edges);
    }

    /**
     * Creates a disconnected undirected graph.
     * The graph is the same as the undirected graph in the pdf but without connections to F.
     *
     * @return the completed graph
     */
    private Graph<Character> createDisconnectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 70; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        //edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 8));
        //edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 8));
        //edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        //edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

        return new Graph<Character>(vertices, edges);
    }

    /**
     * Creates a directed graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Integer> createDirectedGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<Vertex<Integer>>();
        for (int i = 1; i <= 7; i++) {
            vertices.add(new Vertex<Integer>(i));
        }

        Set<Edge<Integer>> edges = new LinkedHashSet<Edge<Integer>>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(3), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(5), 0));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(6), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(7), 0));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(6), 0));

        return new Graph<Integer>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 70; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 8));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 8));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

        return new Graph<Character>(vertices, edges);
    }

    @Test(timeout = TIMEOUT)
    public void testBFS() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<Integer>(1), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        bfsExpected.add(new Vertex<Integer>(1));
        bfsExpected.add(new Vertex<Integer>(2));
        bfsExpected.add(new Vertex<Integer>(3));
        bfsExpected.add(new Vertex<Integer>(4));
        bfsExpected.add(new Vertex<Integer>(5));
        bfsExpected.add(new Vertex<Integer>(6));
        bfsExpected.add(new Vertex<Integer>(7));

        assertEquals(bfsExpected, bfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testBFSOneVertex() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<Integer>(1), directedOneVertexGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        bfsExpected.add(new Vertex<Integer>(1));

        assertEquals(bfsExpected, bfsActual);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSInvalidStart() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<Integer>(8), directedGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSNullStart() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                null, directedGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSNullGraph() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<Integer>(5), null);
    }



    @Test(timeout = TIMEOUT)
    public void testDFS() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<Integer>(5), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<Integer>(5));
        dfsExpected.add(new Vertex<Integer>(4));
        dfsExpected.add(new Vertex<Integer>(6));
        dfsExpected.add(new Vertex<Integer>(7));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSOneVertex() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<Integer>(1), directedOneVertexGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<Integer>(1));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSInvalidStart() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<Integer>(8), directedGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSNullStart() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                null, directedGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSNullGraph() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<Integer>(5), null);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Character>('D'), undirectedGraph);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 4);
        dijkExpected.put(new Vertex<>('B'), 4);
        dijkExpected.put(new Vertex<>('C'), 2);
        dijkExpected.put(new Vertex<>('D'), 0);
        dijkExpected.put(new Vertex<>('E'), 1);
        dijkExpected.put(new Vertex<>('F'), 7);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstrasOneVertex() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Character>('A'), undirectedOneVertexGraph);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasInvalidStart() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Character>('G'), undirectedGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasNullStart() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                null, undirectedGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasNullGraph() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Character>('D'), null);
    }

    @Test(timeout = TIMEOUT)
    public void testPrims() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
            new Vertex<>('A'), undirectedGraph);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

        assertEquals(edges, mstActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrimsOneVertex() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('A'), undirectedOneVertexGraph);
        Set<Edge<Character>> edges = new HashSet<>();

        assertEquals(edges, mstActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrimsNoValidMST() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('A'), disconnectedGraph);
        assertEquals(null, mstActual);
    }
}