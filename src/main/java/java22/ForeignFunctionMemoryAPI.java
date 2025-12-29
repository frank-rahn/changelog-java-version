package java22;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.ValueLayout;

public class ForeignFunctionMemoryAPI {
  static void process() throws Throwable {
    var linker = Linker.nativeLinker();

    var stdlib = linker.defaultLookup();

    var printfAddress = stdlib.find("printf").orElseThrow();

    var printfDescriptor = FunctionDescriptor.of(
        ValueLayout.JAVA_INT, ValueLayout.ADDRESS);

    var printfHandle = linker.downcallHandle(
        printfAddress, printfDescriptor);

    try (var heap = Arena.ofConfined()) {
        var cString = heap.allocateFrom("Hello World!");
        var ret = printfHandle.invoke(cString);
        System.out.println(ret);
    }
  }
}
