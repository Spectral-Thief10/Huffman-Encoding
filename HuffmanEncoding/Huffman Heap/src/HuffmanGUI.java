import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Cameron Rodgers
 */
public class HuffmanGUI extends JFrame implements ActionListener {
    private JTextArea inputTextArea, encodedTextArea, decodedTextArea;
    private JButton encodeButton, decodeButton;
    private HuffmanTree huffmanTree=null;
    private String encoded;

    public HuffmanGUI() {
        setTitle("Huffman Encoder/Decoder");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //center frame
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JLabel inputLabel = new JLabel("Input Text:");
        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setLineWrap(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);

        //if input text is changed, we need to disable decode button
        //only encode button enables it amd clear output
        inputTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                clear();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                clear();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                clear();
            }

            private void clear() {
                decodeButton.setEnabled(false);
                encodedTextArea.setText("");
                decodedTextArea.setText("");
            }
        });


        // Output Panel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(2, 1));

        JPanel output1Panel = new JPanel();
        output1Panel.setLayout(new BorderLayout());
        JLabel output1Label = new JLabel("Encoded Text:");
        encodedTextArea = new JTextArea(10, 40);
        encodedTextArea.setEditable(false);
        encodedTextArea.setLineWrap(true);
        JScrollPane output1ScrollPane = new JScrollPane(encodedTextArea);
        output1Panel.add(output1Label, BorderLayout.NORTH);
        output1Panel.add(output1ScrollPane, BorderLayout.CENTER);

        JPanel output2Panel = new JPanel();
        output2Panel.setLayout(new BorderLayout());
        JLabel output2Label = new JLabel("Decoded Text:");
        decodedTextArea = new JTextArea(10, 40);
        decodedTextArea.setEditable(false);
        decodedTextArea.setLineWrap(true);
        JScrollPane output2ScrollPane = new JScrollPane(decodedTextArea);
        output2Panel.add(output2Label, BorderLayout.NORTH);
        output2Panel.add(output2ScrollPane, BorderLayout.CENTER);

        outputPanel.add(output1Panel);
        outputPanel.add(output2Panel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        encodeButton = new JButton("Encode");
        decodeButton = new JButton("Decode");
        encodeButton.addActionListener(this);
        decodeButton.addActionListener(this);
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        decodeButton.setEnabled(false);

        // Add components to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HuffmanGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //use StringBuilder to create output
        StringBuilder output = new StringBuilder();
        //get input
        String data = inputTextArea.getText();

        if(e.getSource()==encodeButton) {

            if(data.length()==0) {
                JOptionPane.showMessageDialog(null,"Empty input!");
                return;
            }

            //output input length
            output.append("Data size, characters: ").append(data.length()).append("\n");

            //output frequency table
            FreqTable freqTable = GetInput.getStringInput(data);
            output.append("Frequency table:\n");

            Pair<Character, Integer>[] frequencyCharPair = freqTable.getFrequencies();
            for (Pair<Character, Integer> p : frequencyCharPair) {
                output.append(p.getKey()).append(" ").append(p.getValue()).append("\n");
            }
            output.append("\n");

            //output huffman tree
            huffmanTree = new HuffmanTree(freqTable);
            output.append("\nHuffman tree:\n");
            output.append(huffmanTree.treeToString()).append("\n\n");

            //encode
            Encoder Encoder = new Encoder(huffmanTree);
            output.append("\nCharacter encodings:\n");
            for (Pair<Character, String> entry : Encoder.getCharEncoding()) {

                output.append(entry.getKey()).append(" ");

                output.append(entry.getValue());

                output.append("\n");
            }

            encoded = Encoder.encode(data);
            output.append("\nEncoded data:\n");
            output.append(encoded);

            output.append("\nEncoded data size, bits: ");
            output.append(encoded.length());

            // Displays the total bit length of the Original input Char x 8
            output.append("ASCII Char(").append(data.length()).append(") x 8 size, bits: ")
            .append(data.length()*8).append("\n");

            //display
            encodedTextArea.setText(output.toString());

            decodeButton.setEnabled(true);
        } else {
            //decode
            String decoded = Decoder.decodeString(huffmanTree, encoded);

            output.append("Decoded data:\n");
            output.append(decoded).append("\n");

            //display
            decodedTextArea.setText(output.toString());
        }
    }
}
