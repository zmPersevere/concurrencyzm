package com.ming.concurrency.example.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 8:44 PM 2019/4/25
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLoaclController {

    @RequestMapping("/test")
    @ResponseBody
    public Long test(){
        return RequestHolder.getId();
    }

}
