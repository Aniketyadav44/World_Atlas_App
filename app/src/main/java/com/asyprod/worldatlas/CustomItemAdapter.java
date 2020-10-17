package com.asyprod.worldatlas;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.List;

public class CustomItemAdapter  extends ArrayAdapter<countryModel> {

    private Context context;
    private List<countryModel> countryModelList;           //a list of all countries received from API call
    public List<countryModel> countryModelListFiltered;   //Creating a list of filtered list based on searched word

    public CustomItemAdapter(@NonNull Context context, @NonNull List<countryModel> countryModelList) {
        super(context, R.layout.custom_item_layout,countryModelList);
        this.context = context;
        this.countryModelList = countryModelList;
        //this.countryModelListFiltered.clear();
        this.countryModelListFiltered = countryModelList;    //Initially filtered list will contain all countries results
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_item_layout,null,false);

        ImageView flag = view.findViewById(R.id.flag);
        TextView name = view.findViewById(R.id.name);

        GlideToVectorYou.justLoadImage((Activity) parent.getContext(), Uri.parse(countryModelListFiltered.get(position).getFlagURL()),flag);
        //Glide.with(parent.getContext()).load(countryModelListFiltered.get(position).getFlagURL()).error(R.drawable.fade_blue).into(flag);
        name.setText(countryModelListFiltered.get(position).getName());
        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public countryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){            //Checking if user has not entered any contraints i.e string input in search Bar
                    filterResults.count = countryModelList.size();            //If empty then filtered result will be same as containing all resukts from API call
                    filterResults.values = countryModelList;
                }
                else{
                    List<countryModel> resultsArray = new ArrayList<>();                    //Creating a temporary array to store filtered country models in for loop
                    String searchStr = constraint.toString().toLowerCase();               //String of user entered input
                    for(countryModel itemModel : countryModelList){
                        if(itemModel.getName().toLowerCase().contains(searchStr)){
                            resultsArray.add(itemModel);
                        }
                        filterResults.count = resultsArray.size();
                        filterResults.values = resultsArray;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModelListFiltered = (List<countryModel>) results.values;
                CountriesList.countryModelList = (List<countryModel>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }
}
