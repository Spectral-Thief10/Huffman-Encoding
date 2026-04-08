/**
 * 
 * @author Cameron Rodgers
 *	This class is Frequency Table 
 *  used when counting all occurrences of char
 */

public class FreqTable {
	private static final int TABLE_SIZE = 256; // assuming ASCII
	private int[] freq;
	private int charCount = 0; 
	
	public FreqTable() {
		freq = new int[TABLE_SIZE];
	}
	/** Justin 
	 * Increment the frequency table at location ch
	 * also counts any new chars added to the table 
	 * to be used in later table down sizing. 
	 * @param ch Character key to increment. 
	 */
	public void incrament(char ch) {
		if(get(ch) == 0)
			charCount++;
		freq[ch]++;
		
	}
	
	/** Justin
	 * Get returns the frequency of char ch 
	 * @param ch Character key to get
	 * @return ch frequency or 0 if not found. 
	 */
	public int get(char ch) {
		return freq[ch];
	}
	/** Justin 
	 * Get Frequencies returns an int[charCount][2]
	 * this is the frequency pairs without all the empty 
	 * space from the first map.
	 * 
	 * @return frequencCharPairs int[Char][Freq] 
	 */
	public Pair<Character, Integer>[] getFrequencies(){
		Pair<Character, Integer>[] frequencyCharPair;
		
		frequencyCharPair = 
				( Pair<Character, Integer>[] ) new Pair[charCount];	

		int charCTR = 0; 
		
		for(int i =0 ; i < TABLE_SIZE ; i++) {
			if(freq[i]!= 0) {
				frequencyCharPair[charCTR] = new Pair<Character, Integer>((char)i,freq[i]);
				charCTR++;
			}
		}
		return frequencyCharPair;
	}
		
	
	//Debug testing 
	/*
    public static void main(String[] args) {
        freqTable table = new freqTable();

        // Increment frequencies for characters in a string
        String testString = "Hello, world!";
        for (char ch : testString.toCharArray()) {
            table.incrament(ch);
        }

        // Print frequencies
        Pair<Character, Integer>[] frequencies = table.getFrequencies();
        for (int i = 0; i < frequencies.length; i++) {
            char ch = (char) frequencies[i].getKey();
            int frequency = (int) frequencies[i].getValue();
            System.out.println("Character: " + ch + ", Frequency: " + frequency);
        }
    }*/

}
