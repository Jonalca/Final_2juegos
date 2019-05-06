/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2juegos;

import java.util.Scanner;

/**
 *
 * @author alumneM
 */
public class RowCol {
    
    public static boolean validateUserInput(int row_1, int row_2, int col_1, int col_2, int size)
    {
        if (row_1 == row_2 && col_1 == col_2)
        {
            System.out.println("Son iguales");
            return true;
        }
        else if (row_1 < 0 || row_2 < 0 || col_1 < 0 || col_2 < 0)
        {
            System.out.println("Por debajo de 0");
            return true;
        }
        else if (row_1 > (size-1) || row_2 > (size-1) || col_1 > (size-1) ||col_2 > (size-1))
        {
            System.out.println("Fuera de rango");
            return true;
        }
        else
        {
            System.out.println("Esta bien");
            return false;
        }   
    }
    
    
    public static void main(String[] args)
    {
        int row_1 = 0;
        int row_2 = 0;
        int col_1 = 0;
        int col_2 = 0;
        int size = 2;
        
        Scanner scan = new Scanner(System.in);
        
        boolean out = true;
        
        while(out)
        {
            System.out.print("Row_1: ");
                row_1 = scan.nextInt();
            System.out.print("Col_1: ");
                col_1 = scan.nextInt();
            System.out.print("Row_2: ");
                row_2 = scan.nextInt();
            System.out.print("Col_2: ");
                col_2 = scan.nextInt();         
            
            
            out = validateUserInput(row_1, row_2, col_1, col_2, size);
            System.out.println(out);
        }
        
        
        
        
    }
    
}
