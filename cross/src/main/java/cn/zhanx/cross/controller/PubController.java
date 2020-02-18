package cn.zhanx.cross.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RequestMapping("pub")
@RestController
public class PubController {

    @RequestMapping("/hello")
    public void hello(String callback, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //这里写自己的逻辑过程
        //前端传过来的回调函数名称
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("name", "张三");
        resultMap.put("age", 18);
        resultMap.put("sex", "male");
        resultMap.put("address", new String[]{"地址1","地址2","地址3"});
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了，callback()这个（）里面放的是json格式
        String str = callback + "(" + new Gson().toJson(resultMap) + ")";

        response.getWriter().write(str);
    }

    @GetMapping("/hello2")
    public String hello2(@RequestParam("callback") String callback){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("name", "张三丰");
        resultMap.put("age", 28);
        resultMap.put("sex", "male");
        resultMap.put("education", "大学本科");
        resultMap.put("address", new String[]{"地址一","地址二","地址三"});
        return callback+ "(" + new Gson().toJson(resultMap) + ")";
    }

    @PostMapping("/hello3")
    public JSONObject hello3(@RequestBody JSONObject jsonObject){
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("code",jsonObject.get("code").toString());
        jsonObj.put("id",jsonObject.get("id").toString());
        jsonObj.put("name","李思涵");
        jsonObj.put("address",new String[]{"地址一","地址二","地址三"});
        return jsonObj;
        //return  jsonObj.toJSONString(); 如果函数返回类型是String
    }

    @GetMapping("/hello4")
    public JSONObject hello4(@RequestParam("height") int height){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("name", "张三丰");
        resultMap.put("age", 28);
        resultMap.put("sex", "male");
        resultMap.put("education", "大学本科");
        resultMap.put("height",height);
        resultMap.put("address", new String[]{"地址一","地址二","地址三","地址四"});
        JSONObject jsonObject=new JSONObject(resultMap);
        return jsonObject;

    }
}
