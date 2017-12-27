package hackathon.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import hackathon.service.UserService;
import hackathon.utils.FileUtils;
import hackathon.utils.MongoDBUtils;

/**
 * Created by jxu on 2017/12/27.
 */
@Service
public class UserServiceImpl implements UserService {
	@Override
	public String parseFile(String fileName) {
		return FileUtils.UploadFile(fileName);
	}

	@Override
	public Long getCounts(Map<String, String[]> map) {
		BasicDBObject query = new BasicDBObject();

		// age
		BasicDBList age = new BasicDBList();
		String[] ages = map.get("age");
		for (String a : ages) {
			age.add(a);
		}
		query.put("生日年", new BasicDBObject("$in", age));

		// gender
		BasicDBList gender = new BasicDBList();
		String[] genders = map.get("gender");
		for (String g : genders) {
			gender.add(g);
		}
		query.put("性别", new BasicDBObject("$in", gender));
		// query.put("性别", "M");

		// district
		BasicDBList district = new BasicDBList();

		String[] districts = map.get("district");
		for (String d : districts) {
			district.add(d);
		}
		query.put("省份", new BasicDBObject("$in", district));

		return MongoDBUtils.getCountByCondition(query);
	}

	@Override
	public String getParserStatus(String jobId) {
		return MongoDBUtils.getProcessStatusById(jobId);
	}
}
