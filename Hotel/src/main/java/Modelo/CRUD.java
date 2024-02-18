/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CRUD {

    public void guardar_cliente(Connection conexion, Cliente cliente) throws SQLException {
        try {
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("INSERT INTO cliente (cedula, nombre, apellido, direccion, telefono, pais, correo, contacto ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            consulta.setInt(1, cliente.getCedula());
            consulta.setString(2, cliente.getNombre());
            consulta.setString(3, cliente.getApellido());
            consulta.setString(4, cliente.getDireccion());
            consulta.setString(5, cliente.getTelefono());
            consulta.setString(6, cliente.getPais());
            consulta.setString(7, cliente.getCorreo());
            consulta.setString(8, cliente.getContacto());
            int respuesta;
            respuesta = consulta.executeUpdate();
            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente");
            }
            System.out.println("Hizo el guardar");
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void eliminar_por_codigo_cliente(Connection conexion, int cedula) {
        try {
            PreparedStatement consulta;
            System.out.println("entró al eliminar");
            consulta = conexion.prepareStatement("delete from cliente where cedula = ?");
            consulta.setInt(1, cedula);
            int respuesta;
            respuesta = consulta.executeUpdate();
            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            }
            System.out.println("Hizo el eliminar");
        } catch (SQLException ex) {
            System.out.println("el error en el eliminar es: " + ex.getMessage());
        }
    }

    public void actualizar_clientes(Connection conexion, Cliente cliente) throws SQLException {
        try {
            PreparedStatement consulta;
            int cedula = cliente.getCedula();
            String nombre1 = cliente.getNombre();
            String apellido1 = cliente.getApellido();
            String direccion1 = cliente.getDireccion();
            String telefono1 = cliente.getTelefono();
            String pais1 = cliente.getPais();
            String correo1 = cliente.getCorreo();
            String contacto1 = cliente.getContacto();

            String actualizar = "UPDATE cliente set nombre= " + "'" + nombre1 + "'" + "," + "apellido= " + "'" + apellido1 + "'" + "," + "direccion= " + "'" + direccion1 + "'" + "," + "telefono= " + "'" + telefono1 + "'" + "," + "pais = " + "'" + pais1 + "'" + "," + "correo= " + "'" + correo1 + "'" + "," + "contacto= " + "'" + contacto1 + "'" + "where cedula = " + cedula;
            consulta = conexion.prepareStatement(actualizar);
            System.out.println("este es el string de actualizar " + actualizar);

            int respuesta;
            respuesta = consulta.executeUpdate();
            if (respuesta > 0) {
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            }
            System.out.println("Hizo el actualizar");
        } catch (SQLException ex) {
            System.out.println("el error en el actualizar es: " + ex.getMessage());
        }
    }
    public DefaultTableModel llamar_clientes(Connection conexion) {
        DefaultTableModel DT2 = new DefaultTableModel();
        ResultSet RS2;

        DT2.addColumn("Cedula");
        DT2.addColumn("Nombre");
        DT2.addColumn("Apellido");
        DT2.addColumn("Dirección");
        DT2.addColumn("Teléfono");
        DT2.addColumn("País");
        DT2.addColumn("Correo");
        DT2.addColumn("Contacto");
        try {
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("select cedula, nombre, apellido, direccion, telefono, pais, correo, contacto from cliente");
            RS2 = consulta.executeQuery();
            Object[] fila2 = new Object[8];

            while (RS2.next()) {
                fila2[0] = RS2.getInt(1);
                fila2[1] = RS2.getString(2);
                fila2[2] = RS2.getString(3);
                fila2[3] = RS2.getString(4);
                fila2[4] = RS2.getString(5);
                fila2[5] = RS2.getString(6);
                fila2[6] = RS2.getString(7);
                fila2[7] = RS2.getString(8);
                DT2.addRow(fila2);
            }
            return DT2;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos: " + e.getMessage());
            return DT2;
        }

    }
}
