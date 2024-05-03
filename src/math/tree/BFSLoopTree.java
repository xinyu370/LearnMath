package math.tree;

import java.util.*;

public class BFSLoopTree {

    public static void main(String[] args) {
        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        node1.left=node2;node1.right=node3;
        node2.left=node4;
        System.out.println(getMaxGap(node1));
        List<List<Integer>> lists = ZBFSPrintTree(node1);
        lists.forEach((ele->{
            List<Integer> ele1 = ele;
            System.out.print("[");
            ele1.forEach(ele2->{
                    System.out.print(ele2+",");}
            );
            System.out.print("]");
        }));
    }
    /**按层遍历二叉树（宽度优先遍历）:
      1.准备一个队列，把根结点放进去，然后弹出一个节点，有左加左有右加右(加到队列里面去ß)
     2,怎么知道它的层？用hashmap，层从0开始，每次放入队列，那么对应的层就加一，并把子节点放入map
     3.判断数的数组是否已经创建？新增这个元素:新增一个数组
     */
    public static List<List<Integer>> BFSPrint(TreeNode<Integer> tree){
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        HashMap<TreeNode,Integer> map = new HashMap<>();
        queue.add(tree);
        map.put(tree,0);
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            Integer i = map.get(poll);
            if(poll.left!=null){
                queue.add(poll.left);
                map.put(poll.left,i+1);
            }
            if(poll.right!=null){
                queue.add(poll.right);
                map.put(poll.right,i+1);
            }
            if(ans.size()<=i){
                List<Integer> l = new ArrayList<>();
                l.add((Integer) poll.val);
                ans.add(l);
            }else{
                List<Integer> integers = ans.get(i);
                integers.add((Integer) poll.val);
            }
        }
        return ans;
    }

    private static List<TreeNode> queue = new ArrayList<>(2001);
    //2,自己用数组实现队列
    /**
     * 用一个大数组，数组长度要大于等于二叉树节点最多的情况
     * 然后用左右下标来记录当前一层的元素，然后一次处理一层
     * @param root
     * @return
     */
    public static List<List<Integer>> BFSPrint2(TreeNode<Integer> root){
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null)
            return ans;
        int l=0,r=0;
        queue.add(l,root);
        r++;
        while(l<r){
            int size = r-l; //当前层元素的个数
            List<Integer> list = new ArrayList<>();
            //遍历当前队列内的元素，这是一层的r一直往后移，l则每次遍历玩=完往后一定一次
            //循环结束。l就来到原来r的位置
            for(int i = 0;i<size;i++){
                TreeNode treeNode = queue.get(l);
                if(treeNode.left!=null){
                    queue.add(r++,treeNode.left);
                }
                if(treeNode.right!=null){
                    queue.add(r++,treeNode.right);
                }
                list.add((Integer) treeNode.val);
                l++;
            }
            ans.add(list);
        }
        return ans;
    }

    //按照z型宽度优先遍历输,该反转的时候直接利用下标反向遍历就好了
    public static List<List<Integer>> ZBFSPrintTree(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        int l =0, r = 0;
        queue.add(root);
        r++;
        boolean reverse = false;
        while(l<r){
            int size = r-l;
            List<Integer> list = new ArrayList<>();
            int k=l,j=r-1;
            while(list.size()<size){
                list.add(reverse?(Integer) queue.get(j--).val:(Integer) queue.get(k++).val);
            }
            for(int i = 0;i<size;i++){
                TreeNode treeNode = queue.get(l);
                if(treeNode.left!=null){
                    queue.add(r++,treeNode.left);
                }
                if(treeNode.right!=null){
                    queue.add(r++,treeNode.right);
                }
                l++;
            }
            res.add(list);
            reverse=!reverse;
        }
        return res;
    }

    //返回树最大的间隔
    public static List<Integer> qt = new ArrayList<>();
    public static int getMaxGap(TreeNode root){
        int max = 0;
        int l=0,r=0;
        queue.add(r,root);
        qt.add(r,1);
        r++;
        while(l<r){
            int size = r-l;
            max = Math.max(max,qt.get(r-1)-qt.get(l)+1);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.get(l);
                if(node.left!=null){
                    queue.add(r,node.left);
                    qt.add(r++,qt.get(i)*2);
                }
                if(node.right!=null){
                    queue.add(r,node.right);
                    qt.add(r++,qt.get(i)*2+1);
                }
                l++;
            }
        }
        return max;
    }


}
