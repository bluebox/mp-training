package com.medplus.test.dao;

import com.medplus.dao.impl.MemberDAOImpl;
import com.medplus.model.Member;
import org.junit.*;
import java.util.*;

public class MemberDAOTest {
    private MemberDAOImpl dao;
    @Before
    public void setUp() { dao = new MemberDAOImpl(); }
    @Test
    public void testRegisterMember() throws Exception {
        Member m = new Member(0, "Sai", "sai@gmail.com", 999999999, 'M', "Hyd");
        Assert.assertTrue(dao.addMember(m));
    }
    @Test
    public void testGetAllMembers() throws Exception {
        Assert.assertNotNull(dao.getAllMembers());
    }
    @Test
    public void testUpdateMember() throws Exception {
        Member m = dao.getMemberById(1);
        m.setAddress("Updated Address");
        Assert.assertTrue(dao.updateMember(m));
    }
    @Test
    public void testGetMemberById() throws Exception {
        Assert.assertNotNull(dao.getMemberById(1));
    }
}