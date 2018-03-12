package home.pb.springwebmvc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.junit.*;

import java.util.Date;

public class JsondoubleTest {
    @BeforeClass
    public static void beforeClass(){
        System.out.println("this is @BeforeClass");
    }
    @Before
    public void before(){
        System.out.println("this is @Before");
    }
    @After
    public void after(){
        System.out.println("this is @After");
    }
    @AfterClass
    public static void afterClass(){
        System.out.println("this is @AfterClass");
    }

    @Test
    public void test1(){

        Student student = new Student();

        student.setName("baidu");
        student.setDate(new Date());

        System.out.println(JSONSerializer.toJSON(student));
        System.out.println(JSONObject.fromObject(student));
    }
    @Test
    public void test2(){

        Student student = new Student();

        student.setDate(new Date());
        student.setName("sansan");
//        student.setStudent(new Student());

        JsonConfig config = new JsonConfig();
        DateJsonValueProcessor djvp = new DateJsonValueProcessor();
        config.registerJsonValueProcessor(Date.class,djvp);
//        config.setExcludes(new String[]{"student"});
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

        System.out.println(JSONObject.fromObject(student,config));
    }
    @Test
    public  void test3(){
        Student student1 = new Student();
        student1.setDate(new Date());
        student1.setName("student1");
        Student student2 = new Student();
        student2.setDate(new Date());
        student2.setName("student2");
        JSONArray array = new JSONArray();
        array.add(student1);
        array.add(student2);
        JSONObject tmp = new JSONObject();
        tmp.put("array",array);
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{});
        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor());
        System.out.println(JSONObject.fromObject(tmp,config));

    }


}
