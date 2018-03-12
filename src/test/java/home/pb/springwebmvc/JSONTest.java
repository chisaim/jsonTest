package home.pb.springwebmvc;

import net.sf.json.*;
import net.sf.json.util.CycleDetectionStrategy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONTest {

    private static Grade grade;

    private static Student student;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("-----测试方法执行之前就执行-----");
        student = new Student();
        grade = new Grade();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("-----测试方法执行之前就执行-----");
        student = null;
        grade = null;

    }

    @Test//测试java对象转成Json的格式
    public void test() {
        System.out.println(JSONSerializer.toJSON(grade));
    }

    @Test//测试数据的JSON表现形式
    public void test2() {
        grade.setId(1);
        grade.setName("java");
        Student student = new Student();
        Student student2 = new Student();
        student.setName("小王");
        student.setDate(new Date());
        student2.setName("小李");
        student2.setDate(new Date());
        List<Student> list = new ArrayList<Student>();
        list.add(student);
        list.add(student2);
        grade.setStudents(list);
        System.out.println(JSONSerializer.toJSON(grade));
    }

    @Test //static属性是不能被转化为json对象的
    public void test3() {
//        student.setDate(new Date());
        student.setName("admin");
//        student.setAge(18);
        System.out.println(JSONSerializer.toJSON(student));
        //如果返回的static，或者返回的类型不确定，那么可以采用map或者自己构建json格式
        JSONObject object = new JSONObject();
//        object.put("age",student.getAge());
        object.put("date", student.getDate());
        object.put("name", student.getName());
        System.out.println(JSONSerializer.toJSON(object));
    }

    @Test //解决自关联的问题
    public void test4() {
        student.setDate(new Date());
        student.setName("admin");
//        student.setStudent(new Student());
        //通过配置JSONConfig来过滤相应的参数,排除密码字段
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"date"});
        /**
         * 设置如果有些字段自关联则过滤
         *  private Student student = this;
         *  STRICT 是否自关联都要转化
         *  LENIENT 如果有自关联对象，则设置为null
         *  NOPROP 如果自关联则忽略属性
         */
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        System.out.println(JSONObject.fromObject(student, config));
    }

    @Test //自己定日期的处理类，来格式化日期数据。
    public void test5() {
        student.setDate(new Date());
        student.setName("admin");
        JsonConfig config = new JsonConfig();
        DateJsonValueProcessor djvp = new DateJsonValueProcessor();
        config.registerJsonValueProcessor(Date.class, djvp);
        System.out.println(JSONObject.fromObject(student,config));
    }

    @Test //
    public void test6(){
        //JSONobject 可以自定义对象，jsonArray可以自定义数组
        JSONObject object = new JSONObject();
        object.put("id",123);
        object.put("name","admin");
        JSONObject object2 = new JSONObject();
        object2.put("id",234);
        object2.put("name","xyz");
        JSONArray array = new JSONArray();
        array.add(object);
        array.add(object2);
        JSONObject tmp = new JSONObject();
        tmp.put("array",array);
        System.out.println(JSONObject.fromObject(tmp));
    }
}
