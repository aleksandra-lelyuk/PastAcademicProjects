import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

// non recursive implementation using sliding window

public class Version2 {
	
	public static ArrayList<Integer> searchStr(String longS, String shortS){
		
		ArrayList<Integer> index = new ArrayList<>();
		//create an array that counts letters a to z lowercase
		int[] chCount = new int[26];
		
		char[] chShortS = shortS.toCharArray();
		
		//Depends on number of character in ShortS 
		for (int i = 0; i < chShortS.length; i++) {
			//calculate which cell the character goes to
			int chIndex = chShortS[i] - 'a';
			//increment the counter at that cell
			chCount[chIndex] ++;
		}
	
		int lettersRepeated = 0;
		for (int i = 0; i < chCount.length; i++) {
			if (chCount[i] > 1)
				lettersRepeated =+ chCount[i];
		}
		//System.out.println("LettersRepeated: " + lettersRepeated);
		
		int calcFactorial = 1;
		for (int i = lettersRepeated; i > 0; i-- ) {
			//System.out.println(calcFactorial + " * " + i);
			calcFactorial = calcFactorial * i;
			
		}
		
		//sliding window
		int l = 0;
		int r = 0;
		//size of string we are looking for in longS
		int count = shortS.length();
		
		
		//loop through with boundaries on longS
		
		while(r < longS.length()) {
			//if the character on the right bound in longS has an occurence in shortS
			if (chCount[longS.charAt(r) - 'a'] >= 1) {
				//remove the found letter from the character array
				chCount[longS.charAt(r) - 'a']--;
				//move right boundary right 
				r++;
				//decrease count since we found one character from the short string and now
				//want to look for the rest
				count--;
			}
			else {
				//decrement the character in the char array because that char is in our window
				chCount[longS.charAt(r) - 'a']--;
				//move the boundary to the right
				r++;
				
				
			}
			
			//found all letter from shortS in a sequence
			if (count == 0) {
				//add the index of the found permutation
				for (int i = 1; i <= calcFactorial; i++)
					index.add(l);
			}
			
			if ((r - l == shortS.length()) && chCount[longS.charAt(l) - 'a'] >= 0) {
				chCount[longS.charAt(l) - 'a']++;
				//move the left bound over so the window remains correct size
				l++;
				//increase count because we have one less character in the window now
				count++;
				
			}
			//if the window is the size of the shortS but permutation is not found
			if (r - l == shortS.length()) {
				//move the left boundary to the right and increment the character
				chCount[longS.charAt(l) - 'a']++;
				l++;
			}
			
		}
		
		return index;
		
		//looking for a window in longS that is the size of the shortS AND has the same occurence of each character
		
	}

	public static void main(String[] args) {
		
		PrintWriter runtimeOut= null;
		PrintWriter outputStream = null;
		try {
			runtimeOut = new PrintWriter(new FileOutputStream("totalsV2.txt"));
			outputStream = new PrintWriter(new FileOutputStream("outV2.txt"));
		}
		catch (FileNotFoundException e){
			System.out.println("Error opening file. Program will now exit.");
			System.exit(0);
		}
		
		String shortS = "abc";
		String longS = "hhhlajkjgabckkkkcbakkdfjknbbca";
		
		Random rnd = new Random();
		
		
		for(int i = 3; i <= 200; i++) {
			runtimeOut.println("n = " + i);
			runtimeOut.println("shortS: " + shortS);
			runtimeOut.println("longS: " + longS);
			long startTime = System.nanoTime();
			ArrayList<Integer> results = searchStr(longS, shortS);
			for (Integer index : results) {
				outputStream.println("Found one match: " + longS.substring(index, index + shortS.length()) + " is in " + longS
						+ " at location " + index);
			}
			runtimeOut.println("Permutations found: " + results.size());
			long endTime = System.nanoTime();
			runtimeOut.println("Program took "+(endTime - startTime) +  " nanoseconds\n");
			if (i <= 6)
				shortS += 'k';
			else {
				char c = (char) (rnd.nextInt(26) + 'a');
				shortS += c;
				
			}
			
			char c = (char) (rnd.nextInt(26) + 'a');
			longS += c;
			
			
			
		}
		
		outputStream.close();
		runtimeOut.close();

	}

}
