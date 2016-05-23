/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photorender;

import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author sega
 */
public class ImageRender extends JLabel implements ListCellRenderer<Image>{

    public ImageRender() {
        setOpaque(true);
    }
    
    

    @Override
    public Component getListCellRendererComponent(JList<? extends Image> list, Image image, int index, boolean isSelected, boolean cellHasFocus) {
       
        File file = image.getFile();
        ImageIcon imageIcon = new ImageIcon(image.getImage());
        
        
        setIcon(imageIcon);
        setText(image.getNombre().toString());
        
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            Window.previewImage.setIcon(imageIcon);
            Window.previewImage.setText(image.getNombre().toString());
            Window.previewFrame.setVisible(true);
            Window.previewFrame.setSize(624, 168);
            Window.previewFrame.setDefaultCloseOperation(closePreviewFrame(JFrame.DISPOSE_ON_CLOSE));
            Window.setNumber.setEnabled(true);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            Window.previewFrame.dispose();
        }
        
        return this;
    }
    public int closePreviewFrame(int i)
    {
       // Window.cancel.doClick();
        return i;
    }
    
}
