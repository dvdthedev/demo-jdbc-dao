package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;
    public DepartmentDaoJDBC (Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement("INSERT INTO department " +
                    "(Name) " +
                    "VALUES " +
                    "(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.executeUpdate();

            } catch (SQLException e){
            throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
            }
    }

//    public void insert(Seller obj) {
//        PreparedStatement st = null;
//        try {
//            st = conn.prepareStatement("INSERT INTO seller " +
//                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
//                    "VALUES " +
//                    "(?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
//            st.setString(1, obj.getName());
//            st.setString(2, obj.getEmail());
//            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
//            st.setDouble(4, obj.getBaseSalary());
//            st.setInt(5, obj.getDepartment().getId());
//
//            int rowsAffected = st.executeUpdate();
//
//            if(rowsAffected > 0 ){
//                ResultSet rs = st.getGeneratedKeys();
//                if(rs.next()){
//                    int id = rs.getInt(1);
//                    obj.setId(id);
//                }
//                DB.closeResultSet(rs);
//            }
//            else {
//                throw new DbException("Unexpected error! No rows affected!");
//            }
//        } catch (SQLException e){
//            throw new DbException(e.getMessage());
//        } finally {
//            DB.closeStatement(st);
//
//        }


        @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department " +
                   "WHERE Id = ?");
             st.setInt(1, id);
             rs = st.executeQuery();
            if(rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
            } else
                return null;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }



    @Override
    public List<Department> findAll() {
        return List.of();
    }


    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department obj = new Department();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        return obj;
    }
}
