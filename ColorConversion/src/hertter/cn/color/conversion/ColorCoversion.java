package hertter.cn.color.conversion;

/**
 * 颜色转换的小工具
 * 实现RGB和HEX互转
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ColorCoversion extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String r;
	private String g;
	private String b;
	private int r1;
	private int g1;
	private int b1;
	private String hex;
	/**
	 * 用于匹配十六进制
	 */
	private String patternHex = "^#([0-9a-fA-F]{6})|([0-9a-fA-F]{3})$";
	private char[] dst = new char[7];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorCoversion frame = new ColorCoversion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ColorCoversion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ColorCoversion.class.getResource("/ico/ico.jpg")));
		setTitle("CC\u00B2");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(240, 278);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 234, 122);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TextField R = new TextField();
		R.setFont(new Font("Dialog", Font.BOLD, 14));
		R.setText("255");
		R.setBounds(14, 37, 48, 25);
		panel.add(R);
		
		TextField G = new TextField();
		G.setFont(new Font("Dialog", Font.BOLD, 14));
		G.setText("215");
		G.setBounds(64, 37, 48, 25);
		panel.add(G);
		
		TextField B = new TextField();
		B.setFont(new Font("Dialog", Font.BOLD, 14));
		B.setText("0");
		B.setBounds(114, 37, 48, 25);
		panel.add(B);
		
		JLabel label = new JLabel("RGB");
		label.setBounds(14, 14, 40, 18);
		panel.add(label);
		
		TextField OutPutHEX = new TextField();
		OutPutHEX.setFont(new Font("Dialog", Font.BOLD, 14));
		OutPutHEX.setBackground(Color.WHITE);
		OutPutHEX.setText("#ffd700");
		OutPutHEX.setEditable(false);
		OutPutHEX.setBounds(122, 80, 100, 20);
		panel.add(OutPutHEX);
		
		JPanel OutPutColor1 = new JPanel();
		OutPutColor1.setBackground(new Color(255,215,0));
		OutPutColor1.setBounds(14, 68, 48, 48);
		panel.add(OutPutColor1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 121, 234, 122);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHex = new JLabel("HEX");
		lblHex.setBounds(14, 14, 40, 18);
		panel_1.add(lblHex);
		
		TextField InPutHEX = new TextField();
		InPutHEX.setFont(new Font("Dialog", Font.BOLD, 14));
		InPutHEX.setText("#7b68ee");
		InPutHEX.setBounds(14, 37, 102, 25);
		panel_1.add(InPutHEX);
		
		TextField OutPutRGB = new TextField();
		OutPutRGB.setFont(new Font("Dialog", Font.BOLD, 14));
		OutPutRGB.setText("123,104,238");
		OutPutRGB.setEditable(false);
		OutPutRGB.setBackground(Color.WHITE);
		OutPutRGB.setBounds(122, 80, 100, 20);
		panel_1.add(OutPutRGB);
		
		JPanel OutPutColor2 = new JPanel();
		OutPutColor2.setBackground(new Color(123,104,238));
		OutPutColor2.setBounds(14, 68, 48, 48);
		panel_1.add(OutPutColor2);
		
		JButton Conversion1 = new JButton("Go");
		Conversion1.setBackground(SystemColor.menu);
		Conversion1.setBounds(167, 35, 55, 27);
		panel.add(Conversion1);
		
		Conversion1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r = R.getText();
				g = G.getText();
				b = B.getText();
				try{
					r1 = Integer.parseInt(r);
					g1 = Integer.parseInt(g);
					b1 = Integer.parseInt(b);
					if(r1==0) {r="00";}else {r=Integer.toHexString(r1);}
					if(g1==0) {g="00";}else {g=Integer.toHexString(g1);}
					if(b1==0) {b="00";}else {b=Integer.toHexString(b1);}
					if(r1>0&&r1<16) {r="0"+Integer.toHexString(r1);}
					if(g1>0&&g1<16) {g="0"+Integer.toHexString(g1);}
					if(b1>0&&b1<16) {b="0"+Integer.toHexString(b1);}
					hex = "#"+r+g+b;
					System.out.println(hex);
					OutPutColor1.setBackground(new Color(r1,g1,b1));
					OutPutHEX.setText(hex);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "RGB value is wrong.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		

		JButton Conversion2 = new JButton("Go");
		Conversion2.setBackground(SystemColor.menu);
		Conversion2.setBounds(167, 35, 55, 27);
		panel_1.add(Conversion2);
		
		JLabel lblNewLabel = new JLabel("By Hertter");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(150, 102, 88, 18);
		panel_1.add(lblNewLabel);
		
		Conversion2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hex = InPutHEX.getText();
				if(Pattern.matches(patternHex, hex)) {//符合十六进制颜色的正则匹配
					dst = hex.toCharArray();
					switch(dst.length) {
					case 4:r = String.copyValueOf(dst,1,1);g = String.copyValueOf(dst,2,1);b = String.copyValueOf(dst,3,1);break;
					case 7:r = String.copyValueOf(dst,1,2);g = String.copyValueOf(dst,3,2);b = String.copyValueOf(dst,5,2);break;
					}
					r1 = Integer.parseInt(r,16);
					g1 = Integer.parseInt(g,16);
					b1 = Integer.parseInt(b,16);
					System.out.println("RGB:"+r1+","+g1+","+b1);
					OutPutColor2.setBackground(new Color(r1,g1,b1));
					OutPutRGB.setText(r1+","+g1+","+b1);
				}else {
					JOptionPane.showMessageDialog(null, "HEX value is wrong.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
