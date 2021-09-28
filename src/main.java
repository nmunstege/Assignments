import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class main extends Application implements EventHandler<ActionEvent>{
    StringProperty passwordFieldProperty;

    public static void main(String[] args) {
        launch(args);
    }

    private boolean isValid(String text){
        if(text == null){
            return false;
        }
        if(text.length() < 8){
            return false;
        }
        if(!containsNumber(text)){
            return false;
        }
        if(!containsLetter(text)){
            return false;
        }
        if(!containsSpecialChar(text)){
            return false;
        }
        return true;
    }

    private boolean containsNumber(String text){
        for (char c: text.toCharArray()) {
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    private boolean containsLetter(String text){
        for (char c: text.toCharArray()) {
            if(Character.isLetter(c)){
                return true;
            }
        }
        return false;
    }
    private boolean containsSpecialChar(String text){
        String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        for (char c : text.toCharArray()) {
            if(specialCharacters.contains(String.valueOf(c))){
                return true;
            }
        }
        return false;
    }


    @Override
    public void handle(ActionEvent actionEvent) {

    }

    @Override
    public void start(Stage logInScreen) throws Exception {
        logInScreen.setTitle("Login Screen");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(8);

        Label userLabel = new Label("Username: ");
        grid.setConstraints(userLabel,0,0);
        Label passwordLabel = new Label("Password: ");
        grid.setConstraints(passwordLabel, 0, 1);

        TextField userInput = new TextField("username");
        grid.setConstraints(userInput,1,0);
        PasswordField passwordInput = new PasswordField();
        grid.setConstraints(passwordInput, 1,1);

        Button loginButton = new Button("Log in");
        grid.setConstraints(loginButton, 1, 2);
        loginButton.setDisable(true);

        grid.getChildren().addAll(userInput,userLabel, passwordInput, passwordLabel, loginButton);
        Scene scene = new Scene(grid);
        logInScreen.setScene(scene);
        logInScreen.show();

        passwordFieldProperty = passwordInput.textProperty();

        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(isValid(newValue)){
                    loginButton.setDisable(false);
                } else
                {
                    loginButton.setDisable(true);
                }
            }
        });



    }


}
