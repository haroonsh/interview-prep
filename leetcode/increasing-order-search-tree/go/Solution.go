/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func increasingBST(root *TreeNode) *TreeNode {
    traversal := []int{}
    inorderTraversal(root, &traversal)
    fmt.Printf("%v", traversal)
    return traversalToChain(traversal)
}

func inorderTraversal(root *TreeNode, traversal *[]int) {
    if (root != nil) {
        inorderTraversal(root.Left, traversal)
        *traversal = append((*traversal), root.Val)
        inorderTraversal(root.Right, traversal)
    }
}

func traversalToChain(traversal []int) *TreeNode {
    if (len(traversal) == 0) {
        return nil
    }
    root := &TreeNode{Val: traversal[0]}
    parent := root;
    for i := 1; i < len(traversal); i++ {
        child := &TreeNode{Val: traversal[i]}
        parent.Right = child
        parent = child
    }
    return root
}
