package module.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.kit.RecordKit;
import frame.plugin.tablebind.Table;

@SuppressWarnings("serial")
@Table("sys_config")
public class ConfModel extends Model<ConfModel> {
	public static final ConfModel dao = new ConfModel();

	/**
	 * 获取所有配置信息
	 * 
	 * @return
	 */
	public List<Record> findAllConfig() {
		return Db.find("select * from sys_config sc left join sys_config_type sct on sct.cfg_type_id = sc.cfg_type_id");
	}

	/**
	 * 分页获取所有配置信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> findAllConfigPage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select sc.*,sct.cfg_type_name", "from sys_config sc left join sys_config_type sct on sct.cfg_type_id = sc.cfg_type_id order by sc.cfg_id asc");
	}

	/**
	 * 批量添加配置
	 * 
	 * @param list
	 * @return
	 */
	public void insertConfig(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("insert into sys_config(cfg_key,cfg_value,cfg_type_id) values (?,?,?)", "cfg_key,cfg_value,cfg_type_id", recordList, recordList.size());
	}

	/**
	 * 批量更新配置
	 * 
	 * @param list
	 */
	public void updateConfig(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("update sys_config set cfg_key = ?,cfg_value= ?,cfg_type_id=? where cfg_id = ?", "cfg_key,cfg_value,cfg_type_id,cfg_id", recordList, recordList.size());
	}

	/**
	 * 批量删除配置
	 * 
	 * @param list
	 */
	public void deleteConfig(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from sys_config where cfg_id = ?", "cfg_id", recordList, recordList.size());
	}

	/**
	 * 获取所有配置分类信息
	 * 
	 * @return
	 */
	public List<Record> findAllConfigType() {
		return Db.find("select * from sys_config_type");
	}

	/**
	 * 分页获取所有配置分类信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> findAllConfigTypePage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from sys_config_type");
	}

	/**
	 * 批量添加配置分类
	 * 
	 * @param list
	 * @return
	 */
	public void insertConfigType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("insert into sys_config_type(cfg_type_name) values (?)", "cfg_type_name", recordList, recordList.size());
	}

	/**
	 * 批量更新配置分类
	 * 
	 * @param list
	 */
	public void updateConfigType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("update sys_config_type set cfg_type_name = ? where cfg_type_id = ?", "cfg_type_name,cfg_type_id", recordList, recordList.size());
	}

	/**
	 * 批量删除配置分类
	 * 
	 * @param list
	 */
	public void deleteConfigType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from sys_config_type where cfg_type_id = ?", "cfg_type_id", recordList, recordList.size());
	}

	/**
	 * 获取所有配置分类信息
	 * 
	 * @return
	 */
	public List<Record> findAllQAContent() {
		return Db.find("select * from qa_content");
	}

	/**
	 * 分页获取所有配置分类信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> findAllQAContentPage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from qa_content");
	}

	/**
	 * 批量添加配置分类
	 * 
	 * @param list
	 * @return
	 */
	public void insertQAContent(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("insert into qa_content(qac_question,qac_answer,qac_type) values (?,?,?)", "qac_question,qac_answer,qac_type", recordList, recordList.size());
	}

	/**
	 * 批量更新配置分类
	 * 
	 * @param list
	 */
	public void updateQAContent(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("update qa_content set qac_question = ?,qac_answer = ?,qac_type=? where qac_id = ?", "qac_question,qac_answer,qac_type,qac_id", recordList, recordList.size());
	}

	/**
	 * 批量删除配置分类
	 * 
	 * @param list
	 */
	public void deleteQAContent(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from qa_content where qac_id = ?", "qac_id", recordList, recordList.size());
	}

	/**
	 * 获取所有配置分类信息
	 * 
	 * @return
	 */
	public List<Record> findAllQAType() {
		return Db.find("select * from qa_type");
	}

	/**
	 * 分页获取所有配置分类信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> findAllQATypePage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from qa_type");
	}

	/**
	 * 批量添加配置分类
	 * 
	 * @param list
	 * @return
	 */
	public void insertQAType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("insert into qa_type(qat_name) values (?)", "qat_name", recordList, recordList.size());
	}

	/**
	 * 批量更新配置分类
	 * 
	 * @param list
	 */
	public void updateQAType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("update qa_type set qat_name = ? where qat_id = ?", "qat_name,qat_id", recordList, recordList.size());
	}

	/**
	 * 批量删除配置分类
	 * 
	 * @param list
	 */
	public void deleteQAType(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from qa_type where qat_id = ?", "qat_id", recordList, recordList.size());
	}

	/**
	 * 获取所有配置分类信息
	 * 
	 * @return
	 */
	public List<Record> findAllWxUser() {
		return Db.find("select * from wx_customer");
	}

	/**
	 * 分页获取所有配置分类信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> findAllWxUserPage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from wx_customer");
	}

	/**
	 * 通过key获取value
	 * 
	 * @param cfg_key
	 * @return
	 */
	public String findCfgValueByKey(String cfg_key) {
		return Db.findFirst("select * from sys_config where cfg_key = ?", cfg_key).getStr("cfg_value");
	}

	/**
	 * 获取客户的问答
	 * 
	 * @return
	 */
	public List<Record> findAllWxUserQA() {
		return Db.find("select * from wx_question wq left join wx_customer wc on wq.wq_openid=wc.wc_openid");
	}

	/**
	 * 分页获取客户问答
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> findAllWxUserPageQA(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from wx_question wq left join wx_customer wc on wq.wq_openid=wc.wc_openid");
	}
}
