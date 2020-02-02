package com.api.server;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@ApiOperation(value = "/", tags = "处理前端传来的Json数据")
@RequestMapping("/api/v1")
public class HandleJson {

    @RequestMapping(value = "/json/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "返回想要的Json格式", httpMethod = "POST")
    public String returnJson(HttpServletRequest request, @RequestBody String jsonString) throws IOException {

        Cookie[] cookies = request.getCookies();
        String fileName = cookies[0].getValue();
        System.out.println(fileName);
        if (Objects.isNull(fileName))
            return "Please provide file name...!";
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/WebAPI-5/src/main/jsonFiles/" + fileName + ".json";
        System.out.println(filePath);
        FileUtils.writeStringToFile(new File(filePath), jsonString);
        System.out.println(jsonString);

        return jsonString;
    }
}