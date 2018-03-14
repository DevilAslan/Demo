package cn.umbrella.commons.util.base;

import java.util.ArrayList;
import java.util.List;

import cn.umbrella.commons.enums.FileType;


public class FileTool {

	public static boolean ImgContains(String s) {
		List<String> list = new ArrayList<String>();
		list.add(FileType.GIF.getValue());
		list.add(FileType.JPG.getValue());
		list.add(FileType.PNG.getValue());
		list.add(FileType.BMP.getValue());
		return list.contains(s);
	}
}
