package com.jayway.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Role.TABLE_NAME_ROLES)
public class Role {

    public static final String TABLE_NAME_ROLES = "roles";

    public static final String FIELD_NAME_ID   = "id";
    public static final String FIELD_NAME_ROLE = "role_name";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_ROLE)
    private String mRoleName;

    public Role(final String roleName) {
        mRoleName = roleName;
    }

    public int getId() {
        return mId;
    }

    public String getRoleName() {
        return mRoleName;
    }

    public Role setRoleName(final String roleName) {
        mRoleName = roleName;
        return this;
    }

    @Override
    public String toString() {
        return "Role{" +
                "mRoleName='" + mRoleName + '\'' +
                '}';
    }
}
