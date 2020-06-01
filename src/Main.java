import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

 public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
         VBox pane = new VBox();
        Label before_sort = new Label("");
        Label after_sort = new Label("");
        Button sort = new Button("sort");
        Button randomize = new Button ("randomize");
        Sorter  sorter = new Sorter ();
        sorter.setN(3);
        Scene scene = new Scene(pane, 800, 600);

        int[] array = new int [13];
        for(int i = 0; i < array.length; i++ ) {
            array[i] = (int)(Math.random()*43);
            before_sort.setText(before_sort.getText() + ' ' + array[i]);
        }

        pane.getChildren().addAll(randomize, before_sort, sort, after_sort);
        pane.setAlignment(Pos.CENTER);
        randomize.setOnAction( e -> {
            before_sort.setText("");
            after_sort.setText("");
              for( int i = 0; i < array.length; i++){
                array[i] = (int)(Math.random()*43);
                before_sort.setText(before_sort.getText() + ' ' + array[i]);
            }
        } );
        pane.setSpacing(5);
        sort.setOnAction(e -> {
            sorter.sort(array);
            after_sort.setText("");
            for(int i = 0; i < array.length; i++){
                after_sort.setText(after_sort.getText() + ' ' + array[i]);

            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args ){ launch(args);

    }
    public Main(){

    }
    
}



