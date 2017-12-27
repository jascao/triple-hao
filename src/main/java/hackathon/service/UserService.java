package hackathon.service;

import hackathon.pojo.RequestStatus;
import sun.misc.Request;

/**
 * Created by jxu on 2017/12/27.
 */
public interface UserService {

    void parseFile();

    Long getCounts();

    RequestStatus getParserStatus(int jobId);
}
