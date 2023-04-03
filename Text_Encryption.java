import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Text_Encryption extends Frame implements ActionListener {

    Label text_label = new Label("Enter Text");
    Label result_label = new Label("Result : ");
    Label ed_label = new Label();
    TextField text = new TextField(27);
    Panel p1 = new Panel();

    Button btn_e = new Button("Encrypt");
    Button btn_d = new Button("Decrypt");
    Dimension d = new Dimension(600, 100);

    Text_Encryption() {
        setSize(600, 600);
        setTitle("Java CP");
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        text_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text.setFont(new Font("Times New Roman", Font.PLAIN, 17));

        add(text_label);
        add(text);

        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        p1.setPreferredSize(d);
        p1.add(btn_e);
        p1.add(btn_d);
        add(p1);

        result_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(result_label);
        ed_label.setSize(40, 20);;
        add(ed_label);

        btn_e.addActionListener(this);
        btn_d.addActionListener(this);

    }

    public static void main(String[] args) {
        Text_Encryption t = new Text_Encryption();
        t.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object ref = e.getSource();
        if (ref == btn_e) {
            encryptBtnAction();
        }
        else if (ref == btn_d)
            decryptBtnAction();
        else
            setBackground(Color.BLUE);
    }

    public void setTextInFile(String s) {
        try {
            FileWriter fw = new FileWriter("OnTheOrigin.txt");
            fw.write(s);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTextFromLabel() {
        String s = text.getText();
        return s;
    }

    public void setLabelTextToFile() {
        String labelText = getTextFromLabel();
        setTextInFile(labelText);
    }

    public String huffmanEncrypt() {
        String[] input = new String[5];
        String[] output = new String[5];
        input[0] = "OnTheOrigin.txt";
        output[0] = "encoded.txt";
        HuffmanBetterEnDe test1 = new HuffmanBetterEnDe();
        test1.Compress(input, output);
        HashMap<Integer, String> mapEncoded = test1.getHuffmanMap();
        String inputStr = getTextFromLabel();
        String encodedStr = "";
        for (int i = 0; i < inputStr.length(); i++) {
            encodedStr += mapEncoded.get((int) inputStr.charAt(i));
        }
        System.out.println(encodedStr);
        return encodedStr;
    }

    public void huffmanDecrypt() {
        String[] input = new String[5];
        String[] output = new String[5];
        input[0] = "encoded.txt";
        output[0] = "decoded.txt";
        HuffmanBetterEnDe test1 = new HuffmanBetterEnDe();
        test1.Decompress(input, output);
    }

    public String getEncodedBytesFromFile() {
        String s = "";
        try {
            FileReader fr = new FileReader("decoded.txt");
            int i;
            while ((i = fr.read()) != -1) {
                s += (char) i;
            }
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }

    public void setResultLabel(String s) {
        ed_label.setText(s);
    }

    public void encryptBtnAction() {
        setLabelTextToFile();
        String encrypt = huffmanEncrypt();
        setResultLabel(encrypt);
        // setBackground(Color.RED);
    }   

    public void decryptBtnAction() {
        huffmanDecrypt();
        String decrypt = getEncodedBytesFromFile();
        System.out.println(decrypt);
        setResultLabel(decrypt);
        // setBackground(Color.GREEN);
    }
}
