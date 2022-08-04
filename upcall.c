#include <stdio.h>
#include <stdbool.h>
#include <stdarg.h>
#include "downcall.h"

stru_Byte
print2ArbIntsReturnStructByUpcallMH(stru_Byte (*upcallMH)())
{
        return (*upcallMH)();
}

void
print2ArbIntsByUpcallMH(void (*upcallMH)())
{
        (*upcallMH)();
}

char
print2ArbIntsReturnCharByUpcallMH(int (*upcallMH)())
{
        return (*upcallMH)();
}

short
print2ArbIntsReturnShortByUpcallMH(short (*upcallMH)())
{
        return (*upcallMH)();
}

int
print2ArbIntsReturnSumByUpcallMH(int (*upcallMH)())
{
        return (*upcallMH)();
}

bool *
print2ArbIntsReturnPointerByUpcallMH(bool * (*upcallMH)())
{
        return (*upcallMH)();
}

int64_t
print2ArbIntsReturnLongByUpcallMH(int64_t (*upcallMH)())
{
        return (*upcallMH)();
}

float
print2ArbIntsReturnFloatByUpcallMH(float (*upcallMH)())
{
        return (*upcallMH)();
}

double
print2ArbIntsReturnDoubleByUpcallMH(double (*upcallMH)())
{
        return (*upcallMH)();
}

stru_254_Bytes
return254BytesFromStructByUpcallMH(stru_254_Bytes (*upcallMH)())
{
        return (*upcallMH)();
}

stru_255_Bytes
return255BytesFromStructByUpcallMH(stru_255_Bytes (*upcallMH)())
{
        return (*upcallMH)();
}

stru_256_Bytes
return256BytesFromStructByUpcallMH(stru_256_Bytes (*upcallMH)())
{
        return (*upcallMH)();
}

stru_4079_Bytes
return4079BytesFromStructByUpcallMH(stru_4079_Bytes (*upcallMH)())
{
        return (*upcallMH)();
}

stru_4080_Bytes
return4080BytesFromStructByUpcallMH(stru_4080_Bytes (*upcallMH)())
{
        return (*upcallMH)();
}

stru_4081_Bytes
return4081BytesFromStructByUpcallMH(stru_4081_Bytes (*upcallMH)())
{
        return (*upcallMH)();
}

void
printByteByUpcallMH(char arg1, void (*upcallMH)(char))
{
        (*upcallMH)(arg1);
}

void
printShortByUpcallMH(short arg1, void (*upcallMH)(short))
{
        (*upcallMH)(arg1);
}

void
printIntByUpcallMH(int arg1, void (*upcallMH)(int))
{
        (*upcallMH)(arg1);
}

void
printLongByUpcallMH(int64_t arg1, void (*upcallMH)(int64_t))
{
        (*upcallMH)(arg1);
}

void
printFloatByUpcallMH(float arg1, void (*upcallMH)(float))
{
        (*upcallMH)(arg1);
}

void
printDoubleByUpcallMH(double arg1, void (*upcallMH)(double))
{
        (*upcallMH)(arg1);
}

char
subtract2BytesByUpcallMH(char arg1, char arg2, char (*upcallMH)(char, char))
{
        char result = (*upcallMH)(arg1, arg2);
        return result;
}

short
subtract2ShortsByUpcallMH(short arg1, short arg2, short (*upcallMH)(short, short))
{
        short result = (*upcallMH)(arg1, arg2);
        return result;
}

int
subtract2IntsByUpcallMH(int arg1, int arg2, int (*upcallMH)(int, int))
{
        int result = (*upcallMH)(arg1, arg2);
        return result;
}

int64_t
subtract2LongsByUpcallMH(int64_t arg1, int64_t arg2, int64_t (*upcallMH)(int64_t, int64_t))
{
        int64_t result = (*upcallMH)(arg1, arg2);
        return result;
}

float
subtract2FloatsByUpcallMH(float arg1, float arg2, float (*upcallMH)(float, float))
{
        float result = (*upcallMH)(arg1, arg2);
        return result;
}

double
subtract2DoublesByUpcallMH(double arg1, double arg2, double (*upcallMH)(double, double))
{
        double result = (*upcallMH)(arg1, arg2);
        return result;
}

char
pass6BytesByUpcallMH(char arg1, char arg2, char arg3, char arg4, char arg5, char arg6, char (*upcallMH)(char, char, char, char, char, char))
{
        char result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6);
        return result;
}

short
pass6ShortsByUpcallMH(short arg1, short arg2, short arg3, short arg4, short arg5, short arg6, short (*upcallMH)(short, short, short, short, short, short))
{
        short result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6);
        return result;
}

int
pass6IntsByUpcallMH(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int (*upcallMH)(int, int, int, int, int, int))
{
        int result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6);
        return result;
}

float
pass6FloatsByUpcallMH(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float (*upcallMH)(float, float, float, float, float, float))
{
        float result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6);
        return result;
}

int64_t
pass6LongsByUpcallMH(int64_t arg1, int64_t arg2, int64_t arg3, int64_t arg4, int64_t arg5, int64_t arg6, int64_t (*upcallMH)(int64_t, int64_t, int64_t, int64_t, int64_t, int64_t))
{
        int64_t result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6);
        return result;
}

double
pass6DoublesByUpcallMH(double arg1, double arg2, double arg3, double arg4, double arg5, double arg6, double (*upcallMH)(double, double, double, double, double, double))
{
        double result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6);
        return result;
}

char
pass10BytesByUpcallMH(char arg1, char arg2, char arg3, char arg4, char arg5, char arg6, char arg7, char arg8, char arg9, char arg10, char (*upcallMH)(char, char, char, char, char, char, char, char, char, char))
{
        char result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        return result;
}

short
pass10ShortsByUpcallMH(short arg1, short arg2, short arg3, short arg4, short arg5, short arg6, short arg7, short arg8, short arg9, short arg10, int (*upcallMH)(short, short, short, short, short, short, short, short, short, short))
{
        short result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        return result;
}

int
pass10IntsByUpcallMH(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int (*upcallMH)(int, int, int, int, int, int, int, int, int, int ))
{
        int result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        return result;
}

int64_t
pass10LongsByUpcallMH(int64_t arg1, int64_t arg2, int64_t arg3, int64_t arg4, int64_t arg5, int64_t arg6, int64_t arg7, int64_t arg8, int64_t arg9, int64_t arg10, int64_t (*upcallMH)(int64_t, int64_t, int64_t, int64_t, int64_t, int64_t, int64_t, int64_t, int64_t, int64_t ))
{
        int64_t result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        return result;
}

double
pass10DoublesByUpcallMH(double arg1, double arg2, double arg3, double arg4, double arg5, double arg6, double arg7, double arg8, double arg9, double arg10, double (*upcallMH)(double, double, double, double, double, double, double, double, double, double ))
{
        double result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        return result;
}

float
pass10FloatsByUpcallMH(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float (*upcallMH)(float, float, float, float, float, float, float, float, float, float ))
{
        float result = (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        return result;
}

int
GPRLimitTestByUpcallMH(char arg1, float arg2, short arg3, double arg4, int arg5, double arg6, long arg7, float arg8, short arg9, char arg10, int (*upcallMH)(char, float, short, double, int, double, long, float, short, char))
{
        return (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
}

int
GPROverFlowTestByUpcallMH(char arg1, short arg2, int arg3, long arg4, float arg5, char arg6, short arg7, int arg8, long arg9, double arg10, long arg11, int arg12, short arg13, char arg14, float arg15, short arg16, int arg17, char arg18, long arg19, double arg20, int (*upcallMH)(char, short, int, long, float, char, short, int, long, double, long, int, short, char, float, short, int, char, long, double))
{
        return (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
}

char
FPROverFlowTestByUpcallMH(int arg1, float arg2, char arg3, short arg4, double arg5, int arg6, long arg7, double arg8, int arg9, float arg10, double arg11, float arg12, float arg13, double arg14, char (*upcallMH)(int, float, char, short, double, int, long, double, int, float, double, float, float, double))
{
        return (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14);
}

void
print16MixedArgumentsByUpcallMH(short arg1, short arg2, int arg3, long arg4, float arg5, double arg6, float arg7, double arg8, float arg77, double arg88, short arg9, short arg10, int arg11, long arg12, float arg13, double arg14, float arg15, double arg16, short arg17, short arg18, int arg19, long arg20, float arg21, double arg22, float arg23, double arg24, short arg25, short arg26, int arg27, long arg28, float arg29, double arg30, float arg31, double arg32, void (*upcallMH)(short, short, int, long, float, double, float, double, float, double,short, short, int, long, float, double, float, double, short, short, int, long, float, double, float, double, short, short, int, long, float, double, float, double))
{
        (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg77, arg88, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29, arg30, arg31, arg32);
}

int
structsMixedVariousSizesByUpcallMH(stru_Float arg1, stru_1ElementStruct arg2, stru_2ElementStruct arg3, stru_Double arg4, stru_3ElementStruct arg5, stru_4ElementStruct arg6, stru_Float arg7, stru_7ElementStruct arg8, stru_8ElementStruct arg9, stru_Double arg10, stru_Float arg11, stru_1ElementStruct arg12, stru_2ElementStruct arg13, stru_Double arg14, stru_3ElementStruct arg15, stru_4ElementStruct arg16, stru_Float arg17, stru_7ElementStruct arg18, stru_8ElementStruct arg19, stru_Double arg20, int (*upcallMH)(stru_Float, stru_1ElementStruct, stru_2ElementStruct, stru_Double, stru_3ElementStruct, stru_4ElementStruct, stru_Float, stru_7ElementStruct, stru_8ElementStruct, stru_Double, stru_Float, stru_1ElementStruct, stru_2ElementStruct, stru_Double, stru_3ElementStruct, stru_4ElementStruct, stru_Float, stru_7ElementStruct, stru_8ElementStruct, stru_Double))
{
        return (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
}

void
printVariousTypesOfFloatArgumentsByUpcallMH(float arg1, stru_Float arg2, stru_Float_Float arg3, float arg4, stru_Float arg5, stru_Float_Float arg6, float arg7, stru_Float arg8, stru_Float_Float arg9, float arg10, stru_Float arg11, stru_Float_Float arg12, float arg13, stru_Float arg14, stru_Float_Float arg15, float arg16, stru_Float arg17, stru_Float_Float arg18, void (*upcallMH)(float, stru_Float, stru_Float_Float, float, stru_Float, stru_Float_Float, float, stru_Float, stru_Float_Float, float, stru_Float, stru_Float_Float, float, stru_Float, stru_Float_Float, float, stru_Float, stru_Float_Float))
{
        (*upcallMH)(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
}

