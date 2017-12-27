package hackathon.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hackathon.service.impl.UserServiceImpl;

public class UploadTest {
	public static String DB_IP = "10.207.2.144";
	public static int DB_PORT = 40001;
	public static String DB_DATABASE_NAME = "mydb";
	public static String DB_COLLECTION_CUSTOMER = "customer";

	public static void main(String[] args) throws Exception {

		// String file_test = "/Users/jascao/Documents/test.csv";
		// String file_one_million = "/Users/jascao/Documents/one_million.csv";
		// String file_one_hundred_million =
		// "/Users/jascao/Documents/one_hundred_million.csv";
		//
		// FileUtils.UploadFile(file_one_million);

		
		Date start = new Date();
		UserServiceImpl i = new UserServiceImpl();
		Long x = i.getCounts(getMap());

		System.out.println("---------------" + x);
		
		Date end = new Date();
		long d = end.getTime() - start.getTime();
		System.out.println("共耗时" + d / 1000 + "秒");
	}

	public static Map<String, String[]> getMap() {
		String[] age = { "2008", "2009", "2010" };
		String[] gender = { "F" };
		String[] district = { "10", "20" };

		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("age", age);
		map.put("gender", gender);
		map.put("district", district);
		return map;
	}

}
