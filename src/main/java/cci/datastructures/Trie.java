package cci.datastructures;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
  
  public static void main(String[] args) {
    List<String> dictionary = new java.util.ArrayList<>();
    for(int i = 0;i<6;i++) {
      dictionary.add(genString());
      System.out.println(dictionary.get(i));
    }
    Trie trie = new Trie(dictionary);
    
    System.out.println(trie.contains("", false));
    System.out.println(trie.contains(null, false));
    System.out.println(trie.contains("", true));
    System.out.println(trie.contains(null, true));
    System.out.println(trie.contains("burak", false));
    System.out.println(trie.contains("burak", true));
    
    System.out.println(trie.contains(dictionary.get(3), false));
    System.out.println(trie.contains(dictionary.get(3), true));
    System.out.println(dictionary.get(3).substring(0,4));
    System.out.println(trie.contains(dictionary.get(3).substring(0,4), false));
    System.out.println(trie.contains(dictionary.get(3).substring(3), true));
    
  }
  
  private static String genString() {
    StringBuilder sb = new StringBuilder(6);
    try {
      SecureRandom.getInstanceStrong().ints(65, 71).limit(6).forEach(i->sb.append((char)i));
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return sb.toString();
    
  }
  private TrieNode root = new TrieNode(null);

  public Trie(List<String> list) {
    list.forEach(root::addWord);
  }

  public boolean contains(String prefix, boolean exact) {

    if(prefix == null || prefix.length() < 1) {return false;}
    TrieNode lastNode = root;
    for (int i = 0; i < prefix.length(); i++) {
      lastNode = lastNode.childrens.get(prefix.charAt(i));
      if (lastNode == null) {
        return false;
      }
    }
    return !exact || lastNode.terminates;
  }

  public static class TrieNode {

    private Map<Character, TrieNode> childrens = new HashMap<>();
    private boolean terminates;

    public Character character;


    public TrieNode(Character c) {
      this.character = c;
    }

    public void addWord(String s) {
      addWord(s.toCharArray(), 0);
    }

    public void addWord(char[] chars, int start) {
      
      

      if (chars == null || chars.length < 1 || start >= chars.length) {
        return;
      }

      char firstChar = chars[start];
      TrieNode child = childrens.computeIfAbsent(firstChar,TrieNode::new);
      if (chars.length > 1) {
        child.addWord(chars, ++start);
      } else {
        child.terminates = true;
      }

    }


  }


}

