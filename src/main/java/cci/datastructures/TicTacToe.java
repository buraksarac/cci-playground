package cci.datastructures;

import cci.datastructures.Graph;
import cci.datastructures.Graph.Node;
import cci.datastructures.Graph.Node.Neighbour;
import cci.datastructures.Graph.Node.Neighbour.Direction;

public class TicTacToe {

	private int dimA;
	private int dimB;

	public TicTacToe(int dimA, int dimB) {
		this.dimA = dimA;
		this.dimB = dimB;
		build();
	}

	private static Graph<Boolean> graph = new Graph<Boolean>();

	public static final void main(String... strings) {
		TicTacToe tt = new TicTacToe(3, 3);
		tt.setField(true, 0, 0);
		tt.setField(false, 1, 1);
		tt.setField(true, 2, 2);
	}

	public void setField(boolean val, int x, int y) {
		Node<Boolean> node = graph.getNode((x * dimA) + y);
		node.setValue(val);
		if (isWin(node)) {
			System.out.println("Winnerrr!!!!");
		}
	}

	public boolean isWin(Node<Boolean> n) {
		graph.unvisit();
		if(n.getValue()) {
			n.setVisited(true);
			for (Neighbour<Boolean> ne : n.getNeighbours()) {
				if (!ne.getNode().isVisited() && ne.getNode().getValue() != null && !ne.getNode().getValue()) {
					ne.getNode().setVisited(true);
					for (Neighbour<Boolean> n3 : ne.getNode().getNeighbours()) {
						if (!n3.getNode().isVisited() && n3.getDirection().equals(ne.getDirection())
								&& n3.getNode().getValue() != null && n3.getNode().getValue()) {
							return true;
						}

					}
				}
			}
		}else {
			for (Neighbour<Boolean> ne : n.getNeighbours()) {
				if(n.getValue()) {
					return isWin(ne.getNode());
				}
			}
		}
		
		return false;
	}

	public final void build() {
		for (int i = 0; i < dimA; i++) {
			for (int j = 0; j < dimB; j++) {
				graph.addNode(null, "" + i + " " + j);
			}
		}
		for (int i = 0; i < dimA; i++) {
			for (int j = 0; j < dimB; j++) {

				if (j > 0) {
					graph.getNode((i * dimA) + j).addNeighbour(graph.getNode((i * dimA) + (j - 1)), Direction.W);
					if (i > 0) {
						graph.getNode((i * dimA) + j).addNeighbour(graph.getNode(((i - 1) * dimA) + (j - 1)),
								Direction.NW);
					}
					if (i < dimA - 1) {
						graph.getNode((i * dimA) + j).addNeighbour(graph.getNode(((i + 1) * dimA) + (j - 1)),
								Direction.SW);
					}
				}
				if (j < dimB - 1) {
					graph.getNode((i * dimA) + j).addNeighbour(graph.getNode(((i) * dimA) + (j + 1)), Direction.E);
					if (i > 0) {
						graph.getNode((i * dimA) + j).addNeighbour(graph.getNode(((i - 1) * dimA) + (j + 1)),
								Direction.NE);
					}
					if (i < dimA - 1) {
						graph.getNode((i * dimA) + j).addNeighbour(graph.getNode(((i + 1) * dimA) + (j + 1)),
								Direction.SE);
					}
				}
				if (i > 0) {
					graph.getNode((i * dimA) + j).addNeighbour(graph.getNode(((i - 1) * dimA) + (j)), Direction.N);
				}

				if (i < dimA - 1) {
					graph.getNode((i * dimA) + j).addNeighbour(graph.getNode(((i + 1) * dimA) + (j)), Direction.S);
				}
			}
		}

		System.out.println("Build done");

	}

}
