package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        //Initializing Sandwich object as null
        Sandwich sObject = null;
        // Declaring the KEY values
        final   String NAME_KEY = "name",
                MAIN_NAME_KEY = "mainName",
                ALSO_KNOWN_AS_KEY= "alsoKnownAs",
                PLACE_OF_ORIGIN_KEY= "placeOfOrigin",
                DESCRIPTION_KEY= "description",
                IMAGE_URL_KEY= "image",
                INGREDIENTS_KEY= "ingredients";


        try {

            JSONObject JSON_RootObject, nameObject;
            String mainName, placeOfOrigin, description, imageURL;

            List<String> alsoKnownAsList=new ArrayList<>();
            List<String> ingredientsList=new ArrayList<>();

            JSON_RootObject = new JSONObject(json);

            nameObject = JSON_RootObject.getJSONObject(NAME_KEY);

            mainName = nameObject.optString(MAIN_NAME_KEY);

            JSONArray alsoKnownAsArray = nameObject.getJSONArray(ALSO_KNOWN_AS_KEY);
            //Adding Array values to List
            for (int i = 0; i < alsoKnownAsArray.length(); i++)
            {
                String alsoKnown = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(alsoKnown);

            }

            placeOfOrigin = JSON_RootObject.optString(PLACE_OF_ORIGIN_KEY);
            description = JSON_RootObject.optString(DESCRIPTION_KEY);
            imageURL = JSON_RootObject.optString(IMAGE_URL_KEY);

            JSONArray ingredientsArray = JSON_RootObject.getJSONArray(INGREDIENTS_KEY);
            //Adding Array values to List
            for (int i = 0; i < ingredientsArray.length(); i++)
            {
                String ingredient = ingredientsArray.getString(i);
                ingredientsList.add(ingredient);
            }
            sObject = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin,
                    description, imageURL, ingredientsList);


        } catch (JSONException e) {
            e.printStackTrace();
        }
//Returning Sandwich Object
        return sObject;
    }

    }

