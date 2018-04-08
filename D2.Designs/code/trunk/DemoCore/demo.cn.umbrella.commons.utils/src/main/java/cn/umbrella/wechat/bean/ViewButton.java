/**
 * 
 */
package cn.umbrella.wechat.bean;

/**
 * view菜单
 * 
 * @author chu.feifei
 *
 */
public class ViewButton extends Button {
	private String type;
	private String EventKey;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return EventKey;
	}

	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}


}
