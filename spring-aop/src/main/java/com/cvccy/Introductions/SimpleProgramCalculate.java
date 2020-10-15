package com.cvccy.Introductions;

public class SimpleProgramCalculate implements ProgramCalculate{
    @Override
    public String toBinary(Integer value) {
        return Integer.toBinaryString(value);
    }
}
