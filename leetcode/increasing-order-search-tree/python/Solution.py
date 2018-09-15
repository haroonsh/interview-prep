# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def increasingBST(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        traversal = []
        self.inorder_traversal(root, traversal)
        return self.traversal_to_chain(traversal);
    
    def inorder_traversal(self, root, traversal):
        if (root != None):
            self.inorder_traversal(root.left, traversal);
            traversal.append(root.val)
            self.inorder_traversal(root.right, traversal);
        return
    
    def traversal_to_chain(self, traversal):
        if (len(traversal) == 0):
            return None
        root = TreeNode(traversal[0])
        parent = root
        for i in range(len(traversal) - 1):
            child = TreeNode(traversal[i+1])
            parent.right = child
            parent = child
        return root
