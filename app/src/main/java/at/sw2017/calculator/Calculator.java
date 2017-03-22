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
    private Integer firstNumber;
    private static State state = State.INIT;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        buttonEqual =(Button)findViewById(R.id.buttonEqual);
        buttonAdd =(Button)findViewById(R.id.buttonAdd);
        buttonSub =(Button)findViewById(R.id.buttonSub);
        buttonMul =(Button)findViewById(R.id.buttonMul);
        buttonDiv =(Button)findViewById(R.id.buttonDiv);
        buttonClear =(Button)findViewById(R.id.buttonClear);

        numberView =(TextView)findViewById(R.id.textView);

        buttonEqual.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonClear.setOnClickListener(this);



        setUpNumberButtonListener();
    }


    public void setUpNumberButtonListener()
    {
        for(int i = 0; i <= 9; i++)
        {
            String buttonName = "button" + i;
            int id = getResources().getIdentifier(buttonName, "id", R.class.getPackage().getName());
            Button button =(Button)findViewById(id);
            button.setOnClickListener(this);
            numberButtons.add(button);
        }
    }


    @Override
    public void onClick(View v)
    {
        Button clickedButton =(Button)v;
        switch(clickedButton.getId())
        {
            case R.id.buttonAdd:
                clearNumberView();
                state = State.ADD;
                break;
            case R.id.buttonSub:
                clearNumberView();
                state = State.SUB;
                break;
            case R.id.buttonMul:
                clearNumberView();
                state = State.MUL;
                break;
            case R.id.buttonDiv:
                clearNumberView();
                state = State.DIV;
                break;
            case R.id.buttonEqual:
                calculateResult();
                state = State.INIT;
                break;
            case R.id.buttonClear:
                clearTextView();
                break;
            default:
                String recentNumber = numberView.getText().toString();
                if(state == State.INIT)
                {
                    recentNumber = "";
                    state = State.NUM;
                }
                recentNumber += clickedButton.getText().toString();
                numberView.setText(recentNumber);
        }

    }

    private void clearTextView()
    {
        numberView.setText("0");
        firstNumber = 0;
        state = State.INIT;
    }

    private void clearNumberView()
    {
        String tempString = numberView.getText().toString();
        if(!tempString.equals(""))
        {
            firstNumber = Integer.valueOf(tempString);
        }
        numberView.setText("");
    }

    private void calculateResult()
    {
        int secondNumber = 0;
        String tempString = numberView.getText().toString();
        if(!tempString.equals(""))
        {
            secondNumber = Integer.valueOf(tempString);
        }
        int result;
        switch(state)
        {
            case ADD:
                result = Calculations.doAddition(firstNumber, secondNumber);
                break;
            case SUB:
                result = Calculations.doSubtraction(firstNumber, secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber, secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber, secondNumber);
                break;
            default:
                result = secondNumber;
        }
        numberView.setText(Integer.toString(result));
    }
}
