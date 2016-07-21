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
import net.oschina.zwlzwl376.jfinal.plugin.collerbind.Coller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

@Coller(value = { "/api" }, views = "api")
public class ApiController extends Controller {

	public void index() {
		List<Record> users = ApiModel.dao.search();
		renderJson(users);
	}
}