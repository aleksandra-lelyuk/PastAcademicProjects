import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

//recursive implementation of permutation finder

public class Version1 {
	
	//finds short string permutations in longer string
	public static int permu(String perm , String shortS,  String longS, PrintWriter output, int counter) {
		//System.out.println("recursion!");
		if (shortS.length() == 0) {
			output.println(perm);
			if(SearchLongS(longS, perm, output))
				counter++;
		}
		for (int i = 0; i< shortS.length();i++) { 
			perm += shortS.charAt(i);
			//remove the letter added to the permutation from the shortS
			String newS = shortS.substring(0, i) + shortS.substring(i+1);
			//permutate the rest of the characters in shortS
			counter = permu(perm, newS,  longS, output, counter);
			//remove the new letter from permutation
			perm = perm.substring(0, perm.length()-1); 
		}
		
		return counter;
	}
	
	public static boolean SearchLongS(String longS, String shortS, PrintWriter output) {
		boolean found = false;
		if (longS.indexOf(shortS) >= 0 ) {
			output.println("Found one match: " + shortS + " is in " + longS
					+ " at location " + longS.indexOf(shortS));
			found = true;
		}
		return found;
	}

	public static void main(String[] args) {
	
		PrintWriter runtimeOut= null;
		PrintWriter outputStream = null;
		try {
			runtimeOut = new PrintWriter(new FileOutputStream("totalsV1.txt"));
			outputStream = new PrintWriter(new FileOutputStream("outV1.txt"));
		}
		catch (FileNotFoundException e){
			System.out.println("Error opening file. Program will now exit.");
			System.exit(0);
		}
		
		String shortS = "abc";
		String longS = "hhhlajkjgabckkkkcbakkdfjknbbca";
		
		Random rnd = new Random();
		
		
		for(int i = 3; i <= 12; i++) {
			runtimeOut.println("n = " + i);
			runtimeOut.println("shortS: " + shortS);
			runtimeOut.println("longS: " + longS);
			long startTime = System.currentTimeMillis();
			int foundcount = permu("", shortS, longS, outputStream, 0);
			runtimeOut.println("Permutations found: " + foundcount);
			long endTime = System.currentTimeMillis();
			runtimeOut.println("Program took "+(endTime - startTime) +  " milliseconds\n");
			if (i <= 6)
				shortS += 'k';
			else {
				char c = (char) (rnd.nextInt(26) + 'a');
				shortS += c;
			}
		}
		
		outputStream.close();
		runtimeOut.close();

	}

}
