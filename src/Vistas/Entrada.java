/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Componentes.ManejoArchivo;
import Componentes.Sistema;
import Componentes.TablaDistribuciones;
import Funciones.Alerta;
import Funciones.Numerico;
import javax.swing.JTextField;

public class Entrada extends javax.swing.JFrame {

  private Salida salida;
  private TablaDistribuciones tablaLlegadasM = new TablaDistribuciones();
  private TablaDistribuciones tablaLlegadasA = new TablaDistribuciones();
  private TablaDistribuciones tablaServidoresA = new TablaDistribuciones();
  private TablaDistribuciones tablaServidoresM = new TablaDistribuciones();
  private TablaDistribuciones tablaLlegada = new TablaDistribuciones();
  public static final int DIA = 1;
  public static final int MES = 30;
  public static final int SEMANA = 7;
  public static final int YEAR = 360;
  private int valorDuracion;
  private int valorNumEtapas;
  private int valorCostoCliente;
  private int valorCostoServidor;
  private int valorCostoOperacion;
  private int valorCostoOperacionExtra;
  private int seleccion;

  public Entrada() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    this.salida = new Salida();
    buttonGroup1 = new javax.swing.ButtonGroup();
    buttonGroup2 = new javax.swing.ButtonGroup();
    jPanel1 = new javax.swing.JPanel();
    titulo = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    jLabel13 = new javax.swing.JLabel();
    jLabel14 = new javax.swing.JLabel();
    jLabel15 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jLabel17 = new javax.swing.JLabel();
    jLabel18 = new javax.swing.JLabel();
    jLabel12.setVisible(false);
    jLabel13.setVisible(false);
    jLabel15.setVisible(false);
    jLabel16.setVisible(false);
    manualLlegada = new javax.swing.JRadioButton();
    archivoLlegada = new javax.swing.JRadioButton();
    manualServicios = new javax.swing.JRadioButton();
    archivoServicio = new javax.swing.JRadioButton();
    simular = new javax.swing.JButton();
    archivoLlegada.setSelected(true);
    archivoServicio.setSelected(true);
    valorLlegada = new javax.swing.JTextField();
    valorServidor = new javax.swing.JTextField();
    tiempoSimulacion = new javax.swing.JTextField();
    unidadCostoEsperaCliente = new javax.swing.JLabel();
    unidadCostoExtra = new javax.swing.JLabel();
    unidadCostoServidor = new javax.swing.JLabel();
    unidadCostoSistema = new javax.swing.JLabel();
    porcentajeLlegada = new javax.swing.JTextField();
    porcentajeServidor = new javax.swing.JTextField();
    agregarLlegada = new javax.swing.JButton();
    agregarServidor = new javax.swing.JButton();
    agregarLlegada.setVisible(false);
    agregarServidor.setVisible(false);
    porcentajeLlegada.setVisible(false);
    porcentajeServidor.setVisible(false);
    valorLlegada.setVisible(false);
    valorServidor.setVisible(false);
    tablaLlegadasArchivo = new javax.swing.JTextArea();
    tablaLlegadasManual = new javax.swing.JTextArea();
    tablaServidoresArchivo = new javax.swing.JTextArea();
    tablaServidoresManual = new javax.swing.JTextArea();
    leerArchivoServidor = new javax.swing.JButton();
    leerArchivoLlegada = new javax.swing.JButton();
    costoCliente = new javax.swing.JTextField();
    costoServidor = new javax.swing.JTextField();
    costoOperacion = new javax.swing.JTextField();
    costoOperacionExtra = new javax.swing.JTextField();
    duracion = new javax.swing.JTextField();
    maxServidores = new javax.swing.JTextField();
    maxClientes = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    jScrollPane2 = new javax.swing.JScrollPane();
    jScrollPane3 = new javax.swing.JScrollPane();
    jScrollPane4 = new javax.swing.JScrollPane();
    jScrollPane2.setVisible(true);
    jScrollPane4.setVisible(true);
    unidadLabel = new javax.swing.JLabel();
    unidad = new javax.swing.JComboBox<>();
    unidadLabel2 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);

    jPanel1.setBackground(new java.awt.Color(232, 225, 225));
    jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    titulo.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
    titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    titulo.setText("Simulador de Colas");
    jPanel1.add(
      titulo,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 790, 42)
    );

    jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabel1.setText("Di Mella, D. Bermudez, D");
    jPanel1.add(
      jLabel1,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, 11)
    );

    jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabel2.setText("Ingeniería Informatica");
    jPanel1.add(
      jLabel2,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 15)
    );

    jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel3.setText("Unidad de tiempo a utilizar");
    jPanel1.add(
      jLabel3,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1)
    );

    jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel4.setText("Duración de la simulación");
    jPanel1.add(
      jLabel4,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1)
    );

    jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel9.setText("Cantidad maxima de clientes");
    jPanel1.add(
      jLabel9,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1)
    );
    jPanel1.add(
      maxClientes,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 42, -1)
    );

    jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel11.setText("Cantidad de servidores");
    jPanel1.add(
      jLabel11,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1)
    );
    jPanel1.add(
      maxServidores,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 42, -1)
    );

    jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel6.setText("Costo de espera del cliente");
    jPanel1.add(
      jLabel6,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1)
    );

    unidadCostoEsperaCliente.setFont(new java.awt.Font("Roboto Mono", 1, 15)); // NOI18N
    unidadCostoEsperaCliente.setForeground(new java.awt.Color(0, 0, 0));
    unidadCostoEsperaCliente.setText("$ por Segundo");
    jPanel1.add(
      unidadCostoEsperaCliente,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, -1)
    );

    jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel7.setText("Costo por servidor");
    jPanel1.add(
      jLabel7,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1)
    );

    unidadCostoServidor.setFont(new java.awt.Font("Roboto Mono", 1, 15)); // NOI18N
    unidadCostoServidor.setForeground(new java.awt.Color(0, 0, 0));
    unidadCostoServidor.setText("$ por Segundo");
    jPanel1.add(
      unidadCostoServidor,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1)
    );

    jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel17.setText("Costo por uso normal del sistema");
    jPanel1.add(
      jLabel17,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1)
    );

    unidadCostoSistema.setFont(new java.awt.Font("Roboto Mono", 1, 15)); // NOI18N
    unidadCostoSistema.setForeground(new java.awt.Color(0, 0, 0));
    unidadCostoSistema.setText("$ por Segundo");
    jPanel1.add(
      unidadCostoSistema,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, -1, -1)
    );

    jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    jLabel18.setText("Costo por operacion extra del sistema ");
    jPanel1.add(
      jLabel18,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1)
    );

    unidadCostoExtra.setFont(new java.awt.Font("Roboto Mono", 1, 15)); // NOI18N
    unidadCostoExtra.setForeground(new java.awt.Color(0, 0, 0));
    unidadCostoExtra.setText("$ por Segundo");
    jPanel1.add(
      unidadCostoExtra,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, -1, -1)
    );

    jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel8.setText("Tiempos entre llegadas");
    jPanel1.add(
      jLabel8,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, 20)
    );

    manualLlegada.setBackground(new java.awt.Color(232, 225, 225));
    buttonGroup1.add(manualLlegada);
    manualLlegada.setText("Manual");
    jPanel1.add(
      manualLlegada,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, -1, -1)
    );
    manualLlegada.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          leerArchivoLlegada.setVisible(false);
          jScrollPane1.setVisible(false);
          jLabel12.setVisible(true);
          jLabel13.setVisible(true);
          agregarLlegada.setVisible(true);
          porcentajeLlegada.setVisible(true);
          valorLlegada.setVisible(true);
          jScrollPane2.setVisible(true);
        }
      }
    );

    archivoLlegada.setBackground(new java.awt.Color(232, 225, 225));
    buttonGroup1.add(archivoLlegada);
    archivoLlegada.setText("Archivo");
    jPanel1.add(
      archivoLlegada,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, -1, -1)
    );
    archivoLlegada.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          jLabel12.setVisible(false);
          jLabel13.setVisible(false);
          jScrollPane2.setVisible(false);
          porcentajeLlegada.setVisible(false);
          valorLlegada.setVisible(false);
          agregarLlegada.setVisible(false);
          leerArchivoLlegada.setVisible(true);
          jScrollPane1.setVisible(true);
        }
      }
    );

    leerArchivoLlegada.setText("Abrir archivo");
    leerArchivoLlegada.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          leerArchivoLlegadaActionPerformed(evt);
        }
      }
    );

    jPanel1.add(
      leerArchivoLlegada,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, -1, 20)
    );

    jPanel1.add(
      jScrollPane1,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 570, 120)
    );
    jPanel1.add(
      jScrollPane2,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 570, 120)
    );

    jLabel12.setText("Valor:");
    jPanel1.add(
      jLabel12,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, -1, -1)
    );
    jPanel1.add(
      valorLlegada,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 38, -1)
    );

    jLabel13.setText("Porcentaje:");
    jPanel1.add(
      jLabel13,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, -1, -1)
    );
    jPanel1.add(
      porcentajeLlegada,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, 42, -1)
    );

    agregarLlegada.setText("Agregar");
    jPanel1.add(
      agregarLlegada,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 90, 20)
    );
    agregarLlegada.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          agregarLlegadaActionPerformed(evt);
        }
      }
    );

    //Tiempo de servicios

    jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel14.setText("Tiempos de Servicios");
    jPanel1.add(
      jLabel14,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 630, -1, 20)
    );

    manualServicios.setBackground(new java.awt.Color(232, 225, 225));
    buttonGroup2.add(manualServicios);
    manualServicios.setText("Manual");
    jPanel1.add(
      manualServicios,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, -1, -1)
    );
    manualServicios.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          leerArchivoServidor.setVisible(false);
          jScrollPane3.setVisible(false);
          jLabel15.setVisible(true);
          jLabel16.setVisible(true);
          agregarServidor.setVisible(true);
          porcentajeServidor.setVisible(true);
          valorServidor.setVisible(true);
          jScrollPane4.setVisible(true);
        }
      }
    );

    archivoServicio.setBackground(new java.awt.Color(232, 225, 225));
    buttonGroup2.add(archivoServicio);
    archivoServicio.setText("Archivo");
    jPanel1.add(
      archivoServicio,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 650, -1, -1)
    );
    archivoServicio.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          jLabel15.setVisible(false);
          jLabel16.setVisible(false);
          jScrollPane4.setVisible(false);
          porcentajeServidor.setVisible(false);
          valorServidor.setVisible(false);
          agregarServidor.setVisible(false);
          leerArchivoServidor.setVisible(true);
          jScrollPane3.setVisible(true);
        }
      }
    );

    leerArchivoServidor.setText("Abrir archivo");
    leerArchivoServidor.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          leerArchivoServicioActionPerformed(evt);
        }
      }
    );
    jPanel1.add(
      leerArchivoServidor,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 690, -1, 20)
    );

    jPanel1.add(
      jScrollPane3,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 570, 120)
    );
    jPanel1.add(
      jScrollPane4,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 570, 120)
    );

    jLabel15.setText("Valor:");
    jPanel1.add(
      jLabel15,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 670, -1, -1)
    );
    jPanel1.add(
      valorServidor,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 670, 38, -1)
    );

    jLabel16.setText("Porcentaje:");
    jPanel1.add(
      jLabel16,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 670, -1, -1)
    );
    jPanel1.add(
      porcentajeServidor,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 670, 42, -1)
    );

    agregarServidor.setText("Agregar");
    jPanel1.add(
      agregarServidor,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 670, 90, 20)
    );
    agregarServidor.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          agregarServidorActionPerformed(evt);
        }
      }
    );

    simular.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    simular.setText("Simular");
    jPanel1.add(
      simular,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 860, 120, 30)
    );
    simular.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          simularActionPerformed(evt);
        }
      }
    );

    tablaLlegadasArchivo.setColumns(40);
    tablaLlegadasArchivo.setRows(5);
    jScrollPane1.setViewportView(tablaLlegadasArchivo);

    tablaLlegadasManual.setColumns(40);
    tablaLlegadasManual.setRows(5);
    jScrollPane2.setViewportView(tablaLlegadasManual);

    tablaServidoresArchivo.setColumns(40);
    tablaServidoresArchivo.setRows(5);
    jScrollPane3.setViewportView(tablaServidoresArchivo);

    tablaServidoresManual.setColumns(40);
    tablaServidoresManual.setRows(5);
    jScrollPane4.setViewportView(tablaServidoresManual);

    jPanel1.add(
      maxServidores,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 42, -1)
    );
    jPanel1.add(
      costoCliente,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 42, -1)
    );
    jPanel1.add(
      costoServidor,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 42, -1)
    );
    jPanel1.add(
      costoOperacion,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 42, -1)
    );
    jPanel1.add(
      costoOperacionExtra,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 42, -1)
    );
    jPanel1.add(
      duracion,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 42, -1)
    );

    unidad.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] {
          "Segundos",
          "Minutos",
          "Horas",
          "Dias",
          "Semanas",
          "Meses",
          "Años",
        }
      )
    );
    unidad.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          unidadActionPerformed(evt);
        }
      }
    );
    jPanel1.add(
      unidad,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
      getContentPane()
    );
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(
          jPanel1,
          javax.swing.GroupLayout.Alignment.TRAILING,
          400,
          650,
          javax.swing.GroupLayout.PREFERRED_SIZE
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(
          jPanel1,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          920,
          Short.MAX_VALUE
        )
    );

    pack();
  } // </editor-fold>//GEN-END:initComponents

  private void simularActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_siguienteActionPerformed
    // TODO add your handling code here:
    if (!Numerico.isNumeric(this.duracion.getText())) {
      Alerta.mensajeError(
        "La duración de simulación debe ser un valor numerico"
      );
      this.duracion.setText("");
      return;
    } else {
      if (Integer.parseInt(this.duracion.getText()) <= 0) {
        Alerta.mensajeError("La duración de la simulación no es valida");
        this.duracion.setText("");
        return;
      }
    }
    if (!Numerico.isNumeric(this.costoCliente.getText())) {
      Alerta.mensajeError("El costo de espera por cliente debe ser númerico");
      this.costoCliente.setText("");
      return;
    } else {
      if (Integer.parseInt(this.costoCliente.getText()) < 0) {
        Alerta.mensajeError("El costo de espera del cliente no es valido");
        this.costoCliente.setText("");
        return;
      }
    }
    if (!Numerico.isNumeric(this.costoServidor.getText())) {
      Alerta.mensajeError("El costo por servidor debe ser númerico");
      this.costoServidor.setText("");
      return;
    } else {
      if (Integer.parseInt(this.costoServidor.getText()) < 0) {
        Alerta.mensajeError("El costo por servidor no es valido");
        this.costoServidor.setText("");
        return;
      }
    }
    if (!Numerico.isNumeric(this.costoOperacion.getText())) {
      Alerta.mensajeError("El costo de operacion debe ser númerico");
      this.costoOperacion.setText("");
      return;
    } else {
      if (Integer.parseInt(this.costoOperacion.getText()) < 0) {
        Alerta.mensajeError("El costo de operacion no es valido");
        this.costoOperacion.setText("");
        return;
      }
    }
    if (!Numerico.isNumeric(this.costoOperacionExtra.getText())) {
      Alerta.mensajeError("El costo de operacion extra debe ser númerico");
      this.costoOperacionExtra.setText("");
      return;
    } else {
      if (Integer.parseInt(this.costoOperacionExtra.getText()) < 0) {
        Alerta.mensajeError("El costo de operacion extra no es valido");
        this.costoOperacionExtra.setText("");
        return;
      }
    }
    if (!archivoLlegada.isSelected() && !manualLlegada.isSelected()) {
      Alerta.mensajeError(
        "No se selecciono una opcion para los tiempos entre llegadas"
      );
      return;
    }

    if (manualLlegada.isSelected() && !this.tablaLlegadasM.completo()) {
      Alerta.mensajeError(
        "El tiempo entre llegadas no tiene el % introducido completo"
      );
      return;
    }
    if (archivoLlegada.isSelected() && !this.tablaLlegadasA.completo()) {
      Alerta.mensajeError(
        "El tiempo entre llegadas no tiene el % introducido completo"
      );
      return;
    }

    if (manualLlegada.isSelected()) this.seleccion = 0; else this.seleccion = 1;

    this.valorDuracion = Integer.parseInt(this.duracion.getText());
    this.valorCostoCliente = Integer.parseInt(this.costoCliente.getText());
    this.valorCostoServidor = Integer.parseInt(this.costoServidor.getText());
    this.valorCostoOperacion = Integer.parseInt(this.costoOperacion.getText());
    this.valorCostoOperacionExtra =
      Integer.parseInt(this.costoOperacionExtra.getText());

    Sistema Simulacion;

    if (this.seleccion == 0) if (manualServicios.isSelected()) Simulacion =
      new Sistema(
        Integer.parseInt(this.maxServidores.getText()),
        Integer.parseInt(this.duracion.getText()),
        Integer.parseInt(this.maxClientes.getText()),
        this.getValorCostoOperacionExtra(),
        this.getValorCostoOperacion(),
        this.getValorCostoCliente(),
        this.getValorCostoServidor(),
        this.tablaLlegadasM,
        this.tablaServidoresM,
        this.salida
      ); else Simulacion =
      new Sistema(
        Integer.parseInt(this.maxServidores.getText()),
        Integer.parseInt(this.duracion.getText()),
        Integer.parseInt(this.maxClientes.getText()),
        this.getValorCostoOperacionExtra(),
        this.getValorCostoOperacion(),
        this.getValorCostoCliente(),
        this.getValorCostoServidor(),
        this.tablaLlegadasM,
        this.tablaServidoresA,
        this.salida
      ); else if (manualServicios.isSelected()) Simulacion =
      new Sistema(
        Integer.parseInt(this.maxServidores.getText()),
        Integer.parseInt(this.duracion.getText()),
        Integer.parseInt(this.maxClientes.getText()),
        this.getValorCostoOperacionExtra(),
        this.getValorCostoOperacion(),
        this.getValorCostoCliente(),
        this.getValorCostoServidor(),
        this.tablaLlegadasA,
        this.tablaServidoresM,
        this.salida
      ); else Simulacion =
      new Sistema(
        Integer.parseInt(this.maxServidores.getText()),
        Integer.parseInt(this.duracion.getText()),
        Integer.parseInt(this.maxClientes.getText()),
        this.getValorCostoOperacionExtra(),
        this.getValorCostoOperacion(),
        this.getValorCostoCliente(),
        this.getValorCostoServidor(),
        this.tablaLlegadasA,
        this.tablaServidoresA,
        this.salida
      );
    Simulacion.comenzarSimulacion(this.getUnidad());
    this.setVisible(false);
    salida.setVisible(true);
  } //GEN-LAST:event_siguienteActionPerformed

  public int getValorDuracion() {
    return valorDuracion;
  }

  public int getValorCostoCliente() {
    return valorCostoCliente;
  }

  public int getValorCostoServidor() {
    return valorCostoServidor;
  }

  public int getValorCostoOperacion() {
    return valorCostoOperacion;
  }

  public int getValorCostoOperacionExtra() {
    return valorCostoOperacionExtra;
  }

  public String getUnidad() {
    return (String) unidad.getSelectedItem();
  }

  private void agregarLlegadaActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
    if (!Numerico.isNumeric(this.valorLlegada.getText())) {
      Alerta.mensajeError("El parametro: VALOR LLEGADA, no es numerico");
      this.valorLlegada.setText("");
      return;
    } else {
      if (Integer.parseInt(this.valorLlegada.getText()) <= 0) {
        Alerta.mensajeError("El parametro: VALOR LLEGADA, no es valido");
        this.valorLlegada.setText("");
        return;
      }
    }
    if (!Numerico.isNumeric(this.porcentajeLlegada.getText())) {
      Alerta.mensajeError("El parametro: PORCENTAJE LLEGADA, no es numerico");
      this.valorLlegada.setText("");
      return;
    } else {
      if (Integer.parseInt(this.porcentajeLlegada.getText()) <= 0) {
        Alerta.mensajeError("El parametro: PORCENTAJE LLEGADA, no es valido");
        this.valorLlegada.setText("");
        return;
      }
    }
    this.tablaLlegadasM.addTiempo(
        Integer.parseInt(this.valorLlegada.getText()),
        Double.parseDouble(this.porcentajeLlegada.getText()) / 100
      );
    this.tablaLlegadasManual.setText(this.tablaLlegadasM.toString());
  }

  private void agregarServidorActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_agregarLlegadaActionPerformed
    // TODO add your handling code here:
    if (!Numerico.isNumeric(this.valorServidor.getText())) {
      Alerta.mensajeError("El parametro: VALOR LLEGADA, no es numerico");
      this.valorLlegada.setText("");
      return;
    } else {
      if (Integer.parseInt(this.valorServidor.getText()) <= 0) {
        Alerta.mensajeError("El parametro: VALOR LLEGADA, no es valido");
        this.valorLlegada.setText("");
        return;
      }
    }
    if (!Numerico.isNumeric(this.porcentajeServidor.getText())) {
      Alerta.mensajeError("El parametro: PORCENTAJE LLEGADA, no es numerico");
      this.valorLlegada.setText("");
      return;
    } else {
      if (Integer.parseInt(this.porcentajeServidor.getText()) <= 0) {
        Alerta.mensajeError("El parametro: PORCENTAJE LLEGADA, no es valido");
        this.valorLlegada.setText("");
        return;
      }
    }
    this.tablaServidoresM.addTiempo(
        Integer.parseInt(this.valorServidor.getText()),
        Double.parseDouble(this.porcentajeServidor.getText()) / 100
      );
    this.tablaServidoresManual.setText(this.tablaServidoresM.toString());
  } //GEN-LAST:event_agregarLlegadaActionPerformed

  private void unidadActionPerformed(java.awt.event.ActionEvent evt) {
    String tiempo = (String) unidad.getSelectedItem();
    String unidadCosto = "$ por " + tiempo;
    unidadCostoEsperaCliente.setText(unidadCosto);
    unidadCostoExtra.setText(unidadCosto);
    unidadCostoSistema.setText(unidadCosto);
    unidadCostoServidor.setText(unidadCosto);
    //unidadCostoServidor.setText(unidadCosto);
  } //GEN-FIRST:event_unidadActionPerformed //GEN-LAST:event_unidadActionPerformed

  private void leerArchivoLlegadaActionPerformed(
    java.awt.event.ActionEvent evt
  ) {
    ManejoArchivo M = new ManejoArchivo();
    M.leerArchivoLlegadas();
    this.tablaLlegadasA = M.getTablaLlegadas();
    this.tablaLlegadasArchivo.setText(tablaLlegadasA.toString());
  }

  private void leerArchivoServicioActionPerformed(
    java.awt.event.ActionEvent evt
  ) { //GEN-FIRST:event_leerArchivoActionPerformed
    ManejoArchivo M = new ManejoArchivo();
    M.leerArchivoServicios(0);
    this.tablaServidoresA = M.getTablaServicio();
    this.tablaServidoresArchivo.setText(tablaServidoresA.toString());
  } //GEN-LAST:event_leerArchivoActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.ButtonGroup buttonGroup1;
  private javax.swing.ButtonGroup buttonGroup2;
  private javax.swing.JButton agregarLlegada;
  private javax.swing.JButton agregarServidor;
  private javax.swing.JRadioButton archivoLlegada;
  private javax.swing.JRadioButton archivoServicio;
  private javax.swing.JTextField costoCliente;
  private javax.swing.JTextField costoServidor;
  private javax.swing.JTextField costoOperacion;
  private javax.swing.JTextField costoOperacionExtra;
  private javax.swing.JTextField duracion;
  private javax.swing.JTextField maxServidores;
  private javax.swing.JTextField maxClientes;
  private javax.swing.JButton leerArchivoLlegada;
  private javax.swing.JButton leerArchivoServidor;
  private javax.swing.JLabel unidadCostoEsperaCliente;
  private javax.swing.JLabel unidadCostoExtra;
  private javax.swing.JLabel unidadCostoServidor;
  private javax.swing.JLabel unidadCostoSistema;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel18;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JRadioButton manualLlegada;
  private javax.swing.JRadioButton manualServicios;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JTextField tiempoSimulacion;
  private javax.swing.JTextField porcentajeLlegada;
  private javax.swing.JTextField porcentajeServidor;
  private javax.swing.JTextArea tablaLlegadasArchivo;
  private javax.swing.JTextArea tablaLlegadasManual;
  private javax.swing.JTextArea tablaServidoresArchivo;
  private javax.swing.JTextArea tablaServidoresManual;
  private javax.swing.JTextField valorLlegada;
  private javax.swing.JTextField valorServidor;
  private javax.swing.JButton simular;
  private javax.swing.JLabel titulo;
  private javax.swing.JComboBox<String> unidad;
  private javax.swing.JLabel unidadLabel;
  private javax.swing.JLabel unidadLabel2;
  // End of variables declaration//GEN-END:variables
}
