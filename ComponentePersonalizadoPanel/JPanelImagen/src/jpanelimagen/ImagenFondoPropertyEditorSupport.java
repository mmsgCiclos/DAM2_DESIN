/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanelimagen;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author 34605
 */
public class ImagenFondoPropertyEditorSupport extends PropertyEditorSupport 
{
    private ImagenFondoPanel imagenFondoPanel = new ImagenFondoPanel();

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return imagenFondoPanel;
    }

    @Override
    public String getJavaInitializationString() {
        ImagenFondo imagenFondo = imagenFondoPanel.getSelectedValue();
        String rutaAbsoluta = imagenFondo.getRutaImagen().getAbsolutePath();
        String ruta = rutaAbsoluta.replace("\\","/" );
        return "new jpanelimagen.ImagenFondo("+"new java.io.File(\""+ruta+"\"),"+imagenFondo.getOpacidad()+"f)";
               
        //return "new jpanelimagen.ImagenFondo("+"new java.io.File(\""+imagenFondo.getRutaImagen().getAbsolutePath()+"\"),"+imagenFondo.getOpacidad()+"f)";
        //return "new jpanelimagen.ImagenFondo("+"new java.io.File(\"C:/Users/34605/Pictures/birrete.jpg\""+"),"+imagenFondo.getOpacidad()+"f)";
    }

    @Override
    public Object getValue() {
        return imagenFondoPanel.getSelectedValue();
    }
    
}
