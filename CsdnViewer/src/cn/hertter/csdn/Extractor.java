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
 * CSDN������ȡ��
 * @author Hertter
 * @since 2019��8��2������3:22:04
 */
public class Extractor {
	/*
	 * ���ߵĲ�����ҳ
	 */
	private String authorURL;
	
	/*
	 * ��ǰ����ҳ��ĵ�ַ
	 */
	private String articlesURL;
	
	/*
	 * �����б�
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
		// ��ȡ���µ�ҳ������
		StringBuilder contents = new StringBuilder(dataOfArticle());
		
		// ��ȡԭ������ƪ��
		int counts = countOfArticle(contents.toString());

		int times = (int) Math.ceil((double) counts / 20);
		// �ܹ���Ҫ��ȡtimes��
		for (int i = 1; i <= times; i++) {
			// ��ȡ���µ�����
			listOfContent(counts, contents.toString());
			// ��ȡ��һҳ
			if (i + 1 <= times) {
				// ��һ������
				StringBuilder nextURL = new StringBuilder(articlesURL);
				nextURL.setCharAt(nextURL.length() - 1, (char) ((i + 1) + '0'));
				// ����URL
				articlesURL = nextURL.toString();
				// ��ȡ���µ�ҳ������
				contents = new StringBuilder(dataOfArticle());
			}
		}
		return articles;
	}
	
	private void listOfContent(int counts, String content) {
		// һ��������ϴ����ȡ�б������
		String regex = "<h4 class=\"\">(.*?)<a href=\"(.*?)\" target=\"_blank\">";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		StringBuilder articlesData = new StringBuilder();
		while (matcher.find()) {
			articlesData.append(matcher.group());
		}
		
		// ����������ϴ����ȡ���µ����Ӻ����µı���
		// ���±������
		String regex1 = "<span class=\"article-type type-1 float-none\">ԭ</span>        (.*?)</a>";
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
	 * ��ȡ���µ�ҳ������
	 * @param content
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private StringBuilder dataOfArticle() throws MalformedURLException, IOException {
		// ģ���������Ϊ
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(articlesURL).openConnection();
		httpsURLConnection.setRequestMethod("GET");
		httpsURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

		// ��ȡҳ������
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
	 * ��ȡԭ�����µ�ƪ��
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
