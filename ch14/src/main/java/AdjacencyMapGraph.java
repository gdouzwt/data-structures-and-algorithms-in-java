/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * An implementation for a graph structure using an adjacency map for each vertex.
 * <p>
 * Every vertex stores an element of type V.
 * Every edge stores an element of type E.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class AdjacencyMapGraph<V, E> implements Graph<V, E> {
    private boolean isDirected;
    private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    /**
     * Constructs an empty graph.
     * The parameter determines whether this is an undirected or directed graph.
     */
    public AdjacencyMapGraph(boolean directed) {
        isDirected = directed;
    }

    /**
     * Returns the number of vertices of the graph
     */
    public int numVertices() {
        return vertices.size();
    }

    /**
     * Returns the vertices of the graph as an iterable collection
     */
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    /**
     * Returns the number of edges of the graph
     */
    public int numEdges() {
        return edges.size();
    }

    /**
     * Returns the edges of the graph as an iterable collection
     */
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    /**
     * Returns the number of edges for which vertex v is the origin.
     * Note that for an undirected graph, this is the same result
     * returned by inDegree(v).
     *
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    public int outDegree(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().size();
    }

    /**
     * Returns an iterable collection of edges for which vertex v is the origin.
     * Note that for an undirected graph, this is the same result
     * returned by incomingEdges(v).
     *
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().values();   // edges are the values in the adjacency map
    }

    /**
     * Returns the number of edges for which vertex v is the destination.
     * Note that for an undirected graph, this is the same result
     * returned by outDegree(v).
     *
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    public int inDegree(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().size();
    }

    /**
     * Returns an iterable collection of edges for which vertex v is the destination.
     * Note that for an undirected graph, this is the same result
     * returned by outgoingEdges(v).
     *
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().values();   // edges are the values in the adjacency map
    }

    /**
     * Returns the edge from u to v, or null if they are not adjacent.
     */
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> origin = validate(u);
        return origin.getOutgoing().get(v);    // will be null if no edge from u to v
    }

    /**
     * Returns the vertices of edge e as an array of length two.
     * If the graph is directed, the first vertex is the origin, and
     * the second is the destination.  If the graph is undirected, the
     * order is arbitrary.
     */
    public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    /**
     * Returns the vertex that is opposite vertex v on edge e.
     */
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
        throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();
        if (endpoints[0] == v)
            return endpoints[1];
        else if (endpoints[1] == v)
            return endpoints[0];
        else
            throw new IllegalArgumentException("v is not incident to this edge");
    }

    /**
     * Inserts and returns a new vertex with the given element.
     */
    public Vertex<V> insertVertex(V element) {
        InnerVertex<V> v = new InnerVertex<>(element, isDirected);
        v.setPosition(vertices.addLast(v));
        return v;
    }

    /**
     * Inserts and returns a new edge between vertices u and v, storing given element.
     *
     * @throws IllegalArgumentException if u or v are invalid vertices, or if an edge already exists between u and v.
     */
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element)
        throws IllegalArgumentException {
        if (getEdge(u, v) == null) {
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            e.setPosition(edges.addLast(e));
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, e);
            dest.getIncoming().put(u, e);
            return e;
        } else
            throw new IllegalArgumentException("Edge from u to v exists");
    }

    /**
     * Removes a vertex and all its incident edges from the graph.
     */
    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> vert = validate(v);
        // remove all incident edges from the graph
        for (Edge<E> e : vert.getOutgoing().values())
            removeEdge(e);
        for (Edge<E> e : vert.getIncoming().values())
            removeEdge(e);
        // remove this vertex from the list of vertices
        vertices.remove(vert.getPosition());
        vert.setPosition(null);             // invalidates the vertex
    }

    @SuppressWarnings({"unchecked"})
    /** Removes an edge from the graph. */
    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        // remove this edge from vertices' adjacencies
        InnerVertex<V>[] verts = (InnerVertex<V>[]) edge.getEndpoints();
        verts[0].getOutgoing().remove(verts[1]);
        verts[1].getIncoming().remove(verts[0]);
        // remove this edge from the list of edges
        edges.remove(edge.getPosition());
        edge.setPosition(null);             // invalidates the edge
    }

    @SuppressWarnings({"unchecked"})
    private InnerVertex<V> validate(Vertex<V> v) {
        if (!(v instanceof InnerVertex)) throw new IllegalArgumentException("Invalid vertex");
        InnerVertex<V> vert = (InnerVertex<V>) v;     // safe cast
        if (!vert.validate(this)) throw new IllegalArgumentException("Invalid vertex");
        return vert;
    }

    @SuppressWarnings({"unchecked"})
    private InnerEdge<E> validate(Edge<E> e) {
        if (!(e instanceof InnerEdge)) throw new IllegalArgumentException("Invalid edge");
        InnerEdge<E> edge = (InnerEdge<E>) e;     // safe cast
        if (!edge.validate(this)) throw new IllegalArgumentException("Invalid edge");
        return edge;
    }

    //---------------- nested Vertex class ----------------

    /**
     * A vertex of an adjacency map graph representation.
     */
    private class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        /**
         * Constructs a new InnerVertex instance storing the given element.
         */
        public InnerVertex(V elem, boolean graphIsDirected) {
            element = elem;
            outgoing = new ProbeHashMap<>();
            if (graphIsDirected)
                incoming = new ProbeHashMap<>();
            else
                incoming = outgoing;    // if undirected, alias outgoing map
        }

        /**
         * Validates that this vertex instance belongs to the given graph.
         */
        public boolean validate(Graph<V, E> graph) {
            return (AdjacencyMapGraph.this == graph && pos != null);
        }

        /**
         * Returns the element associated with the vertex.
         */
        public V getElement() {
            return element;
        }

        /**
         * Stores the position of this vertex within the graph's vertex list.
         */
        public void setPosition(Position<Vertex<V>> p) {
            pos = p;
        }

        /**
         * Returns the position of this vertex within the graph's vertex list.
         */
        public Position<Vertex<V>> getPosition() {
            return pos;
        }

        /**
         * Returns reference to the underlying map of outgoing edges.
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Returns reference to the underlying map of incoming edges.
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    } //------------ end of InnerVertex class ------------

    //---------------- nested InnerEdge class ----------------

    /**
     * An edge between two vertices.
     */
    private class InnerEdge<E> implements Edge<E> {
        private E element;
        private Position<Edge<E>> pos;
        private Vertex<V>[] endpoints;

        @SuppressWarnings({"unchecked"})
        /** Constructs InnerEdge instance from u to v, storing the given element. */
        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
            element = elem;
            endpoints = (Vertex<V>[]) new Vertex[]{u, v};  // array of length 2
        }

        /**
         * Returns the element associated with the edge.
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns reference to the endpoint array.
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        /**
         * Validates that this edge instance belongs to the given graph.
         */
        public boolean validate(Graph<V, E> graph) {
            return AdjacencyMapGraph.this == graph && pos != null;
        }

        /**
         * Stores the position of this edge within the graph's vertex list.
         */
        public void setPosition(Position<Edge<E>> p) {
            pos = p;
        }

        /**
         * Returns the position of this edge within the graph's vertex list.
         */
        public Position<Edge<E>> getPosition() {
            return pos;
        }
    } //------------ end of InnerEdge class ------------

    /**
     * Returns a string representation of the graph.
     * This is used only for debugging; do not rely on the string representation.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
//     sb.append("Edges:");
//     for (Edge<E> e : edges) {
//       Vertex<V>[] verts = endVertices(e);
//       sb.append(String.format(" (%s->%s, %s)", verts[0].getElement(), verts[1].getElement(), e.getElement()));
//     }
//     sb.append("\n");
        for (Vertex<V> v : vertices) {
            sb.append("Vertex " + v.getElement() + "\n");
            if (isDirected)
                sb.append(" [outgoing]");
            sb.append(" " + outDegree(v) + " adjacencies:");
            for (Edge<E> e : outgoingEdges(v))
                sb.append(String.format(" (%s, %s)", opposite(v, e).getElement(), e.getElement()));
            sb.append("\n");
            if (isDirected) {
                sb.append(" [incoming]");
                sb.append(" " + inDegree(v) + " adjacencies:");
                for (Edge<E> e : incomingEdges(v))
                    sb.append(String.format(" (%s, %s)", opposite(v, e).getElement(), e.getElement()));
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
