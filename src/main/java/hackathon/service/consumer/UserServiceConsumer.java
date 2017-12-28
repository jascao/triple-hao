package hackathon.service.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import hackathon.pojo.CustomerSearch;
import hackathon.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by jxu on 2017/12/27.
 */
@Component
public class UserServiceConsumer {

	@Reference(version = "1.0.0")
    UserService userService;

	public String parseFile(String fileName) {
		return userService.parseFile(fileName);
	}

	public Long getCounts(CustomerSearch customer) {
		return userService.getCounts(customer);
	}

	public String getParserStatus(String jobId) {
		return userService.getParserStatus(jobId);
	}
}
