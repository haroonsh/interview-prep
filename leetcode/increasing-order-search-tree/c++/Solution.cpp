/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* increasingBST(TreeNode* root) {
        vector<int> l = {};
        inorderTraversal(root, &l);
        return traversalToChain(&l);
    }
    
    void inorderTraversal(TreeNode* root, vector<int> *l) {
        if (root != NULL) {
            inorderTraversal(root->left, l);
            (*l).push_back(root->val);
            inorderTraversal(root->right, l);
        }
    }
    
    TreeNode* traversalToChain(vector<int> *l) {
        if ((*l).size() == 0) {
            return NULL;
        }
        TreeNode* root = new TreeNode((*l)[0]);
        TreeNode* parent = root;
        for (int i = 1; i < (*l).size(); i++) {
            TreeNode* child = new TreeNode((*l)[i]);
            parent->right = child;
            parent = child;
        }
        return root;
    }
};
