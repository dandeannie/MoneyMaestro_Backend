package com.moneymaestro.api;

import com.google.gson.Gson;
import com.moneymaestro.dao.UserDAO;
import com.moneymaestro.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/users")
public class UserResource {
    private final UserDAO userDAO = new UserDAO();
    private final Gson gson = new Gson();
    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String json) {
        try {
            User user = gson.fromJson(json, User.class);

            // Simple validation check
            if (user.getName() == null || user.getPassword() == null) {
                return gson.toJson("Error: User name and password cannot be null");
            }

            userDAO.addUser(user);
            return gson.toJson("User added successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding user", e);
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        try {
            List<User> users = userDAO.getAllUsers();
            return gson.toJson(users);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving users", e);
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") int id) {
        try {
            User user = userDAO.getUser(id);

            return gson.toJson(Objects.requireNonNullElse(user, "Error: User not found"));

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving user", e);
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(@PathParam("id") int id, String json) {
        try {
            User user = gson.fromJson(json, User.class);
            user.setUserId(id);

            if (userDAO.getUser(id) == null) {
                return gson.toJson("Error: User not found");
            }

            userDAO.updateUser(user);
            return gson.toJson("User updated successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") int id) {
        try {
            if (userDAO.getUser(id) == null) {
                return gson.toJson("Error: User not found");
            }

            userDAO.deleteUser(id);
            return gson.toJson("User deleted successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
            return gson.toJson("Error: " + e.getMessage());
        }
    }
}
