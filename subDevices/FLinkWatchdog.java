package fLink.subDevices;

import fLink.core.FLinkDefinitions;
import fLink.core.FLinkSubDevice;

public class FLinkWatchdog implements FLinkDefinitions{
	private static int BASE_CLOCK_ADDRESS = 0;
	private static int COUNTER_0_ADDRESS = BASE_CLOCK_ADDRESS + REGISTER_WITH;
	private static int RESET_BIT_MASK = 0x1;
	private static int EDGE_POL_BIT_MASK = 0x2;
	public FLinkSubDevice dev;
	private int config0Address;
	
	public FLinkWatchdog(FLinkSubDevice dev){
		this.dev = dev;
		this.config0Address = COUNTER_0_ADDRESS + dev.getNumberOfChanels()*REGISTER_WITH;
	}
	
	public int getBaseClock(){
		return dev.read(BASE_CLOCK_ADDRESS);
	}
	
	public int getCounterValue(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			return dev.read(COUNTER_0_ADDRESS+chanel*REGISTER_WITH);
		}else{
			return 0;
		}
	}
	public void setCounterValue(int chanel,int value){
		if(chanel<dev.getNumberOfChanels()){
			dev.write(COUNTER_0_ADDRESS+chanel*REGISTER_WITH,value);
		}
	}
	
	public void resetChanel(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			int regValue = dev.read(config0Address+chanel*REGISTER_WITH);
			regValue = regValue | RESET_BIT_MASK;
			dev.write(config0Address+chanel*REGISTER_WITH,regValue);
		}
	}
	
	public void setRisingEdge(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			int regValue = dev.read(config0Address+chanel*REGISTER_WITH);
			regValue = regValue & ~EDGE_POL_BIT_MASK;
			dev.write(config0Address+chanel*REGISTER_WITH,regValue);
		}
	}
	
	public void setFallingEdge(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			int regValue = dev.read(config0Address+chanel*REGISTER_WITH);
			regValue = regValue | EDGE_POL_BIT_MASK;
			dev.write(config0Address+chanel*REGISTER_WITH,regValue);
		}
	}
	
}
