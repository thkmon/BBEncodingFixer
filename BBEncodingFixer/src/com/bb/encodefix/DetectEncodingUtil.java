package com.bb.encodefix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.mozilla.universalchardet.UniversalDetector;

public class DetectEncodingUtil {
	
	
	/**
	 * 파일 인코딩 타입을 찾는다.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String detectCharset(File file) throws IOException, Exception {
		if (file == null || !file.exists()) {
			return null;
		}

		String result = "";

		FileInputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);
			result = detectCharset(inputStream);

		} catch (IOException e) {
			throw e;

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (NullPointerException e) {
			} catch (Exception e) {
			}
		}

		return result;
	}
	
	
	/**
	 * 파일 인코딩 타입을 찾는다.
	 * 
	 * @param inputStream
	 * @return
	 * @throws NullPointerException
	 * @throws Exception
	 */
	public static String detectCharset(InputStream inputStream) throws NullPointerException, Exception {

		int nread = 0;
		String encoding = "";
		try {
			byte[] buf = new byte[1024];
			UniversalDetector detector = new UniversalDetector(null);

			while ((nread = inputStream.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
			detector.dataEnd();

			encoding = detector.getDetectedCharset();
			if (encoding == null) {
				encoding = "";
			}

			detector.reset();

		} catch (NullPointerException e) {
			throw e;

		} catch (Exception e) {
			throw e;

		} finally {
		}

		return encoding;
	}
}