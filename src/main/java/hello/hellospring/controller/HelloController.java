package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello (Model model) {
        model.addAttribute("data", "hello!!!");
        return "hello"; // hello.html찾아서 페이지 이동 (viewResolver)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 응답 body에다가 리턴값 집적 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 이 데이터만 이동 (HttpMessageConverter)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 리턴하면 디폴트값으로 JSON
        // 메세지 컨버터가 동작 (HttpMessageConverter)
        // 객체 - JsonConverter
        // 스트링 - StringConverter
    }

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
