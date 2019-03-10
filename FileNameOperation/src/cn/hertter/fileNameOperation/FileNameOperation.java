package cn.hertter.fileNameOperation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class FileNameOperation extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    //用于显示所有选中的记录
    private String displayContent;
    
    //用于保存每一条选中的记录
    private static ArrayList<String> content = new ArrayList<String>();
    
    //用于保存每一条选中的记录的副本——更新修改后的文件名
    private static ArrayList<String> contentCopy = new ArrayList<String>();
    
    //计数器，判断选中记录
    private int i;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileNameOperation frame = new FileNameOperation();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void copyContent() {
        content.clear();
        for(String temp : contentCopy) {
            content.add(temp);
        }
//        System.out.println("*****************拷贝后********************");
//        System.out.println(content);
//        System.out.println("*****************拷贝后********************");
    }
    
    
    /**
     * 修改文件名的初始化操作
     * @param tf
     * @param bg
     * @param content
     * @param i
     */
    public static void initialize(TextField tf, ButtonGroup bg, TextField content, int i) {
//        System.out.println("*****************拷贝前********************");
//        System.out.println(content);
//        System.out.println("*****************拷贝前********************");
        //初始化
        tf.setText("");
        contentCopy.clear();
        
        if(bg.getSelection() == null) {
            tf.setText("你还没选择前缀或者后缀呢~");
            return;
        }
        if(content.getText().equals("") || content.getText().trim().length() == 0) {
            tf.setText("你还没输入前（后）缀的内容呢Σ( °△°|||)");
            return;
        }
        if(i == 0) {
            tf.setText("你还没选中文件或者目录呢Σ( °△°|||)");
            return;
        }
    }

    /**
     * Create the frame.
     */
    public FileNameOperation() {
        setResizable(false);
        setTitle("\u6587\u4EF6\u540D\u64CD\u4F5C\u5C0F\u52A9\u624B1.0   By:Hertter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(479, 724);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u72B6\u6001\uFF1A");
        label.setBounds(10, 329, 50, 18);
        contentPane.add(label);
        
        TextField textField = new TextField();
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);
        textField.setText("\u672A\u9009\u4E2D\u6587\u4EF6\u6216\u8005\u76EE\u5F55");
        textField.setEditable(false);
        textField.setBounds(66, 329, 248, 20);
        contentPane.add(textField);
        
        JLabel label_1 = new JLabel("\u5DF2\u9009\u4E2D\uFF1A");
        label_1.setBounds(10, 421, 60, 18);
        contentPane.add(label_1);
        
        JTextArea textArea = new JTextArea();
        textArea.setRows(20);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBounds(10, 382, 452, 240);
        contentPane.add(textArea);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 441, 452, 240);
        contentPane.add(scrollPane);
        
        //文件记录条数标签
        JLabel label_2 = new JLabel("0\u6761\u8BB0\u5F55");
        label_2.setBounds(85, 421, 112, 18);
        contentPane.add(label_2);
        
        //单选框组合
        ButtonGroup buttonGroup1 = new ButtonGroup();   //添加组合
        ButtonGroup buttonGroup2 = new ButtonGroup();   //删除组合
        
        //单选框，添加前缀
        JRadioButton radioButton = new JRadioButton("\u524D\u7F00");
        radioButton.setSelected(true);
        radioButton.setBounds(66, 355, 60, 27);
        contentPane.add(radioButton);
        
        //单选框，添加后缀
        JRadioButton radioButton_1 = new JRadioButton("\u540E\u7F00");
        radioButton_1.setBounds(123, 355, 60, 27);
        contentPane.add(radioButton_1);
        
        //单选框，添加前缀
        JRadioButton radioButton_2 = new JRadioButton("\u524D\u7F00");
        radioButton_2.setSelected(true);
        radioButton_2.setBounds(66, 385, 60, 27);
        contentPane.add(radioButton_2);
        
        //单选框，添加后缀
        JRadioButton radioButton_3 = new JRadioButton("\u540E\u7F00");
        radioButton_3.setBounds(123, 385, 60, 27);
        contentPane.add(radioButton_3);
        
        //添加单选框组合
        buttonGroup1.add(radioButton);
        buttonGroup1.add(radioButton_1);

        //删除单选框组合
        buttonGroup2.add(radioButton_2);
        buttonGroup2.add(radioButton_3);
        
        /********************************************************************************/
        //文件选择器
        JFileChooser jFileChooser = new JFileChooser();
        
        //设置只允许选择目录——1，只允许选择文件——0，只允许文件和目录——2
        jFileChooser.setFileSelectionMode(2);
        
        jFileChooser.setControlButtonsAreShown(false);
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.setBounds(0, 0, 473, 320);
        
        contentPane.add(jFileChooser);
        
        JButton btnNewButton = new JButton("\u9009\u62E9\u6587\u4EF6\u6216\u76EE\u5F55");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //初始化选中条数为0
                i = 0;
                
                //每点击一次，先情况显示框的内容
                textArea.setText("");
                
                //选择多个文件
                File[] selectFiles = jFileChooser.getSelectedFiles();
                
                //遍历多文件数组并保存在数组中
                for(File temp : selectFiles) {
                    i++;
                    displayContent = textArea.getText() + temp.getAbsolutePath() + "\n";
                    content.add(temp.getAbsolutePath());
                    textArea.setText(displayContent);
                }
                
                label_2.setText(i + "条记录");
                
                if(i == 0) {
                    textField.setText("你还没选中呢Σ( °△°|||)");
                }else {
                    textField.setText("选好啦！快批量修改名字吧O(∩_∩)O");
                }
            }
        });
        btnNewButton.setBounds(318, 325, 145, 27);
        contentPane.add(btnNewButton);
        
        //添加标签
        JLabel label_3 = new JLabel("\u6DFB\u52A0\uFF1A");
        label_3.setBounds(10, 359, 50, 18);
        contentPane.add(label_3);
        
        //删除标签
        JLabel label_4 = new JLabel("\u5220\u9664\uFF1A");
        label_4.setBounds(10, 390, 50, 18);
        contentPane.add(label_4);
        
        //添加内容文本框
        TextField textField_1 = new TextField();
        textField_1.setForeground(Color.BLACK);
        textField_1.setBackground(Color.WHITE);
        textField_1.setBounds(183, 358, 132, 20);
        contentPane.add(textField_1);
        
        //删除内容文本框
        TextField textField_2 = new TextField();
        textField_2.setForeground(Color.BLACK);
        textField_2.setBackground(Color.WHITE);
        textField_2.setBounds(183, 388, 132, 20);
        contentPane.add(textField_2);
        
        JButton button = new JButton("\u6279\u91CF\u6DFB\u52A0");
        //添加事件触发器
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                //初始化操作
                initialize(textField, buttonGroup1, textField_1, i);
                
                //获取单选框的内容，true前缀，false后缀
                boolean addContent = (radioButton.isSelected()) ? true : false;
                
                //修改成功的条数
                int successNums = 0;
                
                //遍历修改文件名
                for(String temp : content) {
                    File file = new File(temp);
                    
                    //文件名
                    String fileName = file.getName();
                    
                    //文件的根目录
                    String parent = file.getParent();
                    
                    //修改后的新文件名称
                    String newFileName = "";
                    if(addContent) {
                        newFileName = parent + "\\" + textField_1.getText() + fileName;
                    }else {
                        newFileName = parent + "\\" + fileName + textField_1.getText();
                    }

                    //文件重命名
                    if(file.renameTo(new File(newFileName))) {
                        successNums++;
                    }
                    
                    //拷贝新文件名到副本中
                    contentCopy.add(newFileName);
                }
                textField.setText("共选择" + i + "条记录" + "，成功修改" + successNums + "条记录！");
                
                //使用副本替换原来的content
                if(successNums != 0) {
                    copyContent();
                    JOptionPane.showMessageDialog(null, "修改" + successNums + "条记录成功，建议刷新查看。");
                }
            }
        });
        button.setBounds(318, 355, 145, 27);
        contentPane.add(button);
        
        JButton button_1 = new JButton("\u6279\u91CF\u5220\u9664");
        //删除事件触发器
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                //初始化
                initialize(textField, buttonGroup2, textField_2, i);
                
                //获取单选框的内容，true前缀，false后缀
                boolean addContent = (radioButton_2.isSelected()) ? true : false;
                
                //修改成功的条数
                int successNums = 0;
                
                //遍历修改文件名
                for(String temp : content) {
                    File file = new File(temp);
                    
                    //文件的根目录
                    String parent = file.getParent();
                    
                    //修改后的新文件名称
                    String newFileName = "";
                    if(addContent) {
                        
                        //文件名，删除前缀
                        if(file.getName().startsWith(textField_2.getText())) {
                            String fileName = file.getName().replaceFirst("^" + textField_2.getText(), "");
                            newFileName = parent + "\\" + fileName;
                        }else {
                            //文件名不是指定后缀，则继续循环
                            continue;
                        }
                        
                    }else {
                        
                        //文件名，删除后缀
                        if(file.getName().endsWith(textField_2.getText())) {
                            String fileName = file.getName().replaceFirst(textField_2.getText() + "$", "");
                            newFileName = parent + "\\" + fileName;
                        }else {
                            //文件名不是指定后缀，则继续循环
                            continue;
                        }
                        
                    }
                    
                    //文件重命名
                    if(file.renameTo(new File(newFileName))) {
                        successNums++;
                    }
                    
                    //拷贝新文件名到副本中
                    contentCopy.add(newFileName);
                }
                
                textField.setText("共选择" + i + "条记录" + "，成功修改" + successNums + "条记录！");
                
                //使用副本替换原来的content
                if(successNums != 0) {
                    copyContent();
                    JOptionPane.showMessageDialog(null, "修改" + successNums + "条记录成功，建议刷新查看。");
                }
            }
        });
        button_1.setBounds(318, 385, 145, 27);
        contentPane.add(button_1);
    }
}
