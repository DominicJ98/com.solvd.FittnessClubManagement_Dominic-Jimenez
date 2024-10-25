package service;

import dao.GenericDAO;
import model.Member;

import java.sql.SQLException;
import java.util.List;

public class MemberService {
    private GenericDAO<Member, Integer> memberDAO;

    public MemberService(GenericDAO<Member, Integer> memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void addMember(Member member) throws SQLException {
        memberDAO.insert(member);
    }

    public Member getMemberById(Integer id) throws SQLException {
        return memberDAO.getById(id);
    }

    public List<Member> getAllMembers() throws SQLException {
        return memberDAO.getAll();
    }

    public void updateMember(Member member) throws SQLException {
        memberDAO.update(member);
    }

    public void deleteMember(Integer id) throws SQLException {
        memberDAO.delete(id);
    }
}
