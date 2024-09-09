package com.moneymaestro.api;

import com.google.gson.Gson;
import com.moneymaestro.dao.CategoryDAO;
import com.moneymaestro.model.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryResource {
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCategory(String json) {
        try {
            Category category = gson.fromJson(json, Category.class);
            categoryDAO.addCategory(category);
            return gson.toJson("Category added successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCategories() {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            return gson.toJson(categories);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategory(@PathParam("id") int id) {
        try {
            Category category = categoryDAO.getCategory(id);
            return gson.toJson(category);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateCategory(@PathParam("id") int id, String json) {
        try {
            Category category = gson.fromJson(json, Category.class);
            category.setCategoryId(id);
            categoryDAO.updateCategory(category);
            return gson.toJson("Category updated successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCategory(@PathParam("id") int id) {
        try {
            categoryDAO.deleteCategory(id);
            return gson.toJson("Category deleted successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }
}