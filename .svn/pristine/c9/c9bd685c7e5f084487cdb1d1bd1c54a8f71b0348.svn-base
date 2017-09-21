package 操作数据库;

import java.util.List;

import com.chen.domain.Stxxb;

public class Model操作数据库 {

	/**
	 * 设置id为3，name为4
	 */
	public void update() {
		new Stxxb().set("id", 3).set("name", 4);
	}

	/**
	 * 删除id为25的数据
	 */
	public void delete() {
		new Stxxb().deleteById(25);
	}

	/*
	 * 查询id为25的对象，并且将name属性修改为James并更新到数据库
	 */
	public void findToUpdate() {
		new Stxxb().findByIdLoadColumns(25, "id").set("name", "James").update();
	}

	/**
	 * 获取name熟悉
	 */
	public void findName() {
		String name = new Stxxb().getSTTM();
	}
	
	/**
	 * 查询id大于18的数据
	 */
	public void findByWhere(){
		List<Stxxb> stxxb = new Stxxb().dao.find("select * from stxxb where id>18");
	}
	
	/**
	 * 分页查询
	 */
	public void findToPage(){
		new Stxxb().dao.paginate(1, 10, "select * ", "from stxxb where id > ?", 18);
	}
}
