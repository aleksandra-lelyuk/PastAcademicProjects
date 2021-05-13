package ProjectFiles;

/**
 * Class defining objects of type Article containing methods for writing
 * used to organize article information for citation purposes.
 * The class includes methods for writing citations based on IEEE, ACM and Natura Journal formats.
 * 
 */

public class Article {
	private String[] author;
	private String journal;
	private String title;
	private String year;
	private String volume;
	private String number;
	private String pages;
	private String doi;
	private String issn;
	private String month;
	
	
	/**
	 * Constructor for an object of class Article. Constructor takes in parameters to be assigned to every attribute.
	 * @param author the array of Strings of author names
	 * @param journal the journal title
	 * @param title the title of the article
	 * @param year publishing date
	 * @param volume the volume
	 * @param number the journal edition number
	 * @param pages the page numbers
	 * @param doi the doi reference
	 * @param issn the ISSN number
	 * @param month the month published
	 */
	public Article(String[] author, String journal, String title, String year, String volume, String number, String pages,
			String keywords, String doi, String issn, String month) {
		
		this.author = author;
		
		this.journal = journal;
		this.title = title;
		this.year = year;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		this.doi = doi;
		this.issn = issn;
		this.month = month;
	}
	
	/**
	 * Default constructor assigning "no data yet" to attributes of type String and null to the array type author attribute.
	 */
	public Article() {
		this.author = null;
		this.journal = "no data yet";
		this.title = "no data yet";
		this.year = "no data yet";
		this.volume = "no data yet";
		this.pages = "no data yet";
		this.number = "no data yet";
		this.doi = "no data yet";
		this.issn = "no data yet";
		this.month = "no data yet";
	}
	
	/**
	 * Allows to access the author array.
	 * @return the author array
	 */
	public String[] getAuthor() {
		return author;
	}
	/**
	 * Allows to assign new String array to the authors parameter.
	 * @param author String array of individual author names
	 */
	public void setAuthor(String[] author) {
		this.author = author;
	}
	/**
	 * Allows to access the journal name.
	 * @return the journal name
	 */
	public String getJournal() {
		return journal;
	}
	/**
	 * Allows to assign new journal name.
	 * @param journal the new journal name
	 */
	public void setJournal(String journal) {
		this.journal = journal;
	}
	/**
	 * Allows to access the article title.
	 * @return the article title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Allows to assign new article title.
	 * @param title the new article title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Allows to access the publishing date.
	 * @return the date of article publishing
	 */
	public String getYear() {
		return year;
	}
	/**
	 * Allows to assign a new publishing year.
	 * @param year new publishing year
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * Allows to access the article volume number.
	 * @return the volume number
	 */
	public String getVolume() {
		return volume;
	}
	/**
	 * Allows to assign new volume number.
	 * @param volume new volume number
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	/**
	 * Allows to access the article page numbers.
	 * @return the page numbers
	 */
	public String getPages() {
		return pages;
	}
	/**
	 * Allows to assign new page numbers.
	 * @param pages new page numbers
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}
	/**
	 * Allows to access the journal issue number.
	 * @return the journal issue number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Allows to assign new journal issue number.
	 * @param number the new journal issue number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * Allows to access the doi reference number.
	 * @return the doi reference number
	 */
	public String getDoi() {
		return doi;
	}
	/**
	 * Allows to assign new doi reference number.
	 * @param doi new doi reference number
	 */
	public void setDoi(String doi) {
		this.doi = doi;
	}
	/**
	 * Allows to access the ISSN number.
	 * @return the ISSN number
	 */
	public String getIssn() {
		return this.issn;
	}
	/**
	 * Allows to assign new ISSN number.
	 * @param issn new ISSN number
	 */
	public void setIssn(String issn) {
		this.issn = issn;
	}
	/**
	 * Allows to access the month of the article publication.
	 * @return the publication month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * Allows to assign new article publication month.
	 * @param month new article publication month
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	 * Method for compiling the article information from the instance variables in the IEEE citation format.
	 * @return citation of the article in IEEE format 
	 */
	public String formatIEEE(){
		//format the author names
		String auth = "";
		for (int i = 0; i < this.getAuthor().length; i++) {
			auth = auth + this.getAuthor()[i];
			if (i == (this.getAuthor().length - 1))
				auth = auth + ". ";
			else
				auth = auth + ", ";
		}
		
		String toPrint = (auth + "\"" + this.getTitle() + "\", " + this.getJournal() + ", vol. "
				+ this.getVolume() + ", no. " + this.getNumber() + ", p. " + this.getPages()
				+ ", " + this.getMonth() + " " + this.getYear() + "." + "\n");
		
		//format line arrangement
		int space = 0;
		int start = 0;
		String formatted = "";
		int IEEElines = 0;
		
		for (int i = 0; i < toPrint.length(); i++) {
			
			
			if (toPrint.substring(i, i+1).equals(" ")) {
				space = i;
				
			}
			if (i == 0)
				continue;
			
			if ((IEEElines == 0) && (i%90 == 0)) {
				formatted = (formatted + toPrint.substring(start, space) + "\n" + "       ");
				start = (space + 1);
				
				IEEElines++;
			}
			
			if (((IEEElines != 0) && (i - start)%82 == 0) && (i!= start)) {
				formatted = (formatted + toPrint.substring(start, space) + "\n" + "       " );
				
				start = (space + 1);
				
				
				IEEElines++;
			}
			if (i == (toPrint.length() - 1)){
				formatted = formatted + toPrint.substring(start);
			}
			
		}
		return formatted;
	}
	
	/**
	 * Method for compiling the article information from the instance variables in the ACM citation format
	 * (NOTE: does not include numbering).
	 * @return citation of the article in ACM format (no numbering)
	 */
	public String formatACM(){
		//format the author names
		String auth = this.getAuthor()[0];
		if (this.getAuthor().length == 1) 
			auth = auth + ". ";
		else
			auth = auth + " et al. ";
		
		String toPrint =  (auth + this.getYear() + ". " + this.getTitle() + ". " + this.getJournal() + ". "
				+ this.getVolume() + ", " + this.getNumber() + " (" + this.getYear() + "), "
				+ this.getPages() + ". DOI:https://doi.org/" + this.getDoi() + "." + "\n");
		
		//format line arrangement
		int space = 0;
		int start = 0;
		String formatted = "";
		
		for (int i = 0; i < toPrint.length(); i++) {
			
			if (toPrint.substring(i, i+1).equals(" ")) {
				space = i;
			}
			
			if (i == 0)
				continue;
			
			if (((i - start)%90 == 0) && (i!= start)) {
				formatted = (formatted + toPrint.substring(start, space) + "\n" + "       ");
				start = (space + 1);
				
			}
			if (i == (toPrint.length() - 1)){
				formatted = formatted + toPrint.substring(start);
			}
			
		}
		
		return formatted;
		
	}
	
	/**
	 * Method for compiling the article information from the instance variables in the Nature Journal format.
	 * @return citation of the article in the Nature Journal format
	 */
	public String formatNature(){
		//format the author names
		String auth = "";
		for (int i = 0; i < this.getAuthor().length; i++) {
			auth = auth + this.getAuthor()[i];
			if (i == (this.getAuthor().length - 1))
				auth = auth + ". ";
			else
				auth = auth + " & ";
		}
		String toPrint = (auth + this.getTitle() + ". " + this.getJournal() + ". "
				+ this.getVolume() + ", " + this.getPages() + "(" + this.getYear() + ")." + "\n");
		
		//format line arrangement
		int space = 0;
		int start = 0;
		String formatted = "";
		
		for (int i = 0; i < toPrint.length(); i++) {
			
			if (toPrint.substring(i, i+1).equals(" ")) {
				space = i;
			}
			
			if (i == 0)
				continue;
			
			if (((i - start)%90 == 0) && (i!= start) ) {
				formatted = (formatted + toPrint.substring(start, space) + "\n");
				start = (space + 1);
				
			}
			if (i == (toPrint.length() - 1)){
				formatted = formatted + toPrint.substring(start);
			}
		}
		return formatted;
	}
}

