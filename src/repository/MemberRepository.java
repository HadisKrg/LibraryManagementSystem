package repository;

import model.Member;
import Exception.MemberNotFoundException;
import util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemberRepository {
    public Member registerMember(Member member) throws SQLException{
        String insert="insert into member(full_name, phone) values (?,?);";
        try ( Connection connection= DatabaseConfig.getConnection();
              PreparedStatement statement=connection.prepareStatement(insert)){
            statement.setString(1,member.getFullName());
            statement.setString(2,member.getPhone());
            statement.executeUpdate();
            return member;
        }catch (SQLException e){
            throw e;
        }
    }

    public Member findById(int id) throws SQLException {
        String findMember="select * from member where id=?";
        try (Connection connection=DatabaseConfig.getConnection();
             PreparedStatement statement=connection.prepareStatement(findMember)){
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                Member member=new Member(
                        resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("phone")
                );
                return member;
            }else throw new MemberNotFoundException("Member not found");
        }catch (SQLException e){
            throw e;
        }
    }

    public void deleteMember(int id) throws SQLException {
        String delete="delete from member where id=?;";
        try (Connection connection=DatabaseConfig.getConnection();
             PreparedStatement statement=connection.prepareStatement(delete)){
            findById(id);
            statement.setInt(1,id);
            int result=statement.executeUpdate();
            if (result > 0){
                System.out.println("deleted");}
        }catch (SQLException e){
           throw e;
        }

    }
}
