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

import java.util.HashMap;
import java.util.TreeSet;

/**
 * This class provides a utility to build a graph from a list of edges.
 *
 * It also contains specific factory methods to generate graph instances used
 * as examples within Data Structures and Algorithms in Java, 6th edition.
 */
public class GraphExamples {

  /**
   * Constructs a graph from an array of array strings.
   *
   * An edge can be specified as { "SFO", "LAX" }, in which case edge is created
   * with default edge value of 1, or as { "SFO", "LAX", "337" }, in which case
   * the third entry should be a string that will be converted to an integral value.
   */
  public static Graph<String,Integer> graphFromEdgelist(String[][] edges, boolean directed) {
    Graph<String,Integer> g = new AdjacencyMapGraph<>(directed);

    // first pass to get sorted set of vertex labels
    TreeSet<String> labels = new TreeSet<>();
    for (String[] edge : edges) {
      labels.add(edge[0]);
      labels.add(edge[1]);
    }

    // now create vertices (in alphabetical order)
    HashMap<String, Vertex<String> > verts = new HashMap<>();
    for (String label : labels)
      verts.put(label, g.insertVertex(label));

    // now add edges to the graph
    for (String[] edge : edges) {
      Integer cost = (edge.length == 2 ? 1 : Integer.parseInt(edge[2]));
      g.insertEdge(verts.get(edge[0]), verts.get(edge[1]), cost);
    }
    return g;
  }

  /** Returns the unweighted, directed graph from Figure 14.3 of DSAJ6. */
  public static Graph<String,Integer> figure14_3() {
    String[][] edges = {
      {"BOS","SFO"}, {"BOS","JFK"}, {"BOS","MIA"}, {"JFK","BOS"},
      {"JFK","DFW"}, {"JFK","MIA"}, {"JFK","SFO"}, {"ORD","DFW"},
      {"ORD","MIA"}, {"LAX","ORD"}, {"DFW","SFO"}, {"DFW","ORD"},
      {"DFW","LAX"}, {"MIA","DFW"}, {"MIA","LAX"},
    };
    return graphFromEdgelist(edges, true);
  }

  /** Returns the unweighted, directed graph from Figure 14.8 of DSAJ6. */
  public static Graph<String,Integer> figure14_8() {
    String[][] edges = {
      {"BOS","SFO"}, {"BOS","JFK"}, {"BOS","MIA"}, {"JFK","BOS"},
      {"JFK","DFW"}, {"JFK","MIA"}, {"JFK","SFO"}, {"ORD","DFW"},
      {"ORD","MIA"}, {"LAX","ORD"}, {"DFW","SFO"}, {"DFW","ORD"},
      {"DFW","LAX"}, {"MIA","DFW"}, {"MIA","LAX"}, {"SFO","LAX"},
    };
    return graphFromEdgelist(edges, true);
  }

  /**
   * Returns the unweighted, undirected graph from Figure 14.9 of DSAJ6.
   * This is the same graph as in Figure 14.10.
   */
  public static Graph<String,Integer> figure14_9() {
    String[][] edges = {
      {"A","B"}, {"A","E"}, {"A","F"}, {"B","C"}, {"B","F"},
      {"C","D"}, {"C","G"}, {"D","G"}, {"D","H"}, {"E","F"},
      {"E","I"}, {"F","I"}, {"G","J"}, {"G","K"}, {"G","L"},
      {"H","L"}, {"I","J"}, {"I","M"}, {"I","N"}, {"J","K"},
      {"K","N"}, {"K","O"}, {"L","P"}, {"M","N"},
    };
    return graphFromEdgelist(edges, false);
  }

  /** Returns the unweighted, directed graph from Figure 14.11 of DSAJ6. */
  public static Graph<String,Integer> figure14_11() {
    String[][] edges = {
      {"BOS","JFK"}, {"BOS","MIA"}, {"JFK","BOS"}, {"JFK","DFW"},
      {"JFK","MIA"}, {"JFK","SFO"}, {"ORD","DFW"},
      {"LAX","ORD"}, {"DFW","SFO"}, {"DFW","ORD"},
      {"DFW","LAX"}, {"MIA","DFW"}, {"MIA","LAX"},
    };
    return graphFromEdgelist(edges, true);
  }

  /**
   * Returns the unweighted, directed graph from Figure 14.12 of DSAJ6.
   * This is the same graph as in Figure 14.13.
   */
  public static Graph<String,Integer> figure14_12() {
    String[][] edges = {
      {"A","C"}, {"A","D"}, {"B","D"}, {"B", "F"}, {"C","D"}, {"C","E"},
      {"C","H"}, {"D","F"}, {"E","G"}, {"F","G"}, {"F","H"}, {"G","H"}
    };
    return graphFromEdgelist(edges, true);
  }

  /** Returns the weighted, undirected graph from Figure 14.14 of DSAJ6. */
  public static Graph<String,Integer> figure14_14() {
    String[][] edges = {
      {"SFO", "LAX", "337"}, {"SFO", "BOS", "2704"}, {"SFO", "ORD", "1846"},
      {"SFO", "DFW", "1464"}, {"LAX", "DFW", "1235"}, {"LAX", "MIA", "2342"},
      {"DFW", "ORD", "802"}, {"DFW", "MIA", "1121"}, {"ORD", "BOS", "867"},
      {"ORD", "JFK", "740"}, {"MIA", "JFK", "1090"}, {"MIA", "BOS", "1258"},
      {"JFK", "BOS", "187"},
    };
    return graphFromEdgelist(edges, false);
  }

  /**
   * Returns the weighted, undirected graph from Figure 14.15 of DSAJ6.
   * This is the same graph as in Figures 14.16, 14.17, and 14.20-14.24.
   */
  public static Graph<String,Integer> figure14_15() {
    String[][] edges = {
      {"SFO", "LAX", "337"}, {"SFO", "BOS", "2704"}, {"SFO", "ORD", "1846"},
      {"SFO", "DFW", "1464"}, {"LAX", "DFW", "1235"}, {"LAX", "MIA", "2342"},
      {"DFW", "ORD", "802"}, {"DFW", "JFK", "1391"}, {"DFW", "MIA", "1121"},
      {"ORD", "BOS", "867"}, {"ORD", "PVD", "849"}, {"ORD", "JFK", "740"},
      {"ORD", "BWI", "621"}, {"MIA", "BWI", "946"}, {"MIA", "JFK", "1090"},
      {"MIA", "BOS", "1258"}, {"BWI", "JFK", "184"}, {"JFK", "PVD", "144"},
      {"JFK", "BOS", "187"},
    };
    return graphFromEdgelist(edges, false);
  }


  public static void main(String[] args) {
    System.out.println("Figure 14.3");
    System.out.println(figure14_3());

    System.out.println("Figure 14.8");
    System.out.println(figure14_8());

    System.out.println("Figure 14.9");
    System.out.println(figure14_9());

    System.out.println("Figure 14.11");
    System.out.println(figure14_11());

    System.out.println("Figure 14.12");
    System.out.println(figure14_12());

    System.out.println("Figure 14.14");
    System.out.println(figure14_14());

    System.out.println("Figure 14.15");
    System.out.println(figure14_15());
  }

}
