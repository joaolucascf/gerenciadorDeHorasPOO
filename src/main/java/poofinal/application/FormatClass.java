package poofinal.application;

import javafx.beans.value.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class FormatClass {
    private static Pattern patNum = Pattern.compile("[0-9]*");
    private static Pattern patEmail = Pattern.compile("^(.+)@(\\S+)$");

    public UnaryOperator<TextFormatter.Change> justNumbers = c -> {
        if(patNum.matcher(c.getControlNewText()).matches()){
            return c;
        }
        return null;
    };

    public static boolean checkEmail(String email){
        if(patEmail.matcher(email).matches()) {
            String[] splitter = email.split("@");
            if(splitter[1].equals("inf.ufpel.edu.br"))
                return true;
            return false;
        }
        return false;
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}
