package at.sw2017.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends Activity implements View.OnClickListener
{

    private Button button0, button1, button2, button3, button4, button5, button6, button7,
        button8, button9, buttonEqual, buttonAdd, buttonSub, buttonMul, buttonDiv,
        buttonClear;
    private ArrayList<Button> numberButtons = new ArrayList<Button>();
    private TextView numberView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        buttonEqual = (Button)findViewById(R.id.buttonEqual);
        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonSub = (Button)findViewById(R.id.buttonSub);
        buttonMul = (Button)findViewById(R.id.buttonMul);
        buttonDiv = (Button)findViewById(R.id.buttonDiv);
        buttonClear = (Button)findViewById(R.id.buttonClear);

        numberView = (TextView)findViewById(R.id.textView);

        buttonEqual.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

//        button3 = (Button)findViewById(R.id.button3);
//        button3.setOnClickListener(this);


        setUpNumberButtonListener();
    }


    public void setUpNumberButtonListener()
    {
        for(int i = 0; i <= 9; i++)
        {
            String buttonName = "button" + i;
            int id = getResources().getIdentifier(buttonName, "id", R.class.getPackage().getName());
            Button button = (Button)findViewById(id);
            button.setOnClickListener(this);
            numberButtons.add(button);
        }
    }


    @Override
    public void onClick(View v)
    {
        Button clickedButton = (Button)v;
        switch (clickedButton.getId())
        {
            case R.id.buttonAdd:
                break;
            case R.id.buttonSub:
                break;
            case R.id.buttonMul:
                break;
            case R.id.buttonDiv:
                break;
            case R.id.buttonEqual:
                break;
            case R.id.buttonClear:
                clearTextView();
                break;
            default:
                String recentNumber = numberView.getText().toString();
                if (recentNumber.equals("0"))
                {
                    recentNumber = "";
                }
                recentNumber += clickedButton.getText().toString();
                numberView.setText(recentNumber);
        }

    }

    private void clearTextView()
    {
        numberView.setText("0");
    }
}
