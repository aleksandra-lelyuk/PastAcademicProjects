import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class V2 {
	
	//method to calculate y to the power of x
	//nlogn
	private static double power(double y, int x) {
		
		if(x == 0)
			return 1;
		else {
			double partial = power(y, x/2);
			double result = partial * partial;
			if (x % 2 == 1)
				result *= y;
			return result;
		}
	}
	
	//method to calculate x!
	//O(n)
	private static int factorial(int x) throws IllegalArgumentException {
		if (x < 0)
			throw new IllegalArgumentException();
		else if (x == 0)
			return 1;
		else
			return x * factorial(x-1);
		
	}
	
	//method defining precedence of operators
	//O(1)
	private static int prec(String op) {
		if (op.equals("(") || op.equals(")"))
			return 1;
		else if (op.equals("!"))
			return 2;
		else if (op.equals("^"))
			return 4;
		else if (op.equals("*")|| op.equals("/"))
			return 5;
		else if (op.equals("+") || op.equals("-"))
			return 6;
		else if (op.equals("<=") || op.equals("<") || op.equals(">=") || op.equals(">"))
			return 7;
		else if (op.equals("==") || op.equals("!="))
			return 8;
		else 
			return -1; //unknown symbol
	}
	
	//O(1)
	//method deterining whether a string is an operator
	private static boolean isOp(String str) {
		return (prec(str) >= 0 );
	}
	
	
	//method performing arithmetic operation basec on the provided operator and operands
	//O(1)
	private static double operation(double y, double x, String op) {
		
		if (prec(op) == 4) {
			//cast to int so power is an int
			return (power(y, (int)x));
		}
		else if (prec(op) == 5) {
			if(op.equals("*"))
				return (y * x);
			else if(op.equals("/"))
				return (y/x);
		}
		else if (prec(op) == 6) {
			if(op.equals("+"))
				return (y + x);
			else if(op.equals("-"))
				return (y - x);
		}
		else if(prec(op) == 7) {
			if(op.equals(">"))
				return (y > x ? 1 : 0);
			else if(op.equals(">="))
				return (y >= x ? 1 : 0);
			if(op.equals("<"))
				return (y < x ? 1 : 0);
			else if(op.equals("<="))
				return (y <= x ? 1 : 0);
		}
		else if(prec(op) == 8) {
			if(op.equals("=="))
				return (y == x ? 1 : 0);
			else if(op.equals("!="))
				return (y != x ? 1 : 0);
		}
		//in case operation not found --> maybe change to an error 
		return 0;
	}
	
	
	//method evaluating an arithmetic expression dealing with one operator at a time
	private static double evaluate(String str) {
		
		//System.out.println("Sting passed: " + str);
		
		//O(n)
		//change all unary - to unique $
		for (int i = 0;i < str.length(); i++) {
			if (str.charAt(i) == '-') {
				if (i == 0) {
					str = "$" + str.substring(1);
					continue;
				}
				if (i > 1 && isOp(str.substring(i-2, i))){
					str = str.substring(0, i) + "$" + str.substring(i + 1);
					continue;
				}
				if (isOp(str.substring(i-1, i)) && prec(str.substring(i-1, i)) != 2){
					str = str.substring(0, i) + "$" + str.substring(i+1);
					continue;
				}
				
			}
		}
		
		//O(n)
		if (!str.contains("+") && !str.contains("-") && !str.contains("*") && !str.contains("/")
				&& !str.contains("^") && !str.contains("<") && !str.contains(">")
				&& !str.contains(">=") && !str.contains("<=") 
				&& !str.contains("(") && !str.contains(")") && !str.contains("!=") && !str.contains("==")) {
			if (str.contains("!")) {
				if(str.contains("$")) {
					return evaluate( "$" + factorial((int)Double.parseDouble(str.substring(1, str.length()-1))));
				}
				else {
					return factorial((int)Double.parseDouble(str.substring(0, str.length()-1)));
				}
				
			}
			if(str.contains("$")) {
				return Double.parseDouble(str.substring(1, str.length())) * -1;
			}
			else {
				return Double.parseDouble(str);
			}
				
	    }
		
		
		String symbol = "";
		int i;
		
		//detect != and ==
		//O(n)
		for (i = str.length()-1; i > 0; i--) {
			symbol = Character.toString(str.charAt(i));
			
			if(prec(str.charAt(i-1)+ symbol ) == 8) {
				symbol = str.charAt(i-1)+ symbol;
				break;
			}
		}
		
		//detect inequality operators
		//O(n)
		if (i <= 0) {
			for (i = str.length()-1; i > 0; i--) {
				symbol = Character.toString(str.charAt(i));
				if(prec(str.charAt(i-1)+ symbol ) == 7 ) {
					symbol = str.charAt(i-1)+ symbol;
					break;
				}
				if(prec(symbol) == 7) {
					break;
				}
			}
			
		}
		
		
		// detect + and -
		//O(n)
		if (i <= 0) {
			for (i = str.length()-1; i >= 0; i--) {
				symbol = Character.toString(str.charAt(i));
				
				//skip the unary --
				if(prec(symbol) == 6) {
					break;
				}
					
			}
		}
		
		//detect * and /
		//O(n)
		if (i < 0) {
			for (i = str.length()-1; i >= 0; i--) {
				symbol = Character.toString(str.charAt(i));
				
				if(prec(symbol) == 5)
					break;
			}
		}
		// detect ^
		//O(n)
		if (i < 0) {
			for (i = str.length()-1; i >= 0; i--) {
				symbol = Character.toString(str.charAt(i));
				
				if(prec(symbol) == 4)
					break;
			}
		}
		
		
		
		//parts before and after found operator
		String p1 = "";
		String p2 = "";
		
		
		
		//case for 1 digit symbol other than !
		if (symbol.length() == 1) {
			p1 = str.substring(0, i);
			p2 = str.substring(i+1, str.length());
		}
		
		//case for 2 digit symbol
		else if (symbol.length() == 2) {
			p1 = str.substring(0, i-1);
			p2 = str.substring(i+2, str.length());
		}
		
		//System.out.println("num1: " + p1 + " num2: " + p2 + " op: " + symbol);
		
		double result;
		
		//each call results in 2 calls to method, depends on the number of operators
		//if all low precedence, 2n calls will be made 
		//O(n)
		result = operation(evaluate(p1), evaluate(p2), symbol);
		
		
		return result;
	}
	

	//method to correctly use the brackets
	public static double solveBrackets(String str) {
			
			int openI;
			int opencount = -1;
			double temp;
			
			String newS = "";
			//space complexity O(n) because this array is created
			//array to store open brackets
			int[] open = new int[str.length()/2];
			
			char symbol;
			
			
			//O(n)
			for (int i = 0; i < str.length(); i++) {
				symbol = (str.charAt(i));
				//find open bracket and save its index into array
				if (symbol == '(') {
					openI = i;
					opencount++;
					open[opencount] = openI; 
					
				}
				//if close bracket found, evaluate the expression starting from the last open bracket index
				if (symbol == ')') {
					newS = str.substring(open[opencount]+1, i);
					temp = evaluate(newS);
					str = str.substring(0, open[opencount]) + temp + str.substring(i+1, str.length());
					i = open[opencount];
					opencount--;
				}
			}
			return evaluate(str);
		}
	
	public static void main(String[] args) {
		
		
		
		Scanner inputStream = null;
		PrintWriter outputStream = null ;
		try {
			inputStream = new Scanner(new FileInputStream("arithmetic.txt"));
			outputStream = new PrintWriter(new FileOutputStream("outV2.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error opening file outV2.txt.");
			System.exit(0);
		}
		
		
		while(inputStream.hasNextLine()) {
			String toSolve = inputStream.nextLine();
			System.out.println("Solving...: " + toSolve);
			
			boolean boolAnswer = false;
			if (toSolve.contains("<") || toSolve.contains(">")
					|| toSolve.contains(">=") || toSolve.contains("<=") 
					|| toSolve.contains("!=") || toSolve.contains("==")){
						boolAnswer = true;
					}
			
			double result = solveBrackets(toSolve);
			
			if (boolAnswer) {
				if (result > 0) {
					outputStream.println(toSolve + "\n" + result + "-->" + "True" + "\n");
				}
				else {
					outputStream.println(toSolve + "\n" + result + "-->" + "False" + "\n");
				}
			}
			else { 
				outputStream.println(toSolve + "\n" + result + "\n");
			}
			
		}
		
		outputStream.close();
		inputStream.close();
	}

}
