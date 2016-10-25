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

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import event.ApiEvent;
import frame.plugin.collerbind.Coller;

@Coller(value = { "/api" }, path = "api")
public class ApiController extends Controller {

	public void index() {
		List<Record> users = ApiModel.dao.search();
		EventKit.post(new ApiEvent(users));
		renderJson(users);
	}


}