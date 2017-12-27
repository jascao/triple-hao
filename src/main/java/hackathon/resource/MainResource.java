package hackathon.resource;

import hackathon.pojo.RequestStatus;
import hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jxu on 2017/12/27.
 */
@Controller
public class MainResource {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/fileParser", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void fileParser()  {
        userService.getCounts();
    }

    @RequestMapping(value = "/getCounts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Long getCounts()  {
        return userService.getCounts();
    }

    @RequestMapping(value = "/status/{jobId}", method = RequestMethod.GET)
    public @ResponseBody
    RequestStatus getParserStatus(@PathVariable("jobId") int jobId) {
        return userService.getParserStatus(jobId);
    }

}
