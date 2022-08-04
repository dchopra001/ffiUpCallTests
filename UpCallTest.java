import java.lang.invoke.MethodHandle;
import jdk.incubator.foreign.NativeSymbol;
import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.NativeSymbol;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.SegmentAllocator;
import jdk.incubator.foreign.SymbolLookup;
import jdk.incubator.foreign.SequenceLayout;
import jdk.incubator.foreign.ValueLayout;
import static jdk.incubator.foreign.ValueLayout.*;
import jdk.incubator.foreign.GroupLayout;
import java.lang.invoke.VarHandle;
import jdk.incubator.foreign.Addressable;

public class UpCallTest
{
	private static CLinker clinker = CLinker.systemCLinker();
        static {
                System.loadLibrary("clinkerffitests");
        }
        private static final SymbolLookup nativeLibLookup = SymbolLookup.loaderLookup();


        public static void main(String[] args) throws Throwable // throws Throwable
        {
                try {
                        test_print2ArbInts();
                        test_print2ArbIntsReturnChar();
                        test_print2ArbIntsReturnShort();
						test_print2ArbIntsReturnSum();
                        test_print2ArbIntsReturnPointer();
                        test_print2ArbIntsReturnLong();
                        test_print2ArbIntsReturnFloat();
                        test_print2ArbIntsReturnDouble();
                        test_print2ArbIntsReturnStruct();
                        test_return254BytesFromStructByUpcallMH();
                        test_return255BytesFromStructByUpcallMH();
                        test_return256BytesFromStructByUpcallMH();
                        test_return4079BytesFromStructByUpcallMH();
                        test_return4080BytesFromStructByUpcallMH();
                        test_return4081BytesFromStructByUpcallMH();
						test_printByte();
                        test_printShort();
                        test_printInt();
                        test_printLong();
                        test_printFloat();
                        test_printDouble();
                        test_subtract2Bytes();
                        test_subtract2Shorts();
                        test_subtract2Ints();
                        test_subtract2Longs();
                        test_subtract2Floats();
                        test_subtract2Doubles();
                        test_pass6Bytes();
                        test_pass6Shorts();
                        test_pass6Ints();
                        test_pass6Longs();
                        test_pass6Floats();
                        test_pass6Doubles();
                        test_pass10Bytes();
                        test_pass10Shorts();
                        test_pass10Ints();
                        test_pass10Longs();
                        test_pass10Floats();
                        test_pass10Doubles();
                        test_GPRLimitTest();
                        test_GPROverFlowTest();
                        test_FPROverFlowTest();
                        test_print16MixedArguments();
                        test_structsMixedVariousSizesByUpcallMH();
                        test_printVariousTypesOfFloatArgumentsByUpcallMH();
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
		
		}

        public static void assertEquals(byte result, byte expected) {
                if (result != expected)
                        System.out.println("ERROR!!! Result: " + result + ", Expected: " + expected);
                else
                        System.out.println("PASS!!! Result: " + result + ", Expected: " + expected);
        }
        public static void assertEquals(short result, short expected) {
                if (result != expected)
                        System.out.println("ERROR!!! Result: " + result + ", Expected: " + expected);
                else
                        System.out.println("PASS!!! Result: " + result + ", Expected: " + expected);
        }

        public static void assertEquals(int result, int expected) {
                if (result != expected)
                        System.out.println("ERROR!!! Result: " + result + ", Expected: " + expected);
                else
                        System.out.println("PASS!!! Result: " + result + ", Expected: " + expected);
        }

        public static void assertEquals(long result, long expected) {
                if (result != expected)
                        System.out.println("ERROR!!! Result: " + result + ", Expected: " + expected);
                else
                        System.out.println("PASS!!! Result: " + result + ", Expected: " + expected);
        }

        public static void assertEquals(float result, float expected) {
                if (Math.abs(result - expected) > 0.01f)
                        System.out.println("ERROR!!! Result: " + result + ", Expected: " + expected);
                else
                        System.out.println("PASS!!! Result: " + result + ", Expected: " + expected);
        }

        public static void assertEquals(double result, double expected) {
                if (Math.abs(result - expected) > 0.01D)
                        System.out.println("ERROR!!! Result: " + result + ", Expected: " + expected);
                else
                        System.out.println("PASS!!! Result: " + result + ", Expected: " + expected);
        }

        public static void test_print2ArbInts() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbInts,
                                        FunctionDescriptor.ofVoid(), scope);
                        System.out.println("test_print2ArbInts Results-->");
                        mh.invoke(upcallFuncAddr);
                        oracle_print2ArbInts();
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }

        }

        public static void oracle_print2ArbInts()
        {
                System.out.println("Java Oracle print2ArbInts: Output: 1+1=2");

        }

        public static void test_print2ArbIntsReturnStruct() throws Throwable {
                GroupLayout structLayout = MemoryLayout.structLayout(JAVA_BYTE.withName("elem1"));
                VarHandle byteHandle1 = structLayout.varHandle(PathElement.groupElement("elem1"));

                FunctionDescriptor fd = FunctionDescriptor.of(structLayout, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnStructByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnStruct,
                                        FunctionDescriptor.of(structLayout), scope);

                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        MemorySegment resultSegmt = (MemorySegment)mh.invoke(allocator, upcallFuncAddr);
                        MemorySegment expectedSegmt = oracle_print2ArbIntsReturnStruct(scope);
                        byte result = resultSegmt.get(JAVA_BYTE, 0);
                        byte expected = expectedSegmt.get(JAVA_BYTE, 0);
                        if (result != expected)
                                System.out.println("Error! Result: " + result + " Expected: " + expected);
                        else
                                System.out.println("Pass! Result: " + result + " Expected: " + expected);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static MemorySegment oracle_print2ArbIntsReturnStruct(ResourceScope scope) {
                System.out.println("Java MH print2ArbIntsReturnStruct: Output 1+1=2");
                GroupLayout structLayout = MemoryLayout.structLayout(JAVA_BYTE.withName("elem1"));
                MemorySegment resultSegmt = MemorySegment.allocateNative(structLayout, scope);
                byte val = 25;
                resultSegmt.set(JAVA_BYTE, 0, val);
                return resultSegmt;
        }

        public static void test_print2ArbIntsReturnChar() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_CHAR, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnCharByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnChar,
                                        FunctionDescriptor.of(JAVA_CHAR), scope);
                        System.out.println("test_print2ArbIntsReturnChar Results-->");
                        char result = (char)mh.invoke(upcallFuncAddr);
                        char expected = oracle_print2ArbIntsReturnChar();
                        if (result != expected)
                                System.out.println("Error! Result: " + result + " Expected: " + expected);
                        else
                                System.out.println("Pass! Result: " + result + " Expected: " + expected);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static char oracle_print2ArbIntsReturnChar()
        {
                System.out.println("Java Oracle print2ArbIntsReturnChar: Output: 1+1=2");
                return 'a';
        }

        public static void test_print2ArbIntsReturnShort() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_SHORT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnShortByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnShort,
                                        FunctionDescriptor.of(JAVA_SHORT), scope);
                        System.out.println("test_print2ArbIntsReturnShort Results-->");
                        short result = (short)mh.invoke(upcallFuncAddr);
                        short expected = oracle_print2ArbIntsReturnShort();
                        if (result != expected)
                                System.out.println("Error! Result: " + result + " Expected: " + expected);
                        else
                                System.out.println("Pass! Result: " + result + " Expected: " + expected);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static short oracle_print2ArbIntsReturnShort()
        {
                System.out.println("Java Oracle print2ArbIntsReturnShort: Output: 1+1=2");
                return 6;
        }

        public static void test_print2ArbIntsReturnSum() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_INT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnSumByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnSum,
                                        FunctionDescriptor.of(JAVA_INT), scope);
                        System.out.println("test_print2ArbIntsReturnSum Results-->");
                        int result = (int)mh.invoke(upcallFuncAddr);
                        int expected = oracle_print2ArbIntsReturnSum();
                        if (result != expected)
                                System.out.println("Error! Result: " + result + " Expected: " + expected);
                        else
                                System.out.println("Pass! Result: " + result + " Expected: " + expected);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static int oracle_print2ArbIntsReturnSum()
        {
                System.out.println("Java Oracle print2ArbIntsReturnSum: Output 1+1=2");
                return 2;
        }

        public static void test_print2ArbIntsReturnPointer() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.of(ADDRESS, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnPointerByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnPointer,
                                        FunctionDescriptor.of(ADDRESS), scope);
                        System.out.println("test_print2ArbIntsReturnPointer Results-->");
                        MemoryAddress resultAddr = (MemoryAddress)mh.invoke(upcallFuncAddr);
                        MemoryAddress expectedAddr = (MemoryAddress)oracle_print2ArbIntsReturnPointer(scope);
                        if (expectedAddr.get(JAVA_BOOLEAN, 0) != resultAddr.get(JAVA_BOOLEAN, 0))
                        {
                                System.out.println("Error! Result: " + resultAddr.get(JAVA_BOOLEAN, 0) + " Expected: " + expectedAddr.get(JAVA_BOOLEAN, 0));
                        }
                        else
                        {
                                System.out.println("PASS! Result: " + resultAddr.get(JAVA_BOOLEAN, 0) + " Expected: " + expectedAddr.get(JAVA_BOOLEAN, 0));
                        }
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static Addressable oracle_print2ArbIntsReturnPointer(ResourceScope scope) {
                System.out.println("Java Oracle print2ArbIntsReturnBoolPointer: Output 1+1=2");
                MemorySegment resultSegmt = MemorySegment.allocateNative(JAVA_BOOLEAN, scope);
                resultSegmt.set(JAVA_BOOLEAN, 0, true);
                return resultSegmt.address();
        }

        public static void test_print2ArbIntsReturnLong() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_LONG, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnLongByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnLong,
                                        FunctionDescriptor.of(JAVA_LONG), scope);
                        System.out.println("test_print2ArbIntsReturnLong Results-->");
                        long result = (long)mh.invoke(upcallFuncAddr);
                        long expected = oracle_print2ArbIntsReturnLong();
                        if (result != expected)
                                System.out.println("Error! Result: " + result + " Expected: " + expected);
                        else
                                System.out.println("Pass! Result: " + result + " Expected: " + expected);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static long oracle_print2ArbIntsReturnLong()
        {
                System.out.println("Java Oracle print2ArbIntsReturnLong: Output: 1+1=2");
                return Long.MAX_VALUE;
        }

        public static void test_print2ArbIntsReturnFloat() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_FLOAT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnFloatByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnFloat,
                                        FunctionDescriptor.of(JAVA_FLOAT), scope);
                        System.out.println("test_print2ArbIntsReturnFloat Results-->");
                        float result = (float)mh.invoke(upcallFuncAddr);
                        float expected = oracle_print2ArbIntsReturnFloat();
                        if (result != expected)
                                System.out.println("Error! Result: " + result + " Expected: " + expected);
                        else
                                System.out.println("Pass! Result: " + result + " Expected: " + expected);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static float oracle_print2ArbIntsReturnFloat()
        {
                System.out.println("Java Oracle print2ArbIntsReturnFloat: Output: 1+1=2");
                return 3.14f;
        }

        public static void test_print2ArbIntsReturnDouble() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_DOUBLE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("print2ArbIntsReturnDoubleByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print2ArbIntsReturnDouble,
                                        FunctionDescriptor.of(JAVA_DOUBLE), scope);
                        System.out.println("test_print2ArbIntsReturnDouble Results-->");
                        double result = (double)mh.invoke(upcallFuncAddr);
                        double expected = oracle_print2ArbIntsReturnDouble();
                        if (result != expected)
                                System.out.println("Error! Result: " + result + " Expected: " + expected);
                        else
                                System.out.println("Pass! Result: " + result + " Expected: " + expected);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static double oracle_print2ArbIntsReturnDouble()
        {
                System.out.println("Java Oracle print2ArbIntsReturnDouble: Output: 1+1=2");
                return 6.28;
        }

        public static void test_return254BytesFromStructByUpcallMH() throws Throwable {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(254, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);

                FunctionDescriptor fd = FunctionDescriptor.of(structLayout, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("return254BytesFromStructByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_return254BytesFromStruct,
                                        FunctionDescriptor.of(structLayout), scope);
                        MemorySegment byteArrStruSegment = (MemorySegment)mh.invoke(allocator, upcallFuncAddr);
                        boolean isFail = false;
                        for (int i = 0; i < 254; i++) {
                                if(byteArrStruSegment.get(JAVA_BYTE, i) !=  (byte)i)
                                {
                                        System.out.println("ERROR!!! Result: " + byteArrStruSegment.get(JAVA_BYTE, i) + " Expected: " + i);
                                        isFail = true;
                                        break;
                                }
                        }
                        if (!isFail)
                                System.out.println("PASS!!!!!");

                }
        }

        public static void test_return255BytesFromStructByUpcallMH() throws Throwable {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(255, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);

                FunctionDescriptor fd = FunctionDescriptor.of(structLayout, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("return255BytesFromStructByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_return255BytesFromStruct,
                                        FunctionDescriptor.of(structLayout), scope);
                        MemorySegment byteArrStruSegment = (MemorySegment)mh.invoke(allocator, upcallFuncAddr);
                        boolean isFail = false;
                        for (int i = 0; i < 255; i++) {
                                if(byteArrStruSegment.get(JAVA_BYTE, i) !=  (byte)i)
                                {
                                        System.out.println("ERROR!!! Result: " + byteArrStruSegment.get(JAVA_BYTE, i) + " Expected: " + i);
                                        isFail = true;
                                        break;
                                }
                        }
                        if (!isFail)
                                System.out.println("PASS!!!!!");

                }
        }
        public static void test_return256BytesFromStructByUpcallMH() throws Throwable {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(256, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);

                FunctionDescriptor fd = FunctionDescriptor.of(structLayout, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("return256BytesFromStructByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_return256BytesFromStruct,
                                        FunctionDescriptor.of(structLayout), scope);
                        MemorySegment byteArrStruSegment = (MemorySegment)mh.invoke(allocator, upcallFuncAddr);
                        boolean isFail = false;
                        for (int i = 0; i < 256; i++) {
                                if((byte)(byteArrStruSegment.get(JAVA_BYTE, i) % 256) !=  (byte)(i % 256))
                                {
                                        System.out.println("ERROR!!! Result: " + byteArrStruSegment.get(JAVA_BYTE, i) + " Expected: " + (i%256));
                                        isFail = true;
                                        break;
                                }
                        }
                        if (!isFail)
                                System.out.println("PASS!!!!!");

                }
        }
        public static void test_return4079BytesFromStructByUpcallMH() throws Throwable {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(4079, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);

                FunctionDescriptor fd = FunctionDescriptor.of(structLayout, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("return4079BytesFromStructByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_return4079BytesFromStruct,
                                        FunctionDescriptor.of(structLayout), scope);
                        MemorySegment byteArrStruSegment = (MemorySegment)mh.invoke(allocator, upcallFuncAddr);
                        boolean isFail = false;
                        for (int i = 0; i < 4079; i++) {
                                if((byte)(byteArrStruSegment.get(JAVA_BYTE, i) % 256) !=  (byte)(i % 256))
                                {
                                        System.out.println("ERROR!!! Result: " + byteArrStruSegment.get(JAVA_BYTE, i) + " Expected: " + (i%256));
                                        isFail = true;
                                        break;
                                }
                        }
                        if (!isFail)
                                System.out.println("PASS!!!!!");

                }
        }

        public static void test_return4080BytesFromStructByUpcallMH() throws Throwable {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(4080, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);

                FunctionDescriptor fd = FunctionDescriptor.of(structLayout, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("return4080BytesFromStructByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_return4080BytesFromStruct,
                                        FunctionDescriptor.of(structLayout), scope);
                        MemorySegment byteArrStruSegment = (MemorySegment)mh.invoke(allocator, upcallFuncAddr);
                        boolean isFail = false;
                        for (int i = 0; i < 4080; i++) {
                                if((byte)(byteArrStruSegment.get(JAVA_BYTE, i) % 256) !=  (byte)(i % 256))
                                {
                                        System.out.println("ERROR!!! Result: " + byteArrStruSegment.get(JAVA_BYTE, i) + " Expected: " + (i%256));
                                        isFail = true;
                                        break;
                                }
                        }
                        if (!isFail)
                                System.out.println("PASS!!!!!");

                }
        }

        public static void test_return4081BytesFromStructByUpcallMH() throws Throwable {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(4081, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);

                FunctionDescriptor fd = FunctionDescriptor.of(structLayout, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("return4081BytesFromStructByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_return4081BytesFromStruct,
                                        FunctionDescriptor.of(structLayout), scope);
                        MemorySegment byteArrStruSegment = (MemorySegment)mh.invoke(allocator, upcallFuncAddr);
                        boolean isFail = false;
                        for (int i = 0; i < 4081; i++) {
                                if((byte)(byteArrStruSegment.get(JAVA_BYTE, i) % 256) !=  (byte)(i % 256))
                                {
                                        System.out.println("ERROR!!! Result: " + byteArrStruSegment.get(JAVA_BYTE, i) + " Expected: " + (i%256));
                                        isFail = true;
                                        break;
                                }
                        }
                        if (!isFail)
                                System.out.println("PASS!!!!!");

                }
        }

        public static void test_printByte() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_BYTE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("printByteByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_printByte,
                                        FunctionDescriptor.ofVoid(JAVA_BYTE), scope);
                        mh.invoke((byte)66, upcallFuncAddr);
                        oracle_printByte((byte)66);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_printByte(byte letter)
        {
                System.out.println("Java Oracle printByte: " + letter);
        }

        public static void test_printShort() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_SHORT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("printShortByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_printShort,
                                        FunctionDescriptor.ofVoid(JAVA_SHORT), scope);
                        mh.invoke((short)16, upcallFuncAddr);
                        oracle_printShort((short)16);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_printShort(short arg1)
        {
                System.out.println("Java Oracle printShort: " + arg1);
        }

        public static void test_printInt() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_INT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("printIntByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_printInt,
                                        FunctionDescriptor.ofVoid(JAVA_INT), scope);
                        mh.invoke(65555, upcallFuncAddr);
                        oracle_printInt(65555);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_printInt(int arg1)
        {
                System.out.println("Java Oracle printInt: " + arg1);
        }

        public static void test_printLong() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_LONG, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("printLongByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_printLong,
                                        FunctionDescriptor.ofVoid(JAVA_LONG), scope);
                        mh.invoke(6555566L, upcallFuncAddr);
                        oracle_printLong(6555566L);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_printLong(long arg1)
        {
                System.out.println("Java Oracle printLong: " + arg1);
        }

        public static void test_printFloat() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_FLOAT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("printFloatByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_printFloat,
                                        FunctionDescriptor.ofVoid(JAVA_FLOAT), scope);
                        mh.invoke(3.14f, upcallFuncAddr);
                        oracle_printFloat(3.14f);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_printFloat(float arg1)
        {
                System.out.println("Java Oracle printFloat: " + arg1);
        }

        public static void test_printDouble() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_DOUBLE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("printDoubleByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_printDouble,
                                        FunctionDescriptor.ofVoid(JAVA_DOUBLE), scope);
                        mh.invoke(3.141519, upcallFuncAddr);
                        oracle_printDouble(3.141519);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_printDouble(double arg1)
        {
                System.out.println("Java Oracle printDouble: " + arg1);
        }

        public static void test_subtract2Bytes() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("subtract2BytesByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_subtract2Bytes,
                                        FunctionDescriptor.of(JAVA_BYTE, JAVA_BYTE, JAVA_BYTE), scope);
                        byte result = (byte)(mh.invoke((byte)100, (byte)9, upcallFuncAddr));
                        byte expected = oracle_subtract2Bytes((byte)100, (byte)9);
                        assertEquals(result, expected);
                }
        }

        public static byte oracle_subtract2Bytes(byte arg1, byte arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return (byte)(arg1 - arg2);
        }

        public static void test_subtract2Shorts() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("subtract2ShortsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_subtract2Shorts,
                                        FunctionDescriptor.of(JAVA_SHORT, JAVA_SHORT, JAVA_SHORT), scope);
                        short result = (short)(mh.invoke((short)100, (short)9, upcallFuncAddr));
                        short expected = oracle_subtract2Shorts((short)100, (short)9);
                        assertEquals(result, expected);
                }
        }

        public static short oracle_subtract2Shorts(short arg1, short arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return (short)(arg1 - arg2);
        }

        public static void test_subtract2Ints() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_INT, JAVA_INT, JAVA_INT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("subtract2IntsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_subtract2Ints,
                                        FunctionDescriptor.of(JAVA_INT, JAVA_INT, JAVA_INT), scope);
                        int result = (int)(mh.invoke((int)100, (int)9, upcallFuncAddr));
                        int expected = oracle_subtract2Ints((int)100, (int)9);
                        assertEquals(result, expected);
                }
        }

        public static int oracle_subtract2Ints(int arg1, int arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static void test_subtract2Longs() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("subtract2LongsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_subtract2Longs,
                                        FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG), scope);
                        long result = (long)(mh.invoke((long)100L, (long)9L, upcallFuncAddr));
                        long expected = oracle_subtract2Longs((long)100L, (long)9L);
                        assertEquals(result, expected);
                }
        }

        public static long oracle_subtract2Longs(long arg1, long arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static void test_subtract2Floats() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("subtract2FloatsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_subtract2Floats,
                                        FunctionDescriptor.of(JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT), scope);
                        float result = (float)(mh.invoke((float)100.3f, (float)9.1f, upcallFuncAddr));
                        float expected = oracle_subtract2Floats((float)100.3f, (float)9.1f);
                        assertEquals(result, expected);
                }
        }

        public static float oracle_subtract2Floats(float arg1, float arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static void test_subtract2Doubles() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("subtract2DoublesByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_subtract2Doubles,
                                        FunctionDescriptor.of(JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE), scope);
                        double result = (double)(mh.invoke((double)100.39, (double)9.123, upcallFuncAddr));
                        double expected = oracle_subtract2Doubles((double)100.39, (double)9.123);
                        assertEquals(result, expected);
                }
        }

        public static double oracle_subtract2Doubles(double arg1, double arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static void test_pass6Bytes() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass6BytesByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass6Bytes,
                                        FunctionDescriptor.of(JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE), scope);
                        byte result = (byte)(mh.invoke((byte)2, (byte)4, (byte)6, (byte)8, (byte)10, (byte)12, upcallFuncAddr));
                        byte expected = oracle_pass6Bytes((byte)2, (byte)4, (byte)6, (byte)8, (byte)10, (byte)12);
                        assertEquals(result, expected);
                }
        }

        public static byte oracle_pass6Bytes(byte a1, byte a2, byte a3, byte a4, byte a5, byte a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (byte)(a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static void test_pass6Shorts() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass6ShortsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass6Shorts,
                                        FunctionDescriptor.of(JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT), scope);
                        short result = (short)(mh.invoke((short)2, (short)4, (short)6, (short)8, (short)10, (short)12, upcallFuncAddr));
                        short expected = oracle_pass6Shorts((short)2, (short)4, (short)6, (short)8, (short)10, (short)12);
                        assertEquals(result, expected);
                }
        }

        public static short oracle_pass6Shorts(short a1, short a2, short a3, short a4, short a5, short a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (short)(a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static void test_pass6Ints() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass6IntsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass6Ints,
                                        FunctionDescriptor.of(JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT), scope);
                        int result = (int)(mh.invoke(2, 4, 6, 8, 10, 12, upcallFuncAddr));
                        int expected = oracle_pass6Ints(2, 4, 6, 8, 10, 12);
                        assertEquals(result, expected);
                }
        }

        public static int oracle_pass6Ints(int a1, int a2, int a3, int a4, int a5, int a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static void test_pass6Floats() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass6FloatsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass6Floats,
                                        FunctionDescriptor.of(JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT), scope);
                        float result = (float)(mh.invoke(2.1f, 4.1f, 6.1f, 8.1f, 10.1f, 12.1f, upcallFuncAddr));
                        float expected = oracle_pass6Floats(2.1f, 4.1f, 6.1f, 8.1f, 10.1f, 12.1f);
                        assertEquals(result, expected);
                }
        }

        public static float oracle_pass6Floats(float a1, float a2, float a3, float a4, float a5, float a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static void test_pass6Longs() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass6LongsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass6Longs,
                                        FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG), scope);
                        long result = (long)(mh.invoke(2L, 4L, 6L, 8L, 10L, 12L, upcallFuncAddr));
                        long expected = oracle_pass6Longs(2L, 4L, 6L, 8L, 10L, 12L);
                        assertEquals(result, expected);
                }
        }

        public static long oracle_pass6Longs(long a1, long a2, long a3, long a4, long a5, long a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static void test_pass6Doubles() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass6DoublesByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass6Doubles,
                                        FunctionDescriptor.of(JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE), scope);
                        double result = (double)(mh.invoke(2.1, 4.1, 6.1, 8.1, 10.1, 12.1, upcallFuncAddr));
                        double expected = oracle_pass6Doubles(2.1, 4.1, 6.1, 8.1, 10.1, 12.1);
                        assertEquals(result, expected);
                }
        }

        public static double oracle_pass6Doubles(double a1, double a2, double a3, double a4, double a5, double a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static void test_pass10Bytes() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass10BytesByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass10Bytes,
                                        FunctionDescriptor.of(JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE, JAVA_BYTE), scope);
                        byte result = (byte)(mh.invoke((byte)2, (byte)4, (byte)6, (byte)8, (byte)10, (byte)12, (byte)14, (byte)16, (byte)18, (byte)20, upcallFuncAddr));
                        byte expected = oracle_pass10Bytes((byte)2, (byte)4, (byte)6, (byte)8, (byte)10, (byte)12, (byte)14, (byte)16, (byte)18, (byte)20);
                        assertEquals(result, expected);
                }
        }
        public static byte oracle_pass10Bytes(byte a1, byte a2, byte a3, byte a4, byte a5, byte a6, byte a7, byte a8, byte a9, byte a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (byte)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }

        public static void test_pass10Shorts() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass10ShortsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass10Shorts,
                                        FunctionDescriptor.of(JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT, JAVA_SHORT), scope);
                        short result = (short)(mh.invoke((short)2, (short)4, (short)6, (short)8, (short)10, (short)12, (short)14, (short)16, (short)18, (short)20, upcallFuncAddr));
                        short expected = oracle_pass10Shorts((short)2, (short)4, (short)6, (short)8, (short)10, (short)12, (short)14, (short)16, (short)18, (short)20);
                        assertEquals(result, expected);
                }
        }
        public static short oracle_pass10Shorts(short a1, short a2, short a3, short a4, short a5, short a6, short a7, short a8, short a9, short a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (short)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }

        public static void test_pass10Ints() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass10IntsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass10Ints,
                                        FunctionDescriptor.of(JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT), scope);
                        int result = (int)(mh.invoke((int)2, (int)4, (int)6, (int)8, (int)10, (int)12, (int)14, (int)16, (int)18, (int)20, upcallFuncAddr));
                        int expected = oracle_pass10Ints((int)2, (int)4, (int)6, (int)8, (int)10, (int)12, (int)14, (int)16, (int)18, (int)20);
                        assertEquals(result, expected);
                }
        }
        public static int oracle_pass10Ints(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }

        public static void test_pass10Longs() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass10LongsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass10Longs,
                                        FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG), scope);
                        long result = (long)(mh.invoke((long)2, (long)4, (long)6, (long)8, (long)10, (long)12, (long)14, (long)16, (long)18, (long)20, upcallFuncAddr));
                        long expected = oracle_pass10Longs((long)2, (long)4, (long)6, (long)8, (long)10, (long)12, (long)14, (long)16, (long)18, (long)20);
                        assertEquals(result, expected);
                }
        }
        public static long oracle_pass10Longs(long a1, long a2, long a3, long a4, long a5, long a6, long a7, long a8, long a9, long a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (long)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }

        public static void test_pass10Doubles() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass10DoublesByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass10Doubles,
                                        FunctionDescriptor.of(JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE, JAVA_DOUBLE), scope);
                        double result = (double)(mh.invoke((double)2.5, (double)4.3, (double)6.2, (double)8.2, (double)10.2, (double)12.1, (double)14.1, (double)16.1, (double)18.1, (double)20.1, upcallFuncAddr));
                        double expected = oracle_pass10Doubles((double)2.5, (double)4.3, (double)6.2, (double)8.2, (double)10.2, (double)12.1, (double)14.1, (double)16.1, (double)18.1, (double)20.1);
                        assertEquals(result, expected);
                }
        }

        public static double oracle_pass10Doubles(double a1, double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9, double a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (double)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }

        public static void test_pass10Floats() throws Throwable {
//              MemoryLayout javaArgLayout = MemoryLayout.ofSequence(2, JAVA_BYTE);
//              MemoryLayout C_argLayout = MemoryLayout.ofSequence(javaArgLayout, ADDRESS);
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("pass10FloatsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_pass10Floats,
                                        FunctionDescriptor.of(JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT, JAVA_FLOAT), scope);
                        float result = (float)(mh.invoke((float)2.2, (float)4.3, (float)6.6, (float)8.7, (float)10.8, (float)12.9, (float)14.1, (float)16.2, (float)18.1, (float)20.5, upcallFuncAddr));
                        float expected = oracle_pass10Floats((float)2.2, (float)4.3, (float)6.6, (float)8.7, (float)10.8, (float)12.9, (float)14.1, (float)16.2, (float)18.1, (float)20.5);
                        assertEquals(result, expected);
                }
        }
        public static float oracle_pass10Floats(float a1, float a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9, float a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (float)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }

        public static void test_GPRLimitTest() throws Throwable {

                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_INT, JAVA_BYTE, JAVA_FLOAT, JAVA_SHORT, JAVA_DOUBLE, JAVA_INT, JAVA_DOUBLE, JAVA_LONG, JAVA_FLOAT, JAVA_SHORT, JAVA_BYTE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("GPRLimitTestByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_GPRLimitTest,
                                        FunctionDescriptor.of(JAVA_INT, JAVA_BYTE, JAVA_FLOAT, JAVA_SHORT, JAVA_DOUBLE, JAVA_INT, JAVA_DOUBLE, JAVA_LONG, JAVA_FLOAT, JAVA_SHORT, JAVA_BYTE), scope);
                        int result = (int)(mh.invoke((byte)4, 3.6f, (short)16, 4.795, 33, 6.789, 456L, 3.214f, (short)39, (byte)42, upcallFuncAddr));
                        int expected = oracle_GPRLimitTest((byte)4, 3.6f, (short)16, 4.795, 33, 6.789, 456L, 3.214f, (short)39, (byte)42);
                        assertEquals(result, expected);
                }
        }

        public static int oracle_GPRLimitTest(byte byteArg1, float floatArg1, short shortArg1, double doubleArg1, int intArg1, double doubleArg2, long longArg1, float floatArg2, short shortArg2, byte byteArg2)
        {
                System.out.println("byteArg1: " + byteArg1 + " floatArg1: " + floatArg1 + " shortArg1: " + shortArg1 + " doubleArg1: " + doubleArg1 + " intArg1: " + intArg1 + " doubleArg2: " + doubleArg2 + " longArg1: " + longArg1 + " floatArg2: " + floatArg2 + "shortArg2: " + shortArg2 + " byteArg2: " + byteArg2);
                return 65;
        }

        public static void test_GPROverFlowTest() throws Throwable {
                //                                              Return=I, ParameterList=B,S,I,L,F,B,S,I,L,D,L,I,S,B,F,S,I,B,L,D
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_INT, JAVA_BYTE, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_BYTE, JAVA_SHORT, JAVA_INT, JAVA_LONG,
                                                                JAVA_DOUBLE, JAVA_LONG, JAVA_INT, JAVA_SHORT, JAVA_BYTE, JAVA_FLOAT, JAVA_SHORT, JAVA_INT, JAVA_BYTE, JAVA_LONG,
                                                                JAVA_DOUBLE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("GPROverFlowTestByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_GPROverFlowTest,
                                        FunctionDescriptor.of(JAVA_INT, JAVA_BYTE, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_BYTE, JAVA_SHORT, JAVA_INT, JAVA_LONG,
                                                                JAVA_DOUBLE, JAVA_LONG, JAVA_INT, JAVA_SHORT, JAVA_BYTE, JAVA_FLOAT, JAVA_SHORT, JAVA_INT, JAVA_BYTE, JAVA_LONG,
                                                                JAVA_DOUBLE), scope);
                        byte testB = (byte)125;
                        int result = (int)(mh.invoke(         (byte)5, (short)32, 667, 8765L, 43.2f, (byte)11, (short)345, 863, 9054L, 455.678, 5432L, 675, (short)98, (byte)13, 54.3f, (short)55, 87, testB, 789L, 4567.02, upcallFuncAddr));
                        int expected = oracle_GPROverFlowTest((byte)5, (short)32, 667, 8765L, 43.2f, (byte)11, (short)345, 863, 9054L, 455.678, 5432L, 675, (short)98, (byte)13, 54.3f, (short)55, 87, testB, 789L, 4567.02);
                        assertEquals(result, expected);
                }
        }

        public static int oracle_GPROverFlowTest(byte arg1, short arg2, int arg3, long arg4, float arg5, byte arg6, short arg7, int arg8, long arg9, double arg10, long arg11, int arg12, short arg13, byte arg14, float arg15, short arg16, int arg17, byte arg18, long arg19, double arg20)
        {
                System.out.println("Oracle: " + arg18);
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2 + " Arg3: " + arg3 + " Arg4: " + arg4 + "Arg5: " + arg5 + "Arg6: " + arg6 + " Arg7: " + arg7 + " Arg8: " + arg8 + " Arg9: " + arg9 + " Arg10: " + arg10 + " Arg11: " + arg11 + " Arg12: " + arg12 + " Arg13: " + arg13 + " Arg14: " + arg14 + " Arg15: " + arg15 + " Arg16: " + arg16 + " Arg17: " + arg17 + " Arg18: " + arg18 + " Arg19: " + arg19 + " Arg20: " + arg20);
                return 43;
        }

        public static void test_FPROverFlowTest() throws Throwable {
                // BIFBSDILDIFDFFD
                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_BYTE, JAVA_INT, JAVA_FLOAT, JAVA_BYTE, JAVA_SHORT, JAVA_DOUBLE, JAVA_INT, JAVA_LONG, JAVA_DOUBLE, JAVA_INT,
                                                                JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_FLOAT, JAVA_DOUBLE, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("FPROverFlowTestByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_FPROverFlowTest,
                                        FunctionDescriptor.of(JAVA_BYTE, JAVA_INT, JAVA_FLOAT, JAVA_BYTE, JAVA_SHORT, JAVA_DOUBLE, JAVA_INT, JAVA_LONG, JAVA_DOUBLE, JAVA_INT,
                                                                JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_FLOAT, JAVA_DOUBLE), scope);
                        byte result = (byte)(mh.invoke(56, 3.14f, (byte)45, (short)67, 4.3215, 567, 56890L, 45.32, 890, 3.245f, 43.905, 99.987f, 98.96f, 89.765, upcallFuncAddr));
                        byte expected = oracle_FPROverFlowTest(56, 3.14f, (byte)45, (short)67, 4.3215, 567, 56890L, 45.32, 890, 3.245f, 43.905, 99.987f, 98.96f, 89.765);
                        assertEquals(result, expected);

                }
        }

        public static byte oracle_FPROverFlowTest(int arg1, float arg2, byte arg3, short arg4, double arg5, int arg6, long arg7, double arg8, int arg9, float arg10, double arg11, float arg12, float arg13, double arg14)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2 + " Arg3: " + arg3 + " Arg4: " + arg4 + "Arg5: " + arg5 + "Arg6: " + arg6 + " Arg7: " + arg7 + " Arg8: " + arg8 + " Arg9: " + arg9 + " Arg10: " + arg10 + " Arg11: " + arg11 + " Arg12: " + arg12 + " Arg13: " + arg13 + " Arg14: " + arg14);
                return (byte)127;
        }

		public static void test_print16MixedArguments() throws Throwable {
                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, ADDRESS);

                NativeSymbol functionSymbol = nativeLibLookup.lookup("print16MixedArgumentsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);
                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                         NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_print16MixedArguments,
                                         FunctionDescriptor.ofVoid(JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE, JAVA_CHAR, JAVA_SHORT, JAVA_INT, JAVA_LONG, JAVA_FLOAT, JAVA_DOUBLE, JAVA_FLOAT, JAVA_DOUBLE), scope);
                         mh.invoke('A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916, 3.1415f, 3.14151916, 'A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916, 'A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916, 'A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916, upcallFuncAddr);
                         oracle_print16MixedArguments('A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916, 3.1415f, 3.14151916, 'A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916, 'A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916, 'A', (short)16, 65555, 6789012345L, 3.14f, 3.141519, 3.1415f, 3.14151916);
                } catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_print16MixedArguments(char arg1, short arg2, int arg3, long arg4, float arg5, double arg6, float arg7, double arg8, float arg77, double arg88, char arg9, short arg10, int arg11, long arg12, float arg13, double arg14, float arg15, double arg16, char arg17, short arg18, int arg19, long arg20, float arg21, double arg22, float arg23, double arg24, char arg25, short arg26, int arg27, long arg28, float arg29, double arg30, float arg31, double arg32) {

                System.out.println("arg1: " + arg1 + " arg2: " + arg2 + " arg3: " + arg3 + " arg4: " + arg4 + " arg5: " + arg5 + " arg6: " + arg6 + " arg7: " + arg7 + " arg8: " + arg8 + " arg9: " + arg9 + " arg10: " + arg10 + " arg11: " + arg11 + " arg12: " + arg12 + " arg13: " + arg13 + " arg14: " + arg14 + " arg15: " + arg15 + " arg16: " + arg16 + " arg17: " + arg17 + " arg18: " + arg18 + " arg19: " + arg19 + " arg20: " + arg20 + " arg21: " + arg21 + " arg22: " + arg22 + " arg23: " + arg23 + " arg24: " + arg24 + " arg25: " + arg25 + " arg26: " + arg26 + " arg27: " + arg27 + " arg28: " + arg28 + " arg29: " + arg29 + " arg30: " + arg30 + " arg31: " + arg31 + " arg32: " + arg32 + " arg77: " + arg77 + " arg88: " + arg88);


        }

        public static void test_structsMixedVariousSizesByUpcallMH() throws Throwable {
                SequenceLayout byteArray1 = MemoryLayout.sequenceLayout(1, JAVA_BYTE);
                GroupLayout structLayout_byteArray1 = MemoryLayout.structLayout(byteArray1);

                SequenceLayout byteArray2 = MemoryLayout.sequenceLayout(2, JAVA_BYTE);
                GroupLayout structLayout_byteArray2 = MemoryLayout.structLayout(byteArray2);

                SequenceLayout byteArray3 = MemoryLayout.sequenceLayout(3, JAVA_BYTE);
                GroupLayout structLayout_byteArray3 = MemoryLayout.structLayout(byteArray3);

                SequenceLayout byteArray4 = MemoryLayout.sequenceLayout(4, JAVA_BYTE);
                GroupLayout structLayout_byteArray4 = MemoryLayout.structLayout(byteArray4);

                SequenceLayout byteArray7 = MemoryLayout.sequenceLayout(7, JAVA_BYTE);
                GroupLayout structLayout_byteArray7 = MemoryLayout.structLayout(byteArray7);

                SequenceLayout byteArray8 = MemoryLayout.sequenceLayout(8, JAVA_BYTE);
                GroupLayout structLayout_byteArray8 = MemoryLayout.structLayout(byteArray8);

                GroupLayout structLayoutFloat = MemoryLayout.structLayout(JAVA_FLOAT.withName("elem1"));
                VarHandle floatHandle1 = structLayoutFloat.varHandle(PathElement.groupElement("elem1"));

                GroupLayout structLayoutDouble = MemoryLayout.structLayout(JAVA_DOUBLE.withName("elem1"));
                VarHandle doubleHandle1 = structLayoutDouble.varHandle(PathElement.groupElement("elem1"));

                FunctionDescriptor fd = FunctionDescriptor.of(JAVA_INT, structLayoutFloat, structLayout_byteArray1, structLayout_byteArray2, structLayoutDouble, structLayout_byteArray3, structLayout_byteArray4, structLayoutFloat, structLayout_byteArray7, structLayout_byteArray8, structLayoutDouble, structLayoutFloat, structLayout_byteArray1, structLayout_byteArray2, structLayoutDouble, structLayout_byteArray3, structLayout_byteArray4, structLayoutFloat, structLayout_byteArray7, structLayout_byteArray8, structLayoutDouble, ADDRESS);

                NativeSymbol functionSymbol = nativeLibLookup.lookup("structsMixedVariousSizesByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_structsMixedVariousSizes,
                                        FunctionDescriptor.of(JAVA_INT, structLayoutFloat, structLayout_byteArray1, structLayout_byteArray2, structLayoutDouble, structLayout_byteArray3, structLayout_byteArray4, structLayoutFloat, structLayout_byteArray7, structLayout_byteArray8, structLayoutDouble, structLayoutFloat, structLayout_byteArray1, structLayout_byteArray2, structLayoutDouble, structLayout_byteArray3, structLayout_byteArray4, structLayoutFloat, structLayout_byteArray7, structLayout_byteArray8, structLayoutDouble), scope);
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        MemorySegment singleElemFloatStructSegm1 = allocator.allocate(structLayoutFloat); floatHandle1.set(singleElemFloatStructSegm1, 3.14f);
                        MemorySegment singleElemFloatStructSegm2 = allocator.allocate(structLayoutFloat); floatHandle1.set(singleElemFloatStructSegm2, 3.15f);
                        MemorySegment singleElemFloatStructSegm3 = allocator.allocate(structLayoutFloat); floatHandle1.set(singleElemFloatStructSegm3, 3.16f);
                        MemorySegment singleElemFloatStructSegm4 = allocator.allocate(structLayoutFloat); floatHandle1.set(singleElemFloatStructSegm4, 3.17f);

                        MemorySegment singleElemDoubleStructSegm1 = allocator.allocate(structLayoutDouble); doubleHandle1.set(singleElemDoubleStructSegm1, 3.141519);
                        MemorySegment singleElemDoubleStructSegm2 = allocator.allocate(structLayoutDouble); doubleHandle1.set(singleElemDoubleStructSegm2, 3.141518);
                        MemorySegment singleElemDoubleStructSegm3 = allocator.allocate(structLayoutDouble); doubleHandle1.set(singleElemDoubleStructSegm3, 3.141517);
                        MemorySegment singleElemDoubleStructSegm4 = allocator.allocate(structLayoutDouble); doubleHandle1.set(singleElemDoubleStructSegm4, 3.141516);

                        MemorySegment structSegmtByteArray1_1 = allocator.allocate(structLayout_byteArray1); structSegmtByteArray1_1.set(JAVA_BYTE, 0, (byte)55);
                        MemorySegment structSegmtByteArray1_2 = allocator.allocate(structLayout_byteArray1); structSegmtByteArray1_2.set(JAVA_BYTE, 0, (byte)56);

                        MemorySegment structSegmtByteArray2_1 = allocator.allocate(structLayout_byteArray2); structSegmtByteArray2_1.set(JAVA_BYTE, 0, (byte)57); structSegmtByteArray2_1.set(JAVA_BYTE, 1, (byte)58);
                        MemorySegment structSegmtByteArray2_2 = allocator.allocate(structLayout_byteArray2); structSegmtByteArray2_2.set(JAVA_BYTE, 0, (byte)59); structSegmtByteArray2_2.set(JAVA_BYTE, 1, (byte)60);

                        MemorySegment structSegmtByteArray3_1 = allocator.allocate(structLayout_byteArray3); structSegmtByteArray3_1.set(JAVA_BYTE, 0, (byte)61); structSegmtByteArray3_1.set(JAVA_BYTE, 1, (byte)62);
                                                                                                                structSegmtByteArray3_1.set(JAVA_BYTE, 2, (byte)63);
                        MemorySegment structSegmtByteArray3_2 = allocator.allocate(structLayout_byteArray3); structSegmtByteArray3_2.set(JAVA_BYTE, 0, (byte)64); structSegmtByteArray3_2.set(JAVA_BYTE, 1, (byte)65);
                                                                                                                structSegmtByteArray3_2.set(JAVA_BYTE, 2, (byte)66);

                        MemorySegment structSegmtByteArray4_1 = allocator.allocate(structLayout_byteArray4); structSegmtByteArray4_1.set(JAVA_BYTE, 0, (byte)67); structSegmtByteArray4_1.set(JAVA_BYTE, 1, (byte)68);
                                                                                                                structSegmtByteArray4_1.set(JAVA_BYTE, 2, (byte)69); structSegmtByteArray4_1.set(JAVA_BYTE, 3, (byte)70);
                        MemorySegment structSegmtByteArray4_2 = allocator.allocate(structLayout_byteArray4); structSegmtByteArray4_2.set(JAVA_BYTE, 0, (byte)71); structSegmtByteArray4_2.set(JAVA_BYTE, 1, (byte)72);
                                                                                                                structSegmtByteArray4_2.set(JAVA_BYTE, 2, (byte)73); structSegmtByteArray4_2.set(JAVA_BYTE, 3, (byte)74);

                        MemorySegment structSegmtByteArray7_1 = allocator.allocate(structLayout_byteArray7); structSegmtByteArray7_1.set(JAVA_BYTE, 0, (byte)75); structSegmtByteArray7_1.set(JAVA_BYTE, 1, (byte)76); structSegmtByteArray7_1.set(JAVA_BYTE, 2, (byte)77);
                                                        structSegmtByteArray7_1.set(JAVA_BYTE, 3, (byte)78); structSegmtByteArray7_1.set(JAVA_BYTE, 4, (byte)79); structSegmtByteArray7_1.set(JAVA_BYTE, 5, (byte)80); structSegmtByteArray7_1.set(JAVA_BYTE, 6, (byte)81);
                        MemorySegment structSegmtByteArray7_2 = allocator.allocate(structLayout_byteArray7); structSegmtByteArray7_2.set(JAVA_BYTE, 0, (byte)82); structSegmtByteArray7_2.set(JAVA_BYTE, 1, (byte)83); structSegmtByteArray7_2.set(JAVA_BYTE, 2, (byte)84);
                                                        structSegmtByteArray7_2.set(JAVA_BYTE, 3, (byte)85); structSegmtByteArray7_2.set(JAVA_BYTE, 4, (byte)86); structSegmtByteArray7_2.set(JAVA_BYTE, 5, (byte)87); structSegmtByteArray7_2.set(JAVA_BYTE, 6, (byte)88);

                        MemorySegment structSegmtByteArray8_1 = allocator.allocate(structLayout_byteArray8); structSegmtByteArray8_1.set(JAVA_BYTE, 0, (byte)89);structSegmtByteArray8_1.set(JAVA_BYTE, 1, (byte)90); structSegmtByteArray8_1.set(JAVA_BYTE, 2, (byte)91);
                        structSegmtByteArray8_1.set(JAVA_BYTE, 3, (byte)92); structSegmtByteArray8_1.set(JAVA_BYTE, 4, (byte)93); structSegmtByteArray8_1.set(JAVA_BYTE, 5, (byte)94); structSegmtByteArray8_1.set(JAVA_BYTE, 6, (byte)95);
                        structSegmtByteArray8_1.set(JAVA_BYTE, 7, (byte)96);
                        MemorySegment structSegmtByteArray8_2 = allocator.allocate(structLayout_byteArray8); structSegmtByteArray8_2.set(JAVA_BYTE, 0, (byte)97);structSegmtByteArray8_2.set(JAVA_BYTE, 1, (byte)98); structSegmtByteArray8_2.set(JAVA_BYTE, 2, (byte)99);
                        structSegmtByteArray8_2.set(JAVA_BYTE, 3, (byte)100); structSegmtByteArray8_2.set(JAVA_BYTE, 4, (byte)101); structSegmtByteArray8_2.set(JAVA_BYTE, 5, (byte)102); structSegmtByteArray8_2.set(JAVA_BYTE, 6, (byte)103);
                        structSegmtByteArray8_2.set(JAVA_BYTE, 7, (byte)104);

                        int result = (int)(mh.invoke(singleElemFloatStructSegm1, structSegmtByteArray1_1, structSegmtByteArray2_1, singleElemDoubleStructSegm1, structSegmtByteArray3_1, structSegmtByteArray4_1,
                                                singleElemFloatStructSegm2, structSegmtByteArray7_1, structSegmtByteArray8_1, singleElemDoubleStructSegm2, singleElemFloatStructSegm3, structSegmtByteArray1_2, structSegmtByteArray2_2,
                                                singleElemDoubleStructSegm3, structSegmtByteArray3_2, structSegmtByteArray4_2, singleElemFloatStructSegm4, structSegmtByteArray7_2, structSegmtByteArray8_2, singleElemDoubleStructSegm4,
                                                upcallFuncAddr));

                        int expected = oracle_structsMixedVariousSizes(singleElemFloatStructSegm1, structSegmtByteArray1_1, structSegmtByteArray2_1, singleElemDoubleStructSegm1, structSegmtByteArray3_1, structSegmtByteArray4_1,
                                                singleElemFloatStructSegm2, structSegmtByteArray7_1, structSegmtByteArray8_1, singleElemDoubleStructSegm2, singleElemFloatStructSegm3, structSegmtByteArray1_2, structSegmtByteArray2_2,
                                                singleElemDoubleStructSegm3, structSegmtByteArray3_2, structSegmtByteArray4_2, singleElemFloatStructSegm4, structSegmtByteArray7_2, structSegmtByteArray8_2, singleElemDoubleStructSegm4);

                        assertEquals(result, expected);

                }
        }

        public static int oracle_structsMixedVariousSizes(MemorySegment floatStruct1, MemorySegment byteArray1_1, MemorySegment byteArray2_1, MemorySegment doubleStruct1, MemorySegment byteArray3_1, MemorySegment byteArray4_1,
                                                        MemorySegment floatStruct2, MemorySegment byteArray7_1, MemorySegment byteArray8_1, MemorySegment doubleStruct2, MemorySegment floatStruct3, MemorySegment byteArray1_2, MemorySegment byteArray2_2,
                                                        MemorySegment doubleStruct3, MemorySegment byteArray3_2, MemorySegment byteArray4_2, MemorySegment floatStruct4, MemorySegment byteArray7_2, MemorySegment byteArray8_2, MemorySegment doubleStruct4)
        {
                System.out.println("ORACLE");

                System.out.println("floatStruct1: " + floatStruct1.get(JAVA_FLOAT, 0));
                System.out.println("floatStruct2: " + floatStruct2.get(JAVA_FLOAT, 0));
                System.out.println("floatStruct3: " + floatStruct3.get(JAVA_FLOAT, 0));
                System.out.println("floatStruct4: " + floatStruct4.get(JAVA_FLOAT, 0));

                System.out.println("doubleStruct1: " + doubleStruct1.get(JAVA_DOUBLE, 0));
                System.out.println("doubleStruct2: " + doubleStruct2.get(JAVA_DOUBLE, 0));
                System.out.println("doubleStruct3: " + doubleStruct3.get(JAVA_DOUBLE, 0));
                System.out.println("doubleStruct4: " + doubleStruct4.get(JAVA_DOUBLE, 0));

                System.out.println("byteArray1_1: " + byteArray1_1.get(JAVA_BYTE, 0));
                System.out.println("byteArray1_2: " + byteArray1_2.get(JAVA_BYTE, 0));

                System.out.println("byteArray2_1: " + byteArray2_1.get(JAVA_BYTE, 0) + ", " + byteArray2_1.get(JAVA_BYTE, 1));
                System.out.println("byteArray2_2: " + byteArray2_2.get(JAVA_BYTE, 0) + ", " + byteArray2_2.get(JAVA_BYTE, 1));

                System.out.println("byteArray3_1: " + byteArray3_1.get(JAVA_BYTE, 0) + ", " + byteArray3_1.get(JAVA_BYTE, 1) + ", " + byteArray3_1.get(JAVA_BYTE, 2));
                System.out.println("byteArray3_2: " + byteArray3_2.get(JAVA_BYTE, 0) + ", " + byteArray3_2.get(JAVA_BYTE, 1) + ", " + byteArray3_2.get(JAVA_BYTE, 2));

                System.out.println("byteArray4_1: " + byteArray4_1.get(JAVA_BYTE, 0) + ", " + byteArray4_1.get(JAVA_BYTE, 1) + ", " + byteArray4_1.get(JAVA_BYTE, 2) + ", " + byteArray4_1.get(JAVA_BYTE, 3));
                System.out.println("byteArray4_2: " + byteArray4_2.get(JAVA_BYTE, 0) + ", " + byteArray4_2.get(JAVA_BYTE, 1) + ", " + byteArray4_2.get(JAVA_BYTE, 2) + ", " + byteArray4_2.get(JAVA_BYTE, 3));


                System.out.println("byteArray7_1: " + byteArray7_1.get(JAVA_BYTE, 0) + ", " + byteArray7_1.get(JAVA_BYTE, 1) + ", " + byteArray7_1.get(JAVA_BYTE, 2) + ", " + byteArray7_1.get(JAVA_BYTE, 3) + ", " + byteArray7_1.get(JAVA_BYTE, 4) + ", " +
                                        byteArray7_1.get(JAVA_BYTE, 5) + ", " + byteArray7_1.get(JAVA_BYTE, 6));
                System.out.println("byteArray7_2: " + byteArray7_2.get(JAVA_BYTE, 0) + ", " + byteArray7_2.get(JAVA_BYTE, 1) + ", " + byteArray7_2.get(JAVA_BYTE, 2) + ", " + byteArray7_2.get(JAVA_BYTE, 3) + ", " + byteArray7_2.get(JAVA_BYTE, 4) + ", " +
                                        byteArray7_2.get(JAVA_BYTE, 5) + ", " + byteArray7_2.get(JAVA_BYTE, 6));

                System.out.println("byteArray8_1: " + byteArray8_1.get(JAVA_BYTE, 0) + ", " + byteArray8_1.get(JAVA_BYTE, 1) + ", " + byteArray8_1.get(JAVA_BYTE, 2) + ", " + byteArray8_1.get(JAVA_BYTE, 3) + ", " + byteArray8_1.get(JAVA_BYTE, 4) + ", " +
                                        byteArray8_1.get(JAVA_BYTE, 5) + ", " + byteArray8_1.get(JAVA_BYTE, 6) + ", " + byteArray8_1.get(JAVA_BYTE, 7));
                System.out.println("byteArray8_2: " + byteArray8_2.get(JAVA_BYTE, 0) + ", " + byteArray8_2.get(JAVA_BYTE, 1) + ", " + byteArray8_2.get(JAVA_BYTE, 2) + ", " + byteArray8_2.get(JAVA_BYTE, 3) + ", " + byteArray8_2.get(JAVA_BYTE, 4) + ", " +
                                        byteArray8_2.get(JAVA_BYTE, 5) + ", " + byteArray8_2.get(JAVA_BYTE, 6) + ", " + byteArray8_2.get(JAVA_BYTE, 7));

                return 4567;

        }

        public static void test_printVariousTypesOfFloatArgumentsByUpcallMH() throws Throwable {
//              System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
                GroupLayout structLayout = MemoryLayout.structLayout(JAVA_FLOAT.withName("elem1"));
                VarHandle floatHandle1 = structLayout.varHandle(PathElement.groupElement("elem1"));

                GroupLayout structLayout2 = MemoryLayout.structLayout(JAVA_FLOAT.withName("elem1"), JAVA_FLOAT.withName("elem2"));
                VarHandle floatHandle2 = structLayout2.varHandle(PathElement.groupElement("elem1"));
                VarHandle floatHandle3 = structLayout2.varHandle(PathElement.groupElement("elem2"));

                FunctionDescriptor fd = FunctionDescriptor.ofVoid(JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, ADDRESS);
                NativeSymbol functionSymbol = nativeLibLookup.lookup("printVariousTypesOfFloatArgumentsByUpcallMH").get();
                MethodHandle mh = clinker.downcallHandle(functionSymbol, fd);

                try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                        NativeSymbol upcallFuncAddr = clinker.upcallStub(UpcallMethodHandles.MH_printVariousTypesOfFloatArguments,
                                        FunctionDescriptor.ofVoid(JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2, JAVA_FLOAT, structLayout, structLayout2), scope);
                        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(scope);
                        MemorySegment singleElemFloatStructSegm1 = allocator.allocate(structLayout); floatHandle1.set(singleElemFloatStructSegm1, 3.14f);
                        MemorySegment singleElemFloatStructSegm2 = allocator.allocate(structLayout); floatHandle1.set(singleElemFloatStructSegm2, 3.14f);
                        MemorySegment singleElemFloatStructSegm3 = allocator.allocate(structLayout); floatHandle1.set(singleElemFloatStructSegm3, 3.14f);
                        MemorySegment singleElemFloatStructSegm4 = allocator.allocate(structLayout); floatHandle1.set(singleElemFloatStructSegm4, 3.14f);
                        MemorySegment singleElemFloatStructSegm5 = allocator.allocate(structLayout); floatHandle1.set(singleElemFloatStructSegm5, 3.14f);
                        MemorySegment singleElemFloatStructSegm6 = allocator.allocate(structLayout); floatHandle1.set(singleElemFloatStructSegm6, 3.14f);

                        MemorySegment doubleElemFloatStructSegm1 = allocator.allocate(structLayout2); floatHandle2.set(doubleElemFloatStructSegm1, 3.15f); floatHandle3.set(doubleElemFloatStructSegm1, 3.16f);
                        MemorySegment doubleElemFloatStructSegm2 = allocator.allocate(structLayout2); floatHandle2.set(doubleElemFloatStructSegm2, 3.15f); floatHandle3.set(doubleElemFloatStructSegm2, 3.16f);
                        MemorySegment doubleElemFloatStructSegm3 = allocator.allocate(structLayout2); floatHandle2.set(doubleElemFloatStructSegm3, 3.15f); floatHandle3.set(doubleElemFloatStructSegm3, 3.16f);
                        MemorySegment doubleElemFloatStructSegm4 = allocator.allocate(structLayout2); floatHandle2.set(doubleElemFloatStructSegm4, 3.15f); floatHandle3.set(doubleElemFloatStructSegm4, 3.16f);
                        MemorySegment doubleElemFloatStructSegm5 = allocator.allocate(structLayout2); floatHandle2.set(doubleElemFloatStructSegm5, 3.15f); floatHandle3.set(doubleElemFloatStructSegm5, 3.16f);
                        MemorySegment doubleElemFloatStructSegm6 = allocator.allocate(structLayout2); floatHandle2.set(doubleElemFloatStructSegm6, 3.15f); floatHandle3.set(doubleElemFloatStructSegm6, 3.16f);

                        mh.invoke(3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1,3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, upcallFuncAddr);
                        oracle_printVariousTypesOfFloatArguments(3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1, 3.13f, singleElemFloatStructSegm1, doubleElemFloatStructSegm1);

                }
                catch (Throwable t) {
                        System.out.println("Exception Thrown: " + t.getMessage());
                        t.printStackTrace();
                }
        }

        public static void oracle_printVariousTypesOfFloatArguments(float arg1, MemorySegment singleElemStruct1, MemorySegment doubleElementStruct1, float arg2, MemorySegment singleElemStruct2, MemorySegment doubleElementStruct2, float arg3, MemorySegment singleElemStruct3, MemorySegment doubleElementStruct3, float arg4, MemorySegment singleElemStruct4, MemorySegment doubleElementStruct4, float arg5, MemorySegment singleElemStruct5, MemorySegment doubleElementStruct5, float arg6, MemorySegment singleElemStruct6, MemorySegment doubleElementStruct6) {
                System.out.print(" arg1: " + arg1);
                System.out.print(" singleElemStruct1: " + singleElemStruct1.get(JAVA_FLOAT, 0));
                System.out.print(" doubleElemStruct1_0: " + doubleElementStruct1.get(JAVA_FLOAT, 0) + " doubleElemStruct1_1: " + doubleElementStruct1.get(JAVA_FLOAT, 4));

                System.out.print(" arg2: " + arg2);
                System.out.print(" singleElemStruct2: " + singleElemStruct2.get(JAVA_FLOAT, 0));
                System.out.print(" doubleElemStruct2_0: " + doubleElementStruct2.get(JAVA_FLOAT, 0) + " doubleElemStruct2_1: " + doubleElementStruct2.get(JAVA_FLOAT, 4));

                System.out.print(" arg3: " + arg3);
                System.out.print(" singleElemStruct3: " + singleElemStruct3.get(JAVA_FLOAT, 0));
                System.out.print(" doubleElemStruct3_0: " + doubleElementStruct3.get(JAVA_FLOAT, 0) + " doubleElemStruct3_1: " + doubleElementStruct3.get(JAVA_FLOAT, 4));

                System.out.print(" arg4: " + arg4);
                System.out.print(" singleElemStruct4: " + singleElemStruct4.get(JAVA_FLOAT, 0));
                System.out.print(" doubleElemStruct4_0: " + doubleElementStruct4.get(JAVA_FLOAT, 0) + " doubleElemStruct4_1: " + doubleElementStruct4.get(JAVA_FLOAT, 4));

                System.out.print(" arg5: " + arg5);
                System.out.print(" singleElemStruct5: " + singleElemStruct5.get(JAVA_FLOAT, 0));
                System.out.print(" doubleElemStruct5_0: " + doubleElementStruct5.get(JAVA_FLOAT, 0) + " doubleElemStruct5_1: " + doubleElementStruct5.get(JAVA_FLOAT, 4));

                System.out.print(" arg6: " + arg6);
                System.out.print(" singleElemStruct6: " + singleElemStruct6.get(JAVA_FLOAT, 0));
                System.out.println(" doubleElemStruct6_0: " + doubleElementStruct6.get(JAVA_FLOAT, 0) + " doubleElemStruct6_1: " + doubleElementStruct6.get(JAVA_FLOAT, 4));

        }

}

