package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}
}
