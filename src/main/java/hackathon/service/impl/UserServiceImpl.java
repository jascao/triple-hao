package hackathon.service.impl;

import hackathon.pojo.RequestStatus;
import hackathon.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by jxu on 2017/12/27.
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void parseFile() {

    }

    @Override
    public Long getCounts() {
        return 20L;
    }

    @Override
    public RequestStatus getParserStatus(int jobId) {
        return RequestStatus.DONE;
    }
}
