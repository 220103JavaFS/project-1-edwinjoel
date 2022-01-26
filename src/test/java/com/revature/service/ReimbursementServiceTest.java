package com.revature.service;

import com.revature.model.*;
import com.revature.repository.ReimbursementDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReimbursementServiceTest {

    private ReimbursementService testService;

    @Mock
    private ReimbursementDAO testDAO;

    private ReimbursementDTO reimb = new ReimbursementDTO();



    @BeforeEach
    public void setup(){
        reimb.setAmount(100);
        User jimUser = new User(10000,"itsJim", "password".getBytes(StandardCharsets.UTF_8), "Jim" , "Lee", "jimlee@gmail.com", UserRole.EMPLOYEE);
        User billUser = new User(10001,"itsBill", "password1".getBytes(StandardCharsets.UTF_8), "Bill" , "Brown", "billbrown@gmail.com", UserRole.MANAGER);
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

        MockitoAnnotations.openMocks(this);
        testService = new ReimbursementService(testDAO);

        Mockito.when(testDAO.getReimbursementById(20000)).thenReturn(reimb);

    }

    @Test
    public void testGetReimbursementSuccess(){
        ReimbursementDTO reimb = testDAO.getReimbursementById(20000);
        assertEquals("Jim", reimb.getAuthorUser().getFirstName());
        assertEquals(20000, reimb.getId());
        assertEquals(Status.PENDING, reimb.getStatus());
    }

    @Test
    public void testGetReimbursementFail(){
        ReimbursementDTO reimb = testDAO.getReimbursementById(60000);
        assertNull(reimb);
    }

    @Test
    public void testGetReimbursementNegativeAndZero(){
        ReimbursementDTO reimb = testDAO.getReimbursementById(-1000);
        assertNull(reimb);
        ReimbursementDTO reimb2 = testDAO.getReimbursementById(0);
        assertNull(reimb2);

        ArrayList<ReimbursementDTO> reimbList = testDAO.getAllReimbursementsByAuthor(-1000);
        assertEquals(0, reimbList.size());
        ArrayList<ReimbursementDTO> reimbList2 = testDAO.getAllReimbursementsByAuthor(0);
        assertEquals(0, reimbList2.size());

        ArrayList<ReimbursementDTO> reimbList3 = testDAO.getAllReimbursementsByResolver(-1000);
        assertEquals(0, reimbList3.size());
        ArrayList<ReimbursementDTO> reimbList4= testDAO.getAllReimbursementsByResolver(0);
        assertEquals(0, reimbList4.size());

    }

    @Test
    public void testGetReimbursementByStatusNull(){
        ArrayList<ReimbursementDTO> reimbList = testDAO.getAllReimbursementsByStatus(null);
        assertEquals(0, reimbList.size());
    }

    @Test
    public void testDeleteReimbursementNegativeZero(){
        boolean result = testDAO.deleteReimbursement(-1000);
        assertFalse(result);
        boolean result2 = testDAO.deleteReimbursement(0);
        assertFalse(result2);
    }


}
