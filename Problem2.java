import java.util.*;

// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : NO


// Your code here along with comments explaining your approach

/**
 * After whiteboarding and understanding the problem, we can see that this is a graph problem.
 * We can represent the courses as nodes and the prerequisites as edges. We make an indegree
 * array to keep track of number of dependencies for each course. We also create a map
 * of independent to dependent courses. We add the independent courses (with indegree 0)
 * to the queue and increment the count. We then iterate over the dependent courses and decrease
 * their indegree by 1. If the indegree becomes 0, we add it to the queue and increment the count.
 *
 * At last, we check if the count is equal to the number of courses, if yes, we can complete all the courses.
 */

public class Problem2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) return true;
        Map<Integer, List<Integer>> deps = new HashMap<>();
        int[] indegree = new int[numCourses];

//         fill the indegree array and create the deps map
        for (int[] edge : prerequisites) {
            int dep = edge[0];
            int inDep = edge[1];
            indegree[dep]++;
            if (!deps.containsKey(inDep)) {
                deps.put(inDep, new ArrayList<>());
            }
            deps.get(inDep).add(dep);
        }

        Queue<Integer> q = new LinkedList<>();
// count to check how many courses are we able to complete so we can tally with numCourses
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                count++;
            }
        }

        while (!q.isEmpty()) {
            int inDep = q.poll();
            List<Integer> dependants = deps.get(inDep);
            if (dependants == null) continue;
            for (int dep : dependants) {
                indegree[dep]--;
                if (indegree[dep] == 0) {
                    q.add(dep);
                    count++;
                    if (count == numCourses) return true;
                }
            }
        }
        return false;
    }
}
