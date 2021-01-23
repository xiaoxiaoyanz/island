package com.wucc.island.common.util.export;

import java.util.HashMap;
import java.util.Map;

import com.wucc.island.common.util.ReflectionUtils;
import org.springframework.stereotype.Service;



public class DEMO {

    public static void main(String[] args) {
        Parent student = new Student();

        ReflectionUtils.setValue(Student.class, "name", student, "张三");
        Object value = ReflectionUtils.getValue(Student.class, "name", student);
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(map.getClass());
        System.out.println(student.getClass().getAnnotation(Service.class));
        System.out.println(value.toString());
    }

}

//@Service
class Student extends Parent {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Parent {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
