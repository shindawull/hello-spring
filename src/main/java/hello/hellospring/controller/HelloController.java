package hello.hellospring.controller;

import jdk.jfr.Frequency;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody  //* http에서 헤더와바디 부분 중 바디부분에 데이터를 직접 넣어주겠다라는 의미임.
    // 응답 바디부분에 직접 데이터값을 넣어주겠다라는 의미임.
    public String helloString(@RequestParam("name") String name){
        // 무식한방식으로 그대로 내려줌.ㅋㅋ쓸일없음
        return "hello" + name; // "hello spring"
    }

    //데이터를 내놓으라고 할 경우. 많이 씀
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //hello-api에 사용할 객체
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }


}
