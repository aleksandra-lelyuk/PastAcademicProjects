import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class V1 {
	
	
	
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
	//O(n)
	private static int factorial(int x) throws IllegalArgumentException {
		if (x < 0)
			throw new IllegalArgumentException();
		else if (x == 0)
			return 1;
		else
			return x * factorial(x-1);
		
	}
	
	//O(1)
	//1 is the highest precedence
	private static int prec(String op) {
		if (op.equals("(") || op.equals(")"))
			return 1;
		else if (op.equals("!"))
			return 2;
		else if (op.equals("--"))
			return 3;
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
		else if (op.equals("$"))
			return 9;
		else 
			return -1; //unknown symbol
	}
	
	//O(1)
	private static boolean isOp(String str) {
		return (prec(str) >= 0 );
	}
	
	
	//all Stack ADT methods take O(1) --> implemented with linkedlist
	private static void doOp(LinkedStack<String> opStk, LinkedStack<Double> valStk) {
		Double x = valStk.pop();
		Double y = valStk.pop();
		String op = (String)opStk.pop();
		valStk.push(operation(y, x, op));
	}
	
	
	//
	private static double operation(double y, double x, String op) {
		 
		//do the opation for each opStk
		if(op.equals("--")) {
			return y - x;
		}
		else if (prec(op) == 4) {
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
	
	private static void repeatOps(String op, LinkedStack<String> opStk, LinkedStack<Double> valStk) {
		//perform higher and equal precedence operations
	
		
		if (op.equals(")")){
			do {
				doOp(opStk, valStk);
			}
			while (!opStk.top().equals("("));
			opStk.pop();
		}
		else if (prec(op) == 2) {
			int num = valStk.pop().intValue();
			valStk.push((double) factorial(num));
		}
			
		else {
			while(opStk.size() >= 1 && prec(op) >= prec(opStk.top()) && !opStk.top().equals("(")) {
				doOp(opStk, valStk);
			}
		}
	}
	
	public static double EvalExp(LinkedStack<String> opStk, LinkedStack<Double> valStk, String str) {
		String arithm = str;
		
		boolean unary = true;
		String num = "";
		
		//O(n)
		for (int i = 0; i < arithm.length() ; i++) {
			
			String symbol = Character.toString(arithm.charAt(i));
			
			
			//if current char not an operator
			if(!isOp(symbol) && !symbol.equals("=")) {
				// add digit to number string
				num += arithm.charAt(i);
				if (i == arithm.length()-1) {
					valStk.push(Double.parseDouble(num));
					num = "";
				}
			}
			else {
				//if there is a number in the string before the operator 
				if (num.length() > 0) {
					valStk.push(Double.parseDouble(num));
					num = "";
				}
				else if (symbol.equals("-") && unary) {
					valStk.push(0.0);
					//switch to special notation to unary -
					symbol = "--";
				}
				
				if (symbol.equals("(")) {
					opStk.push(symbol);
					unary = true;
					continue;
				}
				if (symbol.equals(")")) {
					repeatOps(symbol, opStk, valStk);
					unary = false;
					continue;
				}
				
				
					
				//reset num
				
				if (i < arithm.length()-1 && isOp(symbol + arithm.charAt(i+1)) && !isOp(arithm.substring(i+1, i+3))) {
					repeatOps(symbol + arithm.charAt(i+1), opStk, valStk);
					opStk.push(symbol + arithm.charAt(i+1));
					i++;
				}
				
				else if (isOp(symbol)){
					if (prec(symbol) == 2) {
						repeatOps(symbol, opStk, valStk);
					}
					else {
						repeatOps(symbol, opStk, valStk);
						opStk.push(symbol);
					}
					
				}
			}
		}
		repeatOps("$", opStk, valStk);
		return valStk.top();
	}
	
	
	public static void main(String[] args) {
		
		Scanner inputStream = null;
		PrintWriter outputStream = null ;
		try {
			inputStream = new Scanner(new FileInputStream("arithmetic.txt"));
			outputStream = new PrintWriter(new FileOutputStream("outV1.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error opening file outV1.txt.");
			System.exit(0);
		}
		
		LinkedStack<String> opStk = new LinkedStack<String>();
		LinkedStack<Double> valStk = new LinkedStack<Double>();
		
		String toSolve = "";
		//double result = EvalExp(opStk, valStk, arithm);
		
		
		//System.out.println(result);
		
		while(inputStream.hasNextLine()) {
			toSolve = inputStream.nextLine();
			System.out.println("Solving...: " + toSolve);
			
			double result = EvalExp(opStk, valStk, toSolve);
			
			
			boolean boolAnswer = false;
			if (toSolve.contains("<") || toSolve.contains(">")
					|| toSolve.contains(">=") || toSolve.contains("<=") 
					|| toSolve.contains("!=") || toSolve.contains("==")){
						boolAnswer = true;
					}
			

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
