import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author floresr048
 */
interface Constants
{
    char LEFT_NORMAL = '(';
    char RIGHT_NORMAL = ')';
    char RIGHT_CURLY = '}';
    char RIGHT_BRACK = ']';
    char LEFT_CURLY = '{';
    char LEFT_BRACK = '[';
}

public class ArithmeticStackPlay {
    
    Stack stk,stkstk;
    String expression,
            postfix = "";
    int length;
    int result;
    
    ArithmeticStackPlay(String s)
    {
        expression = s;
        length = expression.length();
        stk = new Stack<Object>();
    }
    //Checks whether the parentheses are balanced
    boolean isBalance()
    {
        int index = 0;
        boolean fail = false;
        
        try
        {
            while (index < length)
            {
                char ch = expression.charAt(index);
                
                switch(ch)
                {
                    case Constants.LEFT_NORMAL:
                        stk.push(ch); 
                        break;
                    case Constants.LEFT_CURLY:
                        if (stk.peek().equals('('))
                        {
                            fail = false;
                        }
                        stk.push(ch); 
                        break;
                     case Constants.LEFT_BRACK:
                         if (stk.peek().equals('(') || stk.peek().equals('{'))
                        {
                            fail = false;
                        }
                        stk.push(ch); 
                        break;
                    case Constants.RIGHT_NORMAL:
                        if (ch == Constants.RIGHT_NORMAL)
                        {
                            if(stk.peek().equals('('))
                            {
                                stk.pop();
                            }
                            else if (stk.peek().equals('[') || stk.peek().equals('{'))
                            {
                                fail = false;
                            }
                            else {
                                fail = false;
                            }
                        }
                        break;
                    case Constants.RIGHT_CURLY:
                        if (ch == Constants.RIGHT_CURLY)
                        {
                            if(stk.peek().equals('{'))
                            {
                                stk.pop();
                            }
                            else if (stk.peek().equals('['))
                                    {
                                        fail = false;
                                    }
                            else {
                                fail = false;
                            }
                        }                       
                        break;
                    case Constants.RIGHT_BRACK:
                        if (ch == Constants.RIGHT_BRACK)
                        {
                            if(stk.peek().equals('['))
                            {
                                stk.pop();
                            }
                            
                            else {
                                fail = false;
                            }
                        }                       
                        break;
                        
                    default:

                        break;
                }
                index++;
            }
        }
    catch(EmptyStackException e)
    {
        fail = true;
    }
    return stk.empty() && !fail; 
    }
    
    void postfixExpression() //Converts the expression to a postfix notation
    {
        Scanner scan = new Scanner(expression);
        char current;
        
        while (scan.hasNext())
        {
            String token = scan.next();
            
            
            if (isNumber(token))
            {
                        postfix = postfix + token + " ";
            }
            else 
            { 
                current = token.charAt(0);
                
                if(isParantheses(current)) 
                {
                if(stk.empty() || isLeftParantheses(current))
                {
                   stk.push(new Character(current));
                   
                }
                else if (isRightParantheses(current))
                {
                    try
                    {
                        Character topmost = (Character)stk.pop();
                        char top = topmost.charValue();
                        
                        while (!isParantheses(top))
                        {
                            postfix = postfix + top + " ";
                            top = (Character)stk.pop();
                        }
                    }
                    catch(EmptyStackException e)
                    {
                    }
                }
            }
            else if (isOperator(current))
            {
                if(stk.empty())
                {
                    stk.push(new Character(current));
                }
                else {
                    try
                    {
                        char top = (Character)stk.peek();
                        boolean higher = hasHigherPrecedence(top, current);
                        
                        while (!isParantheses(top) && higher)
                        {
                            postfix = postfix + stk.pop() + " ";
                            top = (Character)stk.peek();
                        }
                        stk.push(new Character(current));
                    }
                    catch(EmptyStackException e)
                    {
                        stk.push(new Character(current));
                    }
                }
            }
            }
        }
    try
    {
        while(!stk.empty())
        {
            Character yoyo = (Character)stk.pop();
                        char yo = yoyo.charValue();
                        postfix = postfix + yo + " ";
        }
            
    }
    
    catch(EmptyStackException e)
    {
    }
    }
    
//Determines if the given string element can be converted to a number    
boolean isNumber(String str)
{ 
  boolean isTrue = false;
   
    try
    {
        int newInt = 0;
        newInt = Integer.parseInt(str);
        isTrue = true;
    }
    catch(NumberFormatException e)
            {
              isTrue = false;
            }
    
    return isTrue;
}

boolean isParantheses(char current)
{
//Determines if the given character is a left prenthesis 
//or a right paranthesis
    boolean isTrue = false;
    if (current == Constants.LEFT_BRACK || current == Constants.LEFT_CURLY || current == Constants.LEFT_NORMAL || current == Constants.RIGHT_BRACK || current == Constants.RIGHT_CURLY || current == Constants.RIGHT_NORMAL )
    {
        isTrue = true;
    }
    return isTrue;
}

boolean isLeftParantheses(char current)
{
//Determines if the given character is a left prenthesis 

    boolean isTrue = false;
    if (current == Constants.LEFT_BRACK || current == Constants.LEFT_CURLY || current == Constants.LEFT_NORMAL)
    {
        isTrue = true;
    }
    return isTrue;
}

boolean isRightParantheses(char current)
{
//Determines if the given character is a right prenthesis 

    boolean isTrue = false;
    if (current == Constants.RIGHT_BRACK || current == Constants.RIGHT_CURLY || current == Constants.RIGHT_NORMAL)
    {
        isTrue = true;
    }
    return isTrue;
}

boolean isOperator(char ch)
{
    boolean isTrue = false;
    if(ch == '+' || ch =='-' || ch =='*' || ch=='/' || ch=='%')
    {
        isTrue = true;
    }
    return isTrue;
}

boolean isOperatorz(String f)
{
    boolean isTrue = false;
    if(f.contains("+") || f.contains("-") || f.contains("%") || f.contains("*") || f.contains("/"))
    {
        isTrue = true;
    }
    return isTrue;
}
boolean hasHigherPrecedence(char top, char current)
{
//Determines which of the two characters has higher preedence
    boolean isTrue = false;
    int topValue = 0;
    int currValue = 0;
    
    if (top == Constants.LEFT_NORMAL || top == Constants.RIGHT_NORMAL)
    {
        topValue = 1;
    }
    if (current == Constants.LEFT_NORMAL || current == Constants.RIGHT_NORMAL)
    {
        currValue = 1;
    }
    if (top == Constants.LEFT_BRACK || top == Constants.RIGHT_BRACK)
    {
        topValue = 2;
    }
   if (current == Constants.LEFT_BRACK || current == Constants.RIGHT_BRACK)
    {
        currValue = 2;
    }
   if (top == Constants.LEFT_CURLY || top == Constants.RIGHT_CURLY)
    {
        topValue = 3;
    }
   if (current == Constants.LEFT_CURLY || current == Constants.RIGHT_CURLY)
    {
        currValue = 3;
    }
   if (top == '+' || top == '-')
   {
       topValue = 4;
   }
   if (current == '+' || current == '-')
   {
       currValue = 4;
   }
   if (top == '*' || top == '/' || top == '%')
   {
       topValue = 5;
   }
   if (current == '*' || current == '/' || current == '%')
   {
       currValue = 5;
   }
   if (topValue >= currValue)
   {
       isTrue = true;
   }
   return isTrue;
}

void doMath() 
{
    Scanner scanscan = new Scanner(postfix);
    stkstk = new Stack<Object>();
    char prettyPls;
    
    try {
    while (scanscan.hasNext())
    {
        String numPls = scanscan.next();
        prettyPls = numPls.charAt(0);
        
       if (isNumber(numPls)) 
       {
           int lol = Integer.parseInt(numPls);
           stkstk.push(lol);
       }
      else if (isOperator(prettyPls))
       {
               
           int value1 = (Integer)stkstk.pop();
           int value2 = (Integer) stkstk.pop();
           int resultz = doMoreMath(value2,prettyPls,value1);
           stkstk.push(resultz);
       
           
       }
     } int theResult = (Integer)stkstk.pop();
     result = theResult;
}  
    catch(EmptyStackException e)
           {
            System.out.println("Stack Empty!");
           }
    if (!stkstk.empty())
    {
        while(!stkstk.empty())
        {
            stkstk.pop();
        } 
    }
    
}

int doMoreMath(int a, char op, int b)
{
    int resultPls = 0;
    if(op == '+')
    {
        resultPls = a + b;
    }
    if (op == '-')
    {
        resultPls = a + (-b);
    }
    if (op == '*')
    {
        resultPls = a * b;
    }
    if (op == '/')
    {
        resultPls = a / b;
    }
    if (op == '%')
    {
        resultPls = a % b;
    }
    return resultPls;
}

String getPostfix()
{
return postfix;
}
int getResult()
{
    return result;
}
}