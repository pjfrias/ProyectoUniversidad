/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Connection.AlumnoData;
import Connection.InscripcionData;
import java.util.ArrayList;
import Entidades.Alumno;
import Entidades.Inscripcion;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 54265
 */
public class JIFNotas extends javax.swing.JInternalFrame {

    private List<Alumno> alumnos = new ArrayList<>();
    private Alumno alumnoComun = new Alumno();
    private AlumnoData alumno = new AlumnoData();
    private List<Inscripcion> inscripciones = new ArrayList<>();
    private DefaultTableModel modelo = new DefaultTableModel(){
        private static final long serialVersionUID = 1L;
        @Override
        public boolean isCellEditable(int fila, int columna) {
            // Hacer que solo la tercera columna sea editable
            return columna == 2;
        }
    };   
    private InscripcionData inscripcion = new InscripcionData();
    
    
    public JIFNotas() {
        initComponents();
        
        cargarAlumnos();
        
        modelo.addColumn("ID Materia");
        modelo.addColumn("Nombre");
        modelo.addColumn("Nota");
        jTNotas.setModel(modelo);
        
//        modelo.addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent tme) {
//                if (jTNotas.isEditing()) {
//                    JOptionPane.showMessageDialog(null, "Entro al JTNotas.isEditing(). Nota actual "+
//                            modelo.getValueAt((Integer) jTNotas.getSelectedRow(), (Integer) jTNotas.getSelectedColumn()));
//                 }
//            }
//        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBAlumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNotas = new javax.swing.JTable();
        jBSalir = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Actualizacion de Notas");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Actualizacion de Notas");

        jLabel2.setText("Seleccione un alumno:");

        jCBAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAlumnoActionPerformed(evt);
            }
        });

        jTNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTNotas);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jCBAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBSalir)
                    .addComponent(jBGuardar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jCBAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAlumnoActionPerformed
        limpiarTabla();
        cargarTabla();
    }//GEN-LAST:event_jCBAlumnoActionPerformed
    
    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
       
        if (validarNotas()){
            for (int i = 0; i < jTNotas.getRowCount(); i++) {
                inscripcion.actualizarNota(alumnoComun.getIdAlumno(), (Integer) jTNotas.getValueAt(i, 0), Double.parseDouble(""+jTNotas.getValueAt(i, 2)));
            }
        }else JOptionPane.showMessageDialog(null, "No se pudo actualizar la nota");
    }//GEN-LAST:event_jBGuardarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<Alumno> jCBAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTNotas;
    // End of variables declaration//GEN-END:variables
    
    private void cargarAlumnos(){
        alumnos = alumno.listarAlumnos();
        
        for (Alumno alu : alumnos) {
            jCBAlumno.addItem(alu);
        }
    }
    
    private void limpiarTabla(){
        for (int i = modelo.getRowCount()-1; i >= 0 ; i--) {
            modelo.removeRow(i);
        }
    }
    
    private void cargarTabla(){
        alumnoComun = (Alumno) jCBAlumno.getSelectedItem();
        inscripciones = inscripcion.obtenerInscripcionesPorAlumno(alumnoComun.getIdAlumno());
        for (Inscripcion ins : inscripciones) {
            modelo.addRow(new Object[]{ins.getMateria().getIdMateria(),
                                        ins.getMateria().getNombre(),
                                        ins.getNota()
                                        });
        }
    }
    
    private boolean validarNotas(){
        Double nota;
        for (int i = 0; i < jTNotas.getRowCount(); i++) {
            try {
                nota = Double.parseDouble(""+jTNotas.getValueAt(i, 2));
                
                if(nota < 0.0 || nota > 10.0){
                    JOptionPane.showMessageDialog(null, "La nota de la materia "+jTNotas.getValueAt(i, 1)+" no es valida");
                    return false;
                }
                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "La nota de la materia "+jTNotas.getValueAt(i, 1)+" no es valida");
                return false;
            }
        }
        return true;
    }
}
