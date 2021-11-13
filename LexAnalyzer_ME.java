import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.io.IOException;

class lexx{
    String input = "";

    public void lex (String input)
    { 
        while(input.length()>0)
        {
            input = addChar(input);
        }
    
    }//end lex

    public int getChar(String input, int index)
    {
        String a = input;
        String c = Character.toString(a.charAt(index));
    
        if(Character.isLetter(a.charAt(index))== true)
        {
            return 0;  //letter = 0
        }else if (Character.isDigit(a.charAt(index)) == true)
        {
            return 1; //digit = 1 
        }else if (c.matches("^[+–*/()=]"))
        {
            return 2; // unknown but valid
        }else if (Character.isWhitespace(a.charAt(index)))
        {
            return 3; //whitespace
        }
        else
            return -1; // unknown and invalid EOF
    }//end getchar

    public String addChar(String input)
    {
        String s = input;
        int count = 0;
        int arraySize = 9; // from 10 naging 9 since aaccomodate yata yun string terminator?? **slide 15
        String[] lexemeArr = new String[arraySize];
    
        for (int x=0; x<arraySize; x++)
        {
            int val = getChar(s, x);   // s is the original input
            input = input.substring(count, input.length());
            count = 0; 
            
            if (val == 2){ //if symbol na valid
                lexemeArr[x] = Character.toString(s.charAt(x)); //adds to the array
                input = input.substring(1, input.length());     //change the value of input to be read, eliminates the first char
                break;
            } 
            else if (val == 0 && getChar(s , 0) != 1)
            { //if letter 
                lexemeArr[x] = Character.toString(s.charAt(x));        
                input = input.substring(1, input.length());  
                
                if (input.length() == 0 || getChar(input , 0)== -1 || getChar(input , 0) == 3|| getChar(input , 0) == 2) //checks the first char of the new input
                { //checks if yung new input ay = sa sym (di na sa digit since pwede naman letter + digit after)
                    break;
                }  
                if (x == arraySize-1) // if the iteration is in the last letter
                {
                    String inputDel = input;
                    while (val == 0 || val == 1)
                    {
                        count ++;
                        input =  input.substring(1, input.length());
                        if (input.length() == 0 || getChar(input , 0)== -1 || getChar(input , 0) == 3|| getChar(input , 0) == 2)
                        {
                            break;
                        }
                        //System.out.println(input + count);
                        if (input.length() == 0)
                        {
                            break;
                        }
                    }
                    break;
                }
            }
            else if (val == 1 && getChar(s , 0) == 0)
            { //if digit(identifier) e.g v4r/v4
                lexemeArr[x] = Character.toString(s.charAt(x));
                input = input.substring(1, input.length());
                //System.out.println("New input: " + input);
                if (input.length() == 0 || getChar(input , 0)== -1 || getChar(input , 0) == 3|| getChar(input , 0) == 2)
                { //checks if yung new input ay = sa sym (if simula nung input ay letter: di na chineck if letter kasunod kasi pwede letterdigitletter)
                break;
                }
            }
            else if (val == 1 && getChar(s , 0) == 1)
            { //if digit e.g 22
                lexemeArr[x] = Character.toString(s.charAt(x));
                input = input.substring(1, input.length());  // deletes the first char
                //System.out.println("New input: " + input);
                if (input.length() == 0 || getChar(input , 0) != 1 )
                { //checks if yung new input ay = sa sym (ito if simula nung input ay digit naman: dapat digit lang din kasunod
                    break;
                }  
                if (x == arraySize-1) 
                {
                    String inputDel = input;
                    while (val == 1)
                    {
                        count ++;
                        input =  input.substring(1, input.length());
                        if (input.length() == 0 || getChar(input , 0) != 1){
                            break;
                        }
                        //System.out.println(input + count);
                        if (input.length() == 0){
                            break;
                        }
                    } 
                    break;
                }
            }
            else if (val == -1 || val == 3)
            { //if invalid or whitespace
                lexemeArr[x] = Character.toString(s.charAt(x)); 
                input = input.substring(1, input.length());  // deletes the first char
                break;
            } 
        }//end for


        String lexeme="";
        for (int i = 0; i< arraySize; i++)
        {
            if(lexemeArr[i]!=null)
            {
                lexeme += lexemeArr[i];   //adds the char in an array to a string
            } 
        }

        if(getChar(lexeme, 0) == 2)
        {
            System.out.print(lexeme + "\t : ");
            lookup(lexeme);
            System.out.println();
        }
        else if(getChar(lexeme, 0) == 0 || getChar(lexeme, 0) == 1 )
        {
            System.out.print(lexeme + "\t : ");
            lookup2(lexeme);
            System.out.println();
        }
        else if(getChar(lexeme, 0) == -1)
        {
            System.out.print(lexeme + "\t : ");
            lookup(lexeme);
            System.out.println();
        }
    return input;
    }//end addChar


    public void lookup (String lexeme) 
    {
        switch(lexeme){
            case "+":
                System.out.print("PLUS_OP"); 
                break;
            case "-":
                System.out.print("SUB_OP"); 
                break;
            case "*":
                System.out.print("MUL_OP");
                break;
            case "/":
                System.out.print("DIV_OP");
                break;
            case "(":
                System.out.print("L_PAREN");
                break;
            case ")":
                System.out.print("R_PAREN");
                break;
            case "=":
                System.out.print("ASSIGN_OP");
                break;
            default:
                System.out.print("EOF");
                System.exit(0);
                break;
        }//end switch
    }//end lookup

    public void lookup2 (String lexeme) 
    {
        if (lexeme.matches("^[a-zA-Z][a-zA-Z0-9]*"))
        {
            System.out.print("IDENTIFIER");
        }
        else{
            System.out.print("DIGIT");
        }
    } // end lookup2
}

public class Main
{
    public static void main(String[] args) throws Exception
    {
        lexx lexer = new lexx();
        BufferedReader bufferedreader = new BufferedReader(new FileReader("input.txt"));
        
        try                                            //error checking for reading the file                                         
        {
            String input = bufferedreader.readLine();
            System.out.println("input.txt: " + input);
            lexer.lex(input);
        }
        catch(Exception e)                             
        {
            System.out.print("No input \n");
        }

    }
}//end main
