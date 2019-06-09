package Vista;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

/**
 * @author Pablo Oraa Lopez
 */
public class Configuracion extends javax.swing.JFrame
{
    private final VentanaPrincipal padre;
    private JList<String> listaOpciones;
    
    /**
     * Creates new form Configuracion
     */
    public Configuracion(VentanaPrincipal padre)
    {
        initComponents();
        listaOpciones = new JList();
        setLocationRelativeTo(padre);
        this.padre = padre;
        paneles.setSize(padre.getWidth(), padre.getHeight());
        configurarPaneles();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter y Setter">
    public VentanaPrincipal getPadre()
    {
        return padre;
    }
    
    public JSplitPane getPaneles()
    {
        return paneles;
    }

    public JRadioButton getTemaClaro()
    {
        return temaClaro;
    }

    public JRadioButton getTemaOscuro()
    {
        return temaOscuro;
    }
    
    public ButtonGroup getTemas()
    {
        return temas;
    }
    
    public JPanel getPersonalizacion()
    {
        return personalizacion;
    }
    //</editor-fold>
    
    public void configurarPaneles()
    {
        paneles.setDividerSize(1);
        panelIzquierdo();
        panelDerecho();
    }
    
    public void panelIzquierdo()
    {
        paneles.setLeftComponent(listaOpciones);
        paneles.getLeftComponent().setPreferredSize(new Dimension(170,0));
        paneles.getLeftComponent().setBackground(padre.getBackground());
    }
    
    public void panelDerecho()
    {
        paneles.setRightComponent(personalizacion);
        personalizacion.setVisible(false);
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

        temas = new javax.swing.ButtonGroup();
        paneles = new javax.swing.JSplitPane();
        personalizacion = new javax.swing.JPanel();
        temaClaro = new javax.swing.JRadioButton();
        temaOscuro = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuracion");
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);

        paneles.setBorder(null);
        paneles.setDividerLocation(170);
        paneles.setMaximumSize(new java.awt.Dimension(600, 500));
        paneles.setMinimumSize(new java.awt.Dimension(600, 500));
        paneles.setPreferredSize(new java.awt.Dimension(600, 500));
        paneles.setRequestFocusEnabled(false);

        personalizacion.setMaximumSize(new java.awt.Dimension(600, 500));
        personalizacion.setMinimumSize(new java.awt.Dimension(600, 500));
        personalizacion.setPreferredSize(new java.awt.Dimension(600, 500));

        temaClaro.setText("Tema Claro");

        temaOscuro.setText("Tema Oscuro");

        javax.swing.GroupLayout personalizacionLayout = new javax.swing.GroupLayout(personalizacion);
        personalizacion.setLayout(personalizacionLayout);
        personalizacionLayout.setHorizontalGroup(
            personalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(temaClaro)
                    .addComponent(temaOscuro))
                .addContainerGap(505, Short.MAX_VALUE))
        );
        personalizacionLayout.setVerticalGroup(
            personalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(temaClaro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(temaOscuro)
                .addContainerGap(412, Short.MAX_VALUE))
        );

        paneles.setRightComponent(personalizacion);

        getContentPane().add(paneles, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane paneles;
    private javax.swing.JPanel personalizacion;
    private javax.swing.JRadioButton temaClaro;
    private javax.swing.JRadioButton temaOscuro;
    private javax.swing.ButtonGroup temas;
    // End of variables declaration//GEN-END:variables
}
