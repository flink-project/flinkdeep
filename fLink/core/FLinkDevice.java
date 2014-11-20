package fLink.core;

public class FLinkDevice implements FLinkDefinitions{
	private FLinkBusInterface busInterface;
	private FLinkSubDevice list[];
	
	public FLinkDevice(FLinkBusInterface busInterface){
		this.busInterface = busInterface;
		findSubdevices();
	}
	
	public int getNumberOfSubDevices(){
		return list.length;
	}
	
	public FLinkSubDevice[] getDeviceList(){
		return list;
	}

	public FLinkSubDevice getSubdeviceByNr(int nr){
		if(nr< list.length){
			return list[nr];
		}else{
			return null;
		}
	}
	
	public FLinkSubDevice getSubdeviceByType(int type, int nr){
		int subDevNr = 0;
		for(int i = 0; i <list.length;i++){
			if(list[i].getInterfaceType() == type){
				if(subDevNr == nr){
					return list[i];
				}
				subDevNr++;
			}
		}
		return null;
	}
	
	public FLinkSubDevice getSubdeviceByUniceID(int id){
		for(int i = 0; i <list.length;i++){
			if(list[i].getUniceID() == id){
				return list[i];
			}
		}
		return null;
	}
	
	
	public FLinkSubDevice getSubdeviceByType(int type){
		return getSubdeviceByType(type,0);
	}
	
	
	private void findSubdevices(){
		int memptr = 0;
		int numberOfSubdevices = 0;
		int deciceLength = 0;
		FLinkSubDevice firstDevice = new FLinkSubDevice();
		FLinkSubDevice actualDevice = firstDevice;
		if(busInterface.hasInfoDev()){
			deciceLength = busInterface.read(memptr + TOTAL_HEADER_SIZE); //device size register
		}else{
			deciceLength = busInterface.getMemoryLength();
		}
		
		
		
		
		while (memptr < deciceLength){
			numberOfSubdevices++;
			actualDevice.setBaseAddress(memptr);
			actualDevice.setBusInterface(busInterface);
			
			//id register
			int reg = busInterface.read(memptr + TYPE_OFFSET);
			actualDevice.setInterfaceType(reg >> 16);
			actualDevice.setSupType((reg >> 8) & 0xFF);
			actualDevice.setVersion(reg & 0xFF);
			//memory size register
			actualDevice.setMemSize(busInterface.read(memptr + SIZE_OFFSET));
			//number of channels register
			actualDevice.setChanels(busInterface.read(memptr + CHANEL_OFFSET));
			//unice id
			actualDevice.setUniceID(busInterface.read(memptr + UNIC_ID_OFFSET));
			
			//address of next subdevice
			memptr = memptr + actualDevice.getMemSize();
			
			//create new device
			if(memptr < deciceLength){
				FLinkSubDevice nextDevice = new FLinkSubDevice();
				actualDevice.setNextSubdevice(nextDevice);
				actualDevice = nextDevice;
			}
		}
		//create array for easier access
		this.list = new FLinkSubDevice[numberOfSubdevices];
		actualDevice = firstDevice;
		for(int i = 0 ; i<numberOfSubdevices;i++){
			this.list[i] = actualDevice;
			actualDevice = actualDevice.getNext();
		}
	}
	
	public static String idToCharArray(int id){
		switch(id){
		case PWM_INTERFACE_ID:
			return "PWM";
		case GPIO_INTERFACE_ID:
			return "GPIO";
		case COUNTER_INTERFACE_ID:
			return "FQD";
		case WD_INTERFACE_ID:
			return "WD";
		case PPWA_INTERFACE_ID:
			return "PPWA";
		case ANALOG_INPUT_INTERFACE_ID:
			return "ANALOG INPUT";
		case ANALOG_OUTPUT_INTERFACE_ID:
			return "ANALOG OUTPUT";
		case INFO_DEVICE_ID:
			return "INFO DEVICE";
		default:
			return Integer.toString(id);
		}
		
	}
	
	
	
	
}
