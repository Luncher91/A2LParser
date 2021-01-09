package net.alenzen.a2l.enums;

import java.io.IOException;

import net.alenzen.a2l.A2LWriter;
import net.alenzen.a2l.IA2LWriteable;

public enum Monotony implements IA2LWriteable {
	MON_DECREASE, MON_INCREASE, STRICT_DECREASE, STRICT_INCREASE, MONOTONOUS, STRICT_MON, NOT_MON;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
