import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String number = "0000";
    public static String numberScanStr = "0000";
    public static int[] digitsN = new int[]{
            0,0,0,0
    };

    public static int[] digitsG = new int[]{
            0,0,0,0
    };

    public static boolean guessed = false;
    public static Scanner scan;
    public static Random rand = new Random();

    public static int plus = 0;
    public static int minus = 0;

    public static int step = 0;
    public static JFrame frame;
    public static JTextField tf;
    public static JButton btn;
    public static JLabel lbl;

    public static void main(String[] args){
        ui();
        do {
            int numberi = rand.nextInt(10000);
            if(numberi < 1000) {
                number = "0" + numberi;
            } else {
                number = "" + numberi;
            }
            // println("" + number);
        } while(repeatedNumbers(number));

        getDigits(number, "N");
        // printDigits("N");
        lbl.setText("Try to guess my number, its 4 digits and digits are unique");
//        while(!guessed){
//            guess();
//        }
    }

    public static void ui(){
        frame = new JFrame("Guess Number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        tf = new JTextField();
        tf.setSize(200,50);
        tf.setLocation(10, 10);
        tf.setFont(new Font("Courier", Font.BOLD,25));
        btn = new JButton("Enter");
        btn.setFont(new Font("Courier", Font.BOLD,25));
        btn.setSize(200, 50);
        btn.setLocation(220, 10);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                guess();
            }
        });

        lbl = new JLabel("Log is hereeeeeee");
        lbl.setSize(410, 50);
        lbl.setLocation(10, 70);

        frame.add(tf);
        frame.add(btn);
        frame.add(lbl);
        frame.setSize(430,160);
        frame.setVisible(true);
    }

    public static void getDigits (String s, String NG) {
        for(int i = 0; i < 4; i++){
            if(NG.equals("N")) digitsN[i] = s.charAt(i);
            if(NG.equals("G")) digitsG[i] = s.charAt(i);
        }
    }

    public static void guess(){
        numberScanStr = tf.getText();
        if((numberScanStr).length() != 4){
            lbl.setText("My number is 4 digits long");
        } else if(repeatedNumbers(numberScanStr)){
            lbl.setText("Don't type repeating digits");
        } else {
            getDigits(numberScanStr, "G");
            step++;
            check();
        }
    }

    public static void check(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(digitsN[i] == digitsG[j]){
                    if(i == j){
                        plus++;
                    } else {
                        minus++;
                    }
                }
            }
        }

        if(plus == 4) {
            lbl.setText("You guessed right, " + step + " steps needed");
        } else {
            lbl.setText("Step " + step + " : " + numberScanStr +
                    " (+" + plus + "; -" + minus + ")");
        }
        resetPM();
    }

    public static void resetPM(){
        plus = 0;
        minus = 0;
    }

    public static boolean repeatedNumbers(String s){
        boolean rep = false;
        int[] digits = new int[4];

        for(int i = 0; i < 4; i++){
            digits[i] = s.charAt(i);
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < i; j++){
                if(digits[i] == digits[j]){
                    rep = true;
                    break;
                }
            }
        }
        return rep;
    }
}
