/**
 * 
 */
/**
 * @author hadong
 *
 */
package listener;

import net.dreamlu.event.core.ApplicationListener;
import net.dreamlu.event.core.Listener;
import event.ApiEvent;

//注解标记，切勿忘记
//@Listener(order = 1, enableAsync = true)
//监听器执行顺序order = 1 越小越优先执行，默认 Integer.MAX_VALUE
//单个监听器的，同步或者异步开关enableAsync = true。当然需要先开启EventPlugin全局异步
@Listener
public class ApiCallListener implements ApplicationListener<ApiEvent> {

	@Override
	public void onApplicationEvent(ApiEvent event) {
		Object source = (Object) event.getSource();
		System.out.println("--------------------ApiCallListener--------------------------");
		// 你的逻辑
		// EmailUtils.sendXxxxx
	}

}