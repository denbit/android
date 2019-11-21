package calcualator.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

enum Operator{First,Second}
public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private StringBuffer operand2;
    private  String operator;
    private Operator selector= Operator.First;
    private StringBuffer operand1;
    private float buffer;
    private StringBuffer operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operand1 = new StringBuffer();
        operand2 = new StringBuffer();
        operations = new StringBuffer();
    }
    public void enterDigit(View view){
        Button   btnview = (Button) view;
         Resources res =  btnview.getResources();
         String elName =  res.getResourceEntryName(view.getId());
        char digit = btnview.getText().charAt(0);
        Log.d(TAG, "enterDigit: ".concat(elName).concat("  "+Character.toString(digit)));
        showValue(String.valueOf(btnview.getText()));
        if (selector == Operator.First){
            operand1.append(btnview.getText());

        } else {
            operand2.append(btnview.getText());
        }

    }
    void showValue(String text){
        Log.d(TAG, "enterDigit: " +text);
        operations.append(text);
        EditText operationsField =  findViewById(R.id.operations);
        operationsField.setText(operations.toString());

    }
    public void enterSign(View view){
        Button   btnview = (Button) view;
        Resources res =  btnview.getResources();
        String elName =  res.getResourceEntryName(view.getId());
        String digit = String.valueOf(btnview.getText());
        Log.d(TAG, "enterSign: ".concat(elName).concat("  "+digit));
        showValue(digit);
        if (digit.compareTo("C")==0) {
            buffer = 0;
            operand1.delete(0, operand1.length());
            operations.delete(0,operations.length());
            operand2.delete(0, operand2.length());
            selector = Operator.First;
            showValue("");
            Log.d(TAG, "enterSign: Cleared ");
            return;
        }

        if (selector == Operator.First){
            selector = Operator.Second;
            operator = digit;
        } else if (selector == Operator.Second) {

            switch (operator){
                case  "+":
                    buffer =  Float.parseFloat(operand1.toString()) + Float.parseFloat(operand2.toString());
                    break;
                case  "-":
                    buffer =  Float.parseFloat(operand1.toString()) - Float.parseFloat(operand2.toString());
                    break;
                case  "*":
                    buffer =  Float.parseFloat(operand1.toString()) * Float.parseFloat(operand2.toString());
                    break;
                case  "/":
                    buffer =  Float.parseFloat(operand1.toString()) / Float.parseFloat(operand2.toString());
                    break;
                case  "=":
                    buffer = Float.parseFloat(operand1.toString());
                    break;
            }
            EditText result =  findViewById(R.id.result);
            result.setText(String.valueOf(buffer));
            operand1.delete(0, operand1.length());
            operand2.delete(0, operand2.length());
            operand1.append(buffer);
            operator = "";
            selector = Operator.First;
        }


    }
}
