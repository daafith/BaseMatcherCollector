package speedcamera;

/**
 * 
 * @author davidbaak <br />
 * Simple parent class proving the use of <? super T> in the BaseMatcherCollector
 */
public class MeasuringDevice {
	
	private String type;

	public String getDeviceType() {
		return type;
	}
	
	protected void setDeviceType(String type) {
		this.type = type;
	}
}
