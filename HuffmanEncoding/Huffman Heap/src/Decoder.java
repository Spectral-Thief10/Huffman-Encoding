
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Decodes a given file when given a huffman tree containing the encoding
 */
public class Decoder {

    /**
     * Decodes a given file using the given tree
     *
     * @param fileName The name of the file to decode
     * @param encoding The tree to use when decoding the file
     */
    public static void decode(String fileName, HuffmanTree encoding) {
        try {
            Scanner file = new Scanner(new File(fileName));
            HuffmanNode cur = encoding.getRoot();

            while (file.hasNextInt()) {
                int next = file.nextInt();
                if (next == 1) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }

                if (cur.data != '\0') {
                    // Can change this to output to a file
                    System.out.print(cur.data);
                    cur = encoding.getRoot();
                }
            }

        } catch (FileNotFoundException E) {
            System.out.println("File not found");
            return;
        }
    }

    public static String decodeString(HuffmanTree encoding, String encoded) {
        HuffmanNode cur = encoding.getRoot();
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            int x = encoded.charAt(i) - '0';

            if (x == 1) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }

            if (cur.data != '\0') {
                data.append(cur.data);
                cur = encoding.getRoot();
            }
        }
        return data.toString();
    }
}
