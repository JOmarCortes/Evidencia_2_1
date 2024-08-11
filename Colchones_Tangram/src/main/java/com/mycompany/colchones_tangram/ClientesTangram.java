package com.mycompany.colchones_tangram;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class ClientesTangram {
    
    int codigo;
    String nombresCliente;
    String apellidosCliente;
    String celularCliente;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }
    
    public void InsertarCliente(JTextField paramNombres, JTextField paramApellidos, JTextField paramCelular){
        
        setNombresCliente (paramNombres.getText());
        setApellidosCliente (paramApellidos.getText());
        setCelularCliente (paramCelular.getText());
        
        CConexion objetoConexion = new CConexion();
        
       String consulta ="INSERT INTO Clientes (nombres, apellidos, celular) VALUES (?,?,?);";
       
       try {
           CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
           
           cs.setString(1, getNombresCliente ());
           cs.setString(2, getApellidosCliente ());           
           cs.setString(3, getCelularCliente ());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "Se inserto correctamente el cliente");
           
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el cliente, error: "+e.toString());
       
        }
       
             
        }
        public void MostrarClientes (JTable paramtbclientes) {

        CConexion objetoConexion = new CConexion ();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<>(modelo);
        paramtbclientes.setRowSorter (OrdenarTabla);

        String sql="";

        modelo.addColumn ("Id");
        modelo.addColumn ("Nombres") ;
        modelo.addColumn ("Apellidos");
        modelo.addColumn ("Celular");
        
        paramtbclientes.setModel(modelo);
        
        sql = "SELECT * FROM Clientes;";
        
        String[] datos = new String[4];
        Statement st;

        try {
        st= objetoConexion.estableceConexion().createStatement() ;

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

        datos[0]=rs.getString(1);
        datos[1]=rs.getString(2);
        datos[2]=rs.getString(3);
        datos[3]=rs.getString(4);
        
        modelo.addRow(datos);
        
        }
        
        paramtbclientes.setModel (modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: "+ e.toString());
            
        }
        

        }
        
        public void SeleccionarCliente(JTable paramtbclientes, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramCelular){
        
        try {
            int fila = paramtbclientes.getSelectedRow();
            
            if (fila>=0){
            
            paramId.setText((paramtbclientes.getValueAt (fila,0).toString()));
            paramNombres.setText((paramtbclientes.getValueAt (fila,1).toString()));
            paramApellidos.setText((paramtbclientes.getValueAt (fila,2).toString()));
            paramCelular.setText((paramtbclientes.getValueAt (fila,3).toString()));
            
                       
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        }catch (Exception e) {
            JOptionPane. showMessageDialog(null, "Error de seleccion, error: "+ e.toString());
    }
        
        
    }
        public void ModificarCliente (JTextField txtide, JTextField paramNombres, JTextField paramApellidos, JTextField paramCelular){
            
            setCodigo (Integer.parseInt(txtide.getText()));
            setNombresCliente (paramNombres.getText ());
            setApellidosCliente (paramApellidos.getText ());
            setCelularCliente (paramCelular.getText ());
            
            CConexion objetoConexion = new CConexion() ;

            String consulta = "UPDATE Clientes SET Clientes.nombres = ?, Clientes.apellidos = ?, Clientes.celular = ? WHERE Clientes.id =?;";
            

            try {

            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, getNombresCliente()) ;
            cs.setString(2, getApellidosCliente()) ;
            cs.setString(3, getCelularCliente()) ;
            cs.setInt(4, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "No se modifico error:"+ e.toString());

            }
        
        }
        
        public void EliminarCliente (JTextField  paramCodigo){
            
            setCodigo(Integer.parseInt(paramCodigo.getText()));
            
            CConexion objetoConexion = new CConexion() ;
            
            String consulta = "DELETE FROM Clientes WHERE Clientes.id=?;";
            
            try {

            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setInt(1, getCodigo()) ;
                       
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Eliminacion exitosa");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "No se elimino, error:"+ e.toString());

            }

        }
    }

    
    
 
       
   
