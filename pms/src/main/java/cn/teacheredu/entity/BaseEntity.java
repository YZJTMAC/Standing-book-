package cn.teacheredu.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 5696084954627586225L;
	
	private static String getString(Object o, Class<?> c) {
		String res = "";
		Field[] fields = c.getDeclaredFields();

		if (c.getSuperclass() != BaseEntity.class) {
			res += getString(o, c.getSuperclass()) + ", ";
		}

		for (Field field : fields) {
			field.setAccessible(true);
			try {
				res += field.getName() + " = " + field.get(o) + ", ";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return res.substring(0, (res.length() - 2 < 0 ? 0 : res.length() - 2));
	}

	public String toString() {
		return BaseEntity.getString(this, this.getClass());
	}

	java.util.Map<String, Object> extInfo = null;
	/**获得用户扩展显示信息*/
	public java.util.Map<String,  Object> getExtInfo() {
		return extInfo;
	}
	public void setExtInfo(java.util.Map<String, Object> extInfo) {
		this.extInfo = extInfo;
	}
	
}
