package com.hp.linkreadersdksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hp.linkreadersdksample.util.ProductUtil;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;

public class ProductDetails extends AppCompatActivity {

    TextView productType;
    TextView productName;
    TextView calories;
    TextView fat;
    TextView cholestrol;
    TextView sugar;
    TextView protein;
    TextView vitamins;
    TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String productId = extras.getString("content");

            Product product = ProductUtil.getProduct(productId);
            if(null != product){
                productType = (TextView) findViewById(R.id.product_type);
                productName = (TextView) findViewById(R.id.product_name);
                calories = (TextView) findViewById(R.id.product_calories);
                fat = (TextView) findViewById(R.id.product_fat);
                cholestrol = (TextView) findViewById(R.id.product_cholesterol);
                sugar = (TextView) findViewById(R.id.product_sugar);
                protein = (TextView) findViewById(R.id.product_protein);
                vitamins = (TextView) findViewById(R.id.product_vitamins);
                ingredients = (TextView) findViewById(R.id.product_ingredients);

                productType.setText(product.getProductType());
                productName.setText(product.getProductName());
                calories.setText(product.getCalories());
                fat.setText(product.getFat());
                cholestrol.setText(product.getCholestrol());
                sugar.setText(product.getSugar());
                protein.setText(product.getProtein());
                vitamins.setText(product.getVitamins());
                ingredients.setText(product.getIngredients());

                User user = User.getUserInstance();
                Set<String> allergies = user.getAllergies();
                Set<String> foodPreferences = user.getFoodPreferences();
                String[] foodIngredients = product.getIngredients().split(",");
                for(String foodIngredient: foodIngredients) {
                    //TODO: implement hashcode and equals
                    if (allergies.contains(foodIngredient)) {
                        //TODO: set allergic food warning
                    }
                }

                for(String foodPreference: foodPreferences) {
                    if (product.getProductType().equalsIgnoreCase(foodPreference)) {
                        //TODO: set allergic food warning
                    }
                }
            }
        } else {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
