/**
 * 
 */
/**
 * @author hadong
 *
 */
package controller;

import com.jfinal.core.Controller;

import frame.plugin.collerbind.Coller;

@Coller(value = { "/" }, path = "main")
public class MainController extends Controller {

	public void index() {
		render("index.html");
	}
	
	public void getMenu(){
		renderJson();
	}

}