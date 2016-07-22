/**
 * 
 */
/**
 * @author hadong
 *
 */
package controller;

import java.util.List;

import model.ApiModel;
import net.dreamlu.event.EventKit;
import net.oschina.zwlzwl376.jfinal.plugin.collerbind.Coller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import event.ApiEvent;

@Coller(value = { "/api" }, path = "api")
public class ApiController extends Controller {

	public void index() {
		List<Record> users = ApiModel.dao.search();
		EventKit.postEvent(new ApiEvent(users));
		renderJson(users);
	}

	public void qq() {
		WebQQController webqq = new WebQQController();
		renderText("ok");
	}

}