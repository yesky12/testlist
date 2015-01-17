package com.leo.test.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    /** 类加载器名称 */
    private String       name;
    /** 加载路径 */
    private String       path;

    private final String classType = ".class";

    public MyClassLoader(String name) {
        super();
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        name = name.replace(".", File.separator);
        try {
            is = new FileInputStream(new File(path + name + classType));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (baos != null) baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {
        // MyClassLoader loader1 = new MyClassLoader("loader1");
        // loader1.setPath(System.getProperty("java.class.path"));
        // try {
        // Class clazz = loader1.loadClass("com.mycompany.app.Cat");
        // clazz.newInstance();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // MyClassLoader loader1 = new MyClassLoader("loader1");
        // loader1.setPath("d:\\");
        // try {
        // Class<?> clazz = loader1.loadClass("Animal");
        // loader1 = null;
        // loader1 = new MyClassLoader("loader1");
        // loader1.setPath("d:\\");
        // Class<?> clazz2 = loader1.loadClass("Animal");
        // System.out.println(clazz == clazz2);
        // System.out.println(clazz.hashCode());
        // System.out.println(clazz2.hashCode());
        // } catch (ClassNotFoundException e) {
        // e.printStackTrace();
        // }

        // java.lang.Object

        MyClassLoader loader1 = new MyClassLoader(null, "loader1");
        loader1.setPath("/Users/leo/test/");
        try {
            System.out.println(loader1.getParent());
            Class<?> clazz = loader1.loadClass("com.leo.test.classloader.Animal");
            System.out.println(clazz.getClassLoader());
            System.out.println(Animal.class.getClassLoader());
            Animal animal = (Animal) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // MyClassLoader loader1 = new MyClassLoader("loader1");
        // loader1.setPath("d:\\");
        // try {
        // Class<?> clazz = loader1.loadClass("Animal");
        // Object object = clazz.newInstance();
        // Field field = clazz.getField("a");
        // System.out.println(field.getInt(object));
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }
}
