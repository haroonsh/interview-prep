"use strict";
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var increasingBST = function(root) {
    traversal = [];
    inorderTraversal(root, traversal);
    return traversalToChain(traversal);
};

function inorderTraversal(root, traversal) {
    if (root !== null) {
        inorderTraversal(root.left, traversal);
        traversal.push(root.val);
        inorderTraversal(root.right, traversal);
    }
}

function traversalToChain(traversal) {
    if (traversal.length == 0) {
        return null;
    }
    root = new TreeNode(traversal[0]);
    parent = root;
    for (var i = 1; i < traversal.length; i++) {
        child = new TreeNode(traversal[i]);
        parent.right = child;
        parent = child;
    }
    return root;
}
