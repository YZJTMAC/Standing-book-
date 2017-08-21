package cn.teacheredu.utils;

/**
 * 路径处理类，为了程序在windows和linux下都能用，必须对路径进行处理
 * @author Zhaojie
 *
 */
public class FilePathUtil {
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");  
    //public static final String FILE_SEPARATOR = File.separator;  
  
    public static String getRealFilePath(String path) {  
        return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);  
    }  
  
    public static String getHttpURLPath(String path) {  
        return path.replace("\\", "/");  
    }
}
