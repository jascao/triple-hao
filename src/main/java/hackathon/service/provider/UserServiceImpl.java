package hackathon.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import hackathon.pojo.CustomerSearch;
import hackathon.service.UserService;
import hackathon.utils.FileUtils;
import hackathon.utils.MongoDBUtils;

/**
 * Created by jxu on 2017/12/27.
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {
	@Override
	public String parseFile(String fileName) {
		return FileUtils.UploadFile(fileName);
	}

	@Override
	public Long getCounts(CustomerSearch customer) {
		BasicDBObject query = new BasicDBObject();

		// age
		BasicDBList age = new BasicDBList();
		for (String a : customer.getYear()) {
			age.add(a);
		}
		query.put("生日年", new BasicDBObject("$in", age));

		// gender
		BasicDBList gender = new BasicDBList();
		for (String g : customer.getGender()) {
			gender.add(g);
		}
		query.put("性别", new BasicDBObject("$in", gender));

		// district
		BasicDBList district = new BasicDBList();
		for (String d : customer.getProvince()) {
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
