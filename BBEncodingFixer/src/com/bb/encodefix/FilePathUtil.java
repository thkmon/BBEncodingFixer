package com.bb.encodefix;

import java.io.File;

public class FilePathUtil {
	
	
	/**
	 * 특정 폴더 하위의 모든 파일 경로 가져오기
	 * 
	 * @param target
	 * @return
	 */
	public static StringList getFilePathList(File target) {
		StringList resultList = new StringList();
		getFilePathListCore(resultList, target);
		return resultList;
	}
	
	
	/**
	 * 특정 폴더 하위의 모든 파일 경로를 가져오기 (재귀)
	 * 
	 * @param resultList
	 * @param target
	 */
	private static void getFilePathListCore(StringList resultList, File target) {
		if (target == null || !target.exists()) {
			return;
		}
		
		if (target.isDirectory()) {
			File[] fileArr = target.listFiles();
			if (fileArr != null && fileArr.length > 0) {
				int fileCount = fileArr.length;
				for (int i=0; i<fileCount; i++) {
					getFilePathListCore(resultList, fileArr[i]);
				}
			}
			
		} else if (target.isFile()) {
			resultList.add(target.getAbsolutePath());
		}
	}
}