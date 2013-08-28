//package com.gonglin.test.asm;
//import org.springframework.asm.ClassWriter;
//import org.springframework.asm.MethodVisitor;
//import org.springframework.asm.Opcodes;
//import org.springframework.asm.Type;
//import org.springframework.asm.commons.GeneratorAdapter;
//import org.springframework.asm.commons.Method;
//
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//
///**
// * User: lin.gong
// * Date: 12-8-26
// * Time: 下午7:58
// */
//public class Helloworld extends ClassLoader implements Opcodes {
//    public static void main(final String args[]) throws Exception {
//
//        // creates a ClassWriter for the Example public class,
//        // which inherits from Object
//
//        ClassWriter cw = new ClassWriter(true);
//        cw.visit(V1_1, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
//        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,null);
//        mw.visitVarInsn(ALOAD, 0);
//        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
//        mw.visitInsn(RETURN);
//        mw.visitMaxs(1, 1);
//        mw.visitEnd();
//        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
//                "([Ljava/lang/String;)V", null, null);
//        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
//                "Ljava/io/PrintStream;");
//        mw.visitLdcInsn("Hello world!");
//        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
//                "(Ljava/lang/String;)V");
//        mw.visitInsn(RETURN);
//        mw.visitMaxs(2, 2);
//        mw.visitEnd();
//        byte[] code = cw.toByteArray();
//        FileOutputStream fos = new FileOutputStream("Example.class");
//        fos.write(code);
//        fos.close();
//        Helloworld loader = new Helloworld();
//        Class exampleClass = loader
//                .defineClass("Example", code, 0, code.length);
//        exampleClass.getMethods()[0].invoke(null, new Object[] { null });
//
//        // ------------------------------------------------------------------------
//        // Same example with a GeneratorAdapter (more convenient but slower)
//        // ------------------------------------------------------------------------
//
//        cw = new ClassWriter(true);
//        cw.visit(V1_1, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
//        Method m = Method.getMethod("void <init> ()");
//        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, m, null, null,
//                cw);
//        mg.loadThis();
//        mg.invokeConstructor(Type.getType(Object.class), m);
//        mg.returnValue();
//        mg.endMethod();
//        m = Method.getMethod("void main (String[])");
//        mg = new GeneratorAdapter(ACC_PUBLIC + ACC_STATIC, m, null, null, cw);
//        mg.getStatic(Type.getType(System.class), "out", Type
//                .getType(PrintStream.class));
//        mg.push("Hello world!");
//        mg.invokeVirtual(Type.getType(PrintStream.class), Method
//                .getMethod("void println (String)"));
//        mg.returnValue();
//        mg.endMethod();
//        cw.visitEnd();
//        code = cw.toByteArray();
//        loader = new Helloworld();
//        exampleClass = loader.defineClass("Example", code, 0, code.length);
//        exampleClass.getMethods()[0].invoke(null, new Object[] { null });
//    }
//}
