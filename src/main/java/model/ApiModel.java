/**
 * 
 */
/**
 * @author hadong
 *
 */
package model;

import java.util.List;

import net.oschina.zwlzwl376.jfinal.plugin.tablebind.Table;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
@Table("shiro_users")
public class ApiModel extends Model<ApiModel> {
	public final static ApiModel dao = new ApiModel();

	public List<Record> search() {
		List<Record> records = Db.find("select * from shiro_users");
		return records;
	}

}