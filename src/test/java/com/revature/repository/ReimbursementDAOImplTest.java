package com.revature.repository;

import com.revature.model.*;
import org.junit.jupiter.api.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReimbursementDAOImplTest {
    private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    public static ReimbursementDTO reimb = new ReimbursementDTO();
    public static ReimbursementDTO reimb2 = new ReimbursementDTO();

    @BeforeAll
    static void setup(){
        UserDAO userDAO = new UserDAOImpl();
        User user1 = userDAO.getUserById(127);
        User user2 = userDAO.getUserById(128);
        User user3 = userDAO.getUserById(129);
        User user4 = userDAO.getUserById(130);


        reimb.setAmount(100);
        reimb.setAuthorUser(user1);
        reimb.setAuthorUserId(user1.getUserId());
        reimb.setResolverUser(user2);
        reimb.setResolverUserId(user2.getUserId());
        reimb.setDescription("need money please");
        reimb.setId(20000);
        reimb.setStatus(Status.PENDING);
        reimb.setStatusId(1);
        reimb.setType(Type.OTHER);
        reimb.setTypeId(4);
        reimb.setTimeSubmitted(new Timestamp(System.currentTimeMillis()));

        reimb2.setAmount(200);
        reimb2.setAuthorUser(user3);
        reimb2.setAuthorUserId(user3.getUserId());
        reimb2.setResolverUser(user4);
        reimb2.setResolverUserId(user4.getUserId());
        reimb2.setDescription("I'm broke cmon");
        reimb2.setId(20202);
        reimb2.setStatus(Status.PENDING);
        reimb2.setStatusId(1);
        reimb2.setType(Type.OTHER);
        reimb2.setTypeId(4);
        reimb2.setTimeSubmitted(new Timestamp(System.currentTimeMillis()));

    }

    @Test
    @Order(1)
    void testAddReimbursement(){
        assertTrue(reimbursementDAO.addReimbursement(reimb));

    }

    @Test
    @Order(2)
    void testGetAllReimbursement(){
        ArrayList<ReimbursementDTO> reimbursementList = reimbursementDAO.getAllReimbursements();
        assertFalse(reimbursementList.isEmpty());
        //this is really dumb but idk how else to get the reimb we just added
        reimb = reimbursementList.get(reimbursementList.size()-1);
    }

    @Test
    @Order(3)
    void testGetReimbursement(){
        assertEquals(reimb.getAuthorUserId(), reimbursementDAO.getReimbursementById(reimb.getId()).getAuthorUserId());
    }

    @Test
    @Order(4)
    void testUpdate(){
        assertTrue(reimbursementDAO.updateReimbursement(reimb2, reimb.getId()));
        assertEquals(reimb2.getAuthorUserId(), reimbursementDAO.getReimbursementById(reimb.getId()).getAuthorUserId());
    }

    @Test
    @Order(5)
    void testDelete(){
        assertTrue(reimbursementDAO.deleteReimbursement(reimb.getId()));
        assertNull(reimbursementDAO.getReimbursementById(reimb.getId()));
    }
}
