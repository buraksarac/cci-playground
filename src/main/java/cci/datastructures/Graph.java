package cci.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cci.datastructures.Graph.Node.Neighbour.Direction;


public class Graph<T extends Comparable<T>>{
	
	private List<Node<T>> nodes = new ArrayList<>();
	
	public static class Node<T extends Comparable<T>>{
		public static class Neighbour<T extends Comparable<T>>{
			private Node<T> node;
			private Direction direction;
			public enum Direction{
				N,W,E,S,NE,SE,NW,SW;
			}
			
			public Neighbour(Node<T> node, Direction direction){
				this.direction = direction;
				this.node = node;
			}
			
			public Direction getDirection() {
				return direction;
			}
			
			public Node<T> getNode() {
				return node;
			}
			
			
			
		}
		private T value;
		private List<Neighbour<T>> neighbours = new ArrayList<>();
		boolean visited;
		private String name;
		
		
		
		public Node(T data, String name) {
			this.value = data;
			this.name = name;
			
		}
		
		public T getValue() {
			return this.value;
		}
		
		public void setValue(T value) {
			this.value = value;
		}
		
		public void addNeighbour(Node<T> n, Direction direction ) {
			this.neighbours.add(new Neighbour(n, direction));
		}
		public String toString() {
			return "" + this.name + "[" + value + "]" + "(" + neighbours.stream().map(n->String.valueOf(n.getNode().getValue())).collect(Collectors.joining(", ")) + ")";
		}
		
		public List<Neighbour<T>> getNeighbours() {
			return neighbours;
		}
		
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		public boolean isVisited() {
			return visited;
		}
		
	}
	
	public void addNode(T data, String name) {
		this.nodes.add(new Node(data, name));
	}
	
	public Node getNode(int indx) {
		return nodes.get(indx);
	}
	
	
	public void initNodes(int count) {
		IntStream.range(0, count).forEach(i->addNode(null, null));
	}
	
	public String toString() {
		return nodes.toString();
	}
	
	public List<Node<T>> getNodes() {
		return nodes;
	}
	
	public void unvisit() {
		for(Node n:nodes) {
			n.setVisited(false);
		}
	}
}
