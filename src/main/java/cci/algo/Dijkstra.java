package cci.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class Dijkstra {

  public static void main(String[] args) {
    Graph graph = new Graph();

    IntStream.range(0, 9).forEach(graph::addNode);

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

    for (Node node : graph.nodes) {
      System.out.println(node);
    }
    
    dijkstra(graph.nodes.get(0));
    System.out.println("\n\n\n");
    for (Node node : graph.nodes) {
      System.out.println(node + " distance: " + node.totalDistance + " ssp->" + node.shortestPath);
    }
  }

  public static final void dijkstra(Node source) {
    source.totalDistance = 0;
    
    Set<Node> settledNodes = new HashSet<Dijkstra.Node>();
    Queue<Node> unsettledNodes = new PriorityQueue<Dijkstra.Node>((n1,n2)->Integer.compare(n1.totalDistance, n2.totalDistance));
    unsettledNodes.add(source);
    while(!unsettledNodes.isEmpty()) {
      Node currentNode = unsettledNodes.poll();
      for(Edge edge:currentNode.edges) {
        if(!settledNodes.contains(edge.target)) {
          calculateMinimumDistance(edge, currentNode);
          unsettledNodes.add(edge.target);
        }
      }
      settledNodes.add(currentNode);
    }
    
  }
  
  private static final void calculateMinimumDistance(Edge edge, Node node) {
    int nodeDistance = node.totalDistance;
    if(nodeDistance + edge.weight < edge.target.totalDistance) {
      edge.target.totalDistance = nodeDistance + edge.weight;
      LinkedList<Node> newShortest = new LinkedList<Dijkstra.Node>(node.shortestPath);
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
    private int totalDistance = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();

    public Node(int value) {
      this.value = value;
    }

    public void addEdge(Node target, int weight, EdgeDirection direction) {
      this.edges.add(new Edge(weight, target, direction));
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
