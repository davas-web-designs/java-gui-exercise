import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.util.*;

public class Marco extends JFrame{

  public Marco(){

    //getContentPane().setBackground(Color.BLACK); 

    setTitle("Prueba de dialogos");

    setBounds(500,300,600,450);

    //primera lamina, para los diferentes containers con radio buttons
    //usamos la clase lamina

    JPanel lamina_elementos = new JPanel();

    //disposicion en grid (f x c), esto ayuda a que sea "responsive"

    lamina_elementos.setLayout(new GridLayout(2,3));

    //array de opciones que determinan los radio butttons del primer box

    String opciones[] = {"Mensaje", "Confirmar", "Opcion", "Entrada"};

    //creamos la primera lamina con su constructor que he hemos hecho en
    //lamina.java

    lamina_tipo = new Lamina("tipo", opciones);

    //ahora creamos el resto de laminas pero el array de opciones lo
    //instanciamos directamente en el paso de parametros del constructor

    lamina_tipo_mensaje = new Lamina("tipo de mensaje" , new String[]{
      "ERROR_MESSAGE",
      "INFORMATION_MESSAGE",
      "WARNING_MESSAGE",
      "QUESTION_MESSAGE",
      "PLAIN_MESSAGE"
    });

    lamina_mensaje = new Lamina("mensaje" , new String[]{
      "Cadena",
      "Icono",
      "Componente",
      "Otros",
      "Objects[]"
    });

    lamina_confirmar = new Lamina("confirmar" , new String[]{
      "DEFAULT_OPTION",
      "YES_NO_OPTION",
      "YES_NO_CANCEL_OPTION",
      "OK_CANCEL_OPTION"
    });

    lamina_opcion = new Lamina("mensaje" , new String[]{
      "String[]",
      "Icon[]",
      "Object[]"
    });

    lamina_entrada = new Lamina("mensaje" , new String[]{
      "Campo de texto",
      "Combo"
    });

    setLayout(new BorderLayout());

    //aniadimos todos los boxes a nuestra lamina

    lamina_elementos.add(lamina_tipo);
    lamina_elementos.add(lamina_tipo_mensaje);
    lamina_elementos.add(lamina_mensaje);
    lamina_elementos.add(lamina_confirmar);
    lamina_elementos.add(lamina_opcion);
    lamina_elementos.add(lamina_entrada);

    //construimos la segunda lamina

    JPanel lamina_mostrar = new JPanel();

    JButton boton_mostrar = new JButton("Mostrar");

    //le damos a este boton una funcionalidad, que ejecute el getSelection
    boton_mostrar.addActionListener(new AccionMostrar());

    lamina_mostrar.add(boton_mostrar);

    //colocamos la segunda lamina en la parte inferior

    add(lamina_mostrar, BorderLayout.SOUTH);

    //colocamos la primera lamina en la parte superior

    add(lamina_elementos, BorderLayout.CENTER);

  }

  public Object getMessage(){

    String s = lamina_mensaje.getSelection();
    if(s.equals("Cadena")){
      return cadena;
    }else if(s.equals("Icono")){
      return iconoMensaje;
    }else if(s.equals("Componente")){
      return componente;
    }else if(s.equals("Otros")){
      return date;
    }else if(s.equals("Objects[]")){
      return new Object[]{
        cadena, iconoMensaje, componente, date
      };
    }else return null;

  }

  public int getPanelType(Lamina l){
    String s = l.getSelection();
    if(s.equals("ERROR_MESSAGE") || s.equals("YES_NO_OPTION")){
      return 0;
    }else if(s.equals("INFORMATION_MESSAGE") || s.equals("YES_NO_CANCEL_OPTION")){
      return 1;
    }else if(s.equals("WARNING_MESSAGE") || s.equals("OK_CANCEL_OPTION")){
      return 2;
    }else if(s.equals("QUESTION_MESSAGE")){
      return 3;
    }else if(s.equals("PLAIN_MESSAGE") || s.equals("DEFAULT_OPTION")){
      return -1;
    }else return 0;
  }

  public Object[] getOptions(Lamina l){
    String s = l.getSelection();
    if(s.equals("String[]")){
      return new String[]{"Amarillo", "Azul", "Rojo"};
    }else if(s.equals("Icon[]")){
      return new Object[]{new ImageIcon("azul.gif"), new ImageIcon("roja.gif"), new ImageIcon("verde.gif")};
    }else if(s.equals("Object[]")){
      return new Object[]{
        cadena, iconoMensaje, componente, date
      };
    }
    else return null;
  }

  private class AccionMostrar implements ActionListener{

    public void actionPerformed(ActionEvent e){
      // System.out.println(lamina_tipo.getSelection());

      if(lamina_tipo.getSelection().equals("Mensaje")){
        JOptionPane.showMessageDialog(Marco.this, getMessage(), "Titulo", getPanelType(lamina_tipo_mensaje));
      }else if(lamina_tipo.getSelection().equals("Confirmar")){
        JOptionPane.showConfirmDialog(Marco.this, getMessage(), "Titulo",getPanelType(lamina_confirmar),getPanelType(lamina_tipo_mensaje));
      }else if(lamina_tipo.getSelection().equals("Entrada")){
        if(lamina_entrada.getSelection().equals("Campo de texto")){
          JOptionPane.showInputDialog(Marco.this, getMessage(), "Titulo",getPanelType(lamina_tipo_mensaje));
        }else{
          JOptionPane.showInputDialog(Marco.this, getMessage(), "Titulo", getPanelType(lamina_tipo_mensaje), null, new String[]{"Amarillo", "Azul", "Rojo"}, "Azul");
        }
      }else if(lamina_tipo.getSelection().equals("Opcion")){
        JOptionPane.showOptionDialog(Marco.this, getMessage(), "Titulo",0,getPanelType(lamina_tipo_mensaje),null,getOptions(lamina_opcion),null);
      }
    }

  }

  private Lamina lamina_tipo , lamina_tipo_mensaje , lamina_mensaje, lamina_confirmar, lamina_opcion, lamina_entrada;

  private String cadena = "Mensaje";
  private Icon iconoMensaje = new ImageIcon("icon.gif");
  private Object date = new Date();
  private Component componente = new Lamina_Ejemplo();
}

class Lamina_Ejemplo extends JPanel{

  public void paintComponent(Graphics g){
    super.paintComponents(g);

    Graphics2D g2 = (Graphics2D) g;

    Rectangle2D rectangulo = new Rectangle2D.Double(0,0,getWidth(), getHeight());
  
    g2.setPaint(Color.YELLOW);

    g2.fill(rectangulo);
  }

}
