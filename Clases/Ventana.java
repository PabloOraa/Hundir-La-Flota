package Clases;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;

/**
 * Ventana de la aplicación
 * @version 1.4.1
 * @author Pablo Oraa Lopez
 */
public class Ventana extends javax.swing.JFrame
{

    /**
     * Constructor de la clase App que crea un jugador controlado por la maquina
     * y la ruta a usar por el usuario en caso de querer guardar.
     * @param j1 Jugador que usará el usuario.
     */
    public Ventana(Jugador j1)
    {
        this.j1 = j1;
        j2 = new Jugador(Textos.NPC);
        path = System.getProperty("user.home") + "/Desktop/"+j1.getNickname()+".save";
        initComponents();
        asignarBotones();
    }
    
    /**
     * Los botones del programa de cancelar guardar y disparar/insertar reciben
     * una función en función del botón que sea para hacer la acción marcada.
     */
    private void asignarBotones()
    {
        Cancelar.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                EleccionFila.getModel().setValue(1);
                SpinnerListModel slm = (SpinnerListModel) EleccionColumna.getModel();
                EleccionColumna.getModel().setValue(slm.getList().get(0));
            }                
        });  
        
        Guardar.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                save();
            }                
        }); 
        
        InsertarDisparar.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int fila = (Integer) EleccionFila.getValue();
                String col = (String)EleccionColumna.getValue();
                char columna = col.charAt(0);
                if(InsertarDisparar.getText().equals(Textos.SHOOT))
                    disparar(fila,columna);
                else
                    insertarBarcos(fila,columna);
            }  
        });
    }
    
    /**
     * Dispara en la posición indicada por fila y columna y realiza lo propio con el NPC
     * @param fila Número que indica la Fila elegida por el usuario
     * @param columna Carcater que indica la Columna elegida por el usuario
     */
    private void disparar(int fila, char columna)
    {
        try
        {
            String res = j1.Disparar(j2, fila, columna);
            String resultadoDisparocpu = "";
            if(res.equals(Textos.PLAYERDEAD))
                mostrarGanador(j1); 
            else
            {
                fila = generarFila();
                columna = generarColumna();
                boolean salir = false;
                try
                {
                    resultadoDisparocpu = j2.Disparar(j1, fila, columna);
                    if(res.equals(Textos.PLAYERDEAD))
                        mostrarGanador(j2);
                }catch(ExcepcionesBarco ex)
                {
                    while(!salir)
                    {
                        fila = generarFila();
                        columna = generarColumna();
                        try
                        {
                            resultadoDisparocpu = j2.Disparar(j1, fila, columna);
                            if(res.equals(Textos.PLAYERDEAD))
                                mostrarGanador(j2);
                            salir = true;
                        }catch(ExcepcionesBarco error)
                        {
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, j1.getNickname() + ": "+ res + "\n" + j2.getNickname() + ": " + resultadoDisparocpu, "Resultado del disparo",JOptionPane.INFORMATION_MESSAGE);
            imprimirTableros(j1);
        } catch (ExcepcionesBarco ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra un mensaje felicitando al ganador de la partida
     * @param player Jugador que ha ganado
     */
    private void mostrarGanador(Jugador player)
    {
        JOptionPane.showMessageDialog(null, "Enhorabuena " + player.getNickname() + " has ganado", "Ganador",JOptionPane.INFORMATION_MESSAGE);
        if(this.buscarPartida())
            this.borrarPartida();
        Guardar.setEnabled(false);
        InsertarDisparar.setEnabled(false);
        Cancelar.setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        etiquetaBarcos = new javax.swing.JLabel();
        etiquetaResultados = new javax.swing.JLabel();
        Fila = new javax.swing.JLabel();
        EleccionFila = new javax.swing.JSpinner();
        Columna = new javax.swing.JLabel();
        EleccionColumna = new javax.swing.JSpinner();
        InsertarDisparar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Turno = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        Barcos = new javax.swing.JTextPane();
        Resultados = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hundir la Flota");
        setLocation((getToolkit().getScreenSize().width/2)-(getWidth()/2),(getToolkit().getScreenSize().height/2)-(getHeight()/2));
        setMaximumSize(new java.awt.Dimension(650, 400));
        setMinimumSize(new java.awt.Dimension(650, 400));
        setPreferredSize(new java.awt.Dimension(650, 400));
        setResizable(false);

        etiquetaBarcos.setText(Barcos.getName());

        etiquetaResultados.setText(Resultados.getName());

        Fila.setText("Fila");

        EleccionFila.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        EleccionFila.setEditor(new javax.swing.JSpinner.NumberEditor(EleccionFila, ""));

        Columna.setText("Columna");

        EleccionColumna.setModel(new javax.swing.SpinnerListModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        EleccionColumna.setEditor(new javax.swing.JSpinner.ListEditor(EleccionColumna));
        EleccionColumna.setName(""); // NOI18N

        InsertarDisparar.setText(getEstado());

        Cancelar.setText("Cancelar");

        Turno.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Turno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Turno.setText("Turno de " + j1.getNickname()
        );

        Guardar.setText("Guardar");

        Barcos.setEditable(false);
        Barcos.setBackground(new java.awt.Color(240, 240, 240));
        Barcos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Barcos.setAutoscrolls(false);
        Barcos.setMaximumSize(new java.awt.Dimension(248, 250));
        Barcos.setMinimumSize(new java.awt.Dimension(248, 250));
        Barcos.setPreferredSize(new java.awt.Dimension(248, 250));

        Resultados.setEditable(false);
        Resultados.setBackground(new java.awt.Color(240, 240, 240));
        Resultados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Resultados.setAutoscrolls(false);
        Resultados.setMaximumSize(new java.awt.Dimension(248, 250));
        Resultados.setMinimumSize(new java.awt.Dimension(248, 250));
        Resultados.setPreferredSize(new java.awt.Dimension(248, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Turno, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaBarcos)
                            .addComponent(Barcos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaResultados)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(Resultados, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Columna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Fila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(EleccionFila)
                                            .addComponent(EleccionColumna)))
                                    .addComponent(InsertarDisparar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Turno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiquetaResultados)
                    .addComponent(etiquetaBarcos))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Fila)
                            .addComponent(EleccionFila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Columna)
                            .addComponent(EleccionColumna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InsertarDisparar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cancelar)
                        .addGap(18, 18, 18)
                        .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Resultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Barcos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        String nickname = (String)JOptionPane.showInputDialog(null, Textos.WELCOMETEXT + "\n" + Textos.ASKNICKNAME, "Nombre de Usuario", JOptionPane.QUESTION_MESSAGE, null, null, null);
        try
        {    
            if(!nickname.isEmpty())
            {    
                Jugador jtemp = new Jugador(nickname);            
                Ventana aplicacion = new Ventana(jtemp); 
        
                if(aplicacion.buscarPartida())
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(Textos.FORMAT);
                    int opt = JOptionPane.showOptionDialog(null, Textos.SAVEFILEFOUND + sdf.format(new Date(new File(aplicacion.path).lastModified())) + Textos.ASKLOAD
                            , "Cargar Partida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(opt == JOptionPane.YES_OPTION)   
                        aplicacion.quiereCargar(aplicacion);
                    else
                        aplicacion.empezarNuevo(aplicacion);
                }
                else
                    aplicacion.empezarNuevo(aplicacion);
                
                aplicacion.imprimirTableros(aplicacion.j1);
            }
        }catch(NullPointerException ex)
        {
            
        }
    }
    
    public void quiereCargar(Ventana vtn)
    {
        this.cargarPartida();
        if(this.j1.getContador() == 4)
            this.setEstado();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            vtn.setVisible(true);
        });
    }
    
    public void empezarNuevo(Ventana vtn)
    {
        if(buscarPartida())
            this.borrarPartida();
        this.insertarBarcos(this.j2, "src/Datos/posiciones.csv"); 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            vtn.setVisible(true);
        });      
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Barcos;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Columna;
    private javax.swing.JSpinner EleccionColumna;
    private javax.swing.JSpinner EleccionFila;
    private javax.swing.JLabel Fila;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton InsertarDisparar;
    private javax.swing.JTextPane Resultados;
    private javax.swing.JLabel Turno;
    private javax.swing.JLabel etiquetaBarcos;
    private javax.swing.JLabel etiquetaResultados;
    // End of variables declaration//GEN-END:variables
    /**
     * Cadena que marca el estado del juego en función de Insertar Barco o disparar
     */
    private String cadenaEstado = Textos.ADDSHIP;
    /**
     * Clase Scanner para la entrada por teclado del usuario.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Jugador controlado por el usuario del programa.
     */
    private Jugador j1;
    /**
     * Jugador controlado por la máquina.
     */
    private Jugador j2;
    /**
     * Ruta a guardar el archivo del jugador si desea guardar.
     */
    private final String path;
    
    /**
     * Devuelve el estado del programa
     * @return Cadena ADDSHIP o SHOOT en función del punto en el que esté el juego
     */
    public String getEstado()
    {
        return cadenaEstado;
    }
    
    /**
     * Cambia el estado de ADDSHIP a SHOOT
     */
    private void setEstado()
    {
        cadenaEstado = Textos.SHOOT;
        InsertarDisparar.setText(cadenaEstado);
    }
    
    /**
     * Imprime los dos tableros del Jugador.
     * @param j1 Jugador a imprimir.
     */
    public void imprimirTableros(Jugador j1) 
    {
        Barcos.setEditable(true);
        this.Barcos = j1.getTableroBarcos().imprimirTableroInterfaz(Barcos);
        Barcos.setEditable(false);
        Resultados.setEditable(true);
        this.Resultados = j1.getTableroResultados().imprimirTableroInterfaz(Resultados);
        Resultados.setEditable(false);
    }

    /**
     * Guarda el estado de los jugadores para recuperarlo en el futuro.
     * @return True si se ha guardado y false si no.
     */
    public boolean save()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));)
        {
            oos.writeObject(j1);
            oos.writeObject(j2);
            return true;
        }
        catch (IOException ex) 
        {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Busca si existe una partida guardada para el nickname indicado.
     * @return True si existe y False si no.
     */
    public boolean buscarPartida()
    {
        return new File(path).exists();
    }
    
    /**
     * Carga la partida que ha encontrado el programa para el nickname indicado.
     */
    private void cargarPartida()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));) 
        {
            this.j1 = (Jugador) ois.readObject();
            j2 = (Jugador) ois.readObject();
        } catch (EOFException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Borra la partida guardada al acabar la partida (si existe) o si no se 
     * quiere cargar
     */
    private void borrarPartida() 
    {
        File arch = new File(path);
        if(arch.exists())
            arch.delete();
    }
    
    /**
     * Comprueba que la direccion introducida es V,H,D o I.
     * @param columna Columna introducida por el usuario.
     * @return True si es valido y False si no.
     */
     private boolean comprobarDireccion(char direccion)
     {
         return (direccion == Textos.HORIZONTAL || direccion == Textos.VERTICAL
                 || direccion == Textos.DIAGONAL || direccion == Textos.INVERSEDIAGONAL);
     }

    /**
     * Genera un numero entre 1 y 10 para representar la fila a disparar.
     * @return Número generado entre 1 y 10.
     */
    private int generarFila()
    {
        return 1+(new Random().nextInt(10));
    }

    /**
     * Genera un caracter entre A y J para representar la columna a disparar.
     * @return Caracter generado entre A y J.
     */
    private char generarColumna()
    {
        return obtenerCaracter(1+(new Random().nextInt(10)));
    }

    /**
     * A partir del número generado para la columna para el NPC, se obtiene el 
     * caracter asociado.
     * @param i Numero de columna entre 1 y 10.
     * @return Caracter entre A y J asociado al número de columna.
     */
    private char obtenerCaracter(int i)
    {
        switch(i)
        {
            case 1:
                return Textos.COLUMN1;
            case 2:
                return Textos.COLUMN2;
            case 3:
                return Textos.COLUMN3;
            case 4:
                return Textos.COLUMN4;
            case 5:
                return Textos.COLUMN5;
            case 6:
                return Textos.COLUMN6;
            case 7:
                return Textos.COLUMN7;
            case 8:
                return Textos.COLUMN8;
            case 9:
                return Textos.COLUMN9;
            default: /*Se considera el Default la letra J*/
                return Textos.COLUMN10;
        }
    }

    /**
     * Inserta los barcos en la posición que indique el jugador.
     * <br/>
     * Empieza con el portaaviones, buque, submarino y acaba con la lancha.
     * @param j1 Jugador en el que se van a introducir los barcos según indique 
     * el usuario
     */
    private void insertarBarcos(int fila, char columna)
    {
        System.out.println("Insertando barcos del jugador " + j1.getNickname());
        try
        {
            char dir = ((String)JOptionPane.showInputDialog(null, "¿En que dirección quieres que se introduzca el barco?", "Dirección", JOptionPane.QUESTION_MESSAGE)).charAt(0);
            if(comprobarDireccion(dir))
                if(j1.insertarBarco(dir, fila, columna, j1.getListaBarcos().get(j1.getContador())))
                {
                    Barcos.setEditable(true);
                    this.Barcos = j1.getTableroBarcos().imprimirTableroInterfaz(Barcos);
                    Barcos.setEditable(false);
                    j1.sumContador();
                    if(j1.getContador() == 4)
                       this.setEstado(); 
                }
                else
                    JOptionPane.showMessageDialog(null, Textos.NOTVALIDFIELDS, "Error", JOptionPane.ERROR_MESSAGE);
	    else
		JOptionPane.showMessageDialog(null, Textos.NOTVALIDDIR, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcionesBarco ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Inserta los barcos en la posición que indique el jugador mediante un fichero
     * CSV que el jugador indicará la ruta.
     * @param j1 Jugador en el que se van a introducir los barcos según indique 
     * el usuario
     * @param path Ruta en la que se encuentra el archivo CSV con los barcos a 
     * insertar.
     */
    private boolean insertarBarcos(Jugador j1, String path)
    {
        System.out.println("Insertando barcos del jugador " + j1.getNickname());
        return j1.insertarBarco(new File(path));
    }
}
