package com.alex.database;

import java.sql.*;

public final class DatabaseClassLoader extends ClassLoader {
    private ClassLoader parent;
    private String connectionString;

    public DatabaseClassLoader(String connectionString) {
        this(ClassLoader.getSystemClassLoader(), connectionString);
    }

    private DatabaseClassLoader(ClassLoader parent, String connectionString) {
        super(parent);
        this.parent = parent;
        this.connectionString = connectionString;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> cls = null;
        try {
            cls = parent.loadClass(name);
        } catch (ClassNotFoundException e) {
            try {
                final byte[] bytes = loadClassFromDatabase(name);
                cls = defineClass(name, bytes, 0, bytes.length);
            } catch (SQLException sqle) {
                throw new ClassNotFoundException("Unable to load class", sqle);
            }
        }

        return cls;
    }

    private byte[] loadClassFromDatabase(String name) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = null;
        Connection connection = null;
        byte[] data = null;
        try {
            connection = DriverManager.getConnection(connectionString);

            String sql = "select class from Classes where className= ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob(1);
                data = blob.getBytes(1, (int) blob.length());
                return data;
            }
        } catch (SQLException sqlex) {
            System.out.println("Unexpected exception: " + sqlex.toString());
        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.toString());
        } finally {
            if (pstmt != null) pstmt.close();
            if (connection != null) connection.close();
        }

        if (data == null) {
            throw new ClassNotFoundException();
        }
        return data;
    }
}
