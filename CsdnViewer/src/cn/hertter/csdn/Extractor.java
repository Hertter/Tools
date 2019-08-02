package cn.hertter.csdn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

/**
 * CSDN文章提取器
 * @author Hertter
 * @since 2019年8月2日下午3:22:04
 */
public class Extractor {
	/*
	 * 作者的博客主页
	 */
	private String authorURL;
	
	/*
	 * 当前文章页面的地址
	 */
	private String articlesURL;
	
	/*
	 * 文章列表
	 */
	private Map<String, String> articles;

	public String getAuthorURL() {
		return authorURL;
	}

	public void setAuthorURL(String authorURL) {
		this.authorURL = authorURL;
	}

	public Map<String, String> getArticles() {
		return articles;
	}

	public void setArticles(Map<String, String> articles) {
		this.articles = articles;
	}

	public Extractor(String authorURL) {
		super();
		this.authorURL = authorURL;
		articlesURL = authorURL + "/article/list/1";
		articles = new HashMap<>();
	}
	
	public Map<String, String> extract() throws MalformedURLException, IOException {
		// 获取文章的页面内容
		StringBuilder contents = new StringBuilder(dataOfArticle());
		
		// 提取原创文章篇数
		int counts = countOfArticle(contents.toString());

		int times = (int) Math.ceil((double) counts / 20);
		// 总共需要提取times次
		for (int i = 1; i <= times; i++) {
			// 提取文章的链接
			listOfContent(counts, contents.toString());
			// 读取下一页
			if (i + 1 <= times) {
				// 下一个链接
				StringBuilder nextURL = new StringBuilder(articlesURL);
				nextURL.setCharAt(nextURL.length() - 1, (char) ((i + 1) + '0'));
				// 更改URL
				articlesURL = nextURL.toString();
				// 获取文章的页面内容
				contents = new StringBuilder(dataOfArticle());
			}
		}
		return articles;
	}
	
	private void listOfContent(int counts, String content) {
		// 一轮数据清洗，提取列表的文章
		String regex = "<h4 class=\"\">(.*?)<a href=\"(.*?)\" target=\"_blank\">";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		StringBuilder articlesData = new StringBuilder();
		while (matcher.find()) {
			articlesData.append(matcher.group());
		}
		
		// 二轮数据清洗，提取文章的链接和文章的标题
		// 文章标题规则
		String regex1 = "<span class=\"article-type type-1 float-none\">原</span>        (.*?)</a>";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(content);
		regex = authorURL + "(.*?)\"";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(articlesData.toString());
		while (matcher.find() && matcher1.find()) {
			articles.put(authorURL + matcher.group(1), matcher1.group(1));
		}
	}
	
	/**
	 * 获取文章的页面内容
	 * @param content
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private StringBuilder dataOfArticle() throws MalformedURLException, IOException {
		// 模拟浏览器行为
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(articlesURL).openConnection();
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
		
		return contents;
	}
	
	/**
	 * 获取原创文章的篇数
	 * @param content
	 */
	private int countOfArticle(String content) {
		int counts = 0;
		String regex = "<span class=\"count\">(.*?)</span>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		if (matcher.find())
			counts = Integer.parseInt(matcher.group(1));
		return counts;
	}
	
}
