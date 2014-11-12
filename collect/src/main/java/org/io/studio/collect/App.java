package org.io.studio.collect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.io.studio.collect.analysis.ProcessFile;
import org.io.studio.collect.generate.CollectData;
import org.io.studio.collect.generate.GenerateData;
import org.io.studio.collect.tools.FileUtil;

/**
 * 生成汇总excel文件
 * 
 * @author mr.io
 * 
 */
public class App {
	static Logger log = Logger.getLogger(App.class);
	private List<String> filepaths = new ArrayList<String>();
	private ExecutorService threadPool = null;
	private int docNum = 0;

	public void getAllFiles(File root) {
		File[] fs = root.listFiles();
		boolean hasExcels = false;
		if (fs.length <= 0) {
			System.out.println("请在 data目录 放入需要处理的文件 ");
			System.exit(1);
		}
		for (int i = 0; i < fs.length; i++) {
			System.out.println(fs[i].getAbsolutePath());
			if (fs[i].isFile()
					&& FileUtil.getFileExtension(fs[i].getName()).contains(
							"xls")) {
				filepaths.add(fs[i].getAbsolutePath());
				hasExcels = true;
			}
		}
		if (hasExcels) {
			docNum = filepaths.size();
			process();
		} else {
			System.out.println("未发现 excel 文件");
			System.exit(1);
		}
	}

	private void process() {
		threadPool = Executors.newFixedThreadPool(10);
		CollectData cd = CollectData.getSyncSingle();
		for (String s : filepaths) {
			ProcessFile pro = new ProcessFile(s);
			threadPool.execute(pro);
		}
		while (docNum != cd.getCourses().size()) {
			try {
				Thread.currentThread();
				Thread.sleep(1*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		threadPool.shutdown();
		log.debug("threadPool shutdown");
		if (threadPool.isShutdown()) {
			log.debug("threadPool isShutdown");
			GenerateData.process(cd.getCourses());
		}
	}

	public static void main(String[] args) {
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
