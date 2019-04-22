/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author pabli
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
                        {
                            try
                            {
                                String res = j1.Disparar(j2, fila, columna);
                                if(res.equals(Textos.PLAYERDEAD))
                                {
                                    JOptionPane.showMessageDialog(null, "Enhorabuena " + j1.getNickname() + " has ganado", "Ganador",JOptionPane.INFORMATION_MESSAGE);
                                    Guardar.setEnabled(false);
                                    InsertarDisparar.setEnabled(false);
                                    Cancelar.setEnabled(false);
                                }   
                                else
                                {
                                    JOptionPane.showMessageDialog(null, res, "Resultado del disparo",JOptionPane.INFORMATION_MESSAGE);
                                    fila = generarFila();
                                    columna = generarColumna();
                                    boolean salir = false;
                                    try
                                    {
                                        j2.Disparar(j1, fila, columna);
                                        if(res.equals(Textos.PLAYERDEAD))
                                        {
                                            JOptionPane.showMessageDialog(null, "Enhorabuena " + j1.getNickname() + " has ganado", "Ganador",JOptionPane.INFORMATION_MESSAGE);
                                            Guardar.setEnabled(false);
                                            InsertarDisparar.setEnabled(false);
                                            Cancelar.setEnabled(false);
                                        } 
                                    }catch(ExcepcionesBarco ex)
                                    {
                                        while(!salir)
                                        {
                                            fila = generarFila();
                                            columna = generarColumna();
                                            try
                                            {
                                                j2.Disparar(j1, fila, columna);
                                                if(res.equals(Textos.PLAYERDEAD))
                                                {
                                                    JOptionPane.showMessageDialog(null, "Enhorabuena " + j1.getNickname() + " has ganado", "Ganador",JOptionPane.INFORMATION_MESSAGE);
                                                    Guardar.setEnabled(false);
                                                    InsertarDisparar.setEnabled(false);
                                                    Cancelar.setEnabled(false);
                                                } 
                                                salir = true;
                                            }catch(ExcepcionesBarco error)
                                            {
                                            }
                                        }
                                    }
                                }
                                imprimirTableros(j1);
                            } catch (ExcepcionesBarco ex)
                            {
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        
                    }  
                });
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

        Resultados.setEditable(false);
        Resultados.setBackground(new java.awt.Color(240, 240, 240));
        Resultados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                            .addComponent(Barcos, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(Resultados, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(Barcos))
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
        Jugador jtemp = new Jugador(nickname);
             
        Ventana aplicacion = new Ventana(jtemp); 
        
        if(aplicacion.buscarPartida())
        {
            SimpleDateFormat sdf = new SimpleDateFormat(Textos.FORMAT);
            int opt = JOptionPane.showOptionDialog(null, Textos.SAVEFILEFOUND + sdf.format(new Date(new File(aplicacion.path).lastModified())) + Textos.ASKLOAD
                    , "Cargar Partida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(opt == JOptionPane.YES_OPTION)
            {        
                aplicacion.cargarPartida();
                
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable()
                {   
                    public void run()
                    {
                        aplicacion.setVisible(true);
                    }
                });
            }
            else
            {   
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        aplicacion.setVisible(true);
                    }
                });
                
                aplicacion.borrarPartida();
                aplicacion.insertarBarcos(aplicacion.j1);
                aplicacion.insertarBarcos(aplicacion.j2, "src/Datos/posiciones.csv");
            }
        }
        else
        {
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    aplicacion.setVisible(true);
                }
            });
            //aplicacion.insertarBarcos(aplicacion.j1);
            aplicacion.insertarBarcos(aplicacion.j2, "src/Datos/posiciones.csv");
        }
        aplicacion.imprimirTableros(aplicacion.j1);
        aplicacion.setEstado();
        //aplicacion.jugar();
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
    private String cadenaEstado = "Insertar";
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
    
    public String getEstado()
    {
        return cadenaEstado;
    }
    
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
     * Pide una fila al usuario que introducirá por teclado
     * @return Número introducido por teclado.
     */
    private int pedirFila()
    {
        System.out.println(Textos.ASKROW);
        try
        {
            return Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException ex)
        {
            System.err.println(ex.getMessage());
        }
        return -1;
    }
    
    /**
     * Pide la columna al usuario para que la introduzca por teclado.
     * @return Caracter que indica la columna.
     */
    private char pedirColumna()
    {
        System.out.println(Textos.ASKCOLUMN);
        return sc.nextLine().charAt(0);
    }
    
    /**
     * Comprueba que la fila introducida esté entre 1 y 10.
     * @param fila Fila introducida por el usuario.
     * @return True si es valido y False si no.
     */
    private boolean comprobarFila(int fila)
    {
        return fila < 11 && fila > 0;
    }
    
    /**
     * Comprueba que la columna introducida esté entre A y J.
     * @param columna Columna introducida por el usuario.
     * @return True si es valido y False si no.
     */
    private boolean comprobarColumna(char columna)
    {
        return j1.getTableroBarcos().getCoord(Character.toUpperCase(columna)) < 10 
                && j1.getTableroBarcos().getCoord(Character.toUpperCase(columna)) >-1;
    }
    /**
     * Comprueba que la direccion introducida es V,H,D o I.
     * @param columna Columna introducida por el usuario.
     * @return True si es valido y False si no.
     */
     private boolean comprobarDireccion(char direccion)
     {
         return (direccion == Textos.HORIZONTAL && direccion == Textos.VERTICAL
                 && direccion == Textos.DIAGONAL && direccion == Textos.INVERSEDIAGONAL);
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
    private void insertarBarcos(Jugador j1)
    {
        System.out.println("Insertando barcos del jugador " + j1.getNickname());
        char dir, columna;
        int fila;
        for(int i = 0; i < j1.getListaBarcos().size();)
        {
            try
            {
                System.out.println("Se va a introducir el: " + j1.getListaBarcos().get(i).getName());
                System.out.println(Textos.ASKDIR);
                dir = sc.nextLine().charAt(0);
                fila = pedirFila();
                columna = pedirColumna();
                
                if(comprobarDireccion(dir))
			if(comprobarFila(fila) && comprobarColumna(columna))
                	{
                    		if(j1.insertarBarco(dir, fila, columna, j1.getListaBarcos().get(i)))
                    		{
//                        		j1.getTableroBarcos().imprimirTablero();
                        		i++;
                    		}
                	}
                	else
                    		System.err.println(Textos.NOTVALIDFIELDS);
		else
			System.err.println(Textos.NOTVALIDDIR); 
            } catch (ExcepcionesBarco | NumberFormatException ex)
            {
                System.err.println(ex.getMessage());
            }
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
