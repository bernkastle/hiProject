package com.annotation;

@Annotation(id = 2, msg = "test")
public class main {
    public static void main(String[] args) {
        boolean hasAnnotation = main.class.isAnnotationPresent(Annotation.class);
        if (hasAnnotation){
            Annotation annotation = main.class.getAnnotation(Annotation.class);
            System.out.println("Id:" + annotation.id());
            System.out.println("msg:" + annotation.msg());
        }
    }
}
