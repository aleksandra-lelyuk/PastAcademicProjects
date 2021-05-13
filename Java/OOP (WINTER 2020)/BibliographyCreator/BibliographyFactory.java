package ProjectFiles;

/**
 * <p> Driver class implementing the Article class that allows for creation of the objects 
 * containing attribute of journal article information needed for citation creation,
 * including methods for outputting citations in specific formats (IEEE, ACM, Nature). </p>
 * <p> The driver program processes exactly 10 .bib files (titled Latext1 through Latex10) containing article information
 * in JSON format. If the input files are valid, i.e. no attribute-value pairs are blank,
 * the program creates 3 .json output files of the three citation formats per each input file.
 * The program allows the user to access and view one created output file after the creation
 * process is complete.</p>
 * 
 * 
 * 
 *
 */


import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class BibliographyFactory {
	
	/**
	 * Static method allowing to process text files in JSON format containing journal article
	 * information. The method ensures that the file is valid, i.e. no data fields are blank, and
	 * creates an object of type Article using the provided data fields. The method prints citation
	 * in three different formats into three different files utilizing three provided output streams.
	 * Closes the input and output streams associated with the current file at completion.
	 * @param inputStream The input stream connected to a text file containing journal article info in 
	 * JSON format.
	 * @param IEEE An output stream connected to a file used for recording citation in IEEE format.
	 * @param ACM An output stream connected to a file used for recording citation in ACM format.
	 * @param NJ An output stream connected to a file used for recording citation in Nature Journal format.
	 * @param fileNum The number of the file being processed.
	 * @param invalidNum The number of files that have been judged invalid so far.
	 * @return The updated number of files that have been judged invalid so far.
	 * @see Article
	 */
	public static int processFilesForValidation(Scanner inputStream, PrintWriter IEEE, PrintWriter ACM, 
			PrintWriter NJ, int fileNum, int invalidNum) {
		
		
		String outFileEnding = (fileNum + ".json");
		
		try {
			
			
			String next = "";
			String errField = "";
			boolean newArticle = false;
			Article toWrite = new Article();
			int ACMnum = 0;
			
			
			
			do
			{
				newArticle = false;
				String authIn = "";
				String[] authArray;
				
				//Read file and parse the information as attribute to the object type Article
				do {
					next = inputStream.nextLine();
					if (next.indexOf("{}")!= -1) {
						errField = next.substring(0, (next.indexOf("{}") - 1));
						throw new FileInvalidException("Latex" + fileNum + ".bib" + "\nFile is Invalid: Field \"" + errField);
					}
					if (next.indexOf("author=")!= -1) {
						authIn = (next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						authArray = authIn.split(" and ");
						for (int i = 0; i < authArray.length; i++) {
							
						}
						toWrite.setAuthor(authArray);
						
					}
					
					if (next.indexOf("journal=")!= -1) {
						toWrite.setJournal(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("title=")!= -1) {
						toWrite.setTitle(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("year=")!= -1) {
						toWrite.setYear(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("volume=")!= -1) {
						toWrite.setVolume(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("number=")!= -1) {
						toWrite.setNumber(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("pages=")!= -1) {
						toWrite.setPages(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("doi=")!= -1) {
						toWrite.setDoi(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("ISSN=")!= -1) {
						toWrite.setIssn(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
						
					}
					
					if (next.indexOf("month=")!= -1) {
						toWrite.setMonth(next.substring((next.indexOf("{") + 1), next.indexOf("}")));
					}
					
					if (next.indexOf("}") == 0) {
						IEEE.println(toWrite.formatIEEE());
						
						ACMnum++;
						ACM.println("[" + ACMnum + "]    " + toWrite.formatACM());
						
						NJ.println(toWrite.formatNature());
						
						newArticle = true;
					} 
				}
				while (!newArticle && inputStream.hasNext());
			}
			while(inputStream.hasNext());
		}
		//Throw an error if a file is judged invalid, i.e. if any blank data fields are encountered.
		catch(FileInvalidException e) {
			System.out.println("\nError: Detected Empty Field!\n" 
					+  "============================\n" + "Problem detected with input file: "
					+ e.getMessage() + "\" is Empty. "
					+ "Processing stopped at this point. Other empty fields may be present as well.\n");
			
			invalidNum++;
			
			//delete the so far written file here
			File directory = new File(".");		
			File latexFiles[] = directory.listFiles((d, name) -> name.toLowerCase().endsWith(outFileEnding));
			for (File f: latexFiles) {
				f.delete();
			}
		}
		//close the streams for the current file
		finally {
			inputStream.close();
			IEEE.close();
			ACM.close();
			NJ.close();
		}
	
		return invalidNum;
	
	}
	
	public static void main(String[] args) {
		
		//Create input streams for reading 10 provided files
		Scanner[] inputStreams = new Scanner[10];
		
		//keep track of files opened for numbering and file retrieval purposes
		int filesopened = 1;
		
		try
		{
			
			for (int i = 0; i < inputStreams.length; i++) {
				inputStreams[i] = new Scanner (new FileInputStream("Latex" + filesopened + ".bib"));
				filesopened++;
			}
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println("Could not open input file Latex" + (filesopened) + ".bib for reading." + "\n"
					+ "Please check if file exists! Program will terminate after closing any opened files.”");
			
			for (int i = 0; i < inputStreams.length; i++) {
				inputStreams[i].close();
			}
			
			System.exit(0);
		}
		
		//Create output stream arrays creating 10 files for each citation format
		filesopened = 1;
		String outType = "IEEE";
		PrintWriter[] IEEEout = new PrintWriter[10];
		PrintWriter[] ACMout = new PrintWriter[10];
		PrintWriter[] NJout = new PrintWriter[10];
		
		try {
			
			for (int i = 0; i < 10; i++) {
				IEEEout[i] = new PrintWriter (new FileOutputStream(outType + filesopened + ".json"));
				filesopened++;
			}
			
			filesopened = 1;
			outType = "ACM";
			
			for (int i = 0; i < 10; i++) {
				ACMout[i] = new PrintWriter (new FileOutputStream(outType + filesopened + ".json"));
				filesopened++;
			}
	
			filesopened = 1;
			outType = "NJ";
			
			for (int i = 0; i < 10; i++) {
				NJout[i] = new PrintWriter (new FileOutputStream(outType + filesopened + ".json"));
				filesopened++;
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Problem opening/creating " + outType + filesopened + ".json file."
					+ "All of the created files will be deleted and the program will exit.");
			
			//delete all other output files
			File directory = new File(".");		
			File latexFiles[] = directory.listFiles((d, name) -> name.toLowerCase().endsWith(".json"));
			for (File f: latexFiles) {
				f.delete();
			}
			
			//close open files
			
			for (int i = 0; i < inputStreams.length; i++) {
				inputStreams[i].close();
				IEEEout[i].close();
				ACMout[i].close();
				NJout[i].close();
				
			}

			System.exit(0);
		}
		
		
		//keep track of files judged invalid
		int invalid = 0;
		
		//process each file with an established input stream
		for (int i = 0; i < 10; i++) {
			invalid = processFilesForValidation(inputStreams[i], IEEEout[i], ACMout[i], 
					NJout[i], i+1, invalid);
		}
		
		System.out.println("A total of " + invalid + " files were invalid and could not be processed."
				+ " All other " + (10-invalid) + " \"valid\" files have been created.\n");
		
		//Allow the user to review one of the file that were jsut created by entering appropriate name
		Scanner inKey = new Scanner(System.in);
		System.out.print("Please enter the name of one of the files that you need to review: ");
		String fNameIn = inKey.next();
		BufferedReader openF;
		
		//try to read the file if a file with the provided name exists
		try
		{
			
			openF = new BufferedReader(new FileReader(fNameIn));
			//read the file and print the contents to the screen
			System.out.println("\nHere are the concents of the successfully created JSON file " + fNameIn + ":\n");
			String line = openF.readLine();
			while (line != null) {
				System.out.println(line);
				line = openF.readLine();
			}
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println("Could not open input file. File does not exist;"
					+ "possibly it could not be created!" + "\n\n"
					+ "However, you will be allowed another chance to enter another file name.\n");
			
			System.out.print("Final attempt: Please enter the name of one of the files that you need to review: ");
			fNameIn = inKey.next();
			
			//second chance
			try {
				openF = new BufferedReader(new FileReader(fNameIn));
				
				System.out.println("\nHere are the concents of the successfully created JSON file: " + fNameIn);
				String line = openF.readLine();
				while (line != null) {
					System.out.println(line);
					line = openF.readLine();
				}
			}
			catch(IOException e2) {
				System.out.println("Could not open input file. File does not exist.\n\n"
						+ "The program will now exit.");
			}
			
			
		}
		catch(IOException e) {
			System.out.println("Error reading from file. The program will exit.");			
		}
		finally {
			System.out.println("Goodbye! Hope you enjoyed creating the needed files using BibliographyFactory! <3");
			inKey.close();
			System.exit(0);
		}
	}
}
