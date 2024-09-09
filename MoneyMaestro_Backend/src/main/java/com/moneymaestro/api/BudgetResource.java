package com.moneymaestro.api;

import com.google.gson.Gson;
import com.moneymaestro.dao.BudgetDAO;
import com.moneymaestro.model.Budget;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/budgets")
public class BudgetResource {
    private final BudgetDAO budgetDAO = new BudgetDAO();
    private final Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBudget(String json) {
        try {
            Budget budget = gson.fromJson(json, Budget.class);
            budgetDAO.addBudget(budget);
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson(budget))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson("Error: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBudget(@PathParam("id") int id) {
        try {
            Budget budget = budgetDAO.getBudgetById(id);
            if (budget != null) {
                return Response.ok(gson.toJson(budget)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(gson.toJson("Budget not found"))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson("Error: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBudgets() {
        try {
            List<Budget> budgets = budgetDAO.getAllBudgets();
            return Response.ok(gson.toJson(budgets)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson("Error: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBudgetsByUserId(@PathParam("userId") int userId) {
        try {
            List<Budget> budgets = budgetDAO.getBudgetsByUserId(userId);
            return Response.ok(gson.toJson(budgets)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson("Error: " + e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBudget(@PathParam("id") int id, String json) {
        try {
            Budget updatedBudget = gson.fromJson(json, Budget.class);
            updatedBudget.setBudgetId(id);
            budgetDAO.updateBudget(updatedBudget);
            return Response.ok(gson.toJson(updatedBudget)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson("Error: " + e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBudget(@PathParam("id") int id) {
        try {
            budgetDAO.deleteBudget(id);
            return Response.ok(gson.toJson("Budget deleted successfully")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson("Error: " + e.getMessage()))
                    .build();
        }
    }
}