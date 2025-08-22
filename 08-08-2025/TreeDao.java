package com.treeapp.dao;

import com.treeapp.model.Tree;
import java.util.*;

public class TreeDao {
    private static List<Tree> trees = new ArrayList<>();
    private static int counter = 1;

    public List<Tree> getAllTrees() {
        return trees;
    }

    public void addTree(Tree tree) {
        tree.setId(counter++);
        trees.add(tree);
    }

    public Tree getTreeById(int id) {
        return trees.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void updateTree(Tree updatedTree) {
        Tree tree = getTreeById(updatedTree.getId());
        if (tree != null) {
            tree.setName(updatedTree.getName());
            tree.setType(updatedTree.getType());
            tree.setAge(updatedTree.getAge());
        }
    }

    public void deleteTree(int id) {
        trees.removeIf(t -> t.getId() == id);
    }
}