package java9;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VariableHandles {
  @SuppressWarnings("FieldMayBeFinal")
  private int testVariable = 4711;

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    VariableHandles variableHandles = new VariableHandles();

    // Hole ein Handle für das private Attribut dieser Klasse
    VarHandle varHandleForTestVariable =
        MethodHandles.privateLookupIn(VariableHandles.class, MethodHandles.lookup())
            .findVarHandle(VariableHandles.class, "testVariable", int.class);

    // Lesender Zugriff
    Object testVariableValue = varHandleForTestVariable.get(variableHandles);
    System.out.println(testVariableValue);

    // Schreibender Zugriff
    varHandleForTestVariable.set(variableHandles, 15);
    testVariableValue = varHandleForTestVariable.get(variableHandles);
    System.out.println(testVariableValue);
  }
}
