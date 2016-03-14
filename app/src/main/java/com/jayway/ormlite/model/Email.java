package com.jayway.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Email.TABLE_NAME_EMAIL)
public class Email {

    public static final String TABLE_NAME_EMAIL = "emails";

    public static final String FIELD_NAME_ID    = "id";
    public static final String FIELD_NAME_EMAIL = "email";
    public static final String FIELD_NAME_USER  = "user";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_EMAIL)
    private String mEmail;

    @DatabaseField(columnName = FIELD_NAME_USER, foreign = true, foreignAutoRefresh = true)
    private User mUser;

    public Email() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    public Email(final User user) {
        mUser = user;
    }

    /** Getters & Setters **/

    public int getId() {
        return mId;
    }

    public String getEmail() {
        return mEmail;
    }

    public Email setEmail(final String email) {
        mEmail = email;
        return this;
    }

    public Email setUser(final User user) {
        mUser = user;
        return this;
    }

    @Override
    public String toString() {
        return "Email{" +
                "mEmail='" + mEmail + '\'' +
                '}';
    }
}
