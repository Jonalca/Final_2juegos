/*
TODO:
*/

/*
DONE:

    1. El joc comença demanant la mida del tauler que podrà ser 2, 4 o 6. Per utilitzar només les
    lletres majúscules de l'abecedari anglès.

    2. Es configurarà el joc segons la mida introduïda i començarà.
    3. Es mostrarà el tauler omplert amb el caràcter que s'hagi triat (si encara no s'ha destapat cap
    casella).
    4. Es demanarà la fila i columna de la primera casella, comprovant que siguin correctes i que
    que no estigui ja destapada.
    5. Es demanarà la fila i columna de la segona casella, comprovant que siguin correctes i que
    que no estigui ja destapada.
    6. Es comprovarà que les posicions de les caselles no siguin iguals.
    7. Es destapen les dues caselles al tauler que es mostra.
    8. Si les dues caselles tenen la mateixa lletra, es comprovarà si el joc ha finalitzat (totes les
    caselles descobertes), si és que no es continuarà al punt 3.
    9. Si les dues caselles no tenen la mateixa lletra, es mostra el tauler amb les dues caselles
    destapades, durant uns segons i s'imprimiran línies en blanc perquè no es visualitzi. Es 
    tornaran a tapar les caselles del tauler que es mostra.Es torna al punt 3.
    10. Quan finalitza el joc, es demanarà a l'usuari si vol continuar jugant, si és que sí, es torna al
    punt 1 i en cas contrari finalitzarà l'aplicació.

*/
package pkg2juegos;


import java.util.Scanner;
import static pkg2juegos.Shuffle.shuffle;




public class Joc1 {
    
//Global variables
public static int size; //Size for the gameMatrix (size * size = matrix)
public static String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Subject to change

public static String gameMatrix[][]; //Matrix for the game
public static String displayMatrix[][]; //To display empty and fill it only with pairs.

public static String shuffled_string; //Shuffled string for randomization

//Global Methods
public static Scanner scan = new Scanner(System.in);

public static int row_1 = 0;
public static int col_1 = 0;
public static int row_2 = 0;
public static int col_2 = 0;      

public static void Randomizer(int size, String[][] gameMatrix)
    {
        
        String str = "";
        int filling_counter = 0;
        
        //Fill the board with mayus chars 
        /*
            if 6*6 -> 36 then 18 letters to be doubled
            if 4*4 -> 16 then 8 letter to be doubled
            if 2*2 -> 4 then 2 letters to be doubled        
        */
        
        //Size limits how many letters are going to be shuffled TODO: Maybe randomly pick them up?
        /*
            For the case of size=6; 6 by 6 which is 36 letters, BUT each letter is repeated so divide by 2 and get 18.
            18 is the number of repeated letters needed to fill up the board. To fill up the board with this method,
            6 * (6 / 2); 6 letters by (half of them)
        */
        
        for (int i = 0; i < (size*(size/2)); i++) 
        {
            str = str + abc.charAt(i);
        }        
        //System.out.println("String is: " + str);//Debug
                        
        shuffled_string = shuffle(str+str);
        
            //System.out.println("Shuffled is: " + shuffled_string + " " + shuffled_string.length() + " letters "); //Debug 
        
            //System.out.println("Filling up the board of size: " + gameMatrix.length);//Debug       
            
        
        for (int i = 0; i < gameMatrix.length; i++) 
        {
            for (int j = 0; j < gameMatrix.length; j++) 
            {
                gameMatrix[i][j] = shuffled_string.charAt(filling_counter)+"";
                filling_counter++;
            }
        } 
    }
    /*Creates a matrix from a given input, if not match on possible inputs, loops and calls itself till a matching input provided*/
public static void createMatrix(int size)
    {
        boolean flag = true;
                
         if (size == 2 || size == 4 || size == 6)
         {
             gameMatrix = new String[size][size]; //Resize of array
             displayMatrix = new String[size][size];
                for (int i = 0; i < displayMatrix.length; i++)
                {
                    for (int j = 0; j < displayMatrix.length; j++) 
                    {
                        displayMatrix[i][j] = "-";
                    }
                }
             System.out.printf("Board [%d x %d] created!\n", size, size); //Debug
         }
         else
         {
             while(flag)//Loop for repeately ask for a valid input
             {
                 System.out.println("Size not valid!, only 2, 4 or 6");
                 System.out.print("Input board size: ");
                    size = scan.nextInt();
                    
                     if (size == 2 || size == 4 || size == 6)//Check
                     {
                         createMatrix(size); //Calls itself
                         break;              //break loop
                     }
             }             
         }
    }
    
    //Shows the all game board filled
public static void showMatrix(String[][] gameMatrix)
    {
        System.out.println("     Display");
        System.out.println("----------------");
        for (int i = 0; i < gameMatrix.length; i++)
        {
            
            System.out.printf("   %d", i);
        }
        System.out.println("");
        
        //System.out.println("Matrix size is: " + gameMatrix.length); //Debug
        for (int i = 0; i < gameMatrix.length; i++) 
        {
            System.out.printf("%d | ", i);
            for (int j = 0; j < gameMatrix.length; j++) 
            {
                System.out.printf(gameMatrix[i][j] + " | ");
            }System.out.println(" ");
        }
    }
    
    //Used for debug purposes
public static int noRepeat(String[][] gameMatrix, char letter)
    {
        int counter = 0;
        
        System.out.println("Testing letter: " + letter);
        
        for (int i = 0; i < gameMatrix.length; i++)
        {
            for (int j = 0; j < gameMatrix.length; j++) 
            {
                if (gameMatrix[i][j].equals(letter))
                {
                    counter++;
                }
            }
        }
        System.out.println("Found: " + counter + "letter " + letter);
        return counter;
    }
    
    //Show letters from a given position
public static void Turn(int row_1, int col_1, int row_2, int col_2, String[][] gameMatrix)
    {  
        displayMatrix[row_1][col_1] = gameMatrix[row_1][col_1];
        displayMatrix[row_2][col_2] = gameMatrix[row_2][col_2];
        
        //Check the letters
        if (gameMatrix[row_1][col_1].equals(gameMatrix[row_2][col_2]))
        {
            showMatrix(displayMatrix);
            System.out.println("--------------------");
            System.out.printf("You found a pair of: %s\n", displayMatrix[row_1][col_1]);
            //Both are the same letter so they stay there shown.
        }
        else
        {
            showMatrix(displayMatrix);
            System.out.println("--------------------");
            System.out.println("Those are not equal! Try again!");
            //We erase what was shown if not equal.
            displayMatrix[row_1][col_1] = "-";
            displayMatrix[row_2][col_2] = "-";
        }
    }
    //This is cheat code for Debug purposes
public static void PlayMeBoi(String[][] gameMatrix, String letter) {
    int row_1 = 0;
    int col_1 = 0;
    int row_2 = 0;
    int col_2 = 0;

    boolean found = false;
    for (int i = 0; i < gameMatrix.length; i++) {
        if (found) break;
        for (int j = 0; j < gameMatrix[0].length; j++) {
            if (letter.equals(gameMatrix[i][j])) {
                row_1 = i;
                col_1 = j;
                found = true;
                break;
            }
        }
    }

    if (!found) {
        System.out.println("Not Found");
        return;
    }

    found = false;
    for (int i = 1; i < gameMatrix.length; i++) {
        if (found) break;
        for (int j = 1; j < gameMatrix[0].length; j++) {
            if (i * gameMatrix[0].length + j > row_1 * gameMatrix[0].length + col_1) {
                if (letter.equals(gameMatrix[i][j])) {
                    row_2 = i;
                    col_2 = j;
                    found = true;
                    break;
                }
            }
        }
    }

    System.out.println("First " + gameMatrix[row_1][col_1] + " at " + " [ " + row_1 + " ] " + "," + " [ " + col_1 + " ] ");
    if (!found) {
        System.out.println("Second Not Found");
        return;
    }
    System.out.println("Second " + gameMatrix[row_1][col_1] + " at " + " [ " + row_2 + " ] " + "," + " [ " + col_2 + " ] ");
    
    Turn(row_1, col_1, row_2, col_2, gameMatrix);
}

public static boolean checkEndgame(String[][] gameMatrix, String[][] displayMatrix)
{
    //We are playing, so true it is.
    boolean flag = false; //We stop playing if the check coudn't find a difference.
    
    for (int i = 0; i < gameMatrix.length; i++) 
    {
        for (int j = 0; j < gameMatrix.length; j++)
        {
            if (!gameMatrix[i][j].equals(displayMatrix[i][j]))
            {
                flag = true; //While different, we keep playing                
            }
        }
    }
    //System.out.println("Game Over!");
    return flag;
    
}

public static void printUserInput()
{
    showMatrix(displayMatrix);
    System.out.println("--------------------");
    System.out.println("Input first row and col press enter between inputs");
    System.out.print("First row: ");
       row_1 = scan.nextInt();
    System.out.print("First column: ");
       col_1 = scan.nextInt();
    System.out.println("Input second row and col press enter between inputs");
    System.out.print("Second row: ");
       row_2 = scan.nextInt();
    System.out.print("Second column: ");
       col_2 = scan.nextInt();
    
}




public static void gameLoop()
{
    
    
        
    //To control whether still play or not
    String game_option;
    
    //Flag Variables to control game loop
    boolean play = true;
    boolean check = true;
    
    //While you still play (true) or if (false) -> end game
    showMatrix(gameMatrix);//Cheat
    while(play == true)
        {
            showMatrix(displayMatrix);
            System.out.println("--------------------");
            System.out.println("Input first row and col press enter between inputs");
            System.out.print("First row: ");
                row_1 = scan.nextInt();
            System.out.print("First column: ");
                col_1 = scan.nextInt();
            System.out.println("Input second row and col press enter between inputs");
            System.out.print("Second row: ");
                row_2 = scan.nextInt();
            System.out.print("Second column: ");
                col_2 = scan.nextInt();
                
                if (row_1 == row_2 && col_1 == col_2)
                {
                    //System.out.println("I tested this!");
                    showMatrix(displayMatrix);
                    System.out.println("--------------------");
                    System.out.println("[ERROR] Input not valid! Try again");
                    printUserInput();
                }
                else if ((row_1 > (size-1) || row_1 < 0) && (row_2 > (size-1) || row_2 < 0) && (col_1 > (size-1) || col_2 < 0) && ((col_2 > (size-1) || col_2 < 0)))     
                {
                    //System.out.println("I tested that!");
                    showMatrix(displayMatrix);
                    System.out.println("--------------------");
                    System.out.println("[ERROR] Input not valid! Try again");
                    printUserInput();
                }
                        
                
            Turn(row_1, col_1, row_2, col_2, gameMatrix);
            check = checkEndgame(gameMatrix, displayMatrix);
                
                if(check == false)
                {
                    System.out.println("Would you like to play again? Y/N");
                        game_option = scan.next();
                        
                        if ("Y".equals(game_option) || "y".equals(game_option))
                        {
                            createGame();
                            gameLoop();
                        }     
                        else if ("N".equals(game_option) || "n".equals(game_option))
                        {
                            play = false;
                        }
                }
        }
}

public static void createGame()
{
    System.out.println("Game Setup");
    System.out.println("----------");
    System.out.println("Board setup");
    System.out.print("Input board size: ");
         size = scan.nextInt();
        
        
    createMatrix(size);
    Randomizer(size, gameMatrix); //input size
    //showMatrix(gameMatrix); //Debug/Cheat
}
public static void main(String[] args)
    {
        createGame();
        gameLoop();        
    }
    
}
