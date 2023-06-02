package iphone11.apps;

import iphone11.BlackPanel;
import iphone11.Home;
import iphone11.MainPanel;
import iphone11.etc.DefaultSetting;
import iphone11.etc.Images;
import iphone11.etc.Time;
import iphone11.etc.TimeCount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calculator extends JPanel {
    private int startY;
    private TimeCount timeCount;
    private ImageIcon[] calculatorImgs;
    private static Calculator instance;
    public static Calculator getInstance() throws Exception {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }
    private final ImageIcon telecommunications = Images.TELECOMMUNICATIONS;
    private final ImageIcon battery = Images.BATTERY;
    private JLabel result;
    private Calculator() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);
        //NORTH prat
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setOpaque(false);
        JLabel minuteHour = Time.getHourMinute();
        minuteHour.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 22));
        JLabel whiteSpace = new JLabel("                                                ");

        JLabel lte = new JLabel("LTE");
        lte.setForeground(Color.WHITE);
        lte.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 18));

        JLabel tel = new JLabel(telecommunications);
        JLabel bat = new JLabel(battery);

        northPanel.add(minuteHour);
        northPanel.add(whiteSpace);
        northPanel.add(tel);
        northPanel.add(lte);
        northPanel.add(bat);


        add(northPanel, BorderLayout.NORTH);


        //CENTER part
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        result = new JLabel("0");
        result.setForeground(Color.WHITE);
        result.setHorizontalAlignment(SwingConstants.LEFT);  // 왼쪽 정렬로 변경
        result.setFont(new Font(DefaultSetting.getInstance().getFontName(), DefaultSetting.getInstance().getFontStyle(), 50));
        result.setBounds(40, 80, 400, 100);
        centerPanel.add(result);

        calculatorImgs = new ImageIcon[19];
        JButton[] calculatorBtns = new JButton[19];
        sortCalculatorOrder();
        for(int i = 0; i < calculatorImgs.length; i++) {
            calculatorBtns[i] = new JButton(calculatorImgs[i]);
            DefaultSetting.btnSetting(calculatorBtns[i]);
            centerPanel.add(calculatorBtns[i]);
        }
        calculatorBtnPosition(calculatorBtns);
        calculator(calculatorBtns);

        timeCount = new TimeCount();
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackPanel blackPanel = new BlackPanel();
                Home home = (Home) getTopLevelAncestor();
                if (home != null) {
                    DefaultSetting.setContentPane(home, blackPanel);
                }
            }
        };
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();
                timeCount.start(actionListener);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (startY - e.getY() > 100) {
                    MainPanel mainPanel = null;
                    try {
                        mainPanel = new MainPanel();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    Home home = (Home)getTopLevelAncestor();
                    DefaultSetting.setContentPane(home, mainPanel);
                }
            }
        });
        timeCount.start(actionListener);
    }



    private static void calculatorBtnPosition(JButton[] calculatorBtns) {
        int calX = 25, calY = 180, calWidth =60, calHeight = 60, interval = 90;
        calculatorBtns[0].setBounds(calX, calY + interval * 4, 173, 80);
        for(int i = 1; i < 5; i++) {
            if(i == 4) {
                calculatorBtns[16].setBounds(calX + 3 * interval, calY + interval * 3, 80, 80);
                break;
            }
            calculatorBtns[i].setBounds(calX + (i - 1) * interval, calY + interval * 3, 80, 80);
        }
        for(int i = 4; i < 8; i++) {
            if(i == 7) {
                calculatorBtns[15].setBounds(calX + 3 * interval, calY + interval * 2, 80, 80);
                break;
            }
            calculatorBtns[i].setBounds(calX + (i - 4) * interval, calY + interval * 2, 80, 80);
        }
        for(int i = 7; i < 11; i++){
            if(i == 10){
                calculatorBtns[14].setBounds(calX + 3 * interval, calY + interval, 80, 80);
                break;
            }
            calculatorBtns[i].setBounds(calX + (i - 7) * interval, calY + interval, 80, 80);
        }
        for(int i = 10; i < 14; i++) {
            calculatorBtns[i].setBounds(calX + (i- 10) * interval, calY, 80, 80);
        }
        for(int i = 17; i < 19; i++) {
            calculatorBtns[i].setBounds(calX + (i - 15) * interval, calY + interval * 4, 80, 80);
        }

    }
    private void sortCalculatorOrder() {
        calculatorImgs[0] = Images._0;calculatorImgs[1] = Images._1;calculatorImgs[2] = Images._2;
        calculatorImgs[3] = Images._3;calculatorImgs[4] = Images._4;calculatorImgs[5] = Images._5;
        calculatorImgs[6] = Images._6;calculatorImgs[7] = Images._7;calculatorImgs[8] = Images._8;
        calculatorImgs[9] = Images._9;calculatorImgs[10] = Images.AC;calculatorImgs[11] = Images.PLUS_MINUS;
        calculatorImgs[12] = Images.DEVIDE_100;calculatorImgs[13] = Images.DIVISION;calculatorImgs[14] = Images.MULTIPLICATION;
        calculatorImgs[15] = Images.MINUS;calculatorImgs[16] = Images.PLUS;calculatorImgs[17] = Images.MINORITY_POINT;calculatorImgs[18] = Images.EQUAL;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(130, 694, 140, 8, 10, 10);
    }


    private String saveResult = "";
    private void calculator(JButton[] calculatorBtns) {

        //When you click the number button
        for (int i = 0; i <= 9; i++) {
            int num = i;
            calculatorBtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = result.getText();
                    result.setText(inputText + num);
                    saveResult += num;
                }
            });
        }
        // When you click the plus button
        calculatorBtns[16].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = result.getText();
                result.setText(currentText + "+");
                saveResult += "+";
            }
        });

        // When you click the minus button
        calculatorBtns[15].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = result.getText();
                result.setText(currentText + "-");
                saveResult += "-";
            }
        });

        // When you click the multiplication button
        calculatorBtns[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = result.getText();
                result.setText(currentText + "*");
                saveResult += "*";
            }
        });

        // When you click the division button
        calculatorBtns[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = result.getText();
                result.setText(currentText + "/");
                saveResult += "/";
            }
        });
        // When you click the equal button
        calculatorBtns[18].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] tokens = saveResult.split("(?=[\\+\\-\\*/])|(?<=[\\+\\-\\*/])");
                double result = Double.parseDouble(tokens[0]);
                for (int i = 1; i < tokens.length; i += 2) {
                    int operand = Integer.parseInt(tokens[i + 1]);
                    switch (tokens[i]) {
                        case "+":
                            result += operand;
                            break;
                        case "-":
                            result -= operand;
                            break;
                        case "*":
                            result *= operand;
                            break;
                        case "/":
                            result /= operand;
                            break;
                    }
                }
                Calculator.this.result.setText(saveResult + "=" + result);
            }
        });
        // When you click the AC button
        calculatorBtns[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("0"); // result 라벨 초기화
                saveResult = ""; // currentExpression 초기화
            }
        });


    }

}
