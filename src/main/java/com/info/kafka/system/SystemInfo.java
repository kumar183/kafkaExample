package com.info.kafka.system;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SystemInfo {

	private Sigar sigar1 = new Sigar();

	public SystemInfo() {
		super();
	}

	public long getSystemTotalMemory() throws SigarException {

		Mem mem = sigar1.getMem();
		long systemTotal = mem.getTotal();
		//double systemLoadInPercentage = (systemUsed * 0.1 / systemTotal) * 1000;
		return systemTotal;
	}
	
	public long getSystemUsedMemory() throws SigarException {

		Mem mem = sigar1.getMem();
		long systemUsed = mem.getUsed();
		//double systemLoadInPercentage = (systemUsed * 0.1 / systemTotal) * 1000;
		return systemUsed;
	}

	public double getCPUIdlePerc() {

		try {
			CpuPerc cpuPerc = sigar1.getCpuPerc();
			String cpuIdlePerc = CpuPerc.format(cpuPerc.getIdle());
			return Double.parseDouble(cpuIdlePerc.replace("%", ""));
		} catch (SigarException e) {
		}
		return 0;
	}
}
