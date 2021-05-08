package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class SiExponents implements IA2LWriteable {
	private long length;
	private long mass;
	private long time;
	private long electricCurrent;
	private long temperature;
	private long amountOfSubstance;
	private long luminousIntensity;

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public long getMass() {
		return mass;
	}

	public void setMass(long mass) {
		this.mass = mass;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getElectricCurrent() {
		return electricCurrent;
	}

	public void setElectricCurrent(long electricCurrent) {
		this.electricCurrent = electricCurrent;
	}

	public long getTemperature() {
		return temperature;
	}

	public void setTemperature(long temperature) {
		this.temperature = temperature;
	}

	public long getAmountOfSubstance() {
		return amountOfSubstance;
	}

	public void setAmountOfSubstance(long amountOfSubstance) {
		this.amountOfSubstance = amountOfSubstance;
	}

	public long getLuminousIntensity() {
		return luminousIntensity;
	}

	public void setLuminousIntensity(long luminousIntensity) {
		this.luminousIntensity = luminousIntensity;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("SI_EXPONENTS", Long.toString(length), Long.toString(mass), Long.toString(time),
				Long.toString(electricCurrent), Long.toString(temperature), Long.toString(amountOfSubstance),
				Long.toString(luminousIntensity));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SiExponents that = (SiExponents) o;
		return length == that.length && mass == that.mass && time == that.time
				&& electricCurrent == that.electricCurrent && temperature == that.temperature
				&& amountOfSubstance == that.amountOfSubstance && luminousIntensity == that.luminousIntensity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(length, mass, time, electricCurrent, temperature, amountOfSubstance, luminousIntensity);
	}
}
