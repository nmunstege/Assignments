import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    StringProperty passwordFieldProperty;
    Label visiblePassword;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage logInScreen) throws Exception {
        logInScreen.setTitle("Login Screen");

        VBox layout = new VBox(10);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(8);

        Label userLabel = new Label("Username: ");
        GridPane.setConstraints(userLabel, 0, 0);
        Label passwordLabel = new Label("Password: ");
        GridPane.setConstraints(passwordLabel, 0, 1);

        TextField userInput = new TextField("username");
        GridPane.setConstraints(userInput, 1, 0);
        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setVisible(false);

        passwordFieldProperty = passwordInput.textProperty();

        visiblePassword = new Label();
        visiblePassword.setPadding(new Insets(10));
        visiblePassword.textProperty().bind(passwordFieldProperty);

        grid.getChildren().addAll(userInput, userLabel, passwordInput, passwordLabel, loginButton);
        layout.getChildren().addAll(grid, visiblePassword);
        Scene scene = new Scene(layout);
        logInScreen.setScene(scene);
        logInScreen.show();


        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                loginButton.setVisible(isValid(newValue));
            }
        });
    }

    private boolean isValid(String text) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLetter(c)) {
                hasLetter = true;
            } else {
                hasSpecial = true;
            }
        }
        return text.length() >= 8 && (hasLetter && hasDigit && hasSpecial);
    }
}
