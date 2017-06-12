package cn.umbrella.conmmon.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;



/** 
* 自定义分页标签  
* 使用方式 <my:pager pageSize="10"  
*                   pageNo="1"  
*                   recordCount="100" 
*                   url="index.jsp" /> 
*  
* @author 
*  
*/ 

public class PagerTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageSize = 20;  // 每页要显示的记录数
	private int pageNo = 1; // 页号
	private int recordCount; // 总记录数
	private String url; // 目的地URL

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public String getUrl() {
		return url;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// 主要的逻辑
	@Override
	public int doStartTag() throws JspException {
		if (recordCount == 0) {
			return super.doStartTag();
		}

		// 总页数
		int pageCount = (recordCount + pageSize - 1) / pageSize;

		// 页号越界处理
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("<form name='pageController' id='pageController' action='' method='post'>\r\n");
		sb.append("<div style='text-align:right;padding-right:18px;color:#666666;font-weight:bold;'>");
		sb.append("<input type='hidden' id='pageNo' name='pageNo' value='" + pageNo + "' />\r\n");

		// ------------------------------------ 获取所有請求中的参数
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Enumeration<String> enumeration = request.getParameterNames();
		String name = null;
		String value = null;
		// 把请求中的所有参数当作隐藏表单域在页面中写出)
		while (enumeration.hasMoreElements()) {
			name = enumeration.nextElement();
			value = request.getParameter(name);

			// 去除页号
			if (name.equals("pageNo")) {
				if (null != value && !"".equals(value)) {
					pageNo = Integer.parseInt(value);
				}
				continue;
			}
			sb.append("<input type='hidden' name='").append(name).append("' value='").append(value).append("'/>\r\n");
		}
		// ----------------------------------------------------

		sb.append(" 总共有<font class='font_red'  id='pageCountLabel'>" + pageCount + "</font>页,");
		sb.append("当前是第<font class='font_red'  id='pageCountLabel'>" + pageNo + "</font>页 \r\n");

		if (pageNo == 1) {
			sb.append("<input type='button' name='first_button' value='首頁' id='firstPage' class='btn_mouseout' disabled>");
			sb.append(" ");
			sb.append("<input type='button' name='pre_button' value='上一頁' id='prePage' class='btn_mouseout' disabled>\r\n");

		} else {
			sb.append("<input type='button' name='first_button' value='首頁' id='firstPage' class='btn_mouseout'  onclick='javascript:turnOverPage(1)' >\r\n");
			sb.append(" ");
			sb.append("<input type='button' name='pre_button' value='上一頁' id='prePage' class='btn_mouseout'  onclick='javascript:turnOverPage(").append(pageNo-1).append(")' >\r\n");
		}

		sb.append(" ");
		if (pageNo == pageCount) {
			sb.append("<input type='button' name='next_button' value='下一页' id='nextPage' class='btn_mouseout' disabled>");
			sb.append(" ");
			sb.append("<input type='button' name='last_button' value='尾页' id='lastPage' class='btn_mouseout' disabled>\r\n");

		} else {
			sb.append("<input type='button' name='next_button' value='下一頁' id='nextPage' class='btn_mouseout'  onclick='javascript:turnOverPage(").append(pageNo+1).append(")' >\r\n");
			sb.append(" ");
			sb.append("<input type='button' name='last_button' value='尾頁' id='lastPage' class='btn_mouseout'  onclick='javascript:turnOverPage(").append(pageCount).append(")' >\r\n");
		}

		sb.append(" 跳到<select onChange='turnOverPage(this.value)'>\r\n");
		for (int i = 1; i <= pageCount; i++) {
			if (i == pageNo) {
				sb.append("  <option value='").append(i).append("' selected='selected'>第").append(i).append(
						"页</option>\r\n");
			} else {
				sb.append("  <option value='").append(i).append("'>第").append(i).append("页</option>\r\n");
			}
		}
		sb.append("</select>\r\n");
		sb.append("</div> \r\n");
		sb.append("</form>\r\n");

		// 生成提交表单的JS
		sb.append("<script language='javascript'>\r\n");
		sb.append("  //翻页函数\t\n");
		sb.append("  function turnOverPage(no){\r\n");
		sb.append("    var form = document.pageController;\r\n");
		sb.append("    //页号越界处理\r\n");
		sb.append("    if(no").append(">").append(pageCount).append(") {\r\n");
		sb.append("        no=").append(pageCount).append(";\r\n");
		sb.append("    }\r\n");
		sb.append("    if(no").append("< 1){\r\n");
		sb.append("        no=1;\r\n");
		sb.append("    }\r\n");
		sb.append("    form.").append("pageNo").append(".value=no;\r\n");
		sb.append("    form.action='").append(url).append("';\r\n");
		sb.append("    form.submit();\r\n");
		sb.append("  }\r\n");
		sb.append("</script>\r\n");

		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doStartTag();
	}

}
