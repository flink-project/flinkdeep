package fLink.core;

public interface FLinkDefinitions {

	public static final int REGISTER_WITH		=	4;		// byte
	public static final int REGISTER_WITH_BIT	=	REGISTER_WITH*8;	
	public static final int HEADER_SIZE		=	16;		// byte
	public static final int SUBHEADER_SIZE	=	16;		// byte
	public static final int TOTAL_HEADER_SIZE = HEADER_SIZE + SUBHEADER_SIZE;
	
	
	public static final int TYPE_OFFSET = 0x0;
	public static final int SIZE_OFFSET = 0x4;
	public static final int CHANEL_OFFSET = 0x8;
	public static final int UNIC_ID_OFFSET = 0xC;
	public static final int MOD_STATUS_OFFSET = 0x10;
	public static final int MOD_CONF_OFFSET = 0x14;
	
	
	public static final int INFO_DEVICE_ID				=	0x0;	
	public static final int PWM_INTERFACE_ID			=	0xC;	
	public static final int GPIO_INTERFACE_ID			=	0x5;
	public static final int COUNTER_INTERFACE_ID		=	0x6;	
	public static final int WD_INTERFACE_ID				=	0x10;	
	public static final int PPWA_INTERFACE_ID			=	0xD;	
	public static final int ANALOG_INPUT_INTERFACE_ID 	= 	0x1;
	public static final int ANALOG_OUTPUT_INTERFACE_ID 	= 	0x2;
	
	public static final int INTERFACE_TYPE_MASK = 0xFFFF;
	public static final int INFO_DEVICE_SIZE = 0x80;


}
