package java24;

import static java.lang.classfile.ClassFile.ACC_FINAL;
import static java.lang.classfile.ClassFile.ACC_PRIVATE;
import static java.lang.classfile.ClassFile.ACC_PUBLIC;
import static java.lang.constant.ConstantDescs.CD_Object;
import static java.lang.constant.ConstantDescs.CD_String;
import static java.lang.constant.ConstantDescs.CD_void;
import static java.lang.constant.ConstantDescs.INIT_NAME;

import java.lang.classfile.Annotation;
import java.lang.classfile.AnnotationElement;
import java.lang.classfile.AnnotationValue;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.CodeBuilder;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;

public class ClassFileAPI {
  public final static String COMPILER_OUTPUT_WITH_PACKAGE = "out/production/changelog-java-version/java24/";

  private static ClassDesc classDesc;

  static void process() throws Exception {
    classDesc = ClassDesc.of("java24", "User");
    System.out.println(classDesc);

    ClassFile.of().buildTo(
        Path.of(COMPILER_OUTPUT_WITH_PACKAGE + classDesc.displayName() + ".class"),
        classDesc,
        ClassFileAPI::createUserClass
    );

    var userClassName = classDesc.packageName() + "." + classDesc.displayName();
    var userClass = ClassFileAPI.class.getClassLoader().loadClass(userClassName);
    System.out.println(userClass);
    var constructor = userClass.getConstructor(String.class);
    System.out.println(constructor.newInstance("Klaus"));
  }

  private static void createUserClass(ClassBuilder classBuilder) {
    classBuilder
        .withFlags(ACC_PUBLIC)
        .withField("name", CD_String, ACC_FINAL | ACC_PRIVATE)
        .withMethodBody( // Create Constructor
            INIT_NAME,
            MethodTypeDesc.of(CD_void, CD_String),
            ACC_PUBLIC,
            ClassFileAPI::createConstructor)
        .withMethodBody( // Override toString()
            "toString",
            MethodTypeDesc.of(CD_String),
            ACC_PUBLIC,
            ClassFileAPI::overrideToString)
        .with(SourceFileAttribute.of(classDesc.displayName() + ".java"))
        .with(
            RuntimeVisibleAnnotationsAttribute.of(
                Annotation.of(
                    ClassDesc.of("javax.annotation.processing", "Generated"),
                    AnnotationElement.of("value", AnnotationValue.ofString("ClassFileAPI")))));
  }

  private static void createConstructor(CodeBuilder codeBuilder) {
    codeBuilder
        .aload(0) // load this onto stack
        .invokespecial(CD_Object, INIT_NAME, MethodTypeDesc.of(CD_void)) // call super constructor
        .aload(0) // load this onto stack
        .aload(1) // load name onto stack
        .putfield(classDesc, "name", CD_String) // Set this.name to the last value on the stack
        .return_(); // Return nothing
  }

  private static void overrideToString(CodeBuilder codeBuilder) {
    codeBuilder
        .aload(0) // load this onto stack
        .getfield(classDesc, "name", CD_String) // Push this.name onto stack
        .areturn(); // Return the value on the stack
  }
}
