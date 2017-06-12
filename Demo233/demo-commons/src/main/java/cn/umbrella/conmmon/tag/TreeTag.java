package cn.umbrella.conmmon.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.jsp.StrutsBodyTagSupport;

public class TreeTag extends StrutsBodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String treeID = ""; // 树的ID

	private String xmlUrl = ""; // Xml地址

	private Integer treeFile = 0; // 是否包含JAVASCRIPT 默认为包含 0 包含 1不包含

	public Integer getTreeFile() {
		return treeFile;
	}

	public void setTreeFile(Integer treeFile) {
		this.treeFile = treeFile;
	}

	public String getTreeID() {
		return treeID;
	}

	public void setTreeID(String treeID) {
		this.treeID = treeID;
	}

	public String getXmlUrl() {
		return xmlUrl;
	}

	public void setXmlUrl(String xmlUrl) {
		this.xmlUrl = xmlUrl;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		String webpath = ServletActionContext.getRequest().getContextPath()
				+ "/javascript/dhtmlxTree";
		StringBuffer sb = new StringBuffer();
		if (treeFile == 0) {
			sb.append("<link rel=\"STYLESHEET\" type=\"text/css\" href=\""
					+ webpath + "/dhtmlxtree.css\">");
			sb.append("<script  src=\"" + webpath
					+ "/dhtmlxcommon.js\"></script>");
			sb.append("<script  src=\"" + webpath
					+ "/dhtmlxtree.js\"></script>");
		}
		sb.append("<script>");
		sb.append("tree=new dhtmlXTreeObject(\"" + treeID
				+ "\",\"100%\",\"100%\",0);");
		sb.append("tree.setSkin('dhx_skyblue');");
		sb.append("tree.setImagePath(\"" + webpath + "/imgs/\");");
		sb.append("tree.setStdImages('open_sub.gif','bigopen.gif','bigclose.gif');");
		sb.append("tree.setOnClickHandler(tonclick);");
		sb.append("tree.loadXML(\""
				+ ServletActionContext.getRequest().getContextPath() + xmlUrl
				+ "\");");
		sb.append("</script>");
		try {
			pageContext.getOut().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
