/**
 * 
 */
/**
 * @author Administrator
 *
 */
package module.validator;

import module.controller.SystemCtrl;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class SigninValidator extends Validator {

	protected void validate(Controller c) {
		if ("POST".equalsIgnoreCase(c.getRequest().getMethod().toUpperCase())) {
			validateRequiredString("password", "errorMsg", "请输入密码");
			validateRequiredString("username", "errorMsg", "请输入用户名");
		}
	}

	protected void handleError(Controller c) {
		c.setAttr("username", c.getPara("username"));
		c.setAttr("rememberMe", c.getPara("rememberMe"));
		c.forwardAction(SystemCtrl.SYSTEM_LOGIN_PAGE);
		return;
	}
}