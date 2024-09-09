package com.moneymaestro.api;

import com.google.gson.Gson;
import com.moneymaestro.dao.FamilyDAO;
import com.moneymaestro.model.Family;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/families")
public class FamilyResource {
    private final FamilyDAO familyDAO = new FamilyDAO();
    private final Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addFamily(String json) {
        try {
            Family family = gson.fromJson(json, Family.class);
            familyDAO.addFamily(family);
            return gson.toJson("Family added successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllFamilies() {
        try {
            List<Family> families = familyDAO.getAllFamilies();
            return gson.toJson(families);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFamily(@PathParam("id") int id) {
        try {
            Family family = familyDAO.getFamily(id);
            return gson.toJson(family);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateFamily(@PathParam("id") int id, String json) {
        try {
            Family family = gson.fromJson(json, Family.class);
            family.setFamilyId(id);
            familyDAO.updateFamily(family);
            return gson.toJson("Family updated successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteFamily(@PathParam("id") int id) {
        try {
            familyDAO.deleteFamily(id);
            return gson.toJson("Family deleted successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }
}