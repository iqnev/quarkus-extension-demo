package org.iqnev.demo.client.extension.deployment;

import io.quarkus.arc.deployment.GeneratedBeanBuildItem;
import io.quarkus.arc.deployment.GeneratedBeanGizmoAdaptor;
import io.quarkus.arc.processor.MethodDescriptors;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.gizmo.BytecodeCreator;
import io.quarkus.gizmo.ClassCreator;
import io.quarkus.gizmo.FieldCreator;
import io.quarkus.gizmo.FieldDescriptor;
import io.quarkus.gizmo.Gizmo;
import io.quarkus.gizmo.Gizmo.JdkList;
import io.quarkus.gizmo.MethodCreator;
import io.quarkus.gizmo.MethodDescriptor;
import io.quarkus.gizmo.ResultHandle;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.iqnev.demo.client.extension.runtime.AnimalService;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.objectweb.asm.Opcodes;

class DemoClientExtensionProcessor {

  private static final String FEATURE = "demo-client-extension";

  @BuildStep
  FeatureBuildItem feature() {
    return new FeatureBuildItem(FEATURE);
  }

  @BuildStep
  void generateAnimalService(BuildProducer<GeneratedBeanBuildItem> generatedBeanClasses,
      CombinedIndexBuildItem index) {

    GeneratedBeanGizmoAdaptor gizmoAdaptor = new GeneratedBeanGizmoAdaptor(
        generatedBeanClasses);

    try (ClassCreator classCreator = ClassCreator.builder()
        .className("org.iqnev.demo.client.extension.runtime.GeneratedAnimalService")
        .interfaces(AnimalService.class)
        .classOutput(gizmoAdaptor)
        .build()) {

      classCreator.addAnnotation(ApplicationScoped.class);

      // Add the private final field List<String> animals;
      FieldCreator animalsField = classCreator.getFieldCreator("animals", List.class);
      animalsField.setModifiers(Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL);

      // Generate the constructor
      MethodCreator constructor = classCreator.getMethodCreator("<init>", void.class);

      // Invoke super()
      constructor.invokeSpecialMethod(
          MethodDescriptors.OBJECT_CONSTRUCTOR,
          constructor.getThis());

      // ArrayList list = new ArrayList()
//      ResultHandle arr = constructor.newArray(String.class, 3);
//      constructor.writeArrayValue(arr, 0, constructor.load("dog"));
//      constructor.writeArrayValue(arr, 1, constructor.load("cat"));
//      constructor.writeArrayValue(arr, 2, constructor.load("tiger"));
//
//      ResultHandle listOfAnimals = constructor.invokeStaticInterfaceMethod(
//          MethodDescriptor.ofMethod(List.class, "of", List.class, Object[].class), arr);

      JdkList listOps = Gizmo.listOperations(constructor);
      ResultHandle listOfAnimals = listOps.of(constructor.load("dog"), constructor.load("cat"));

      constructor.writeInstanceField(animalsField.getFieldDescriptor(), constructor.getThis(),
          listOfAnimals);

      constructor.returnValue(null);

      // Implement the isAnimal method
      MethodCreator isAnimalMethod = classCreator.getMethodCreator("isAnimal", boolean.class,
          String.class);
      ResultHandle containsResult = isAnimalMethod.invokeInterfaceMethod(
          MethodDescriptor.ofMethod(List.class, "contains", boolean.class, Object.class),
          isAnimalMethod.readInstanceField(animalsField.getFieldDescriptor(),
              isAnimalMethod.getThis()),
          isAnimalMethod.getMethodParam(0)
      );

      isAnimalMethod.returnValue(containsResult);
    }
  }
}
