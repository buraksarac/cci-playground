package cci.algo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class AStar {
  public static void main(String[] args) {
    Graph graph = new Graph();

    /*
     * Graph pic here
     * https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
     */
    IntStream.range(0, 9).forEach(graph::addNode);

    graph.nodes.get(0).coord = new Point(1, 5);
    graph.nodes.get(1).coord = new Point(3, 9);
    graph.nodes.get(2).coord = new Point(5, 9);
    graph.nodes.get(3).coord = new Point(8, 9);
    graph.nodes.get(4).coord = new Point(12, 5);
    graph.nodes.get(5).coord = new Point(8, 2);
    graph.nodes.get(6).coord = new Point(5, 2);
    graph.nodes.get(7).coord = new Point(3, 2);
    graph.nodes.get(8).coord = new Point(5, 5);

    graph.addEdge(0, 1, 4);
    graph.addEdge(0, 7, 8);
    graph.addEdge(1, 2, 8);
    graph.addEdge(1, 7, 11);
    graph.addEdge(2, 3, 7);
    graph.addEdge(2, 8, 2);
    graph.addEdge(2, 5, 4);
    graph.addEdge(3, 4, 9);
    graph.addEdge(3, 5, 14);
    graph.addEdge(4, 5, 10);
    graph.addEdge(5, 6, 2);
    graph.addEdge(6, 8, 6);
    graph.addEdge(6, 7, 1);
    graph.addEdge(7, 8, 7);


    aStar(graph.nodes.get(3), graph.nodes.get(8));
    System.out.println("\n\n\n");
    Node node = graph.nodes.get(8);
    System.out.println(node + " distance: " + node.totalDistance + " ssp->" + node.shortestPath);
  }



  public static final void aStar(Node start, Node end) {
    start.totalDistance = 0;
    start.setHeuristic(end);

    Set<Node> settledNodes = new HashSet<Node>();
    Queue<Node> unsettledNodes = new PriorityQueue<Node>((n1, n2) -> Double
        .compare(n1.heuristic + n1.totalDistance, n2.heuristic + n2.totalDistance));
    unsettledNodes.add(start);
    Node currentNode = null;
    while (!unsettledNodes.isEmpty()) {
      currentNode = unsettledNodes.poll();
      if (currentNode.equals(end)) {
        System.out.println(currentNode);
        return;
      }
      for (Edge edge : currentNode.edges) {
        if (!settledNodes.contains(edge.target)) {
          calculateMinimumDistance(edge, currentNode);
          edge.target.setHeuristic(end);
          unsettledNodes.add(edge.target);
        }
      }
      settledNodes.add(currentNode);
    }

  }

  private static final void calculateMinimumDistance(Edge edge, Node node) {
    double nodeDistance = node.totalDistance;
    if (nodeDistance + edge.weight < edge.target.totalDistance) {
      edge.target.totalDistance = nodeDistance + edge.weight;
      LinkedList<Node> newShortest = new LinkedList<Node>(node.shortestPath);
      newShortest.add(node);
      edge.target.shortestPath = newShortest;
    }
  }


  public enum EdgeDirection {
    INBOUND, OUTBOUND, BIDIRECTIONAL;
  }
  public static class Graph {

    public List<Node> nodes = new ArrayList<>();

    public void addNode(int value) {
      this.nodes.add(new Node(value));
    }

    public void addEdge(int n1, int n2, int weight) {
      Node from = null;
      Node to = null;
      for (Node n : this.nodes) {
        if (from == null && n.value == n1) {
          from = n;
        }
        if (to == null && n.value == n2) {
          to = n;
        }
      }

      from.addEdge(to, weight, EdgeDirection.OUTBOUND);
      to.addEdge(from, weight, EdgeDirection.INBOUND);
    }
  }
  public static class Node {
    private int value;
    private List<Edge> edges = new ArrayList<>();
    private double totalDistance = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    private Point coord;
    private double heuristic;

    public Node(int value) {
      this.value = value;
    }

    public void addEdge(Node target, int weight, EdgeDirection direction) {
      this.edges.add(new Edge(weight, target, direction));
    }

    public void setHeuristic(Node target) {
      this.heuristic = Math.hypot(this.coord.x - target.coord.x, this.coord.y - target.coord.y);
    }

    @Override
    public String toString() {
      return "" + this.value;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + value;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Node other = (Node) obj;
      if (value != other.value)
        return false;
      return true;
    }



  }
  public static class Edge {
    private Node target;
    private int weight;
    private EdgeDirection direction;


    public Edge(int weight, Node target, EdgeDirection direction) {
      this.weight = weight;
      this.target = target;
      this.direction = direction;
    }

    @Override
    public String toString() {
      return "\t" + (direction.equals(EdgeDirection.OUTBOUND) ? "--(" + weight + ")-->"
          : direction.equals(EdgeDirection.BIDIRECTIONAL) ? "<--(" + weight + ")-->"
              : "<--(" + weight + ")--")
          + target.value;
    }
  }
}
