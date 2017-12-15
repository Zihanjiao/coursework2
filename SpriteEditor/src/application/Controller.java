package application;




import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

public class Controller {
	@FXML
	private AnchorPane spritepane;	
	private Button open;
	private Button save;
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private CheckBox fill;
	Rectangle [][]recPixels;
	Image image;
	int pixelSize=10;
	
	
	
	int x;
	
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	
	void display() {
		int w=(int)image.getWidth();
		int h=(int)image.getHeight();
		spritepane.getChildren().clear();
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Rectangle rectangle=recPixels[i][j];
				spritepane.getChildren().add(rectangle);
			}
		}
		
	}
	
	boolean flag=false;
	Rectangle getRec(Color color,int x,int y) {
		Rectangle rectangle=new Rectangle();
		rectangle.setFill(color);
		rectangle.setStroke(Color.BLACK);
		rectangle.setWidth(pixelSize);
		rectangle.setHeight(pixelSize);
		rectangle.setLayoutX(y*pixelSize);
		rectangle.setLayoutY(x*pixelSize);
		
		
		rectangle.setOnMouseClicked(new EventHandler<Event>() {

			
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if (fill.isSelected()) {
					rectangle.setFill(colorPicker.getValue());
					flag=!flag;
				}
			}
		});
		
		rectangle.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (fill.isSelected()&&flag) {
					rectangle.setFill(colorPicker.getValue());
				}
			}
		});
		
		return rectangle;
	}
	
	void creatPixels() {
		int w=(int)image.getWidth();
		int h=(int)image.getHeight();
		PixelReader pixelReader=image.getPixelReader();
		recPixels=new Rectangle[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Color color=pixelReader.getColor(j, i);
				recPixels[i][j]=getRec(color, i, j);
			}
		}
		
		display();
		
	}
	
	 public void openClick(MouseEvent event) {
		
		
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open File");
		 fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("PNG", "*.png")
	            );
		 File file=fileChooser.showOpenDialog(null);
		
		 if (file!=null) {
			
			 image=new Image("file:"+file.getPath());
			
			creatPixels();
	
			
		}
		
	 }
	
	 
     public void saveClick(MouseEvent event) {
    	 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open File");
		 fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("PNG", "*.png")
	            );
		 File file=fileChooser.showSaveDialog(null);
		
    	 try {  
           ImageIO.write(SwingFXUtils.fromFXImage(getWImage(), null), "png", file);  
       } catch (IOException e) {  
           e.printStackTrace();  
       }  
		 
	 }
     
     WritableImage getWImage() {
    	 int h=recPixels.length;
    	 int w=recPixels[0].length;
    	 WritableImage writableImage=new WritableImage(w, h);
    	 PixelWriter pixelWriter=writableImage.getPixelWriter();
    	 for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Color color=(Color)recPixels[i][j].getFill();
				pixelWriter.setColor(j, i, color);
			}
		}
    	 
    	 
    	 return writableImage;
     }
	

}
