package cn.umbrella.commons.base.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.umbrella.commons.base.model.BaseForm;
import cn.umbrella.commons.base.model.BaseModel;
import cn.umbrella.commons.base.model.BaseQuery;
import cn.umbrella.commons.base.service.IBaseService;
import cn.umbrella.commons.bean.ResponseData;
//import cn.com.guangduo.common.bean.ResponseData;
import cn.umbrella.commons.enums.MethodType;
//import cn.umbrella.commons.util.usual.ValidateUtil;
import cn.umbrella.commons.validate.ValidateUtil;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @Description: BaseController定义一些基本的action
 * @ClassName: BaseController  
 * @author zhou.xy
 * @date 2016年1月4日 下午1:31:13  
 *  
 * @param <MODEL> BEAN对象
 * @param <FORM>  表单对象
 * @param <KEY_TYPE> 表主键类型
 */
public abstract class BaseController<MODEL extends BaseModel<KEY_TYPE>, FORM extends BaseForm<KEY_TYPE>, KEY_TYPE extends Serializable> {
	@Autowired
	IBaseService<MODEL, KEY_TYPE> baseService;

	public IBaseService<MODEL, KEY_TYPE> getBaseService() {
		return baseService;
	}

	/**
	 * 
	 * @Description: 列表页面 
	 * @Title: list 
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/list");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description: 查询分页 
	 * @Title: doList 
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="doList.json", method={RequestMethod.POST})
	@ResponseBody
	public PageInfo<MODEL> doList(HttpServletRequest request) {
		BaseQuery query = EncapsulateQueryCondition(request);
		doQueryBefore(query);
		PageInfo<MODEL> queryPage = baseService.queryPage(query);
		doQueryAfter(query,queryPage);
		return queryPage;
	}

	
	/**
	 * 查询之后的处理
	 * @param query
	 * @param queryPager
	 * */
	public void doQueryAfter(BaseQuery query,PageInfo<MODEL> queryPage) {
	}
	public void doQueryAfter(FORM form,ModelAndView modeAndView) {
	}
	/**
	 * 查询之前的处理
	 * @param query
	 * **/
	public void doQueryBefore(BaseQuery query) {}

	/**
	 * 
	 * @Description: 新增  
	 * @Title: add 
	 * @param form
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "add")
	public ModelAndView add(FORM form) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
		FORM f = null;
		Class<KEY_TYPE> keyType = null;
		try {
			Class<FORM> genericType2 = getGenericType(1);
			f = genericType2.newInstance();
			
			keyType = getGenericType(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(keyType.isAssignableFrom(Integer.class)) {// 当主键为自增时不设置UUID
			
		} else {
			form.setUuid((KEY_TYPE) UUID.randomUUID().toString().replace("-", ""));
		}
		
		modelAndView.addObject(getLowerClassName(f.getClass()), form);
		modelAndView.addObject("validate", ValidateUtil.getValidate(f.getClass()));
		expandParam(modelAndView, form);
		expandParam(modelAndView, form, MethodType.ADD.getValue());
		return modelAndView;
	}

	/**
	 * 
	 * @Description: 将类名首字母小写
	 * @Title: getLowerClassName 
	 * @param c
	 * @return String
	 * @throws
	 */
	private String getLowerClassName(Class<?> c) {
		return c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
	}

	/**
	 * 
	 * @Description: 提交新增 
	 * @Title: doAdd 
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 * @return
	 * @throws Exception ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "doAdd", method = { RequestMethod.POST })
	public ModelAndView doAdd(@Valid FORM form, BindingResult result, RedirectAttributes redirectAttributes) {
		doAddBefore(form, result);// 新增校验
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
			modelAndView.addObject(getLowerClassName(form.getClass()), form);
			modelAndView.addObject("validate", ValidateUtil.getValidate(form.getClass()));
			expandParam(modelAndView, form);
			return modelAndView;
		}
		Class<MODEL> modelClass = getGenericType(0);
		MODEL model = null;
		try {
			model = modelClass.newInstance();
			BeanUtils.copyProperties(form, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		baseService.add(model);
		doAddAfter(model);
		doAddAfter(model,form);
		ModelAndView modelAndView = new ModelAndView("redirect:" + getActionPath() + "/list.htm");
		redirectAttributes.addFlashAttribute("msg", "新增成功！");
		return modelAndView;
	}
	/**
	 * 保存之前     
	 *
	 * @Title: doAddBefore 
	 * @param form
	 * @param result 
	 * @return void
	 * @throws
	 */
	protected void doAddBefore(FORM form, BindingResult result) {
	}
	/**
	 * 保存成功之后
	 * @param model
	 */
	protected void doAddAfter(MODEL model,FORM form) {
	}
	protected void doAddAfter(MODEL model) {
	}

	/**
	 * 
	 * @Description: 更新 
	 * @Title: update 
	 * @param id
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="update")
	public ModelAndView update(KEY_TYPE id) {
		MODEL model = baseService.query(id);
		FORM form = null;
		try {
			Class<FORM> genericType = getGenericType(1);
			form = genericType.newInstance();
			BeanUtils.copyProperties(model, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/update");
		doQueryAfter(form,modelAndView);
		modelAndView.addObject(getLowerClassName(form.getClass()), form);
		modelAndView.addObject("validate", ValidateUtil.getValidate(form.getClass()));
		expandParam(modelAndView, form);
		expandParam(modelAndView, form, MethodType.EDIT.getValue());
		return modelAndView;
	}
	
	/**
	 * 
	 * 扩展数据 
	 *  
	 * @Title: expandParam 
	 * @param modelAndView
	 * @param form void
	 * @throws
	 */
	public void expandParam(ModelAndView modelAndView, FORM form) {
		
	}
	
	/**
	 *根据不同操作添加相应的扩展数据 
	 *
	 * @Title: expandParam 
	 * @param modelAndView
	 * @param form
	 * @param enums MethodType
	 * @return void
	 * @throws
	 */
	public void expandParam(ModelAndView modelAndView, FORM form, String methodType) {
		
	}

	/**
	 * 
	 * @Description: 提交更新  
	 * @Title: doUpdate 
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 * @return
	 * @throws Exception ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "doUpdate", method = { RequestMethod.POST })
	public ModelAndView doUpdate(@Valid FORM form, BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
		doUpdateBefore(form, result);// 更新前校验
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(getActionPath() + "/update");
			modelAndView.addObject(getLowerClassName(form.getClass()), form);
			modelAndView.addObject("validate", ValidateUtil.getValidate(form.getClass()));
			return modelAndView;
		}
		Class<MODEL> modelClass = getGenericType(0);
		MODEL model = modelClass.newInstance();
		BeanUtils.copyProperties(form, model);
		baseService.update(model);
		doUpdateAfter(model);
		doAddAfter(model,form);
		ModelAndView modelAndView = new ModelAndView("redirect:" + getActionPath() + "/list.htm");
		redirectAttributes.addFlashAttribute("msg", "更新成功！");
		return modelAndView;
	}
	/**
	 * 修改成功之后
	 * @param model
	 */
	protected void doUpdateAfter(MODEL model) {
	}
	protected void doUpdateAfter(MODEL model,FORM from) {
	}
	/**
	 * 修改之前 
	 *
	 * @Title: doAddBefore 
	 * @param form
	 * @param result 
	 * @return void
	 * @throws
	 */
	protected void doUpdateBefore(FORM form, BindingResult result) {
	}

	/**
	 * 
	 * @Description: 查看详情 
	 * @Title: detail 
	 * @param id
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="detail")
	public ModelAndView detail(KEY_TYPE id) {
		MODEL model = baseService.query(id);
		FORM form = null;
		try {
			Class<FORM> genericType = getGenericType(1);
			form = genericType.newInstance();
			BeanUtils.copyProperties(model, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/detail");
		doQueryAfter(form,modelAndView);
		modelAndView.addObject(getLowerClassName(form.getClass()), form);
		expandParam(modelAndView, form);
		expandParam(modelAndView, form, MethodType.VIEW.getValue());
		return modelAndView;
	}

	/**
	 * 
	 * @Description: 删除 
	 * @Title: doDelete 
	 * @param ids
	 * @return ResponseData
	 * @throws
	 */
	@RequestMapping(value = "doDelete.json")
	@ResponseBody
	public ResponseData doDelete(KEY_TYPE[] ids) {
		int deleteNum = baseService.delete(Arrays.asList(ids));
		ResponseData rd = new ResponseData();
		rd.setMsg("成功删除【" + deleteNum + "】条数据！");
		rd.addData("ids", ids);
		return rd;
	}

	public abstract String getActionPath();// 获取action路径
	
	/**
	 * 
	 * @Description: 封装查询条件 
	 * @Title: EncapsulateQueryCondition 
	 * @param request
	 * @return BaseQuery
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	protected BaseQuery EncapsulateQueryCondition(HttpServletRequest request) {
		BaseQuery query = new BaseQuery();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			if(key.startsWith("arr_")) {
				String[] values = request.getParameterValues(key);
				key = key.split("_|\\[")[1];
				query.getQueryData().put(key, values);
				continue;
			}
			switch (key) {
			case "pageNum":
				query.setPageNum(Integer.parseInt(value));
				break;
			case "pageSize":
				query.setPageSize(Integer.parseInt(value));
				break;
			case "orderColumns":
				query.setOrderColumns(value);
				break;
			case "orderDirection":
				query.setOrderDirection(value);
				break;
			default:
				query.getQueryData().put(key, value);
				break;
			}
		}
		query.setOrderBy();// 将排序字段和排序方向拼接
		return query;
	}

	/**
	 * 
	 * @Description: 获取类型
	 * @Title: getGenericType
	 * @return Class<T>
	 * @throws
	 */
	@SuppressWarnings({"unchecked" })
	public <T> Class<T> getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType)
					.getActualTypeArguments();
			return (Class<T>) params[index];
		}
		return null;
	}

	
}
