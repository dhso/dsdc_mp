/**
 * 
 */
/**
 * @author hadong
 *
 */
package event;

import net.dreamlu.event.core.ApplicationEvent;

public class ApiEvent extends ApplicationEvent {

	private static final long serialVersionUID = 6994987952247306131L;

	public ApiEvent(Object source) {
		super(source);
	}

}