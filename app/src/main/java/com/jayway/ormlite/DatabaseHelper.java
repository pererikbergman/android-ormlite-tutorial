package com.jayway.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jayway.ormlite.model.Email;
import com.jayway.ormlite.model.Project;
import com.jayway.ormlite.model.Role;
import com.jayway.ormlite.model.User;
import com.jayway.ormlite.model.UserProject;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "ormlite.db";
    private static final int    DATABASE_VERSION = 16;

    private Dao<User, Integer> mUserDao = null;
    private Dao<Role, Integer> mRoleDao = null;
    private Dao<Email, Integer> mEmailDao = null;
    private Dao<UserProject, Integer> mUserProjectDao = null;
    private Dao<Project, Integer> mProjectDao = null;
    private RuntimeExceptionDao<User, ?> m;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Role.class);
            TableUtils.createTable(connectionSource, Email.class);
            TableUtils.createTable(connectionSource, UserProject.class);
            TableUtils.createTable(connectionSource, Project.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Role.class, true);
            TableUtils.dropTable(connectionSource, Email.class, true);
            TableUtils.dropTable(connectionSource, UserProject.class, true);
            TableUtils.dropTable(connectionSource, Project.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* User */

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (mUserDao == null) {
            mUserDao = getDao(User.class);
        }
m = getRuntimeExceptionDao(User.class);
        return mUserDao;
    }

    /* Role */

    public Dao<Role, Integer> getRoleDao() throws SQLException {
        if (mRoleDao == null) {
            mRoleDao = getDao(Role.class);
        }

        return mRoleDao;
    }

    /* Email */

    public Dao<Email, Integer> getEmailDao() throws SQLException {
        if (mEmailDao == null) {
            mEmailDao = getDao(Email.class);
        }

        return mEmailDao;
    }

    /* UserProject */

    public Dao<UserProject, Integer> getUserProjectDao() throws SQLException {
        if (mUserProjectDao == null) {
            mUserProjectDao = getDao(UserProject.class);
        }

        return mUserProjectDao;
    }

    /* Project */

    public Dao<Project, Integer> getProjectDao() throws SQLException {
        if (mProjectDao == null) {
            mProjectDao = getDao(Project.class);
        }

        return mProjectDao;
    }

    @Override
    public void close() {
        mUserDao = null;
        mRoleDao = null;
        mEmailDao = null;
        mUserProjectDao = null;
        mProjectDao = null;

        super.close();
    }

    private static DatabaseHelper sDatabaseHelper;

//    public static DatabaseHelper getInstance(Context context) {
//        if (sDatabaseHelper == null) {
//            sDatabaseHelper = new DatabaseHelper(context.getApplicationContext());
//        }
//
//        return sDatabaseHelper;
//    }

    public static DatabaseHelper getInstance() {
        return sDatabaseHelper;
    }
}