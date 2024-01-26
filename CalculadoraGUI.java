import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {

    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private JButton sinButton;
    private JButton cosButton;
    private JButton tanButton;
    private JButton squareButton;
    private JButton cubeButton;
    private JButton piButton;
    private JButton sqrtButton;
    private JButton negateButton;

    private double num1, num2, result;
    private String operator;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        display = new JTextField(20); 
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
            buttonPanel.add(numberButtons[i]);
        }

        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (JButton button : operationButtons) {
            button.addActionListener(new OperationButtonListener());
            buttonPanel.add(button);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsButtonListener());
        buttonPanel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(new ClearButtonListener());
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);
        sinButton = new JButton("sin");
        sinButton.addActionListener(new TrigFunctionButtonListener("sin"));
        buttonPanel.add(sinButton);

        cosButton = new JButton("cos");
        cosButton.addActionListener(new TrigFunctionButtonListener("cos"));
        buttonPanel.add(cosButton);

        tanButton = new JButton("tan");
        tanButton.addActionListener(new TrigFunctionButtonListener("tan"));
        buttonPanel.add(tanButton);
        
        squareButton = new JButton("x²");
        squareButton.addActionListener(new PowerButtonListener(2));
        buttonPanel.add(squareButton);

        cubeButton = new JButton("x³");
        cubeButton.addActionListener(new PowerButtonListener(3));
        buttonPanel.add(cubeButton);
        
        piButton = new JButton("π");
        piButton.addActionListener(new PiButtonListener());
        buttonPanel.add(piButton);
        
        sqrtButton = new JButton("√");
        sqrtButton.addActionListener(new SqrtButtonListener());
        buttonPanel.add(sqrtButton);
        
        negateButton = new JButton("±");
        negateButton.addActionListener(new NegateButtonListener());
        buttonPanel.add(negateButton);
    }
      private class NegateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double value = Double.parseDouble(display.getText());
            value = -value;
            display.setText(String.valueOf(value));
        }
    }
    private class PiButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double piValue = Math.PI;
            display.setText(String.valueOf(piValue));
        }
    }
     private class TrigFunctionButtonListener implements ActionListener {
        private String function;

        public TrigFunctionButtonListener(String function) {
            this.function = function;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            double value = Double.parseDouble(display.getText());

            switch (function) {
                case "sin":
                    display.setText(String.valueOf(Math.sin(Math.toRadians(value))));
                    break;
                case "cos":
                    display.setText(String.valueOf(Math.cos(Math.toRadians(value))));
                    break;
                case "tan":
                    display.setText(String.valueOf(Math.tan(Math.toRadians(value))));
                    break;
            }
        }
    }
    
     private class PowerButtonListener implements ActionListener {
        private int power;

        public PowerButtonListener(int power) {
            this.power = power;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            double value = Double.parseDouble(display.getText());
            double result = Math.pow(value, power);
            display.setText(String.valueOf(result));
        }
    }
    

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            display.setText(display.getText() + source.getText());
        }
    }

    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            operator = source.getText();
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            operator = "";
        }
    }
     private class SqrtButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double value = Double.parseDouble(display.getText());
            if (value >= 0) {
                double result = Math.sqrt(value);
                display.setText(String.valueOf(result));
            } else {
                display.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calculator = new CalculadoraGUI();
            calculator.setVisible(true);
        });
    }
}
