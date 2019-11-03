package cci.datastructures;

import java.util.function.Consumer;


public class BinaryTree<T extends Comparable<T>> {
  
  public static void main(String[] args) {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    bt.add(50);
    bt.add(30);
    bt.add(70);
    bt.add(20);
    bt.add(40);
    bt.add(60);
    bt.add(80);
    bt.delete(20);
    bt.delete(30);

    bt.delete(50);
    bt.preOrderTraversal(System.out::println);
  }

  private Node root;

  public enum Direction {
    LEFT, RIGHT;
  }

  public void add(T value) {
    if (this.root == null) {
      root = new Node(value, null, null);
      return;
    }

    Node currentNode = root;
    Node parent = null;
    while (currentNode != null) {
      parent = currentNode;
      int diff = value.compareTo(currentNode.value);
      if (diff < 0) {
        currentNode = currentNode.left;
      } else if (diff > 0) {
        currentNode = currentNode.right;
      } else {
        return;// duplicate
      }
    }

    if (value.compareTo(parent.value) < 0) {
      parent.left = new Node(value, parent, Direction.LEFT);
    } else {
      parent.right = new Node(value, parent, Direction.RIGHT);
    }
  }

  public Node search(T data) {
    if (this.root == null || data == null) {
      return null;
    }
    if(data.compareTo(root.value) == 0) {
      return root;
    }

    Node currentNode = root;
    while (currentNode != null) {
      int diff = data.compareTo(currentNode.value);
      if (diff < 0) {
        currentNode = currentNode.left;
      } else if (diff > 0) {
        currentNode = currentNode.right;
      } else {
        return currentNode;
      }
    }
    return null;
  }

  public void delete(T data) { //log N for search + ~logN/2 for minima which around 2logN?
    Node node = search(data);
    if (node != null) {
      node.delete();
    }
  }

  public void inOrderTraversal(Consumer<Node> consumer) {
    inOrderTraversal(root, consumer);
  }

  public void inOrderTraversal(Node node, Consumer<Node> consumer) {
    if (node != null) {
      inOrderTraversal(node.left, consumer);
      consumer.accept(node);
      inOrderTraversal(node.right, consumer);
    }
  }

  public void preOrderTraversal(Consumer<Node> consumer) {
    preOrderTraversal(root, consumer);
  }

  public void preOrderTraversal(Node node, Consumer<Node> consumer) {
    if (node != null) {
      consumer.accept(node);
      preOrderTraversal(node.left, consumer);
      preOrderTraversal(node.right, consumer);
    }
  }

  public void postOrderTraversal(Consumer<Node> consumer) {
    postOrderTraversal(root, consumer);
  }

  public void postOrderTraversal(Node node, Consumer<Node> consumer) {
    if (node != null) {
      postOrderTraversal(node.left, consumer);
      postOrderTraversal(node.right, consumer);
      consumer.accept(node);
    }
  }

  public class Node {
    private T value;
    private Node left;
    private Node right;
    private Node parent; // ?
    private Direction direction;


    public Node(T value, Node parent, Direction direction) {
      this.value = value;
      this.parent = parent;
      this.direction = direction;
    }

    public void delete() {

      if (this.right != null) {
        Node minValueNode = this.minValue(this.right);
        this.value = minValueNode.value;
        minValueNode.updateParentByDirection(null);
      } else if (this.left != null) {
        updateParentByDirection(this.left);
      } else {
        updateParentByDirection(null);
      }
    }

    private void updateParentByDirection(Node nodeToReplace) {
      if (this.parent == null) {
        root = nodeToReplace; // TODO coupling!
      }
      if (this.direction.equals(Direction.LEFT)) {
        this.parent.left = nodeToReplace;
      } else {
        this.parent.right = nodeToReplace;
      }
    }

    private Node minValue(Node parent) {
      Node node = parent;
      while (node.left != null) {
        node = parent.left;
      }
      return node;
    }
    
    @Override
    public String toString() {
      return "" + this.value + " " + this.direction + " childOf(" + (this.parent == null ? "no parent" : this.parent.value) + ")";
    }

  }



}

