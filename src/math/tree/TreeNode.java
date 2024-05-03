package math.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    public T val;
    public TreeNode left;

    public TreeNode right;

    public TreeNode(T val){
        this.val = val;
    }
}
