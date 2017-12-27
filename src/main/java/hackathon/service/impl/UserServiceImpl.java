package hackathon.service.impl;

import org.springframework.stereotype.Service;

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
	public Long getCounts() {
		// TODO
		return 20L;
	}

	@Override
	public String getParserStatus(String jobId) {
		return MongoDBUtils.getProcessStatusById(jobId);
	}
}
