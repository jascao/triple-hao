package hackathon.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hackathon.service.impl.UserServiceImpl;
import hackathon.utils.FileUtils;

public class UploadTest {

	public static void main(String[] args) throws Exception {

		String file_test = "/Users/jascao/Documents/test.csv";
		String file_one_million = "/Users/jascao/Documents/one_million.csv";
		String file_one_hundred_million = "/Users/jascao/Documents/one_hundred_million.csv";

		FileUtils.UploadFile(file_test);

		// Date start = new Date();
		// UserServiceImpl i = new UserServiceImpl();
		// Long x = i.getCounts(getMap());
		//
		// System.out.println("---------------" + x);
		//
		// Date end = new Date();
		// long d = end.getTime() - start.getTime();
		// System.out.println("共耗时" + d + "ms");
	}

	public static Map<String, String[]> getMap() {
		String[] age = { "2008", "2009", "2010" };
		String[] gender = { "F", "M" };
		String[] district = { "10", "20" };

		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("age", age);
		map.put("gender", gender);
		map.put("district", district);
		return map;
	}

}
