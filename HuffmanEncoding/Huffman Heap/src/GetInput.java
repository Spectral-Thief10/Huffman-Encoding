
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 */
public class GetInput {

    /**
     * Prompts the user for a file to use as input and fills a freqTable with the characters
     *
     * @return The filled table
     */
    public static FreqTable getFileInput() {
        System.out.println("Enter name of file:");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        input.close();
        FreqTable frequence = new FreqTable();

        try {
            Scanner file = new Scanner(new File(fileName));

            while (file.hasNextLine()) {
                String line = file.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    frequence.incrament(line.charAt(j));
                }
            }
            file.close();

            return frequence;

        } catch (FileNotFoundException E) {
            System.out.println("File not found");
            return null;
        }
    }

    /**
     * Takes the name of a file to use to fill a freqTable
     *
     * @param fileName the name of the file to read
     * @return the filled freqTable
     */
    public static FreqTable getFileInput(String fileName) {
        FreqTable frequence = new FreqTable();

        try {
            Scanner file = new Scanner(new File(fileName));

            while (file.hasNextLine()) {
                String line = file.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    frequence.incrament(line.charAt(j));
                }
            }
            file.close();

            return frequence;

        } catch (FileNotFoundException E) {
            System.out.println("File not found");
            return null;
        }
    }

    public static FreqTable getStringInput(String data) {
        FreqTable frequence = new FreqTable();

        for (int j = 0; j < data.length(); j++) {
            frequence.incrament(data.charAt(j));
        }

        return frequence;
    }

}
