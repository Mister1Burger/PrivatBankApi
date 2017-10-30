package home.rxjavatest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import home.rxjavatest.rest.PBBransches;

/**
 * Created by Burge on 27.10.2017.
 */

public class PBBranchesAdapter extends BaseAdapter {
    Context contxt;
    LayoutInflater layoutInflater;
    List<PBBransches> bransches;

    public PBBranchesAdapter(Context contxt, List<PBBransches> bransches) {
        this.contxt = contxt;
        this.bransches = bransches;
        layoutInflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return bransches.size();
    }

    @Override
    public Object getItem(int position) {
        return bransches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.cellgrid,parent,false);

        }
        PBBransches bransch = getBranch(position);

        ((TextView) view.findViewById(R.id.tvText1)).setText(bransch.getAddress());
        ((TextView) view.findViewById(R.id.tvText2)).setText(bransch.getCity());

        return view;
    }

    PBBransches getBranch(int position) {
        return ((PBBransches) getItem(position));
    }
}

