package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ModPar implements IA2LWriteable {
	private String comment;

	// optional parameters
	private List<Long> addresses;
	private List<CalibrationMethod> calibrationMethods;
	private String cpuType;
	private String customer;
	private String customerNo;
	private String ecu;
	private Long ecuCalibrationOffset;
	private String epk;
	private List<MemoryLayout> memoryLayouts;
	private List<MemorySegment> memorySegments;
	private Long numberOfInterfaces;
	private String phoneNumber;
	private String supplier;
	private List<SystemConstant> systemConstants;
	private String user;
	private String version;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Long> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Long> addresses) {
		this.addresses = addresses;
	}

	public List<CalibrationMethod> getCalibrationMethods() {
		return calibrationMethods;
	}

	public void setCalibrationMethods(List<CalibrationMethod> calibrationMethods) {
		this.calibrationMethods = calibrationMethods;
	}

	public String getCpuType() {
		return cpuType;
	}

	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getEcu() {
		return ecu;
	}

	public void setEcu(String ecu) {
		this.ecu = ecu;
	}

	public Long getEcuCalibrationOffset() {
		return ecuCalibrationOffset;
	}

	public void setEcuCalibrationOffset(Long ecuCalibrationOffset) {
		this.ecuCalibrationOffset = ecuCalibrationOffset;
	}

	public String getEpk() {
		return epk;
	}

	public void setEpk(String epk) {
		this.epk = epk;
	}

	public List<MemoryLayout> getMemoryLayouts() {
		return memoryLayouts;
	}

	public void setMemoryLayouts(List<MemoryLayout> memoryLayouts) {
		this.memoryLayouts = memoryLayouts;
	}

	public List<MemorySegment> getMemorySegments() {
		return memorySegments;
	}

	public void setMemorySegments(List<MemorySegment> memorySegments) {
		this.memorySegments = memorySegments;
	}

	public Long getNumberOfInterfaces() {
		return numberOfInterfaces;
	}

	public void setNumberOfInterfaces(Long numberOfInterfaces) {
		this.numberOfInterfaces = numberOfInterfaces;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public List<SystemConstant> getSystemConstants() {
		return systemConstants;
	}

	public void setSystemConstants(List<SystemConstant> systemConstants) {
		this.systemConstants = systemConstants;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("MOD_PAR", A2LWriter.toA2LString(comment));
		writer.indent();

		writeAddresses(writer);
		writer.write(calibrationMethods);

		if (cpuType != null) {
			writer.writelnSpaced("CPU_TYPE", A2LWriter.toA2LString(cpuType));
		}

		if (customer != null) {
			writer.writelnSpaced("CUSTOMER", A2LWriter.toA2LString(customer));
		}

		if (customerNo != null) {
			writer.writelnSpaced("CUSTOMER_NO", A2LWriter.toA2LString(customerNo));
		}

		if (ecu != null) {
			writer.writelnSpaced("ECU", A2LWriter.toA2LString(ecu));
		}

		if (ecuCalibrationOffset != null) {
			writer.writelnSpaced("ECU_CALIBRATION_OFFSET", Long.toString(ecuCalibrationOffset));
		}

		if (epk != null) {
			writer.writelnSpaced("EPK", A2LWriter.toA2LString(epk));
		}

		writer.write(memoryLayouts);
		writer.write(memorySegments);
		
		if(numberOfInterfaces != null) {
			writer.writelnSpaced("NO_OF_INTERFACES", Long.toString(numberOfInterfaces));
		}
		
		if(phoneNumber != null) {
			writer.writelnSpaced("PHONE_NO", A2LWriter.toA2LString(phoneNumber));
		}

		if (supplier != null) {
			writer.writelnSpaced("SUPPLIER", A2LWriter.toA2LString(supplier));
		}

		writer.write(systemConstants);
		
		if(user != null) {
			writer.writelnSpaced("USER", A2LWriter.toA2LString(user));
		}
		
		if(version != null) {
			writer.writelnSpaced("VERSION", A2LWriter.toA2LString(version));
		}

		writer.dedent();
		writer.writelnEnd("MOD_PAR");
	}

	private void writeAddresses(A2LWriter writer) throws IOException {
		if (addresses != null && addresses.size() > 0) {
			for (Long l : addresses) {
				if (l != null) {
					writer.writelnSpaced("ADDR_EPK", "0x" + Long.toHexString(l));
				}
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ModPar modPar = (ModPar) o;
		return Objects.equals(comment, modPar.comment) && Objects.equals(addresses, modPar.addresses) && Objects
				.equals(calibrationMethods, modPar.calibrationMethods) && Objects.equals(cpuType, modPar.cpuType)
				&& Objects.equals(customer, modPar.customer) && Objects.equals(customerNo, modPar.customerNo) && Objects
				.equals(ecu, modPar.ecu) && Objects.equals(ecuCalibrationOffset, modPar.ecuCalibrationOffset) && Objects
				.equals(epk, modPar.epk) && Objects.equals(memoryLayouts, modPar.memoryLayouts) && Objects
				.equals(memorySegments, modPar.memorySegments) && Objects
				.equals(numberOfInterfaces, modPar.numberOfInterfaces) && Objects
				.equals(phoneNumber, modPar.phoneNumber) && Objects.equals(supplier, modPar.supplier) && Objects
				.equals(systemConstants, modPar.systemConstants) && Objects.equals(user, modPar.user) && Objects
				.equals(version, modPar.version);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(comment, addresses, calibrationMethods, cpuType, customer, customerNo, ecu, ecuCalibrationOffset,
						epk, memoryLayouts, memorySegments, numberOfInterfaces, phoneNumber, supplier, systemConstants,
						user, version);
	}
}
