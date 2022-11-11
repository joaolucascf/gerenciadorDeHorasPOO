package poofinal.application;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class formatCPF {
    Pattern pat = Pattern.compile("[0-9]*");
    public UnaryOperator<TextFormatter.Change> justNumbers = c -> {
        if(pat.matcher(c.getControlNewText()).matches()){
            return c;
        }
        return null;
    };
}
