package cn.hertter.csdn;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * 启动小助手
 * @author Hertter
 * @since 2019年8月2日下午3:28:36
 */
public class Starter {
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		// 主页链接，格式为：https://blog.csdn.net/xxxxxxx，例如https://blog.csdn.net/weixin_41463193
		String url = "https://blog.csdn.net/weixin_41463193";
		// 总共需要刷多少次，每次会阅读所有的文章
		int times = 2;
		// 开始阅读
		new Viewer(new Extractor(url).extract(), times).startView();
	}
}
