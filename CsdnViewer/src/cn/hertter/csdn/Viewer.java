package cn.hertter.csdn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

/**
 * CSDN文章访问量助手
 * @author Hertter
 * @since 2019年8月2日下午1:17:41
 */
public class Viewer {
	private Map<String, String> maps;
	
	/*
	 * 总共需要爬多少次
	 */
	private int times;

	public Viewer(Map<String, String> maps, int times) {
		super();
		this.maps = maps;
		this.times = times;
	}
	
	public void startView() throws MalformedURLException, IOException, InterruptedException {
		for (int i = 1; i <= times; i++) {
			for (String key : maps.keySet()) {
				view(maps.get(key), key);
			}
			if (i + 1 <= times) {
				System.out.println("当前第" + i + "次已经刷完，剩下" + (times - i) + "次，等待一分钟后继续...");
				Thread.sleep(60 * 1000);
			}
		}
		System.out.println("结束");
	}
	
	/**
	 * 模拟阅读
	 * @param url
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void view(String title, String url) throws MalformedURLException, IOException {
		// 模拟浏览器行为
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(url).openConnection();
		httpsURLConnection.setRequestMethod("GET");
		httpsURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

		// 爬取页面内容
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), "UTF-8"));
		StringBuilder contents = new StringBuilder();
		String tempString = null;
		while (null != (tempString = bufferedReader.readLine())) {
			contents.append(tempString);
		}
		bufferedReader.close();
		
		// 打印信息
		printMessage(title, contents.toString());
	}
	
	/**
	 * 打印当前文章标题以及阅读数
	 */
	private void printMessage(String title, String content) {
		String regex = "<span class=\"read-count\">阅读数 (.*?)</span>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			System.out.println("正在刷阅读数...");
			System.out.println("当前阅读数为：" + matcher.group(1) + "   标题：" + title);
		} else {
			System.out.println("找不到文章...");
		}
	}
}
