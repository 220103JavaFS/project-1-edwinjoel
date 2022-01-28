package com.revature.repository;

import com.revature.model.*;
import org.junit.jupiter.api.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReimbursementDAOImplTest {
    private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    public static ReimbursementDTO reimb = new ReimbursementDTO();
    public static ReimbursementDTO reimb2 = new ReimbursementDTO();

    @BeforeEach
    public void setup(){
        reimb.setAmount(100);
        User jimUser = new User(10000,"itsJim", "password".getBytes(StandardCharsets.UTF_8), "Jim" , "Lee", "jimlee@gmail.com",1, UserRole.EMPLOYEE);
        User billUser = new User(10001,"itsBill", "password1".getBytes(StandardCharsets.UTF_8), "Bill" , "Brown", "billbrown@gmail.com",2, UserRole.MANAGER);
        reimb.setAuthorUser(jimUser);
        reimb.setAuthorUserId(jimUser.getUserId());
        reimb.setResolverUser(billUser);
        reimb.setResolverUserId(billUser.getUserId());
        reimb.setDescription("need money please");
        reimb.setId(20000);
        reimb.setStatus(Status.PENDING);
        reimb.setStatusId(1);
        reimb.setType(Type.OTHER);
        reimb.setTypeId(4);
        reimb.setTimeSubmitted(new Timestamp(System.currentTimeMillis()));

        reimb2.setAmount(200);
        User jimUser2 = new User(10101,"itsJim2", "password2".getBytes(StandardCharsets.UTF_8), "Jim" , "Lee", "jimlee@gmail.com",1, UserRole.EMPLOYEE);
        User billUser2 = new User(10102,"itsBill2", "password12".getBytes(StandardCharsets.UTF_8), "Bill" , "Brown", "billbrown@gmail.com",1, UserRole.MANAGER);
        reimb2.setAuthorUser(jimUser2);
        reimb2.setAuthorUserId(jimUser2.getUserId());
        reimb2.setResolverUser(billUser2);
        reimb2.setResolverUserId(billUser2.getUserId());
        reimb2.setDescription("need money please");
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
    void testGetReimbursement(){
        assertEquals(reimb, reimbursementDAO.getAllReimbursementsByAuthor(reimb.getAuthorUserId()));
    }

    @Test
    @Order(3)
    void testUpdate(){
        assertTrue(reimbursementDAO.updateReimbursement(reimb2, reimb.getId()));
        assertEquals(reimb2.getAuthorUserId(), reimbursementDAO.getReimbursementById(reimb.getId()).getAuthorUserId());
    }

    @Test
    @Order(4)
    void testDelete(){
        assertTrue(reimbursementDAO.deleteReimbursement(reimb.getId()));
        assertNull(reimbursementDAO.getReimbursementById(reimb.getId()));
    }
}
