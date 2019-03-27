package cn.hertter.coderain;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextPane;

public class CodeRain extends JFrame {

    /**
     * ��һ���汾��ʵ���˼��׵�01����Ч��
     */
    private static final long serialVersionUID = 1L;
    /**
     * ��Ļ���
     */
    private int screenWidth;
    /**
     * ��Ļ�߶�
     */
    private int screenHeight;
    /**
     * 01TextPane������
     */
    private int columns;
    /**
     * TextPane����
     */
    private JTextPane[] textPanes;
    /**
     * �̡߳������ֹ���Ч��
     */
    class PlayThread extends Thread {
        /**
         * ��i��TextPane����
         */
        private int i;
        /**
         * �������ٶ�
         */
        private int speed;
        public PlayThread(int i, int speed) {
            this.i = i;
            this.speed = speed;
        }
        @Override
        public void run() {
            while(true) {
                //�������������ÿ����಻����20������
                int len = (int) (Math.random() * 20) + 1;
                //��������ı�
                String text = createNumbers(len);
                //����textPane����
                createTextPane(i, text, i * Constant.PADDING, Constant.DEFAULT_Y, Constant.TEXT_PANE_HEIGHT * len);
                //��ǰ������
                int currentY = textPanes[i].getY();
                //��ǰ������
                int currentX = textPanes[i].getX();
                //TextPane�߶�
                int textPaneHeight = textPanes[i].getHeight();
                //��ǰ������С����Ļ�߶�-TextPane�߶�-Ĭ�������꣬����й���
                while(currentY < screenHeight - textPaneHeight - Constant.DEFAULT_Y) {
                    try {
                        //ÿ20�������һ��
                        Thread.sleep(20);
                        currentY += speed;
                        textPanes[i].setLocation(currentX, currentY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //����ǰtextPanes����Ϊ���ɼ�����������ʧ
                textPanes[i].setVisible(false);
            }
        }
    }
    
    /**
     * �������01�ַ���
     */
    public String createNumbers(int len) {
        StringBuilder randomText = new StringBuilder();
        for(int i = 0; i < len; i++) {
            randomText.append((int)(Math.random() * 2) + "\r\n");
        }
        return randomText.toString();
    }
    
    /**
     * ���ɹ���Ч��
     */
    public void createNumbersTextPane() {
        textPanes = new JTextPane[columns];
        //����columns��JTextPane����
        for(int i = 0; i < columns; i++) {
            //�������10~15��λ���ٶ�
            int speed = (int) (Math.random() * 15) + 10;
            //�����߳�
            new PlayThread(i, speed).start();
        }
    }
    
    /**
     * ����Text Pane
     * @param text  TextPane���ı�����
     * @param x     TextPane�ĺ�����
     * @param y     TextPane��������
     */
    public void createTextPane(int i, String text, int x, int y, int height) {
        textPanes[i] = new JTextPane();
        //��������
        textPanes[i].setFont(new Font("����", Font.PLAIN, Constant.FONT_SIZE));
        //͸������
        textPanes[i].setBackground(new Color(0, 0, 0, 0));
        //��ɫ����
        textPanes[i].setForeground(new Color(0, 128, 0));
        //���ɱ༭
        textPanes[i].setEditable(false);
        //�����ı�
        textPanes[i].setText(text);
        //���ô�С
        textPanes[i].setBounds(x, y, Constant.LABEL_SIZE, height);
        //��ӿؼ�
        getContentPane().add(textPanes[i]);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CodeRain frame = new CodeRain();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * ��ʼ������
     */
    public CodeRain() {
        //��ȡ��Ļ��С
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        //��������
        columns = screenWidth / Constant.FONT_SIZE - Constant.FONT_SIZE;
        
        //�������͸������
        getContentPane().setBackground(new Color(0, 0, 0, 0));
        //ȡ�����Ĳ���
        getContentPane().setLayout(null);
        
        //�رմ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ȡ��״̬��
        setUndecorated(true);
        //����λ��
        setBounds(0, 0, screenWidth, screenHeight);
        //����͸������
        setBackground(new Color(0, 0, 0, 0));
        //�ö�
        setAlwaysOnTop(true);
        //���ɹ���Ч��
        createNumbersTextPane();
    }
}
