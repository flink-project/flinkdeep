package fLink.subDevices;

import fLink.core.FLinkDefinitions;
import fLink.core.FLinkSubDevice;

public class FLinkADC implements FLinkDefinitions{
	private static int RESOLUTION_ADDRESS = 0;
	private static int VALUE_0_ADDRESS = RESOLUTION_ADDRESS + REGISTER_WITH;
	public FLinkSubDevice dev;
	private int resolution;
	private int bit_mask;
	
	public FLinkADC(FLinkSubDevice dev){
		this.dev = dev;
		//cache resolution
		this.resolution = dev.read(RESOLUTION_ADDRESS);
		//create bitmask
		for(int i = 0;i<resolution;i++){
			bit_mask = bit_mask | (0x1<<i);
		}
	}
	
	public int getResolution(){
		return resolution;
	}
	
	public int getValue(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			return (dev.read(VALUE_0_ADDRESS+chanel*REGISTER_WITH)&bit_mask);
		}else{
			return 0;
		}
	}
	
}
