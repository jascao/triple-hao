package hackathon.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hackathon.pojo.CustomerSearch;
import hackathon.service.UserService;

/**
 * Created by jxu on 2017/12/27.
 */
@RestController
public class MainResource {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/fileParser/{filePath}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String fileParser(@PathVariable("filePath") String filePath) {
		return userService.parseFile(filePath);
	}

	@RequestMapping(value = "/getCounts", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Long getCounts(@RequestBody CustomerSearch customer) {
		return userService.getCounts(customer);
	}

	@RequestMapping(value = "/status/{jobId}", method = RequestMethod.GET)
	public @ResponseBody String getParserStatus(@PathVariable("jobId") String jobId) {
		return userService.getParserStatus(jobId);
	}

}
