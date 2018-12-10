package com.ngsky.tools.index.controller;

import com.alibaba.fastjson.JSONObject;
import com.ngsky.tools.domain.User;
import com.ngsky.tools.repository.UserRepository;
import com.ngsky.tools.utils.RandomCharUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <dl>
 * <dt>IndexController</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/18/2018 1:21 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<User> userList = userRepository.findAll();
        logger.info("userList:{}", JSONObject.toJSONString(userList));
        response.getWriter().write(JSONObject.toJSONString(userList));
    }

    @RequestMapping("/batch/import")
    public void batchImport() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "INSERT INTO user(mpath,mname,mdesc) VALUES (?,?,?)";
                for (int i = 0; i < 3; i++) {
                    String mpath = RandomCharUtil.randomAllStr(false, false, false);
                    String mname = RandomCharUtil.randomAllStr(false, false, false);
                    String mdesc = "path" + i % 2;
                    System.out.println("mpath:" + mpath + ",mname:" + mname + ",mdesc:" + mdesc);
                    userRepository.save(new User(mname, mpath, mdesc));
//                    Query query = em.createNativeQuery(sql);
//                    query.setParameter(1, "fjdaskfjklas");
//                    query.setParameter(2, "fjaskfjdsakfj");
//                    query.setParameter(3, "fdasfdasf");
//                    query.executeUpdate();
                }

                List<User> userList = userRepository.findAll();
                System.out.println(JSONObject.toJSONString(userList));
            }
        }).start();

    }
}
