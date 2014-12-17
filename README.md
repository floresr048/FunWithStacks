FunWithStacks
=============
This project was an assignment. It tested my ability to write a program that uses the concept of stack to analyze arithmetic 
expressions with the following operators (+,-,*,/,%).

The program executes as follows:

First, it verifies that the given arithmetic expression is properly organized as far as parentheses are concerned. Paranthetic 
organization is conventional. An empty stack is created to only hold left parentheses, then the expression is scanned from left 
to right. If a left parenthesis is found it is pushed into the stack. When a right parenthesis is found the top element of the 
stack is popped. If the stack still contains an element then the expression was not properly organized.

Valid parenthesizations:
(  )
{  }
[  ]
[ ( ) ]
{  (  )  }
[  {  (  )  }  ]

Second, it will convert the expression to a postfix form. It is done by continually printing operands to a string, and pushing
left parentheses and operators into the stack until a right parentheses is scanned, then all elements are popped and printed to
the string. Precedence of the operators are taken into account.

Lastly, the postfix expresssion is analyzed. This is where the operands are calculated with their appropriate operators. Stack
is still being used.
