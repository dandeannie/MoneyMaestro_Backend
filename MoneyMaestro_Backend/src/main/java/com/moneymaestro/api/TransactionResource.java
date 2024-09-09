package com.moneymaestro.api;

import com.google.gson.Gson;
import com.moneymaestro.dao.TransactionDAO;
import com.moneymaestro.model.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/transactions")
public class TransactionResource {
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addTransaction(String json) {
        try {
            Transaction transaction = gson.fromJson(json, Transaction.class);
            transactionDAO.addTransaction(transaction);
            return gson.toJson("Transaction added successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTransactions() {
        try {
            List<Transaction> transactions = transactionDAO.getAllTransactions();
            return gson.toJson(transactions);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTransaction(@PathParam("id") int id) {
        try {
            Transaction transaction = (Transaction) transactionDAO.getTransactionsByUserId(id);
            return gson.toJson(transaction);
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTransaction(@PathParam("id") int id, String json) {
        try {
            Transaction transaction = gson.fromJson(json, Transaction.class);
            transaction.setTransactionId(id);
            transactionDAO.updateTransaction(transaction);
            return gson.toJson("Transaction updated successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteTransaction(@PathParam("id") int id) {
        try {
            transactionDAO.deleteTransaction(id);
            return gson.toJson("Transaction deleted successfully");
        } catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }
    }
}