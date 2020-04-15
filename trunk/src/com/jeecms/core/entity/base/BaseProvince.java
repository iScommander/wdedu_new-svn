package com.jeecms.core.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_role table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_d_province"
 */

public abstract class BaseProvince  implements Serializable {

	public static String REF = "CmsProvince";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";


	// constructors
	public BaseProvince () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProvince (Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProvince (
		Integer id,
		String name) {

		this.setId(id);
		this.setName(name);
		
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String name;


	private java.util.Set<com.jeecms.core.entity.CmsUser> users;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="role_id"
     */
	public Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: role_name
	 */
	public String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: role_name
	 * @param name the role_name value
	 */
	public void setName (String name) {
		this.name = name;
	}

 
 
  
	
	public java.util.Set<com.jeecms.core.entity.CmsUser> getUsers() {
		return users;
	}

	public void setUsers(java.util.Set<com.jeecms.core.entity.CmsUser> users) {
		this.users = users;
	}

	@Override
    public boolean equals (Object obj) {
		if (null == obj) {
            return false;
        }
		if (!(obj instanceof com.jeecms.core.entity.CmsRole)) {
            return false;
        } else {
			com.jeecms.core.entity.CmsRole cmsRole = (com.jeecms.core.entity.CmsRole) obj;
			if (null == this.getId() || null == cmsRole.getId()) {
                return false;
            } else {
                return (this.getId().equals(cmsRole.getId()));
            }
		}
	}

	@Override
    public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) {
                return super.hashCode();
            } else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	@Override
    public String toString () {
		return super.toString();
	}


}