package hackathon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Test {
	
	public static void main(String[] args) throws Exception {
		System.out.println("--------------1");
		exportCsv();
		System.out.println("--------------2");
	}

	public static <T> void exportCsv() throws Exception {

		File file = new File("/Users/jascao/Documents/test.csv");
		OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(file), "utf-8");

		String spilt = ",";

		StringBuffer t = new StringBuffer();
		t.append("ID").append(spilt);
		t.append("姓名").append(spilt);
		t.append("手机").append(spilt);
		t.append("性别").append(spilt);
		t.append("生日年").append(spilt);
		t.append("生日月").append(spilt);
		t.append("生日天").append(spilt);
		t.append("省份").append(spilt);
		t.append("城市").append(spilt);
		t.append("字段1").append(spilt);
		t.append("字段2").append(spilt);
		t.append("字段3").append(spilt);
		t.append("字段4").append(spilt);
		t.append("字段5").append(spilt);
		t.append("字段6").append(spilt);
		t.append("字段7").append(spilt);
		t.append("字段8").append(spilt);
		t.append("字段9").append(spilt);
		t.append("字段10");

		ow.write(t.toString());
		ow.write("\r\n");

		for (int i = 1; i < 11; i++) {
			User user = getUser(i);

			StringBuffer sb = new StringBuffer();
			sb.append(user.getId()).append(spilt);
			sb.append(user.getName()).append(spilt);
			sb.append(user.getMobile()).append(spilt);
			sb.append(user.getGender()).append(spilt);
			sb.append(user.getBirthyear()).append(spilt);
			sb.append(user.getBirthmonth()).append(spilt);
			sb.append(user.getBirthday()).append(spilt);
			sb.append(user.getProvince()).append(spilt);
			sb.append(user.getCity()).append(spilt);
			sb.append("t1").append(spilt);
			sb.append("t2").append(spilt);
			sb.append("t3").append(spilt);
			sb.append("t4").append(spilt);
			sb.append("t5").append(spilt);
			sb.append("t6").append(spilt);
			sb.append("t7").append(spilt);
			sb.append("t8").append(spilt);
			sb.append("t9").append(spilt);
			sb.append("t10");

			ow.write(sb.toString());
			ow.write("\r\n");
		}

		ow.flush();
		ow.close();
	}

	private static String[] GENDER = new String[] { "M", "F" };

	private static User getUser(int id) {
		User u = new User();
		u.setId(id);
		u.setName("name" + id);
		u.setGender(GENDER[getRandom(0, 2)]);
		u.setMobile(String.valueOf((long) (Math.random() * 90000000000l) + 10000000000l));

		u.setBirthyear(String.valueOf(getRandom(1950, 2010)));
		u.setBirthmonth(String.valueOf(getRandom(1, 12)));
		u.setBirthday(String.valueOf(getRandom(1, 31)));
		u.setProvince(String.valueOf(getRandom(1, 34)));
		u.setCity(String.valueOf(getRandom(1, 200)));
		return u;
	}

	private static int getRandom(int min, int max) {
		Random random = new Random();
		int result = random.nextInt(max) % (max - min + 1) + min;
		return result;
	}
}
