package home.rxjavatest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import home.rxjavatest.rest.PBBransches;


public class AdapterList extends  RecyclerView.Adapter<AdapterList.UserListViewHolder> {

    List<PBBransches> bransches;
    BranchListner listener;


    public static class UserListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView tvText1;
        TextView tvText2;

        UserListViewHolder(View itemView) {
            super(itemView);
            linearLayout =  itemView.findViewById(R.id.ll_gridview);
            tvText1 = itemView.findViewById(R.id.tvText1);
            tvText2 = itemView.findViewById(R.id.tvText2);
        }
    }

    public AdapterList(List<PBBransches> bransches, BranchListner listener){
        this.bransches = bransches;
        this.listener = listener;
    }

    public List<PBBransches> getBransches() {
        return bransches;
    }

    @Override
    public AdapterList.UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cellgrid, parent, false);
        UserListViewHolder pvh = new UserListViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(UserListViewHolder holder, final int position) {
        holder.tvText1.setText(getBransches().get(position).getAddress());
        holder.tvText2.setText(getBransches().get(position).getCity());
        holder.linearLayout.setOnClickListener(view -> listener.getBranch(getBransches().get(position)));

    }

    public void addBranch(PBBransches bransch){
        getBransches().add(bransch);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return bransches.size();
    }
    public  void clear(){
        getBransches().clear();
        notifyDataSetChanged();
    }
}

