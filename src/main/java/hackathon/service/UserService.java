package hackathon.service;

/**
 * Created by jxu on 2017/12/27.
 */
public interface UserService {

	String parseFile(String fileName);

	Long getCounts();

	String getParserStatus(String jobId);
}
