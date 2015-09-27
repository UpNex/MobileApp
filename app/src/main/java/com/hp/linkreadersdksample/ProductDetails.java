package com.hp.linkreadersdksample;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView serves;
    ImageView imageView;

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
                imageView = (ImageView) findViewById(R.id.image);
                serves = (TextView) findViewById(R.id.product_servings);

                productType.setText(product.getProductType());
                productName.setText(product.getProductName());
                calories.setText(product.getCalories());
                fat.setText(product.getFat());
                cholestrol.setText(product.getCholestrol());
                sugar.setText(product.getSugar());
                protein.setText(product.getProtein());
                vitamins.setText(product.getVitamins());
                ingredients.setText(product.getIngredients());
                serves.setText(String.valueOf(product.getServes()));
                Drawable image = getResources().getDrawable(product.getImageResourceId());
                imageView.setBackground(image);
                User user = User.getUserInstance();
                Set<String> allergies = user.getAllergies();
                Set<String> foodPreferences = user.getFoodPreferences();
                String[] foodIngredients = product.getIngredients().split(",");
                Button warningText = (Button) findViewById(R.id.warning);
                Boolean isAllergic = false;
                for(String foodIngredient: foodIngredients) {
                    //TODO: implement hashcode and equals
                    if (allergies.contains(foodIngredient.trim())) {
                        //TODO: set allergic food warning
                        isAllergic = true;
                        warningText.setBackgroundColor(getResources().getColor(R.color.dark_accent_red));
                        warningText.setText(getResources().getString(R.string.allergic_food));
                    }
                }

                ImageButton fitbitButton = (ImageButton) findViewById(R.id.fitbit);
                ImageButton addToCartButton = (ImageButton) findViewById(R.id.add_to_cart);

                fitbitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.fitbit_coming_soon), Toast.LENGTH_LONG).show();
                    }
                });

                addToCartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.add_to_cart_coming_soon), Toast.LENGTH_LONG).show();
                    }
                });
                /*for(String foodPreference: foodPreferences) {
                    //if (product.getProductType().equalsIgnoreCase(foodPreference)) {
                       if(!isAllergic){
                        if(foodPreference.equalsIgnoreCase(getResources().getString(R.string.vegan))) {
                            if(product.getProductType().equalsIgnoreCase())
                        }
                       }
                    //}
                }*/
            }
        } else {}
    }
}
