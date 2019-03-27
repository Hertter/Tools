package cn.hertter.coderain;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

/**
 * 
 * @author 哈哈
 *
 */
public class CoolFrame extends JFrame{
    public void launchFrame() {
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setVisible(true);
        System.out.println(Window.WIDTH);
        this.setSize(screenSize.width, screenSize.height);
        this.setLocation(0, 0);
//        this.setOpacity((float) 0.5);
    }
    public static void main(String[] args) {
        CoolFrame cf = new CoolFrame();
        cf.launchFrame();
    }
}
