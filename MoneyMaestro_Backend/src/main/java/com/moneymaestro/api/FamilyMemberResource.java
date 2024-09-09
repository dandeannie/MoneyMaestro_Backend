package com.moneymaestro.api;

import com.google.gson.Gson;
import com.moneymaestro.dao.FamilyMemberDAO;
import com.moneymaestro.model.FamilyMember;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/family-members")
public class FamilyMemberResource {
    private final FamilyMemberDAO familyMemberDAO = new FamilyMemberDAO();
    private final Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addFamilyMember(String json) {
        try {
            FamilyMember familyMember = gson.fromJson(json, FamilyMember.class);
            familyMemberDAO.addFamilyMember(familyMember);
            return gson.toJson("Family member added successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllFamilyMembers() {
        try {
            List<FamilyMember> familyMembers = familyMemberDAO.getAllFamilyMembers();
            return gson.toJson(familyMembers);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFamilyMember(@PathParam("id") int id) {
        try {
            FamilyMember familyMember = familyMemberDAO.getFamilyMember(id);
            return gson.toJson(familyMember);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateFamilyMember(@PathParam("id") int id, String json) {
        try {
            FamilyMember familyMember = gson.fromJson(json, FamilyMember.class);
            familyMember.setFamilyMemberId(id);
            familyMemberDAO.updateFamilyMember(familyMember);
            return gson.toJson("Family member updated successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteFamilyMember(@PathParam("id") int id) {
        try {
            familyMemberDAO.deleteFamilyMember(id);
            return gson.toJson("Family member deleted successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }
}