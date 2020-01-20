# Spring Boot Study
* study

## 환경
* Spring version 5.x
* Boot 2.2
* Open JDK 11
* Junit5 + AssertJ + Mockito


## Mission
- 1주차 미션 : [네이버 Open API 연동 및 컨텐츠 API 구현](https://github.com/ohtaeg/boot-study-friday/blob/master/docs/mission1.md)
- 2주차 미션 : [2주차 미션](https://github.com/ohtaeg/boot-study-friday/blob/master/docs/mission2.md)
<br>

## I learn
### @SpringBootApplication
- @SpringBootConfiguration
- @ComponentScan
- @EnableAutoConfiguration <br>
@SpringBootApplication 어노테이션은 위 3가지의 어노테이션을 사용하는것과 같다.

### @EnableAutoConfiguration
- 어플리케이션 등록시, 빈을 두 단계를 거쳐 등록 한다.
    1. @ComponentScan
    2. @EnableAutoConfiguration

- 먼저 @ComponentScan으로 빈이 등록이 되는데, @SpringBootApplication 내부에 @ComponentScan을 살펴보면 filter로 AutoConfiguration을 통해 등록되는 빈들을 제외하라고 설정 되어 있다.

<pre>
    <code>@ComponentScan(</code>
    <code>    excludeFilters = {@Filter(</code>
    <code>        type = FilterType.CUSTOM,</code>
    <code>        classes = {TypeExcludeFilter.class}</code>
    <code>    ), @Filter(</code>
    <code>        type = FilterType.CUSTOM,</code>
    <code>        classes = {AutoConfigurationExcludeFilter.class} // <-</code>
    <code>    )}</code>
    <code>)</code>
</pre>

- @ComponentScan으로 빈이 등록 된 다음 @EnableAutoConfiguration이 추가적인 빈들을 등록 한다. (ex ServletWebServerFactory )
- @EnableAutoConfiguration은 추가된 jar dependency 기반으로 Spring application을 자동으로 설정하는 것을 시도한다
- @EnableAutoConfiguration이 빈을 등록하는 방법은 <br>
org.springframework.boot:spring-boot-autoconfigure Library 안의 META-INF/spring.factories 라는 파일에 있는 값들을 읽어 등록 한다. <br>
[spring.factories 파일](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot-autoconfigure/src/main/resources/META-INF/spring.factories)
안에 키 밑에 설정되어있는 value(클래스들)들은 다 적용이 된다. <br> 
<pre>
    # Initializers
    org.springframework.context.ApplicationContextInitializer=\
    org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\
    org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener
    
    # Application Listeners
    org.springframework.context.ApplicationListener=\
    org.springframework.boot.autoconfigure.BackgroundPreinitializer
    
    # Auto Configuration Import Listeners
    org.springframework.boot.autoconfigure.AutoConfigurationImportListener=\
    org.springframework.boot.autoconfigure.condition.ConditionEvaluationReportAutoConfigurationImportListener
    
    # Auto Configuration Import Filters
    org.springframework.boot.autoconfigure.AutoConfigurationImportFilter=\
    org.springframework.boot.autoconfigure.condition.OnBeanCondition,\
    org.springframework.boot.autoconfigure.condition.OnClassCondition,\
    org.springframework.boot.autoconfigure.condition.OnWebApplicationCondition
    
    # Auto Configure
    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
    org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
    ....
    ....
</pre>

- 파일 내용을 보면 다 클래스로 명시 되어 있고 각 클래스 내부에는 @Configuration 어노테이션을 통해 빈 설정파일로 등록 된다. <br>
  다만 @ConditionalOn.... 어노테이션에 맞는 조건에 따라 등록되거나 등록이 안되기도 한다.  <br>
    ex) WebMvcAutoConfiguration.class
    - @ConditionalOnWebApplication(type = Type.SERVLET) : 웹 어플리케이션 타입이 servlet일 때만 AutoConfigure
    - @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class}) <br>
      : 클래스패스에 Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class가 있을 때 AutoConfigure
    <pre>
        <code>@Configuration(</code>
        <code>    proxyBeanMethods = false</code>
        <code>)</code>
        <code>@ConditionalOnWebApplication(type = Type.SERVLET)</code>
        <code>@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})</code>
        <code>@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})</code>
        <code>@AutoConfigureOrder(-2147483638)</code>
        <code>@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})</code>
        <code>public class WebMvcAutoConfiguration {</code>
        <code>  ...</code>
        <code>}</code>
    </pre>

- 만약 @ComponentScan과 @EnableAutoConfigure가 같은 빈을 등록한다면 @ComponentScan 때 등록한 빈을 @EnableAutoConfigure가 덮어 씌울 것이다. <br>
  덮어쓰는 걸 방지하려면 @ConditionalOnMissingBean를 통해 빈을 등록 못했을때 등록하도록 설정해준다.

#### 내장 웹서버
- 내장 서블릿 컨테이너도 자동설정의 일부분이다.
- 스프링 부트는 내장 서블릿 컨테이너를 쉽게 사용할수 있게, 스프링 프레임워크를 쉽게 사용할 수 있게하는 Tool이다.
- 자바로 내장 웹서버인 톰캣 객체를 생성할 수 있다.
<pre>
    <code>Tomcat tomcat = new Tomcat(); // 톰캣 객체 생성</code>
    <code>tomcat.setPort(8081); // 포트 설정</code>
    <code>Context context = tomcat.addContext("/", "/"); // 톰캣에 컨텍스트 추가</code>
    <code></code>
    <code>// 서블릿 만들기</code>
    <code>HttpServlet servlet = new HttpServlet() {</code>
    <code>    @Override</code>
    <code>    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {</code>
    <code>        PrintWriter writer = resp.getWriter();</code>
    <code>        writer.println("&lt;html&gt;");</code>
    <code>        writer.println("&lt;head&gt;");</code>
    <code>        writer.println("    &lt;title&gt;mission1&lt;/title&gt;");</code>
    <code>        writer.println("&lt;/head&gt;");</code>
    <code>        writer.println("&lt;body&gt;hello world&lt;/body&gt;");</code>
    <code>        writer.println("&lt;/html&gt;");</code>
    <code>    }</code>
    <code>};</code>
    <code>String servletName = "apiServlet";</code>
    <code>tomcat.addServlet("/", servletName, servlet); // 톰캣에 서블릿 추가</code>
    <code>context.addServletMappingDecoded("/api", servletName); // 컨텍스트에 서블릿 매핑</code>
    <code>tomcat.getConnector();</code>
    <code>tomcat.start(); // 톰캣 실행</code>
    <code>tomcat.getServer().await(); // 톰캣 대기</code>
</pre>

- 다만 별도의 서블릿들을 다 정의해야 되기 때문에 이런 과정을 설정하고 실행하게 해주는 것이 스프링 부트의 기능 중 하나이다.
- 그렇다면 위 소스코드 처럼 was 설정은 어디에 있고 어떤 원리로 스프링 부트가 실행할 수 있는건가?
    - 위에서 배웠던 @AutoConfigure를 통해 자동 설정이 된다.
    - org.springframework.boot:spring-boot-autoconfigure Library META-INF/spring.factories <br>
    ㄴ ServletWebServerFactoryAutoConfiguration.class -> 서블릿 웹서버 생성 <br>
    ㄴ DispatcherServletAutoConfigure.class -> 서블릿 만들고 등록
    - 스프링 부트가 실행되면 자동으로 톰캣 객체를 생성하고, 서블릿이 추가가 되고 Web MVC 설정이 되면서 어플리케이션이 동작한다.
    
<br>

#### 언더토우
- 성능이슈를 위해 톰캣보단 언더토우를 사용하자. https://okky.kr/article/552112
- 공식 문서를 참고하여 언더토우로 변경해보자. [Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-embedded-web-servers)
<pre>
    <code>
        configurations {
            compile.exclude module: 'spring-boot-starter-tomcat'
        }
        dependencies {
            implementation 'org.springframework.boot:spring-boot-starter-web'
            compile 'org.springframework.boot:spring-boot-starter-undertow'
        }
    </code>
</pre>
- 만약 내 어플리케이션을 웹 어플리케이션으로 만들기 싫은데 의존성에 웹관련 의존성 모듈이 있다면 스프링 부트는 자동으로 웹 어플리케이션으로 만들려고 한다.
- 프로퍼티 설정을 통해 클래스패스에 웹 관련 의존성이 있다 하더라도, 웹 어플리케이션으로 안바꿀 수 있다.
    - spring.main.web-application-type=none
- 프로퍼티 설정을 통해 포트 변경 및 랜덤 포트로 변경할 수 있다.
    - server.port = 8081
    - server.port = 0 // 랜덤 포트
    




