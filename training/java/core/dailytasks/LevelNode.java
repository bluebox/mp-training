
import java.util.*;


class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
    }
}

 class Level {
    public static void main(String[] args) {
        
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(12);
        root.right = new TreeNode(13);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(14);
        root.right.left = new TreeNode(2);
        root.left.left.left = new TreeNode(17);
        root.left.left.right = new TreeNode(23);
        root.left.right.left = new TreeNode(27);
        root.left.right.right = new TreeNode(3);
        root.right.left.left = new TreeNode(8);
        root.right.left.right = new TreeNode(11);

        List<List<Integer>> output = getLevelPattern(root);

      
        for (List<Integer> level : output) {
            System.out.println(level);
        }
    }

    static List<List<Integer>> getLevelPattern(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;

        while (!queue.isEmpty()) {
            int count = Math.min(queue.size(), level); 
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(currentLevel);
            level++;
        }

        return result;
    }
}