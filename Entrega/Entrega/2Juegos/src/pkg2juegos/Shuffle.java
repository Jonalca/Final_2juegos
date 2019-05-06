/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author alumneM
 */
public class Shuffle {
    
    
     public static String shuffle(String input)
     {
        List<Character> characters = new ArrayList<>();
        for(char c : input.toCharArray())
        {
            characters.add(c);            
        }
        
        /*
        Collections.shuffle(characters);
        return characters+"";
        */
        
        StringBuilder output = new StringBuilder(input.length());
        while(!characters.isEmpty())
        {
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        //System.out.println(output.toString());
        return output.toString();
     }  
     
     
     

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String test;
        
        String abc = "ABCDEFG";
        test = shuffle(abc+abc);
        System.out.println(test);
        
        
        
    
    }
    
}
