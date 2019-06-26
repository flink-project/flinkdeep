package fLink.subDevices;

import fLink.core.FLinkDefinitions;
import fLink.core.FLinkSubDevice;

public class FLinkPPWA implements FLinkDefinitions{
	private static int BASE_CLOCK_ADDRESS = 0;
	private static int PERIOD_0_ADDRESS = BASE_CLOCK_ADDRESS + REGISTER_WIDTH;
	public FLinkSubDevice dev;
	private int highTime0Address;
	
	public FLinkPPWA(FLinkSubDevice dev){
		this.dev = dev;
		this.highTime0Address = PERIOD_0_ADDRESS + dev.getNumberOfChanels()*REGISTER_WIDTH;
	}
	
	public int getBaseClock(){
		return dev.read(BASE_CLOCK_ADDRESS);
	}
	
	public int getPeriodTime(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			return dev.read(PERIOD_0_ADDRESS+chanel*REGISTER_WIDTH);
		}else{
			return 0;
		}
	}
	
	public int getHighTime(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			return dev.read(highTime0Address + chanel*REGISTER_WIDTH);
		}else{
			return 0;
		}
	}
}
