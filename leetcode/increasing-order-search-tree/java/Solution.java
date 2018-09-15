/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> inorderTraversal = new ArrayList<>();
        inorder(root, inorderTraversal);
        Integer[] inorderArr = inorderTraversal.toArray(new Integer[inorderTraversal.size()]);
        return inorderToSingleChainTree(inorderArr);
    }
    
    public void inorder(TreeNode root, List<Integer> traversal) {
        if (root == null) {
            return;
        }
        inorder(root.left, traversal);
        traversal.add(root.val);
        inorder(root.right, traversal);
    }
    
    public TreeNode inorderToSingleChainTree(Integer[] traversal) {
        if (traversal.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(traversal[0]);
        TreeNode parent = root;
        for (int i = 1; i < traversal.length; i++) {
            TreeNode child = new TreeNode(traversal[i]);
            parent.right = child;
            parent = child;
        }
        return root;
    }
}
