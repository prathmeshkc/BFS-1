import java.util.*;

// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : NO


// Your code here along with comments explaining your approach

/**
 * We can traverse the tree using DFS or BFS. Here we are using BFS.
 * The BFS approach is to traverse the tree level by level naturally.
 * With DFS, we keep track of the level of the tree we are at
 * and add the elements to the list at that level.
 */

public class Problem1 {
    List<List<Integer>> res;

    public List<List<Integer>> levelOrder(TreeNode root) {
        this.res = new ArrayList<>();

        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                level.add(curr.val);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
            res.add(level);
        }

        return res;

    }


    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        this.res = new ArrayList<>();
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int level) {
        //base
        if (root == null) return;

        //logic
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}