package org.io.studio.collect.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil {

	private static int BUFFER_SIZE = 1024;


	public static long getFileSizes(File f) {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				s = fis.available();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	public static void copyFile(File source, File destination) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(source));
		OutputStream out = new FileOutputStream(destination);
		byte[] buffer = new byte[BUFFER_SIZE];
		int length;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		out.close();
		in.close();
	}

	/**
	 * @param srcName
	 * @param destPath
	 * @return
	 */
	public static boolean copyFile(String srcName, String destPath) {
		File srcFile = new File(srcName);
		File destFile = new File(destPath + srcFile.getName());
		if (srcFile.isDirectory())
			return copyDir(srcFile.getAbsolutePath(), destPath);
		else
			return copyRealFile(srcName, destFile.getAbsolutePath());
	}

	/**
	 * @param srcName
	 * @param destName
	 * @return
	 */
	public static boolean copyRealFile(String srcName, String destName) {
		try {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(srcName));
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(destName));

			int i = 0;
			byte[] buffer = new byte[2048];
			while ((i = in.read(buffer)) != -1) {
				out.write(buffer, 0, i);

			}
			out.close();
			in.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	/**
	 * @param srcDir
	 * @param destDir
	 * @return
	 */
	public static boolean copyDir(String srcDir, String destDir) {
		boolean star = false;
		File srcFile = new File(srcDir);
		File destFile = new File(destDir + File.separator + srcFile.getName());
		destFile.mkdirs();
		File fileArray[] = srcFile.listFiles();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].isFile()) {
				String destFileName = destFile.getAbsolutePath()
						+ File.separator + fileArray[i].getName();
				System.out.println("原目录" + destFileName);
				star = copyRealFile(fileArray[i].getAbsolutePath(),
						destFileName);
				if (!star)
					return false;
			} else {
				String destFileName = destFile.getAbsolutePath();
				copyDir(fileArray[i].getAbsolutePath(), destFileName);
			}
		}
		return true;

	}

	public static String fileRead(String path) {
		File f = new File(path);

		InputStreamReader is = null;
		try {
			is = new InputStreamReader(new FileInputStream(f));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(is);
		StringBuffer sb = new StringBuffer();
		String line = "";
		while (line != null) {
			try {
				line = br.readLine();

				if (line != null) {
					sb.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	public void unzipFile(String targetPath, String zipFilePath) {

		try {

			File zipFile = new File(zipFilePath);

			InputStream is = new FileInputStream(zipFile);

			ZipInputStream zis = new ZipInputStream(is);
			ZipEntry entry = null;

			System.out.println("开始解压:" + zipFile.getName() + "...");

			while ((entry = zis.getNextEntry()) != null) {

				String zipPath = entry.getName();

				try {

					if (entry.isDirectory()) {

						File zipFolder = new File(targetPath + File.separator

						+ zipPath);

						if (!zipFolder.exists()) {

							zipFolder.mkdirs();

						}
					} else {

						File file = new File(targetPath + File.separator

						+ zipPath);

						if (!file.exists()) {

							File pathDir = file.getParentFile();

							pathDir.mkdirs();

							file.createNewFile();

						}

						FileOutputStream fos = new FileOutputStream(file);

						int bread;

						while ((bread = zis.read()) != -1) {

							fos.write(bread);

						}

						fos.close();

					}

					System.out.println("成功解压:" + zipPath);

				} catch (Exception e) {

					System.out.println("解压" + zipPath + "失败");

					continue;

				}

			}

			zis.close();

			is.close();

			System.out.println("解压结束");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径
	// 调用 delAllFile 方法

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists())
			return flag;
		if (!file.isDirectory())
			return flag;
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	public static void write(String content, String path) {
		try {
			OutputStream fos = new FileOutputStream(path, true);
			try {
				fos.write((content + "\n").getBytes());
				fos.flush();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] subBytes(byte[] obyte, int length) {
		byte[] newBytes = new byte[length];

		for (int i = 0; i < length; i++) {
			newBytes[i] = obyte[i];

		}
		return newBytes;
	}

	public static void writeBytes(byte[] data, String path) {

		OutputStream out;
		try {
			out = new FileOutputStream(path);
			try {
				out.write(data);

				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 输入流转成文件 并写入
	 * 
	 * @param ins
	 *            inputStream输入流
	 * @param file
	 *            File文件
	 */
	public static void inputstreamToFile(InputStream is, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查文件路径问题 如果不存在则创建路径 (只负责路径问题，一般用于 创建文件前处理)
	 * 
	 * @param file
	 *            需要创建的文件
	 * @since 2012-10-14
	 * @author mr.io
	 */
	public static void fixFilePath(File file) {
		String path = file.getPath();
		if (path.split("\\\\").length > 2) {
			path = path.substring(0, path.lastIndexOf(file.separator));
			File temp = new File(path);
			temp.mkdirs();
		}
	}

	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	// 取文件扩展名
	public static String getFileExtension(String fileName) {
		String extension = "";
		int lastIndexOfDot = fileName.lastIndexOf('.');
		if (lastIndexOfDot > -1) {
			if (fileName.indexOf(".tar.gz") > -1) {
				return ".tar.gz";
			}
			extension = fileName.substring(lastIndexOfDot, fileName.length());
		}
		return extension;
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	/**
	 * 由文件路径获得文件名(可控制是否包含文件后缀)
	 * 
	 * @param filePath
	 * @param flag true:包含后缀,false:不包含后缀
	 * @return
	 */
	public static String getFileNameByFilePathJudgeSuffix(String filePath,boolean flag){
		String name = null;
		if(null != filePath && filePath.length() > 0){
			String temp[] = filePath.replaceAll("\\\\","/").split("/");  
			if (temp.length >= 1) {   
				name = temp[temp.length - 1];   
			}
		}
		if(null != name && name.length() > 0){
			if(!flag){
				//不包含后缀
				if(name.indexOf(".") != -1){
					name = name.substring(0, name.lastIndexOf("."));
				}
			}
		}
		return name;
	}
	
}
