/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photorender;

/**
 *
 * @author sega
 */

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Timer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class FileHandler {
	
	  private final static String[] okFileExtensions = 
			    new String[] {"jpg", "png", "gif"};
          int i=0;
          private static final int interval = 1000;
          private Timer timer;
	 public  final DefaultListModel<Image> listModelImage = new DefaultListModel<>();
	
	public void fileLoader( final String src) throws IOException, InterruptedException {
		
		
            
                        File source = new File(src);
                      
                      
                        
		
                    if(!source.exists())
                    {
                        
                        System.err.println(source.getName() + " N'existe pas !");
                        return;
                    }
                 
                      
	
               
		Collection<File> f = FileUtils.listFiles(
				  source, 
				  new RegexFileFilter("^(.*?)"), 
				  DirectoryFileFilter.DIRECTORY
				);
		final File [] files = f.toArray(new File[f.size()]);
                
                
		          Window.bar.setMaximum(files.length);
               
                            timer = new Timer(interval, new ActionListener() {
                            public void actionPerformed(ActionEvent ae) {
                                
                                if(i == files.length)
                                {
                                    timer.stop();
                                    
                                    
                                    System.out.println("Chargement Terminé !");
                                }
                                else
                                {
                                
                                File child = files[i];
                                if(accept(new File( src + "/" + child))){
				System.out.println("Chargement de fichier "+child.getName());
                                    try {
                                        BufferedImage image=ImageIO.read(child);
                                        BufferedImage resizedImage=resize(image,100,100);
                                        listModelImage.addElement(new Image(child.getName(),child, resizedImage));
                                         Window.imagesList.setModel(listModelImage);
                                        Window.imagesList.setCellRenderer(new ImageRender());

                                        Window.panel.revalidate();
                                        Window.panel.repaint();
                                       Window.load.setEnabled(true);
                                    } catch (IOException ex) {
                                        Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				

                                System.out.println("---------------------------------------------------------\n\n");
				
                                }
                        
                   
			i++;
                       Window.bar.setValue(i);
                            }
                                
                            }
                        });
	
               	timer.start();
                        
		
	}
        
        
        public void CopieFile(final String dst){
              final File destination = new File(dst);
              i = 0;
                 if(!destination.exists())
                    {
                        destination.mkdirs();
                         System.out.println(destination.getName() + " Créé");
                    }
                 Window.bar.setMaximum(listModelImage.size());
                     timer = new Timer(interval, new ActionListener() {
                            public void actionPerformed(ActionEvent ae) {
                                
                                  if(i == listModelImage.size())
                                {
                                    timer.stop();
                                    
                                    
                                    System.out.println("Copie Terminée !");
                                }
                                  else{
                                      
                                     Image image = listModelImage.get(i);
                                   if(image.getNombre() > 0){
                                         for(int j= 1 ; j <= image.getNombre(); j++)
                                     {
                                         try {
                                             String [] tNm = image.getNom().split(".");
                                             FileUtils.copyFile(image.getFile(),new File(dst+"/"+j+" - "+image.getNom()));
                                             System.out.println(j+" - "+image.getNom() +" Copié Dans "+dst+"\n");
                                         } catch (IOException ex) {
                                             Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                                         }
                                     }
                                     System.out.println("---------------------------------------------------------\n\n");
                                   }
                                    i++;
                                    Window.bar.setValue(i);
                                  }
                                
                            }
                     });
                            
                  	timer.start();
                 
        }
	
        
	
	private BufferedImage resize(BufferedImage image, int width, int height) {
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = (Graphics2D) bi.createGraphics();
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(image, 0, 0, width, height, null);
    g2d.dispose();
    return bi;
}
	

	
	
	  public boolean accept(File file)
	  {
	    for (String extension : okFileExtensions)
	    {
	      if (file.getName().toLowerCase().endsWith(extension))
	      {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  
	
	
}
