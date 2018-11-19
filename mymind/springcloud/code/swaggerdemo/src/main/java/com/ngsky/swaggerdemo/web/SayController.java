package com.ngsky.swaggerdemo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 一个用来测试swagger注解的控制器
 * 注意@ApiImplicitParam的使用会影响程序运行，如果使用不当可能造成控制器收不到消息
 *
 * @author SUNF
 */
@Controller
@RequestMapping("/say")
@Api(value = "SayController|一个用来测试swagger注解的控制器")
public class SayController {

    @ResponseBody
    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户编号获取用户姓名", notes = "test: 仅1和2有正确返回")
    @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号", required = true)
    public void getUserName(@RequestParam Integer userNumber,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("*** getUserName ***");
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    @ApiOperation(value = "修改用户密码", notes = "根据用户id修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(paramType = "query", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public void updatePassword(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "password") String password,
                                 @RequestParam(value = "newPassword") String newPassword) {
        System.out.println("*** updatePassword ***");
    }
}
