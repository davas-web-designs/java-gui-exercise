import java.awt.*;

import javax.swing.*;

public class Lamina extends JPanel{

  public Lamina (String titulo, String [] opciones){

    setBackground(Color.GREEN);

    //borde de cada box

    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));

    //tipo de disposicion de los boxes

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    //hacemos un button group, esto nos facilita que solo se pueda pulsar un
    //radio button en cada group

    grupo = new ButtonGroup();

    for(int i = 0; i < opciones.length; i++){
      //Recorremos el bucle que nos viene por parametro, en el que cada
      //elemento es un string que representa una opcion

      JRadioButton boton = new JRadioButton(opciones[i]);

      //aniadimos una accion a cada boton
      boton.setActionCommand(opciones[i]);
      boton.setBackground(Color.GREEN);

      add(boton);
      grupo.add(boton);

      boton.setSelected(i==0);
    }

  }

  public String getSelection(){

    //utilizamos el metodo getSelection de ButtonGroup para ver que radio button
    //esta pulsado

    return grupo.getSelection().getActionCommand();

  }
  private ButtonGroup grupo;
}
