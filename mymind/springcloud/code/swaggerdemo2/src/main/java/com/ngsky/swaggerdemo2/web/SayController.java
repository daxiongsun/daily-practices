package com.ngsky.swaggerdemo2.web;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 一个用来测试swagger注解的控制器
 * 注意@ApiImplicitParam的使用会影响程序运行，如果使用不当可能造成控制器收不到消息
 *
 * @author SUNF
 */
@Controller
@RequestMapping("/say")
public class SayController {

    @ResponseBody
    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    public String getUserName(@RequestParam Integer userNumber) {
        System.out.println("userNumber:" + userNumber);
        if (userNumber == 1) {
            return "张三丰";
        } else if (userNumber == 2) {
            return "慕容复";
        } else {
            return "未知";
        }
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    public String updatePassword(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "password") String password,
                                 @RequestParam(value = "newPassword") String newPassword) {
        if (userId <= 0 || userId > 2) {
            return "未知的用户";
        }
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword)) {
            return "密码不能为空";
        }
        if (password.equals(newPassword)) {
            return "新旧密码不能相同";
        }
        return "密码修改成功!";
    }
}
