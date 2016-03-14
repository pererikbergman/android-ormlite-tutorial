package com.jayway.ormlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.jayway.ormlite.model.Email;
import com.jayway.ormlite.model.Project;
import com.jayway.ormlite.model.Role;
import com.jayway.ormlite.model.User;
import com.jayway.ormlite.model.UserProject;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void example() throws SQLException {
        DatabaseHelper     helper  = new DatabaseHelper(this);
        Dao<User, Integer> userDao = helper.getUserDao();

        // Create user.
        User user = new User().setName("Mike");
        userDao.create(user);

        // Update user.
        user.setName("Michael");
        userDao.update(user);

        // Create or Update user.
        userDao.createOrUpdate(user);

        // Refresh user.
        userDao.refresh(user);

        // Delete user.
        userDao.delete(user);

        // One-to-one relationship Create user.
        user = new User().setName("Mike");
        user.setRole(new Role("Programmer"));
        userDao.create(user);

        // Obtain the role
        user = userDao.queryForId(0);
        Role role = user.getRole();

        // One-to-many
        user = new User().setName("Clause");
        userDao.create(user); // Without creating the object this won't work.

        final Dao<Email, Integer> emailsDao = DatabaseHelper.getInstance().getEmailDao();
        emailsDao.create(new Email(user).setEmail("my@example.com"));
        emailsDao.create(new Email(user).setEmail("my2@example.com"));

        // List all users with all addresses
        final List<User> userList = userDao.queryForAll();
        for (User u : userList) {
            System.out.println("user = " + u);
            final ForeignCollection<Email> emailList = u.getEmails();
            for (Email email : emailList) {
                System.out.println("email = " + email);
            }
        }

        // Many-to-many

        // Create the users with roles.
        Role firstRole  = new Role("Programmer");
        User firstUser  = new User().setName("Clause").setRole(firstRole);
        Role secondRole = new Role("Manager");
        User secondUser = new User().setName("Mikes").setRole(secondRole);

        userDao.create(firstUser);
        userDao.create(secondUser);

        // Create the project.
        Dao<Project, Integer> projectDao = helper.getProjectDao();
        Project               project    = new Project().setName("Database Project");
        projectDao.create(project);

        // Create the relation table.
        Dao<UserProject, Integer> userProjectDao = helper.getUserProjectDao();
        UserProject               userProject    = new UserProject(firstUser, project);
        UserProject               userProject2   = new UserProject(secondUser, project);

        userProjectDao.create(userProject);
        userProjectDao.create(userProject2);

        // Get All Projects for one user.
        final List<Project> projects = UserProject.getProjects(firstUser);

        // Get all users for one project.
        final List<User> users = UserProject.getUsers(project);

    }

}
