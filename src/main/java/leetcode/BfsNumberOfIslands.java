package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// LeetCode 200
public class BfsNumberOfIslands {

    private static final int[] DX = {1, -1, 0, 0}, DY = {0, 0, 1, -1};

    private record Cell(int x, int y) {}

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0' || grid[i][j] == '2') {
                    continue;
                }
                cnt++;
                Queue<Cell> q = new LinkedList<>();
                grid[i][j] = '2';
                q.add(new Cell(i, j));
                bfs(grid, q);
            }
        }
        return cnt;
    }

    private void bfs(char[][] grid, Queue<Cell> q) {
        int n = grid.length, m = grid[0].length;
        while (!q.isEmpty()) {
            Cell cur = q.poll();
            int x = cur.x, y = cur.y;
            for (int w = 0; w < DX.length; w++) {
                int nx = x + DX[w], ny = y + DY[w];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (grid[nx][ny] == '1') {
                    grid[nx][ny] = '2';
                    q.add(new Cell(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) {
        BfsNumberOfIslands instance = new BfsNumberOfIslands();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        System.out.println(instance.numIslands(grid));
    }
}
