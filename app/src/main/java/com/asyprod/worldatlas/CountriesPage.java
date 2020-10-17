package com.asyprod.worldatlas;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class CountriesPage extends AppCompatActivity {

    private int position;

    TextView countryName,countryPageName,countryCapital,countryNativeName,countryCurrencyName,countryCurrencySymbol,countryRegion,countrySubregion,countryPopulation,countryArea,countryDemonym,countryTimeZone,countryCoords,countryDomain,a3Code,callingCode,Languages,Borders;
    ImageView countryFlag,countryFlag1;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_page);

        getSupportActionBar().hide();

        position = getIntent().getIntExtra("position",0);

        countryName = findViewById(R.id.countryName);
        countryFlag = findViewById(R.id.countryFlag);
        countryPageName = findViewById(R.id.countryPageName);
        countryCapital = findViewById(R.id.countryPageCapital);
        countryNativeName = findViewById(R.id.countryPageNativeName);
        countryCurrencyName = findViewById(R.id.countryPageCurrencyName);
        countryCurrencySymbol = findViewById(R.id.countryPageCurrencySymbol);
        countryRegion = findViewById(R.id.countryPageRegion);
        countrySubregion = findViewById(R.id.countryPageSubRegion);
        countryPopulation = findViewById(R.id.countryPagePopulation);
        countryArea = findViewById(R.id.countryPageArea);
        countryDemonym = findViewById(R.id.countryPageDemonym);
        countryTimeZone = findViewById(R.id.countryPageTimeZone);
        countryCoords = findViewById(R.id.countryPageCoords);
        countryDomain = findViewById(R.id.countryPageTimeDomain);
        a3Code = findViewById(R.id.countryPagea3Code);
        callingCode = findViewById(R.id.countryPageCallingCode);
        Languages = findViewById(R.id.countryPageLangs);
        Borders = findViewById(R.id.countryBorders);
        countryFlag1 = findViewById(R.id.flag1);
        webView = findViewById(R.id.webView);

        countryName.setText(CountriesList.countryModelList.get(position).getName());
        countryPageName.setText(CountriesList.countryModelList.get(position).getName());
        countryCapital.setText(CountriesList.countryModelList.get(position).getCapitalName());
        countryNativeName.setText(CountriesList.countryModelList.get(position).getNativeName());
        countryCurrencyName.setText(CountriesList.countryModelList.get(position).getCurrencyName());
        countryCurrencySymbol.setText(CountriesList.countryModelList.get(position).getCurrencySymbol());
        countryRegion.setText(CountriesList.countryModelList.get(position).getRegion());
        countrySubregion.setText(CountriesList.countryModelList.get(position).getSubRegion());
        countryPopulation.setText(CountriesList.countryModelList.get(position).population);
        countryArea.setText(CountriesList.countryModelList.get(position).getArea()+" Square KM");
        countryDemonym.setText(CountriesList.countryModelList.get(position).getDemonym());
        countryTimeZone.setText(CountriesList.countryModelList.get(position).getTimeZone());
        countryCoords.setText(CountriesList.countryModelList.get(position).getLats()+","+CountriesList.countryModelList.get(position).getLongs());
        countryDomain.setText(CountriesList.countryModelList.get(position).getDomain());
        a3Code.setText(CountriesList.countryModelList.get(position).getA3Code());
        callingCode.setText("+"+CountriesList.countryModelList.get(position).getCallingCode());
        Languages.setText(CountriesList.countryModelList.get(position).getLanguages());
        Borders.setText(CountriesList.countryModelList.get(position).getBorders());
        GlideToVectorYou.justLoadImage(this, Uri.parse(CountriesList.countryModelList.get(position).getFlagURL()),countryFlag);
        GlideToVectorYou.justLoadImage(this, Uri.parse(CountriesList.countryModelList.get(position).getFlagURL()),countryFlag1);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("https://www.google.co.in/maps/@"+CountriesList.countryModelList.get(position).getLats()+","+CountriesList.countryModelList.get(position).getLongs()+",5.5z");
        webView.loadUrl("http://maps.google.com/?q="+CountriesList.countryModelList.get(position).getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearCache(true);
        webView.clearHistory();
    }


}