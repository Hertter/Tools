package cn.hertter.csdn;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * ����С����
 * @author Hertter
 * @since 2019��8��2������3:28:36
 */
public class Starter {
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		// ��ҳ���ӣ���ʽΪ��https://blog.csdn.net/xxxxxxx������https://blog.csdn.net/weixin_41463193
		String url = "https://blog.csdn.net/weixin_41463193";
		// �ܹ���Ҫˢ���ٴΣ�ÿ�λ��Ķ����е�����
		int times = 2;
		// ��ʼ�Ķ�
		new Viewer(new Extractor(url).extract(), times).startView();
	}
}
