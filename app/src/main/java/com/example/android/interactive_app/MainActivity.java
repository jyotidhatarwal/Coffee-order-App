package com.example.android.interactive_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
       // import android.support.v7.app.AppCompatActivity;
        import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

   // int numberOfCoffe=0;
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
   /* int price = quantity*3 ;
   * */

        EditText name = (EditText)findViewById(R.id.Name_view);
        String value = name.getText().toString();


        CheckBox whippedCream = (CheckBox)findViewById(R.id.checkedbox);
        boolean hasWhippedCream = whippedCream.isChecked();



        CheckBox chocolate = (CheckBox)findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();


        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createMethodSummary(value,price,hasWhippedCream,hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "jyoti.dhatarwal1999@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for customer: " + value);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

   /*  displayMessage(priceMessage);
   * this method is not used now because we do not want to display the order sumary to the user app interface now
   */
    }

    public void increment(View view){

        if(quantity == 100){
            Toast.makeText(this,"You can not order more than 100 cups at a time", Toast.LENGTH_SHORT).show();
            return;
        }


        quantity++;
        display(quantity);

    }
    public void decrement(View view){

        if(quantity == 1){
            Toast.makeText(this,"You can not order less than one cup",Toast.LENGTH_SHORT).show();
            return;
        }


        quantity--;
            display(quantity);


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /*
    *creating a method for calculating price.
    * @return total price
    *
     */


    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
      int basePrice =5;

      if(addWhippedCream == true){
          basePrice += 1;
      }

      if(addChocolate == true){
          basePrice += 2;
      }
      return quantity*basePrice;
    }

    private String createMethodSummary(String name,int price , boolean whippedCream, boolean chocolate){
        String priceMessage =  "Name: " + name ;
        priceMessage = priceMessage + "\n" + "Add Whipped Cream ?" + whippedCream;
        priceMessage = priceMessage +"\n" + "Add Chocolate? " + chocolate;
        priceMessage = priceMessage + "\n" + "Quantity: " + quantity;
        priceMessage = priceMessage +  "\n" +"Total: $" +  price ;
        priceMessage = priceMessage + "\n" +"Thank you!";

        return priceMessage;
    }


}