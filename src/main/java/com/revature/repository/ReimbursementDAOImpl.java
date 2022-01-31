package com.revature.repository;

import com.revature.JDBCPostgreSQLConnection;
import com.revature.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    private static Logger log = LoggerFactory.getLogger(com.revature.App.class);

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursements() {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            ArrayList<ReimbursementDTO> list = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM getAll() ORDER BY reimb_id;");
            while (resultSet.next()) {
                list.add(createReimbursementDAOObj(resultSet));
            }
            log.debug("Got ReimbursementList from database");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get ReimbursementList from Database");
        return new ArrayList<ReimbursementDTO>();


    }

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursementsByAuthor(int authorUserId) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            ArrayList<ReimbursementDTO> list = new ArrayList<>();
            String sql = "SELECT * FROM getByAuthor(?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, authorUserId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createReimbursementDAOObj(resultSet));
            }
            log.debug("Got ReimbursementList by author from database");
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get ReimbursementList by author from Database");
        return new ArrayList<ReimbursementDTO>();

    }

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursementsByResolver(int resolverUserId) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            ArrayList<ReimbursementDTO> list = new ArrayList<>();
            String sql = "SELECT * FROM getByResolver(?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, resolverUserId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createReimbursementDAOObj(resultSet));
            }
            log.debug("Got ReimbursementList by resolver from database");
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get ReimbursementList by resolver from Database");
        return new ArrayList<ReimbursementDTO>();
    }

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursementsByStatus(Status status) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            ArrayList<ReimbursementDTO> list = new ArrayList<>();
            String sql = "SELECT * FROM getByStatus(?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status.toString());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createReimbursementDAOObj(resultSet));
            }
            log.debug("Got ReimbursementList by status from database");
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get ReimbursementList by status from Database");
        return new ArrayList<ReimbursementDTO>();

    }

    @Override
    public ReimbursementDTO getReimbursementById(int reimbId) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            ReimbursementDTO reimbursement = null;
            String sql = "SELECT * FROM getById(?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reimbId);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                reimbursement = createReimbursementDAOObj(resultSet);
            }
            log.debug("Got Reimbursement by id from database");
            return reimbursement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Get Reimbursement by id from Database");
        return null;
    }

    @Override
    public boolean updateReimbursement(ReimbursementDTO reimbursement, int reimbId) {
        try( Connection connection = JDBCPostgreSQLConnection.getConnection()){
            String sql = "UPDATE ers_reimbursement SET reimb_amount = ?, reimb_submitted = ?, reimb_resolved = ?, reimb_description = ?, reimb_author = ?, reimb_resolver = ?, reimb_status_id = ?, reimb_type_id = ? WHERE reimb_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            ReimbursementDTO oldReimbursement = this.getReimbursementById(reimbId);
            if(oldReimbursement == null || oldReimbursement.getAuthorUserId() == reimbursement.getResolverUserId()){
                return false;
            }


            int count = 1;
            statement.setInt(count++, reimbursement.getAmount() == 0 ? oldReimbursement.getAmount() : reimbursement.getAmount());
            statement.setTimestamp(count++, reimbursement.getTimeSubmitted() == null ? oldReimbursement.getTimeSubmitted() : reimbursement.getTimeSubmitted());
            statement.setTimestamp(count++, reimbursement.getTimeResolved() == null ? oldReimbursement.getTimeResolved() : reimbursement.getTimeResolved());
            statement.setString(count++, reimbursement.getDescription() == null ? oldReimbursement.getDescription() : reimbursement.getDescription());
            statement.setInt(count++, reimbursement.getAuthorUserId() == 0 ? oldReimbursement.getAuthorUserId() : reimbursement.getAuthorUserId());
            statement.setInt(count++, reimbursement.getResolverUserId() == 0 ? oldReimbursement.getResolverUserId() : reimbursement.getResolverUserId());
            statement.setInt(count++, reimbursement.getStatusId() == 0 ? oldReimbursement.getStatusId() : reimbursement.getStatusId());
            statement.setInt(count++, reimbursement.getTypeId() == 0 ? oldReimbursement.getTypeId() : reimbursement.getTypeId());
            statement.setInt(count++, reimbId);

            statement.execute();
            log.debug("Updated reimbursement to the database");
            return true;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        log.error("Failed to update reimbursement to the database");
        return false;
    }

    @Override
    public boolean addReimbursement(ReimbursementDTO reimbursement) {
        try( Connection connection = JDBCPostgreSQLConnection.getConnection()){
            String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 1;
            statement.setInt(count++, reimbursement.getAmount());
            statement.setTimestamp(count++, new Timestamp(System.currentTimeMillis()));
            statement.setString(count++, reimbursement.getDescription());
            statement.setInt(count++, reimbursement.getAuthorUserId());
            statement.setInt(count++, 1);
            statement.setInt(count++, reimbursement.getTypeId());

            statement.execute();
            log.debug("Added reimbursement to the database");
            return true;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        log.error("Failed to add reimbursement to the database");
        return false;
    }

    @Override
    public boolean deleteReimbursement(int reimbId) {
        try (Connection connection = JDBCPostgreSQLConnection.getConnection()) {
            String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reimbId);

            statement.execute();
            log.debug("Removed Reimbursement by id from database");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.error("Failed to Remove Reimbursement by id from Database");
        return false;
    }


    private static ReimbursementDTO createReimbursementDAOObj(ResultSet resultSet) throws SQLException {
        //getting Reimbursement fields
        int id = resultSet.getInt("reimb_id");
        int amount = resultSet.getInt("reimb_amount");
        Timestamp timeSubmitted = resultSet.getTimestamp("reimb_submitted");
        Timestamp timeResolved = resultSet.getTimestamp("reimb_resolved");
        String description = resultSet.getString("reimb_description");
        byte[] receipt = resultSet.getBytes("reimb_receipt");
        int authorUserId = resultSet.getInt("reimb_author");
        int resolverUserId = resultSet.getInt("reimb_resolver");
        int statusId = resultSet.getInt("reimb_status_id");
        int typeId = resultSet.getInt("reimb_type_id");
        Status status = null;
        Type type = null;


        //Getting Author User fields
        int userId1 = resultSet.getInt("ers_user_id");
        String username1 = resultSet.getString("ers_username");
        String firstName1 = resultSet.getString("user_first_name");
        String lastName1 = resultSet.getString("user_last_name");
        String email1 = resultSet.getString("user_email");
        byte[] hash1 = resultSet.getBytes("ers_password");
        int roleId1 = resultSet.getInt("user_role_id");
        UserRole userRole1 = null;

        //Getting Resolver User fields
        int userId2 = resultSet.getInt("ers_user_id2");
        String username2 = resultSet.getString("ers_username2");
        String firstName2 = resultSet.getString("user_first_name2");
        String lastName2 = resultSet.getString("user_last_name2");
        String email2 = resultSet.getString("user_email2");
        byte[] hash2 = resultSet.getBytes("ers_password2");
        int roleId2 = resultSet.getInt("user_role_id2");
        UserRole userRole2 = null;



        try{
            status = Status.valueOf(resultSet.getString("reimb_status"));
            type = Type.valueOf(resultSet.getString("reimb_type"));
            userRole1 = UserRole.valueOf(resultSet.getString("user_role"));

            String userRole2String = resultSet.getString("user_role2");
            if(userRole2String != null){
                userRole2 = UserRole.valueOf(userRole2String);
            }

        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        //Creating the user objects
        User authorUser = new User(userId1, username1, hash1, firstName1, lastName1, email1, roleId1, userRole1);
        User resolverUser = new User(userId2, username2, hash2, firstName2, lastName2, email2, roleId2, userRole2);



        //putting it all together and returning
        return new ReimbursementDTO(id,amount,timeSubmitted,timeResolved,description,receipt,authorUserId,authorUser,resolverUserId,resolverUser,statusId,status,typeId,type);
    }
}