import ListPkg.*;
import ExceptionPkg.EmptyListException;
import java.util.Arrays;
import java.util.Scanner;

public class InfixToPostfix {
    public StringBuffer convertToPostfix()
    {

        // declare vars
        StringBuffer postfix = new StringBuffer("");
        StringBuffer infix = new StringBuffer("");
        String input = null;
        char c, operator;

        // the stack of operators
        Stack stack = new Stack();

        // scanner for user input
        Scanner s = new Scanner(System.in);

        System.out.println("Enter an expression in infix notation for evaluation: ");
        input = s.nextLine() + " )";

        // convert input into infix expression
        infix = new StringBuffer(input);

        // assign "(" to postfix
        stack.push('(');
   
        // process input expression
        for(int i = 0; i < infix.length(); i++) {
            // and let c be
            c = infix.charAt(i);

            // operands
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                postfix.append(" ").append(c);
   
            } else if (c == ' ') {
                // do nothing
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                // if token is an operator

                while (!stack.isEmpty() && !hasPrecedence(c, (char) stack.peek()) && (char) stack.peek() != '('
                        && (char) stack.peek() != ')') 
                {
                    // (Operator on the stack does not have lower precedence, so it goes before this one.)
                    postfix.append(" ").append((char) stack.pop());
                
                }    
            stack.push(c);

            } else if (c == ')') {
                // Output the remaining operators in the parenthesized part.
                operator = ' ';

                while(!stack.isEmpty() && (char) stack.peek() != ')'&& (char) stack.peek() != '(') {
                    operator = (char)stack.pop();
                    postfix.append(" ").append(operator);
                }

               if(!stack.isEmpty()){
                   stack.pop();
               }else{
                    System.out.println("*** Unmatched parenthesis in expression ***");
                    postfix = null;
                    break;
               }            
    
            } else if (c == '('){
                stack.push(c);// Push this operator onto the stack.
            } else {
                System.out.println("Invalid operator");
            }
        }
    

        // Output the remaining operators on the stack        
       
        if (!stack.isEmpty()) {
            System.out.println("*** Unmatched parenthesis in expression ***");
            postfix = null; 
        }
        
        return postfix;
    }

    // Define what input, output, isOperator,etc.

    public boolean evaluatePostfix (StringBuffer postfix) {
        // the stack of operators
        Stack stack = new Stack();
        double result = 0.0;
        char c = postfix.charAt(0);
        int pos = 0;
        
                
        for (int i = 0; i < postfix.length(); i++) {
            c = postfix.charAt(pos);	

            // Scanning each character from left. 
            // If character is a space or delimiter, move on. 
            if(c == ' ') {
                // do nothing
            } else if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' 
                    || c == '6' || c == '7' || c == '8' || c == '9') 
            {   
                // if the character is a digit
                Double value = (double) Character.digit(c, 10);
                stack.push(value);

            } else if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^'){ //isOperator
                try {

                    double right = (double)stack.pop();
                    double left = (double)stack.pop();

                    if(c == '+')
                    {
                        result = left + right;
                    }else if(c == '-')
                    {
                        result = left - right;
                    }else if(c== '*')
                    {
                        result = left * right;
                    }else if(c == '/')
                    {
                        if (right == 0.0) {
                            System.out.println("*** Divide by zero attempted ***");
                            System.out.println("*** Aborting expression evaluation ***");
                            return false;
                        } else {
                            result = left / right;
                        }
                    }else if(c == '^'){
                        result = Math.pow(left, right);
                    }
                    
                    stack.push(result);
                    //System.out.println(result);
                    
                } catch(Exception e){
                    System.out.println("Invalid number of operands");
                    return false;					
                }
            } else {
                System.out.println("*** Invalid operator: use + - * / ^ only ***");
                System.out.println("*** Aborting expression evaluation ***");
                return false;
            }
            
            // increment pos
            pos++;
        }
        int here = 0;
        try {
            result = (double) stack.pop();

            if (stack.isEmpty()){
                System.out.println("=====================================");
                System.out.println("\tResult = " + result);
                System.out.println("=====================================");
                return true;
            } else if(!stack.isEmpty()){
                System.out.println("*** Error in Expression: Invalid number of operators or operands input ***");
                System.out.println("*** Aborting expression evaluation ***");
                return false;
            }
        } catch (Exception e) {
            System.out.println("*** Invalid number of operands or operands ***");
            System.out.println("*** Aborting expression evaluation ***");
            return false;
        } finally {
            postfix.setLength(0);
            stack = null;
        }
        
        return true;
    }

    public boolean hasPrecedence (char op1, char op2) {
        switch (op1) {
            case '+':
            case '-':
                return false; 
            case '*':
            case '/':
                if (op2 =='+' || op2=='-')
                    return true;
                else 
                    return false;
            case '^':
                if (op2=='+' || op2 == '-' || op2=='*' || op2 == '/')
                    return true;
                else return false;

            case '(':
                if(op2 != '(')
                    return true;
                else return false;
            default:
                return false;
        }
    }
}


