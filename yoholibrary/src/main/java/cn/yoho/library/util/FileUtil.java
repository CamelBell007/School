/*
 * Created by fanchao
 * 
 * Date:2014年9月18日下午6:31:32 
 * 
 * Copyright (c) 2014, Show(R). All rights reserved.
 * 
 */
package cn.yoho.library.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;



/**
 * Function: 处理文件名，创建文件的类， 如果要得到某个文件目录 {@link SystemUtil}
 * 
 * Date: 2014年9月18日 下午6:31:32
 * 
 * @author fanchao
 */
public final class FileUtil {
	private static final String TAG = "FileUtil";

	private FileUtil() {
	}

	/**
	 * 删除文件夹
	 * @param folder
	 */
	public static void deleteFolderRecursively(File folder) {
		if (folder != null && folder.isDirectory() && folder.canWrite()) {
			File[] listFiles = folder.listFiles();

			if (listFiles != null) {
				for (File f : listFiles) {
					if (f.isFile()) {
						f.delete();
					} else if (f.isDirectory()) {
						deleteFolderRecursively(f);
					}
				}
				folder.delete();
			}
		}
	}

	/**
	 * 创建一个目录，包括子目录
	 *
	 * @param directory  创建的目录 不能为null{@code null}
	 * @throws NullPointerException 如果目录为null {@code null}
	 * @throws IOException 不能被创建
	 */
	public static void forceMkdir(File directory) throws Exception {
		FileUtils.forceMkdir(directory);
	}

	/**
	 * 通过传入的路径得到名字
	 * <p>
	 * <pre>
	 * a/b/c.txt --> c.txt
	 * a.txt     --> a.txt
	 * a/b/c     --> c
	 * a/b/c/    --> ""
	 * http://www.xxx.com/d.jpg --> d.jpg
	 * </pre>
	 * <p>
	 *
	 * @param filename  http或者file, null returns null
	 * @return 返回最后的名字
	 */
	public static String getName(String filename) {
		return FilenameUtils.getName(filename);
	}

	/**
	 * 通过路径得到没有后缀的文件名
	 * <p>
	 * <pre>
	 * a/b/c.txt --> c
	 * a.txt     --> a
	 * a/b/c     --> c
	 * a/b/c/    --> ""
	 * http://xxx.com/d.jpg --> d
	 * </pre>
	 * <p>
	 *
	 * @param filename  http或者file, null returns null
	 * @return 没有后缀的名字
	 */
	public static String getBaseName(String filename) {
		return FilenameUtils.getBaseName(filename);
	}

	/**
	 * 获取后缀
	 * <p>
	 * <pre>
	 * foo.txt      --> "txt"
	 * a/b/c.jpg    --> "jpg"
	 * a/b.txt/c    --> ""
	 * a/b/c        --> ""
	 * </pre>
	 * <p>
	 *
	 * @param filename 整个文件名
	 * @return 后缀
	 */
	public static String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);
	}

	/**
	 * <pre>
	 * C:\a\b\c.txt --> C:\a\b\
	 * ~/a/b/c.txt  --> ~/a/b/
	 * a.txt        --> ""
	 * a/b/c        --> a/b/
	 * a/b/c/       --> a/b/c/
	 * C:           --> C:
	 * C:\          --> C:\
	 * ~            --> ~/
	 * ~/           --> ~/
	 * ~user        --> ~user/
	 * ~user/       --> ~user/
	 * </pre>
	 * <p>
	 */
	public static String getFullPath(String filename) {
		return FilenameUtils.getFullPath(filename);
	}

	/**
	 * 判断文件是否存成
	 * @param fileName
	 * @return true：文件存成 false:文件不存在
	 */
	public static boolean isFileExist(String fileName) {
		boolean flag = false;
		if (fileName == null || fileName.length() == 0) {
			return false;
		}
		File file = new File(fileName);
		flag = isFileExist(file);
		return flag;
	}

	/**
	 * 判断文件是否存在
	 * @param file 文件
	 * @return  true：文件存成 false:文件不存在
	 */
	public static boolean isFileExist(File file) {
		if (file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为URI路径
	 * @param filename
	 * @return true:是URI路径 false:不是
	 */
	public static boolean isURIPath(String filename) {
		if (filename.contains(":")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成文件，如果父文件夹不存在，则先生成父文件夹
	 * 
	 * @param fileName
	 *            :要生成的文件全路径
	 * @return File对象，如果有文件名不存在则返回null
	 */
	public static File createFile(String fileName) {

		if (fileName == null || fileName.length() <= 0) {
			return null;
		}
		File file = new File(fileName);
		// 获取父文件夹
		File folderFile = file.getParentFile();
		if (!folderFile.exists()) {
			folderFile.mkdirs();
		}
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 复制一个文件
	 * 
	 * @param src
	 * @param tar
	 * @throws Exception
	 */
	public static void copyFile(File src, File tar) throws Exception {
		try {
			int bytesum = 0;
			int byteread = 0;
			if (src.isFile()) { // 文件存在时
				InputStream inStream = new FileInputStream(src); // 读入原文件
				FileOutputStream fs = new FileOutputStream(tar);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
				src.delete();
			}
		} catch (Exception e) {
			//System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @param path
	 * @return
	 */
	public static long getFileSize(File file) {
		long dirSize = 0;

		if (file == null) {
			return 0;
		}
		if (!file.isDirectory()) {
			dirSize += file.length();
			return dirSize;
		}

		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				dirSize += f.length();
			} else if (f.isDirectory()) {
				dirSize += f.length();
				dirSize += getFileSize(f); // 如果遇到目录则通过递归调用继续统计
			}
		}

		return dirSize;
	}
}
