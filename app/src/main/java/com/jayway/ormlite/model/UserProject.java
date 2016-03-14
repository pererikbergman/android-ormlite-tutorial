package com.jayway.ormlite.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.table.DatabaseTable;
import com.jayway.ormlite.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pererikbergman on 20/12/15.
 */
@DatabaseTable(tableName = UserProject.TABLE_NAME_USER_PROJECT)
public class UserProject {

    public static final String TABLE_NAME_USER_PROJECT = "user_project";

    public static final String FIELD_NAME_ID        = "id";
    public static final String FIELD_NAME_USER_ID   = "user_id";
    public static final String USER_NAME_PROJECT_ID = "project_id";

    private static PreparedQuery<Project> sProjectsForUserQuery = null;
    private static PreparedQuery<User>    sUsersForPostQuery    = null;

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(foreign = true, columnName = FIELD_NAME_USER_ID)
    private User mUser;

    @DatabaseField(foreign = true, columnName = USER_NAME_PROJECT_ID)
    private Project mProject;

    public UserProject() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    /** Getters & Setters **/

    public UserProject(final User user, final Project project) {
        mUser = user;
        mProject = project;
    }

    public int getId() {
        return mId;
    }

    public User getUser() {
        return mUser;
    }

    public UserProject setUser(final User user) {
        mUser = user;
        return this;
    }

    public Project getProject() {
        return mProject;
    }

    public UserProject setProject(final Project project) {
        mProject = project;
        return this;
    }

    /** Static Help Functions **/

    private static Dao<UserProject, Integer> getUserProjectDao() {
        Dao<UserProject, Integer> userDao = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getInstance();
            userDao = helper.getUserProjectDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDao;
    }

    public static List<Project> getProjects(User user) throws SQLException {
        if (sProjectsForUserQuery == null) {
            sProjectsForUserQuery = makeProjectsForUserQuery();
        }
        sProjectsForUserQuery.setArgumentHolderValue(0, user);
        return getProjectDao().query(sProjectsForUserQuery);
    }

    private static PreparedQuery<Project> makeProjectsForUserQuery() throws SQLException {
        QueryBuilder<UserProject, Integer> userPostQb = getUserProjectDao().queryBuilder();
        userPostQb.selectColumns(UserProject.USER_NAME_PROJECT_ID);
        userPostQb.where().eq(UserProject.FIELD_NAME_USER_ID, new SelectArg());
        QueryBuilder<Project, Integer> postQb = getProjectDao().queryBuilder();
        postQb.where().in(Project.FIELD_NAME_ID, userPostQb);
        return postQb.prepare();
    }

    private static Dao<Project, Integer> getProjectDao() {
        Dao<Project, Integer> projectDao = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getInstance();
            projectDao = helper.getProjectDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectDao;
    }

    public static List<User> getUsers(Project project) throws SQLException {
        if (sUsersForPostQuery == null) {
            sUsersForPostQuery = makeUsersForProjectQuery();
        }
        sUsersForPostQuery.setArgumentHolderValue(0, project);
        return getUserDao().query(sUsersForPostQuery);
    }

    private static PreparedQuery<User> makeUsersForProjectQuery() throws SQLException {
        QueryBuilder<UserProject, Integer> userPostQb = getUserProjectDao().queryBuilder();
        userPostQb.selectColumns(UserProject.FIELD_NAME_USER_ID);
        userPostQb.where().eq(UserProject.USER_NAME_PROJECT_ID, new SelectArg());
        QueryBuilder<User, Integer> userQb = getUserDao().queryBuilder();
        userQb.where().in(Project.FIELD_NAME_ID, userPostQb);
        return userQb.prepare();
    }

    private static Dao<User, Integer> getUserDao() {
        Dao<User, Integer> userDao = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getInstance();
            userDao = helper.getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDao;
    }
}
