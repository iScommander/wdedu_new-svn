package com.jeecms.core.entity;

import com.jeecms.core.entity.base.BaseProvince;

import java.util.Collection;
import java.util.Set;

public class CmsProvince extends BaseProvince {
	private static final long serialVersionUID = 1L;

	public static Integer[] fetchIds(Collection<CmsProvince> provinces) {
		if (provinces == null) {
			return null;
		}
		Integer[] ids = new Integer[provinces.size()];
		int i = 0;
		for (CmsProvince r : provinces) {
			ids[i++] = r.getId();
		}
		return ids;
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsProvince () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsProvince (Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsProvince (
		Integer id,
		String name) {

		super (
			id,
			name);
	}
	public void delFromUsers(CmsUser user) {
		if (user == null) {
			return;
		}
		Set<CmsUser> set = getUsers();
		if (set == null) {
			return;
		}
		set.remove(user);
	}

	/* [CONSTRUCTOR MARKER END] */

}