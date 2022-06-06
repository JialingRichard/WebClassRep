package com.example.restapi4;

import net.minidev.json.JSONObject;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
class test {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("单次测试即将开始...");
        // 初始化MockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @AfterEach
    public void over() throws Exception {
        System.out.println("单次测试已经结束...");
    }

    /**
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")或者post()构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
     */

    @Test
    void loadHello() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user")
                        //.param("name", "xiaoke") 如果是post方式，则要添加param,构造body
                        .accept(MediaType.APPLICATION_JSON))
                //等同于Assert.assertEquals(200,status);
                .andExpect(status().isOk())
                //等同于 Assert.assertEquals("hello world!",content);
                //.andExpect(MockMvcResultMatchers.content().string("hello test!"))
                .andDo(print())
                .andReturn();
        //得到返回代码
        int status = mvcResult.getResponse().getStatus();
        //得到返回结果
        String content = mvcResult.getResponse().getContentAsString();
        //断言，判断返回代码是否正确
        assertEquals(200, status);
//        //断言，判断返回的值是否正确
//        assertEquals("hello test!", content);
        System.out.println("status:"+status+"\ncontent:"+content);
    }

    @Test
    public void loadPrivatePage() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/privatePageName")
//                        .param("a", "1")        // 添加参数
//                        .param("b", "2")
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值

        String result2 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/privatePageName")
                        .param("newPageName", "这是一个新的主页名称")        // 添加参数
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值

    }

    @Test
    public void loadBasicInfo() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/basicInfo")
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值


        Map<String, String> map = new HashMap<>();
        map.put("age", "123");
        map.put("name", "tom");
        map.put("college","scucs");
        map.put("school","scu");
        map.put("studentNum","123321");
        String content = JSONObject.toJSONString(map);

        String result2 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/basicInfo")
                        //.param("newPageName", "这是一个新的主页名称")        // 添加参数
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值

    }

    @Test
    public void loadLogin() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("acountName", "zhangsan");
        map.put("acountPasswd", "123");

        String content = JSONObject.toJSONString(map);

        String result2 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        //.param("newPageName", "这是一个新的主页名称")        // 添加参数
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值

    }

    @Test
    public void loadAcademic() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/academic")
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值


        Map<String, String> map = new HashMap<>();
        map.put("academicExp", "new exp");
        map.put("oldAcademic", "tom???");

        String content = JSONObject.toJSONString(map);

        String result2 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/academic")
                        //.param("newPageName", "这是一个新的主页名称")        // 添加参数
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值

    }

    @Test
    public void loadProExp() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/proExp")
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值


        String content = "{\"ll\":[{\"name\":\"newPro\",\"description\":\"1\"},{\"name\":\"chinese\",\"description\":\"5\"},{\"name\":\"eng\",\"description\":\"6\"}]}";

        String result2 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/proExp")
                        //.param("newPageName", "这是一个新的主页名称")        // 添加参数
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值

    }

    @Test
    public void loadGrade() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/grade")
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值


        String content = "{\"ll\":[{\"name\":\"cal\",\"description\":\"1000\"},{\"name\":\"chinese\",\"description\":\"5\"},{\"name\":\"eng\",\"description\":\"6\"}]}";

        String result2 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/grade")
                        //.param("newPageName", "这是一个新的主页名称")        // 添加参数
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))      // 设置数据格式
                .andDo(print())     // 打印输出发出请求的详细信息
                .andExpect(status().isOk())     // 对返回值进行断言
                .andReturn().getResponse().getContentAsString();        // 获取方法的返回值

    }





}
