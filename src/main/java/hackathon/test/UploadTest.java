package hackathon.test;
 

import java.util.Date;

import hackathon.utils.FileUtils;

public class UploadTest {

	public static void main(String[] args) throws Exception {

		System.out.println("[---------- Start -------------]" + (new Date()).getTime());

		String file_test = "/Users/jascao/Documents/test.csv";
		String file_one_million = "/Users/jascao/Documents/one_million.csv";
		String file_one_hundred_million = "/Users/jascao/Documents/one_hundred_million.csv";

		FileUtils.UploadFile(file_test);

	}

}
