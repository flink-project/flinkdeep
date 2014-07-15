package fLink.subDevices;

import fLink.core.FLinkDefinitions;
import fLink.core.FLinkSubDevice;

public class FLinkPWM implements FLinkDefinitions{
	private static int BASE_CLOCK_ADDRESS = 0;
	private static int PERIOD_0_ADDRESS = BASE_CLOCK_ADDRESS + REGISTER_WITH;
	public FLinkSubDevice dev;
	private int highTime0Address;
	
	public FLinkPWM(FLinkSubDevice dev){
		this.dev = dev;
		this.highTime0Address = PERIOD_0_ADDRESS + dev.getNumberOfChanels()*REGISTER_WITH;
	}
	
	public int getBaseClock(){
		return dev.read(BASE_CLOCK_ADDRESS);
	}
	
	public int getPeriodTime(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			return dev.read(PERIOD_0_ADDRESS+chanel*REGISTER_WITH);
		}else{
			return 0;
		}
	}
	
	public int getHighTime(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			return dev.read(highTime0Address + chanel*REGISTER_WITH);
		}else{
			return 0;
		}
	}
	
	public void setPeriodTime(int chanel,int period){
		if(chanel<dev.getNumberOfChanels()){
			dev.write(PERIOD_0_ADDRESS+chanel*REGISTER_WITH,period);
		}
	}
	
	public void setFrequency(int chanel, int frequency){
		this.setPeriodTime(chanel,this.getBaseClock()/frequency);
	}
	
	public void setHighTime(int chanel, int period){
		if(chanel<dev.getNumberOfChanels()){
			dev.write(highTime0Address + chanel*REGISTER_WITH,period);
		}
	}
	public void setRatio(int chanel, float ratio){
		this.setHighTime(chanel, (int) (this.getPeriodTime(chanel)*ratio));
	}
}
