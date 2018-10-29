package cn.umbrella.wechat.bean;

/**
 * 菜单类（包含父按钮）
 * 
 * @author chu.feifei
 *
 */
public class Menu {
	private Button[] button;

	/**
	 * @return the button
	 */
	public Button[] getButton() {
		return button;
	}

	/**
	 * @param button
	 *            the button to set
	 */
	public void setButton(Button[] button) {
		this.button = button;
	}

}
