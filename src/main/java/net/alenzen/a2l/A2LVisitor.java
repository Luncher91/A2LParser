package net.alenzen.a2l;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import net.alenzen.a2l.FncValues.IndexMode;
import net.alenzen.a2l.Measurement.LayoutIndexMode;
import net.alenzen.a2l.MemoryLayout.PrgType;
import net.alenzen.a2l.MemorySegment.Attribute;
import net.alenzen.a2l.MemorySegment.MemoryType;
import net.alenzen.a2l.VariantCoding.VarNaming;
import net.alenzen.a2l.antlr.a2lLexer;
import net.alenzen.a2l.antlr.a2lParser;
import net.alenzen.a2l.antlr.a2lParser.*;
import net.alenzen.a2l.antlr.a2lParserBaseVisitor;
import net.alenzen.a2l.enums.AddrType;
import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.CalibrationAccess;
import net.alenzen.a2l.enums.CharacteristicType;
import net.alenzen.a2l.enums.ConversionType;
import net.alenzen.a2l.enums.DataSize;
import net.alenzen.a2l.enums.DataType;
import net.alenzen.a2l.enums.Deposit;
import net.alenzen.a2l.enums.IndexOrder;
import net.alenzen.a2l.enums.Monotony;

class A2LVisitor extends a2lParserBaseVisitor<Object> {
	private IParserEventHandler log;

	public A2LVisitor() {
		log = new DefaultParserEventHandler();
	}

	public A2LVisitor(IParserEventHandler logger) {
		log = logger;

		if (log == null) {
			log = new DefaultParserEventHandler();
		}
	}

	@Override
	public Object visitA2ml_version_exp(a2lParser.A2ml_version_expContext ctx) {
		return new A2mlVersion((Long) visit(ctx.VersionNo), (Long) visit(ctx.UpgradeNo));
	}

	@Override
	public Object visitAsap2_version_exp(a2lParser.Asap2_version_expContext ctx) {
		return new Asap2Version((Long) visit(ctx.VersionNo), (Long) visit(ctx.UpgradeNo));
	}

	@Override
	public Object visitInt_value(a2lParser.Int_valueContext ctx) {
		return Long.decode(ctx.getText());
	}

	@Override
	public Object visitString_exp(String_expContext ctx) {
		return extractString(ctx.string);
	}

	@Override
	public Object visitA2l_file(a2lParser.A2l_fileContext ctx) {
		Asap2File a2l = new Asap2File();

		a2l.setA2mlVersion((A2mlVersion) visitSingleOpt(ctx.a2ml_version_exp()));
		a2l.setAsap2Version((Asap2Version) visitSingleOpt(ctx.asap2_version_exp()));

		Project p = (Project) visitOpt(ctx.project_block());
		a2l.setProject(p);
		return a2l;
	}

	@Override
	public Object visitProject_block(a2lParser.Project_blockContext ctx) {
		Project p = new Project();
		Project_expContext expCtx = ctx.project_exp();

		// mandatory parameters
		p.setName(expCtx.Name.getText());
		p.setLongIdentifier(visitString(expCtx.LongIdentifier));

		p.setHeader((Header) visitSingleOpt(ctx.project_sub_nodes().header_block()));

		// modules
		p.setModules(visitMultipleOpt(ctx.project_sub_nodes().module_block(), Module.class));

		return p;
	}

	@Override
	public Object visitHeader_block(a2lParser.Header_blockContext ctx) {
		Header h = new Header();

		h.setComment(visitString(ctx.header_exp().Comment));
		h.setProjectNo((String) visitSingleOpt(ctx.header_sub_nodes().project_no_exp()));
		h.setVersion((String) visitSingleOpt(ctx.header_sub_nodes().version_exp()));

		return h;
	}

	@Override
	public Object visitProject_no_exp(a2lParser.Project_no_expContext ctx) {
		return ctx.ProjectNumber.getText();
	}

	@Override
	public Object visitVersion_exp(a2lParser.Version_expContext ctx) {
		return visit(ctx.VersionIdentifier);
	}

	@Override
	public Object visitModule_block(a2lParser.Module_blockContext ctx) {
		Module m = new Module();

		m.setName(ctx.module_exp().Name.getText());
		m.setLongIdentifier(visitString(ctx.module_exp().LongIdentifier));

		Module_sub_nodesContext sn = ctx.module_sub_nodes();

		m.setA2ml(visitMultipleOpt(sn.a2ml_block(), A2ml.class));
		m.setIfDatas(visitMultipleOpt(sn.if_data_block(), IfData.class));
		m.setModCommon((ModCommon) visitSingleOpt(sn.mod_common_block()));
		m.setModPar((ModPar) visitSingleOpt(sn.mod_par_block()));
		m.setCompuMethods(visitMultipleOpt(sn.compu_method_block(), CompuMethod.class));
		m.setCompuTabs(visitMultipleOpt(sn.compu_tab_block(), CompuTab.class));
		m.setCompuVTabs(visitMultipleOpt(sn.compu_vtab_block(), CompuVTab.class));
		m.setCompuVTabRanges(visitMultipleOpt(sn.compu_vtab_range_block(), CompuVTabRange.class));
		m.setMeasurements(visitMultipleOpt(sn.measurement_block(), Measurement.class));
		m.setRecordLayouts(visitMultipleOpt(sn.record_layout_block(), RecordLayout.class));
		m.setCharacteristics(visitMultipleOpt(sn.characteristic_block(), Characteristic.class));
		m.setAxisPts(visitMultipleOpt(sn.axis_pts_block(), AxisPts.class));
		m.setFunctions(visitMultipleOpt(sn.function_block(), Function.class));
		m.setGroups(visitMultipleOpt(sn.group_block(), Group.class));
		m.setFrame((Frame) visitSingleOpt(sn.frame_block()));
		m.setUnits(visitMultipleOpt(sn.unit_block(), Unit.class));
		m.setUserRights(visitMultipleOpt(sn.user_rights_block(), UserRights.class));
		// TODO typedef_characteristic_block
		// TODO instance
		// TODO typedef_axis_block
		// TODO typedef_structure_block
		// TODO typedef_transformer_block
		m.setBlobs(visitMultipleOpt(sn.blob_block(), Blob.class));
		m.setVariantCoding((VariantCoding) visitSingleOpt(sn.variant_coding_block()));
		// TODO not yet in parser definition: typedef_blob, typedef_measurement

		return m;
	}

	@Override
	public Object visitBlob_block(Blob_blockContext ctx) {
		Blob b = new Blob();
		b.setContent(ctx.getText());
		return b;
	}

	@Override
	public Object visitCompu_tab_block(Compu_tab_blockContext ctx) {
		CompuTab c = new CompuTab();

		Compu_tab_expContext exp = ctx.compu_tab_exp();
		c.setName(exp.Name.getText());
		c.setLongIdentifier(visitString(exp.LongIdentifier));
		c.setConversionType((ConversionType) visit(exp.ConversionType));
		c.setNumberOfValuePairs((Long) visit(exp.NumberValuePairs));

		List<ValuePair<Double, Double>> vps = new ArrayList<ValuePair<Double, Double>>();
		for (Value_pairContext sub_ctx : exp.ValuePairs) {
			ValuePair<Double, Double> vp = new ValuePair<>();
			vp.setInVal((Double) visit(sub_ctx.InVal));
			vp.setOutVal((Double) visit(sub_ctx.OutVal));
			vps.add(vp);
		}
		c.setValuePairs(vps);

		Compu_tab_sub_nodesContext sn = ctx.compu_tab_sub_nodes();
		c.setDefaultValue((String) visitSingleOpt(sn.default_value_exp()));
		c.setDefaultValueNumeric((Double) visitSingleOpt(sn.default_value_numeric_exp()));

		return c;
	}

	@Override
	public Object visitAxis_descr_block(Axis_descr_blockContext ctx) {
		AxisDescr a = new AxisDescr();

		Axis_descr_expContext exp = ctx.axis_descr_exp();
		a.setAttribute((net.alenzen.a2l.AxisDescr.Attribute) visit(exp.Attribute));
		a.setInputQuantity(exp.InputQuantity.getText());
		a.setConversion(exp.Conversion.getText());
		a.setMaxAxisPoints((Long) visit(exp.MaxAxisPoints));
		a.setLowerLimit((double) visit(exp.LowerLimit));
		a.setUpperLimit((double) visit(exp.UpperLimit));

		Axis_descr_sub_nodesContext sn = ctx.axis_descr_sub_nodes();
		a.setAnnotations(visitMultipleOpt(sn.annotation_block(), Annotation.class));
		a.setAxisPoints_ref((String) visitSingleOpt(sn.axis_pts_ref_exp()));
		a.setByteorder((ByteOrder) visitSingleOpt(sn.byte_order_exp()));
		a.setCurveAxis_ref((String) visitSingleOpt(sn.curve_axis_ref_exp()));
		a.setDeposit((Deposit) visitSingleOpt(sn.deposit_exp()));
		a.setExtendedLimits((ExtendedLimits) visitSingleOpt(sn.extended_limits_exp()));
		a.setFixAxisPar((FixAxisPar) visitSingleOpt(sn.fix_axis_par_exp()));
		a.setFixAxisParDist((FixAxisParDist) visitSingleOpt(sn.fix_axis_par_dist_exp()));
		a.setFixAxisParList((FixAxisParList) visitSingleOpt(sn.fix_axis_par_list_block()));
		a.setFormat((String) visitSingleOpt(sn.format_exp()));
		a.setMaxGrad((Double) visitSingleOpt(sn.max_grad_exp()));
		a.setMonotony((Monotony) visitSingleOpt(sn.monotony_exp()));
		a.setPhysUnit((String) visitSingleOpt(sn.phys_unit_exp()));
		a.setReadOnly(visitSingleOptBool(sn.read_only_exp()));
		a.setStepSize((Double) visitSingleOpt(sn.step_size_exp()));

		return a;
	}

	@Override
	public Object visitMax_grad_exp(Max_grad_expContext ctx) {
		return visit(ctx.MaxGradient);
	}

	@Override
	public Object visitFix_axis_par_exp(Fix_axis_par_expContext ctx) {
		FixAxisPar f = new FixAxisPar();

		f.setOffset((Long) visit(ctx.Offset));
		f.setShift((Long) visit(ctx.Shift));
		f.setNumberapo((Long) visit(ctx.Numberapo));

		return f;
	}

	@Override
	public Object visitFix_axis_par_dist_exp(Fix_axis_par_dist_expContext ctx) {
		FixAxisParDist f = new FixAxisParDist();

		f.setOffset((Long) visit(ctx.Offset));
		f.setShift((Long) visit(ctx.Distance));
		f.setNumberapo((Long) visit(ctx.Numberapo));

		return f;
	}

	@Override
	public Object visitFix_axis_par_list_block(Fix_axis_par_list_blockContext ctx) {
		FixAxisParList f = new FixAxisParList();

		double[] v = new double[ctx.AxisPts_Values.size()];
		for (int i = 0; i < v.length; i++) {
			v[i] = (double) visit(ctx.AxisPts_Values.get(i));
		}
		f.setAxisPtsValues(v);

		return f;
	}

	@Override
	public Object visitCurve_axis_ref_exp(Curve_axis_ref_expContext ctx) {
		return ctx.CurveAxis.getText();
	}

	@Override
	public Object visitAxis_pts_ref_exp(Axis_pts_ref_expContext ctx) {
		return ctx.AxisPoints.getText();
	}

	@Override
	public Object visitAttribute_axis_descr_enum(Attribute_axis_descr_enumContext ctx) {
		if (ctx.CURVE_AXIS() != null) {
			return AxisDescr.Attribute.CURVE_AXIS;
		}

		if (ctx.COM_AXIS() != null) {
			return AxisDescr.Attribute.COM_AXIS;
		}

		if (ctx.FIX_AXIS() != null) {
			return AxisDescr.Attribute.FIX_AXIS;
		}

		if (ctx.RES_AXIS() != null) {
			return AxisDescr.Attribute.RES_AXIS;
		}

		if (ctx.STD_AXIS() != null) {
			return AxisDescr.Attribute.STD_AXIS;
		}

		logInvalidEnum(ctx, AxisDescr.Attribute.values());
		return null;
	}

	@Override
	public Object visitCalibration_access_enum(Calibration_access_enumContext ctx) {
		if (ctx.CALIBRATION() != null) {
			return CalibrationAccess.CALIBRATION;
		}

		if (ctx.NO_CALIBRATION() != null) {
			return CalibrationAccess.NO_CALIBRATION;
		}

		if (ctx.NOT_IN_MCD_SYSTEM() != null) {
			return CalibrationAccess.NOT_IN_MCD_SYSTEM;
		}

		if (ctx.OFFLINE_CALIBRATION() != null) {
			return CalibrationAccess.OFFLINE_CALIBRATION;
		}

		logInvalidEnum(ctx, CalibrationAccess.values());
		return null;
	}

	@Override
	public Object visitMonotony_enum(Monotony_enumContext ctx) {
		if (ctx.MON_DECREASE() != null) {
			return Monotony.MON_DECREASE;
		}

		if (ctx.MON_INCREASE() != null) {
			return Monotony.MON_INCREASE;
		}

		if (ctx.STRICT_DECREASE() != null) {
			return Monotony.STRICT_DECREASE;
		}

		if (ctx.STRICT_INCREASE() != null) {
			return Monotony.STRICT_INCREASE;
		}

		if (ctx.MONOTONOUS() != null) {
			return Monotony.MONOTONOUS;
		}

		if (ctx.STRICT_MON() != null) {
			return Monotony.STRICT_MON;
		}

		if (ctx.NOT_MON() != null) {
			return Monotony.NOT_MON;
		}

		logInvalidEnum(ctx, Monotony.values());
		return null;
	}

	@Override
	public Object visitA2ml_block(A2ml_blockContext ctx) {
		A2ml a = new A2ml();
		a.setContent(ctx.getText());
		return a;
	}

	@Override
	public Object visitIf_data_block(If_data_blockContext ctx) {
		IfData i = new IfData();
		i.setContent(ctx.getText());
		return i;
	}

	@Override
	public Object visitMod_common_block(Mod_common_blockContext ctx) {
		ModCommon m = new ModCommon();

		m.setComment(visitString(ctx.mod_common_exp().Comment));

		Mod_common_sub_nodesContext sn = ctx.mod_common_sub_nodes();

		m.setAlignmentByte((Long) visitSingleOpt(sn.alignment_byte_exp()));
		m.setAlignmentWord((Long) visitSingleOpt(sn.alignment_word_exp()));
		m.setAlignmentLong((Long) visitSingleOpt(sn.alignment_long_exp()));
		m.setAlignmentInt64((Long) visitSingleOpt(sn.alignment_int64_exp()));
		m.setAlignmentFloat32IEEE((Long) visitSingleOpt(sn.alignment_float32_exp()));
		m.setAlignmentFloat64IEEE((Long) visitSingleOpt(sn.alignment_float64_exp()));

		m.setDeposit((Deposit) visitSingleOpt(sn.deposit_exp()));
		m.setByteorder((ByteOrder) visitSingleOpt(sn.byte_order_exp()));
		m.setDataSize((Long) visitSingleOpt(sn.data_size_exp()));
		m.setStandardRecordLayout((String) visitSingleOpt(sn.s_rec_layout_exp()));

		return m;
	}

	@Override
	public Object visitDeposit_mode(Deposit_modeContext ctx) {
		if (ctx.ABSOLUTE() != null)
			return Deposit.ABSOLUTE;

		if (ctx.DIFFERENCE() != null)
			return Deposit.DIFFERENCE;

		logInvalidEnum(ctx, Deposit.values());
		return null;
	}

	@Override
	public Object visitByte_order_exp(Byte_order_expContext ctx) {
		return visit(ctx.ByteOrder);
	}

	@Override
	public Object visitByte_order_enum(Byte_order_enumContext ctx) {
		if (ctx.LITTLE_ENDIAN() != null)
			return ByteOrder.LITTLE_ENDIAN;

		if (ctx.BIG_ENDIAN() != null)
			return ByteOrder.BIG_ENDIAN;

		if (ctx.MSB_FIRST() != null)
			return ByteOrder.MSB_FIRST;

		if (ctx.MSB_LAST() != null)
			return ByteOrder.MSB_LAST;

		logInvalidEnum(ctx, ByteOrder.values());
		return null;
	}

	@Override
	public Object visitData_size_exp(Data_size_expContext ctx) {
		return visit(ctx.Size);
	}

	@Override
	public Object visitS_rec_layout_exp(S_rec_layout_expContext ctx) {
		return ctx.Name.getText();
	}

	@Override
	public Object visitMod_par_block(Mod_par_blockContext ctx) {
		ModPar m = new ModPar();

		m.setComment(visitString(ctx.mod_par_exp().Comment));

		Mod_par_sub_nodesContext sn = ctx.mod_par_sub_nodes();
		m.setAddresses(visitMultipleOpt(sn.addr_epk_exp(), Long.class));
		m.setCalibrationMethods(visitMultipleOpt(sn.calibration_method_block(), CalibrationMethod.class));
		m.setCpuType((String) visitSingleOpt(sn.cpu_type_exp()));
		m.setCustomer((String) visitSingleOpt(sn.customer_exp()));
		m.setCustomerNo((String) visitSingleOpt(sn.customer_no_exp()));
		m.setEcu((String) visitSingleOpt(sn.ecu_exp()));
		m.setEcuCalibrationOffset((Long) visitSingleOpt(sn.ecu_calibration_offset_exp()));
		m.setEpk((String) visitSingleOpt(sn.epk_exp()));
		m.setMemoryLayouts(visitMultipleOpt(sn.memory_layout_block(), MemoryLayout.class));
		m.setMemorySegments(visitMultipleOpt(sn.memory_segment_block(), MemorySegment.class));
		m.setNumberOfInterfaces((Long) visitSingleOpt(sn.no_of_interfaces_exp()));
		m.setPhoneNumber((String) visitSingleOpt(sn.phone_no_exp()));
		m.setSupplier((String) visitSingleOpt(sn.supplier_exp()));
		m.setSystemConstants(visitMultipleOpt(sn.system_constant_exp(), SystemConstant.class));
		m.setUser((String) visitSingleOpt(sn.user_exp()));
		m.setVersion((String) visitSingleOpt(sn.version_exp()));

		return m;
	}

	@Override
	public Object visitAddr_epk_exp(Addr_epk_expContext ctx) {
		return visit(ctx.Address);
	}

	@Override
	public Object visitCalibration_method_block(Calibration_method_blockContext ctx) {
		CalibrationMethod c = new CalibrationMethod();

		c.setName(visitString(ctx.calibration_method_exp().Method));
		c.setVersion((Long) visit(ctx.calibration_method_exp().Version));
		c.setCalibrationHandles(visitMultipleOpt(ctx.calibration_method_sub_nodes().calibration_handle_block(),
				CalibrationHandle.class));

		return c;
	}

	@Override
	public Object visitCalibration_handle_block(Calibration_handle_blockContext ctx) {
		CalibrationHandle c = new CalibrationHandle();

		c.setHandles(visitMultipleOpt(ctx.Handle, Long.class));
		c.setCalibrationText((String) visitOpt(ctx.calibration_handle_text_exp()));

		return c;
	}

	@Override
	public Object visitCalibration_handle_text_exp(Calibration_handle_text_expContext ctx) {
		return visit(ctx.text);
	}

	@Override
	public Object visitCpu_type_exp(Cpu_type_expContext ctx) {
		return visit(ctx.CPU);
	}

	@Override
	public Object visitCustomer_exp(Customer_expContext ctx) {
		return visit(ctx.Customer);
	}

	@Override
	public Object visitCustomer_no_exp(Customer_no_expContext ctx) {
		return visit(ctx.Number);
	}

	@Override
	public Object visitEcu_exp(Ecu_expContext ctx) {
		return visit(ctx.ControlUnit);
	}

	@Override
	public Object visitEcu_calibration_offset_exp(Ecu_calibration_offset_expContext ctx) {
		return visit(ctx.Offset);
	}

	@Override
	public Object visitEpk_exp(Epk_expContext ctx) {
		return visit(ctx.Identifier);
	}

	@Override
	public Object visitMemory_layout_block(Memory_layout_blockContext ctx) {
		MemoryLayout m = new MemoryLayout();

		m.setPrgType((PrgType) visit(ctx.memory_layout_exp().PrgType));
		m.setAddress((Long) visit(ctx.memory_layout_exp().Address));
		m.setSize((Long) visit(ctx.memory_layout_exp().Size));
		m.setOffset(visitOffset5IntValues(ctx.memory_layout_exp().Offset));
		m.setIfDatas(visitMultipleOpt(ctx.memory_layout_sub_nodes().if_data_block(), IfData.class));

		return m;
	}

	@Override
	public Object visitPrg_type_memory_layout(Prg_type_memory_layoutContext ctx) {
		if (ctx.PRG_CODE() != null)
			return MemoryLayout.PrgType.PRG_CODE;

		if (ctx.PRG_DATA() != null)
			return MemoryLayout.PrgType.PRG_DATA;

		if (ctx.PRG_RESERVED() != null)
			return MemoryLayout.PrgType.PRG_RESERVED;

		logInvalidEnum(ctx, MemoryLayout.PrgType.values());
		return null;
	}

	private <E extends Enum<E>> void logInvalidEnum(ParserRuleContext ctx, E[] possibleValues) {
		String enclosingClassName = null;
		if (possibleValues != null && possibleValues.length > 0) {
			Class<?> enclosingClass = possibleValues[0].getClass().getEnclosingClass();
			if (enclosingClass != null) {
				enclosingClassName = enclosingClass.getSimpleName();
			}
		}
		logInvalidEnum(ctx, possibleValues, enclosingClassName);
	}

	private <E extends Enum<E>> void logInvalidEnum(ParserRuleContext ctx, E[] possibleValues, String containingBlock) {
		String pv = "["
				+ String.join(",", Arrays.stream(possibleValues).map((v) -> v.name()).collect(Collectors.toList()))
				+ "]";
		String enumName = possibleValues[0].getClass().getSimpleName();

		String enclosingBlockMessage = "";
		if (containingBlock != null && !containingBlock.trim().isEmpty()) {
			enclosingBlockMessage = " of " + containingBlock;
		}

		log.log(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), "Invalid enum value for " + enumName
				+ enclosingBlockMessage + ": " + ctx.getText() + System.lineSeparator() + "Possible values are " + pv);
	}

	@Override
	public Object visitMemory_segment_block(Memory_segment_blockContext ctx) {
		MemorySegment m = new MemorySegment();

		m.setName(ctx.memory_segment_exp().Name.getText());
		m.setLongIdentifier(visitString(ctx.memory_segment_exp().LongIdentifier));
		m.setPrgType((MemorySegment.PrgType) visit(ctx.memory_segment_exp().PrgType));
		m.setMemoryType((MemoryType) visit(ctx.memory_segment_exp().MemoryType));
		m.setAttribute((Attribute) visit(ctx.memory_segment_exp().Attribute));
		m.setAddress((Long) visit(ctx.memory_segment_exp().Address));
		m.setSize((Long) visit(ctx.memory_segment_exp().Size));
		m.setOffset(visitOffset5IntValues(ctx.memory_segment_exp().Offset));

		m.setIfDatas(visitMultipleOpt(ctx.memory_segment_sub_nodes().if_data_block(), IfData.class));

		return m;
	}

	@Override
	public Object visitPrg_type_memory_segment(Prg_type_memory_segmentContext ctx) {
		if (ctx.CALIBRATION_VARIABLES() != null)
			return MemorySegment.PrgType.CALIBRATION_VARIABLES;

		if (ctx.CODE() != null)
			return MemorySegment.PrgType.CODE;

		if (ctx.DATA() != null)
			return MemorySegment.PrgType.DATA;

		if (ctx.EXCLUDE_FROM_FLASH() != null)
			return MemorySegment.PrgType.EXCLUDE_FROM_FLASH;

		if (ctx.OFFLINE_DATA() != null)
			return MemorySegment.PrgType.OFFLINE_DATA;

		if (ctx.RESERVED() != null)
			return MemorySegment.PrgType.RESERVED;

		if (ctx.SERAM() != null)
			return MemorySegment.PrgType.SERAM;

		if (ctx.VARIABLES() != null)
			return MemorySegment.PrgType.VARIABLES;

		logInvalidEnum(ctx, MemorySegment.PrgType.values());
		return null;
	}

	@Override
	public Object visitMemory_type(Memory_typeContext ctx) {
		if (ctx.EEPROM() != null)
			return MemoryType.EEPROM;

		if (ctx.EPROM() != null)
			return MemoryType.EPROM;

		if (ctx.FLASH() != null)
			return MemoryType.FLASH;

		if (ctx.RAM() != null)
			return MemoryType.RAM;

		if (ctx.ROM() != null)
			return MemoryType.ROM;

		if (ctx.REGISTER() != null)
			return MemoryType.REGISTER;

		if (ctx.NOT_IN_ECU() != null)
			return MemoryType.NOT_IN_ECU;

		logInvalidEnum(ctx, MemoryType.values());
		return null;
	}

	@Override
	public Object visitAttribute_memory_segment(Attribute_memory_segmentContext ctx) {
		if (ctx.INTERN() != null)
			return MemorySegment.Attribute.INTERN;

		if (ctx.EXTERN() != null)
			return MemorySegment.Attribute.EXTERN;

		logInvalidEnum(ctx, MemorySegment.Attribute.values());
		return null;
	}

	@Override
	public Object visitNo_of_interfaces_exp(No_of_interfaces_expContext ctx) {
		return visit(ctx.Num);
	}

	@Override
	public Object visitPhone_no_exp(Phone_no_expContext ctx) {
		return visit(ctx.PhoneNo);
	}

	@Override
	public Object visitSupplier_exp(Supplier_expContext ctx) {
		return visit(ctx.Manufacturer);
	}

	@Override
	public Object visitSystem_constant_exp(System_constant_expContext ctx) {
		SystemConstant s = new SystemConstant();

		s.setName(visitString(ctx.Name));
		s.setValue(visitString(ctx.Value));

		return s;
	}

	@Override
	public Object visitUser_exp(User_expContext ctx) {
		return visit(ctx.UserName);
	}

	@Override
	public Object visitCompu_method_block(Compu_method_blockContext ctx) {
		CompuMethod c = new CompuMethod();

		c.setName(ctx.compu_method_exp().Name.getText());
		c.setLongIdentifier(visitString(ctx.compu_method_exp().LongIdentifier));
		c.setConversionType((ConversionType) visit(ctx.compu_method_exp().ConversionType));
		c.setFormat(visitString(ctx.compu_method_exp().Format));
		c.setUnit(visitString(ctx.compu_method_exp().Unit));

		Compu_method_sub_nodesContext sn = ctx.compu_method_sub_nodes();
		c.setCoeffs((Coeffs) visitSingleOpt(sn.coeffs_exp()));
		c.setCoeffsLinear((CoeffsLinear) visitSingleOpt(sn.coeffs_linear_exp()));
		c.setCompuTab_ref((String) visitSingleOpt(sn.compu_tab_ref_exp()));
		c.setFormula((Formula) visitSingleOpt(sn.formula_block()));
		c.setUnit_ref((String) visitSingleOpt(sn.ref_unit_exp()));
		c.setConversionTable_ref((String) visitSingleOpt(sn.status_string_ref_exp()));

		return c;
	}

	@Override
	public Object visitCoeffs_exp(Coeffs_expContext ctx) {
		Coeffs c = new Coeffs();

		c.setA((Double) visit(ctx.a));
		c.setB((Double) visit(ctx.b));
		c.setC((Double) visit(ctx.c));
		c.setD((Double) visit(ctx.d));
		c.setE((Double) visit(ctx.e));
		c.setF((Double) visit(ctx.f));

		return c;
	}

	@Override
	public Object visitValue(ValueContext ctx) {
		return Double.parseDouble(ctx.getText());
	}

	@Override
	public Object visitCoeffs_linear_exp(Coeffs_linear_expContext ctx) {
		CoeffsLinear c = new CoeffsLinear();

		c.setA((Double) visit(ctx.a));
		c.setB((Double) visit(ctx.b));

		return c;
	}

	@Override
	public Object visitCompu_tab_ref_exp(Compu_tab_ref_expContext ctx) {
		return ctx.ConversionTable.getText();
	}

	@Override
	public Object visitFormula_block(Formula_blockContext ctx) {
		Formula f = new Formula();

		f.setFx(visitString(ctx.formula_exp().f_x));
		f.setGx((String) visitOpt(ctx.formula_inv_exp()));

		return f;
	}

	@Override
	public Object visitFormula_inv_exp(Formula_inv_expContext ctx) {
		return visit(ctx.g_x);
	}

	@Override
	public Object visitRef_unit_exp(Ref_unit_expContext ctx) {
		return ctx.Unit.getText();
	}

	@Override
	public Object visitStatus_string_ref_exp(Status_string_ref_expContext ctx) {
		return ctx.ConversionTable.getText();
	}

	@Override
	public Object visitConversion_type(Conversion_typeContext ctx) {
		if (ctx.IDENTICAL() != null)
			return ConversionType.IDENTICAL;

		if (ctx.FORM() != null)
			return ConversionType.FORM;

		if (ctx.LINEAR() != null)
			return ConversionType.LINEAR;

		if (ctx.RAT_FUNC() != null)
			return ConversionType.RAT_FUNC;

		if (ctx.TAB_INTP() != null)
			return ConversionType.TAB_INTP;

		if (ctx.TAB_NOINTP() != null)
			return ConversionType.TAB_NOINTP;

		if (ctx.TAB_VERB() != null)
			return ConversionType.TAB_VERB;

		logInvalidEnum(ctx, ConversionType.values());
		return null;
	}

	@Override
	public Object visitCompu_vtab_block(Compu_vtab_blockContext ctx) {
		CompuVTab c = new CompuVTab();

		c.setName(ctx.compu_vtab_exp().Name.getText());
		c.setLongIdentifier(visitString(ctx.compu_vtab_exp().LongIdentifier));
		if (ctx.compu_vtab_exp().ConversionType.getType() == a2lLexer.TAB_VERB) {
			c.setConversionType(ConversionType.TAB_VERB);
		} else {
			logInvalidEnum(ctx.compu_vtab_exp(), new ConversionType[] { ConversionType.TAB_VERB }, "COMPU_VTAB");
		}
		c.setNumberOfValuePairs((Long) visit(ctx.compu_vtab_exp().NumberValuePairs));

		List<ValuePair<Double, String>> vps = new ArrayList<ValuePair<Double, String>>();
		for (Value_description_pairContext sub_ctx : ctx.compu_vtab_exp().ValuePairs) {
			ValuePair<Double, String> vp = new ValuePair<>();
			vp.setInVal((Double) visit(sub_ctx.InVal));
			vp.setOutVal(visitString(sub_ctx.OutVal));
			vps.add(vp);
		}
		c.setValuePairs(vps);
		c.setDefaultValue((String) visitOpt(ctx.compu_vtab_default_value()));

		return c;
	}

	@Override
	public Object visitDefault_value_exp(Default_value_expContext ctx) {
		return visit(ctx.display_string);
	}

	@Override
	public Object visitCompu_vtab_range_block(Compu_vtab_range_blockContext ctx) {
		CompuVTabRange c = new CompuVTabRange();

		c.setName(ctx.compu_vtab_range_exp().Name.getText());
		c.setLongIdentifier(visitString(ctx.compu_vtab_range_exp().LongIdentifier));
		c.setNumberOfValueTriples((Long) visit(ctx.compu_vtab_range_exp().NumberValueTriples));

		List<ValueTriple<Double, String>> vts = new ArrayList<ValueTriple<Double, String>>();
		for (Value_description_tripleContext sub_ctx : ctx.compu_vtab_range_exp().ValueTriples) {
			ValueTriple<Double, String> vt = new ValueTriple<Double, String>();
			vt.setInValMin((Double) visit(sub_ctx.InValMin));
			vt.setInValMax((Double) visit(sub_ctx.InValMax));
			vt.setOutVal(visitString(sub_ctx.OutVal));
			vts.add(vt);
		}
		c.setValueTriples(vts);

		c.setDefaultValue((String) visitOpt(ctx.compu_vtab_range_default_value()));

		return c;
	}

	@Override
	public Object visitMeasurement_block(Measurement_blockContext ctx) {
		Measurement m = new Measurement();

		m.setName(ctx.measurement_exp().Name.getText());
		m.setLongIdentifier(visitString(ctx.measurement_exp().LongIdentifier));
		m.setDatatype((DataType) visit(ctx.measurement_exp().Datatype));
		m.setConversion(ctx.measurement_exp().Conversion.getText());
		m.setResolution((Long) visit(ctx.measurement_exp().Resolution));
		m.setAccuracy((Double) visit(ctx.measurement_exp().Accuracy));
		m.setLowerLimit((Double) visit(ctx.measurement_exp().LowerLimit));
		m.setUpperLimit((Double) visit(ctx.measurement_exp().UpperLimit));

		Measurement_sub_nodesContext sn = ctx.measurement_sub_nodes();
		m.setAnnotations(visitMultipleOpt(sn.annotation_block(), Annotation.class));
		m.setArraySize((Long) visitSingleOpt(sn.array_size_exp()));
		m.setBitMask((Long) visitSingleOpt(sn.bit_mask_exp()));
		m.setBitOperation((BitOperation) visitSingleOpt(sn.bit_operation_block()));
		m.setByteorder((ByteOrder) visitSingleOpt(sn.byte_order_exp()));
		m.setDiscrete((Boolean) visitSingleOptBool(sn.discrete_exp()));
		m.setDisplayIdentifier((String) visitSingleOpt(sn.display_identifier_exp()));
		m.setEcuAddress((Long) visitSingleOpt(sn.ecu_address_exp()));
		m.setEcuAddressExtension((Long) visitSingleOpt(sn.ecu_address_extension_exp()));
		m.setErrorMask((Long) visitSingleOpt(sn.error_mask_exp()));
		m.setFormat((String) visitSingleOpt(sn.format_exp()));
		m.setFunctionList((FunctionList) visitSingleOpt(sn.function_list_block()));
		m.setIfDatas(visitMultipleOpt(sn.if_data_block(), IfData.class));
		m.setLayout((LayoutIndexMode) visitSingleOpt(sn.layout_exp()));
		m.setMatrixDim((MatrixDim) visitSingleOpt(sn.matrix_dim_exp()));
		m.setMaxRefresh((MaxRefresh) visitSingleOpt(sn.max_refresh_exp()));
		m.setPhysUnit((String) visitSingleOpt(sn.phys_unit_exp()));
		m.setReadWrite((Boolean) visitSingleOptBool(sn.read_write_exp()));
		m.setMemorySegment((String) visitSingleOpt(sn.ref_memory_segment_exp()));
		m.setSymbolLink((SymbolLink) visitSingleOpt(sn.symbol_link_exp()));
		m.setVirtual((Virtual) visitSingleOpt(sn.virtual_block()));

		return m;
	}

	@Override
	public Object visitDiscrete_exp(Discrete_expContext ctx) {
		if (ctx.DISCRETE() != null)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	@Override
	public Object visitDisplay_identifier_exp(Display_identifier_expContext ctx) {
		return ctx.display_name.getText();
	}

	@Override
	public Object visitEcu_address_exp(Ecu_address_expContext ctx) {
		return visit(ctx.Address);
	}

	@Override
	public Object visitEcu_address_extension_exp(Ecu_address_extension_expContext ctx) {
		return visit(ctx.Extension);
	}

	@Override
	public Object visitError_mask_exp(Error_mask_expContext ctx) {
		return visit(ctx.Mask);
	}

	@Override
	public Object visitFormat_exp(Format_expContext ctx) {
		return visit(ctx.Format);
	}

	@Override
	public Object visitFunction_list_block(Function_list_blockContext ctx) {
		FunctionList names = new FunctionList();

		for (Token n : ctx.Names) {
			names.add(n.getText());
		}

		return names;
	}

	@Override
	public Object visitLayout_exp(Layout_expContext ctx) {
		return visit(ctx.IndexMode);
	}

	@Override
	public Object visitMatrix_dim_exp(Matrix_dim_expContext ctx) {
		MatrixDim m = new MatrixDim();

		m.setxDim((Long) visitOpt(ctx.xDim));
		m.setyDim((Long) visitOpt(ctx.yDim));
		m.setzDim((Long) visitOpt(ctx.zDim));

		return m;
	}

	@Override
	public Object visitMax_refresh_exp(Max_refresh_expContext ctx) {
		MaxRefresh m = new MaxRefresh();

		m.setRate((Long) visit(ctx.Rate));
		m.setScalingUnit((Long) visit(ctx.ScalingUnit));

		return m;
	}

	@Override
	public Object visitPhys_unit_exp(Phys_unit_expContext ctx) {
		return visit(ctx.Unit);
	}

	@Override
	public Object visitRead_write_exp(Read_write_expContext ctx) {
		return ctx.READ_WRITE() != null;
	}

	@Override
	public Object visitRef_memory_segment_exp(Ref_memory_segment_expContext ctx) {
		return ctx.Name.getText();
	}

	@Override
	public Object visitSymbol_link_exp(Symbol_link_expContext ctx) {
		SymbolLink s = new SymbolLink();

		s.setSymbolName(visitString(ctx.SymbolName));
		s.setOffset((Long) visit(ctx.Offset));

		return s;
	}

	@Override
	public Object visitVirtual_block(Virtual_blockContext ctx) {
		Virtual v = new Virtual();

		for (Token c : ctx.MeasuringChannels) {
			v.add(c.getText());
		}

		return v;
	}

	@Override
	public Object visitDatatype_enum(Datatype_enumContext ctx) {
		if (ctx.UBYTE() != null)
			return DataType.UBYTE;

		if (ctx.SBYTE() != null)
			return DataType.SBYTE;

		if (ctx.UWORD() != null)
			return DataType.UWORD;

		if (ctx.SWORD() != null)
			return DataType.SWORD;

		if (ctx.ULONG() != null)
			return DataType.ULONG;

		if (ctx.SLONG() != null)
			return DataType.SLONG;

		if (ctx.A_UINT64() != null)
			return DataType.A_UINT64;

		if (ctx.A_INT64() != null)
			return DataType.A_INT64;

		if (ctx.FLOAT32_IEEE() != null)
			return DataType.FLOAT32_IEEE;

		if (ctx.FLOAT64_IEEE() != null)
			return DataType.FLOAT64_IEEE;

		logInvalidEnum(ctx, DataType.values());
		return null;
	}

	@Override
	public Object visitAnnotation_block(Annotation_blockContext ctx) {
		Annotation a = new Annotation();

		Annotation_sub_nodesContext sn = ctx.annotation_sub_nodes();
		a.setLabel((String) visitSingleOpt(sn.annotation_label_exp()));
		a.setOrigin((String) visitSingleOpt(sn.annotation_origin_exp()));
		a.setText((AnnotationText) visitSingleOpt(sn.annotation_text_block()));

		return a;
	}

	@Override
	public Object visitAnnotation_label_exp(Annotation_label_expContext ctx) {
		return visit(ctx.label);
	}

	@Override
	public Object visitAnnotation_origin_exp(Annotation_origin_expContext ctx) {
		return visit(ctx.origin);
	}

	@Override
	public Object visitAnnotation_text_block(Annotation_text_blockContext ctx) {
		AnnotationText texts = new AnnotationText();

		for (a2lParser.String_expContext t : ctx.annotation_text) {
			texts.add(visitString(t));
		}

		return texts;
	}

	@Override
	public Object visitBit_mask_exp(Bit_mask_expContext ctx) {
		return visit(ctx.Mask);
	}

	@Override
	public Object visitBit_operation_block(Bit_operation_blockContext ctx) {
		BitOperation b = new BitOperation();

		Bit_operation_sub_nodesContext sn = ctx.bit_operation_sub_nodes();
		b.setLeftShift((Long) visitSingleOpt(sn.left_shift_exp()));
		b.setRightShift((Long) visitSingleOpt(sn.right_shift_exp()));
		b.setSignExtend(visitSingleOptBool(sn.sign_extend_exp()));

		return b;
	}

	@Override
	public Object visitLeft_shift_exp(Left_shift_expContext ctx) {
		return visit(ctx.Bitcount);
	}

	@Override
	public Object visitRight_shift_exp(Right_shift_expContext ctx) {
		return visit(ctx.Bitcount);
	}

	@Override
	public Object visitSign_extend_exp(Sign_extend_expContext ctx) {
		return ctx.SIGN_EXTEND() != null;
	}

	@Override
	public Object visitArray_size_exp(Array_size_expContext ctx) {
		return visit(ctx.Number);
	}

	@Override
	public Object visitRecord_layout_block(Record_layout_blockContext ctx) {
		RecordLayout r = new RecordLayout();

		r.setName(ctx.record_layout_exp().Name.getText());

		Record_layout_sub_nodesContext sn = ctx.record_layout_sub_nodes();

		r.setAlignmentByte((Long) visitSingleOpt(sn.alignment_byte_exp()));
		r.setAlignmentWord((Long) visitSingleOpt(sn.alignment_word_exp()));
		r.setAlignmentLong((Long) visitSingleOpt(sn.alignment_long_exp()));
		r.setAlignmentInt64((Long) visitSingleOpt(sn.alignment_int64_exp()));
		r.setAlignmentFloat32IEEE((Long) visitSingleOpt(sn.alignment_float32_exp()));
		r.setAlignmentFloat64IEEE((Long) visitSingleOpt(sn.alignment_float64_exp()));

		r.setAxisPtsX((AxisPtsXYZ45) visitSingleOpt(sn.axis_pts_x_exp()));
		r.setAxisPtsY((AxisPtsXYZ45) visitSingleOpt(sn.axis_pts_y_exp()));
		r.setAxisPtsZ((AxisPtsXYZ45) visitSingleOpt(sn.axis_pts_z_exp()));
		r.setAxisPts4((AxisPtsXYZ45) visitSingleOpt(sn.axis_pts_4_exp()));
		r.setAxisPts5((AxisPtsXYZ45) visitSingleOpt(sn.axis_pts_5_exp()));

		r.setAxisRescaleX((AxisRescaleXYZ45) visitSingleOpt(sn.axis_rescale_x_exp()));
		r.setAxisRescaleY((AxisRescaleXYZ45) visitSingleOpt(sn.axis_rescale_y_exp()));
		r.setAxisRescaleZ((AxisRescaleXYZ45) visitSingleOpt(sn.axis_rescale_z_exp()));
		r.setAxisRescale4((AxisRescaleXYZ45) visitSingleOpt(sn.axis_rescale_4_exp()));
		r.setAxisRescale5((AxisRescaleXYZ45) visitSingleOpt(sn.axis_rescale_5_exp()));

		r.setDistOpX((DistOpXYZ45) visitSingleOpt(sn.dist_op_x_exp()));
		r.setDistOpY((DistOpXYZ45) visitSingleOpt(sn.dist_op_y_exp()));
		r.setDistOpZ((DistOpXYZ45) visitSingleOpt(sn.dist_op_z_exp()));
		r.setDistOp4((DistOpXYZ45) visitSingleOpt(sn.dist_op_4_exp()));
		r.setDistOp5((DistOpXYZ45) visitSingleOpt(sn.dist_op_5_exp()));

		r.setFixNoAxisPtsX((FixNoAxisPtsXYZ45) visitSingleOpt(sn.fix_no_axis_pts_x_exp()));
		r.setFixNoAxisPtsY((FixNoAxisPtsXYZ45) visitSingleOpt(sn.fix_no_axis_pts_y_exp()));
		r.setFixNoAxisPtsZ((FixNoAxisPtsXYZ45) visitSingleOpt(sn.fix_no_axis_pts_z_exp()));
		r.setFixNoAxisPts4((FixNoAxisPtsXYZ45) visitSingleOpt(sn.fix_no_axis_pts_4_exp()));
		r.setFixNoAxisPts5((FixNoAxisPtsXYZ45) visitSingleOpt(sn.fix_no_axis_pts_5_exp()));

		r.setFunctionValues((FncValues) visitSingleOpt(sn.fnc_values_exp()));
		r.setIdentification((Identification) visitSingleOpt(sn.identification_exp()));

		r.setNoAxisPtsX((NoAxisPtsXYZ45) visitSingleOpt(sn.no_axis_pts_x_exp()));
		r.setNoAxisPtsY((NoAxisPtsXYZ45) visitSingleOpt(sn.no_axis_pts_y_exp()));
		r.setNoAxisPtsZ((NoAxisPtsXYZ45) visitSingleOpt(sn.no_axis_pts_z_exp()));
		r.setNoAxisPts4((NoAxisPtsXYZ45) visitSingleOpt(sn.no_axis_pts_4_exp()));
		r.setNoAxisPts5((NoAxisPtsXYZ45) visitSingleOpt(sn.no_axis_pts_5_exp()));

		r.setNoRescaleX((NoRescaleXYZ45) visitSingleOpt(sn.no_rescale_x_exp()));
		r.setNoRescaleY((NoRescaleXYZ45) visitSingleOpt(sn.no_rescale_y_exp()));
		r.setNoRescaleZ((NoRescaleXYZ45) visitSingleOpt(sn.no_rescale_z_exp()));
		r.setNoRescale4((NoRescaleXYZ45) visitSingleOpt(sn.no_rescale_4_exp()));
		r.setNoRescale5((NoRescaleXYZ45) visitSingleOpt(sn.no_rescale_5_exp()));

		r.setOffsetX((OffsetXYZ45) visitSingleOpt(sn.offset_x_exp()));
		r.setOffsetY((OffsetXYZ45) visitSingleOpt(sn.offset_y_exp()));
		r.setOffsetZ((OffsetXYZ45) visitSingleOpt(sn.offset_z_exp()));
		r.setOffset4((OffsetXYZ45) visitSingleOpt(sn.offset_4_exp()));
		r.setOffset5((OffsetXYZ45) visitSingleOpt(sn.offset_5_exp()));

		r.setReserved(visitMultipleOpt(sn.reserved_exp(), Reserved.class));

		r.setRipAddressW((RipAddrWXYZ45) visitSingleOpt(sn.rip_addr_w_exp()));
		r.setRipAddressX((RipAddrWXYZ45) visitSingleOpt(sn.rip_addr_x_exp()));
		r.setRipAddressY((RipAddrWXYZ45) visitSingleOpt(sn.rip_addr_y_exp()));
		r.setRipAddressZ((RipAddrWXYZ45) visitSingleOpt(sn.rip_addr_z_exp()));
		r.setRipAddress4((RipAddrWXYZ45) visitSingleOpt(sn.rip_addr_4_exp()));
		r.setRipAddress5((RipAddrWXYZ45) visitSingleOpt(sn.rip_addr_5_exp()));

		r.setSrcAddressX((SrcAddrXYZ45) visitSingleOpt(sn.src_addr_x_exp()));
		r.setSrcAddressY((SrcAddrXYZ45) visitSingleOpt(sn.src_addr_y_exp()));
		r.setSrcAddressZ((SrcAddrXYZ45) visitSingleOpt(sn.src_addr_z_exp()));
		r.setSrcAddress4((SrcAddrXYZ45) visitSingleOpt(sn.src_addr_4_exp()));
		r.setSrcAddress5((SrcAddrXYZ45) visitSingleOpt(sn.src_addr_5_exp()));

		r.setShiftOpX((ShiftOpXYZ45) visitSingleOpt(sn.shift_op_x_exp()));
		r.setShiftOpY((ShiftOpXYZ45) visitSingleOpt(sn.shift_op_y_exp()));
		r.setShiftOpZ((ShiftOpXYZ45) visitSingleOpt(sn.shift_op_z_exp()));
		r.setShiftOp4((ShiftOpXYZ45) visitSingleOpt(sn.shift_op_4_exp()));
		r.setShiftOp5((ShiftOpXYZ45) visitSingleOpt(sn.shift_op_5_exp()));

		r.setStaticRecordLayout(visitSingleOptBool(sn.static_record_layout_exp()));

		return r;
	}
	
	@Override
	public Object visitStatic_record_layout_exp(Static_record_layout_expContext ctx) {
		if(ctx.STATIC_RECORD_LAYOUT() != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object visitFix_no_axis_pts_x_exp(Fix_no_axis_pts_x_expContext ctx) {
		FixNoAxisPtsXYZ45 f = new FixNoAxisPtsXYZ45();
		f.setNumberOfAxisPoints((Long) visit(ctx.NumberOfAxisPoints));
		return f;
	}

	@Override
	public Object visitFix_no_axis_pts_y_exp(Fix_no_axis_pts_y_expContext ctx) {
		FixNoAxisPtsXYZ45 f = new FixNoAxisPtsXYZ45();
		f.setNumberOfAxisPoints((Long) visit(ctx.NumberOfAxisPoints));
		return f;
	}

	@Override
	public Object visitFix_no_axis_pts_z_exp(Fix_no_axis_pts_z_expContext ctx) {
		FixNoAxisPtsXYZ45 f = new FixNoAxisPtsXYZ45();
		f.setNumberOfAxisPoints((Long) visit(ctx.NumberOfAxisPoints));
		return f;
	}

	@Override
	public Object visitFix_no_axis_pts_4_exp(Fix_no_axis_pts_4_expContext ctx) {
		FixNoAxisPtsXYZ45 f = new FixNoAxisPtsXYZ45();
		f.setNumberOfAxisPoints((Long) visit(ctx.NumberOfAxisPoints));
		return f;
	}

	@Override
	public Object visitFix_no_axis_pts_5_exp(Fix_no_axis_pts_5_expContext ctx) {
		FixNoAxisPtsXYZ45 f = new FixNoAxisPtsXYZ45();
		f.setNumberOfAxisPoints((Long) visit(ctx.NumberOfAxisPoints));
		return f;
	}

	// @formatter:off
	/******** GENERATED  CODE START ********/
	@Override public Object visitDist_op_x_exp(Dist_op_x_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);DistOpXYZ45 d = new DistOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitDist_op_y_exp(Dist_op_y_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);DistOpXYZ45 d = new DistOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitDist_op_z_exp(Dist_op_z_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);DistOpXYZ45 d = new DistOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitDist_op_4_exp(Dist_op_4_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);DistOpXYZ45 d = new DistOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitDist_op_5_exp(Dist_op_5_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);DistOpXYZ45 d = new DistOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}

	@Override public Object visitNo_axis_pts_x_exp(No_axis_pts_x_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoAxisPtsXYZ45 d = new NoAxisPtsXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_axis_pts_y_exp(No_axis_pts_y_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoAxisPtsXYZ45 d = new NoAxisPtsXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_axis_pts_z_exp(No_axis_pts_z_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoAxisPtsXYZ45 d = new NoAxisPtsXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_axis_pts_4_exp(No_axis_pts_4_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoAxisPtsXYZ45 d = new NoAxisPtsXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_axis_pts_5_exp(No_axis_pts_5_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoAxisPtsXYZ45 d = new NoAxisPtsXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}


	@Override public Object visitNo_rescale_x_exp(No_rescale_x_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoRescaleXYZ45 d = new NoRescaleXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_rescale_y_exp(No_rescale_y_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoRescaleXYZ45 d = new NoRescaleXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_rescale_z_exp(No_rescale_z_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoRescaleXYZ45 d = new NoRescaleXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_rescale_4_exp(No_rescale_4_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoRescaleXYZ45 d = new NoRescaleXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitNo_rescale_5_exp(No_rescale_5_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);NoRescaleXYZ45 d = new NoRescaleXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}

	@Override public Object visitOffset_x_exp(Offset_x_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);OffsetXYZ45 d = new OffsetXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitOffset_y_exp(Offset_y_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);OffsetXYZ45 d = new OffsetXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitOffset_z_exp(Offset_z_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);OffsetXYZ45 d = new OffsetXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitOffset_4_exp(Offset_4_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);OffsetXYZ45 d = new OffsetXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitOffset_5_exp(Offset_5_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);OffsetXYZ45 d = new OffsetXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}

	@Override public Object visitRip_addr_w_exp(Rip_addr_w_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);RipAddrWXYZ45 d = new RipAddrWXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitRip_addr_x_exp(Rip_addr_x_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);RipAddrWXYZ45 d = new RipAddrWXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitRip_addr_y_exp(Rip_addr_y_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);RipAddrWXYZ45 d = new RipAddrWXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitRip_addr_z_exp(Rip_addr_z_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);RipAddrWXYZ45 d = new RipAddrWXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitRip_addr_4_exp(Rip_addr_4_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);RipAddrWXYZ45 d = new RipAddrWXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitRip_addr_5_exp(Rip_addr_5_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);RipAddrWXYZ45 d = new RipAddrWXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}

	@Override public Object visitSrc_addr_x_exp(Src_addr_x_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);SrcAddrXYZ45 d = new SrcAddrXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitSrc_addr_y_exp(Src_addr_y_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);SrcAddrXYZ45 d = new SrcAddrXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitSrc_addr_z_exp(Src_addr_z_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);SrcAddrXYZ45 d = new SrcAddrXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitSrc_addr_4_exp(Src_addr_4_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);SrcAddrXYZ45 d = new SrcAddrXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitSrc_addr_5_exp(Src_addr_5_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);SrcAddrXYZ45 d = new SrcAddrXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}

	@Override public Object visitShift_op_x_exp(Shift_op_x_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);ShiftOpXYZ45 d = new ShiftOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitShift_op_y_exp(Shift_op_y_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);ShiftOpXYZ45 d = new ShiftOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitShift_op_z_exp(Shift_op_z_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);ShiftOpXYZ45 d = new ShiftOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitShift_op_4_exp(Shift_op_4_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);ShiftOpXYZ45 d = new ShiftOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	@Override public Object visitShift_op_5_exp(Shift_op_5_expContext ctx) {PositionDatatypeParameters pdp = (PositionDatatypeParameters) visit(ctx.Parameters);ShiftOpXYZ45 d = new ShiftOpXYZ45();d.setDataType(pdp.getDatatype());d.setPosition(pdp.getPosition());return d;}
	/******** GENERATED  CODE END **********/
	// @formatter:on

	@Override
	public Object visitReserved_exp(Reserved_expContext ctx) {
		Reserved r = new Reserved();
		r.setPosition((Long) visit(ctx.Position));
		r.setDataSize((DataSize) visit(ctx.DataSize));
		return r;
	}

	@Override
	public Object visitDatasize_enum(Datasize_enumContext ctx) {
		if (ctx.BYTE() != null) {
			return DataSize.BYTE;
		}

		if (ctx.LONG() != null) {
			return DataSize.LONG;
		}

		if (ctx.WORD() != null) {
			return DataSize.WORD;
		}

		logInvalidEnum(ctx, DataSize.values());
		return null;
	}

	@Override
	public Object visitIdentification_exp(Identification_expContext ctx) {
		Identification i = new Identification();
		i.setPosition((Long) visit(ctx.Position));
		i.setDataType((DataType) visit(ctx.Datatype));
		return i;
	}

	@Override
	public Object visitFnc_values_exp(Fnc_values_expContext ctx) {
		FncValues f = new FncValues();
		f.setPosition((Long) visit(ctx.Position));
		f.setDataType((DataType) visit(ctx.Datatype));
		f.setIndexMode((FncValues.IndexMode) visit(ctx.IndexMode));
		f.setAddressType((AddrType) visit(ctx.Addresstype));
		return f;
	}

	@Override
	public Object visitIndexmode_enum(Indexmode_enumContext ctx) {
		if (ctx.ROW_DIR() != null) {
			return LayoutIndexMode.ROW_DIR;
		}

		if (ctx.COLUMN_DIR() != null) {
			return LayoutIndexMode.COLUMN_DIR;
		}

		logInvalidEnum(ctx, LayoutIndexMode.values());
		return null;
	}

	@Override
	public Object visitIndex_mode_enum(Index_mode_enumContext ctx) {
		if (ctx.ALTERNATE_CURVES() != null) {
			return IndexMode.ALTERNATE_CURVES;
		}

		if (ctx.ALTERNATE_WITH_X() != null) {
			return IndexMode.ALTERNATE_WITH_X;
		}

		if (ctx.ALTERNATE_WITH_Y() != null) {
			return IndexMode.ALTERNATE_WITH_Y;
		}

		if (ctx.ROW_DIR() != null) {
			return IndexMode.ROW_DIR;
		}

		if (ctx.COLUMN_DIR() != null) {
			return IndexMode.COLUMN_DIR;
		}

		logInvalidEnum(ctx, IndexMode.values(), "FNC_VALUES");
		return null;
	}

	@Override
	public Object visitAddresstype_enum(Addresstype_enumContext ctx) {
		if (ctx.PBYTE() != null) {
			return AddrType.PBYTE;
		}

		if (ctx.PWORD() != null) {
			return AddrType.PWORD;
		}

		if (ctx.PLONG() != null) {
			return AddrType.PLONG;
		}

		if (ctx.DIRECT() != null) {
			return AddrType.DIRECT;
		}

		logInvalidEnum(ctx, AddrType.values());
		return null;
	}

	@Override
	public Object visitAxis_rescale_xyz45_parameters(Axis_rescale_xyz45_parametersContext ctx) {
		AxisRescaleXYZ45 a = new AxisRescaleXYZ45();
		a.setPosition((Long) visit(ctx.Position));
		a.setMaxNumberOfRescalePairs((Long) visit(ctx.MaxNumberOfrescalePairs));
		a.setIndexorder((IndexOrder) visit(ctx.IndexIncr));
		a.setDatatype((DataType) visit(ctx.Datatype));
		a.setAddressing((AddrType) visit(ctx.Adressing));
		return a;
	}

	@Override
	public Object visitIndex_order_enum(Index_order_enumContext ctx) {
		if (ctx.INDEX_DECR() != null) {
			return IndexOrder.INDEX_DECR;
		}

		if (ctx.INDEX_INCR() != null) {
			return IndexOrder.INDEX_INCR;
		}

		logInvalidEnum(ctx, IndexOrder.values());
		return null;
	}

	@Override
	public Object visitAxis_rescale_x_exp(Axis_rescale_x_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_rescale_y_exp(Axis_rescale_y_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_rescale_z_exp(Axis_rescale_z_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_rescale_4_exp(Axis_rescale_4_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_rescale_5_exp(Axis_rescale_5_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_pts_x_exp(Axis_pts_x_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_pts_y_exp(Axis_pts_y_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_pts_z_exp(Axis_pts_z_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_pts_4_exp(Axis_pts_4_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_pts_5_exp(Axis_pts_5_expContext ctx) {
		return visit(ctx.Parameters);
	}

	@Override
	public Object visitAxis_pts_xyz45_parameters(Axis_pts_xyz45_parametersContext ctx) {
		AxisPtsXYZ45 a = new AxisPtsXYZ45();
		a.setPosition((Long) visit(ctx.Position));
		a.setIndexorder((IndexOrder) visit(ctx.IndexIncr));
		a.setDatatype((DataType) visit(ctx.Datatype));
		a.setAddressing((AddrType) visit(ctx.Adresing));
		return a;
	}

	@Override
	public Object visitPosition_datatype_parameters(Position_datatype_parametersContext ctx) {
		PositionDatatypeParameters p = new PositionDatatypeParameters();

		p.setPosition((Long) visit(ctx.Position));
		p.setDatatype((DataType) visit(ctx.Datatype));

		return p;
	}

	private static class PositionDatatypeParameters {
		private long position;
		private DataType datatype;

		public long getPosition() {
			return position;
		}

		public void setPosition(long position) {
			this.position = position;
		}

		public DataType getDatatype() {
			return datatype;
		}

		public void setDatatype(DataType datatype) {
			this.datatype = datatype;
		}
	}

	@Override
	public Object visitAlignment_byte_exp(Alignment_byte_expContext ctx) {
		return visit(ctx.AlignmentBorder);
	}

	@Override
	public Object visitAlignment_float32_exp(Alignment_float32_expContext ctx) {
		return visit(ctx.AlignmentBorder);
	}

	@Override
	public Object visitAlignment_float64_exp(Alignment_float64_expContext ctx) {
		return visit(ctx.AlignmentBorder);
	}

	@Override
	public Object visitAlignment_int64_exp(Alignment_int64_expContext ctx) {
		return visit(ctx.AlignmentBorder);
	}

	@Override
	public Object visitAlignment_long_exp(Alignment_long_expContext ctx) {
		return visit(ctx.AlignmentBorder);
	}

	@Override
	public Object visitAlignment_word_exp(Alignment_word_expContext ctx) {
		return visit(ctx.AlignmentBorder);
	}

	@Override
	public Object visitCharacteristic_block(Characteristic_blockContext ctx) {
		Characteristic c = new Characteristic();

		Characteristic_expContext exp = ctx.characteristic_exp();
		c.setName(exp.Name.getText());
		c.setLongIdentifier(visitString(exp.LongIdentifier));
		c.setType((CharacteristicType) visit(exp.Type));
		c.setAddress((Long) visit(exp.Address));
		c.setDeposit(exp.Deposit.getText());
		c.setMaxDiff((Double) visit(exp.MaxDiff));
		c.setConversion(exp.Conversion.getText());
		c.setLowerLimit((Double) visit(exp.LowerLimit));
		c.setUpperLimit((Double) visit(exp.UpperLimit));

		Characteristic_sub_nodesContext sn = ctx.characteristic_sub_nodes();
		c.setNotes(visitMultipleOpt(sn.annotation_block(), Annotation.class));
		c.setAxisDescriptions(visitMultipleOpt(sn.axis_descr_block(), AxisDescr.class));
		c.setBitmask((Long) visitSingleOpt(sn.bit_mask_exp()));
		c.setByteorder((ByteOrder) visitSingleOpt(sn.byte_order_exp()));
		c.setAccess((CalibrationAccess) visitSingleOpt(sn.calibration_access_exp()));
		c.setComparisonQuantityMeasurment((String) visitSingleOpt(sn.comparison_quantity_exp()));
		c.setDependetCharacteristic((DependentCharacteristic) visitSingleOpt(sn.dependent_characteristic_block()));
		c.setDiscrete(visitSingleOptBool(sn.discrete_exp()));
		c.setDisplayIdentifier((String) visitSingleOpt(sn.display_identifier_exp()));
		c.setEcuAddressExtension((Long) visitSingleOpt(sn.ecu_address_extension_exp()));
		c.setExtendedLimits((ExtendedLimits) visitSingleOpt(sn.extended_limits_exp()));
		c.setFormat((String) visitSingleOpt(sn.format_exp()));
		c.setFunctions((FunctionList) visitSingleOpt(sn.function_list_block()));
		c.setGuardRails(visitSingleOptBool(sn.guard_rails_exp()));
		c.setIfData(visitMultipleOpt(sn.if_data_block(), IfData.class));
		c.setMapList((IdentReferenceList) visitSingleOpt(sn.map_list_block()));
		c.setMatrixDim((MatrixDim) visitSingleOpt(sn.matrix_dim_exp()));
		c.setMaxRefresh((MaxRefresh) visitSingleOpt(sn.max_refresh_exp()));
		c.setNumber((Long) visitSingleOpt(sn.number_exp()));
		c.setPhysUnit((String) visitSingleOpt(sn.phys_unit_exp()));
		c.setReadOnly(visitSingleOptBool(sn.read_only_exp()));
		c.setMemorySegment((String) visitSingleOpt(sn.ref_memory_segment_exp()));
		c.setStepSize((Double) visitSingleOpt(sn.step_size_exp()));
		c.setSymbolLink((SymbolLink) visitSingleOpt(sn.symbol_link_exp()));
		c.setVirtualCharacteristic((VirtualCharacteristic) visitSingleOpt(sn.virtual_characteristic_block()));

		return c;
	}
	
	@Override
	public Object visitDependent_characteristic_block(Dependent_characteristic_blockContext ctx) {
		DependentCharacteristic depChar = new DependentCharacteristic();
		
		depChar.setFormula(visitString(ctx.dependent_characteristic_exp().Formula));
		depChar.setCharacterstics(visitIdentifierTokenList(ctx.Characteristic));
		
		return depChar;
	}

	@Override
	public Object visitExtended_limits_exp(Extended_limits_expContext ctx) {
		ExtendedLimits e = new ExtendedLimits();

		e.setLowerLimit((Double) visit(ctx.LowerLimit));
		e.setUpperLimit((Double) visit(ctx.UpperLimit));

		return e;
	}

	@Override
	public Object visitStep_size_exp(Step_size_expContext ctx) {
		return visit(ctx.StepSize);
	}

	@Override
	public Object visitVirtual_characteristic_block(Virtual_characteristic_blockContext ctx) {
		VirtualCharacteristic vc = new VirtualCharacteristic();

		vc.setFormula(visitString(ctx.virtual_characteristic_exp().Formula));
		vc.setCharacterstics(visitIdentifierTokenList(ctx.Characteristics));

		return vc;
	}

	@Override
	public Object visitRead_only_exp(Read_only_expContext ctx) {
		if (ctx.READ_ONLY() != null) {
			return true;
		}

		return false;
	}

	@Override
	public Object visitNumber_exp(Number_expContext ctx) {
		return visit(ctx.Number);
	}

	@Override
	public Object visitMap_list_block(Map_list_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Names);
	}

	@Override
	public Object visitGuard_rails_exp(Guard_rails_expContext ctx) {
		if (ctx.GUARD_RAILS() != null) {
			return true;
		}

		return false;
	}

	@Override
	public Object visitComparison_quantity_exp(Comparison_quantity_expContext ctx) {
		return ctx.Name.getText();
	}

	@Override
	public Object visitType_enum(Type_enumContext ctx) {
		if (ctx.ASCII() != null) {
			return CharacteristicType.ASCII;
		}

		if (ctx.CURVE() != null) {
			return CharacteristicType.CURVE;
		}

		if (ctx.MAP() != null) {
			return CharacteristicType.MAP;
		}

		if (ctx.CUBOID() != null) {
			return CharacteristicType.CUBOID;
		}

		if (ctx.CUBE_4() != null) {
			return CharacteristicType.CUBE_4;
		}

		if (ctx.CUBE_5() != null) {
			return CharacteristicType.CUBE_5;
		}

		if (ctx.VAL_BLK() != null) {
			return CharacteristicType.VAL_BLK;
		}

		if (ctx.VALUE() != null) {
			return CharacteristicType.VALUE;
		}

		logInvalidEnum(ctx, CharacteristicType.values());
		return null;
	}

	@Override
	public Object visitAxis_pts_block(Axis_pts_blockContext ctx) {
		AxisPts a = new AxisPts();

		Axis_pts_expContext exp = ctx.axis_pts_exp();
		a.setName(exp.Name.getText());
		a.setLongIdentifier(visitString(exp.LongIdentifier));
		a.setAddress((Long) visit(exp.Address));
		a.setInputQuantitiy(exp.InputQuantity.getText());
		a.setDeposit(exp.Deposit.getText());
		a.setMaxDiff((Double) visit(exp.MaxDiff));
		a.setConversion(exp.Conversion.getText());
		a.setMaxAxisPoints((Long) visit(exp.MaxAxisPoints));
		a.setLowerLimit((Double) visit(exp.LowerLimit));
		a.setUpperLimit((Double) visit(exp.UpperLimit));

		Axis_pts_sub_nodesContext sn = ctx.axis_pts_sub_nodes();
		a.setNotes(visitMultipleOpt(sn.annotation_block(), Annotation.class));
		a.setByteorder((ByteOrder) visitSingleOpt(sn.byte_order_exp()));
		a.setAccess((CalibrationAccess) visitSingleOpt(sn.calibration_access_exp()));
		a.setAxisPointDeposit((Deposit) visitSingleOpt(sn.deposit_exp()));
		a.setDisplayIdentifier((String) visitSingleOpt(sn.display_identifier_exp()));
		a.setEcuAddressExtension((Long) visitSingleOpt(sn.ecu_address_extension_exp()));
		a.setExtendedLimits((ExtendedLimits) visitSingleOpt(sn.extended_limits_exp()));
		a.setFormat((String) visitSingleOpt(sn.format_exp()));
		a.setFunctions((FunctionList) visitSingleOpt(sn.function_list_block()));
		a.setGuardRails(visitSingleOptBool(sn.guard_rails_exp()));
		a.setIfData(visitMultipleOpt(sn.if_data_block(), IfData.class));
		a.setMonotony((Monotony) visitSingleOpt(sn.monotony_exp()));
		a.setPhysUnit((String) visitSingleOpt(sn.phys_unit_exp()));
		a.setReadOnly(visitSingleOptBool(sn.read_only_exp()));
		a.setMemorySegment((String) visitSingleOpt(sn.ref_memory_segment_exp()));
		a.setStepSize((Double) visitSingleOpt(sn.step_size_exp()));
		a.setSymbolLink((String) visitSingleOpt(sn.symbol_link_exp()));

		return a;
	}

	@Override
	public Object visitFunction_block(Function_blockContext ctx) {
		Function f = new Function();

		f.setName(ctx.function_exp().Name.getText());
		f.setLongIdentifier(visitString(ctx.function_exp().LongIdentifier));

		Function_sub_nodesContext sn = ctx.function_sub_nodes();
		f.setAnnotations(visitMultipleOpt(sn.annotation_block(), Annotation.class));
		f.setDefCharacteristics((IdentReferenceList) visitSingleOpt(sn.def_characteristic_block()));
		f.setFunctionVersion((String) visitSingleOpt(sn.function_version_exp()));
		f.setIfDatas(visitMultipleOpt(sn.if_data_block(), IfData.class));
		f.setInMeasurments((IdentReferenceList) visitSingleOpt(sn.in_measurement_block()));
		f.setLocMeasurments((IdentReferenceList) visitSingleOpt(sn.loc_measurement_block()));
		f.setOutMeasurments((IdentReferenceList) visitSingleOpt(sn.out_measurement_block()));
		f.setRefCharacteristics((IdentReferenceList) visitSingleOpt(sn.ref_characteristic_block()));
		f.setSubFunctions((IdentReferenceList) visitSingleOpt(sn.sub_function_block()));

		return f;
	}

	@Override
	public Object visitIn_measurement_block(In_measurement_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitLoc_measurement_block(Loc_measurement_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitOut_measurement_block(Out_measurement_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitSub_function_block(Sub_function_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitDef_characteristic_block(Def_characteristic_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitGroup_block(Group_blockContext ctx) {
		Group g = new Group();

		g.setGroupName(ctx.group_exp().GroupName.getText());
		g.setLongIdentifier(visitString(ctx.group_exp().GroupLongIdentifier));

		Group_sub_nodesContext sn = ctx.group_sub_nodes();
		g.setAnnotations(visitMultipleOpt(sn.annotation_block(), Annotation.class));
		g.setFunctionList((FunctionList) visitSingleOpt(sn.function_list_block()));
		g.setIfDatas(visitMultipleOpt(sn.if_data_block(), IfData.class));
		g.setRefCharacteristics((IdentReferenceList) visitSingleOpt(sn.ref_characteristic_block()));
		g.setRefMeasurements((IdentReferenceList) visitSingleOpt(sn.ref_measurement_block()));
		g.setRoot(visitSingleOptBool(sn.root_exp()));
		g.setSubGroups((IdentReferenceList) visitSingleOpt(sn.sub_group_block()));

		return g;
	}

	@Override
	public Object visitSub_group_block(Sub_group_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitRef_characteristic_block(Ref_characteristic_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitRef_measurement_block(Ref_measurement_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	private IdentReferenceList visitIdentifierTokenList(List<Token> idents) {
		IdentReferenceList identLst = new IdentReferenceList();
		for (Token i : idents) {
			identLst.add(i.getText());
		}
		return identLst;
	}

	@Override
	public Object visitRoot_exp(Root_expContext ctx) {
		if (ctx.ROOT() != null) {
			return true;
		}

		return false;
	}

	@Override
	public Object visitFrame_block(Frame_blockContext ctx) {
		Frame f = new Frame();

		Frame_expContext exp = ctx.frame_exp();
		f.setName(exp.Name.getText());
		f.setLongIdentifier(visitString(exp.LongIdentifier));
		f.setScalingUnit((Long) visit(exp.ScalingUnit));
		f.setRate((Long) visit(exp.Rate));

		Frame_sub_nodesContext sn = ctx.frame_sub_nodes();
		f.setFrameMeasurements((IdentReferenceList) visitSingleOpt(sn.frame_measurement_exp()));
		f.setIfDatas(visitMultipleOpt(sn.if_data_block(), IfData.class));

		return f;
	}

	@Override
	public Object visitFrame_measurement_exp(Frame_measurement_expContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitUnit_block(Unit_blockContext ctx) {
		Unit u = new Unit();

		Unit_expContext exp = ctx.unit_exp();
		u.setName(exp.Name.getText());
		u.setLongIdentifier(visitString(exp.LongIdentifier));
		u.setDisplay(visitString(exp.Display));
		u.setType((Unit.Type) visit(exp.unit_type_enum()));

		Unit_sub_nodesContext sn = ctx.unit_sub_nodes();
		u.setUnit_ref((String) visitSingleOpt(sn.ref_unit_exp()));
		u.setSiExponents((SiExponents) visitSingleOpt(sn.si_exponents_exp()));
		u.setUnitConversion((UnitConversion) visitSingleOpt(sn.unit_conversion_exp()));

		return u;
	}

	@Override
	public Object visitSi_exponents_exp(Si_exponents_expContext ctx) {
		SiExponents s = new SiExponents();

		s.setLength((Long) visit(ctx.Length));
		s.setMass((Long) visit(ctx.Mass));
		s.setTime((Long) visit(ctx.Time));
		s.setElectricCurrent((Long) visit(ctx.ElectricCurrent));
		s.setTemperature((Long) visit(ctx.Temperature));
		s.setAmountOfSubstance((Long) visit(ctx.AmountOfSubstance));
		s.setLuminousIntensity((Long) visit(ctx.LuminousIntensity));

		return s;
	}

	@Override
	public Object visitUnit_conversion_exp(Unit_conversion_expContext ctx) {
		UnitConversion u = new UnitConversion();

		u.setGradient((Double) visit(ctx.Gradient));
		u.setOffset((Double) visit(ctx.Offset));

		return u;
	}

	@Override
	public Object visitUnit_type_enum(Unit_type_enumContext ctx) {
		if (ctx.DERIVED() != null) {
			return Unit.Type.DERIVED;
		}

		if (ctx.EXTENDED_SI() != null) {
			return Unit.Type.EXTENDED_SI;
		}

		logInvalidEnum(ctx, Unit.Type.values());
		return null;
	}

	@Override
	public Object visitUser_rights_block(User_rights_blockContext ctx) {
		UserRights u = new UserRights();

		u.setUserLevelId(ctx.user_rights_exp().UserLevelId.getText());
		u.setReadOnly(visitSingleOptBool(ctx.user_rights_sub_nodes().read_only_exp()));
		u.setGroups(visitMultipleOpt(ctx.user_rights_sub_nodes().ref_group_block(), IdentReferenceList.class));

		return u;
	}

	@Override
	public Object visitRef_group_block(Ref_group_blockContext ctx) {
		return visitIdentifierTokenList(ctx.Identifier);
	}

	@Override
	public Object visitVariant_coding_block(Variant_coding_blockContext ctx) {
		VariantCoding v = new VariantCoding();

		Variant_coding_sub_nodesContext sn = ctx.variant_coding_sub_nodes();
		v.setVarCharacteristics(visitMultipleOpt(sn.var_characteristic_block(), VarCharacteristic.class));
		v.setVarCriterion(visitMultipleOpt(sn.var_criterion_block(), VarCriterion.class));
		v.setVarForbiddenComb(visitMultipleOpt(sn.var_forbidden_comb_block(), VarForbiddenComb.class));
		v.setVarNaming((VarNaming) visitSingleOpt(sn.var_naming_exp()));
		v.setVarSeparator((String) visitSingleOpt(sn.var_separator_exp()));
		return v;
	}

	@Override
	public Object visitVar_naming_exp(Var_naming_expContext ctx) {
		if (ctx.NUMERIC() != null) {
			return VarNaming.NUMERIC;
		}

		logInvalidEnum(ctx, VarNaming.values());
		return null;
	}

	@Override
	public Object visitVar_separator_exp(Var_separator_expContext ctx) {
		return visit(ctx.Separator);
	}

	@Override
	public Object visitVar_characteristic_block(Var_characteristic_blockContext ctx) {
		VarCharacteristic v = new VarCharacteristic();

		v.setName(ctx.var_characteristic_exp().Name.getText());
		v.setCriterions(visitIdentifierTokenList(ctx.var_characteristic_exp().CriterionNames));
		v.setAddresses((VarAddress) visitSingleOpt(ctx.var_characteristic_sub_nodes().var_address_block()));

		return v;
	}

	@Override
	public Object visitVar_criterion_block(Var_criterion_blockContext ctx) {
		VarCriterion v = new VarCriterion();

		v.setName(ctx.var_criterion_exp().Name.getText());
		v.setLongIdentifier(visitString(ctx.var_criterion_exp().LongIdentifier));
		v.setValues(visitIdentifierTokenList(ctx.var_criterion_exp().Values));

		v.setMeasurement((String) visitSingleOpt(ctx.var_criterion_sub_nodes().var_measurement_exp()));
		v.setSelectionCharacteristic(
				(String) visitSingleOpt(ctx.var_criterion_sub_nodes().var_selection_characteristic_exp()));

		return v;
	}

	@Override
	public Object visitVar_measurement_exp(Var_measurement_expContext ctx) {
		return ctx.Name.getText();
	}

	@Override
	public Object visitVar_selection_characteristic_exp(Var_selection_characteristic_expContext ctx) {
		return ctx.Name.getText();
	}

	@Override
	public Object visitVar_address_block(Var_address_blockContext ctx) {
		VarAddress v = new VarAddress();

		for (Int_valueContext c : ctx.Addresses) {
			v.add((Long) visit(c));
		}

		return v;
	}

	@Override
	public Object visitVar_forbidden_comb_block(Var_forbidden_comb_blockContext ctx) {
		VarForbiddenComb vfc = new VarForbiddenComb();

		ArrayList<CriterionTuple> tuples = new ArrayList<CriterionTuple>();
		for (Criterion_tupleContext t : ctx.tuples) {
			tuples.add((CriterionTuple) visit(t));
		}
		vfc.setTuples(tuples);

		return vfc;
	}

	@Override
	public Object visitCriterion_tuple(Criterion_tupleContext ctx) {
		CriterionTuple criterionTuple = new CriterionTuple();

		criterionTuple.setName(ctx.CriterionName.getText());
		criterionTuple.setValue(ctx.CriterionValue.getText());

		return criterionTuple;
	}

	private long[] visitOffset5IntValues(List<Int_valueContext> offsets) {
		List<Long> offsetValues = visitMultipleOpt(offsets, Long.class);

		if (offsets.size() != 5 || offsetValues.size() != 5) {
			if (offsets.size() > 0) {
				log.log(offsets.get(0).getStart().getLine(), offsets.get(0).getStart().getCharPositionInLine(),
						"Invalid number of offset values specified!");
			} else {
				return null;
			}
		}

		long[] offsetPrimitiveValues = new long[5];
		for (int i = 0; i < offsetValues.size() && i < offsetPrimitiveValues.length; i++) {
			offsetPrimitiveValues[i] = offsetValues.get(i);
		}
		return offsetPrimitiveValues;
	}

	private Object visitOpt(ParseTree tree) {
		if (tree == null) {
			return null;
		}

		return visit(tree);
	}

	private <T, U extends ParserRuleContext> List<T> visitMultipleOpt(List<U> rules, Class<T> c) {
		List<T> output = new ArrayList<T>();
		for (U ctx : rules) {
			Object o = visitOpt(ctx);
			if (o != null) {
				if (c.isInstance(o)) {
					output.add(c.cast(o));
				} else {
					log.log(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
							"Invalid sub type: " + ctx.getText());
				}
			} else {
				log.log(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), "Cannot parse sub node!");
			}
		}
		return output;
	}

	private <T extends ParserRuleContext> boolean visitSingleOptBool(List<T> rules) {
		Object o = visitSingleOpt(rules);

		if (o == null)
			return false;

		return (boolean) o;
	}

	private <T extends ParserRuleContext> Object visitSingleOpt(List<T> rules) {
		if (rules.size() > 1) {
			log.log(rules.get(0).getStart().getLine(), rules.get(0).getStart().getCharPositionInLine(),
					"More than one header is specified!");
		}

		if (rules.size() <= 0) {
			return null;
		}

		return visitOpt(rules.get(0));
	}

	private String visitString(ParserRuleContext t) {
		if (t instanceof String_expContext)
			return (String) visit(t);

		log.log(t.getStart().getLine(), t.getStart().getCharPositionInLine(), "Exptected a string: " + t.getText());
		return null;
	}

	private String extractString(Token stringToken) {
		String stringVal = stringToken.getText();

		if (stringToken.getType() != a2lLexer.STRING) {
			log.log(stringToken.getLine(), stringToken.getCharPositionInLine(),
					"Exptected a string: " + stringToken.getText());
			return stringVal;
		}

		try {
			return toJavaString(stringVal);
		} catch (Exception e) {
			log.log(stringToken.getLine(), stringToken.getCharPositionInLine(),
					"Cannot match string: " + stringToken.getText());
		}
		
		return stringVal;
	}
	
	public static String toJavaString(String a2lString) throws InvalidParameterException {
		if (a2lString.startsWith("\"") && a2lString.endsWith("\"")) {
			// remove trailing "
			a2lString = a2lString.substring(1, a2lString.length() - 1);

			return replaceEscapedCharacters(a2lString);
		}
		
		throw new InvalidParameterException("The given string is not enclosed by \": " + a2lString);
	}

	private static String replaceEscapedCharacters(String string) {
		String[] splittedDoubleBackslash = string.split("\\\\\\\\", -1);

		for (int i = 0; i < splittedDoubleBackslash.length; i++) {
			String stringVal = splittedDoubleBackslash[i];
			// replace escaped "
			stringVal = stringVal.replaceAll("\\\\\\\"|\"\"", "\"");

			// replace escaped '
			stringVal = stringVal.replaceAll("\\\\'", "'");

			// replace \n
			stringVal = stringVal.replaceAll("\\\\n", "\n");

			// replace \r
			stringVal = stringVal.replaceAll("\\\\r", "\r");

			// replace \t
			stringVal = stringVal.replaceAll("\\\\t", "\t");

			splittedDoubleBackslash[i] = stringVal;
		}

		return String.join("\\", splittedDoubleBackslash);
	}
}
