package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;
    public DepartmentDaoJDBC (Connection conn){
        this.conn = conn;
    }

    @Override
    public Department insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department (Name) VALUES (?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        obj.setId(id);
                        DB.closeResultSet(rs);
                    }
                }
            } else {
                throw new DbException("Nenhuma linha afetada, não foi possível inserir o departamento.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        return obj;
    }


        @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE Department " +
                    "SET name = ? " + "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rowsAffected =  st.executeUpdate();
            System.out.println(obj);
            System.out.println(rowsAffected);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
            try {
                st = conn.prepareStatement("DELETE FROM Department WHERE Id = ?");
                st.setInt(1, id);
                st.executeUpdate();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
            }
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * From Department", Statement.RETURN_GENERATED_KEYS);
            rs = st.executeQuery();
            List<Department> departments = new ArrayList<>();
            while (rs.next()){
                Department dep = new Department(rs.getString(2));
                dep.setId(rs.getInt(1));

                departments.add(dep);
            }
            return departments;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department obj = new Department();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        return obj;
    }
}
