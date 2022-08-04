import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import static java.lang.invoke.MethodType.methodType;
import java.lang.invoke.VarHandle;

import jdk.incubator.foreign.Addressable;
import static jdk.incubator.foreign.CLinker.*;
import jdk.incubator.foreign.GroupLayout;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.MemoryLayout.PathElement;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.SequenceLayout;
import jdk.incubator.foreign.ValueLayout;
import static jdk.incubator.foreign.ValueLayout.*;
import jdk.incubator.foreign.VaList;

public class UpcallMethodHandles
{
		private static final Lookup lookup = MethodHandles.lookup();
        private static ResourceScope scope = ResourceScope.newImplicitScope();

	    public static final MethodHandle MH_print2ArbInts;
        public static final MethodHandle MH_print2ArbIntsReturnChar;
        public static final MethodHandle MH_print2ArbIntsReturnShort;
        public static final MethodHandle MH_print2ArbIntsReturnSum;
        public static final MethodHandle MH_print2ArbIntsReturnPointer;
        public static final MethodHandle MH_print2ArbIntsReturnLong;
        public static final MethodHandle MH_print2ArbIntsReturnFloat;
        public static final MethodHandle MH_print2ArbIntsReturnDouble;

		public static final MethodHandle MH_print2ArbIntsReturnStruct;
		public static final MethodHandle MH_return254BytesFromStruct;
		public static final MethodHandle MH_return255BytesFromStruct;
		public static final MethodHandle MH_return256BytesFromStruct;

		public static final MethodHandle MH_return4079BytesFromStruct;
        public static final MethodHandle MH_return4080BytesFromStruct;
        public static final MethodHandle MH_return4081BytesFromStruct;

		public static final MethodHandle MH_printByte;
        public static final MethodHandle MH_printShort;
        public static final MethodHandle MH_printInt;
        public static final MethodHandle MH_printLong;
        public static final MethodHandle MH_printFloat;
        public static final MethodHandle MH_printDouble;

		public static final MethodHandle MH_subtract2Bytes;
        public static final MethodHandle MH_subtract2Shorts;
        public static final MethodHandle MH_subtract2Ints;
        public static final MethodHandle MH_subtract2Longs;
        public static final MethodHandle MH_subtract2Floats;
        public static final MethodHandle MH_subtract2Doubles;

		public static final MethodHandle MH_pass6Bytes;
        public static final MethodHandle MH_pass6Shorts;
        public static final MethodHandle MH_pass6Ints;
        public static final MethodHandle MH_pass6Longs;
        public static final MethodHandle MH_pass6Floats;
        public static final MethodHandle MH_pass6Doubles;

		public static final MethodHandle MH_pass10Bytes;
        public static final MethodHandle MH_pass10Shorts;
        public static final MethodHandle MH_pass10Ints;
        public static final MethodHandle MH_pass10Longs;
        public static final MethodHandle MH_pass10Floats;
        public static final MethodHandle MH_pass10Doubles;

		public static final MethodHandle MH_GPRLimitTest;
        public static final MethodHandle MH_GPROverFlowTest;
        public static final MethodHandle MH_FPROverFlowTest;

		public static final MethodHandle MH_print16MixedArguments;
		public static final MethodHandle MH_structsMixedVariousSizes;
		public static final MethodHandle MH_printVariousTypesOfFloatArguments;

		static {
			try {
                    MH_print2ArbInts = lookup.findStatic(UpcallMethodHandles.class, "print2ArbInts", methodType(void.class));
					MH_print2ArbIntsReturnChar = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnChar", methodType(char.class));
					MH_print2ArbIntsReturnShort = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnShort", methodType(short.class));
					MH_print2ArbIntsReturnSum = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnSum", methodType(int.class));
					MH_print2ArbIntsReturnPointer = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnPointer", methodType(Addressable.class));
					MH_print2ArbIntsReturnLong = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnLong", methodType(long.class));
					MH_print2ArbIntsReturnFloat = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnFloat", methodType(float.class));
					MH_print2ArbIntsReturnDouble = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnDouble", methodType(double.class));
					MH_print2ArbIntsReturnStruct = lookup.findStatic(UpcallMethodHandles.class, "print2ArbIntsReturnStruct", methodType(MemorySegment.class));
					MH_return254BytesFromStruct = lookup.findStatic(UpcallMethodHandles.class, "return254BytesFromStruct", methodType(MemorySegment.class)); //$NON-NLS-1$
					MH_return255BytesFromStruct = lookup.findStatic(UpcallMethodHandles.class, "return255BytesFromStruct", methodType(MemorySegment.class)); //$NON-NLS-1$
					MH_return256BytesFromStruct = lookup.findStatic(UpcallMethodHandles.class, "return256BytesFromStruct", methodType(MemorySegment.class)); //$NON-NLS-1$
					MH_return4079BytesFromStruct = lookup.findStatic(UpcallMethodHandles.class, "return4079BytesFromStruct", methodType(MemorySegment.class)); //$NON-NLS-1$
					MH_return4080BytesFromStruct = lookup.findStatic(UpcallMethodHandles.class, "return4080BytesFromStruct", methodType(MemorySegment.class)); //$NON-NLS-1$
					MH_return4081BytesFromStruct = lookup.findStatic(UpcallMethodHandles.class, "return4081BytesFromStruct", methodType(MemorySegment.class)); //$NON-NLS-1$
					MH_printByte = lookup.findStatic(UpcallMethodHandles.class, "printByte", methodType(void.class, byte.class));
					MH_printShort = lookup.findStatic(UpcallMethodHandles.class, "printShort", methodType(void.class, short.class));
					MH_printInt = lookup.findStatic(UpcallMethodHandles.class, "printInt", methodType(void.class, int.class));
					MH_printLong = lookup.findStatic(UpcallMethodHandles.class, "printLong", methodType(void.class, long.class));
					MH_printFloat = lookup.findStatic(UpcallMethodHandles.class, "printFloat", methodType(void.class, float.class));
					MH_printDouble = lookup.findStatic(UpcallMethodHandles.class, "printDouble", methodType(void.class, double.class));
					MH_subtract2Bytes = lookup.findStatic(UpcallMethodHandles.class, "subtract2Bytes", methodType(byte.class, byte.class, byte.class));
					MH_subtract2Shorts = lookup.findStatic(UpcallMethodHandles.class, "subtract2Shorts", methodType(short.class, short.class, short.class));
					MH_subtract2Ints = lookup.findStatic(UpcallMethodHandles.class, "subtract2Ints", methodType(int.class, int.class, int.class));
					MH_subtract2Longs = lookup.findStatic(UpcallMethodHandles.class, "subtract2Longs", methodType(long.class, long.class, long.class));
					MH_subtract2Floats = lookup.findStatic(UpcallMethodHandles.class, "subtract2Floats", methodType(float.class, float.class, float.class));
					MH_subtract2Doubles = lookup.findStatic(UpcallMethodHandles.class, "subtract2Doubles", methodType(double.class, double.class, double.class));
					MH_pass6Bytes = lookup.findStatic(UpcallMethodHandles.class, "pass6Bytes", methodType(byte.class, byte.class, byte.class, byte.class, byte.class, byte.class, byte.class));
					MH_pass6Shorts = lookup.findStatic(UpcallMethodHandles.class, "pass6Shorts", methodType(short.class, short.class, short.class, short.class, short.class, short.class, short.class));
					MH_pass6Ints = lookup.findStatic(UpcallMethodHandles.class, "pass6Ints", methodType(int.class, int.class, int.class, int.class, int.class, int.class, int.class));
					MH_pass6Longs = lookup.findStatic(UpcallMethodHandles.class, "pass6Longs", methodType(long.class, long.class, long.class, long.class, long.class, long.class, long.class));
					MH_pass6Floats = lookup.findStatic(UpcallMethodHandles.class, "pass6Floats", methodType(float.class, float.class, float.class, float.class, float.class, float.class, float.class));
					MH_pass6Doubles = lookup.findStatic(UpcallMethodHandles.class, "pass6Doubles", methodType(double.class, double.class, double.class, double.class, double.class, double.class, double.class));
					MH_pass10Bytes = lookup.findStatic(UpcallMethodHandles.class, "pass10Bytes", methodType(byte.class, byte.class,byte.class,byte.class,byte.class,byte.class,byte.class,byte.class,byte.class,byte.class,byte.class));
					MH_pass10Shorts = lookup.findStatic(UpcallMethodHandles.class, "pass10Shorts", methodType(short.class,short.class,short.class,short.class,short.class,short.class,short.class,short.class,short.class,short.class,short.class));
					MH_pass10Ints = lookup.findStatic(UpcallMethodHandles.class, "pass10Ints", methodType(int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class));
					MH_pass10Longs = lookup.findStatic(UpcallMethodHandles.class, "pass10Longs", methodType(long.class,long.class,long.class,long.class,long.class,long.class,long.class,long.class,long.class,long.class,long.class));
					MH_pass10Floats = lookup.findStatic(UpcallMethodHandles.class, "pass10Floats", methodType(float.class,float.class,float.class,float.class,float.class,float.class,float.class,float.class,float.class,float.class,float.class));
					MH_pass10Doubles = lookup.findStatic(UpcallMethodHandles.class, "pass10Doubles", methodType(double.class,double.class,double.class,double.class,double.class,double.class,double.class,double.class,double.class,double.class,double.class));
					MH_GPRLimitTest = lookup.findStatic(UpcallMethodHandles.class, "GPRLimitTest", methodType(int.class, byte.class, float.class, short.class, double.class, int.class, double.class, long.class, float.class, short.class, byte.class));
					MH_GPROverFlowTest = lookup.findStatic(UpcallMethodHandles.class, "GPROverFlowTest", methodType(int.class, byte.class, short.class, int.class, long.class, float.class, byte.class, short.class, int.class, long.class, double.class,
                                                                                                                        long.class, int.class, short.class, byte.class, float.class, short.class, int.class, byte.class, long.class, double.class));
					MH_FPROverFlowTest = lookup.findStatic(UpcallMethodHandles.class, "FPROverFlowTest", methodType(byte.class, int.class, float.class, byte.class, short.class, double.class, int.class, long.class, double.class,
                                                                                                                        int.class, float.class, double.class, float.class, float.class, double.class));
					MH_print16MixedArguments = lookup.findStatic(UpcallMethodHandles.class, "print16MixedArguments", methodType(void.class, char.class, short.class, int.class, long.class, float.class, double.class, float.class, double.class, float.class, double.class, char.class, short.class, int.class, long.class, float.class, double.class, float.class, double.class,char.class, short.class, int.class, long.class, float.class, double.class, float.class, double.class,char.class, short.class, int.class, long.class, float.class, double.class, float.class, double.class));
					MH_structsMixedVariousSizes = lookup.findStatic(UpcallMethodHandles.class, "structsMixedVariousSizes", methodType(int.class, MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class,
                                                                                                                                                MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class,
                                                                                                                                                MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class,
                                                                                                                                                MemorySegment.class,MemorySegment.class,MemorySegment.class,MemorySegment.class));
					MH_printVariousTypesOfFloatArguments = lookup.findStatic(UpcallMethodHandles.class, "printVariousTypesOfFloatArguments", methodType(void.class, float.class, MemorySegment.class, MemorySegment.class, float.class, MemorySegment.class, MemorySegment.class, float.class, MemorySegment.class, MemorySegment.class, float.class, MemorySegment.class, MemorySegment.class, float.class, MemorySegment.class, MemorySegment.class, float.class, MemorySegment.class, MemorySegment.class));


			} catch (IllegalAccessException | NoSuchMethodException e) {
                        throw new InternalError(e);
            }
		}

		public static void printVariousTypesOfFloatArguments(float arg1, MemorySegment singleElemStruct1, MemorySegment doubleElementStruct1, float arg2, MemorySegment singleElemStruct2, MemorySegment doubleElementStruct2, float arg3, MemorySegment singleElemStruct3, MemorySegment doubleElementStruct3, float arg4, MemorySegment singleElemStruct4, MemorySegment doubleElementStruct4, float arg5, MemorySegment singleElemStruct5, MemorySegment doubleElementStruct5, float arg6, MemorySegment singleElemStruct6, MemorySegment doubleElementStruct6) {
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

		public static int structsMixedVariousSizes(MemorySegment floatStruct1, MemorySegment byteArray1_1, MemorySegment byteArray2_1, MemorySegment doubleStruct1, MemorySegment byteArray3_1, MemorySegment byteArray4_1,
                                                        MemorySegment floatStruct2, MemorySegment byteArray7_1, MemorySegment byteArray8_1, MemorySegment doubleStruct2, MemorySegment floatStruct3, MemorySegment byteArray1_2, MemorySegment byteArray2_2,
                                                        MemorySegment doubleStruct3, MemorySegment byteArray3_2, MemorySegment byteArray4_2, MemorySegment floatStruct4, MemorySegment byteArray7_2, MemorySegment byteArray8_2, MemorySegment doubleStruct4)
        {

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

		public static void print16MixedArguments(char arg1, short arg2, int arg3, long arg4, float arg5, double arg6, float arg7, double arg8, float arg77, double arg88, char arg9, short arg10, int arg11, long arg12, float arg13, double arg14, float arg15, double arg16, char arg17, short arg18, int arg19, long arg20, float arg21, double arg22, float arg23, double arg24, char arg25, short arg26, int arg27, long arg28, float arg29, double arg30, float arg31, double arg32) {

                System.out.println("arg1: " + arg1 + " arg2: " + arg2 + " arg3: " + arg3 + " arg4: " + arg4 + " arg5: " + arg5 + " arg6: " + arg6 + " arg7: " + arg7 + " arg8: " + arg8 + " arg9: " + arg9 + " arg10: " + arg10 + " arg11: " + arg11 + " arg12: " + arg12 + " arg13: " + arg13 + " arg14: " + arg14 + " arg15: " + arg15 + " arg16: " + arg16 + " arg17: " + arg17 + " arg18: " + arg18 + " arg19: " + arg19 + " arg20: " + arg20 + " arg21: " + arg21 + " arg22: " + arg22 + " arg23: " + arg23 + " arg24: " + arg24 + " arg25: " + arg25 + " arg26: " + arg26 + " arg27: " + arg27 + " arg28: " + arg28 + " arg29: " + arg29 + " arg30: " + arg30 + " arg31: " + arg31 + " arg32: " + arg32 + " arg77: " + arg77 + " arg88: " + arg88);


        }

		public static byte FPROverFlowTest(int arg1, float arg2, byte arg3, short arg4, double arg5, int arg6, long arg7, double arg8, int arg9, float arg10, double arg11, float arg12, float arg13, double arg14)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2 + " Arg3: " + arg3 + " Arg4: " + arg4 + "Arg5: " + arg5 + "Arg6: " + arg6 + " Arg7: " + arg7 + " Arg8: " + arg8 + " Arg9: " + arg9 + " Arg10: " + arg10 + " Arg11: " + arg11 + " Arg12: " + arg12 + " Arg13: " + arg13 + " Arg14: " + arg14);
                return (byte)127;
        }

		public static int GPROverFlowTest(byte arg1, short arg2, int arg3, long arg4, float arg5, byte arg6, short arg7, int arg8, long arg9, double arg10, long arg11, int arg12, short arg13, byte arg14, float arg15, short arg16, int arg17, byte arg18, long arg19, double arg20)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2 + " Arg3: " + arg3 + " Arg4: " + arg4 + "Arg5: " + arg5 + "Arg6: " + arg6 + " Arg7: " + arg7 + " Arg8: " + arg8 + " Arg9: " + arg9 + " Arg10: " + arg10 + " Arg11: " + arg11 + " Arg12: " + arg12 + " Arg13: " + arg13 + " Arg14: " + arg14 + " Arg15: " + arg15 + " Arg16: " + arg16 + " Arg17: " + arg17 + " Arg18: " + arg18 + " Arg19: " + arg19 + " Arg20: " + arg20);
                return 43;
        }

		public static int GPRLimitTest(byte byteArg1, float floatArg1, short shortArg1, double doubleArg1, int intArg1, double doubleArg2, long longArg1, float floatArg2, short shortArg2, byte byteArg2)
        {
                System.out.println("byteArg1: " + byteArg1 + " floatArg1: " + floatArg1 + " shortArg1: " + shortArg1 + " doubleArg1: " + doubleArg1 + " intArg1: " + intArg1 + " doubleArg2: " + doubleArg2 + " longArg1: " + longArg1 + " floatArg2: " + floatArg2 + "shortArg2: " + shortArg2 + " byteArg2: " + byteArg2);
                return 65;
        }

		public static double pass10Doubles(double a1, double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9, double a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (double)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }
        public static float pass10Floats(float a1, float a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9, float a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (float)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }
        public static long pass10Longs(long a1, long a2, long a3, long a4, long a5, long a6, long a7, long a8, long a9, long a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (long)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }
        public static int pass10Ints(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }
        public static short pass10Shorts(short a1, short a2, short a3, short a4, short a5, short a6, short a7, short a8, short a9, short a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (short)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }
        public static byte pass10Bytes(byte a1, byte a2, byte a3, byte a4, byte a5, byte a6, byte a7, byte a8, byte a9, byte a10)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6 + " Arg7: " + a7 + " Arg8: " + a8 + " Arg9: " + a9 + " Arg10: " + a10);
                return (byte)(a1 + a2 + a3 + a4 + a5 - a6 - a7 - a8 - a9 - a10);
        }

		public static double pass6Doubles(double a1, double a2, double a3, double a4, double a5, double a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (double)(a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static float pass6Floats(float a1, float a2, float a3, float a4, float a5, float a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (float)(a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static long pass6Longs(long a1, long a2, long a3, long a4, long a5, long a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (long)(a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static int pass6Ints(int a1, int a2, int a3, int a4, int a5, int a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (int)(a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static short pass6Shorts(short a1, short a2, short a3, short a4, short a5, short a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (short)(a1 + a2 + a3 - a4 - a5 - a6);
        }

        public static byte pass6Bytes(byte a1, byte a2, byte a3, byte a4, byte a5, byte a6)
        {
                System.out.println("Arg1: " + a1 + " Arg2: " + a2 + " Arg3: " + a3 + " Arg4: " + a4 + " Arg5: " + a5 + " Arg6: " + a6);
                return (byte)(a1 + a2 + a3 - a4 - a5 - a6);
        }

		public static double subtract2Doubles(double arg1, double arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static float subtract2Floats(float arg1, float arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static long subtract2Longs(long arg1, long arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static int subtract2Ints(int arg1, int arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return arg1 - arg2;
        }

        public static short subtract2Shorts(short arg1, short arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return (short)(arg1 - arg2);
        }

        public static byte subtract2Bytes(byte arg1, byte arg2)
        {
                System.out.println("Arg1: " + arg1 + " Arg2: " + arg2);
                return (byte)(arg1 - arg2);
        }

		public static void printDouble(double arg1)
        {
                System.out.println("Java MH printDouble: " + arg1);
        }

        public static void printFloat(float arg1)
        {
                System.out.println("Java MH printFloat: " + arg1);
        }
        public static void printLong(long arg1)
        {
                System.out.println("Java MH printLong: " + arg1);
        }

        public static void printInt(int arg1)
        {
                System.out.println("Java MH printInt: " + arg1);
        }

        public static void printShort(short arg1)
        {
                System.out.println("Java MH printShort: " + arg1);
        }

		public static void printByte(byte letter)
        {
                System.out.println("Java MH printByte: " + letter);
        }

		public static void print2ArbInts() {
                System.out.println("Java MH print2ArbInts: Output: 1+1=2");
        }

		public static char print2ArbIntsReturnChar() {
                System.out.println("Java MH print2ArbIntsReturnChar: Output 1+1=2");
                return 'a';
        }

		public static short print2ArbIntsReturnShort() {
                System.out.println("Java MH print2ArbIntsReturnShort: Output 1+1=2");
                return 6;
        }

		public static int print2ArbIntsReturnSum() {
                System.out.println("Java MH print2ArbIntsReturnSum: Output 1+1=2");
                return 2;
        }

		public static Addressable print2ArbIntsReturnPointer() {
                System.out.println("Java MH print2ArbIntsReturnBoolPointer: Output 1+1=2");
                MemorySegment resultSegmt = MemorySegment.allocateNative(JAVA_BOOLEAN, scope);
                resultSegmt.set(JAVA_BOOLEAN, 0, true);
                return resultSegmt.address();
        }

		public static long print2ArbIntsReturnLong() {
                System.out.println("Java MH print2ArbIntsReturnLong: Output 1+1=2");
                return Long.MAX_VALUE;
        }

		public static float print2ArbIntsReturnFloat() {
                System.out.println("Java MH print2ArbIntsReturnFloat: Output 1+1=2");
                return 3.14f;
        }

		public static double print2ArbIntsReturnDouble() {
                System.out.println("Java MH print2ArbIntsReturnDouble: Output 1+1=2");
                return 6.28;
        }

		public static MemorySegment print2ArbIntsReturnStruct() {
                System.out.println("Java MH print2ArbIntsReturnStruct: Output 1+1=2");
                GroupLayout structLayout = MemoryLayout.structLayout(JAVA_BYTE.withName("elem1"));
                MemorySegment resultSegmt = MemorySegment.allocateNative(structLayout, scope);
                byte val = 25;
                resultSegmt.set(JAVA_BYTE, 0, val);
                return resultSegmt;
        }

		public static MemorySegment return254BytesFromStruct() {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(254, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);
                MemorySegment byteArrStruSegment = MemorySegment.allocateNative(structLayout, scope);

                for (int i = 0; i < 254; i++) {
                        byteArrStruSegment.set(JAVA_BYTE, i, (byte)i);
                }
                return byteArrStruSegment;
        }

		public static MemorySegment return255BytesFromStruct() {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(255, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);
                MemorySegment byteArrStruSegment = MemorySegment.allocateNative(structLayout, scope);

                for (int i = 0; i < 255; i++) {
                        byteArrStruSegment.set(JAVA_BYTE, i, (byte)i);
                }
                return byteArrStruSegment;
        }

		public static MemorySegment return256BytesFromStruct() {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(256, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);
                MemorySegment byteArrStruSegment = MemorySegment.allocateNative(structLayout, scope);

                for (int i = 0; i < 256; i++) {
                        byteArrStruSegment.set(JAVA_BYTE, i, (byte)(i % 256));
                }
                return byteArrStruSegment;
        }

		public static MemorySegment return4079BytesFromStruct() {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(4079, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);
                MemorySegment byteArrStruSegment = MemorySegment.allocateNative(structLayout, scope);

                for (int i = 0; i < 4079; i++) {
                        byteArrStruSegment.set(JAVA_BYTE, i, (byte)(i % 256));
                }
                return byteArrStruSegment;
        }

		public static MemorySegment return4080BytesFromStruct() {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(4080, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);
                MemorySegment byteArrStruSegment = MemorySegment.allocateNative(structLayout, scope);

                for (int i = 0; i < 4080; i++) {
                        byteArrStruSegment.set(JAVA_BYTE, i, (byte)(i % 256));
                }
                return byteArrStruSegment;
        }

		public static MemorySegment return4081BytesFromStruct() {
                SequenceLayout byteArray = MemoryLayout.sequenceLayout(4081, JAVA_BYTE);
                GroupLayout structLayout = MemoryLayout.structLayout(byteArray);
                MemorySegment byteArrStruSegment = MemorySegment.allocateNative(structLayout, scope);

                for (int i = 0; i < 4081; i++) {
                        byteArrStruSegment.set(JAVA_BYTE, i, (byte)(i % 256));
                }
                return byteArrStruSegment;
        }

}
