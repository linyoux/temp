package org.example;

import java.util.Scanner;

public class Main {
    private static int numOfVertex;
    private static int numOfEdge;        // 边的数量
    private static int[] vertices;       // 顶点集合
    private static int[][] matrix;    // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 最大值

    public static void main(String[] args) {
        init();
        floyd();
    }


    private static void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入顶点数：");
        numOfVertex = scanner.nextInt();
        System.out.print("输入边数：");
        numOfEdge = scanner.nextInt();
        scanner.nextLine();
        matrix = new int[numOfVertex + 1][numOfVertex + 1];

        // 初始化边的权值
        for (int i = 0; i <= numOfVertex; i++) {
            for (int j = 1; j <= numOfVertex; j++) {
                if (i == j)
                    matrix[i][j] = 0;
                else
                    matrix[i][j] = INF;
            }
        }

        // 建图
        for (int i = 0; i < numOfEdge; i++) {
            String input = scanner.nextLine();
            String[] inputs = input.split("\\s");
            int firstPoint = Integer.parseInt(inputs[0]);
            int secondPoint = Integer.parseInt(inputs[1]);
            int length = Integer.parseInt(inputs[2]);
            matrix[firstPoint][secondPoint] = length;
            matrix[secondPoint][firstPoint] = length;
        }


        for (int i = 1; i <= numOfVertex; i++) {
            for (int j = 1; j <= numOfVertex; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void floyd() {
        int[][] path = new int[numOfVertex+1][numOfVertex+1];
        int[][] dist = new int[numOfVertex+1][numOfVertex+1];
        // 初始化
        for (int i = 1; i <= numOfVertex; i++) {
            for (int j = 1; j <= numOfVertex; j++) {
                dist[i][j] = matrix[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
                path[i][j] = j;                // "顶点i"到"顶点j"的最短路径是经过顶点j。
            }
        }

        // 计算最短路径
        for (int k = 1; k <= numOfVertex; k++) {
            for (int i = 1; i <= numOfVertex; i++) {
                for (int j = 1; j <= numOfVertex; j++) {

                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int tmp = (dist[i][k]==INF || dist[k][j]==INF) ? INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                        dist[i][j] = tmp;
                        // "i到j最短路径"对应的路径，经过k
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        // 打印floyd最短路径的结果
        System.out.printf("floyd: \n");
        for (int i = 1; i <= numOfVertex; i++) {
            for (int j = 1; j <= numOfVertex; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.printf("\n");
        }
    }
}