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
     * 第一个版本，实现了简易的01滚动效果
     */
    private static final long serialVersionUID = 1L;
    /**
     * 屏幕宽度
     */
    private int screenWidth;
    /**
     * 屏幕高度
     */
    private int screenHeight;
    /**
     * 01TextPane的列数
     */
    private int columns;
    /**
     * TextPane数组
     */
    private JTextPane[] textPanes;
    /**
     * 线程——数字滚动效果
     */
    class PlayThread extends Thread {
        /**
         * 第i个TextPane对象
         */
        private int i;
        /**
         * 滚动的速度
         */
        private int speed;
        public PlayThread(int i, int speed) {
            this.i = i;
            this.speed = speed;
        }
        @Override
        public void run() {
            while(true) {
                //生成随机数——每列最多不超过20个数字
                int len = (int) (Math.random() * 20) + 1;
                //生成随机文本
                String text = createNumbers(len);
                //绘制textPane对象
                createTextPane(i, text, i * Constant.PADDING, Constant.DEFAULT_Y, Constant.TEXT_PANE_HEIGHT * len);
                //当前纵坐标
                int currentY = textPanes[i].getY();
                //当前横坐标
                int currentX = textPanes[i].getX();
                //TextPane高度
                int textPaneHeight = textPanes[i].getHeight();
                //当前纵坐标小于屏幕高度-TextPane高度-默认纵坐标，则进行滚动
                while(currentY < screenHeight - textPaneHeight - Constant.DEFAULT_Y) {
                    try {
                        //每20毫秒滚动一次
                        Thread.sleep(20);
                        currentY += speed;
                        textPanes[i].setLocation(currentX, currentY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //将当前textPanes设置为不可见，即触底消失
                textPanes[i].setVisible(false);
            }
        }
    }
    
    /**
     * 随机生成01字符串
     */
    public String createNumbers(int len) {
        StringBuilder randomText = new StringBuilder();
        for(int i = 0; i < len; i++) {
            randomText.append((int)(Math.random() * 2) + "\r\n");
        }
        return randomText.toString();
    }
    
    /**
     * 生成滚动效果
     */
    public void createNumbersTextPane() {
        textPanes = new JTextPane[columns];
        //生成columns列JTextPane对象
        for(int i = 0; i < columns; i++) {
            //随机生成10~15单位的速度
            int speed = (int) (Math.random() * 15) + 10;
            //启动线程
            new PlayThread(i, speed).start();
        }
    }
    
    /**
     * 创建Text Pane
     * @param text  TextPane的文本内容
     * @param x     TextPane的横坐标
     * @param y     TextPane的纵坐标
     */
    public void createTextPane(int i, String text, int x, int y, int height) {
        textPanes[i] = new JTextPane();
        //设置字体
        textPanes[i].setFont(new Font("黑体", Font.PLAIN, Constant.FONT_SIZE));
        //透明背景
        textPanes[i].setBackground(new Color(0, 0, 0, 0));
        //绿色字体
        textPanes[i].setForeground(new Color(0, 128, 0));
        //不可编辑
        textPanes[i].setEditable(false);
        //设置文本
        textPanes[i].setText(text);
        //设置大小
        textPanes[i].setBounds(x, y, Constant.LABEL_SIZE, height);
        //添加控件
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
     * 初始化窗口
     */
    public CodeRain() {
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        //代码列数
        columns = screenWidth / Constant.FONT_SIZE - Constant.FONT_SIZE;
        
        //设置面板透明背景
        getContentPane().setBackground(new Color(0, 0, 0, 0));
        //取消面板的布局
        getContentPane().setLayout(null);
        
        //关闭窗口
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //取消状态栏
        setUndecorated(true);
        //设置位置
        setBounds(0, 0, screenWidth, screenHeight);
        //设置透明背景
        setBackground(new Color(0, 0, 0, 0));
        //置顶
        setAlwaysOnTop(true);
        //生成滚动效果
        createNumbersTextPane();
    }
}
