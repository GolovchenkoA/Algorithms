package ua.holovchenko.art.trees;

import java.util.LinkedList;
import java.util.Queue;

public class DFSTraversal {

    public static void main(String[] args) {

        // 1. Depth-First Search (DFS)
        // 2. Breadth-First Search (BFS)

        // For Binary trees, there are three types of DFS traversals.
        // source: https://www.digitalocean.com/community/tutorials/breadth-first-search-depth-first-search-bfs-dfs
        //         https://otus.ru/nest/post/1801/
        //   A
        //  / \
        // B   C
        //
        //In-Order (Left -> Right) (symmetric order)  B, A, C
        //Pre-Order (Up -> Down)                      A, B, C
        //Post-Order (revers order)                   B, C, A

        // For Binary trees, we have Level Order Traversal which follows BFS.

        //       0
        //      / \
        //    1    2
        //  / \
        // 3   4

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println("Inorder traversal"); // 3->1->4->0->2->
        inorder(root);

        System.out.println("\nPreorder traversal "); // 0->1->3->4->2->
        preorder(root);

        System.out.println("\nPostorder traversal"); // 3->4->1->2->0->
        postorder(root);

        // Breadth-First Search (BFS)
        System.out.println("\nLevelorder traversal"); // 0 1 2 3 4
        printLevelOrder(root);
    }

    static class TreeNode {
        int item;
        TreeNode left, right;

        public TreeNode(int key) {
            item = key;
            left = right = null;
        }
    }

    // 1. Pre-Order Traversal (Up -> Down)
    static void preorder(TreeNode TreeNode) {
        if (TreeNode == null)
            return;

        // Traverse root
        System.out.print(TreeNode.item + "->");
        // Traverse left
        preorder(TreeNode.left);
        // Traverse right
        preorder(TreeNode.right);
    }

    //(Left -> Right)
    static void inorder(TreeNode TreeNode) {
        if (TreeNode == null)
            return;

        // Traverse left
        inorder(TreeNode.left);
        // Traverse root
        System.out.print(TreeNode.item + "->");
        // Traverse right
        inorder(TreeNode.right);
    }

    //Post-Order (revers order)
    static void postorder(TreeNode TreeNode) {
        if (TreeNode == null)
            return;

        // Traverse left
        postorder(TreeNode.left);
        // Traverse right
        postorder(TreeNode.right);
        // Traverse root
        System.out.print(TreeNode.item + "->");
    }

    static void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.item + " ");

            /*add left child to the queue */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*add right right child to the queue */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
}
