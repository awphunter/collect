package org.io.studio.collect;

import java.io.File;

import org.junit.Test;

public class TestApp {

	@Test()
	public void test() {
		System.out.println("Hello World!");
		System.out.println("查找指定目录中的excel文件，然后生成汇总文件");
		File root = new File("/data/");
		if (!root.isDirectory()) {
			System.out.println("请在程序执行的根目录创建一个 data的目录，并把相关文件放入");
			System.exit(1);
		}
		App app = new App();
		app.getAllFiles(root);
	}

}
