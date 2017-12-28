package hackathon.service;

import hackathon.pojo.CustomerSearch;

/**
 * Created by jxu on 2017/12/27.
 */
public interface UserService {

	String parseFile(String fileName);

	Long getCounts(CustomerSearch customer);

	String getParserStatus(String jobId);
}
