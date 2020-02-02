package com.api.server;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class GetPath {

    @Test
    public void path() throws IOException {
        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath() ;
        System.out.println(courseFile);

    }

    /*
    第一种：
File f = new File(this.getClass().getResource("/").getPath());
System.out.println(f);
结果:
C:\Documents%20and%20Settings\Administrator\workspace\projectName\bin
获取当前类的所在工程路径;
如果不加“/”
File f = new File(this.getClass().getResource("").getPath());
System.out.println(f);
结果：
C:\Documents%20and%20Settings\Administrator\workspace\projectName\bin\com\test
获取当前类的绝对路径；

第二种：
File directory = new File("");//参数为空
String courseFile = directory.getCanonicalPath() ;
System.out.println(courseFile);
结果：
C:\Documents and Settings\Administrator\workspace\projectName
获取当前类的所在工程路径;

第三种：
URL xmlpath = this.getClass().getClassLoader().getResource("selected.txt");
System.out.println(xmlpath);
结果：
file:/C:/Documents%20and%20Settings/Administrator/workspace/projectName/bin/selected.txt
获取当前工程src目录下selected.txt文件的路径

第四种：
System.out.println(System.getProperty("user.dir"));
结果：
C:\Documents and Settings\Administrator\workspace\projectName
获取当前工程路径

第五种：
System.out.println( System.getProperty("java.class.path"));
结果：
C:\Documents and Settings\Administrator\workspace\projectName\bin
获取当前工程路径
     */

}
