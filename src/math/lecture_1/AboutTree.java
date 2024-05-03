package org.example.lecture_1;

import java.util.Stack;

public class AboutTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        tailPrint(root);
        System.out.println("=====");
        tailPrintStack(root);
    }


    //前序遍历
    public static void prePrint(TreeNode root){
        if(root==null){
            return;
        }
        System.out.println(root.val);
        prePrint(root.left);
        prePrint(root.right);
    }

    public static void prePrintStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if(pop.right!=null){
                stack.push(pop.right);
            }
            if(pop.left!=null){
                stack.push(pop.left);
            }
        }
    }

    //中序遍历
    public static void midPrint(TreeNode root){
        if(root==null){
            return;
        }
        midPrint(root.left);
        System.out.println(root.val);
        midPrint(root.right);
    }

    public static void midPrintStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.empty() || root !=null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode pop = stack.pop();
                System.out.println(pop.val);
                root = pop.right;
            }
        }
    }

    //
    public static void tailPrint(TreeNode root){
      if(root==null){
          return;
      }
      tailPrint(root.left);
      tailPrint(root.right);
      System.out.println(root.val);
    }

    //double stack
    public static  void tailPrintDoubleStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> collect = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            TreeNode pop = stack.pop();
            collect.push(pop);
            if(pop.left!=null){
                stack.push(pop.left);
            }
            if(pop.right!=null){
                stack.push(pop.right);
            }
        }
        while(!collect.isEmpty()){
            System.out.println(collect.pop().val);
        }
    }
    public static  void tailPrintStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
           TreeNode peek = stack.peek();
          if(peek.left!=null && peek.left!=root && peek.right!=root){
                stack.push(peek.left);
          }else if(peek.right!=null && peek.right != root ){
              stack.push(peek.right);
          }else{
              root = stack.pop();
              System.out.println(root.val);
          }
        }
    }







    static class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val, TreeNode left,TreeNode next){
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public TreeNode(int val){
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}
