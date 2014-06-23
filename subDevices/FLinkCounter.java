package fLink.subDevices;

import fLink.core.FLinkDefinitions;
import fLink.core.FLinkSubDevice;

public class FLinkCounter implements FLinkDefinitions{

	public FLinkSubDevice dev;
	private static final int COUNT_0_ADRESS = 0;
	
	public FLinkCounter(FLinkSubDevice dev){
		this.dev = dev;
		
	}
	
	public int getCountValue(int chanel){
		return dev.read(COUNT_0_ADRESS + chanel*REGISTER_WITH);
	}
	
	public void reset(){
		int confReg = dev.getModConfReg();
		confReg = confReg | 0x1;
		dev.setModConfReg(confReg);
	}
}
