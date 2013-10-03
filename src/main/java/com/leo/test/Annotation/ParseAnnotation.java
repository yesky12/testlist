package com.leo.test.Annotation;


/**
 * User: lin.gong
 * Date: 12-8-16
 * Time: 下午9:51
 */
public class ParseAnnotation {

    @MyAnnotation(id=1,description = "Filed")
    public UserService userService;

    public ParseAnnotation() throws IllegalAccessException, InstantiationException {
        userService=UserServiceImpl.class.newInstance();
    }

    @MyAnnotation(id=1,description = "methodOne")
    public void methodOne(){

    }

    @MyAnnotation(id=2)
    public void methodTwo(){

    }

    @MyAnnotation(id=3,description = "method three")
    public void methodThree(){}

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        Method[] methods=ParseAnnotation.class.getDeclaredMethods();
//        for(Method method:methods){
//            boolean hasAnnotation=method.isAnnotationPresent(MyAnnotation.class);
//            if(hasAnnotation){
//                MyAnnotation myAnnotation=method.getAnnotation(MyAnnotation.class);
//                System.out.println("@MyAnnotation(id="+myAnnotation.id()+",description="+myAnnotation.description()+")");
//                System.out.println("public "+method.getReturnType()+" "+method.getName());
//            }
//        }

        ParseAnnotation pa=new ParseAnnotation();
        System.out.println(pa.userService.getUserName());


    }
}
