package com.demo.wpq.mydemo.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.wpq.mydemo.bean.HotBean;
import com.demo.wpq.mydemo.json.Person;

import static com.alibaba.fastjson.JSON.parseObject;

public class TestFastJson {

    public static void main(String[] args) {
        //
        String json = "{\"name\":\"fastjson\",\"age\":10}";
        Person p1 = parseObject(json, Person.class);
        System.out.println(p1.toString());

        //
        JSONObject o2 = new JSONObject();
        o2.put("name", "fastjson2");
        o2.put("age", 20);
        System.out.println(o2.toString());
        Person p2 = parseObject(o2.toString(), Person.class);
        System.out.println(p2.toString());

        //
        Person p3 = new Person();
        p3.setName("fastjson3");
        p3.setAge(30);
        String o3 = JSON.toJSONString(p3);
        System.out.println(o3);

        String hotJson = "{\"msg\":\"获取成功！\",\"code\":1,\"data\":{\"list\":[{\"name\":\"三只松鼠\"}]}}";
        HotBean hotBean = parseObject(hotJson, HotBean.class);
        System.out.println(hotBean.toString());


        String data = "{\n" +
                "  \"status\" : 1,\n" +
                "  \"errorCode\" : \"\",\n" +
                "  \"message\" : null,\n" +
                "  \"data\" : {\n" +
                "    \"preWeekScore\" : 0.0,\n" +
                "    \"thisWeekScore\" : 0.0,\n" +
                "    \"taskName\" : \"课堂点名\",\n" +
                "    \"taskTime\" : \"15秒\",\n" +
                "    \"taskColor\" : 1,\n" +
                "    \"isShow\" : 1\n" +
                "  },\n" +
                "  \"erroCode\" : null\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(data);
//        String string = JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
        System.out.println(jsonObject);
    }
}
