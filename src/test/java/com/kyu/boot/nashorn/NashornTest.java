package com.kyu.boot.nashorn;

import lombok.Data;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

/**
 * @Project : test_project
 * @Date : 2017-06-27
 * @Author : nklee
 * @Description :
 */
public class NashornTest {

    @Test
    public void 나숀() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }

    @Test
    public void loadFileJavascript() throws FileNotFoundException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src\\main\\resources\\js\\demo.js"));
    }

    /**
     * [적용사례]
     * 정확한 것은 아니지만 나름대로의 적용 사례를 분석해 보니
     * front end와 server side 코드가 완전 분리되었을 경우 front는 데이터가 필요할 때 ajax 콜해야 한다.
     * 헌데 이 ajax 요청 처리 시간이 길다면 서버에 부하가 발생하게 된다.
     * 이런 부하를 해소하기 위해 서버 사이드 랜더링이라는 기술이 필요하게 된 것이다.
     * <p>
     * <p>
     * 웹 페이지를 예로 들어보면
     * 1. 웹 브라우저에서 HTTP Request (게시판 리스트 호출)
     * 2. 요청을 받은 서버에서는 스크립트 엔진을 이용하여 js 파일을 파싱하고 특정 함수를 호출한다. (게시판 리스트를 그려주는 함수 callBoardList(); )
     * 3. js 함수 내부에서는 java method를 호출하는 코드가 존재
     * 4. java method가 호출되고 json 데이터가 만들어지면 해당 데이터는 특정 엘리먼트에 append 된다.
     * 5. 웹 브라우저에 HTTP Response
     */
    @Test
    public void callMethod() throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src\\main\\resources\\js\\mylib.js"));
        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction("fun1", "nklee");
        System.out.println(result);
        System.out.println(result.getClass());

        invocable.invokeFunction("fun2", new Date());
        invocable.invokeFunction("fun2", LocalDateTime.now());
        invocable.invokeFunction("fun2", new Person());
    }

    @Test
    public void callJavaMethodFromJavascript() throws FileNotFoundException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src\\main\\resources\\js\\call_java_method.js"));
    }

    public static String fun1(String name) {
        System.out.println("Hi there from Java " + name);
        return "greetings from java";
    }
}


@Data
class Person {

    private String name;
}