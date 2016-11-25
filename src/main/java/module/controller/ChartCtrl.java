package module.controller;

import com.jfinal.core.Controller;

import frame.plugin.collerbind.Coller;

/**
 * @author hadong
 */
@Coller(value = { "/chart" }, path = "chart")
public class ChartCtrl extends Controller {
	public void index() {
		switch (getPara("type", "bar")) {
		case "bar":
			render("chart-bar.htm");
			break;
		case "pie":
			render("chart-pie.htm");
			break;
		}
	}
}
