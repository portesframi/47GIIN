package vista;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.mycompany.viuproyecto.Conexion;
import controlador.DetalleLiquidacionControler;
import controlador.LiquidacionControler;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class VistaLiquidacion extends javax.swing.JFrame {

    /**
     * Creates new form VistaLiquidacion
     */
    LiquidacionControler lc = new LiquidacionControler();
    DetalleLiquidacionControler dlc = new DetalleLiquidacionControler();
    private Firestore db;

    /**
     * Permite crear una lista de nombre de liquidaciones
     */
    public static ArrayList<String> arrayValoresCmbLiquidacion = new ArrayList<>();

    /**
     * Inicializa la vista de liquidacion
     */
    public VistaLiquidacion() {
        initComponents();
        db = Conexion.getConnection(); //Aquí se establece la conexión con Firestore
        LiquidacionControler.cargarTablaLiquidacionTR(tblLiquidacion);
        txtId.setEditable(false);
        setTitle("Ventana para la gestión de las liquidaciones");
        this.setLocationRelativeTo(null);
        try {
            LiquidacionControler.cargarComboLiquidacion(cmbLiquidacion);

        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(VistaDetalleLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLiquidacion = new javax.swing.JTable();
        btnCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jFechaFin = new com.toedter.calendar.JDateChooser();
        jFechaInicio = new com.toedter.calendar.JDateChooser();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDetalle = new javax.swing.JTextArea();
        cmbLiquidacion = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnCrearDetalleLiq = new javax.swing.JButton();
        btnIngresarPeriodos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 510, -1, -1));

        jLabel2.setText("Fecha fin: *");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 110, -1));

        jLabel4.setText("Detalle:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, -1, -1));

        tblLiquidacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblLiquidacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLiquidacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLiquidacion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 810, 151));

        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, -1, -1));

        jLabel1.setText("Fecha inicio: *");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, -1));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, -1, -1));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, -1, -1));
        jPanel1.add(jFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 190, -1));
        jPanel1.add(jFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 190, -1));

        txtId.setForeground(new java.awt.Color(242, 242, 242));
        txtId.setBorder(null);
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 210, -1));

        txtAreaDetalle.setColumns(20);
        txtAreaDetalle.setRows(5);
        jScrollPane1.setViewportView(txtAreaDetalle);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 280, 150));

        cmbLiquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLiquidacionActionPerformed(evt);
            }
        });
        jPanel1.add(cmbLiquidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 190, -1));

        jLabel3.setText("Periodo liquidación: *");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 150, -1));

        btnCrearDetalleLiq.setText("Crear Detalle de Liquidación");
        btnCrearDetalleLiq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearDetalleLiqActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrearDetalleLiq, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 230, -1));

        btnIngresarPeriodos.setText("Ingresar Periodos");
        btnIngresarPeriodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarPeriodosActionPerformed(evt);
            }
        });
        jPanel1.add(btnIngresarPeriodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        lc.limpiarCamposLiquidacion(txtId, jFechaInicio, jFechaFin, txtAreaDetalle, cmbLiquidacion);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblLiquidacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLiquidacionMouseClicked
        int selection = tblLiquidacion.getSelectedRow();
        txtId.setText(tblLiquidacion.getValueAt(selection, 0) + "");
        String fechaInicioString = (String) tblLiquidacion.getValueAt(selection, 1);
        String fechaFinString = (String) tblLiquidacion.getValueAt(selection, 2);
        txtAreaDetalle.setText(tblLiquidacion.getValueAt(selection, 3) + "");

        // Obtener el valor de la celda de la tabla como String
// Definir el formato de fecha que se está utilizando en la tabla
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Convertir el String a un objeto Date utilizando el formato definido
            Date fechaInicio = dateFormat.parse(fechaInicioString);
            Date fechaFin = dateFormat.parse(fechaFinString);

            // Establecer la fecha en el JDateChooser
            jFechaInicio.setDate(fechaInicio);
            jFechaFin.setDate(fechaFin);
        } catch (ParseException ex) {
            // Manejar el error en caso de que el String no se pueda convertir a Date
            System.out.println("Error al convertir fecha: " + ex.getMessage());
        }

    }//GEN-LAST:event_tblLiquidacionMouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        //Capturamos
        Date fechaInicio = jFechaInicio.getDate();
        Date fechaFin = jFechaFin.getDate();
        String detalle = txtAreaDetalle.getText().trim();

        //Guardamos
        if (fechaInicio != null) {
            if (fechaFin != null) {
                if (!detalle.isEmpty()) {
                    if (lc.verificarSeleccionPeriodoLiquidacionVacio(cmbLiquidacion)) {
                        String periodoTrimestral = cmbLiquidacion.getSelectedItem().toString();
                        lc.guardarLiquidacion(fechaInicio, fechaFin, detalle, periodoTrimestral);
                        //Limpiamos
                        lc.limpiarCamposLiquidacion(txtId, jFechaInicio, jFechaFin, txtAreaDetalle, cmbLiquidacion);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el detalle");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione la fecha fin");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione la fecha inicio");
        }


    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Capturamos
        String idDoc = txtId.getText().trim();

        if (!idDoc.isEmpty()) {
            //Eliminamos
            lc.eliminarLiquidacion(idDoc);
            //Limpiamos
            lc.limpiarCamposLiquidacion(txtId, jFechaInicio, jFechaFin, txtAreaDetalle, cmbLiquidacion);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un ID de un dato de la tabla");
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //Capturamos
        String idDoc = txtId.getText().trim();
        Date fechaInicio = jFechaInicio.getDate();
        Date fechaFin = jFechaFin.getDate();
        String detalle = txtAreaDetalle.getText().trim();

        //Actualizamos
        if (!idDoc.isEmpty()) {
            if (fechaInicio != null) {
                if (fechaFin != null) {
                    if (!detalle.isEmpty()) {
                        if (lc.verificarSeleccionPeriodoLiquidacionVacio(cmbLiquidacion)) {
                            String periodoTrimestral = cmbLiquidacion.getSelectedItem().toString();
                            lc.actualizarLiquidacion(idDoc, fechaInicio, fechaFin, detalle, periodoTrimestral);
                            //Limpiamos
                            lc.limpiarCamposLiquidacion(txtId, jFechaInicio, jFechaFin, txtAreaDetalle, cmbLiquidacion);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el detalle");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione la fecha fin");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione la fecha inicio");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el ID de un dato de la tabla");
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCrearDetalleLiqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearDetalleLiqActionPerformed

        String periodoLiquidacion = cmbLiquidacion.getSelectedItem().toString();
        List<Map<String, Object>> documentosEncontradosGastos = new ArrayList<>(); // Lista para almacenar los documentos encontrados
        List<Map<String, Object>> documentosEncontradosSuscripcionServicio = new ArrayList<>(); // Lista para almacenar los documentos encontrados
        List<Map<String, Object>> documentosEncontradosPorIdServicio = new ArrayList<>(); // Lista para almacenar la unión de ambas por llave
        Map<String, Double> importesSumados = new HashMap<>();// SUMA EL IMPORTE
        List<Map<String, Object>> documentosSinRepetidosPorIDS = new ArrayList<>(); // Lista para almacenar la unión de ambas por llave SIN REPETICIÓN
        Set<String> clavesUnicas = new HashSet<>();//RECUPERA EL VALOR DE LOS IDS PARA CREAR UNA CLAVE UNICA

        if (lc.verificarSeleccionPeriodoLiquidacion(cmbLiquidacion)) {

            /*
            ESTA PARTE CONSULTA LOS DATOS EN LA COLECCION GASTOS Y TRAE LOS VALORES
            ENTRE ELLOS EL MAS INPORTANTE EL IDSERVICIO
             */
            try {
                CompletableFuture<List<Map<String, Object>>> datosGastosFuture = lc.consultarDatosGastosPorPeriodoLiquidacion(periodoLiquidacion);
                List<Map<String, Object>> datosGastos = datosGastosFuture.get();

                // Iterar sobre cada documento encontrado
                for (Map<String, Object> datos : datosGastos) {

                    // Crear un nuevo mapa para almacenar los campos específicos
                    Map<String, Object> mapDocumento = new HashMap<>();

                    // Obtener valores específicos del documento
                    String conceptoGasto = (String) datos.get("concepto");
                    String servicioGasto = (String) datos.get("servicio");
                    Double importeGasto = (Double) datos.get("importe");
                    String liquidacionGasto = (String) datos.get("liquidacion");
                    String idServicio = (String) datos.get("idServicio");

                    // Agregar los campos específicos al nuevo mapa
                    mapDocumento.put("concepto", conceptoGasto);
                    mapDocumento.put("servicio", servicioGasto);
                    mapDocumento.put("importe", importeGasto);
                    mapDocumento.put("liquidacion", liquidacionGasto);
                    mapDocumento.put("idServicio", idServicio);

                    // Agregar el nuevo mapa a la lista
                    documentosEncontradosGastos.add(mapDocumento);

                    // Hacer algo con los valores obtenidos
                    System.out.println("\nConcepto: " + conceptoGasto);
                    System.out.println("Servicio: " + servicioGasto);
                    System.out.println("Importe: " + importeGasto);
                    System.out.println("Liquidación: " + liquidacionGasto);
                    System.out.println("ID Servicio: " + idServicio);
                    System.out.println("---------------------\n");
                }

                // Acceder a los documentos encontrados a través de la lista
                for (Map<String, Object> documento : documentosEncontradosGastos) {
                    // Obtener valores específicos del documento
                    String conceptoGasto = (String) documento.get("concepto");
                    String servicioGasto = (String) documento.get("servicio");
                    Double importeGasto = (Double) documento.get("importe");
                    String liquidacionGasto = (String) documento.get("liquidacion");
                    String idServicio = (String) documento.get("idServicio");

                    // Hacer algo con los valores obtenidos
                    // ...
                    System.out.println("\nID SERVICIO ENCONTRADO EN EL MAP GASTOS GLOBAL: " + idServicio);
                    System.out.println("\nLIQUIDACION DESEADA EN EL MAP GASTOS GLOBAL: " + liquidacionGasto);
                }

                // Hacer algo con los valores obtenidos
                // ...
                for (Map<String, Object> documento : documentosEncontradosGastos) {
                    String idServicio = (String) documento.get("idServicio");

                    // Realizar consulta pausada utilizando el idServicio
                    CompletableFuture<List<Map<String, Object>>> consultaPausadaFuture = lc.consultarDatosSuscripcionServicioPorIdServicio(idServicio);

                    // Obtener los resultados de la consulta pausada
                    List<Map<String, Object>> datosSuscripcionServicio = consultaPausadaFuture.get();

                    // Iterar sobre cada documento de la consulta
                    for (Map<String, Object> datosSS : datosSuscripcionServicio) {
                        // Crear un nuevo mapa para almacenar los campos específicos
                        Map<String, Object> mapDocumentoSS = new HashMap<>();

                        // Obtener valores específicos del documento
                        String idInmueble = (String) datosSS.get("idInmueble");
                        String idServio = (String) datosSS.get("idServicio");
                        String idUsuario = (String) datosSS.get("idUsuario");
                        String Inmueble = (String) datosSS.get("inmueble");
                        String Servicio = (String) datosSS.get("servicio");
                        String Usuario = (String) datosSS.get("usuario");

                        // Agregar los campos específicos al nuevo mapa
                        mapDocumentoSS.put("idInmueble", idInmueble);
                        mapDocumentoSS.put("idServicio", idServio);
                        mapDocumentoSS.put("idUsuario", idUsuario);
                        mapDocumentoSS.put("inmueble", Inmueble);
                        mapDocumentoSS.put("servicio", Servicio);
                        mapDocumentoSS.put("usuario", Usuario);

                        // Agregar el nuevo mapa a la lista
                        documentosEncontradosSuscripcionServicio.add(mapDocumentoSS);

                        // Hacer algo con los valores obtenidos
                        System.out.println("\nSuscripcion Servico\n");
                        System.out.println("\nidInmueble: " + idInmueble);
                        System.out.println("idServicio: " + idServio);
                        System.out.println("idUsuario: " + idUsuario);
                        System.out.println("inmueble: " + Inmueble);
                        System.out.println("servicio: " + Servicio);
                        System.out.println("usuario: " + Usuario);
                        System.out.println("---------------------\n");
                    }

                    // Hacer algo con los resultados obtenidos
                    // ...
                    // Puedes guardar los resultados en otro mapa o realizar otras operaciones necesarias
                }

                // Acceder a los documentos encontrados a través de la lista
                for (Map<String, Object> mapSS : documentosEncontradosSuscripcionServicio) {
                    // Obtener valores específicos del documento
                    String idInmueble = (String) mapSS.get("idInmueble");
                    String idServio = (String) mapSS.get("idServicio");
                    String idUsuario = (String) mapSS.get("idUsuario");
                    String Inmueble = (String) mapSS.get("inmueble");
                    String Servicio = (String) mapSS.get("servicio");
                    String Usuario = (String) mapSS.get("usuario");

                    // Hacer algo con los valores obtenidos
                    // ...
                    System.out.println("\nUSUARIO CON ID SIMILAR ENCONTRADO EN EL MAP SS GLOBAL: " + Usuario);
                    System.out.println("\nID Servicio: " + idServio);
                }
                /*
                
                   CONSULTA TODOS LOS DATOS DE LA COLECCIÓN SuscripcionServicio 
            POR EL ID DEL DOCUMENTO SELECCIONADO EN EL COMBOBOX Suscripcion Servicio.
                 */
                System.out.println("\nMapa Entero en GASTOS: " + documentosEncontradosGastos);
                System.out.println("\nMapa Entero en SS: " + documentosEncontradosSuscripcionServicio);

                for (Map<String, Object> suscripcionServicio : documentosEncontradosSuscripcionServicio) {
                    String idServicioSuscripcion = (String) suscripcionServicio.get("idServicio");

                    // Buscar el elemento correspondiente en documentosEncontradosGastos
                    for (Map<String, Object> gasto : documentosEncontradosGastos) {
                        String idServicio = (String) gasto.get("idServicio");
                        String pl = (String) gasto.get("liquidacion");

                        // Comprobar si el idServicio coincide
                        if (pl.equals(periodoLiquidacion) && idServicio.equals(idServicioSuscripcion)) {
                            // Crear un nuevo mapa para almacenar los datos combinados
                            Map<String, Object> mapDocumento = new HashMap<>();

                            // Agregar los campos del gasto
                            mapDocumento.putAll(gasto);

                            // Agregar los campos de la suscripción de servicio
                            mapDocumento.putAll(suscripcionServicio);

                            // Agregar el mapa combinado a la lista
                            documentosEncontradosPorIdServicio.add(mapDocumento);

                            // No es necesario seguir buscando, salir del bucle interno
                            break;
                        }
                    }
                }

                System.out.println("\n\n-----------------------------------------");
                System.out.println("\n\nMapa Entero juntado por ID Servicio: " + documentosEncontradosPorIdServicio);

                for (Map<String, Object> documento : documentosEncontradosPorIdServicio) {
                    String idServicio = (String) documento.get("idServicio");
                    String idInmueble = (String) documento.get("idInmueble");
                    String idUsuario = (String) documento.get("idUsuario");
                    Double importe = (Double) documento.get("importe");

                    // Generar una clave única para identificar la combinación de idServicio, idInmueble e idUsuario
                    String clave = idServicio + "_" + idInmueble + "_" + idUsuario;

                    // Verificar si la clave ya existe en el mapa de importes sumados
                    if (importesSumados.containsKey(clave)) {
                        // Si la clave existe, sumar el importe al valor existente en el mapa
                        Double importeSumado = importesSumados.get(clave) + importe;
                        importesSumados.put(clave, importeSumado);

                        // No agregar el elemento a la lista, ya que se eliminará debido a ser repetido
                    } else {
                        // Si la clave no existe, agregar el importe al mapa de importes sumados y agregar el elemento a la lista
                        importesSumados.put(clave, importe);
                    }
                }

                List<Map<String, Object>> documentosSinRepetidos = new ArrayList<>();

                for (Map<String, Object> documento : documentosEncontradosPorIdServicio) {
                    String idServicio = (String) documento.get("idServicio");
                    String idInmueble = (String) documento.get("idInmueble");
                    String idUsuario = (String) documento.get("idUsuario");

                    // Generar la clave única para identificar la combinación de idServicio, idInmueble e idUsuario
                    String clave = idServicio + "_" + idInmueble + "_" + idUsuario;

                    // Verificar si la clave existe en el mapa de importes sumados
                    if (importesSumados.containsKey(clave)) {
                        // Agregar el importe sumado al documento
                        Double importeSumado = importesSumados.get(clave);
                        documento.put("importe", importeSumado);

                        // Agregar el documento a la lista
                        documentosSinRepetidosPorIDS.add(documento);
                    }
                }
                System.out.println("\n\n|||||||||||||||||||||||||||||||||||||||||");
                System.out.println("\n\nMapa Entero SIENDO SUMADO POR IDS USUARUI, SERVICIO E INMUEBLE: " + documentosSinRepetidosPorIDS);

                for (Map<String, Object> documento : documentosSinRepetidosPorIDS) {
                    String clave = documento.get("idServicio") + "_"
                            + documento.get("idInmueble") + "_"
                            + documento.get("idUsuario");

                    // Verificar si la clave ya existe en el conjunto de claves únicas
                    if (!clavesUnicas.contains(clave)) {
                        // Agregar la clave al conjunto
                        clavesUnicas.add(clave);

                        // Agregar el documento a la lista de documentos sin repetición
                        documentosSinRepetidos.add(documento);
                    }
                }

// Actualizar la lista original con los documentos sin repetición
                documentosSinRepetidosPorIDS = documentosSinRepetidos;

                System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("\n\nMapa Entero SIENDO SIN REPETIDOS Y CALCULADO POR IDENTIDAD UNICA: " + documentosSinRepetidosPorIDS);

                System.out.println("\nFIN. Procediendo a Guardar el Detalle de Liquidación");

                for (Map<String, Object> documento : documentosSinRepetidosPorIDS) {
                    String idInmueble = (String) documento.get("idInmueble");
                    String servicio = (String) documento.get("servicio");
                    String idUsuario = (String) documento.get("idUsuario");
                    String concepto = (String) documento.get("concepto");
                    String inmueble = (String) documento.get("inmueble");
                    String usuario = (String) documento.get("usuario");
                    String liquidacion = (String) documento.get("liquidacion");
                    String idServicio = (String) documento.get("idServicio");
                    Double importe = (Double) documento.get("importe");
                    String nombre = (String) documento.get("idInmueble") + "_" + documento.get("idUsuario") + "_" + documento.get("idServicio");

                    // Hacer algo con los valores obtenidos
                    // ...
                    System.out.println("\nValores encontrados en documentosSinRepetidosPorIDS:");
                    System.out.println("idInmueble: " + idInmueble);
                    System.out.println("servicio: " + servicio);
                    System.out.println("idUsuario: " + idUsuario);
                    System.out.println("concepto: " + concepto);
                    System.out.println("inmueble: " + inmueble);
                    System.out.println("usuario: " + usuario);
                    System.out.println("liquidacion: " + liquidacion);
                    System.out.println("idServicio: " + idServicio);
                    System.out.println("importe: " + importe);
                    System.out.println("Nombre Clave : " + nombre);

                    dlc.guardarDetalleLiquidacion(
                            idInmueble, inmueble,
                            idUsuario, usuario,
                            idServicio, servicio,
                            nombre, importe,
                            concepto, periodoLiquidacion);
                }
                //SI ENCUENTRA EL PERIODO DE LIQUIDACIÓN Y HA LLEGADO
                //A DEJAR LA LISTA DE MAPS CALCULADO Y SIN ELEMENTOS REPETIDOS
                //SOLO QUEDA HACER EL CICLO Y GUARDAR CADA VALOR SIN ERROR ALGUNO
                //ENTONCES...
                JOptionPane.showMessageDialog(null, "Detalle de Liquidación guardado exitosamente");

            } catch (ExecutionException e) {
                if (e.getCause() instanceof RuntimeException) {
                    System.out.println("No se encontró ningún documento con el periodo de liquidación: " + periodoLiquidacion);
                } else {
                    System.err.println("Error al obtener los datos de gastos: " + e.getMessage());
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(VistaLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnCrearDetalleLiqActionPerformed

    private void cmbLiquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLiquidacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLiquidacionActionPerformed

    private void btnIngresarPeriodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarPeriodosActionPerformed
        cmbLiquidacion.removeAllItems();
        arrayValoresCmbLiquidacion.clear();
        DialogIngresarDatosCmbLiquidacion didcl = new DialogIngresarDatosCmbLiquidacion(this, true);
        didcl.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnIngresarPeriodosActionPerformed

    /** the command line arguments
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaLiquidacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCrear;
    public static javax.swing.JButton btnCrearDetalleLiq;
    public static javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIngresarPeriodos;
    public static javax.swing.JButton btnLimpiar;
    public static javax.swing.JButton btnModificar;
    public static javax.swing.JComboBox<String> cmbLiquidacion;
    public static com.toedter.calendar.JDateChooser jFechaFin;
    public static com.toedter.calendar.JDateChooser jFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblLiquidacion;
    public static javax.swing.JTextArea txtAreaDetalle;
    public static javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
