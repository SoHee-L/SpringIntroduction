package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //Spring이 매서드 호출등을 다해줌.
    @GetMapping("hello") //웹어플리케이션에서 /hello라고 들어오면 이 매서드를 호출해준다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //model에 담으면 view에서 렌더할때 씀.
        model.addAttribute("name", name); //model.addAttribute에서 파라미터로 넘겨온 name을 넘겨봄.
        //"name"이 key
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에 바디부에 이데이터를 내가 직접 넣어주겠다는 의미.
    public String helloString(@RequestParam("name") String name){//String name에 spring이라고 넣으면
        return "hello " + name;  //name이 "hello spring"이라고 바뀜.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //객체를 만들고
        hello.setName(name); //파라미터로 넘어온 네임을 넣음.
        return hello; //hello라는 문자가 아닌 객체를 넘김
    }

    static class Hello { //이렇게 static클래스로 만들면 public class HelloController 클래스 안에서
        //또 클래스를 쓸수있음 자바에서 지원하는 문법. (HelloController.Hello)이런식으로
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
