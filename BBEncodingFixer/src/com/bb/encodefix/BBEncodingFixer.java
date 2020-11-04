package com.bb.encodefix;

import java.io.File;
import java.util.Scanner;

public class BBEncodingFixer {

	public static void main(String[] args) {
		try {
			String input = null;
			
			while (input == null || input.trim().length() == 0) {
				System.out.println("대상 경로를 입력해주세요.");
				Scanner scan = new Scanner(System.in);
				input = scan.next();
			}
			
			input = input.trim();
			
			File targetFile = new File(input);
			if (!targetFile.exists()) {
				System.out.println("파일을 찾을 수 없습니다. : " + targetFile);
			}
			
			int fixedCount = 0;
			int skippedCount = 0;
			
			StringList pathList = FilePathUtil.getFilePathList(targetFile);
			if (pathList != null && pathList.size() > 0) {
				String onePath = "";
				int pathCount = pathList.size();
				for (int i=0; i<pathCount; i++) {
					onePath = pathList.get(i);
					if (onePath == null || onePath.length() == 0) {
						continue;
					}
					
					File oneFile = new File(onePath);
					String charset = DetectEncodingUtil.detectCharset(oneFile);
					if (charset != null && charset.equalsIgnoreCase("EUC-KR")) {
						StringList fileContent = FileIOUtil.readFile(oneFile, "EUC-KR");
						FileIOUtil.writeFile(oneFile.getAbsolutePath(), fileContent, "UTF-8", false);
						System.out.println((i+1) + "/" + pathCount + " : " + onePath + " => FIXED");
						fixedCount++;
						
					} else {
						System.out.println((i+1) + "/" + pathCount + " : " + onePath + " => SKIP (charset : " + charset + ")");
						skippedCount++;
					}
				}
			} else {
				if (targetFile.isDirectory()) {
					System.out.println("폴더 하위의 파일을 찾을 수 없습니다. : " + targetFile);
				} else {
					System.out.println("파일을 찾을 수 없습니다. : " + targetFile);
				}
			}
			
			System.out.println("");
			System.out.println("수정한 파일 개수 : " + fixedCount);
			System.out.println("통과한 파일 개수 : " + skippedCount);
			System.out.println("");
			
			System.out.println("끝");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}