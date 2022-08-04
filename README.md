# ffiUpCallTests

To build, run the following commands:
```
javac --enable-preview --source 18 --add-modules jdk.incubator.foreign --add-opens jdk.incubator.foreign/jdk.internal.foreign=ALL-UNNAMED UpCallTest.java UpcallMethodHandles.java
gcc -c -fpic -g  upcall.c; gcc -shared -o libclinkerffitests.so  upcall.o
```

- To run, run the following commands:
```
java --add-modules jdk.incubator.foreign --enable-native-access=ALL-UNNAMED -Dforeign.restricted=permit -Djava.library.path=/root/openj9-openjdk-jdk18/openj9/ UpCallTest
```


# Test Details
This repository includes tests for FFI upcall thunk/adapter on Z. Some of the tests require manual verification of the output to ensure the behaviour is correct. 
On zLinux the parameter can be in either the caller's frame or in a newly allocated callee frame when `getArgPointer` is invoked. To cover all these scenarios there
are several tests with lots of arguments. We simply print each argument to ensure it is accessed correctly.

The list of tests and a brief description about them is added below:

- A test with void argument and void return type
  - test_print2ArbInts

- Tests with void argument and a valid return type - all combinations
  - test_print2ArbIntsReturnChar
  - test_print2ArbIntsReturnShort
  - test_print2ArbIntsReturnSum
  - test_print2ArbIntsReturnPointer
  - test_print2ArbIntsReturnLong
  - test_print2ArbIntsReturnFloat
  - test_print2ArbIntsReturnDouble
	
- Tests with void argument and struct return type --> All combinations and large structs to test MVC edge cases
  - struct (small) - test_print2ArbIntsReturnStruct
  - struct test_return254BytesFromStructByUpcallMH
  - struct test_return255BytesFromStructByUpcallMH
  - struct test_return256BytesFromStructByUpcallMH
  - struct test_return4079BytesFromStructByUpcallMH
  - struct test_return4080BytesFromStructByUpcallMH
  - struct test_return4081BytesFromStructByUpcallMH

- Tests for each argument type (single argument), return void
  - test_printByte
  - test_printShort
  - test_printInt
  - test_printLong
  - test_printFloat
  - test_printDouble

- Tests for each argument type where there's 2 arguments for each item, return sum
  - test_subtract2Bytes
  - test_subtract2Shorts
  - test_subtrat2Ints
  - test_subtract2Longs
  - test_subtract2Floats
  - test_subtract2Doubles

- Tests for each argument type where there's 6 arguments for each item, return an arbitrary calculation based on the arguments
  - test_pass6Bytes
  - test_pass6Shorts
  - test_pass6Ints
  - test_pass6Longs
  - test_pass6Floats
  - test_pass6Doubles

- Tests for each argument type where there's 10 arguments for each type, return calculation
  - test_pass10Bytes
  - test_pass10Shorts
  - test_pass10Ints
  - test_pass10Longs
  - test_pass10Floats
  - test_pass10Doubles

- Test with 6 GPR arguments and 4 FPR arguments
  - test_GPRLimitTest

- Test with 12 GPR arguments and 4 FPR arguments
  - test_GPROverFlowTest

- Test with 6 GPR arguments and 8 FPR arguments
  - test_FPROverFlowTest

- Test with 16 GPR arguments and 16 FPR arguments of all types in mixed up order
  - test_print16MixedArguments

- Test with structs of size 1,2,4,8 in GPRs and in memory. Structs of size 3 and 7 in GPR (pointer) and in memory. Structs of size 4 and 8 in FPR and in memory.
  - test_structsMixedVariousSizesByUpcallMH

- Test with single element float struct, double element pure float struct, and float data type. All argument types in GPR and in memory when passed as parameters
  - test_printVariousTypesOfFloatArgumentsByUpcallMH --> PASSED

- 
