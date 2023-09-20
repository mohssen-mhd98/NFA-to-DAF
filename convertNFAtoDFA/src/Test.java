/*
public class Test {
	public static void main(String [] args) {
		
		double s1=0;
		double s2 = 0;
		double n=1;
		System.out.println("kj");
		while(true) {
			s1 = 0;
			s2 = 0;
			//System.out.println("kj");
			for(double i=1;i<n+1;i++) { 
			 s1 = s1 + 1/i;
			 System.out.println("s1"+"--"+n+"--"+s1);
			}
			for(double i=n;i>0;i--) {
				 s2 = s2 + 1/i;
				System.out.println("s2"+"--"+n+"--"+s2);
			}
				if(s1!=s2) break;
			n++;
				System.out.println(n);
		}
		
		s1 = 0;
		s2 = 0;
		
		for(double i=1;i<10001;i++) { 
			 s1 = s1 + 1/i;
			
			}
		for(double i=10000;i>0;i--) {
		 s2 = s2 + 1/i;
		
	}
		 System.out.println("s1"+"--"+n+s1);
		System.out.println("s2"+"--"+n+s2);
	}


}*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application implements EventHandler<ActionEvent> {

    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Title of Window");
        button = new Button();
        button.setText("Hey baby");

        //This class will handle the button events
        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //When button is clicked, handle() gets called
    //Button click is an ActionEvent (also MouseEvents, TouchEvents, etc...)
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button) 
            System.out.println("Hey Charlie!");
        System.out.println(4 % -2);
    }

}
