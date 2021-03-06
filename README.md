# 一些自己写的小工具
## 来源
我在实际应用中，经常会遇到各种需要重复大量人力的现象，比如学习前端时，需要将某个RGB颜色转为HEX十六进制，之前就是上网直接访问在线RGB和HEX互转的网页（PS也可以，但是我的电脑PS打开比较慢），所以自己用Java写了一个RGB和HEX互转的小工具。
又比如，前几天使用Java界面开发小东西的时候，需要图片素材，而图片素材的名称不唯一，一个一个改起来很繁琐，所以我写了一个批量修改文件名的小助手。
## 意义
写一些小工具，可以有效提高自己的学习效率，而不是浪费大量时间重复没什么用的人力上，同时也可以帮助自己巩固学习知识。
## ColorConversion
颜色转换器，支持RGB格式和HEX十六进制格式互转。
## FileNameOperation
文件名操作小助手，支持批量增加文件名或目录名前（后）缀、批量删除文件名或目录名前（后）缀
## CodeRain
代码雨简易版
## CsdnViewer
CSDN文章访问助手，给定一个作者的博客，并设置要需要访问的次数即可，如下所示。
```java
public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
	// 主页链接，格式为：https://blog.csdn.net/xxxxxxx，例如https://blog.csdn.net/weixin_41463193
	String url = "https://blog.csdn.net/weixin_41463193";
	// 总共需要刷多少次，每次会阅读所有的文章
	int times = 2;
	// 开始阅读
	new Viewer(new Extractor(url).extract(), times).startView();
}
```

