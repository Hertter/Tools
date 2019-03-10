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
    
    //������ʾ����ѡ�еļ�¼
    private String displayContent;
    
    //���ڱ���ÿһ��ѡ�еļ�¼
    private static ArrayList<String> content = new ArrayList<String>();
    
    //���ڱ���ÿһ��ѡ�еļ�¼�ĸ������������޸ĺ���ļ���
    private static ArrayList<String> contentCopy = new ArrayList<String>();
    
    //���������ж�ѡ�м�¼
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
//        System.out.println("*****************������********************");
//        System.out.println(content);
//        System.out.println("*****************������********************");
    }
    
    
    /**
     * �޸��ļ����ĳ�ʼ������
     * @param tf
     * @param bg
     * @param content
     * @param i
     */
    public static void initialize(TextField tf, ButtonGroup bg, TextField content, int i) {
//        System.out.println("*****************����ǰ********************");
//        System.out.println(content);
//        System.out.println("*****************����ǰ********************");
        //��ʼ��
        tf.setText("");
        contentCopy.clear();
        
        if(bg.getSelection() == null) {
            tf.setText("�㻹ûѡ��ǰ׺���ߺ�׺��~");
            return;
        }
        if(content.getText().equals("") || content.getText().trim().length() == 0) {
            tf.setText("�㻹û����ǰ����׺�������ئ�( �����|||)");
            return;
        }
        if(i == 0) {
            tf.setText("�㻹ûѡ���ļ�����Ŀ¼�ئ�( �����|||)");
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
        
        //�ļ���¼������ǩ
        JLabel label_2 = new JLabel("0\u6761\u8BB0\u5F55");
        label_2.setBounds(85, 421, 112, 18);
        contentPane.add(label_2);
        
        //��ѡ�����
        ButtonGroup buttonGroup1 = new ButtonGroup();   //������
        ButtonGroup buttonGroup2 = new ButtonGroup();   //ɾ�����
        
        //��ѡ�����ǰ׺
        JRadioButton radioButton = new JRadioButton("\u524D\u7F00");
        radioButton.setSelected(true);
        radioButton.setBounds(66, 355, 60, 27);
        contentPane.add(radioButton);
        
        //��ѡ����Ӻ�׺
        JRadioButton radioButton_1 = new JRadioButton("\u540E\u7F00");
        radioButton_1.setBounds(123, 355, 60, 27);
        contentPane.add(radioButton_1);
        
        //��ѡ�����ǰ׺
        JRadioButton radioButton_2 = new JRadioButton("\u524D\u7F00");
        radioButton_2.setSelected(true);
        radioButton_2.setBounds(66, 385, 60, 27);
        contentPane.add(radioButton_2);
        
        //��ѡ����Ӻ�׺
        JRadioButton radioButton_3 = new JRadioButton("\u540E\u7F00");
        radioButton_3.setBounds(123, 385, 60, 27);
        contentPane.add(radioButton_3);
        
        //��ӵ�ѡ�����
        buttonGroup1.add(radioButton);
        buttonGroup1.add(radioButton_1);

        //ɾ����ѡ�����
        buttonGroup2.add(radioButton_2);
        buttonGroup2.add(radioButton_3);
        
        /********************************************************************************/
        //�ļ�ѡ����
        JFileChooser jFileChooser = new JFileChooser();
        
        //����ֻ����ѡ��Ŀ¼����1��ֻ����ѡ���ļ�����0��ֻ�����ļ���Ŀ¼����2
        jFileChooser.setFileSelectionMode(2);
        
        jFileChooser.setControlButtonsAreShown(false);
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.setBounds(0, 0, 473, 320);
        
        contentPane.add(jFileChooser);
        
        JButton btnNewButton = new JButton("\u9009\u62E9\u6587\u4EF6\u6216\u76EE\u5F55");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //��ʼ��ѡ������Ϊ0
                i = 0;
                
                //ÿ���һ�Σ��������ʾ�������
                textArea.setText("");
                
                //ѡ�����ļ�
                File[] selectFiles = jFileChooser.getSelectedFiles();
                
                //�������ļ����鲢������������
                for(File temp : selectFiles) {
                    i++;
                    displayContent = textArea.getText() + temp.getAbsolutePath() + "\n";
                    content.add(temp.getAbsolutePath());
                    textArea.setText(displayContent);
                }
                
                label_2.setText(i + "����¼");
                
                if(i == 0) {
                    textField.setText("�㻹ûѡ���ئ�( �����|||)");
                }else {
                    textField.setText("ѡ�������������޸����ְ�O(��_��)O");
                }
            }
        });
        btnNewButton.setBounds(318, 325, 145, 27);
        contentPane.add(btnNewButton);
        
        //��ӱ�ǩ
        JLabel label_3 = new JLabel("\u6DFB\u52A0\uFF1A");
        label_3.setBounds(10, 359, 50, 18);
        contentPane.add(label_3);
        
        //ɾ����ǩ
        JLabel label_4 = new JLabel("\u5220\u9664\uFF1A");
        label_4.setBounds(10, 390, 50, 18);
        contentPane.add(label_4);
        
        //��������ı���
        TextField textField_1 = new TextField();
        textField_1.setForeground(Color.BLACK);
        textField_1.setBackground(Color.WHITE);
        textField_1.setBounds(183, 358, 132, 20);
        contentPane.add(textField_1);
        
        //ɾ�������ı���
        TextField textField_2 = new TextField();
        textField_2.setForeground(Color.BLACK);
        textField_2.setBackground(Color.WHITE);
        textField_2.setBounds(183, 388, 132, 20);
        contentPane.add(textField_2);
        
        JButton button = new JButton("\u6279\u91CF\u6DFB\u52A0");
        //����¼�������
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                //��ʼ������
                initialize(textField, buttonGroup1, textField_1, i);
                
                //��ȡ��ѡ������ݣ�trueǰ׺��false��׺
                boolean addContent = (radioButton.isSelected()) ? true : false;
                
                //�޸ĳɹ�������
                int successNums = 0;
                
                //�����޸��ļ���
                for(String temp : content) {
                    File file = new File(temp);
                    
                    //�ļ���
                    String fileName = file.getName();
                    
                    //�ļ��ĸ�Ŀ¼
                    String parent = file.getParent();
                    
                    //�޸ĺ�����ļ�����
                    String newFileName = "";
                    if(addContent) {
                        newFileName = parent + "\\" + textField_1.getText() + fileName;
                    }else {
                        newFileName = parent + "\\" + fileName + textField_1.getText();
                    }

                    //�ļ�������
                    if(file.renameTo(new File(newFileName))) {
                        successNums++;
                    }
                    
                    //�������ļ�����������
                    contentCopy.add(newFileName);
                }
                textField.setText("��ѡ��" + i + "����¼" + "���ɹ��޸�" + successNums + "����¼��");
                
                //ʹ�ø����滻ԭ����content
                if(successNums != 0) {
                    copyContent();
                    JOptionPane.showMessageDialog(null, "�޸�" + successNums + "����¼�ɹ�������ˢ�²鿴��");
                }
            }
        });
        button.setBounds(318, 355, 145, 27);
        contentPane.add(button);
        
        JButton button_1 = new JButton("\u6279\u91CF\u5220\u9664");
        //ɾ���¼�������
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                //��ʼ��
                initialize(textField, buttonGroup2, textField_2, i);
                
                //��ȡ��ѡ������ݣ�trueǰ׺��false��׺
                boolean addContent = (radioButton_2.isSelected()) ? true : false;
                
                //�޸ĳɹ�������
                int successNums = 0;
                
                //�����޸��ļ���
                for(String temp : content) {
                    File file = new File(temp);
                    
                    //�ļ��ĸ�Ŀ¼
                    String parent = file.getParent();
                    
                    //�޸ĺ�����ļ�����
                    String newFileName = "";
                    if(addContent) {
                        
                        //�ļ�����ɾ��ǰ׺
                        if(file.getName().startsWith(textField_2.getText())) {
                            String fileName = file.getName().replaceFirst("^" + textField_2.getText(), "");
                            newFileName = parent + "\\" + fileName;
                        }else {
                            //�ļ�������ָ����׺�������ѭ��
                            continue;
                        }
                        
                    }else {
                        
                        //�ļ�����ɾ����׺
                        if(file.getName().endsWith(textField_2.getText())) {
                            String fileName = file.getName().replaceFirst(textField_2.getText() + "$", "");
                            newFileName = parent + "\\" + fileName;
                        }else {
                            //�ļ�������ָ����׺�������ѭ��
                            continue;
                        }
                        
                    }
                    
                    //�ļ�������
                    if(file.renameTo(new File(newFileName))) {
                        successNums++;
                    }
                    
                    //�������ļ�����������
                    contentCopy.add(newFileName);
                }
                
                textField.setText("��ѡ��" + i + "����¼" + "���ɹ��޸�" + successNums + "����¼��");
                
                //ʹ�ø����滻ԭ����content
                if(successNums != 0) {
                    copyContent();
                    JOptionPane.showMessageDialog(null, "�޸�" + successNums + "����¼�ɹ�������ˢ�²鿴��");
                }
            }
        });
        button_1.setBounds(318, 385, 145, 27);
        contentPane.add(button_1);
    }
}
