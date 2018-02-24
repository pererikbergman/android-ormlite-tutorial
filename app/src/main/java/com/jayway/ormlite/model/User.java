package com.jayway.ormlite.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.jayway.ormlite.CustomDao;

@DatabaseTable(tableName = User.TABLE_NAME_USERS, daoClass = CustomDao.class)
public class User {

    public User() {
        // Don't forget the empty constructor, needed by ORMLite.
    }   
    
    public static final String TABLE_NAME_USERS = "users";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_NAME_ROLE   = "role";
    public static final String FIELD_NAME_EMAILS = "emails";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    // One-to-one
    @DatabaseField(columnName = FIELD_NAME_ROLE, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Role mRole;

    // One-to-many
    @ForeignCollectionField(columnName = FIELD_NAME_EMAILS, eager = true)
    private ForeignCollection<Email> mEmails;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public User setName(final String name) {
        mName = name;
        return this;
    }

    public User setId(final int id) {
        mId = id;
        return this;
    }

    public Role getRole() {
        return mRole;
    }

    public User setRole(final Role role) {
        mRole = role;
        return this;
    }

    public ForeignCollection<Email> getEmails() {
        return mEmails;
    }

    @Override
    public String toString() {
        return "User{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
