package pkg2juegos;

import java.util.InputMismatchException;
import java.util.Scanner;
import sun.audio.AudioPlayer;


/*
    1. El joc comença demanant el nom dels dos jugadors. El tauler és 5x5.
    2. El funcionament bàsic d'aquest joc consisteix en que cada jugador per torns va col·locant
    una fitxa en el tauler i el primer que aconsegueix 3 de les seves fitxes consecutives en fila,
    columna o diagonal guanya. Es mostrarà el nom del jugador guanyador i en quantes jugades
    ha guanyat. Si cap dels dos ho aconsegueix fan taules. Fitxes: X i 0.
    3. A cada jugador se li anirà demanant que introdueixi la fila i columna de la casella on vulgui
    inserir la fitxa, actualitzant el tauler a continuació, després li tocarà el torn al segon jugador i
    així successivament fins que un dels dos jugadors aconsegueixi tantes fitxes (3) en línia, el
    qual guanyarà i finalitzarà aquest joc. Sempre s’ha d’actualitzar el tauler amb les fitxes que
    s’ha introduït. Un cop finalitzat se li preguntarà als jugadors si volen tornar a jugar o
    finalitzar.
*/


/**
 * 
 * @author Jonathan Alcaide
 *
 */
public class Joc2 {

    //Players
    public static String player_1;
    public static String player_2;
    public static String currentPlayer;
    
    //Board
    public static String[] board = new String[9];
    
    //Tools
    public static Scanner scan = new Scanner(System.in);
    
    
    public static void showBoard(String[] board)
    {
       System.out.println("|---|---|---|");
       System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
       System.out.println("|-----------|");
       System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
       System.out.println("|-----------|");
       System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
       System.out.println("|---|---|---|"); 
    }
    
    public static void initBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            /*
            As we are going to work with Strings
            lets initialize the array with numbers but in String form
            */
            
            board[i] = String.valueOf(i+1);
        }
    }
    
    public static String endgame()
    {
        
        for (int a = 0; a < 8; a++) 
        {
            String line = null;
            switch (a) 
            {
		case 0:
                    line = board[0] + board[1] + board[2];
                    break;
		case 1:
                    line = board[3] + board[4] + board[5];
                    break;
		case 2:
                    line = board[6] + board[7] + board[8];
                    break;
		case 3:
                    line = board[0] + board[3] + board[6];
                    break;
		case 4:
                    line = board[1] + board[4] + board[7];
                    break;
		case 5:
                    line = board[2] + board[5] + board[8];
                    break;
		case 6:
                    line = board[0] + board[4] + board[8];
                    break;
		case 7:
                    line = board[2] + board[4] + board[6];
                    break;
		}
                    if (line.equals("XXX")) 
                    {
			return "X";
                    } else if (line.equals("OOO")) 
                    {
                    	return "O";
                    }
	}
        
        for (int i = 0; i < board.length; i++)
        {
            if (board[i].contains(String.valueOf(i+1)))
            {
                break; //We found a number (the game is still on) and yet no winner (previous check with the switch case)
            }
            else if (i == 8)//Maxim, range is from 0 to 8 -> 9 positions of 3x3
            {
                return "draw";
            }
        }  
        
        return null;

    }
    
    public static boolean restartGame()
    {
        String choose;
        
        System.out.println("Game Over");
        System.out.println("Do you want to play again? Y/N: ");
            choose = scan.next();
            
            if (choose.equals("Y") || choose.equals("y"))
            {
                initBoard();
                gameLoop();
            }
            else
            {
                System.out.println("Closing the game...");
                return false;
            }
        return false;
    }
 
    public static void gameLoop()
    {
        boolean play = true;
        
        String turn = "X";
        currentPlayer = player_1;
        showBoard(board);
        
        while(play)
        {
            int pos;            
            
            System.out.println("----------------");
            System.out.println("Turn for: " + currentPlayer);
                pos = scan.nextInt();
                
                if (board[pos-1].equals(String.valueOf(pos)))
                {
                     board[pos-1] = turn;
                
                    if ("X".equals(turn))
                    {
                        turn = "O";
                        currentPlayer = player_2;
                    }
                    else
                    {
                        turn = "X";
                        currentPlayer = player_1;
                    }          
                }
                else
                {
                    System.out.println("Slot is taken! Try again");
                }
                showBoard(board);
                
                    //Check if winner
                    String winner = endgame();
                    if (null != winner)
                       switch (winner) 
                       {
                        case "draw":
                             System.out.println("Draw! Nobody wins this time");
                             play = restartGame();
                             break;
                        case "X":
                            System.out.println("Player: " + player_1 + " wins!");
                            play = restartGame();
                            break;
                        case "Y":
                            System.out.println("Player: " + player_2 + " wins!");
                            play = restartGame();
                            break;
                        default:   
                            break;
                        }   
        }
    }

    public static void main(String[] args)
    {

        System.out.println("TicTacToe");
        System.out.println("---------");
        System.out.print("Enter name of player 1: ");
            player_1 = scan.nextLine();
            currentPlayer = player_1;
        System.out.print("Enter name of player 2: ");
            player_2 = scan.nextLine();
            
        initBoard();
        gameLoop();  
    }
}