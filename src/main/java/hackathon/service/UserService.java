package hackathon.service;

import java.util.Map;

/**
 * Created by jxu on 2017/12/27.
 */
public interface UserService {

	String parseFile(String fileName);

	Long getCounts(Map<String, String[]> map);

	String getParserStatus(String jobId);
}
