package com.jayway.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Project.TABLE_NAME_PROJECTS)
public class Project {

    public static final String TABLE_NAME_PROJECTS = "projects";

    public static final String FIELD_NAME_ID   = "id";
    public static final String FIELD_NAME_NAME = "name";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    public Project() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    /** Getters & Setters **/

    public int getId() {
        return mId;
    }

    public Project setId(final int id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Project setName(final String name) {
        mName = name;
        return this;
    }
}
