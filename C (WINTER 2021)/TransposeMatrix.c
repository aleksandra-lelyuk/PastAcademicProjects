//
//  main.c
//  Q4
//
//  Created by Alexandra Lelyuk on 2021-04-14.
//

#include <stdio.h>
#include <stdlib.h>

int **matrixTranspose1(int size, int matrix[size][size]);
//void matrixTranspose1(int size, int** matrix);
int **matrixTranspose2(int n, int m, int matrix[n][m]);

//(a) transpose square matrix

int **matrixTranspose1(int size, int matrix[size][size])
{
    int **toReturn= (int **)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++){
        toReturn[i] = (int *)malloc(sizeof(int)*size);
        for (int j = 0; j < size; j++){
            toReturn[i][j] = matrix[j][i];
        }
    }
    return toReturn;
}

//(a) transpose rectangular matrix
int **matrixTranspose2(int n, int m, int matrix[n][m])
{
    int **toReturn= (int **)malloc(n * m * sizeof(int));
    
    for (int i = 0; i < m; i++){
        toReturn[i] = (int *)malloc(sizeof(int)* m *n);
        for (int j = 0; j < n; j++){
            toReturn[i][j] = matrix[j][i];
        }
    }
    return toReturn;
}

    
   
int main(int argc, const char * argv[]) {
    // insert code here...
    
    //print first original matrix
    int size1 = 3;
    int matrix[3][3] = {{1, 2, 3},{4, 5, 6}, {7, 8, 9}};
    
    printf("First (Square) Matrix: \n");
    for (int i = 0; i < size1; i++){
        for (int j = 0; j < size1; j++){
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
    printf("\nFirst (Square) Matrix Transposed: \n");
    int **newMatrix;
    
    newMatrix = matrixTranspose1(size1, matrix);
    
    for (int i = 0; i < size1; i++){
        for (int j = 0; j < size1; j++){
            printf("%d ", newMatrix[i][j]);
        }
        printf("\n");
    }
    
    printf("\nSecond Matrix: \n");
    int **newMatrix2;
    
    int n = 4;
    int m = 3;
    int matrix2[4][3] = {{1, 2, 3},{4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
    
    for (int i = 0; i < n; i++){
        for (int j = 0; j < m; j++){
            printf("%d ", matrix2[i][j]);
        }
        printf("\n");
    }
    printf("\nSecond Matrix Transposed: \n");
    
    newMatrix2 = matrixTranspose2(4, 3, matrix2);

    for (int i = 0; i < m; i++){
        for (int j = 0; j < n; j++){
            printf("%d ", newMatrix2[i][j]);
        }
        printf("\n");
    }
    
    return 0;
}
