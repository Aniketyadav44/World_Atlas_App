package com.asyprod.worldatlas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CountriesList extends AppCompatActivity {

    //Creating instances of all views
    ListView listView;
    EditText search;
    SimpleArcLoader loader;

    //The url string for HTTP request
    String urlString;

    private Map<String, Locale> localeMap;

    public static List<countryModel> countryModelList = new ArrayList<>();
    countryModel countryModels;
    CustomItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);

        getSupportActionBar().hide();

        urlString = getIntent().getStringExtra("urlString");

        listView = findViewById(R.id.countries);
        search = findViewById(R.id.search);
        loader = findViewById(R.id.loader);

        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemAdapter.getFilter().filter(s);
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fetch();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CountriesList.this,CountriesPage.class);
                i.putExtra("position",position);
                startActivity(i);
            }
        });

    }

    public void fetch(){
        String url1 = "https://restcountries.eu/rest/v2/"+urlString;
        initCountryCodeMapping();
        loader.start();
        listView.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
        StringRequest request1 = new StringRequest(StringRequest.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    countryModelList.clear();
                    JSONArray resultsArray = new JSONArray(response);
                    for(int i=0;i<resultsArray.length();i++){
                        JSONObject countryObj = resultsArray.getJSONObject(i);
                        String name = countryObj.getString("name");
                        String capital = countryObj.getString("capital");
                        String region = countryObj.getString("region");
                        String subregion = countryObj.getString("subregion");
                        String population = (String)countryObj.getString("population");
                        JSONArray coords = countryObj.getJSONArray("latlng");
                        String lats = "";
                        String longs = "";
                        if(coords.length()>0){
                             lats = coords.getString(0);
                             longs = coords.getString(1);
                        }
                        String demonym = countryObj.getString("demonym");
                        String area = (String)countryObj.getString("area");
                        JSONArray tzArray = countryObj.getJSONArray("timezones");
                        String timeZones = tzArray.getString(0);
                        String nativeName = countryObj.getString("nativeName");
                        JSONArray currencyInfoArray = countryObj.getJSONArray("currencies");
                        JSONObject currencyObj = currencyInfoArray.getJSONObject(0);
                        String currencyName = (String)currencyObj.getString("name");
                        String currencySymbol = (String)currencyObj.getString("symbol");
                        String flagUrl = countryObj.getString("flag");
                        String a3Code = countryObj.getString("alpha3Code");

                        String borders = "";
                        JSONArray bordersArray = countryObj.getJSONArray("borders");
                        for(int j=0;j<bordersArray.length();j++){
                            String cName = getName(bordersArray.getString(j));
                            if(j>0){
                                borders+=","+cName;
                            }else{
                                borders+=cName;
                            }
                        }


                        JSONArray domainArray = countryObj.getJSONArray("topLevelDomain");
                        String domain = "";
                        if(domainArray.length()>0){
                            domain = domainArray.getString(0);
                        }

                        JSONArray callingCodeArray = countryObj.getJSONArray("callingCodes");
                        String callingCode = "";
                        if(callingCodeArray.length()>0){
                            callingCode = callingCodeArray.getString(0);
                        }

                        JSONArray langArray = countryObj.getJSONArray("languages");
                        String languages = "";
                        if(langArray.length()>0){
                            for(int a=0;a<langArray.length();a++){
                                JSONObject langObj = langArray.getJSONObject(a);
                                String lang = langObj.getString("name");
                                if(a>0){
                                    languages+=","+lang;
                                }else{
                                    languages+=lang;
                                }
                            }
                        }

                        countryModels = new countryModel(name,capital,region,subregion,population,lats,longs,demonym,area,timeZones,nativeName,currencyName,currencySymbol,flagUrl,borders,domain,a3Code,callingCode,languages);
                        countryModelList.add(countryModels);
                    }

                    itemAdapter = new CustomItemAdapter(CountriesList.this,countryModelList);
                    listView.setAdapter(itemAdapter);

                    loader.stop();
                    loader.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.stop();
                    loader.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountriesList.this, "Something went wrong, Please try again later!", Toast.LENGTH_SHORT).show();
                loader.stop();
                loader.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(CountriesList.this);
        requestQueue.add(request1);
    }

    private void initCountryCodeMapping() {
        String[] countries = Locale.getISOCountries();
        localeMap = new HashMap<String, Locale>(countries.length);
        for (String country : countries) {
            Locale locale = new Locale("", country);
            localeMap.put(locale.getISO3Country().toUpperCase(), locale);
        }
    }

    private String iso3ToIso2(String iso3CountryCode) {
        if(localeMap.get(iso3CountryCode)!=null) {
            return localeMap.get(iso3CountryCode).getCountry();
        }
        else{
            return null;
        }
    }

    public String getName(String code){
        String name=" ";
        if(iso3ToIso2(code)!=null) {
            Locale locale = new Locale("en", iso3ToIso2(code));
            name = locale.getDisplayCountry();
            return name;
        }else{
            return " ";
        }
    }
}